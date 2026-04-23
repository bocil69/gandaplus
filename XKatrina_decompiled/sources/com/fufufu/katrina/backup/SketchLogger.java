package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/* loaded from: classes5.dex */
public class SketchLogger {
    private static Thread loggerThread = new Thread() { // from class: com.fufufu.katrina.backup.SketchLogger.1
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            SketchLogger.isRunning = true;
            try {
                Runtime.getRuntime().exec("logcat -c");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat").getInputStream()));
                String readLine = bufferedReader.readLine();
                do {
                    SketchLogger.broadcastLog(readLine);
                    if (!SketchLogger.isRunning) {
                        break;
                    }
                    readLine = bufferedReader.readLine();
                } while (readLine != null);
                if (SketchLogger.isRunning) {
                    SketchLogger.broadcastLog("Logger got killed. Restarting.");
                    SketchLogger.startLogging();
                } else {
                    SketchLogger.broadcastLog("Logger stopped.");
                }
                bufferedReader.close();
            } catch (Exception e) {
                SketchLogger.broadcastLog(e.toString());
            }
        }
    };
    private static volatile boolean isRunning = false;

    public static void startLogging() {
        if (!isRunning) {
            loggerThread.start();
            return;
        }
        throw new IllegalStateException("Logger already running");
    }

    public static void broadcastLog(String str) {
        Context context = SketchApplication.getContext();
        Intent intent = new Intent();
        intent.setAction("com.sketchware.remod.ACTION_NEW_DEBUG_LOG");
        intent.putExtra("log", str);
        intent.putExtra("packageName", context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static void stopLogging() {
        if (isRunning) {
            isRunning = false;
            broadcastLog("Stopping logger by user request.");
            return;
        }
        throw new IllegalStateException("Logger not running");
    }
}
