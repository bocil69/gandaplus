package com.stericson.RootShell;

import android.util.Log;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootShell.execution.Shell;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeoutException;
/* loaded from: classes6.dex */
public class RootShell {
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$com$stericson$RootShell$RootShell$LogLevel = null;
    public static boolean debugMode = false;
    public static int defaultCommandTimeout = 20000;
    public static boolean handlerEnabled = true;
    public static final String version = "RootShell v1.6";

    /* loaded from: classes6.dex */
    public enum LogLevel {
        VERBOSE,
        ERROR,
        DEBUG,
        WARN;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static LogLevel[] valuesCustom() {
            LogLevel[] valuesCustom = values();
            int length = valuesCustom.length;
            LogLevel[] logLevelArr = new LogLevel[length];
            System.arraycopy(valuesCustom, 0, logLevelArr, 0, length);
            return logLevelArr;
        }
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$stericson$RootShell$RootShell$LogLevel() {
        int[] iArr = $SWITCH_TABLE$com$stericson$RootShell$RootShell$LogLevel;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[LogLevel.valuesCustom().length];
        try {
            iArr2[LogLevel.DEBUG.ordinal()] = 3;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[LogLevel.ERROR.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[LogLevel.VERBOSE.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[LogLevel.WARN.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        $SWITCH_TABLE$com$stericson$RootShell$RootShell$LogLevel = iArr2;
        return iArr2;
    }

    public static void closeAllShells() throws IOException {
        Shell.closeAll();
    }

    public static void closeCustomShell() throws IOException {
        Shell.closeCustomShell();
    }

    public static void closeShell(boolean z) throws IOException {
        if (z) {
            Shell.closeRootShell();
        } else {
            Shell.closeShell();
        }
    }

    public static boolean exists(String str) {
        return exists(str, false);
    }

    public static boolean exists(String str, boolean z) {
        final ArrayList<String> arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder("ls ");
        sb.append(z ? "-d " : " ");
        String sb2 = sb.toString();
        Command command = new Command(0, false, new String[]{String.valueOf(sb2) + str}) { // from class: com.stericson.RootShell.RootShell.1
            @Override // com.stericson.RootShell.execution.Command
            public void commandOutput(int i, String str2) {
                RootShell.log(str2);
                arrayList.add(str2);
                super.commandOutput(i, str2);
            }
        };
        try {
            getShell(false).add(command);
            commandWait(getShell(false), command);
            for (String str2 : arrayList) {
                if (str2.trim().equals(str)) {
                    return true;
                }
            }
            arrayList.clear();
            Command command2 = new Command(0, false, new String[]{String.valueOf(sb2) + str}) { // from class: com.stericson.RootShell.RootShell.2
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i, String str3) {
                    RootShell.log(str3);
                    arrayList.add(str3);
                    super.commandOutput(i, str3);
                }
            };
            try {
                getShell(true).add(command2);
                commandWait(getShell(true), command2);
                ArrayList<String> arrayList2 = new ArrayList();
                arrayList2.addAll(arrayList);
                for (String str3 : arrayList2) {
                    if (str3.trim().equals(str)) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
                log("Exception: " + e);
                return false;
            }
        } catch (Exception e2) {
            log("Exception: " + e2);
            return false;
        }
    }

    public static List<String> findBinary(String str, boolean z) {
        return findBinary(str, null, z);
    }

    public static List<String> findBinary(final String str, List<String> list, boolean z) {
        final ArrayList arrayList = new ArrayList();
        if (list == null) {
            list = getPath();
        }
        log("Checking for " + str);
        boolean z2 = false;
        try {
            for (String str2 : list) {
                if (!str2.endsWith("/")) {
                    str2 = String.valueOf(str2) + "/";
                }
                final String str3 = str2;
                commandWait(getShell(false), getShell(false).add(new Command(0, false, new String[]{"stat " + str3 + str}) { // from class: com.stericson.RootShell.RootShell.3
                    @Override // com.stericson.RootShell.execution.Command
                    public void commandOutput(int i, String str4) {
                        if (str4.contains("File: ") && str4.contains(str)) {
                            arrayList.add(str3);
                            RootShell.log(String.valueOf(str) + " was found here: " + str3);
                        }
                        RootShell.log(str4);
                        super.commandOutput(i, str4);
                    }
                }));
                if (arrayList.size() > 0 && z) {
                    break;
                }
            }
            z2 = !arrayList.isEmpty();
        } catch (Exception unused) {
            log(String.valueOf(str) + " was not found, more information MAY be available with Debugging on.");
        }
        if (!z2) {
            log("Trying second method");
            for (String str4 : list) {
                if (!str4.endsWith("/")) {
                    str4 = String.valueOf(str4) + "/";
                }
                if (exists(String.valueOf(str4) + str)) {
                    log(String.valueOf(str) + " was found here: " + str4);
                    arrayList.add(str4);
                    if (arrayList.size() > 0 && z) {
                        break;
                    }
                } else {
                    log(String.valueOf(str) + " was NOT found here: " + str4);
                }
            }
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static Shell getCustomShell(String str, int i) throws IOException, TimeoutException, RootDeniedException {
        return getCustomShell(str, i);
    }

    public static List<String> getPath() {
        return Arrays.asList(System.getenv("PATH").split(":"));
    }

    public static Shell getShell(boolean z, int i, Shell.ShellContext shellContext, int i2) throws IOException, TimeoutException, RootDeniedException {
        if (z) {
            return Shell.startRootShell(i, shellContext, i2);
        }
        return Shell.startShell(i);
    }

    public static Shell getShell(boolean z, int i, Shell.ShellContext shellContext) throws IOException, TimeoutException, RootDeniedException {
        return getShell(z, i, shellContext, 3);
    }

    public static Shell getShell(boolean z, Shell.ShellContext shellContext) throws IOException, TimeoutException, RootDeniedException {
        return getShell(z, 0, shellContext, 3);
    }

    public static Shell getShell(boolean z, int i) throws IOException, TimeoutException, RootDeniedException {
        return getShell(z, i, Shell.defaultContext, 3);
    }

    public static Shell getShell(boolean z) throws IOException, TimeoutException, RootDeniedException {
        return getShell(z, 0);
    }

    public static boolean isAccessGiven() {
        return isAccessGiven(0, 3);
    }

    public static boolean isAccessGiven(int i, int i2) {
        final HashSet<String> hashSet = new HashSet();
        try {
            log("Checking for Root access");
            Command command = new Command(158, false, new String[]{"id"}) { // from class: com.stericson.RootShell.RootShell.4
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i3, String str) {
                    if (i3 == 158) {
                        hashSet.addAll(Arrays.asList(str.split(" ")));
                    }
                    super.commandOutput(i3, str);
                }
            };
            Shell startRootShell = Shell.startRootShell(i, i2);
            startRootShell.add(command);
            commandWait(startRootShell, command);
            for (String str : hashSet) {
                log(str);
                if (str.toLowerCase().contains("uid=0")) {
                    log("Access Given");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isBusyboxAvailable() {
        return isBusyboxAvailable(false);
    }

    public static boolean isBusyboxAvailable(boolean z) {
        return z ? findBinary("busybox", true).size() > 0 || findBinary("toybox", true).size() > 0 : findBinary("busybox", true).size() > 0;
    }

    public static boolean isRootAvailable() {
        return findBinary("su", true).size() > 0;
    }

    public static void log(String str) {
        log(null, str, LogLevel.DEBUG, null);
    }

    public static void log(String str, String str2) {
        log(str, str2, LogLevel.DEBUG, null);
    }

    public static void log(String str, LogLevel logLevel, Exception exc) {
        log(null, str, logLevel, exc);
    }

    public static boolean islog() {
        return debugMode;
    }

    public static void log(String str, String str2, LogLevel logLevel, Exception exc) {
        if (str2 == null || str2.equals("") || !debugMode) {
            return;
        }
        if (str == null) {
            str = version;
        }
        int i = $SWITCH_TABLE$com$stericson$RootShell$RootShell$LogLevel()[logLevel.ordinal()];
        if (i == 1) {
            Log.v(str, str2);
        } else if (i == 2) {
            Log.e(str, str2, exc);
        } else if (i == 3) {
            Log.d(str, str2);
        } else if (i != 4) {
        } else {
            Log.w(str, str2);
        }
    }

    private static void commandWait(Shell shell, Command command) throws Exception {
        while (!command.isFinished()) {
            log(version, shell.getCommandQueuePositionString(command));
            log(version, "Processed " + command.totalOutputProcessed + " of " + command.totalOutput + " output from command.");
            synchronized (command) {
                try {
                    if (!command.isFinished()) {
                        command.wait(2000L);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!command.isExecuting() && !command.isFinished()) {
                if (!shell.isExecuting && !shell.isReading) {
                    log(version, "Waiting for a command to be executed in a shell that is not executing and not reading! \n\n Command: " + command.getCommand());
                    Exception exc = new Exception();
                    exc.setStackTrace(Thread.currentThread().getStackTrace());
                    exc.printStackTrace();
                } else if (shell.isExecuting && !shell.isReading) {
                    log(version, "Waiting for a command to be executed in a shell that is executing but not reading! \n\n Command: " + command.getCommand());
                    Exception exc2 = new Exception();
                    exc2.setStackTrace(Thread.currentThread().getStackTrace());
                    exc2.printStackTrace();
                } else {
                    log(version, "Waiting for a command to be executed in a shell that is not reading! \n\n Command: " + command.getCommand());
                    Exception exc3 = new Exception();
                    exc3.setStackTrace(Thread.currentThread().getStackTrace());
                    exc3.printStackTrace();
                }
            }
        }
    }
}
