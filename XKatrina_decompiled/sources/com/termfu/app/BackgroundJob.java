package com.termfu.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes6.dex */
public final class BackgroundJob {
    private static final String LOG_TAG = "termfu-task";
    final Process mProcess;

    public BackgroundJob(String str, String str2, String[] strArr, TermuxService termuxService) {
        this(str, str2, strArr, termuxService, null);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.termfu.app.BackgroundJob$2] */
    public BackgroundJob(String str, String str2, String[] strArr, final TermuxService termuxService, final PendingIntent pendingIntent) {
        String str3 = str;
        String[] buildEnvironment = buildEnvironment(false, str);
        str3 = str3 == null ? TermuxService.HOME_PATH : str3;
        String[] strArr2 = setupProcessArgs(str2, strArr);
        final String arrays = Arrays.toString(strArr2);
        try {
            Process exec = Runtime.getRuntime().exec(strArr2, buildEnvironment, new File(str3));
            this.mProcess = exec;
            final int pid = getPid(exec);
            final Bundle bundle = new Bundle();
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            final Thread thread = new Thread() { // from class: com.termfu.app.BackgroundJob.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(BackgroundJob.this.mProcess.getErrorStream(), StandardCharsets.UTF_8));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                return;
                            }
                            StringBuilder sb3 = sb2;
                            sb3.append(readLine);
                            sb3.append('\n');
                            Log.i(BackgroundJob.LOG_TAG, "[" + pid + "] stderr: " + readLine);
                        } catch (IOException unused) {
                            return;
                        }
                    }
                }
            };
            thread.start();
            new Thread() { // from class: com.termfu.app.BackgroundJob.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Log.i(BackgroundJob.LOG_TAG, "[" + pid + "] starting: " + arrays);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(BackgroundJob.this.mProcess.getInputStream(), StandardCharsets.UTF_8));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            Log.i(BackgroundJob.LOG_TAG, "[" + pid + "] stdout: " + readLine);
                            StringBuilder sb3 = sb;
                            sb3.append(readLine);
                            sb3.append('\n');
                        } catch (IOException e) {
                            Log.e(BackgroundJob.LOG_TAG, "Error reading output", e);
                        }
                    }
                    try {
                        int waitFor = BackgroundJob.this.mProcess.waitFor();
                        termuxService.onBackgroundJobExited(BackgroundJob.this);
                        if (waitFor == 0) {
                            Log.i(BackgroundJob.LOG_TAG, "[" + pid + "] exited normally");
                        } else {
                            Log.w(BackgroundJob.LOG_TAG, "[" + pid + "] exited with code: " + waitFor);
                        }
                        bundle.putString("stdout", sb.toString());
                        bundle.putInt("exitCode", waitFor);
                        thread.join();
                        bundle.putString("stderr", sb2.toString());
                        Intent intent = new Intent();
                        intent.putExtra("result", bundle);
                        PendingIntent pendingIntent2 = pendingIntent;
                        if (pendingIntent2 != null) {
                            pendingIntent2.send(termuxService.getApplicationContext(), -1, intent);
                        }
                    } catch (PendingIntent.CanceledException | InterruptedException unused) {
                    }
                }
            }.start();
        } catch (IOException e) {
            this.mProcess = null;
            Log.e(LOG_TAG, "Failed running background job: " + arrays, e);
        }
    }

    private static void addToEnvIfPresent(List<String> list, String str) {
        String str2 = System.getenv(str);
        if (str2 != null) {
            list.add(String.valueOf(str) + "=" + str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] buildEnvironment(boolean z, String str) {
        new File(TermuxService.HOME_PATH).mkdirs();
        if (str == null) {
            str = TermuxService.HOME_PATH;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("TERMUX_VERSION=0.108");
        arrayList.add("TERM=xterm-256color");
        arrayList.add("COLORTERM=truecolor");
        arrayList.add("HOME=/data/data/com.fufufu.katrina.backup/files/home");
        arrayList.add("PREFIX=/data/data/com.fufufu.katrina.backup");
        arrayList.add("BOOTCLASSPATH=" + System.getenv("BOOTCLASSPATH"));
        arrayList.add("ANDROID_ROOT=" + System.getenv("ANDROID_ROOT"));
        arrayList.add("ANDROID_DATA=" + System.getenv("ANDROID_DATA"));
        arrayList.add("EXTERNAL_STORAGE=" + System.getenv("EXTERNAL_STORAGE"));
        addToEnvIfPresent(arrayList, "ANDROID_ART_ROOT");
        addToEnvIfPresent(arrayList, "DEX2OATBOOTCLASSPATH");
        addToEnvIfPresent(arrayList, "ANDROID_I18N_ROOT");
        addToEnvIfPresent(arrayList, "ANDROID_RUNTIME_ROOT");
        addToEnvIfPresent(arrayList, "ANDROID_TZDATA_ROOT");
        if (z) {
            arrayList.add("PATH= " + System.getenv("PATH"));
        } else {
            arrayList.add("LANG=en_US.UTF-8");
            arrayList.add("PATH=/system/bin");
            arrayList.add("PWD=" + str);
            arrayList.add("TMPDIR=/data/data/com.fufufu.katrina.backup/tmp");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static int getPid(Process process) {
        try {
            Field declaredField = process.getClass().getDeclaredField("pid");
            declaredField.setAccessible(true);
            int i = declaredField.getInt(process);
            declaredField.setAccessible(false);
            return i;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005a, code lost:
        r2 = r5.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0064, code lost:
        if (r2.startsWith("/usr") != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006c, code lost:
        if (r2.startsWith("/bin") == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006e, code lost:
        r2 = r2.split("/");
        r2 = "/system/bin/" + r2[r2.length - 1];
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00be  */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String[] setupProcessArgs(java.lang.String r11, java.lang.String[] r12) {
        /*
            Method dump skipped, instructions count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termfu.app.BackgroundJob.setupProcessArgs(java.lang.String, java.lang.String[]):java.lang.String[]");
    }
}
