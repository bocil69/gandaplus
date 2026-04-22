# AGENTS.md

## What this repo is

- Android app-virtualization workspace with two active Gradle modules: `:app` and `:lib` (`settings.gradle`).
- `:app` is the host UI/app shell; `:lib` is the virtual engine core with AIDL + JNI/NDK pieces.
- `:libsandhook` is present on disk but intentionally disabled in `settings.gradle`; do not wire work to it unless the user explicitly asks.
- `va-jadx/` is decompiled reference material, not build source.

## Read first

- Root build/version config: `build.gradle`, `gradle.properties`, `VAConfig.gradle`, `gradle/wrapper/gradle-wrapper.properties`.
- Nearest module instructions before editing code: `app/AGENTS.md`, `lib/AGENTS.md`, and deeper `lib/src/main/java/**/AGENTS.md` files.
- Storage/_FIX work: read `STORAGE_FIX_README.md` before touching `*_FIX.java` files.
- Scope guardrails from prior planning live in `.sisyphus/plans/project-improvement.md`.

## Real entrypoints and ownership

- Host app bootstraps through `app/src/main/java/io/virtualapp/App.java` (`android:name=".App"` in `app/src/main/AndroidManifest.xml`).
- Main app flow lives in `app/src/main/java/io/virtualapp/home/`; `HomePresenterImpl` owns launch routing, onboarding gating, spoof validation, and permission checks.
- Virtual engine entry singleton is `lib/src/main/java/com/lody/virtual/client/core/VirtualCore.java`.
- App-start hook injection flows through `lib/src/main/java/com/lody/virtual/client/VClient.java` → `AppCallback.beforeStartApplication(...)` → `app/src/main/java/io/virtualapp/delegate/MyComponentDelegate.java`.
- Legacy package-targeted network hooks are centralized in `app/src/main/java/io/virtualapp/hook/network/NetworkSpoofManager.java`; keep changes aligned with its policy gate, not ad hoc package checks elsewhere.
- App-list backup metadata lives in `app/src/main/java/io/virtualapp/home/repo/AppListBackupManager.java`; `HomeActivity` calls `backupAppList()` in multiple paths, so persistence changes need extra care.

## Build and verification commands

- Full local debug build: `./gradlew clean :lib:assembleDebug :app:assembleDebug`
- Unit tests: `./gradlew :lib:testDebugUnitTest :app:testDebugUnitTest`
- Lint: `./gradlew :lib:lintDebug :app:lintDebug --warning-mode all`
- Prefer `:app:assembleDebug` / module tasks for verification; avoid broad `:app:build` unless the user explicitly wants full release-task expansion.
- Gradle wrapper is pinned to `8.4` (`gradle/wrapper/gradle-wrapper.properties`); AGP is `8.2.0` (root `build.gradle`).

## Repo-specific quirks that matter

- Package identity is not fixed in the manifest alone: `VAConfig.gradle` sets `PACKAGE_NAME_32BIT`, `PACKAGE_NAME_ARM64`, and supports `-Pappid=...` overrides.
- Version data is split: `VAConfig.gradle` defines `VERSION` / `VERSION_CODE` for shared/lib usage, while `app/build.gradle` hardcodes its own `versionCode` / `versionName`. Do not assume changing one updates both.
- Both modules target SDK 34, min SDK 21, Java 8, and arm64-v8a only. `:lib` uses `ndkBuild` with `src/main/jni/Android.mk`, not CMake.
- `app/build.gradle` sets `debuggable false` even in the `debug` build type; do not assume a normal debuggable-debug workflow.
- Root repositories still include `jcenter()` plus two HTTP XDJA Maven endpoints with `allowInsecureProtocol = true`; do not remove them without proving dependency resolution still works.
- `gradle.properties` enables AndroidX + Jetifier and Gradle parallelism.
- No CI workflows, pre-commit config, or `opencode.json` were found at the repo root during inspection, so local Gradle commands are the source of truth.
- `rg` is not available in this shell; use the provided search tools instead of assuming ripgrep exists.

## Editing guardrails

- Treat `app/src/main/AndroidManifest.xml` and especially `lib/src/main/AndroidManifest.xml` as high-risk wiring files; they define many permissions, stubs, and services used by the virtual engine.
- Do not add new bypass/hooking/spoofing capabilities unless the user explicitly requests that scope; existing plan docs in `.sisyphus/` constrain work toward hardening and verification, not capability expansion.
- Follow the nearest nested `AGENTS.md` when working inside `app/`, `lib/`, or deeper `lib/src/main/java/...` areas.
