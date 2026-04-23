package com.termfu.filepicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.util.Patterns;
import com.fufufu.katrina.backup.R;
import com.termfu.app.DialogUtils;
import com.termfu.app.TermuxService;
import com.termux.terminal.EmulatorDebug;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.regex.Pattern;
/* loaded from: classes6.dex */
public class TermuxFileReceiverActivity extends Activity {
    static final String EDITOR_PROGRAM = "/data/data/com.fufufu.katrina.backup/files/home/bin/termfu-file-editor";
    static final String TERMUX_RECEIVEDIR = "/data/data/com.fufufu.katrina.backup/files/home/.sh";
    static final String URL_OPENER_PROGRAM = "/data/data/com.fufufu.katrina.backup/files/home/bin/termfu-url-opener";
    boolean mFinishOnDismissNameDialog = true;

    static boolean isSharedTextAnUrl(String str) {
        return Patterns.WEB_URL.matcher(str).matches() || Pattern.matches("magnet:\\?xt=urn:btih:.*?", str);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        String scheme = intent.getScheme();
        if ("android.intent.action.SEND".equals(action) && type != null) {
            String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
            Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
            if (stringExtra == null) {
                if (uri != null) {
                    handleContentUri(uri, intent.getStringExtra("android.intent.extra.TITLE"));
                } else {
                    showErrorDialogAndQuit("Send action without content - nothing to save.");
                }
            } else if (isSharedTextAnUrl(stringExtra)) {
                handleUrlAndFinish(stringExtra);
            } else {
                String stringExtra2 = intent.getStringExtra("android.intent.extra.SUBJECT");
                if (stringExtra2 == null) {
                    stringExtra2 = intent.getStringExtra("android.intent.extra.TITLE");
                }
                if (stringExtra2 != null) {
                    stringExtra2 = String.valueOf(stringExtra2) + ".txt";
                }
                promptNameAndSave(new ByteArrayInputStream(stringExtra.getBytes(StandardCharsets.UTF_8)), stringExtra2);
            }
        } else if ("content".equals(scheme)) {
            handleContentUri(intent.getData(), intent.getStringExtra("android.intent.extra.TITLE"));
        } else if ("file".equals(scheme)) {
            File file = new File(intent.getData().getPath());
            try {
                promptNameAndSave(new FileInputStream(file), file.getName());
            } catch (FileNotFoundException e) {
                showErrorDialogAndQuit("Cannot open file: " + e.getMessage() + ".");
            }
        } else {
            showErrorDialogAndQuit("Unable to receive any file or URL.");
        }
    }

    void showErrorDialogAndQuit(String str) {
        this.mFinishOnDismissNameDialog = false;
        new AlertDialog.Builder(this).setMessage(str).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                TermuxFileReceiverActivity.this.m123lambda$0$comtermfufilepickerTermuxFileReceiverActivity(dialogInterface);
            }
        }).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                TermuxFileReceiverActivity.this.m124lambda$1$comtermfufilepickerTermuxFileReceiverActivity(dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$0$com-termfu-filepicker-TermuxFileReceiverActivity  reason: not valid java name */
    public /* synthetic */ void m123lambda$0$comtermfufilepickerTermuxFileReceiverActivity(DialogInterface dialogInterface) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$1$com-termfu-filepicker-TermuxFileReceiverActivity  reason: not valid java name */
    public /* synthetic */ void m124lambda$1$comtermfufilepickerTermuxFileReceiverActivity(DialogInterface dialogInterface, int i) {
        finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0031 A[Catch: all -> 0x0044, DONT_GENERATE, TRY_LEAVE, TryCatch #3 {Exception -> 0x004e, blocks: (B:3:0x0002, B:24:0x0038, B:5:0x0007, B:20:0x0031, B:16:0x002a, B:17:0x002d), top: B:36:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void handleContentUri(android.net.Uri r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "_display_name"
            java.lang.String[] r3 = new java.lang.String[]{r0}     // Catch: java.lang.Exception -> L4e
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L44
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r9
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L44
            if (r1 == 0) goto L2e
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L26
            if (r2 == 0) goto L2e
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L26
            if (r0 < 0) goto L2e
            java.lang.String r0 = r1.getString(r0)     // Catch: java.lang.Throwable -> L26
            goto L2f
        L26:
            r10 = move-exception
            r7 = r10
            if (r1 == 0) goto L2d
            r1.close()     // Catch: java.lang.Throwable -> L44
        L2d:
            throw r7     // Catch: java.lang.Throwable -> L44
        L2e:
            r0 = r7
        L2f:
            if (r1 == 0) goto L34
            r1.close()     // Catch: java.lang.Throwable -> L44
        L34:
            if (r0 != 0) goto L37
            goto L38
        L37:
            r10 = r0
        L38:
            android.content.ContentResolver r0 = r8.getContentResolver()     // Catch: java.lang.Exception -> L4e
            java.io.InputStream r0 = r0.openInputStream(r9)     // Catch: java.lang.Exception -> L4e
            r8.promptNameAndSave(r0, r10)     // Catch: java.lang.Exception -> L4e
            goto L7c
        L44:
            r10 = move-exception
            if (r7 == 0) goto L4d
            if (r7 == r10) goto L4c
            r7.addSuppressed(r10)     // Catch: java.lang.Exception -> L4e
        L4c:
            r10 = r7
        L4d:
            throw r10     // Catch: java.lang.Exception -> L4e
        L4e:
            r10 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unable to handle shared content:\n\n"
            r0.<init>(r1)
            java.lang.String r1 = r10.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.showErrorDialogAndQuit(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "handleContentUri(uri="
            r0.<init>(r1)
            r0.append(r9)
            java.lang.String r9 = ") failed"
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            java.lang.String r0 = "termfu"
            android.util.Log.e(r0, r9, r10)
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termfu.filepicker.TermuxFileReceiverActivity.handleContentUri(android.net.Uri, java.lang.String):void");
    }

    void promptNameAndSave(final InputStream inputStream, String str) {
        DialogUtils.textInput(this, R.string.admsoloraya_res_0x7f1200d7, str, R.string.admsoloraya_res_0x7f1200d5, new DialogUtils.TextSetListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda2
            @Override // com.termfu.app.DialogUtils.TextSetListener
            public final void onTextSet(String str2) {
                TermuxFileReceiverActivity.this.m125lambda$2$comtermfufilepickerTermuxFileReceiverActivity(inputStream, str2);
            }
        }, R.string.admsoloraya_res_0x7f1200d6, new DialogUtils.TextSetListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda3
            @Override // com.termfu.app.DialogUtils.TextSetListener
            public final void onTextSet(String str2) {
                TermuxFileReceiverActivity.this.m126lambda$3$comtermfufilepickerTermuxFileReceiverActivity(inputStream, str2);
            }
        }, 17039360, new DialogUtils.TextSetListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda4
            @Override // com.termfu.app.DialogUtils.TextSetListener
            public final void onTextSet(String str2) {
                TermuxFileReceiverActivity.this.m127lambda$4$comtermfufilepickerTermuxFileReceiverActivity(str2);
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.termfu.filepicker.TermuxFileReceiverActivity$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                TermuxFileReceiverActivity.this.m128lambda$5$comtermfufilepickerTermuxFileReceiverActivity(dialogInterface);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$2$com-termfu-filepicker-TermuxFileReceiverActivity  reason: not valid java name */
    public /* synthetic */ void m125lambda$2$comtermfufilepickerTermuxFileReceiverActivity(InputStream inputStream, String str) {
        if (saveStreamWithName(inputStream, str) == null) {
            return;
        }
        new File(EDITOR_PROGRAM);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$3$com-termfu-filepicker-TermuxFileReceiverActivity  reason: not valid java name */
    public /* synthetic */ void m126lambda$3$comtermfufilepickerTermuxFileReceiverActivity(InputStream inputStream, String str) {
        if (saveStreamWithName(inputStream, str) == null) {
            return;
        }
        getAllSHFilesAndWriteToNewDirectory("/data/user/0/com.fufufu.katrina.backup/files/home/.sh/", "/data/user/0/com.fufufu.katrina.backup/files/home/.shortcuts/");
        setPermissions("/data/user/0/com.fufufu.katrina.backup/files/home/.sh/");
        setPermissions("/data/user/0/com.fufufu.katrina.backup/files/home/.shortcuts/");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$4$com-termfu-filepicker-TermuxFileReceiverActivity  reason: not valid java name */
    public /* synthetic */ void m127lambda$4$comtermfufilepickerTermuxFileReceiverActivity(String str) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$5$com-termfu-filepicker-TermuxFileReceiverActivity  reason: not valid java name */
    public /* synthetic */ void m128lambda$5$comtermfufilepickerTermuxFileReceiverActivity(DialogInterface dialogInterface) {
        if (this.mFinishOnDismissNameDialog) {
            finish();
        }
    }

    public File saveStreamWithName(InputStream inputStream, String str) {
        Throwable th;
        File file = new File("/data/data/com.fufufu.katrina.backup/files/home/.sh");
        if (!file.isDirectory() && !file.mkdirs()) {
            showErrorDialogAndQuit("Cannot create directory: " + file.getAbsolutePath());
            return null;
        }
        try {
            File file2 = new File(file, str);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.close();
                            return file2;
                        }
                    }
                } catch (Throwable th2) {
                    try {
                        fileOutputStream.close();
                        throw th2;
                    } catch (Throwable th3) {
                        th = th2;
                        th = th3;
                        if (th != null) {
                            if (th != th) {
                                th.addSuppressed(th);
                            }
                            throw th;
                        }
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                th = null;
            }
        } catch (IOException e) {
            showErrorDialogAndQuit("Error saving file:\n\n" + e);
            Log.e(EmulatorDebug.LOG_TAG, "Error saving file", e);
            return null;
        }
    }

    void handleUrlAndFinish(String str) {
        File file = new File(URL_OPENER_PROGRAM);
        if (!file.isFile()) {
            showErrorDialogAndQuit("The following file does not exist:\n$HOME/bin/termfu-url-opener\n\nCreate this file as a script or a symlink - it will be called with the shared URL as only argument.");
            return;
        }
        file.setExecutable(true);
        Intent intent = new Intent("com.termfu.service_execute", new Uri.Builder().scheme("file").path(URL_OPENER_PROGRAM).build());
        intent.setClass(this, TermuxService.class);
        intent.putExtra(TermuxService.EXTRA_ARGUMENTS, new String[]{str});
        startService(intent);
        finish();
    }

    public static void getAllSHFilesAndWriteToNewDirectory(String str, String str2) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile() && file.getName().endsWith(".sh")) {
                    writeToNewDirectory(file.getName(), file.getAbsolutePath(), str2);
                }
            }
            return;
        }
        System.out.println("Directory is empty or does not exist.");
    }

    public static void writeToNewDirectory(String str, String str2, String str3) {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(str3) + str);
            fileWriter.write("su -c " + str2);
            fileWriter.close();
            PrintStream printStream = System.out;
            printStream.println("File " + str + " has been created with command in " + str3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPermissions(String str) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    try {
                        HashSet hashSet = new HashSet();
                        hashSet.add(PosixFilePermission.OWNER_READ);
                        hashSet.add(PosixFilePermission.OWNER_WRITE);
                        hashSet.add(PosixFilePermission.OWNER_EXECUTE);
                        hashSet.add(PosixFilePermission.GROUP_READ);
                        hashSet.add(PosixFilePermission.GROUP_EXECUTE);
                        hashSet.add(PosixFilePermission.OTHERS_READ);
                        hashSet.add(PosixFilePermission.OTHERS_EXECUTE);
                        Files.setPosixFilePermissions(file.toPath(), hashSet);
                        System.out.println("Permissions set for: " + file.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        }
        System.out.println("Directory is empty or does not exist.");
    }
}
