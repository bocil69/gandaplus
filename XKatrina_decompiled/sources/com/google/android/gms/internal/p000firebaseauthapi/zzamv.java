package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamv  reason: invalid package */
/* loaded from: classes.dex */
abstract class zzamv {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzb(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zzc(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zzd(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zze(Object obj, Object obj2);

    abstract Object zzf();

    abstract Object zzg(Object obj);

    abstract void zzh(Object obj, int i, int i2);

    abstract void zzi(Object obj, int i, long j);

    abstract void zzj(Object obj, int i, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzk(Object obj, int i, zzajf zzajfVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzl(Object obj, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzm(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzn(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzo(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzq(zzama zzamaVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzr(Object obj, zzajt zzajtVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzp(Object obj, zzama zzamaVar) throws IOException {
        int zzd = zzamaVar.zzd();
        int i = zzd >>> 3;
        int i2 = zzd & 7;
        if (i2 == 0) {
            zzl(obj, i, zzamaVar.zzl());
            return true;
        } else if (i2 == 1) {
            zzi(obj, i, zzamaVar.zzk());
            return true;
        } else if (i2 == 2) {
            zzk(obj, i, zzamaVar.zzp());
            return true;
        } else if (i2 != 3) {
            if (i2 != 4) {
                if (i2 == 5) {
                    zzh(obj, i, zzamaVar.zzf());
                    return true;
                }
                throw zzaks.zza();
            }
            return false;
        } else {
            Object zzf = zzf();
            int i3 = i << 3;
            while (zzamaVar.zzc() != Integer.MAX_VALUE && zzp(zzf, zzamaVar)) {
            }
            if ((4 | i3) != zzamaVar.zzd()) {
                throw zzaks.zzb();
            }
            zzg(zzf);
            zzj(obj, i, zzf);
            return true;
        }
    }
}
