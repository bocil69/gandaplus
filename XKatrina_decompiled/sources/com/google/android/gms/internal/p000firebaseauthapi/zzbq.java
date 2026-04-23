package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbq  reason: invalid package */
/* loaded from: classes.dex */
final class zzbq implements zzbs {
    final /* synthetic */ zzng zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(zzng zzngVar) {
        this.zza = zzngVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    public final zzbo zza(Class cls) throws GeneralSecurityException {
        try {
            return new zzbp(this.zza, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    public final zzbo zzb() {
        zzng zzngVar = this.zza;
        return new zzbp(zzngVar, zzngVar.zzi());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    public final Class zzc() {
        return this.zza.getClass();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    @Nullable
    public final Class zzd() {
        return null;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    public final Set zze() {
        return this.zza.zzl();
    }
}
