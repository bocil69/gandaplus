package com.stericson.RootTools.internal;

import android.content.Context;
import android.util.Log;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootShell.execution.Shell;
import com.stericson.RootTools.RootTools;
import java.io.IOException;
/* loaded from: classes6.dex */
public class Runner extends Thread {
    private static final String LOG_TAG = "RootTools::Runner";
    String binaryName;
    Context context;
    String parameter;

    public Runner(Context context, String str, String str2) {
        this.context = context;
        this.binaryName = str;
        this.parameter = str2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        String str;
        try {
            str = this.context.getFilesDir().getCanonicalPath();
        } catch (IOException e) {
            if (RootTools.debugMode) {
                Log.e(LOG_TAG, "Problem occured while trying to locate private files directory!");
            }
            e.printStackTrace();
            str = null;
        }
        if (str != null) {
            try {
                Command command = new Command(0, false, String.valueOf(str) + "/" + this.binaryName + " " + this.parameter);
                Shell.startRootShell().add(command);
                commandWait(command);
            } catch (Exception unused) {
            }
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
