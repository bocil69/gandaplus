# GandaPlus

GandaPlus is an Android VirtualApp-based workspace for cloned app execution, per-clone spoofing, fake location control, deep-link routing, and runtime compatibility work.

## Modules

- `app/` — host UI, onboarding, launcher, clone management, fake-location screens, delegates
- `lib/` — virtual engine core, client/server hooks, manifest stubs, IPC, virtualization runtime

## Build

Use the Gradle wrapper from the repo root:

```bash
./gradlew :lib:assembleDebug :app:assembleDebug
```

## Notes

- This repository intentionally excludes local debug artifacts, pulled APKs, screenshots, UI dumps, and agent/session metadata.
- Release signing is not configured in this workspace.
