package com.stericson.RootTools.internal;

import android.content.Context;
import android.util.Log;
import com.stericson.RootShell.execution.Command;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class Installer {
    static final String BOGUS_FILE_NAME = "bogus";
    static final String LOG_TAG = "RootTools::Installer";
    Context context;
    String filesPath;

    public Installer(Context context) throws IOException {
        this.context = context;
        this.filesPath = context.getFilesDir().getCanonicalPath();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0179 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0170 A[Catch: all -> 0x0169, TRY_LEAVE, TryCatch #5 {all -> 0x0169, blocks: (B:14:0x006f, B:65:0x016c, B:67:0x0170), top: B:88:0x0058 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0184 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean installBinary(int r23, java.lang.String r24, java.lang.String r25) {
        /*
            Method dump skipped, instructions count: 397
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stericson.RootTools.internal.Installer.installBinary(int, java.lang.String, java.lang.String):boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isBinaryInstalled(String str) {
        return new File(String.valueOf(this.filesPath) + File.separator + str).exists();
    }

    protected String getFileSignature(File file) {
        try {
            return getStreamSignature(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            Log.e(LOG_TAG, e.toString());
            return "";
        }
    }

    protected String getStreamSignature(InputStream inputStream) {
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                do {
                } while (-1 != new DigestInputStream(inputStream, messageDigest).read(new byte[4096]));
                byte[] digest = messageDigest.digest();
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b : digest) {
                    stringBuffer.append(Integer.toHexString(b & 255));
                }
                String stringBuffer2 = stringBuffer.toString();
                try {
                    inputStream.close();
                    return stringBuffer2;
                } catch (IOException unused) {
                    return stringBuffer2;
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, e.toString());
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                    return "";
                }
            } catch (NoSuchAlgorithmException e2) {
                Log.e(LOG_TAG, e2.toString());
                inputStream.close();
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException unused3) {
            }
            throw th;
        }
    }

    private void commandWait(Command command) {
        synchronized (command) {
            try {
                if (!command.isFinished()) {
                    command.wait(2000L);
                }
            } catch (InterruptedException e) {
                Log.e(LOG_TAG, e.toString());
            }
        }
    }
}
