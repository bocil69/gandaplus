package com.stericson.RootTools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.stericson.RootShell.RootShell;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootShell.execution.Shell;
import com.stericson.RootTools.containers.Mount;
import com.stericson.RootTools.containers.Permissions;
import com.stericson.RootTools.containers.Symlink;
import com.stericson.RootTools.internal.Remounter;
import com.stericson.RootTools.internal.RootToolsInternalMethods;
import com.stericson.RootTools.internal.Runner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;
/* loaded from: classes6.dex */
public final class RootTools {
    public static boolean debugMode = false;
    public static int default_Command_Timeout = 20000;
    public static boolean handlerEnabled = true;
    private static RootToolsInternalMethods rim;
    public static String utilPath;

    public static void setRim(RootToolsInternalMethods rootToolsInternalMethods) {
        rim = rootToolsInternalMethods;
    }

    private static final RootToolsInternalMethods getInternals() {
        RootToolsInternalMethods rootToolsInternalMethods = rim;
        if (rootToolsInternalMethods == null) {
            RootToolsInternalMethods.getInstance();
            return rim;
        }
        return rootToolsInternalMethods;
    }

    public static boolean checkUtil(String str) {
        return getInternals().checkUtil(str);
    }

    public static void closeAllShells() throws IOException {
        RootShell.closeAllShells();
    }

    public static void closeCustomShell() throws IOException {
        RootShell.closeCustomShell();
    }

    public static void closeShell(boolean z) throws IOException {
        RootShell.closeShell(z);
    }

    public static boolean copyFile(String str, String str2, boolean z, boolean z2) {
        return getInternals().copyFile(str, str2, z, z2);
    }

    public static boolean deleteFileOrDirectory(String str, boolean z) {
        return getInternals().deleteFileOrDirectory(str, z);
    }

    public static boolean exists(String str) {
        return exists(str, false);
    }

    public static boolean exists(String str, boolean z) {
        return RootShell.exists(str, z);
    }

    public static void fixUtil(String str, String str2) {
        getInternals().fixUtil(str, str2);
    }

    public static boolean fixUtils(String[] strArr) throws Exception {
        return getInternals().fixUtils(strArr);
    }

    public static List<String> findBinary(String str, boolean z) {
        return RootShell.findBinary(str, z);
    }

    public static String getBusyBoxVersion(String str) {
        return getInternals().getBusyBoxVersion(str);
    }

    public static String getBusyBoxVersion() {
        return getBusyBoxVersion("");
    }

    public static List<String> getBusyBoxApplets() throws Exception {
        return getBusyBoxApplets("");
    }

    public static List<String> getBusyBoxApplets(String str) throws Exception {
        return getInternals().getBusyBoxApplets(str);
    }

    public static Shell getCustomShell(String str, int i) throws IOException, TimeoutException, RootDeniedException {
        return RootShell.getCustomShell(str, i);
    }

    public static Shell getCustomShell(String str) throws IOException, TimeoutException, RootDeniedException {
        return getCustomShell(str, 10000);
    }

    public static Permissions getFilePermissionsSymlinks(String str) {
        return getInternals().getFilePermissionsSymlinks(str);
    }

    public static String getInode(String str) {
        return getInternals().getInode(str);
    }

    public static ArrayList<Mount> getMounts() throws Exception {
        return getInternals().getMounts();
    }

    public static String getMountedAs(String str) throws Exception {
        return getInternals().getMountedAs(str);
    }

    public static List<String> getPath() {
        return Arrays.asList(System.getenv("PATH").split(":"));
    }

    public static Shell getShell(boolean z, int i, Shell.ShellContext shellContext, int i2) throws IOException, TimeoutException, RootDeniedException {
        return RootShell.getShell(z, i, shellContext, i2);
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

    public static long getSpace(String str) {
        return getInternals().getSpace(str);
    }

    public static String getSymlink(String str) {
        return getInternals().getSymlink(str);
    }

    public static ArrayList<Symlink> getSymlinks(String str) throws Exception {
        return getInternals().getSymlinks(str);
    }

    public static String getWorkingToolbox() {
        return getInternals().getWorkingToolbox();
    }

    public static boolean hasEnoughSpaceOnSdCard(long j) {
        return getInternals().hasEnoughSpaceOnSdCard(j);
    }

    public static boolean hasUtil(String str, String str2) {
        return getInternals().hasUtil(str, str2);
    }

    public static boolean installBinary(Context context, int i, String str, String str2) {
        return getInternals().installBinary(context, i, str, str2);
    }

    public static boolean installBinary(Context context, int i, String str) {
        return installBinary(context, i, str, "700");
    }

    public static boolean hasBinary(Context context, String str) {
        return getInternals().isBinaryAvailable(context, str);
    }

    public static boolean isAppletAvailable(String str, String str2) {
        return getInternals().isAppletAvailable(str, str2);
    }

    public static boolean isAppletAvailable(String str) {
        return isAppletAvailable(str, "");
    }

    public static boolean isAccessGiven() {
        return RootShell.isAccessGiven(0, 3);
    }

    public static boolean isAccessGiven(int i, int i2) {
        return RootShell.isAccessGiven(i, i2);
    }

    public static boolean isBusyboxAvailable() {
        return RootShell.isBusyboxAvailable();
    }

    public static boolean isNativeToolsReady(int i, Context context) {
        return getInternals().isNativeToolsReady(i, context);
    }

    public static boolean isProcessRunning(String str) {
        return getInternals().isProcessRunning(str);
    }

    public static boolean isRootAvailable() {
        return RootShell.isRootAvailable();
    }

    public static boolean killProcess(String str) {
        return getInternals().killProcess(str);
    }

    public static void offerBusyBox(Activity activity) {
        getInternals().offerBusyBox(activity);
    }

    public static Intent offerBusyBox(Activity activity, int i) {
        return getInternals().offerBusyBox(activity, i);
    }

    public static void offerSuperUser(Activity activity) {
        getInternals().offerSuperUser(activity);
    }

    public static Intent offerSuperUser(Activity activity, int i) {
        return getInternals().offerSuperUser(activity, i);
    }

    public static boolean remount(String str, String str2) {
        return new Remounter().remount(str, str2);
    }

    public static void restartAndroid() {
        log("Restart Android");
        killProcess("zygote");
    }

    public static void runBinary(Context context, String str, String str2) {
        new Runner(context, str, str2).start();
    }

    public static void runShellCommand(Shell shell, Command command) throws IOException {
        shell.add(command);
    }

    public static void log(String str) {
        log(null, str, 3, null);
    }

    public static void log(String str, String str2) {
        log(str, str2, 3, null);
    }

    public static void log(String str, int i, Exception exc) {
        log(null, str, i, exc);
    }

    public static boolean islog() {
        return debugMode;
    }

    public static void log(String str, String str2, int i, Exception exc) {
        if (str2 == null || str2.equals("") || !debugMode) {
            return;
        }
        if (str == null) {
            str = Constants.TAG;
        }
        if (i == 1) {
            Log.v(str, str2);
        } else if (i == 2) {
            Log.e(str, str2, exc);
        } else if (i != 3) {
        } else {
            Log.d(str, str2);
        }
    }
}
