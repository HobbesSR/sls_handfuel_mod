$ErrorActionPreference = "Stop"

$repoRoot = Split-Path -Parent $PSScriptRoot
$maven = Join-Path $repoRoot ".tools\\apache-maven-3.9.11\\bin\\mvn.cmd"
$jarSource = Join-Path $repoRoot "target\\CardEnergy.jar"
$jarTarget = "C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\CardEnergy.jar"

$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-8.0.482.8-hotspot"

& $maven -q -DskipTests package "-Dbuild.channel=local" "-Dbuild.channel.display=Local" "-Dbuild.channel.mod_name_suffix= [Local]"

if (-not (Test-Path $jarSource)) {
    throw "Missing built jar at $jarSource"
}

Copy-Item -LiteralPath $jarSource -Destination $jarTarget -Force
Write-Output "Installed local build to $jarTarget"
