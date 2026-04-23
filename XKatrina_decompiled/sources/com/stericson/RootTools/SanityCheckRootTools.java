package com.stericson.RootTools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.widget.ScrollView;
import android.widget.TextView;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootShell.execution.Shell;
import com.stericson.RootTools.containers.Permissions;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;
/* loaded from: classes6.dex */
public class SanityCheckRootTools extends Activity {
    private ProgressDialog mPDialog;
    private ScrollView mScrollView;
    private TextView mTextView;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        RootTools.debugMode = true;
        TextView textView = new TextView(this);
        this.mTextView = textView;
        textView.setText("");
        ScrollView scrollView = new ScrollView(this);
        this.mScrollView = scrollView;
        scrollView.addView(this.mTextView);
        setContentView(this.mScrollView);
        print("SanityCheckRootTools \n\n");
        if (RootTools.isRootAvailable()) {
            print("Root found.\n");
        } else {
            print("Root not found");
        }
        try {
            Shell.startRootShell();
        } catch (RootDeniedException e) {
            print("[ ROOT DENIED EXCEPTION! ]\n");
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (TimeoutException e3) {
            print("[ TIMEOUT EXCEPTION! ]\n");
            e3.printStackTrace();
        }
        try {
            if (!RootTools.isAccessGiven()) {
                print("ERROR: No root access to this device.\n");
                return;
            }
            ProgressDialog progressDialog = new ProgressDialog(this);
            this.mPDialog = progressDialog;
            progressDialog.setCancelable(false);
            this.mPDialog.setProgressStyle(0);
            new SanityCheckThread(this, new TestHandler(this, null)).start();
        } catch (Exception unused) {
            print("ERROR: could not determine root access to this device.\n");
        }
    }

    protected void print(CharSequence charSequence) {
        this.mTextView.append(charSequence);
        this.mScrollView.post(new Runnable() { // from class: com.stericson.RootTools.SanityCheckRootTools.1
            @Override // java.lang.Runnable
            public void run() {
                SanityCheckRootTools.this.mScrollView.fullScroll(130);
            }
        });
    }

    /* loaded from: classes6.dex */
    private class SanityCheckThread extends Thread {
        private Handler mHandler;

        public SanityCheckThread(Context context, Handler handler) {
            this.mHandler = handler;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            visualUpdate(1, null);
            visualUpdate(4, "Testing getPath");
            visualUpdate(3, "[ getPath ]\n");
            try {
                Iterator<String> it = RootTools.getPath().iterator();
                while (it.hasNext()) {
                    visualUpdate(3, String.valueOf(it.next()) + " k\n\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            visualUpdate(4, "Testing A ton of commands");
            visualUpdate(3, "[ Ton of Commands ]\n");
            for (int i = 0; i < 100; i++) {
                RootTools.exists("/system/xbin/busybox");
            }
            visualUpdate(4, "Testing Find Binary");
            boolean isRootAvailable = RootTools.isRootAvailable();
            visualUpdate(3, "[ Checking Root ]\n");
            visualUpdate(3, String.valueOf(isRootAvailable) + " k\n\n");
            visualUpdate(4, "Testing file exists");
            visualUpdate(3, "[ Checking Exists() ]\n");
            visualUpdate(3, String.valueOf(RootTools.exists("/system/sbin/[")) + " k\n\n");
            visualUpdate(4, "Testing Is Access Given");
            boolean isAccessGiven = RootTools.isAccessGiven();
            visualUpdate(3, "[ Checking for Access to Root ]\n");
            visualUpdate(3, String.valueOf(isAccessGiven) + " k\n\n");
            visualUpdate(4, "Testing Remount");
            boolean remount = RootTools.remount("/system", "rw");
            visualUpdate(3, "[ Remounting System as RW ]\n");
            visualUpdate(3, String.valueOf(remount) + " k\n\n");
            visualUpdate(4, "Testing CheckUtil");
            visualUpdate(3, "[ Checking busybox is setup ]\n");
            visualUpdate(3, String.valueOf(RootTools.checkUtil("busybox")) + " k\n\n");
            visualUpdate(4, "Testing getBusyBoxVersion");
            visualUpdate(3, "[ Checking busybox version ]\n");
            visualUpdate(3, String.valueOf(RootTools.getBusyBoxVersion("/system/xbin/")) + " k\n\n");
            try {
                visualUpdate(4, "Testing fixUtils");
                visualUpdate(3, "[ Checking Utils ]\n");
                visualUpdate(3, String.valueOf(RootTools.fixUtils(new String[]{"ls", "rm", "ln", "dd", "chmod", "mount"})) + " k\n\n");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                visualUpdate(4, "Testing getSymlink");
                visualUpdate(3, "[ Checking [[ for symlink ]\n");
                visualUpdate(3, String.valueOf(RootTools.getSymlink("/system/bin/[[")) + " k\n\n");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            visualUpdate(4, "Testing getInode");
            visualUpdate(3, "[ Checking Inodes ]\n");
            visualUpdate(3, String.valueOf(RootTools.getInode("/system/bin/busybox")) + " k\n\n");
            visualUpdate(4, "Testing GetBusyBoxapplets");
            try {
                visualUpdate(3, "[ Getting all available Busybox applets ]\n");
                Iterator<String> it2 = RootTools.getBusyBoxApplets("/data/data/stericson.busybox/files/bb/busybox").iterator();
                while (it2.hasNext()) {
                    visualUpdate(3, String.valueOf(it2.next()) + " k\n\n");
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            visualUpdate(4, "Testing GetBusyBox version in a special directory!");
            try {
                visualUpdate(3, "[ Testing GetBusyBox version in a special directory! ]\n");
                visualUpdate(3, String.valueOf(RootTools.getBusyBoxVersion("/data/data/stericson.busybox/files/bb/")) + " k\n\n");
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            visualUpdate(4, "Testing getFilePermissionsSymlinks");
            Permissions filePermissionsSymlinks = RootTools.getFilePermissionsSymlinks("/system/xbin/busybox");
            visualUpdate(3, "[ Checking busybox permissions and symlink ]\n");
            if (filePermissionsSymlinks != null) {
                visualUpdate(3, "Symlink: " + filePermissionsSymlinks.getSymlink() + " k\n\n");
                visualUpdate(3, "Group Permissions: " + filePermissionsSymlinks.getGroupPermissions() + " k\n\n");
                visualUpdate(3, "Owner Permissions: " + filePermissionsSymlinks.getOtherPermissions() + " k\n\n");
                visualUpdate(3, "Permissions: " + filePermissionsSymlinks.getPermissions() + " k\n\n");
                visualUpdate(3, "Type: " + filePermissionsSymlinks.getType() + " k\n\n");
                visualUpdate(3, "User Permissions: " + filePermissionsSymlinks.getUserPermissions() + " k\n\n");
            } else {
                visualUpdate(3, "Permissions == null k\n\n");
            }
            visualUpdate(4, "Testing output capture");
            visualUpdate(3, "[ busybox ash --help ]\n");
            try {
                Shell shell = RootTools.getShell(true);
                shell.add(new Command(0, "busybox ash --help") { // from class: com.stericson.RootTools.SanityCheckRootTools.SanityCheckThread.1
                    @Override // com.stericson.RootShell.execution.Command
                    public void commandOutput(int i2, String str) {
                        SanityCheckThread sanityCheckThread = SanityCheckThread.this;
                        sanityCheckThread.visualUpdate(3, String.valueOf(str) + "\n");
                        super.commandOutput(i2, str);
                    }
                });
                visualUpdate(4, "getevent - /dev/input/event0");
                visualUpdate(3, "[ getevent - /dev/input/event0 ]\n");
                shell.add(new Command(0, 0, "getevent /dev/input/event0") { // from class: com.stericson.RootTools.SanityCheckRootTools.SanityCheckThread.2
                    @Override // com.stericson.RootShell.execution.Command
                    public void commandOutput(int i2, String str) {
                        SanityCheckThread sanityCheckThread = SanityCheckThread.this;
                        sanityCheckThread.visualUpdate(3, String.valueOf(str) + "\n");
                        super.commandOutput(i2, str);
                    }
                });
            } catch (Exception e6) {
                e6.printStackTrace();
            }
            visualUpdate(4, "Switching RootContext - SYSTEM_APP");
            visualUpdate(3, "[ Switching Root Context - SYSTEM_APP ]\n");
            try {
                Shell shell2 = RootTools.getShell(true, Shell.ShellContext.SYSTEM_APP);
                shell2.add(new Command(0, "id") { // from class: com.stericson.RootTools.SanityCheckRootTools.SanityCheckThread.3
                    @Override // com.stericson.RootShell.execution.Command
                    public void commandOutput(int i2, String str) {
                        SanityCheckThread sanityCheckThread = SanityCheckThread.this;
                        sanityCheckThread.visualUpdate(3, String.valueOf(str) + "\n");
                        super.commandOutput(i2, str);
                    }
                });
                visualUpdate(4, "Testing PM");
                visualUpdate(3, "[ Testing pm list packages -d ]\n");
                shell2.add(new Command(0, "sh /system/bin/pm list packages -d") { // from class: com.stericson.RootTools.SanityCheckRootTools.SanityCheckThread.4
                    @Override // com.stericson.RootShell.execution.Command
                    public void commandOutput(int i2, String str) {
                        SanityCheckThread sanityCheckThread = SanityCheckThread.this;
                        sanityCheckThread.visualUpdate(3, String.valueOf(str) + "\n");
                        super.commandOutput(i2, str);
                    }
                });
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            visualUpdate(4, "Switching RootContext - UNTRUSTED");
            visualUpdate(3, "[ Switching Root Context - UNTRUSTED ]\n");
            try {
                RootTools.getShell(true, Shell.ShellContext.UNTRUSTED_APP).add(new Command(0, "id") { // from class: com.stericson.RootTools.SanityCheckRootTools.SanityCheckThread.5
                    @Override // com.stericson.RootShell.execution.Command
                    public void commandOutput(int i2, String str) {
                        SanityCheckThread sanityCheckThread = SanityCheckThread.this;
                        sanityCheckThread.visualUpdate(3, String.valueOf(str) + "\n");
                        super.commandOutput(i2, str);
                    }
                });
            } catch (Exception e8) {
                e8.printStackTrace();
            }
            visualUpdate(4, "Testing df");
            long space = RootTools.getSpace("/data");
            visualUpdate(3, "[ Checking /data partition size]\n");
            visualUpdate(3, String.valueOf(space) + "k\n\n");
            try {
                RootTools.getShell(true).add(new Command(42, false, "echo done") { // from class: com.stericson.RootTools.SanityCheckRootTools.SanityCheckThread.6
                    boolean _catch = false;

                    @Override // com.stericson.RootShell.execution.Command
                    public void commandOutput(int i2, String str) {
                        if (this._catch) {
                            RootTools.log("CAUGHT!!!");
                        }
                        super.commandOutput(i2, str);
                    }

                    @Override // com.stericson.RootShell.execution.Command
                    public void commandTerminated(int i2, String str) {
                        synchronized (SanityCheckRootTools.this) {
                            this._catch = true;
                            SanityCheckThread.this.visualUpdate(4, "All tests complete.");
                            SanityCheckThread.this.visualUpdate(2, null);
                            try {
                                RootTools.closeAllShells();
                            } catch (IOException e9) {
                                e9.printStackTrace();
                            }
                        }
                    }

                    @Override // com.stericson.RootShell.execution.Command
                    public void commandCompleted(int i2, int i3) {
                        synchronized (SanityCheckRootTools.this) {
                            this._catch = true;
                            SanityCheckThread.this.visualUpdate(4, "All tests complete.");
                            SanityCheckThread.this.visualUpdate(2, null);
                            try {
                                RootTools.closeAllShells();
                            } catch (IOException e9) {
                                e9.printStackTrace();
                            }
                        }
                    }
                });
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void visualUpdate(int i, String str) {
            Message obtainMessage = this.mHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putInt("action", i);
            bundle.putString("text", str);
            obtainMessage.setData(bundle);
            this.mHandler.sendMessage(obtainMessage);
        }
    }

    /* loaded from: classes6.dex */
    private class TestHandler extends Handler {
        public static final String ACTION = "action";
        public static final int ACTION_DISPLAY = 3;
        public static final int ACTION_HIDE = 2;
        public static final int ACTION_PDISPLAY = 4;
        public static final int ACTION_SHOW = 1;
        public static final String TEXT = "text";

        private TestHandler() {
        }

        /* synthetic */ TestHandler(SanityCheckRootTools sanityCheckRootTools, TestHandler testHandler) {
            this();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.getData().getInt("action");
            String string = message.getData().getString("text");
            if (i == 1) {
                SanityCheckRootTools.this.mPDialog.show();
                SanityCheckRootTools.this.mPDialog.setMessage("Running Root Library Tests...");
            } else if (i == 2) {
                if (string != null) {
                    SanityCheckRootTools.this.print(string);
                }
                SanityCheckRootTools.this.mPDialog.hide();
            } else if (i == 3) {
                SanityCheckRootTools.this.print(string);
            } else if (i != 4) {
            } else {
                SanityCheckRootTools.this.mPDialog.setMessage(string);
            }
        }
    }
}
