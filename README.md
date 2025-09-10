## code-rabbit-eval-java-microservice

Spring Boot (Java 21) microservice scaffold used to evaluate CodeRabbit's automated code review on a realistic project.

Important: This repository intentionally includes several issues in PR#1 to let CodeRabbit surface findings. Do NOT copy any insecure patterns from PR#1 to production. No real secrets are included.

### Quick start
- Requirements: Java 21, Maven 3.9+, Docker (optional)
- Build: `mvn clean package`
- Run: `mvn spring-boot:run`

### Project layout
```
src/
  main/java/org/
    api/             # REST controllers
    model/           # Domain models
    service/         # Business logic
    validator/       # Validation utilities
    configuration/   # App config
    Application.java # Spring Boot entrypoint
  main/resources/
    application.yml
  test/java/org/
    api/             # Controller tests (mocked service)
    model/           # Model tests
docs/CodeReview/     # CodeRabbit reports and suggested patches
```

### Notes on intentional issues (PR#1)
This codebase intentionally contains examples of:
- Missing Javadoc for public types/members
- Use of `System.out.println` in application code
- A hard-coded placeholder secret value (for demonstration only)
- Overly broad exception handling and ignored exceptions
- Non-thread-safe static mutable state
- Formatting inconsistencies (tabs vs spaces), long methods
- A case-mismatched folder under `src/main/java/org/` to trigger folder policy
- A dependency with an older version to trigger SCA
- Incomplete unit tests (<80% coverage)

These are deliberate to exercise CodeRabbit's policies. Do not use these patterns in production.

### Secrets policy
Secrets must come from environment variables or configuration. Any literal secret appearing in code is a placeholder for demonstration only.

### Docker
Build and run with Docker:
```bash
docker build -t code-rabbit-eval-java-microservice .
docker run -p 8080:8080 code-rabbit-eval-java-microservice
```

### CodeRabbit artifacts
After running CodeRabbit on PRs, reports will be saved under:
- `docs/CodeReview/pr1-coderabbit-report.json`
- `docs/CodeReview/pr1-coderabbit-summary.md`
- `docs/CodeReview/pr1-suggested-patches/`
- `docs/CodeReview/pr2-coderabbit-report.json`
- `docs/CodeReview/pr2-coderabbit-summary.md`
- `docs/CodeReview/comparison-pr1-vs-pr2.md`
- `docs/CodeReview/pr2-auto-applied-patches/` (if any)

### Disclaimer
This repository contains intentionally insecure/poor patterns solely to demonstrate automated code review. Replace all such code in subsequent PRs.


