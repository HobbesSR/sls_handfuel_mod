$ErrorActionPreference = "Stop"

$repoRoot = Split-Path -Parent $PSScriptRoot
$jarSource = Join-Path $repoRoot "target\\CardEnergy.jar"
$contentDir = Join-Path $PSScriptRoot "content"
$previewDir = Join-Path $PSScriptRoot "preview"
$previewSource = Join-Path $repoRoot "src\\main\\resources\\img\\cardenergy\\charSelect\\handfuelPortrait.jpg"
$jarTarget = Join-Path $contentDir "CardEnergy.jar"
$previewTarget = Join-Path $previewDir "preview.jpg"

if (-not (Test-Path $jarSource)) {
    throw "Missing built jar at $jarSource. Build the project first."
}

if (-not (Test-Path $previewSource)) {
    throw "Missing preview source image at $previewSource."
}

New-Item -ItemType Directory -Force -Path $contentDir | Out-Null
New-Item -ItemType Directory -Force -Path $previewDir | Out-Null

Copy-Item -LiteralPath $jarSource -Destination $jarTarget -Force
Copy-Item -LiteralPath $previewSource -Destination $previewTarget -Force

Write-Output "Workshop content prepared:"
Write-Output "  $jarTarget"
Write-Output "  $previewTarget"
