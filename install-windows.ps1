<#
.SYNOPSIS
  Installs Handcrafted (and its ResourcefulLib dependency) into a Minecraft mods folder. Windows version;
  see install-linux.sh (Linux) and install-macos.sh (macOS).

.DESCRIPTION
  Where the jar comes from:
    - run from a repository checkout (gradlew next to this script): the mod is compiled (code only);
    - run from a release download (handcrafted-<loader>-*.jar next to this script): that jar is used.

  The mod's artwork (textures, models, sounds, language files) is All Rights Reserved by its original authors and is
  not part of this repository or its jars. After the jar is ready this script runs AssetInstaller.java, which fetches
  the artwork from the original mod's official Modrinth download onto this machine and adds it to the installed jar.
  See the README ("Licensing"). Older copies of Handcrafted and ResourcefulLib in the mods folder are replaced.
  Fabric Loader / Fabric API / NeoForge themselves are assumed to be installed already and are never touched.

.PARAMETER ModsDir
  Target mods folder. Defaults to %APPDATA%\.minecraft\mods.

.PARAMETER Loader
  fabric (default) or neoforge.

.PARAMETER SkipBuild
  Use the jar already in <loader>\build\libs instead of rebuilding.

.PARAMETER McJar
  Path to the Minecraft jar (e.g. ...\versions\26.2\26.2.jar). Only needed if it is not in the default .minecraft folder.

.EXAMPLE
  .\install-windows.ps1
  .\install-windows.ps1 -ModsDir "D:\games\mc\mods" -Loader neoforge
#>
param(
    [string]$ModsDir = (Join-Path $env:APPDATA ".minecraft\mods"),
    [ValidateSet("fabric", "neoforge")][string]$Loader = "fabric",
    [switch]$SkipBuild,
    [string]$McJar
)

$ErrorActionPreference = "Stop"
$root = $PSScriptRoot

# Versions: from gradle.properties in a checkout, otherwise derived from the jar name / ResourcefulLib's latest.
$props = @{}
$propsFile = Join-Path $root "gradle.properties"
if (Test-Path $propsFile) {
    Get-Content $propsFile | ForEach-Object {
        if ($_ -match '^\s*([^#=\s]+)\s*=\s*(.*)$') { $props[$Matches[1]] = $Matches[2].Trim() }
    }
}

if (-not (Test-Path $ModsDir)) { New-Item -ItemType Directory -Path $ModsDir | Out-Null }

# 1. Get a code-only jar
$haveGradle = Test-Path (Join-Path $root "gradlew.bat")
if ($haveGradle -and -not $SkipBuild) {
    Write-Host "Building Handcrafted ($Loader, code only)..."
    Push-Location $root
    try {
        & .\gradlew.bat ":${Loader}:build" -PcodeOnly --console=plain
        if ($LASTEXITCODE -ne 0) { throw "Gradle build failed" }
    } finally { Pop-Location }
}
$searchDirs = @((Join-Path $root "$Loader\build\libs"), $root) | Where-Object { Test-Path $_ }
$modJar = $searchDirs | ForEach-Object { Get-ChildItem $_ -Filter "handcrafted-$Loader-*.jar" -ErrorAction SilentlyContinue } |
    Where-Object { $_.Name -notlike "*-sources.jar" } |
    Sort-Object LastWriteTime -Descending | Select-Object -First 1
if (-not $modJar) { throw "No handcrafted-$Loader-*.jar found. Run this from a repository checkout, or put the release jar next to this script." }

$mc = if ($props["minecraftVersion"]) { $props["minecraftVersion"] } elseif ($modJar.Name -match "^handcrafted-$Loader-([^-]+)-") { $Matches[1] } else { throw "Cannot determine the Minecraft version" }
$rlib = $props["resourcefulLibVersion"]
if (-not $rlib) {
    $meta = Invoke-WebRequest -UseBasicParsing "https://maven.teamresourceful.com/repository/maven-public/com/teamresourceful/resourcefullib/resourcefullib-$Loader-$mc/maven-metadata.xml"
    $rlib = ([xml]$meta.Content).metadata.versioning.release
}

# 2. Work in a temp folder so a failure never leaves the mods folder half-updated.
$tmp = Join-Path ([IO.Path]::GetTempPath()) "handcrafted-install"
if (Test-Path $tmp) { Remove-Item $tmp -Recurse -Force }
New-Item -ItemType Directory -Path $tmp | Out-Null

$rlibName = "resourcefullib-$Loader-$mc-$rlib.jar"
Write-Host "Downloading $rlibName..."
Invoke-WebRequest -UseBasicParsing -OutFile (Join-Path $tmp $rlibName) `
    -Uri "https://maven.teamresourceful.com/repository/maven-public/com/teamresourceful/resourcefullib/resourcefullib-$Loader-$mc/$rlib/$rlibName"

# 3. Add the artwork (from the original mod, via the licensing workaround)
$stagedMod = Join-Path $tmp $modJar.Name
Copy-Item $modJar.FullName $stagedMod
$installer = @((Join-Path $root "tools\AssetInstaller.java"), (Join-Path $root "AssetInstaller.java")) | Where-Object { Test-Path $_ } | Select-Object -First 1
if (-not $installer) { throw "AssetInstaller.java not found next to this script (or in tools\)." }
$installerArgs = @($installer, $stagedMod)
if ($McJar) { $installerArgs += @("--mc-jar", $McJar) }
Write-Host "Adding artwork with AssetInstaller..."
$ErrorActionPreference = "Continue"   # java writes its warnings to stderr
& java @installerArgs
$ErrorActionPreference = "Stop"
if ($LASTEXITCODE -ne 0) { throw "AssetInstaller failed (needs Java 25+ on PATH)" }

# 4. Remove old versions, then copy the new ones in.
$toInstall = @(
    @{ Name = $modJar.Name; Pattern = "handcrafted-$Loader-*"; Path = $stagedMod },
    @{ Name = $rlibName; Pattern = "resourcefullib-$Loader-*"; Path = (Join-Path $tmp $rlibName) }
)
foreach ($item in $toInstall) {
    Get-ChildItem $ModsDir -Filter "$($item.Pattern).jar" | ForEach-Object {
        Write-Host "Removing $($_.Name)"
        try { Remove-Item $_.FullName -Force -ErrorAction Stop }
        catch { throw "Cannot replace $($_.Name): is Minecraft (or a server) running with it loaded? Close it and re-run." }
    }
    Copy-Item $item.Path (Join-Path $ModsDir $item.Name) -Force
    Write-Host "Installed $($item.Name)"
}

Remove-Item $tmp -Recurse -Force
Write-Host "`nDone. Mods folder: $ModsDir"
