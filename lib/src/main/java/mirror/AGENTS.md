# MIRROR — Android Framework Reflection Wrappers

Type-safe reflection wrappers for hidden/internal Android framework APIs. NOT raw `java.lang.reflect` — uses custom `RefXxx` abstraction.

## STRUCTURE

```
mirror/
├── RefClass.java, RefMethod.java, RefObject.java, ...   # Core reflection primitives
├── android/                # android.* API mirrors (193 files)
│   ├── app/                # 61 files — Activity, Service, Application, Instrumentation, etc.
│   ├── content/            # 45 files — ContentResolver, PM, etc.
│   ├── os/                 # 20 files — Bundle, Process, UserHandle, etc.
│   ├── pm/                 # 19 files — PackageInfo, ApplicationInfo, etc.
│   ├── view/               # 12 files — Window, Display, etc.
│   ├── telephony/          # 8 files — TelephonyManager, SmsManager
│   ├── location/           # 7 files — LocationManager, Location
│   ├── net/                # 7 files — ConnectivityManager, NetworkInfo
│   ├── hardware/           # 6 files — Sensor, Camera, USB
│   └── ...                 # ~20 more sub-packages
├── com/android/internal/   # 22 files — internal APIs (telephony, policy, os)
├── dalvik/system/          # 1 file — DexFile
├── java/lang/              # 2 files — reflect hacks
├── libcore/io/             # 3 files — I/O utils
└── oem/                    # 4 files — OEM-specific workarounds
```

## WHERE TO LOOK

| Task | Location |
|------|----------|
| Access hidden Activity API | `android/app/` — look for target class wrapper |
| Access hidden PM API | `android/content/pm/` or `android/pm/` |
| Access hidden telephony | `android/telephony/` |
| Add new framework mirror | Create file matching class path, use `RefClass.create()` pattern |

## CONVENTIONS

- **RefClass pattern**: `RefClass.create(ClassMirror.class, TargetClass.class)` maps annotated fields to target methods/fields.
- **Annotations**: `@MethodParams`, `@MethodReflectParams` declare parameter types for method lookup.
- **Field types**: `RefMethod<T>`, `RefStaticMethod<T>`, `RefObject<T>`, `RefInt`, `RefStaticInt`, `RefBoolean`, etc.
- **One file per framework class**: Mirror class name matches target class name.
- **NEVER use raw `java.lang.reflect`** in this project — always add to `mirror/` instead.

## ANTI-PATTERNS

- **DO NOT** use `Class.getDeclaredMethod()` or `Field.setAccessible()` outside this package.
- **DO NOT** modify existing mirrors without checking all call sites — they map to specific Android API levels.
- **DO NOT** add mirrors for public SDK APIs — only hidden/internal ones need wrapping.
