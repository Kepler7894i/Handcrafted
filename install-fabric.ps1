<#
.SYNOPSIS
  Builds the Fabric version of Handcrafted and installs it, plus the dependencies it needs
  (ResourcefulLib), into a local Minecraft mods folder. Older copies of those
  jars are removed first. Fabric Loader and Fabric API are assumed to already be installed and are never touched.

.PARAMETER ModsDir
  Target mods folder. Defaults to %APPDATA%\.minecraft\mods.

.PARAMETER Server
  Shortcut for -ModsDir "\\wsl.localhost\Ubuntu\home\samma\minecraft_server\26.2\mods".

.PARAMETER SkipBuild
  Install the jar that is already in fabric\build\libs instead of rebuilding.

.EXAMPLE
  .\install-fabric.ps1
  .\install-fabric.ps1 -ModsDir "D:\games\mc\mods" -SkipBuild
#>
param(
    [string]$ModsDir = (Join-Path $env:APPDATA ".minecraft\mods"),
    [switch]$Server,
    [switch]$SkipBuild
)

if ($Server) { $ModsDir = "\\wsl.localhost\Ubuntu\home\samma\minecraft_server\26.2\mods" }

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

# 1. Build
if (-not $SkipBuild) {
    Write-Host "Building Handcrafted (Fabric)..."
    Push-Location $root
    try {
        & .\gradlew.bat :fabric:build --console=plain
        if ($LASTEXITCODE -ne 0) { throw "Gradle build failed" }
    } finally { Pop-Location }
}

$modJar = Get-ChildItem (Join-Path $root "fabric\build\libs") -Filter "handcrafted-fabric-$mc-*.jar" |
    Where-Object { $_.Name -notlike "*-sources.jar" } |
    Sort-Object LastWriteTime -Descending | Select-Object -First 1
if (-not $modJar) { throw "No built jar found in fabric\build\libs (run without -SkipBuild)." }

# 2. Download dependencies to a temp folder first, so a failed download doesn't leave the mods folder half-updated.
$tmp = Join-Path ([IO.Path]::GetTempPath()) "handcrafted-install"
if (Test-Path $tmp) { Remove-Item $tmp -Recurse -Force }
New-Item -ItemType Directory -Path $tmp | Out-Null

$deps = @(
    @{ Name = "resourcefullib-fabric-$mc-$rlib.jar"; Pattern = "resourcefullib-fabric-*";
       Url = "https://maven.teamresourceful.com/repository/maven-public/com/teamresourceful/resourcefullib/resourcefullib-fabric-$mc/$rlib/resourcefullib-fabric-$mc-$rlib.jar" }
)
foreach ($d in $deps) {
    Write-Host "Downloading $($d.Name)..."
    Invoke-WebRequest -Uri $d.Url -OutFile (Join-Path $tmp $d.Name) -UseBasicParsing
}

# 3. Remove old versions, then copy the new ones in.
$toInstall = @(@{ Name = $modJar.Name; Pattern = "handcrafted-fabric-*"; Path = $modJar.FullName }) +
    ($deps | ForEach-Object { @{ Name = $_.Name; Pattern = $_.Pattern; Path = (Join-Path $tmp $_.Name) } })

foreach ($item in $toInstall) {
    Get-ChildItem $ModsDir -Filter "$($item.Pattern).jar" | ForEach-Object {
        Write-Host "Removing $($_.Name)"
        Remove-Item $_.FullName -Force
    }
    Copy-Item $item.Path (Join-Path $ModsDir $item.Name) -Force
    Write-Host "Installed $($item.Name)"
}

Remove-Item $tmp -Recurse -Force
Write-Host "`nDone. Mods folder: $ModsDir"
