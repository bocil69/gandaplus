package com.stericson.RootTools.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import com.stericson.RootShell.RootShell;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootShell.execution.Shell;
import com.stericson.RootTools.Constants;
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.containers.Mount;
import com.stericson.RootTools.containers.Permissions;
import com.stericson.RootTools.containers.Symlink;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
/* loaded from: classes6.dex */
public final class RootToolsInternalMethods {
    protected RootToolsInternalMethods() {
    }

    public static void getInstance() {
        RootTools.setRim(new RootToolsInternalMethods());
    }

    public Permissions getPermissions(String str) {
        String str2 = str.split(" ")[0];
        if (str2.length() == 10) {
            if (str2.charAt(0) == '-' || str2.charAt(0) == 'd' || str2.charAt(0) == 'l') {
                if (str2.charAt(1) == '-' || str2.charAt(1) == 'r') {
                    if (str2.charAt(2) == '-' || str2.charAt(2) == 'w') {
                        RootTools.log(str2);
                        Permissions permissions = new Permissions();
                        permissions.setType(str2.substring(0, 1));
                        RootTools.log(permissions.getType());
                        permissions.setUserPermissions(str2.substring(1, 4));
                        RootTools.log(permissions.getUserPermissions());
                        permissions.setGroupPermissions(str2.substring(4, 7));
                        RootTools.log(permissions.getGroupPermissions());
                        permissions.setOtherPermissions(str2.substring(7, 10));
                        RootTools.log(permissions.getOtherPermissions());
                        StringBuilder sb = new StringBuilder();
                        sb.append(parseSpecialPermissions(str2));
                        sb.append(parsePermissions(permissions.getUserPermissions()));
                        sb.append(parsePermissions(permissions.getGroupPermissions()));
                        sb.append(parsePermissions(permissions.getOtherPermissions()));
                        permissions.setPermissions(Integer.parseInt(sb.toString()));
                        return permissions;
                    }
                    return null;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public int parsePermissions(String str) {
        String lowerCase = str.toLowerCase(Locale.US);
        int i = lowerCase.charAt(0) == 'r' ? 4 : 0;
        RootTools.log("permission " + i);
        RootTools.log("character " + lowerCase.charAt(0));
        int i2 = lowerCase.charAt(1) == 'w' ? i + 2 : i + 0;
        RootTools.log("permission " + i2);
        RootTools.log("character " + lowerCase.charAt(1));
        int i3 = (lowerCase.charAt(2) == 'x' || lowerCase.charAt(2) == 's' || lowerCase.charAt(2) == 't') ? i2 + 1 : i2 + 0;
        RootTools.log("permission " + i3);
        RootTools.log("character " + lowerCase.charAt(2));
        return i3;
    }

    public int parseSpecialPermissions(String str) {
        int i = str.charAt(2) == 's' ? 4 : 0;
        if (str.charAt(5) == 's') {
            i += 2;
        }
        if (str.charAt(8) == 't') {
            i++;
        }
        RootTools.log("special permissions " + i);
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0050, code lost:
        if (r13.getExitCode() == 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0082, code lost:
        if (r13.getExitCode() == 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0084, code lost:
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0086, code lost:
        r10 = false;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean copyFile(java.lang.String r10, java.lang.String r11, boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stericson.RootTools.internal.RootToolsInternalMethods.copyFile(java.lang.String, java.lang.String, boolean, boolean):boolean");
    }

    public boolean checkUtil(String str) {
        String num;
        List<String> findBinary = RootShell.findBinary(str, true);
        if (findBinary.size() > 0) {
            for (String str2 : findBinary) {
                Permissions filePermissionsSymlinks = RootTools.getFilePermissionsSymlinks(String.valueOf(str2) + "/" + str);
                if (filePermissionsSymlinks != null) {
                    if (Integer.toString(filePermissionsSymlinks.getPermissions()).length() > 3) {
                        num = Integer.toString(filePermissionsSymlinks.getPermissions()).substring(1);
                    } else {
                        num = Integer.toString(filePermissionsSymlinks.getPermissions());
                    }
                    if (num.equals("755") || num.equals("777") || num.equals("775")) {
                        RootTools.utilPath = String.valueOf(str2) + "/" + str;
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0092 A[Catch: Exception -> 0x000d, TRY_LEAVE, TryCatch #0 {Exception -> 0x000d, blocks: (B:4:0x0007, B:7:0x0010, B:10:0x001b, B:12:0x004b, B:22:0x0092, B:14:0x0050, B:16:0x0056, B:18:0x005c, B:20:0x008c), top: B:26:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean deleteFileOrDirectory(java.lang.String r8, boolean r9) {
        /*
            r7 = this;
            java.lang.String r0 = "busybox"
            java.lang.String r1 = "rm"
            r2 = 0
            if (r9 == 0) goto L10
            java.lang.String r3 = "RW"
            com.stericson.RootTools.RootTools.remount(r8, r3)     // Catch: java.lang.Exception -> Ld
            goto L10
        Ld:
            r8 = move-exception
            goto L99
        L10:
            java.lang.String r3 = "toolbox"
            boolean r3 = r7.hasUtil(r1, r3)     // Catch: java.lang.Exception -> Ld
            java.lang.String r4 = "target not exist or unable to delete file"
            r5 = 1
            if (r3 == 0) goto L50
            java.lang.String r0 = "rm command is available!"
            com.stericson.RootTools.RootTools.log(r0)     // Catch: java.lang.Exception -> Ld
            com.stericson.RootShell.execution.Command r0 = new com.stericson.RootShell.execution.Command     // Catch: java.lang.Exception -> Ld
            java.lang.String[] r1 = new java.lang.String[r5]     // Catch: java.lang.Exception -> Ld
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld
            java.lang.String r6 = "rm -r "
            r3.<init>(r6)     // Catch: java.lang.Exception -> Ld
            r3.append(r8)     // Catch: java.lang.Exception -> Ld
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> Ld
            r1[r2] = r3     // Catch: java.lang.Exception -> Ld
            r0.<init>(r2, r2, r1)     // Catch: java.lang.Exception -> Ld
            com.stericson.RootShell.execution.Shell r1 = com.stericson.RootShell.execution.Shell.startRootShell()     // Catch: java.lang.Exception -> Ld
            r1.add(r0)     // Catch: java.lang.Exception -> Ld
            com.stericson.RootShell.execution.Shell r1 = com.stericson.RootShell.execution.Shell.startRootShell()     // Catch: java.lang.Exception -> Ld
            r7.commandWait(r1, r0)     // Catch: java.lang.Exception -> Ld
            int r0 = r0.getExitCode()     // Catch: java.lang.Exception -> Ld
            if (r0 == 0) goto L90
            com.stericson.RootTools.RootTools.log(r4)     // Catch: java.lang.Exception -> Ld
        L4e:
            r5 = 0
            goto L90
        L50:
            boolean r3 = r7.checkUtil(r0)     // Catch: java.lang.Exception -> Ld
            if (r3 == 0) goto L90
            boolean r0 = r7.hasUtil(r1, r0)     // Catch: java.lang.Exception -> Ld
            if (r0 == 0) goto L90
            java.lang.String r0 = "busybox rm command is available!"
            com.stericson.RootTools.RootTools.log(r0)     // Catch: java.lang.Exception -> Ld
            com.stericson.RootShell.execution.Command r0 = new com.stericson.RootShell.execution.Command     // Catch: java.lang.Exception -> Ld
            java.lang.String[] r1 = new java.lang.String[r5]     // Catch: java.lang.Exception -> Ld
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld
            java.lang.String r6 = "busybox rm -rf "
            r3.<init>(r6)     // Catch: java.lang.Exception -> Ld
            r3.append(r8)     // Catch: java.lang.Exception -> Ld
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> Ld
            r1[r2] = r3     // Catch: java.lang.Exception -> Ld
            r0.<init>(r2, r2, r1)     // Catch: java.lang.Exception -> Ld
            com.stericson.RootShell.execution.Shell r1 = com.stericson.RootShell.execution.Shell.startRootShell()     // Catch: java.lang.Exception -> Ld
            r1.add(r0)     // Catch: java.lang.Exception -> Ld
            com.stericson.RootShell.execution.Shell r1 = com.stericson.RootShell.execution.Shell.startRootShell()     // Catch: java.lang.Exception -> Ld
            r7.commandWait(r1, r0)     // Catch: java.lang.Exception -> Ld
            int r0 = r0.getExitCode()     // Catch: java.lang.Exception -> Ld
            if (r0 == 0) goto L90
            com.stericson.RootTools.RootTools.log(r4)     // Catch: java.lang.Exception -> Ld
            goto L4e
        L90:
            if (r9 == 0) goto L97
            java.lang.String r9 = "RO"
            com.stericson.RootTools.RootTools.remount(r8, r9)     // Catch: java.lang.Exception -> Ld
        L97:
            r2 = r5
            goto L9c
        L99:
            r8.printStackTrace()
        L9c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stericson.RootTools.internal.RootToolsInternalMethods.deleteFileOrDirectory(java.lang.String, boolean):boolean");
    }

    public void fixUtil(String str, String str2) {
        try {
            RootTools.remount("/system", "rw");
            List<String> findBinary = RootShell.findBinary(str, true);
            if (findBinary.size() > 0) {
                Iterator<String> it = findBinary.iterator();
                while (it.hasNext()) {
                    Command command = new Command(0, false, String.valueOf(str2) + " rm " + it.next() + "/" + str);
                    RootShell.getShell(true).add(command);
                    commandWait(RootShell.getShell(true), command);
                }
                Command command2 = new Command(0, false, String.valueOf(str2) + " ln -s " + str2 + " /system/bin/" + str, String.valueOf(str2) + " chmod 0755 /system/bin/" + str);
                RootShell.getShell(true).add(command2);
                commandWait(RootShell.getShell(true), command2);
            }
            RootTools.remount("/system", "ro");
        } catch (Exception unused) {
        }
    }

    public boolean fixUtils(String[] strArr) throws Exception {
        for (String str : strArr) {
            if (!checkUtil(str)) {
                if (checkUtil("busybox")) {
                    if (hasUtil(str, "busybox")) {
                        fixUtil(str, RootTools.utilPath);
                    }
                } else if (!checkUtil("toolbox")) {
                    return false;
                } else {
                    if (hasUtil(str, "toolbox")) {
                        fixUtil(str, RootTools.utilPath);
                    }
                }
            }
        }
        return true;
    }

    public List<String> getBusyBoxApplets(String str) throws Exception {
        if (str != null && !str.endsWith("/") && !str.equals("")) {
            str = String.valueOf(str) + "/";
        } else if (str == null) {
            throw new Exception("Path is null, please specifiy a path");
        }
        final ArrayList arrayList = new ArrayList();
        Command command = new Command(3, false, new String[]{String.valueOf(str) + "busybox --list"}) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.1
            @Override // com.stericson.RootShell.execution.Command
            public void commandOutput(int i, String str2) {
                if (i == 3 && !str2.trim().equals("") && !str2.trim().contains("not found") && !str2.trim().contains("file busy")) {
                    arrayList.add(str2);
                }
                super.commandOutput(i, str2);
            }
        };
        RootShell.getShell(false).add(command);
        commandWait(RootShell.getShell(false), command);
        if (arrayList.size() <= 0) {
            Command command2 = new Command(3, false, new String[]{String.valueOf(str) + "busybox --list"}) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.2
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i, String str2) {
                    if (i == 3 && !str2.trim().equals("") && !str2.trim().contains("not found") && !str2.trim().contains("file busy")) {
                        arrayList.add(str2);
                    }
                    super.commandOutput(i, str2);
                }
            };
            RootShell.getShell(true).add(command2);
            commandWait(RootShell.getShell(true), command2);
        }
        return arrayList;
    }

    public String getBusyBoxVersion(String str) {
        final StringBuilder sb = new StringBuilder();
        if (!str.equals("") && !str.endsWith("/")) {
            str = String.valueOf(str) + "/";
        }
        try {
            Command command = new Command(4, false, new String[]{String.valueOf(str) + "busybox"}) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.3
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i, String str2) {
                    String trim = str2.trim();
                    if (i == 4) {
                        RootTools.log("Version Output: " + trim);
                        String[] split = trim.split(" ");
                        if (split.length > 1 && split[1].contains("v1.")) {
                            sb.append(split[1]);
                            RootTools.log("Found Version: " + sb.toString());
                        }
                    }
                    super.commandOutput(i, trim);
                }
            };
            RootTools.log("Getting BusyBox Version without root");
            Shell shell = RootTools.getShell(false);
            shell.add(command);
            commandWait(shell, command);
            if (sb.length() <= 0) {
                Command command2 = new Command(4, false, new String[]{String.valueOf(str) + "busybox"}) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.4
                    @Override // com.stericson.RootShell.execution.Command
                    public void commandOutput(int i, String str2) {
                        String trim = str2.trim();
                        if (i == 4) {
                            RootTools.log("Version Output: " + trim);
                            String[] split = trim.split(" ");
                            if (split.length > 1 && split[1].contains("v1.")) {
                                sb.append(split[1]);
                                RootTools.log("Found Version: " + sb.toString());
                            }
                        }
                        super.commandOutput(i, trim);
                    }
                };
                RootTools.log("Getting BusyBox Version with root");
                Shell shell2 = RootTools.getShell(true);
                shell2.add(command2);
                commandWait(shell2, command2);
            }
            RootTools.log("Returning found version: " + sb.toString());
            return sb.toString();
        } catch (Exception unused) {
            RootTools.log("BusyBox was not found, more information MAY be available with Debugging on.");
            return "";
        }
    }

    public long getConvertedSpace(String str) {
        double d = 1.0d;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (true) {
                if (i >= str.length()) {
                    break;
                }
                char charAt = str.charAt(i);
                if (Character.isDigit(charAt) || charAt == '.') {
                    stringBuffer.append(str.charAt(i));
                    i++;
                } else {
                    if (charAt != 'm' && charAt != 'M') {
                        if (charAt == 'g' || charAt == 'G') {
                            d = 1048576.0d;
                        }
                    }
                    d = 1024.0d;
                }
            }
            return (long) Math.ceil(Double.valueOf(stringBuffer.toString()).doubleValue() * d);
        } catch (Exception unused) {
            return -1L;
        }
    }

    public String getInode(String str) {
        try {
            Command command = new Command(5, false, "/data/local/ls -i " + str) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.5
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i, String str2) {
                    if (i == 5 && !str2.trim().equals("") && Character.isDigit(str2.trim().substring(0, 1).toCharArray()[0])) {
                        InternalVariables.inode = str2.trim().split(" ")[0];
                    }
                    super.commandOutput(i, str2);
                }
            };
            Shell.startRootShell().add(command);
            commandWait(Shell.startRootShell(), command);
            return InternalVariables.inode;
        } catch (Exception unused) {
            return "";
        }
    }

    public boolean isNativeToolsReady(int i, Context context) {
        RootTools.log("Preparing Native Tools");
        InternalVariables.nativeToolsReady = false;
        try {
            Installer installer = new Installer(context);
            if (installer.isBinaryInstalled("nativetools")) {
                InternalVariables.nativeToolsReady = true;
            } else {
                InternalVariables.nativeToolsReady = installer.installBinary(i, "nativetools", "700");
            }
            return InternalVariables.nativeToolsReady;
        } catch (IOException e) {
            if (RootTools.debugMode) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public Permissions getFilePermissionsSymlinks(String str) {
        RootTools.log("Checking permissions for " + str);
        if (RootTools.exists(str)) {
            RootTools.log(String.valueOf(str) + " was found.");
            try {
                Command command = new Command(1, false, "ls -l " + str, "busybox ls -l " + str, "/system/bin/failsafe/toolbox ls -l " + str, "toolbox ls -l " + str) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.6
                    /* JADX WARN: Removed duplicated region for block: B:16:0x0052 A[Catch: Exception -> 0x0058, TRY_LEAVE, TryCatch #0 {Exception -> 0x0058, blocks: (B:14:0x0046, B:16:0x0052), top: B:23:0x0046 }] */
                    @Override // com.stericson.RootShell.execution.Command
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void commandOutput(int r5, java.lang.String r6) {
                        /*
                            r4 = this;
                            r0 = 1
                            if (r5 != r0) goto L60
                            java.lang.String r1 = " "
                            java.lang.String[] r2 = r6.split(r1)
                            r3 = 0
                            r2 = r2[r3]
                            int r2 = r2.length()
                            r3 = 10
                            if (r2 == r3) goto L18
                            super.commandOutput(r5, r6)
                            return
                        L18:
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            java.lang.String r3 = "Line "
                            r2.<init>(r3)
                            r2.append(r6)
                            java.lang.String r2 = r2.toString()
                            com.stericson.RootTools.RootTools.log(r2)
                            java.lang.String[] r1 = r6.split(r1)     // Catch: java.lang.Exception -> L44
                            int r2 = r1.length     // Catch: java.lang.Exception -> L44
                            int r2 = r2 + (-2)
                            r2 = r1[r2]     // Catch: java.lang.Exception -> L44
                            java.lang.String r3 = "->"
                            boolean r2 = r2.equals(r3)     // Catch: java.lang.Exception -> L44
                            if (r2 == 0) goto L44
                            java.lang.String r2 = "Symlink found."
                            com.stericson.RootTools.RootTools.log(r2)     // Catch: java.lang.Exception -> L44
                            int r2 = r1.length     // Catch: java.lang.Exception -> L44
                            int r2 = r2 - r0
                            r0 = r1[r2]     // Catch: java.lang.Exception -> L44
                            goto L46
                        L44:
                            java.lang.String r0 = ""
                        L46:
                            com.stericson.RootTools.internal.RootToolsInternalMethods r1 = com.stericson.RootTools.internal.RootToolsInternalMethods.this     // Catch: java.lang.Exception -> L58
                            com.stericson.RootTools.containers.Permissions r1 = r1.getPermissions(r6)     // Catch: java.lang.Exception -> L58
                            com.stericson.RootTools.internal.InternalVariables.permissions = r1     // Catch: java.lang.Exception -> L58
                            com.stericson.RootTools.containers.Permissions r1 = com.stericson.RootTools.internal.InternalVariables.permissions     // Catch: java.lang.Exception -> L58
                            if (r1 == 0) goto L60
                            com.stericson.RootTools.containers.Permissions r1 = com.stericson.RootTools.internal.InternalVariables.permissions     // Catch: java.lang.Exception -> L58
                            r1.setSymlink(r0)     // Catch: java.lang.Exception -> L58
                            goto L60
                        L58:
                            r0 = move-exception
                            java.lang.String r0 = r0.getMessage()
                            com.stericson.RootTools.RootTools.log(r0)
                        L60:
                            super.commandOutput(r5, r6)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.stericson.RootTools.internal.RootToolsInternalMethods.AnonymousClass6.commandOutput(int, java.lang.String):void");
                    }
                };
                RootShell.getShell(true).add(command);
                commandWait(RootShell.getShell(true), command);
                return InternalVariables.permissions;
            } catch (Exception e) {
                RootTools.log(e.getMessage());
            }
        }
        return null;
    }

    public ArrayList<Mount> getMounts() throws Exception {
        InternalVariables.mounts = new ArrayList<>();
        if (InternalVariables.mounts == null || InternalVariables.mounts.isEmpty()) {
            Shell shell = RootTools.getShell(true);
            Command command = new Command(8, false, "cat /proc/mounts") { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.7
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i, String str) {
                    if (i == 8) {
                        RootTools.log(str);
                        String[] split = str.split(" ");
                        InternalVariables.mounts.add(new Mount(new File(split[0]), new File(split[1]), split[2], split[3]));
                    }
                    super.commandOutput(i, str);
                }
            };
            shell.add(command);
            commandWait(shell, command);
        }
        return InternalVariables.mounts;
    }

    public String getMountedAs(String str) throws Exception {
        InternalVariables.mounts = getMounts();
        if (InternalVariables.mounts != null) {
            Iterator<Mount> it = InternalVariables.mounts.iterator();
            while (it.hasNext()) {
                Mount next = it.next();
                String absolutePath = next.getMountPoint().getAbsolutePath();
                if (absolutePath.equals("/")) {
                    if (str.equals("/")) {
                        return (String) next.getFlags().toArray()[0];
                    }
                } else {
                    if (!str.equals(absolutePath)) {
                        if (str.startsWith(String.valueOf(absolutePath) + "/")) {
                        }
                    }
                    RootTools.log((String) next.getFlags().toArray()[0]);
                    return (String) next.getFlags().toArray()[0];
                }
            }
            throw new Exception();
        }
        throw new Exception();
    }

    public long getSpace(String str) {
        String[] strArr;
        String[] strArr2;
        InternalVariables.getSpaceFor = str;
        RootTools.log("Looking for Space");
        try {
            Command command = new Command(6, false, "df " + str) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.8
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i, String str2) {
                    if (i == 6 && str2.contains(InternalVariables.getSpaceFor.trim())) {
                        InternalVariables.space = str2.split(" ");
                    }
                    super.commandOutput(i, str2);
                }
            };
            Shell.startRootShell().add(command);
            commandWait(Shell.startRootShell(), command);
        } catch (Exception unused) {
        }
        if (InternalVariables.space != null) {
            RootTools.log("First Method");
            boolean z = false;
            for (String str2 : InternalVariables.space) {
                RootTools.log(str2);
                if (z) {
                    return getConvertedSpace(str2);
                }
                if (str2.equals("used,")) {
                    z = true;
                }
            }
            RootTools.log("Second Method");
            int i = InternalVariables.space[0].length() <= 5 ? 2 : 3;
            int i2 = 0;
            for (String str3 : InternalVariables.space) {
                RootTools.log(str3);
                if (str3.length() > 0) {
                    RootTools.log(String.valueOf(str3) + "Valid");
                    if (i2 == i) {
                        return getConvertedSpace(str3);
                    }
                    i2++;
                }
            }
        }
        RootTools.log("Returning -1, space could not be determined.");
        return -1L;
    }

    public String getSymlink(String str) {
        RootTools.log("Looking for Symlink for " + str);
        try {
            final ArrayList arrayList = new ArrayList();
            Command command = new Command(7, false, new String[]{"ls -l " + str}) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.9
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i, String str2) {
                    if (i == 7 && !str2.trim().equals("")) {
                        arrayList.add(str2);
                    }
                    super.commandOutput(i, str2);
                }
            };
            Shell.startRootShell().add(command);
            commandWait(Shell.startRootShell(), command);
            String[] split = ((String) arrayList.get(0)).split(" ");
            if (split.length > 2 && split[split.length - 2].equals("->")) {
                RootTools.log("Symlink found.");
                if (!split[split.length - 1].equals("") && !split[split.length - 1].contains("/")) {
                    List<String> findBinary = RootShell.findBinary(split[split.length - 1], true);
                    if (findBinary.size() > 0) {
                        return String.valueOf(findBinary.get(0)) + split[split.length - 1];
                    }
                    return split[split.length - 1];
                }
                return split[split.length - 1];
            }
        } catch (Exception e) {
            if (RootTools.debugMode) {
                e.printStackTrace();
            }
        }
        RootTools.log("Symlink not found");
        return "";
    }

    public ArrayList<Symlink> getSymlinks(String str) throws Exception {
        if (!checkUtil("find")) {
            throw new Exception();
        }
        InternalVariables.symlinks = new ArrayList<>();
        Command command = new Command(0, false, "find " + str + " -type l -exec ls -l {} \\;") { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.10
            @Override // com.stericson.RootShell.execution.Command
            public void commandOutput(int i, String str2) {
                if (i == 9) {
                    RootTools.log(str2);
                    String[] split = str2.split(" ");
                    InternalVariables.symlinks.add(new Symlink(new File(split[split.length - 3]), new File(split[split.length - 1])));
                }
                super.commandOutput(i, str2);
            }
        };
        Shell.startRootShell().add(command);
        commandWait(Shell.startRootShell(), command);
        if (InternalVariables.symlinks != null) {
            return InternalVariables.symlinks;
        }
        throw new Exception();
    }

    public String getWorkingToolbox() {
        return RootTools.checkUtil("busybox") ? "busybox" : RootTools.checkUtil("toolbox") ? "toolbox" : "";
    }

    public boolean hasEnoughSpaceOnSdCard(long j) {
        long blockSizeLong;
        long availableBlocksLong;
        RootTools.log("Checking SDcard size and that it is mounted as RW");
        if (Environment.getExternalStorageState().equals("mounted")) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (Build.VERSION.SDK_INT < 18) {
                blockSizeLong = statFs.getBlockSize();
                availableBlocksLong = statFs.getAvailableBlocks();
            } else {
                blockSizeLong = statFs.getBlockSizeLong();
                availableBlocksLong = statFs.getAvailableBlocksLong();
            }
            return j < availableBlocksLong * blockSizeLong;
        }
        return false;
    }

    public boolean hasUtil(final String str, final String str2) {
        StringBuilder sb;
        InternalVariables.found = false;
        if (str2.endsWith("toolbox") || str2.endsWith("busybox")) {
            try {
                String[] strArr = new String[1];
                if (str2.endsWith("toolbox")) {
                    sb = new StringBuilder(String.valueOf(str2));
                    sb.append(" ");
                    sb.append(str);
                } else {
                    sb = new StringBuilder(String.valueOf(str2));
                    sb.append(" --list");
                }
                strArr[0] = sb.toString();
                Command command = new Command(0, false, strArr) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.11
                    @Override // com.stericson.RootShell.execution.Command
                    public void commandOutput(int i, String str3) {
                        if (str2.endsWith("toolbox")) {
                            if (!str3.contains("no such tool")) {
                                InternalVariables.found = true;
                            }
                        } else if (str2.endsWith("busybox") && str3.contains(str)) {
                            RootTools.log("Found util!");
                            InternalVariables.found = true;
                        }
                        super.commandOutput(i, str3);
                    }
                };
                RootTools.getShell(true).add(command);
                commandWait(RootTools.getShell(true), command);
                if (InternalVariables.found) {
                    RootTools.log("Box contains " + str + " util!");
                    return true;
                }
                RootTools.log("Box does not contain " + str + " util!");
                return false;
            } catch (Exception e) {
                RootTools.log(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean installBinary(Context context, int i, String str, String str2) {
        try {
            return new Installer(context).installBinary(i, str, str2);
        } catch (IOException e) {
            if (RootTools.debugMode) {
                e.printStackTrace();
                return false;
            }
            return false;
        }
    }

    public boolean isBinaryAvailable(Context context, String str) {
        try {
            return new Installer(context).isBinaryInstalled(str);
        } catch (IOException e) {
            if (RootTools.debugMode) {
                e.printStackTrace();
                return false;
            }
            return false;
        }
    }

    public boolean isAppletAvailable(String str, String str2) {
        try {
            for (String str3 : getBusyBoxApplets(str2)) {
                if (str3.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            RootTools.log(e.toString());
            return false;
        }
    }

    public boolean isProcessRunning(final String str) {
        RootTools.log("Checks if process is running: " + str);
        InternalVariables.processRunning = false;
        try {
            Command command = new Command(0, false, new String[]{"ps"}) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.12
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i, String str2) {
                    if (str2.contains(str)) {
                        InternalVariables.processRunning = true;
                    }
                    super.commandOutput(i, str2);
                }
            };
            RootTools.getShell(true).add(command);
            commandWait(RootTools.getShell(true), command);
        } catch (Exception e) {
            RootTools.log(e.getMessage());
        }
        return InternalVariables.processRunning;
    }

    public boolean killProcess(final String str) {
        RootTools.log("Killing process " + str);
        InternalVariables.pid_list = "";
        InternalVariables.processRunning = true;
        try {
            Command command = new Command(0, false, new String[]{"ps"}) { // from class: com.stericson.RootTools.internal.RootToolsInternalMethods.13
                @Override // com.stericson.RootShell.execution.Command
                public void commandOutput(int i, String str2) {
                    if (str2.contains(str)) {
                        Matcher matcher = InternalVariables.psPattern.matcher(str2);
                        try {
                            if (matcher.find()) {
                                String group = matcher.group(1);
                                InternalVariables.pid_list = String.valueOf(InternalVariables.pid_list) + " " + group;
                                InternalVariables.pid_list = InternalVariables.pid_list.trim();
                                RootTools.log("Found pid: " + group);
                            } else {
                                RootTools.log("Matching in ps command failed!");
                            }
                        } catch (Exception e) {
                            RootTools.log("Error with regex!");
                            e.printStackTrace();
                        }
                    }
                    super.commandOutput(i, str2);
                }
            };
            RootTools.getShell(true).add(command);
            commandWait(RootTools.getShell(true), command);
            String str2 = InternalVariables.pid_list;
            if (str2.equals("")) {
                return true;
            }
            try {
                Command command2 = new Command(0, false, "kill -9 " + str2);
                RootTools.getShell(true).add(command2);
                commandWait(RootTools.getShell(true), command2);
                return true;
            } catch (Exception e) {
                RootTools.log(e.getMessage());
                return false;
            }
        } catch (Exception e2) {
            RootTools.log(e2.getMessage());
        }
    }

    public void offerBusyBox(Activity activity) {
        RootTools.log("Launching Market for BusyBox");
        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=stericson.busybox")));
    }

    public Intent offerBusyBox(Activity activity, int i) {
        RootTools.log("Launching Market for BusyBox");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=stericson.busybox"));
        activity.startActivityForResult(intent, i);
        return intent;
    }

    public void offerSuperUser(Activity activity) {
        RootTools.log("Launching Play Store for SuperSU");
        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=eu.chainfire.supersu")));
    }

    public Intent offerSuperUser(Activity activity, int i) {
        RootTools.log("Launching Play Store for SuperSU");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=eu.chainfire.supersu"));
        activity.startActivityForResult(intent, i);
        return intent;
    }

    private void commandWait(Shell shell, Command command) throws Exception {
        while (!command.isFinished()) {
            RootTools.log(Constants.TAG, shell.getCommandQueuePositionString(command));
            RootTools.log(Constants.TAG, "Processed " + command.totalOutputProcessed + " of " + command.totalOutput + " output from command.");
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
                    Log.e(Constants.TAG, "Waiting for a command to be executed in a shell that is not executing and not reading! \n\n Command: " + command.getCommand());
                    Exception exc = new Exception();
                    exc.setStackTrace(Thread.currentThread().getStackTrace());
                    exc.printStackTrace();
                } else if (shell.isExecuting && !shell.isReading) {
                    Log.e(Constants.TAG, "Waiting for a command to be executed in a shell that is executing but not reading! \n\n Command: " + command.getCommand());
                    Exception exc2 = new Exception();
                    exc2.setStackTrace(Thread.currentThread().getStackTrace());
                    exc2.printStackTrace();
                } else {
                    Log.e(Constants.TAG, "Waiting for a command to be executed in a shell that is not reading! \n\n Command: " + command.getCommand());
                    Exception exc3 = new Exception();
                    exc3.setStackTrace(Thread.currentThread().getStackTrace());
                    exc3.printStackTrace();
                }
            }
        }
    }
}
