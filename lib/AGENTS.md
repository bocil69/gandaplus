# LIB MODULE — Virtual Engine Core

The heart of VirtuApp. Client hooks intercept Android framework calls; server manages virtual app lifecycle. IPC via AIDL between client (virtual app process) and server (host process).

## STRUCTURE

```
lib/src/main/
├── java/
│   ├── com/lody/virtual/    # VirtualApp engine
│   │   ├── client/          # Client-side: hooks, IPC stubs, process bootstrap
│   │   │   ├── core/        # VClientImpl, VirtualCore — engine entry points
│   │   │   ├── hook/        # Framework API proxies (121 files — AMS, PMS, etc.)
│   │   │   ├── ipc/         # IPC wrapper classes for system services
│   │   │   ├── stub/        # Stub activities/services for manifest
│   │   │   ├── env/         # Virtual environment setup
│   │   │   └── fixer/       # Compatibility fixers
│   │   ├── server/          # Server-side: virtual system services
│   │   │   ├── am/          # VActivityManagerService (15 files)
│   │   │   ├── pm/          # VPackageManagerService (27 files + installer)
│   │   │   ├── accounts/    # VAccountManagerService
│   │   │   ├── content/     # SyncManager, SyncStorageEngine
│   │   │   ├── notification/ # VNotificationManagerService
│   │   │   ├── device/      # Device config
│   │   │   ├── location/    # Location service
│   │   │   └── ...          # job, memory, vs, bit64, secondary
│   │   ├── helper/          # Shared utilities (persistence, compat, collection)
│   │   ├── remote/          # Remote data objects (Parcelable)
│   │   ├── os/              # VEnvironment — virtual filesystem paths
│   │   ├── open/            # Public API
│   │   └── oem/             # OEM-specific workarounds
│   ├── com/xdja/            # XDJA security SDK integration (52 files)
│   │   ├── zs/              # Safekey, watermark, permissions, installers
│   │   ├── activitycounter/ # Activity counting, float icon, screen lock
│   │   ├── call/            # Phone call interception
│   │   ├── mms/             # SMS interception
│   │   ├── tunvpn/          # VPN tunnel core
│   │   └── ckms/            # Token/key management
│   └── mirror/              # Reflection mirrors for Android framework (225 files)
├── aidl/                    # 86 AIDL files — IPC interfaces
├── jni/                     # 96 native files (C/C++)
│   ├── Foundation/          # Syscall wrappers
│   ├── Jni/                 # JNI bridge Java↔Native
│   ├── Substrate/           # Hook substrate (function hooking)
│   ├── transparentED/       # Encryption/decryption
│   ├── safekey/             # XDJA safekey native
│   └── utils/               # Native utilities
└── jniLibs/                 # Pre-built .so files (armeabi, armeabi-v7a)
```

## WHERE TO LOOK

| Task | Location |
|------|----------|
| Start app inside VA | `client/core/VClientImpl.java` |
| Intercept framework API | `client/hook/proxies/` — pick service subdirectory |
| Add new hook proxy | `client/hook/proxies/<service>/` — extend base proxy class |
| Fix virtual service bug | `server/<service>/` — e.g. `server/am/` for Activity |
| Add AIDL interface | `aidl/` + implement in matching `server/` package |
| Fix storage paths | `os/VEnvironment.java` + `helper/PersistenceLayer*.java` |
| Add Android API reflection | `mirror/android/<api-package>/` — use RefXxx pattern |
| Fix native hooking | `jni/Substrate/`, `jni/Foundation/` |
| XDJA safekey/VPN | `com/xdja/zs/`, `com/xdja/tunvpn/` |

## CONVENTIONS

- **Client-Server IPC**: Client hooks delegate to server via AIDL. Client runs in virtual app process; server runs in host.
- **Hook proxies**: Each system service has a proxy directory under `client/hook/proxies/`. Follow existing pattern.
- **`mirror/` reflection**: Uses `RefClass`, `RefMethod`, `RefObject`, `RefStaticMethod` — never raw `java.lang.reflect`.
- **`V` prefix**: Virtual services (VAMS, VPMS, etc.). `_FIX` suffix for patched files.
- **NDK Build**: Uses `Android.mk` (not CMake). ABI: arm64-v8a only.
- **Persistence**: `PersistenceLayer` uses file-based storage with atomic writes (see `_FIX` version).

## ANTI-PATTERNS

- **DO NOT** modify `mirror/` wrappers without understanding `RefXxx` pattern — they're auto-mapping to hidden APIs.
- **DO NOT** add new hooking capabilities without feature-flag gating.
- **DO NOT** remove `jcenter()` — XDJA dependencies may need it.
- **DO NOT** use `as any` or suppress type warnings.
- **DO NOT** touch `_FIX.java` files without reading `STORAGE_FIX_README.md`.
