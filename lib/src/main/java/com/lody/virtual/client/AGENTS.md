# CLIENT — Hook Engine & Process Bootstrap

Runs inside the virtual app process. Intercepts Android framework API calls and delegates to server via IPC.

## STRUCTURE

```
client/
├── core/           # VClientImpl, VirtualCore — engine bootstrap
├── hook/           # 121 files — framework API interception
│   ├── base/       # Base proxy classes (MethodProxy, ReplaceCallingPkgHook, etc.)
│   ├── proxies/    # 82 files — per-service hook proxies
│   │   ├── am/           # ActivityManager hooks
│   │   ├── pm/           # PackageManager hooks
│   │   ├── window/       # WindowManager hooks
│   │   ├── telephony/    # TelephonyManager hooks
│   │   ├── location/     # LocationManager hooks
│   │   ├── wifi/         # WifiManager hooks
│   │   ├── network/      # ConnectivityManager hooks
│   │   ├── notification/ # NotificationManager hooks
│   │   └── ...           # ~30 more service proxy dirs
│   ├── delegate/   # Hook delegation
│   ├── secondary/  # Secondary process hooks
│   └── utils/      # Hook utilities
├── ipc/            # IPC wrappers (13 files) for system services
├── stub/           # Stub activities/services declared in manifest (31 files)
├── env/            # Virtual environment configuration (6 files)
├── fixer/          # Compatibility fixers for edge cases (3 files)
├── badger/         # Notification badge support (4 files)
├── service/        # Client-side service implementations
├── natives/        # Native method declarations
└── interfaces/     # Client callback interfaces
```

## WHERE TO LOOK

| Task | Location |
|------|----------|
| Understand bootstrap | `core/VClientImpl.java` — starts virtual app, calls `beforeStartApplication()` |
| Hook new API method | `hook/proxies/<service>/` — find service dir, add method proxy |
| Fix IPC call | `ipc/` — wrapper classes delegate to server via AIDL |
| Fix stub resolution | `stub/` — manifest-declared stubs for intents |
| Fix env issues | `env/` — SpecialEnvironment, SpecialDeviceStorage |

## CONVENTIONS

- **MethodProxy pattern**: Each hooked method is a class extending base proxy. Override `call()` or `replace()` etc.
- **Hook registration**: Proxies registered in `base/` via annotation or manual list. Follow existing service dirs.
- **IPC flow**: Client hook → IPC wrapper → AIDL → Server implementation → return.
- **`beforeStartApplication()`**: Main hook injection point in `VClientImpl`. New hooks wire here.

## ANTI-PATTERNS

- **DO NOT** add hooks without feature-flag gating when they affect specific apps.
- **DO NOT** bypass IPC — always delegate to server for state changes.
- **DO NOT** use raw reflection — use `mirror/` package.
