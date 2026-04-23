package com.google.android.recaptcha.internal;

import java.io.IOException;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzjh extends zzjf {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* synthetic */ int zza(Object obj) {
        return ((zzjg) obj).zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* synthetic */ int zzb(Object obj) {
        return ((zzjg) obj).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* bridge */ /* synthetic */ Object zzc(Object obj) {
        zzgo zzgoVar = (zzgo) obj;
        zzjg zzjgVar = zzgoVar.zzc;
        if (zzjgVar == zzjg.zzc()) {
            zzjg zzf = zzjg.zzf();
            zzgoVar.zzc = zzf;
            return zzf;
        }
        return zzjgVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* synthetic */ Object zzd(Object obj) {
        return ((zzgo) obj).zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* bridge */ /* synthetic */ Object zze(Object obj, Object obj2) {
        if (zzjg.zzc().equals(obj2)) {
            return obj;
        }
        if (zzjg.zzc().equals(obj)) {
            return zzjg.zze((zzjg) obj, (zzjg) obj2);
        }
        ((zzjg) obj).zzd((zzjg) obj2);
        return obj;
    }

    @Override // com.google.android.recaptcha.internal.zzjf
    final /* synthetic */ Object zzf() {
        return zzjg.zzf();
    }

    @Override // com.google.android.recaptcha.internal.zzjf
    final /* synthetic */ Object zzg(Object obj) {
        ((zzjg) obj).zzh();
        return obj;
    }

    @Override // com.google.android.recaptcha.internal.zzjf
    final /* bridge */ /* synthetic */ void zzh(Object obj, int i, int i2) {
        ((zzjg) obj).zzj((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.recaptcha.internal.zzjf
    final /* bridge */ /* synthetic */ void zzi(Object obj, int i, long j) {
        ((zzjg) obj).zzj((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.recaptcha.internal.zzjf
    final /* bridge */ /* synthetic */ void zzj(Object obj, int i, Object obj2) {
        ((zzjg) obj).zzj((i << 3) | 3, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* bridge */ /* synthetic */ void zzk(Object obj, int i, zzez zzezVar) {
        ((zzjg) obj).zzj((i << 3) | 2, zzezVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* bridge */ /* synthetic */ void zzl(Object obj, int i, long j) {
        ((zzjg) obj).zzj(i << 3, Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final void zzm(Object obj) {
        ((zzgo) obj).zzc.zzh();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* synthetic */ void zzn(Object obj, Object obj2) {
        ((zzgo) obj).zzc = (zzjg) obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* synthetic */ void zzo(Object obj, Object obj2) {
        ((zzgo) obj).zzc = (zzjg) obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* synthetic */ void zzp(Object obj, zzjx zzjxVar) throws IOException {
        ((zzjg) obj).zzk(zzjxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final /* synthetic */ void zzq(Object obj, zzjx zzjxVar) throws IOException {
        ((zzjg) obj).zzl(zzjxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzjf
    public final boolean zzs(zzik zzikVar) {
        return false;
    }
}
