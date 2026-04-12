param(
    [Parameter(Mandatory = $true)]
    [string]$SteamUser
)

$ErrorActionPreference = "Stop"

$steamCmd = "C:\Users\Corey\AppData\Local\Microsoft\WinGet\Packages\Valve.SteamCMD_Microsoft.Winget.Source_8wekyb3d8bbwe\steamcmd.exe"
$itemVdf = Join-Path $PSScriptRoot "item.vdf"
$prepareScript = Join-Path $PSScriptRoot "prepare_workshop.ps1"

if (-not (Test-Path $steamCmd)) {
    throw "steamcmd.exe not found at $steamCmd"
}

if (-not (Test-Path $itemVdf)) {
    throw "Workshop descriptor not found at $itemVdf"
}

if (-not (Test-Path $prepareScript)) {
    throw "Workshop prepare script not found at $prepareScript"
}

Write-Output "Launching SteamCMD for Workshop upload."
Write-Output "Steam user: $SteamUser"
Write-Output "Descriptor: $itemVdf"
Write-Output ""
Write-Output "SteamCMD will prompt for your password and Steam Guard code interactively if needed."

& powershell -ExecutionPolicy Bypass -File $prepareScript

& $steamCmd +login $SteamUser +workshop_build_item $itemVdf +quit
