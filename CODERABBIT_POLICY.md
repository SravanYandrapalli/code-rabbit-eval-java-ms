Copy these rules into CodeRabbit configuration/policy:

```
coverage: core-modules >= 80%  # require 80% unit-test coverage for core modules (service, validator, api)
no-hardcoded-secrets: true     # flag any literal-looking secrets (password=, secret=, API_KEY, etc.)
folder-naming: enforce         # repo must match src/main/java/org/{api,model,service,validator,configuration} exactly (case-sensitive)
forbid-system-out: true        # System.out.println usage is forbidden; prefer a logger
formatting: enforce            # apply/prefer consistent formatting rules (Java standard)
javadoc-required: public-members
secrets-by-env: true           # secrets/config sensitive values must come from env or config (no literals)
security-checks: high          # run SCA and flag suspicious dependency versions and common security antipatterns
exception-handling: no-silence # flag empty or overly-broad exception swallowing
thread-safety-checks: enabled  # detect unsafe static mutability or concurrency issues
```


