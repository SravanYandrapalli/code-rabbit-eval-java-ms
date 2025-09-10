# Requires: public repo (no auth) or a GitHub token in $env:GITHUB_TOKEN for higher rate limits
param(
    [string]$Owner = "SravanYandrapalli",
    [string]$Repo = "code-rabbit-eval-java-ms",
    [int]$PrNumber,
    [string]$OutputPrefix = "pr"
)

$ErrorActionPreference = 'Stop'

$headers = @{ 'User-Agent'='coderabbit-fetch'; 'Accept'='application/vnd.github+json' }
if ($env:GITHUB_TOKEN) { $headers.Authorization = "Bearer $($env:GITHUB_TOKEN)" }

if (-not $PrNumber) {
    $prs = Invoke-RestMethod -Headers $headers -Uri "https://api.github.com/repos/$Owner/$Repo/pulls?state=open"
    if (-not $prs) {
        $prs = Invoke-RestMethod -Headers $headers -Uri "https://api.github.com/repos/$Owner/$Repo/pulls?state=all&per_page=1&sort=created&direction=desc"
    }
    $pr = $prs | Select-Object -First 1
    if (-not $pr) { Write-Warning "No PRs found for $Owner/$Repo"; exit 0 }
    $PrNumber = $pr.number
}

$outPrefix = "docs/CodeReview/${OutputPrefix}"
New-Item -ItemType Directory -Force -Path 'docs/CodeReview' | Out-Null
New-Item -ItemType Directory -Force -Path "${outPrefix}-suggested-patches" | Out-Null

$pr = Invoke-RestMethod -Headers $headers -Uri "https://api.github.com/repos/$Owner/$Repo/pulls/$PrNumber"
$issueComments = Invoke-RestMethod -Headers $headers -Uri "https://api.github.com/repos/$Owner/$Repo/issues/$PrNumber/comments"
$reviewComments = Invoke-RestMethod -Headers $headers -Uri "https://api.github.com/repos/$Owner/$Repo/pulls/$PrNumber/comments"
$reviews = Invoke-RestMethod -Headers $headers -Uri "https://api.github.com/repos/$Owner/$Repo/pulls/$PrNumber/reviews"

$obj = [PSCustomObject]@{
    repository = $Repo
    owner = $Owner
    pr = $pr
    issueComments = $issueComments
    reviewComments = $reviewComments
    reviews = $reviews
    fetchedAt = [DateTime]::UtcNow
}

$reportPath = "${outPrefix}-coderabbit-report.json"
$summaryPath = "${outPrefix}-coderabbit-summary.md"

$obj | ConvertTo-Json -Depth 12 | Out-File -Encoding UTF8 $reportPath

$allBodies = @()
if ($issueComments) { $allBodies += ($issueComments | ForEach-Object { $_.body }) }
if ($reviewComments) { $allBodies += ($reviewComments | ForEach-Object { $_.body }) }

$lines = @(
    "# CodeRabbit Summary for PR #$PrNumber",
    "Repository: $Owner/$Repo",
    "PR URL: $($pr.html_url)",
    "",
    "## First 10 comments (truncated)"
)

$i = 0
foreach ($b in $allBodies) {
    if ($null -ne $b) {
        $one = '- ' + ($b -replace "`r",' ' -replace "`n",' ')
        if ($one.Length -gt 500) { $one = $one.Substring(0,500) }
        $lines += $one
        $i++
        if ($i -ge 10) { break }
    }
}

$lines -join "`n" | Out-File -Encoding UTF8 $summaryPath
Write-Host "Saved $reportPath and $summaryPath for PR #$PrNumber"
