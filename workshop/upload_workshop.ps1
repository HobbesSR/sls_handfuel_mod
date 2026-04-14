param(
    [Parameter(Mandatory = $true)]
    [string]$SteamUser,

    [string]$ChangeNote
)

$ErrorActionPreference = "Stop"

$steamCmd = "C:\Users\Corey\AppData\Local\Microsoft\WinGet\Packages\Valve.SteamCMD_Microsoft.Winget.Source_8wekyb3d8bbwe\steamcmd.exe"
$itemVdf = Join-Path $PSScriptRoot "item.vdf"
$prepareScript = Join-Path $PSScriptRoot "prepare_workshop.ps1"
$repoRoot = Split-Path $PSScriptRoot -Parent

if (-not (Test-Path $steamCmd)) {
    throw "steamcmd.exe not found at $steamCmd"
}

if (-not (Test-Path $itemVdf)) {
    throw "Workshop descriptor not found at $itemVdf"
}

if (-not (Test-Path $prepareScript)) {
    throw "Workshop prepare script not found at $prepareScript"
}

function Get-DefaultChangeNote {
    try {
        $gitNote = (& git -C $repoRoot log -1 --pretty=%s 2>$null)
        if ($LASTEXITCODE -eq 0 -and -not [string]::IsNullOrWhiteSpace($gitNote)) {
            return $gitNote.Trim()
        }
    }
    catch {
    }

    return "Workshop update."
}

if ([string]::IsNullOrWhiteSpace($ChangeNote)) {
    $ChangeNote = Get-DefaultChangeNote
}

$itemVdfText = Get-Content $itemVdf -Raw
$escapedChangeNote = $ChangeNote.Replace('\', '\\').Replace('"', '\"')
$tempItemVdf = Join-Path $env:TEMP ("cardenergy_workshop_{0}.vdf" -f ([guid]::NewGuid().ToString("N")))
$updatedItemVdfText = [regex]::Replace(
    $itemVdfText,
    '"changenote"\s+"(?:[^"\\]|\\.)*"',
    ('"changenote"' + "`t`t" + '"' + $escapedChangeNote + '"')
)
Set-Content -LiteralPath $tempItemVdf -Value $updatedItemVdfText -Encoding ASCII

Write-Output "Launching SteamCMD for Workshop upload."
Write-Output "Steam user: $SteamUser"
Write-Output "Descriptor template: $itemVdf"
Write-Output "Upload descriptor: $tempItemVdf"
Write-Output "Change note: $ChangeNote"
Write-Output ""
Write-Output "SteamCMD will prompt for your password and Steam Guard code interactively if needed."

try {
    & powershell -ExecutionPolicy Bypass -File $prepareScript

    & $steamCmd +login $SteamUser +workshop_build_item $tempItemVdf +quit
}
finally {
    if (Test-Path $tempItemVdf) {
        Remove-Item -LiteralPath $tempItemVdf -Force
    }
}
