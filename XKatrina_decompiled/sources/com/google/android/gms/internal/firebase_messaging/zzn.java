package com.google.android.gms.internal.firebase_messaging;
/* loaded from: classes2.dex */
public final class zzn {
    private static final zzm zzk;
    private static final int zzl;

    /* loaded from: classes2.dex */
    static final class zza extends zzm {
        zza() {
        }

        @Override // com.google.android.gms.internal.firebase_messaging.zzm
        public final void zza(Throwable th, Throwable th2) {
        }
    }

    public static void zza(Throwable th, Throwable th2) {
        zzk.zza(th, th2);
    }

    private static Integer zzb() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0070  */
    static {
        /*
            r2 = 1
            r1 = 0
            java.lang.Integer r0 = zzb()     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L1d
            int r1 = r0.intValue()     // Catch: java.lang.Throwable -> L2e
            r3 = 19
            if (r1 < r3) goto L1d
            com.google.android.gms.internal.firebase_messaging.zzr r1 = new com.google.android.gms.internal.firebase_messaging.zzr     // Catch: java.lang.Throwable -> L2e
            r1.<init>()     // Catch: java.lang.Throwable -> L2e
        L15:
            com.google.android.gms.internal.firebase_messaging.zzn.zzk = r1
            if (r0 != 0) goto L70
            r0 = r2
        L1a:
            com.google.android.gms.internal.firebase_messaging.zzn.zzl = r0
            return
        L1d:
            java.lang.String r1 = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic"
            boolean r1 = java.lang.Boolean.getBoolean(r1)     // Catch: java.lang.Throwable -> L2e
            if (r1 != 0) goto L68
            r1 = r2
        L26:
            if (r1 == 0) goto L6a
            com.google.android.gms.internal.firebase_messaging.zzq r1 = new com.google.android.gms.internal.firebase_messaging.zzq     // Catch: java.lang.Throwable -> L2e
            r1.<init>()     // Catch: java.lang.Throwable -> L2e
            goto L15
        L2e:
            r1 = move-exception
        L2f:
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.Class<com.google.android.gms.internal.firebase_messaging.zzn$zza> r4 = com.google.android.gms.internal.firebase_messaging.zzn.zza.class
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = java.lang.String.valueOf(r4)
            int r5 = r5.length()
            int r5 = r5 + 133
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy "
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r5 = "will be used. The error is: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
            java.io.PrintStream r3 = java.lang.System.err
            r1.printStackTrace(r3)
            com.google.android.gms.internal.firebase_messaging.zzn$zza r1 = new com.google.android.gms.internal.firebase_messaging.zzn$zza
            r1.<init>()
            goto L15
        L68:
            r1 = 0
            goto L26
        L6a:
            com.google.android.gms.internal.firebase_messaging.zzn$zza r1 = new com.google.android.gms.internal.firebase_messaging.zzn$zza     // Catch: java.lang.Throwable -> L2e
            r1.<init>()     // Catch: java.lang.Throwable -> L2e
            goto L15
        L70:
            int r0 = r0.intValue()
            goto L1a
        L75:
            r0 = move-exception
            r7 = r0
            r0 = r1
            r1 = r7
            goto L2f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_messaging.zzn.<clinit>():void");
    }
}
