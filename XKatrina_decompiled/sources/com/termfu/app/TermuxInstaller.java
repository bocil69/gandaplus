package com.termfu.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Process;
import android.os.UserManager;
import android.system.Os;
import android.util.Log;
import android.util.Pair;
import com.fufufu.katrina.backup.R;
import com.termfu.app.TermuxInstaller;
import com.termux.terminal.EmulatorDebug;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class TermuxInstaller {
    public static native byte[] getZip();

    TermuxInstaller() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setupIfNeeded(Activity activity, Runnable runnable) {
        if (!(((UserManager) activity.getSystemService("user")).getSerialNumberForUser(Process.myUserHandle()) == 0)) {
            new AlertDialog.Builder(activity).setTitle(R.string.admsoloraya_res_0x7f1200d0).setMessage(R.string.admsoloraya_res_0x7f1200cf).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.termfu.app.TermuxInstaller$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    System.exit(0);
                }
            }).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).show();
            return;
        }
        File file = new File(TermuxService.PREFIX_PATH);
        if (file.isDirectory()) {
            runnable.run();
        } else {
            new AnonymousClass1(activity, ProgressDialog.show(activity, null, activity.getString(R.string.admsoloraya_res_0x7f1200d2), true, false), file, runnable).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.termfu.app.TermuxInstaller$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends Thread {
        private final /* synthetic */ File val$PREFIX_FILE;
        private final /* synthetic */ Activity val$activity;
        private final /* synthetic */ ProgressDialog val$progress;
        private final /* synthetic */ Runnable val$whenDone;

        AnonymousClass1(Activity activity, ProgressDialog progressDialog, File file, Runnable runnable) {
            this.val$activity = activity;
            this.val$progress = progressDialog;
            this.val$PREFIX_FILE = file;
            this.val$whenDone = runnable;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Activity activity;
            Runnable runnable;
            try {
                try {
                    File file = new File("/data/data/com.fufufu.katrina.backup/files/usr-staging");
                    if (file.exists()) {
                        TermuxInstaller.deleteFolder(file);
                    }
                    byte[] bArr = new byte[8096];
                    ArrayList<Pair> arrayList = new ArrayList(50);
                    try {
                        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(TermuxInstaller.loadZipBytes()));
                        while (true) {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry != null) {
                                if (nextEntry.getName().equals("SYMLINKS.txt")) {
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zipInputStream));
                                    while (true) {
                                        String readLine = bufferedReader.readLine();
                                        if (readLine == null) {
                                            break;
                                        }
                                        String[] split = readLine.split("←");
                                        if (split.length != 2) {
                                            throw new RuntimeException("Malformed symlink line: " + readLine);
                                        }
                                        String str = split[0];
                                        String str2 = "/data/data/com.fufufu.katrina.backup/files/usr-staging/" + split[1];
                                        arrayList.add(Pair.create(str, str2));
                                        TermuxInstaller.ensureDirectoryExists(new File(str2).getParentFile());
                                    }
                                } else {
                                    String name = nextEntry.getName();
                                    File file2 = new File("/data/data/com.fufufu.katrina.backup/files/usr-staging", name);
                                    boolean isDirectory = nextEntry.isDirectory();
                                    TermuxInstaller.ensureDirectoryExists(isDirectory ? file2 : file2.getParentFile());
                                    if (isDirectory) {
                                        continue;
                                    } else {
                                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                        while (true) {
                                            try {
                                                int read = zipInputStream.read(bArr);
                                                if (read == -1) {
                                                    break;
                                                }
                                                fileOutputStream.write(bArr, 0, read);
                                            } finally {
                                                fileOutputStream.close();
                                            }
                                        }
                                        if (name.startsWith("bin/") || name.startsWith("libexec") || name.startsWith("lib/apt/methods")) {
                                            Os.chmod(file2.getAbsolutePath(), 448);
                                        }
                                    }
                                }
                            } else {
                                zipInputStream.close();
                                if (arrayList.isEmpty()) {
                                    throw new RuntimeException("No SYMLINKS.txt encountered");
                                }
                                for (Pair pair : arrayList) {
                                    Os.symlink((String) pair.first, (String) pair.second);
                                }
                                if (!file.renameTo(this.val$PREFIX_FILE)) {
                                    throw new RuntimeException("Unable to rename staging folder");
                                }
                                this.val$activity.runOnUiThread(this.val$whenDone);
                                activity = this.val$activity;
                                final ProgressDialog progressDialog = this.val$progress;
                                runnable = new Runnable() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda3
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        progressDialog.dismiss();
                                    }
                                };
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    Activity activity2 = this.val$activity;
                    final ProgressDialog progressDialog2 = this.val$progress;
                    activity2.runOnUiThread(new Runnable() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            progressDialog2.dismiss();
                        }
                    });
                    throw th;
                }
            } catch (Exception e) {
                Log.e(EmulatorDebug.LOG_TAG, "Bootstrap error", e);
                final Activity activity3 = this.val$activity;
                final Runnable runnable2 = this.val$whenDone;
                activity3.runOnUiThread(new Runnable() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        new AlertDialog.Builder(r0).setTitle(R.string.admsoloraya_res_0x7f1200d0).setMessage(R.string.admsoloraya_res_0x7f1200ce).setNegativeButton(R.string.admsoloraya_res_0x7f1200cd, new DialogInterface.OnClickListener() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda0
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i) {
                                TermuxInstaller.AnonymousClass1.lambda$2(r1, dialogInterface, i);
                            }
                        }).setPositiveButton(R.string.admsoloraya_res_0x7f1200d1, new DialogInterface.OnClickListener() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda1
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i) {
                                TermuxInstaller.AnonymousClass1.lambda$3(r1, r2, dialogInterface, i);
                            }
                        }).show();
                    }
                });
                activity = this.val$activity;
                final ProgressDialog progressDialog3 = this.val$progress;
                runnable = new Runnable() { // from class: com.termfu.app.TermuxInstaller$1$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        progressDialog3.dismiss();
                    }
                };
            }
            activity.runOnUiThread(runnable);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$2(Activity activity, DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            activity.finish();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$3(Activity activity, Runnable runnable, DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            TermuxInstaller.setupIfNeeded(activity, runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ensureDirectoryExists(File file) {
        if (file.isDirectory() || file.mkdirs()) {
            return;
        }
        throw new RuntimeException("Unable to create directory: " + file.getAbsolutePath());
    }

    public static byte[] loadZipBytes() {
        System.loadLibrary("termfu-bootstrap");
        return getZip();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deleteFolder(File file) throws IOException {
        File[] listFiles;
        if (file.getCanonicalPath().equals(file.getAbsolutePath()) && file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                deleteFolder(file2);
            }
        }
        if (file.delete()) {
            return;
        }
        StringBuilder sb = new StringBuilder("Unable to delete ");
        sb.append(file.isDirectory() ? "directory " : "file ");
        sb.append(file.getAbsolutePath());
        throw new RuntimeException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.termfu.app.TermuxInstaller$2] */
    public static void setupStorageSymlinks(final Context context) {
        new Thread() { // from class: com.termfu.app.TermuxInstaller.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    File file = new File(TermuxService.HOME_PATH, "storage");
                    if (file.exists()) {
                        try {
                            TermuxInstaller.deleteFolder(file);
                        } catch (IOException e) {
                            Log.e("termfu-storage", "Could not delete old $HOME/storage, " + e.getMessage());
                            return;
                        }
                    }
                    if (!file.mkdirs()) {
                        Log.e("termfu-storage", "Unable to mkdirs() for $HOME/storage");
                        return;
                    }
                    Os.symlink(Environment.getExternalStorageDirectory().getAbsolutePath(), new File(file, "shared").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), new File(file, "downloads").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), new File(file, "dcim").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath(), new File(file, "pictures").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath(), new File(file, "music").getAbsolutePath());
                    Os.symlink(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath(), new File(file, "movies").getAbsolutePath());
                    File[] externalFilesDirs = context.getExternalFilesDirs(null);
                    if (externalFilesDirs != null) {
                        if (externalFilesDirs.length > 1) {
                            for (int i = 1; i < externalFilesDirs.length; i++) {
                                File file2 = externalFilesDirs[i];
                                if (file2 != null) {
                                    Os.symlink(file2.getAbsolutePath(), new File(file, "external-" + i).getAbsolutePath());
                                }
                            }
                        }
                    }
                } catch (Exception e2) {
                    Log.e("termfu-storage", "Error setting up link", e2);
                }
            }
        }.start();
    }
}
