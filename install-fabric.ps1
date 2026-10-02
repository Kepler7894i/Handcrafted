<#
.SYNOPSIS
  Builds the Fabric version of Handcrafted and installs it, plus ResourcefulLib, into a Minecraft mods folder.
  Older copies of those jars are removed first. Fabric Loader and Fabric API are assumed to already be installed
  and are never touched.

.DESCRIPTION
  The mod's artwork (textures, models, sounds, language files) is All Rights Reserved by its original authors and is
  not part of this repository. This script therefore always builds a code-only jar and then runs
  tools/AssetInstaller.java on it, which fetches the artwork from the original mod's official Modrinth download
  onto this machine and adds it to the installed jar. See the README ("Licensing") for details.

.PARAMETER ModsDir
  Target mods folder. Defaults to %APPDATA%\.minecraft\mods.

.PARAMETER SkipBuild
  Install the code-only jar already in fabric\build\libs instead of rebuilding.

.PARAMETER McJar
  Path to the Minecraft jar (e.g. ...\versions\26.2\26.2.jar). Only needed if it is not in the default .minecraft folder.

.EXAMPLE
  .\install-fabric.ps1
  .\install-fabric.ps1 -ModsDir "D:\games\mc\mods" -SkipBuild
#>
param(
    [string]$ModsDir = (Join-Path $env:APPDATA ".minecraft\mods"),
    [switch]$SkipBuild,
    [string]$McJar
)

$ErrorActionPreference = "Stop"
$root = $PSScriptRoot

# Versions come from gradle.properties so they always match what the mod was built against.
$props = @{}
Get-Content (Join-Path $root "gradle.properties") | ForEach-Object {
    if ($_ -match '^\s*([^#=\s]+)\s*=\s*(.*)$') { $props[$Matches[1]] = $Matches[2].Trim() }
}
$mc = $props["minecraftVersion"]
$rlib = $props["resourcefulLibVersion"]

if (-not (Test-Path $ModsDir)) { New-Item -ItemType Directory -Path $ModsDir | Out-Null }

# 1. Build (always code-only; the artwork is added by the asset installer below)
if (-not $SkipBuild) {
    Write-Host "Building Handcrafted (Fabric, code only)..."
    Push-Location $root
    try {
        & .\gradlew.bat :fabric:build -PcodeOnly --console=plain
        if ($LASTEXITCODE -ne 0) { throw "Gradle build failed" }
    } finally { Pop-Location }
}

$modJar = Get-ChildItem (Join-Path $root "fabric\build\libs") -Filter "handcrafted-fabric-$mc-*.jar" |
    Where-Object { $_.Name -notlike "*-sources.jar" } |
    Sort-Object LastWriteTime -Descending | Select-Object -First 1
if (-not $modJar) { throw "No built jar found in fabric\build\libs (run without -SkipBuild)." }

# 2. Work in a temp folder so a failure never leaves the mods folder half-updated.
$tmp = Join-Path ([IO.Path]::GetTempPath()) "handcrafted-install"
if (Test-Path $tmp) { Remove-Item $tmp -Recurse -Force }
New-Item -ItemType Directory -Path $tmp | Out-Null

$rlibName = "resourcefullib-fabric-$mc-$rlib.jar"
Write-Host "Downloading $rlibName..."
Invoke-WebRequest -UseBasicParsing -OutFile (Join-Path $tmp $rlibName) `
    -Uri "https://maven.teamresourceful.com/repository/maven-public/com/teamresourceful/resourcefullib/resourcefullib-fabric-$mc/$rlib/$rlibName"

# 3. Add the artwork (from the original mod, via the licensing workaround)
$stagedMod = Join-Path $tmp $modJar.Name
Copy-Item $modJar.FullName $stagedMod
$installerArgs = @((Join-Path $root "tools\AssetInstaller.java"), $stagedMod)
if ($McJar) { $installerArgs += @("--mc-jar", $McJar) }
Write-Host "Adding artwork with AssetInstaller..."
$ErrorActionPreference = "Continue"   # java writes its warnings to stderr
& java @installerArgs
$ErrorActionPreference = "Stop"
if ($LASTEXITCODE -ne 0) { throw "AssetInstaller failed (needs Java 25+ on PATH)" }

# 4. Remove old versions, then copy the new ones in.
$toInstall = @(
    @{ Name = $modJar.Name; Pattern = "handcrafted-fabric-*"; Path = $stagedMod },
    @{ Name = $rlibName; Pattern = "resourcefullib-fabric-*"; Path = (Join-Path $tmp $rlibName) }
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
