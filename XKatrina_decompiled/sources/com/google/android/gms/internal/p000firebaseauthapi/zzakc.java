package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakc  reason: invalid package */
/* loaded from: classes.dex */
final class zzakc {
    private static final zzakc zzb = new zzakc(true);
    final zzamo zza = new zzame(16);
    private boolean zzc;
    private boolean zzd;

    private zzakc() {
    }

    public static zzakc zza() {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void zzd(com.google.android.gms.internal.p000firebaseauthapi.zzakb r4, java.lang.Object r5) {
        /*
            com.google.android.gms.internal.firebase-auth-api.zzanl r0 = r4.zzb()
            byte[] r1 = com.google.android.gms.internal.p000firebaseauthapi.zzakq.zzd
            java.util.Objects.requireNonNull(r5)
            com.google.android.gms.internal.firebase-auth-api.zzanl r1 = com.google.android.gms.internal.p000firebaseauthapi.zzanl.DOUBLE
            com.google.android.gms.internal.firebase-auth-api.zzanm r1 = com.google.android.gms.internal.p000firebaseauthapi.zzanm.INT
            com.google.android.gms.internal.firebase-auth-api.zzanm r0 = r0.zza()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L43;
                case 1: goto L40;
                case 2: goto L3d;
                case 3: goto L3a;
                case 4: goto L37;
                case 5: goto L34;
                case 6: goto L2b;
                case 7: goto L22;
                case 8: goto L19;
                default: goto L18;
            }
        L18:
            goto L48
        L19:
            boolean r0 = r5 instanceof com.google.android.gms.internal.p000firebaseauthapi.zzalp
            if (r0 != 0) goto L47
            boolean r0 = r5 instanceof com.google.android.gms.internal.p000firebaseauthapi.zzaku
            if (r0 == 0) goto L48
            goto L47
        L22:
            boolean r0 = r5 instanceof java.lang.Integer
            if (r0 != 0) goto L47
            boolean r0 = r5 instanceof com.google.android.gms.internal.p000firebaseauthapi.zzakm
            if (r0 == 0) goto L48
            goto L47
        L2b:
            boolean r0 = r5 instanceof com.google.android.gms.internal.p000firebaseauthapi.zzajf
            if (r0 != 0) goto L47
            boolean r0 = r5 instanceof byte[]
            if (r0 == 0) goto L48
            goto L47
        L34:
            boolean r0 = r5 instanceof java.lang.String
            goto L45
        L37:
            boolean r0 = r5 instanceof java.lang.Boolean
            goto L45
        L3a:
            boolean r0 = r5 instanceof java.lang.Double
            goto L45
        L3d:
            boolean r0 = r5 instanceof java.lang.Float
            goto L45
        L40:
            boolean r0 = r5 instanceof java.lang.Long
            goto L45
        L43:
            boolean r0 = r5 instanceof java.lang.Integer
        L45:
            if (r0 == 0) goto L48
        L47:
            return
        L48:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            int r3 = r4.zza()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            r2 = 1
            com.google.android.gms.internal.firebase-auth-api.zzanl r4 = r4.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzanm r4 = r4.zza()
            r1[r2] = r4
            r4 = 2
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getName()
            r1[r4] = r5
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r4 = java.lang.String.format(r4, r1)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p000firebaseauthapi.zzakc.zzd(com.google.android.gms.internal.firebase-auth-api.zzakb, java.lang.Object):void");
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzakc zzakcVar = new zzakc();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzakcVar.zzc((zzakb) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzakcVar.zzc((zzakb) entry.getKey(), entry.getValue());
        }
        zzakcVar.zzd = this.zzd;
        return zzakcVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzakc) {
            return this.zza.equals(((zzakc) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzc) {
            return;
        }
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            if (zzg.getValue() instanceof zzakk) {
                ((zzakk) zzg.getValue()).zzF();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzc(zzakb zzakbVar, Object obj) {
        if (zzakbVar.zzc()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzakbVar, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            zzd(zzakbVar, obj);
        }
        if (obj instanceof zzaku) {
            this.zzd = true;
        }
        this.zza.put(zzakbVar, obj);
    }

    private zzakc(boolean z) {
        zzb();
        zzb();
    }
}
