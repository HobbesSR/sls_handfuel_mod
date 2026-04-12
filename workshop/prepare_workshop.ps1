$ErrorActionPreference = "Stop"

$repoRoot = Split-Path -Parent $PSScriptRoot
$maven = Join-Path $repoRoot ".tools\\apache-maven-3.9.11\\bin\\mvn.cmd"
$jarSource = Join-Path $repoRoot "target\\CardEnergy.jar"
$contentDir = Join-Path $PSScriptRoot "content"
$previewDir = Join-Path $PSScriptRoot "preview"
$previewSource = Join-Path $repoRoot "src\\main\\resources\\img\\cardenergy\\charSelect\\handfuelPortrait.jpg"
$jarTarget = Join-Path $contentDir "CardEnergy.jar"
$previewTarget = Join-Path $previewDir "preview.jpg"

$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-8.0.482.8-hotspot"

& $maven -q -DskipTests package "-Dbuild.channel=workshop" "-Dbuild.channel.display=Workshop" "-Dbuild.channel.mod_name_suffix= [Workshop]"

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
