<#
.SYNOPSIS
  Installs Handcrafted and its dependencies into a Minecraft mods folder. Windows version;
  see install-linux.sh (Linux) and install-macos.sh (macOS).

.DESCRIPTION
  Dependencies installed automatically (skip with -NoDeps):
    - ResourcefulLib (always replaced by the matching version)
    - Fabric API (Fabric only, and only if the mods folder has no fabric-api jar yet)

  Where the Handcrafted jar comes from:
    - run from a repository checkout (gradlew next to this script): the mod is compiled (code only);
    - run from a release download (handcrafted-<loader>-*.jar next to this script): that jar is used.

  The mod's artwork (textures, models, sounds, language files) is All Rights Reserved by its original authors and is
  not part of this repository or its jars. After the jar is ready this script runs AssetInstaller.java, which fetches
  the artwork from the original mod's official Modrinth download onto this machine and adds it to the installed jar.
  See the README ("Licensing"). Older copies of Handcrafted (and ResourcefulLib) in the mods folder are replaced.
  Fabric Loader / NeoForge themselves are assumed to be installed already and are never touched.

.PARAMETER ModsDir
  Target mods folder. Defaults to %APPDATA%\.minecraft\mods.

.PARAMETER Loader
  fabric (default) or neoforge.

.PARAMETER SkipBuild
  Use the jar already in <loader>\build\libs instead of rebuilding.

.PARAMETER NoDeps
  Do not install or replace dependencies (ResourcefulLib, Fabric API); only Handcrafted itself is installed.

.PARAMETER McJar
  Path to the Minecraft jar (e.g. ...\versions\<mc>\<mc>.jar). Only needed if it is not in the default .minecraft folder.

.EXAMPLE
  .\install-windows.ps1
  .\install-windows.ps1 -ModsDir "D:\games\mc\mods" -Loader neoforge -NoDeps
#>
param(
    [string]$ModsDir = (Join-Path $env:APPDATA ".minecraft\mods"),
    [ValidateSet("fabric", "neoforge")][string]$Loader = "fabric",
    [switch]$SkipBuild,
    [switch]$NoDeps,
    [string]$McJar
)

$ErrorActionPreference = "Stop"
$root = $PSScriptRoot

# Versions: from gradle.properties in a checkout, otherwise derived from the jar name / the Maven repositories.
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

# 2. Work in a temp folder so a failure never leaves the mods folder half-updated.
$tmp = Join-Path ([IO.Path]::GetTempPath()) "handcrafted-install"
if (Test-Path $tmp) { Remove-Item $tmp -Recurse -Force }
New-Item -ItemType Directory -Path $tmp | Out-Null

# 3. Dependencies (unless -NoDeps)
$rlibName = $null
$fabricApiName = $null
if (-not $NoDeps) {
    $rlibRepo = "https://maven.teamresourceful.com/repository/maven-public/com/teamresourceful/resourcefullib/resourcefullib-$Loader-$mc"
    $rlib = $props["resourcefulLibVersion"]
    if (-not $rlib) {
        $meta = Invoke-WebRequest -UseBasicParsing "$rlibRepo/maven-metadata.xml"
        $rlib = ([xml]$meta.Content).metadata.versioning.release
    }
    $rlibName = "resourcefullib-$Loader-$mc-$rlib.jar"
    Write-Host "Downloading $rlibName..."
    Invoke-WebRequest -UseBasicParsing -OutFile (Join-Path $tmp $rlibName) -Uri "$rlibRepo/$rlib/$rlibName"

    if ($Loader -eq "fabric") {
        $existingApi = Get-ChildItem $ModsDir -Filter "fabric-api-*.jar" -ErrorAction SilentlyContinue | Where-Object { $_.Name -notlike "*-sources.jar" }
        if ($existingApi) {
            Write-Host "Fabric API already present, leaving it alone."
        } else {
            $fabricRepo = "https://maven.fabricmc.net/net/fabricmc/fabric-api/fabric-api"
            if ($props["fabricApiVersion"]) {
                $fapi = "$($props['fabricApiVersion'])+$mc"
            } else {
                $meta = Invoke-WebRequest -UseBasicParsing "$fabricRepo/maven-metadata.xml"
                $fapi = ([xml]$meta.Content).metadata.versioning.versions.version | Where-Object { $_ -like "*+$mc" } | Select-Object -Last 1
                if (-not $fapi) { throw "Could not find a Fabric API version for Minecraft $mc" }
            }
            $fabricApiName = "fabric-api-$fapi.jar"
            Write-Host "Downloading $fabricApiName..."
            Invoke-WebRequest -UseBasicParsing -OutFile (Join-Path $tmp $fabricApiName) -Uri "$fabricRepo/$fapi/$fabricApiName"
        }
    }
}

# 4. Add the artwork (from the original mod, via the licensing workaround)
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

# 5. Remove old versions, then copy the new ones in.
$toInstall = @(@{ Name = $modJar.Name; Pattern = "handcrafted-$Loader-*"; Path = $stagedMod })
if ($rlibName) { $toInstall += @{ Name = $rlibName; Pattern = "resourcefullib-$Loader-*"; Path = (Join-Path $tmp $rlibName) } }
if ($fabricApiName) { $toInstall += @{ Name = $fabricApiName; Pattern = $null; Path = (Join-Path $tmp $fabricApiName) } }
foreach ($item in $toInstall) {
    if ($item.Pattern) {
        Get-ChildItem $ModsDir -Filter "$($item.Pattern).jar" | ForEach-Object {
            $old = $_
            Write-Host "Removing $($old.Name)"
            try { Remove-Item $old.FullName -Force -ErrorAction Stop }
            catch { throw "Cannot replace $($old.Name): is Minecraft (or a server) running with it loaded? Close it and re-run." }
        }
    }
    Copy-Item $item.Path (Join-Path $ModsDir $item.Name) -Force
    Write-Host "Installed $($item.Name)"
}

Remove-Item $tmp -Recurse -Force
Write-Host "`nDone. Mods folder: $ModsDir"
