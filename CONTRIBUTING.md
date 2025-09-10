## Contributing

### Branching and PRs
- Default branch: `main`
- Feature branches: `feature/<short-description>`
- PRs must include a clear description and reference relevant docs
- For fixes after CodeRabbit review, open PRs stacked on the prior feature branch (e.g., PR#3 based on PR#1) to keep diffs focused

### Team rules (enforced by CodeRabbit)
- coverage: core-modules >= 80%
- no-hardcoded-secrets: true
- folder-naming: enforce
- forbid-system-out: true
- formatting: enforce
- javadoc-required: public-members
- secrets-by-env: true
- security-checks: high
- exception-handling: no-silence
- thread-safety-checks: enabled

### Secrets
- Never commit real secrets; use env vars and `application.yml` placeholders
 - Prefer property keys (e.g., `app.secret`) with Spring relaxed binding over raw env names in annotations

### Development
- Java 21, Maven 3.9+
- Run locally: `mvn spring-boot:run`
- Tests: `mvn test`
 - Do not commit build artifacts (e.g., `target/`); add to `.gitignore`


