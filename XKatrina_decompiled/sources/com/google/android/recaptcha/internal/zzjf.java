package com.google.android.recaptcha.internal;

import java.io.IOException;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
abstract class zzjf {
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
    public abstract void zzk(Object obj, int i, zzez zzezVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzl(Object obj, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzm(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzn(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzo(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzp(Object obj, zzjx zzjxVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzq(Object obj, zzjx zzjxVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzs(zzik zzikVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzr(Object obj, zzik zzikVar) throws IOException {
        int zzd = zzikVar.zzd();
        int i = zzd >>> 3;
        int i2 = zzd & 7;
        if (i2 == 0) {
            zzl(obj, i, zzikVar.zzl());
            return true;
        } else if (i2 == 1) {
            zzi(obj, i, zzikVar.zzk());
            return true;
        } else if (i2 == 2) {
            zzk(obj, i, zzikVar.zzp());
            return true;
        } else if (i2 != 3) {
            if (i2 != 4) {
                if (i2 == 5) {
                    zzh(obj, i, zzikVar.zzf());
                    return true;
                }
                throw zzgy.zza();
            }
            return false;
        } else {
            Object zzf = zzf();
            int i3 = i << 3;
            while (zzikVar.zzc() != Integer.MAX_VALUE && zzr(zzf, zzikVar)) {
            }
            if ((4 | i3) != zzikVar.zzd()) {
                throw zzgy.zzb();
            }
            zzg(zzf);
            zzj(obj, i, zzf);
            return true;
        }
    }
}
