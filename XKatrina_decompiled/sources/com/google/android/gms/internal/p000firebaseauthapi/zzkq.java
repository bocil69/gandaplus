package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkq  reason: invalid package */
/* loaded from: classes.dex */
public final class zzkq extends zzlg {
    private final zzkn zza;
    private final zzkm zzb;
    private final zzkh zzc;
    private final zzko zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzkq(zzkn zzknVar, zzkm zzkmVar, zzkh zzkhVar, zzko zzkoVar, zzkp zzkpVar) {
        this.zza = zzknVar;
        this.zzb = zzkmVar;
        this.zzc = zzkhVar;
        this.zzd = zzkoVar;
    }

    public static zzkl zzc() {
        return new zzkl(null);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzkq) {
            zzkq zzkqVar = (zzkq) obj;
            return this.zza == zzkqVar.zza && this.zzb == zzkqVar.zzb && this.zzc == zzkqVar.zzc && this.zzd == zzkqVar.zzd;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzkq.class, this.zza, this.zzb, this.zzc, this.zzd});
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        throw null;
    }

    public final zzkh zzb() {
        return this.zzc;
    }

    public final zzkm zzd() {
        return this.zzb;
    }

    public final zzkn zze() {
        return this.zza;
    }

    public final zzko zzf() {
        return this.zzd;
    }
}
