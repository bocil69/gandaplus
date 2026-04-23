package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqp  reason: invalid package */
/* loaded from: classes.dex */
public final class zzqp extends zzqs {
    private final int zza;
    private final int zzb;
    private final zzqn zzc;
    private final zzqm zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzqp(int i, int i2, zzqn zzqnVar, zzqm zzqmVar, zzqo zzqoVar) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = zzqnVar;
        this.zzd = zzqmVar;
    }

    public static zzql zze() {
        return new zzql(null);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzqp) {
            zzqp zzqpVar = (zzqp) obj;
            return zzqpVar.zza == this.zza && zzqpVar.zzd() == zzd() && zzqpVar.zzc == this.zzc && zzqpVar.zzd == this.zzd;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzqp.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public final String toString() {
        zzqm zzqmVar = this.zzd;
        String valueOf = String.valueOf(this.zzc);
        String valueOf2 = String.valueOf(zzqmVar);
        return "HMAC Parameters (variant: " + valueOf + ", hashType: " + valueOf2 + ", " + this.zzb + "-byte tags, and " + this.zza + "-byte key)";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        return this.zzc != zzqn.zzd;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zza;
    }

    public final int zzd() {
        zzqn zzqnVar = this.zzc;
        if (zzqnVar == zzqn.zzd) {
            return this.zzb;
        }
        if (zzqnVar == zzqn.zza || zzqnVar == zzqn.zzb || zzqnVar == zzqn.zzc) {
            return this.zzb + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public final zzqm zzf() {
        return this.zzd;
    }

    public final zzqn zzg() {
        return this.zzc;
    }
}
