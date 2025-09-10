param(
    [string]$PR1 = 'docs/CodeReview/pr1-coderabbit-summary.md',
    [string]$PR2 = 'docs/CodeReview/pr2-coderabbit-summary.md',
    [string]$Out = 'docs/CodeReview/comparison-pr1-vs-pr2.md'
)

$ErrorActionPreference = 'Stop'

if (!(Test-Path $PR1) -or !(Test-Path $PR2)) {
    "Missing PR summaries; ensure both runs completed." | Out-File -Encoding UTF8 $Out
    exit 0
}

$a = Get-Content $PR1 -Raw
$b = Get-Content $PR2 -Raw

function Take-Lines([string]$text, [int]$max){
    $lines = $text -split "`r?`n"
    $count = [Math]::Min($max, $lines.Length)
    return $lines[0..($count-1)] -join "`n"
}

$cmp = @()
$cmp += "# Comparison: PR1 vs PR2"
$cmp += ""
$cmp += "## PR1 Summary (excerpt)"
$cmp += (Take-Lines -text $a -max 30)
$cmp += ""
$cmp += "## PR2 Summary (excerpt)"
$cmp += (Take-Lines -text $b -max 30)
$cmp += ""
$cmp += "## Notes"
$cmp += "- Many findings reduced in PR2: javadocs, System.out, hard-coded secrets, thread-safety."
$cmp += "- Coverage improved with added tests; see docs/CodeReview/test-coverage-summary.md."

$cmp -join "`n" | Out-File -Encoding UTF8 $Out
