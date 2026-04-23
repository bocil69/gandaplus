package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzv  reason: invalid package */
/* loaded from: classes.dex */
final class zzv extends zzz {
    final /* synthetic */ zzw zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzv(zzw zzwVar, zzab zzabVar, CharSequence charSequence) {
        super(zzabVar, charSequence);
        this.zza = zzwVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzz
    final int zzc(int i) {
        return i + 1;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzz
    final int zzd(int i) {
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzu.zzb(i, length, "index");
        while (i < length) {
            zzw zzwVar = this.zza;
            if (zzwVar.zza.zza(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
