package com.google.firebase.messaging;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_messaging.zzk;
import com.google.android.gms.internal.firebase_messaging.zzn;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.io.Closeable;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
final class zzd implements Closeable {
    private final URL url;
    @Nullable
    private Task<Bitmap> zzea;
    @Nullable
    private volatile InputStream zzeb;

    @Nullable
    public static zzd zzo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new zzd(new URL(str));
        } catch (MalformedURLException e) {
            String valueOf = String.valueOf(str);
            Log.w("FirebaseMessaging", valueOf.length() != 0 ? "Not downloading image, bad URL: ".concat(valueOf) : new String("Not downloading image, bad URL: "));
            return null;
        }
    }

    private zzd(URL url) {
        this.url = url;
    }

    public final void zza(Executor executor) {
        this.zzea = Tasks.call(executor, new Callable(this) { // from class: com.google.firebase.messaging.zze
            private final zzd zzed;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzed = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzed.zzat();
            }
        });
    }

    public final Task<Bitmap> getTask() {
        return (Task) Preconditions.checkNotNull(this.zzea);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public final android.graphics.Bitmap zzat() throws java.io.IOException {
        /*
            r9 = this;
            r2 = 0
            java.lang.String r0 = "FirebaseMessaging"
            java.net.URL r1 = r9.url
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r3 = r3 + 22
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Starting download of: "
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r0, r1)
            java.net.URL r0 = r9.url     // Catch: java.io.IOException -> L85
            java.net.URLConnection r0 = r0.openConnection()     // Catch: java.io.IOException -> L85
            java.io.InputStream r3 = r0.getInputStream()     // Catch: java.io.IOException -> L85
            r0 = 1048576(0x100000, double:5.180654E-318)
            java.io.InputStream r4 = com.google.android.gms.internal.firebase_messaging.zzj.zza(r3, r0)     // Catch: java.lang.Throwable -> L7a
            r9.zzeb = r3     // Catch: java.lang.Throwable -> L70
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r4)     // Catch: java.lang.Throwable -> L70
            if (r0 != 0) goto Laf
            java.net.URL r0 = r9.url     // Catch: java.lang.Throwable -> L70
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: java.lang.Throwable -> L70
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch: java.lang.Throwable -> L70
            int r1 = r1.length()     // Catch: java.lang.Throwable -> L70
            int r1 = r1 + 24
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L70
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L70
            java.lang.String r1 = "Failed to decode image: "
            java.lang.StringBuilder r1 = r5.append(r1)     // Catch: java.lang.Throwable -> L70
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch: java.lang.Throwable -> L70
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L70
            java.lang.String r1 = "FirebaseMessaging"
            android.util.Log.w(r1, r0)     // Catch: java.lang.Throwable -> L70
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L70
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L70
            throw r1     // Catch: java.lang.Throwable -> L70
        L70:
            r0 = move-exception
            throw r0     // Catch: java.lang.Throwable -> L72
        L72:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L76:
            zza(r1, r4)     // Catch: java.lang.Throwable -> L7a
            throw r0     // Catch: java.lang.Throwable -> L7a
        L7a:
            r0 = move-exception
            throw r0     // Catch: java.lang.Throwable -> L7c
        L7c:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L7f:
            if (r3 == 0) goto L84
            zza(r2, r3)     // Catch: java.io.IOException -> L85
        L84:
            throw r0     // Catch: java.io.IOException -> L85
        L85:
            r0 = move-exception
            java.lang.String r1 = "FirebaseMessaging"
            java.net.URL r2 = r9.url
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r3 = java.lang.String.valueOf(r2)
            int r3 = r3.length()
            int r3 = r3 + 26
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Failed to download image: "
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            android.util.Log.w(r1, r2)
            throw r0
        Laf:
            java.lang.String r1 = "FirebaseMessaging"
            r5 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r5)     // Catch: java.lang.Throwable -> L70
            if (r1 == 0) goto Le0
            java.lang.String r1 = "FirebaseMessaging"
            java.net.URL r5 = r9.url     // Catch: java.lang.Throwable -> L70
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L70
            java.lang.String r6 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L70
            int r6 = r6.length()     // Catch: java.lang.Throwable -> L70
            int r6 = r6 + 31
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L70
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L70
            java.lang.String r6 = "Successfully downloaded image: "
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch: java.lang.Throwable -> L70
            java.lang.StringBuilder r5 = r6.append(r5)     // Catch: java.lang.Throwable -> L70
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L70
            android.util.Log.d(r1, r5)     // Catch: java.lang.Throwable -> L70
        Le0:
            r1 = 0
            zza(r1, r4)     // Catch: java.lang.Throwable -> L7a
            if (r3 == 0) goto Lea
            r1 = 0
            zza(r1, r3)     // Catch: java.io.IOException -> L85
        Lea:
            return r0
        Leb:
            r0 = move-exception
            goto L7f
        Led:
            r0 = move-exception
            r1 = r2
            goto L76
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zzd.zzat():android.graphics.Bitmap");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        zzk.zza(this.zzeb);
    }

    private static /* synthetic */ void zza(Throwable th, InputStream inputStream) {
        if (th == null) {
            inputStream.close();
            return;
        }
        try {
            inputStream.close();
        } catch (Throwable th2) {
            zzn.zza(th, th2);
        }
    }
}
