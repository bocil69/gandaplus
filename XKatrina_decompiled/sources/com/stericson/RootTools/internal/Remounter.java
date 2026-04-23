package com.stericson.RootTools.internal;

import com.stericson.RootShell.execution.Command;
import com.stericson.RootShell.execution.Shell;
import com.stericson.RootTools.Constants;
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.containers.Mount;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes6.dex */
public class Remounter {
    public boolean remount(String str, String str2) {
        String str3;
        Remounter remounter = this;
        String str4 = str;
        String str5 = str2;
        if (str4.endsWith("/") && !str4.equals("/")) {
            str4 = str4.substring(0, str4.lastIndexOf("/"));
        }
        boolean z = false;
        while (!z) {
            String str6 = str5;
            try {
                Iterator<Mount> it = RootTools.getMounts().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Mount next = it.next();
                    RootTools.log(next.getMountPoint().toString());
                    if (str4.equals(next.getMountPoint().toString())) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    try {
                        str4 = new File(str4).getParent();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                str5 = str6;
            } catch (Exception e2) {
                if (RootTools.debugMode) {
                    e2.printStackTrace();
                    return false;
                }
                return false;
            }
        }
        Mount findMountPointRecursive = remounter.findMountPointRecursive(str4);
        if (findMountPointRecursive != null) {
            RootTools.log(Constants.TAG, "Remounting " + findMountPointRecursive.getMountPoint().getAbsolutePath() + " as " + str2.toLowerCase());
            if (findMountPointRecursive.getFlags().contains(str2.toLowerCase())) {
                str3 = Constants.TAG;
            } else {
                try {
                    String[] strArr = new String[17];
                    str3 = Constants.TAG;
                    try {
                        StringBuilder sb = new StringBuilder("busybox mount -o remount,");
                        try {
                            sb.append(str2.toLowerCase());
                            sb.append(" ");
                            sb.append(findMountPointRecursive.getDevice().getAbsolutePath());
                            sb.append(" ");
                            sb.append(findMountPointRecursive.getMountPoint().getAbsolutePath());
                            strArr[0] = sb.toString();
                            strArr[1] = "busybox mount -o remount," + str2.toLowerCase() + " " + str4;
                            strArr[2] = "busybox mount -o " + str2.toLowerCase() + ",remount " + findMountPointRecursive.getDevice().getAbsolutePath();
                            strArr[3] = "busybox mount -o " + str2.toLowerCase() + ",remount " + str4;
                            strArr[4] = "toolbox mount -o remount," + str2.toLowerCase() + " " + findMountPointRecursive.getDevice().getAbsolutePath() + " " + findMountPointRecursive.getMountPoint().getAbsolutePath();
                            StringBuilder sb2 = new StringBuilder("toolbox mount -o remount,");
                            sb2.append(str2.toLowerCase());
                            sb2.append(" ");
                            sb2.append(str4);
                            strArr[5] = sb2.toString();
                            strArr[6] = "toybox mount -o remount," + str2.toLowerCase() + " " + findMountPointRecursive.getDevice().getAbsolutePath() + " " + findMountPointRecursive.getMountPoint().getAbsolutePath();
                            StringBuilder sb3 = new StringBuilder("toolbox mount -o ");
                            sb3.append(str2.toLowerCase());
                            sb3.append(",remount ");
                            sb3.append(findMountPointRecursive.getDevice().getAbsolutePath());
                            strArr[7] = sb3.toString();
                            strArr[8] = "toolbox mount -o " + str2.toLowerCase() + ",remount " + str4;
                            strArr[9] = "mount -o remount," + str2.toLowerCase() + " " + findMountPointRecursive.getDevice().getAbsolutePath() + " " + findMountPointRecursive.getMountPoint().getAbsolutePath();
                            StringBuilder sb4 = new StringBuilder("mount -o remount,");
                            sb4.append(str2.toLowerCase());
                            sb4.append(" ");
                            sb4.append(str4);
                            strArr[10] = sb4.toString();
                            strArr[11] = "mount -o " + str2.toLowerCase() + ",remount " + findMountPointRecursive.getDevice().getAbsolutePath();
                            strArr[12] = "mount -o " + str2.toLowerCase() + ",remount " + str4;
                            strArr[13] = "toybox mount -o remount," + str2.toLowerCase() + " " + findMountPointRecursive.getDevice().getAbsolutePath() + " " + findMountPointRecursive.getMountPoint().getAbsolutePath();
                            StringBuilder sb5 = new StringBuilder("toybox mount -o remount,");
                            sb5.append(str2.toLowerCase());
                            sb5.append(" ");
                            sb5.append(str4);
                            strArr[14] = sb5.toString();
                            strArr[15] = "toybox mount -o " + str2.toLowerCase() + ",remount " + findMountPointRecursive.getDevice().getAbsolutePath();
                            strArr[16] = "toybox mount -o " + str2.toLowerCase() + ",remount " + str4;
                            Command command = new Command(0, true, strArr);
                            Shell.startRootShell().add(command);
                            remounter = this;
                            remounter.commandWait(command);
                        } catch (Exception unused) {
                            remounter = this;
                        }
                    } catch (Exception unused2) {
                    }
                } catch (Exception unused3) {
                    str3 = Constants.TAG;
                }
                findMountPointRecursive = remounter.findMountPointRecursive(str4);
            }
            if (findMountPointRecursive != null) {
                RootTools.log(str3, findMountPointRecursive.getFlags() + " AND " + str2.toLowerCase());
                if (findMountPointRecursive.getFlags().contains(str2.toLowerCase())) {
                    RootTools.log(findMountPointRecursive.getFlags().toString());
                    return true;
                }
                RootTools.log(findMountPointRecursive.getFlags().toString());
                return false;
            }
            RootTools.log("mount is null, file was: " + str4 + " mountType was: " + str2);
            return false;
        }
        String str7 = str5;
        RootTools.log("mount is null, file was: " + str4 + " mountType was: " + str7);
        return false;
    }

    private Mount findMountPointRecursive(String str) {
        try {
            ArrayList<Mount> mounts = RootTools.getMounts();
            File file = new File(str);
            while (true) {
                Iterator<Mount> it = mounts.iterator();
                while (it.hasNext()) {
                    Mount next = it.next();
                    if (next.getMountPoint().equals(file)) {
                        return next;
                    }
                }
            }
        } catch (IOException e) {
            if (RootTools.debugMode) {
                e.printStackTrace();
                return null;
            }
            return null;
        } catch (Exception e2) {
            if (RootTools.debugMode) {
                e2.printStackTrace();
                return null;
            }
            return null;
        }
    }

    private void commandWait(Command command) {
        synchronized (command) {
            try {
                if (!command.isFinished()) {
                    command.wait(2000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
