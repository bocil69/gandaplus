# SERVER — Virtual System Services

Runs inside the host process. Manages virtual app lifecycle, package state, accounts, notifications, and content sync. Implements AIDL interfaces consumed by client.

## STRUCTURE

```
server/
├── am/             # VActivityManagerService (15 files) — activity stack, intent resolution
│   ├── ActivityStack.java          # Virtual activity back stack
│   ├── AttributeCache.java         # Attribute caching
│   └── ...                         # Activity record, task management
├── pm/             # VPackageManagerService (27 files) — package install, uninstall, query
│   ├── installer/                  # VPackageInstallerService, PackageInstallerSession
│   └── ...                         # Package parsing, permission management
├── accounts/       # VAccountManagerService (5 files) — account add/remove/auth
├── notification/   # VNotificationManagerService (9 files) — notification proxy + fixer
│   ├── RemoteViewsFixer.java       # RemoteViews compatibility
│   └── NotificationFixer.java      # Notification channel/targeting fixes
├── content/        # SyncManager + SyncStorageEngine (8 files) — content provider sync
├── device/         # Device info management (2 files)
├── location/       # Virtual location service (1 file)
├── memory/         # Memory management (5 files)
├── vs/             # VS service (3 files)
├── bit64/          # 64-bit support (1 file)
├── secondary/      # Secondary process handling (2 files)
└── job/            # Job scheduler service (1 file)
```

## WHERE TO LOOK

| Task | Location |
|------|----------|
| Fix activity launch/resolution | `am/VActivityManagerService.java`, `am/ActivityStack.java` |
| Fix package install/query | `pm/VPackageManagerService.java` |
| Fix app uninstall | `pm/` — uninstall flow |
| Fix notification delivery | `notification/VNotificationManagerService.java` |
| Fix account sync | `accounts/VAccountManagerService.java`, `content/SyncManager.java` |
| Add new virtual service | Create dir + implement AIDL interface from `aidl/` |

## CONVENTIONS

- **Service pattern**: Each service is a singleton. Init in `VActivityManagerService.systemReady()` or similar.
- **AIDL implementation**: Server implements AIDL interface from `lib/src/main/aidl/`. Registered via `ServiceCache`.
- **Thread safety**: Services accessed from multiple processes. Use synchronized or concurrent collections.
- **Chinese comments**: Server-side code has many Chinese (中文) comments/TODOs — don't remove them.

## ANTI-PATTERNS

- **DO NOT** directly modify system state — go through virtual service layer.
- **DO NOT** skip thread safety — multiple virtual app processes access these concurrently.
- **DO NOT** ignore the `TODO`/`FIXME` comments — many are known issues.
