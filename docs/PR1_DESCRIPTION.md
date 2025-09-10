### PR#1: Initial microservice scaffold (intentional issues)

Purpose: evaluate CodeRabbit on typical Java/Spring microservice code.

Intentional issue categories included:
- A) Rule violations/team-specific: missing Javadoc, System.out, hard-coded placeholder secret, folder naming mismatch
- B) Quality/security/perf/maintainability: catch-all exception swallow, non-thread-safe static cache, naive SQL-like concatenation, missing controller input validation, incomplete tests
- C) Style/formatting: tabs vs spaces, inconsistent brace style, long method
- D) Config/deps: older commons-io version

Team rules for CodeRabbit evaluation (see CODERABBIT_POLICY.md):
```
coverage: core-modules >= 80%
no-hardcoded-secrets: true
folder-naming: enforce
forbid-system-out: true
formatting: enforce
javadoc-required: public-members
secrets-by-env: true
security-checks: high
exception-handling: no-silence
thread-safety-checks: enabled
```

Artifacts to save after CodeRabbit run:
- docs/CodeReview/pr1-coderabbit-report.json
- docs/CodeReview/pr1-coderabbit-summary.md
- docs/CodeReview/pr1-suggested-patches/


