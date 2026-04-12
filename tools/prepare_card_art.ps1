param(
    [string]$SourceDir = "custom_art",
    [string]$MaskSourceDir = "custom_art\\masks",
    [string]$OutputDir = "src\\main\\resources\\img\\cardenergy\\cards\\raw",
    [string]$MaskOutputDir = "src\\main\\resources\\img\\cardenergy\\cards\\masks"
)

$ErrorActionPreference = "Stop"
Add-Type -AssemblyName System.Drawing

$repoRoot = Split-Path -Parent $PSScriptRoot
$sourcePath = Join-Path $repoRoot $SourceDir
$maskSourcePath = Join-Path $repoRoot $MaskSourceDir
$outputPath = Join-Path $repoRoot $OutputDir
$maskOutputPath = Join-Path $repoRoot $MaskOutputDir

$cardNames = @(
    'AshenCount','BarbedGuard','Brace','BraceForImpact','BurnThrough','DugIn',
    'GuardTheHeap','HiddenCache','Hunker','LooseParts','PackedSwing','Patchwork',
    'PileDriver','Recovery','RottingBlow','RottingShelter','RottingSlash','SalvageSwing',
    'ScavengeTheWreck','ScrapBurst','ScrapKnife','ScrapSpray','ScroungeDefend',
    'ScroungeStrike','Stockpile','TurnAside'
)

function New-CroppedImage($srcPath, $dstPath, $targetWidth, $targetHeight) {
    $src = [System.Drawing.Image]::FromFile($srcPath)
    try {
        $targetRatio = [double]$targetWidth / [double]$targetHeight
        $srcRatio = [double]$src.Width / [double]$src.Height

        if ($srcRatio -gt $targetRatio) {
            $cropHeight = $src.Height
            $cropWidth = [int][Math]::Round($cropHeight * $targetRatio)
            $cropX = [int](($src.Width - $cropWidth) / 2)
            $cropY = 0
        } else {
            $cropWidth = $src.Width
            $cropHeight = [int][Math]::Round($cropWidth / $targetRatio)
            $cropX = 0
            $cropY = [int](($src.Height - $cropHeight) / 2)
        }

        $bmp = New-Object System.Drawing.Bitmap $targetWidth, $targetHeight
        try {
            $graphics = [System.Drawing.Graphics]::FromImage($bmp)
            try {
                $graphics.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic
                $graphics.CompositingQuality = [System.Drawing.Drawing2D.CompositingQuality]::HighQuality
                $graphics.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::HighQuality
                $graphics.PixelOffsetMode = [System.Drawing.Drawing2D.PixelOffsetMode]::HighQuality
                $graphics.DrawImage(
                    $src,
                    (New-Object System.Drawing.Rectangle 0,0,$targetWidth,$targetHeight),
                    $cropX,
                    $cropY,
                    $cropWidth,
                    $cropHeight,
                    [System.Drawing.GraphicsUnit]::Pixel
                )
            } finally {
                $graphics.Dispose()
            }
            $bmp.Save($dstPath, [System.Drawing.Imaging.ImageFormat]::Png)
        } finally {
            $bmp.Dispose()
        }
    } finally {
        $src.Dispose()
    }
}

if (-not (Test-Path $sourcePath)) {
    throw "Missing source art directory: $sourcePath"
}

if (-not (Test-Path $maskSourcePath)) {
    throw "Missing mask source directory: $maskSourcePath"
}

$sourceFiles = Get-ChildItem -Path $sourcePath -File |
    Where-Object { $_.DirectoryName -notlike "*\\masks" } |
    Sort-Object Name

if ($sourceFiles.Count -eq 0) {
    throw "No source art files found in $sourcePath"
}

New-Item -ItemType Directory -Force -Path $outputPath | Out-Null
New-Item -ItemType Directory -Force -Path $maskOutputPath | Out-Null

for ($i = 0; $i -lt $cardNames.Count; $i++) {
    $cardName = $cardNames[$i]
    $src = $sourceFiles[$i % $sourceFiles.Count].FullName
    $dst = Join-Path $outputPath ($cardName + '.png')
    New-CroppedImage $src $dst 500 380
}

Copy-Item -LiteralPath (Join-Path $maskSourcePath 'attack_mask_rgba.png') -Destination (Join-Path $maskOutputPath 'attack_mask_rgba.png') -Force
Copy-Item -LiteralPath (Join-Path $maskSourcePath 'power_mask_rgba.png') -Destination (Join-Path $maskOutputPath 'power_mask_rgba.png') -Force
Copy-Item -LiteralPath (Join-Path $maskSourcePath 'skill_mask_rgba.png') -Destination (Join-Path $maskOutputPath 'skill_mask_rgba.png') -Force

Write-Output "Prepared raw art for $($cardNames.Count) cards."
Write-Output "Raw art: $outputPath"
Write-Output "Masks:   $maskOutputPath"
