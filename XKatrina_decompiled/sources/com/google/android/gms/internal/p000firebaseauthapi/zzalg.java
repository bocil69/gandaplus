package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalg  reason: invalid package */
/* loaded from: classes.dex */
final class zzalg implements zzaln {
    private final zzaln[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzalg(zzaln... zzalnVarArr) {
        this.zza = zzalnVarArr;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaln
    public final zzalm zzb(Class cls) {
        for (int i = 0; i < 2; i++) {
            zzaln zzalnVar = this.zza[i];
            if (zzalnVar.zzc(cls)) {
                return zzalnVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaln
    public final boolean zzc(Class cls) {
        for (int i = 0; i < 2; i++) {
            if (this.zza[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
