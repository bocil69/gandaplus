package com.google.android.recaptcha.internal;

import java.util.List;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzhk extends zzhm {
    private zzhk() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhk(zzhj zzhjVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzhm
    public final List zza(Object obj, long j) {
        zzgv zzgvVar = (zzgv) zzjp.zzf(obj, j);
        if (zzgvVar.zzc()) {
            return zzgvVar;
        }
        int size = zzgvVar.size();
        zzgv zzd = zzgvVar.zzd(size == 0 ? 10 : size + size);
        zzjp.zzs(obj, j, zzd);
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzhm
    public final void zzb(Object obj, long j) {
        ((zzgv) zzjp.zzf(obj, j)).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzhm
    public final void zzc(Object obj, Object obj2, long j) {
        zzgv zzgvVar = (zzgv) zzjp.zzf(obj, j);
        zzgv zzgvVar2 = (zzgv) zzjp.zzf(obj2, j);
        int size = zzgvVar.size();
        int size2 = zzgvVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzgvVar.zzc()) {
                zzgvVar = zzgvVar.zzd(size2 + size);
            }
            zzgvVar.addAll(zzgvVar2);
        }
        if (size > 0) {
            zzgvVar2 = zzgvVar;
        }
        zzjp.zzs(obj, j, zzgvVar2);
    }
}
