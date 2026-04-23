package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgw  reason: invalid package */
/* loaded from: classes.dex */
public final class zzgw {
    @Nullable
    private String zza;
    @Nullable
    private zzgx zzb;
    @Nullable
    private zzcx zzc;

    private zzgw() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgw(zzgv zzgvVar) {
    }

    public final zzgw zza(zzcx zzcxVar) {
        this.zzc = zzcxVar;
        return this;
    }

    public final zzgw zzb(zzgx zzgxVar) {
        this.zzb = zzgxVar;
        return this;
    }

    public final zzgw zzc(String str) {
        this.zza = str;
        return this;
    }

    public final zzgz zzd() throws GeneralSecurityException {
        if (this.zza != null) {
            zzgx zzgxVar = this.zzb;
            if (zzgxVar != null) {
                zzcx zzcxVar = this.zzc;
                if (zzcxVar != null) {
                    if (zzcxVar.zza()) {
                        throw new GeneralSecurityException("dekParametersForNewKeys must note have ID Requirements");
                    }
                    if ((!zzgxVar.equals(zzgx.zza) || !(zzcxVar instanceof zzey)) && ((!zzgxVar.equals(zzgx.zzc) || !(zzcxVar instanceof zzga)) && ((!zzgxVar.equals(zzgx.zzb) || !(zzcxVar instanceof zzhr)) && ((!zzgxVar.equals(zzgx.zzd) || !(zzcxVar instanceof zzdn)) && ((!zzgxVar.equals(zzgx.zze) || !(zzcxVar instanceof zzeh)) && (!zzgxVar.equals(zzgx.zzf) || !(zzcxVar instanceof zzfp))))))) {
                        String zzgxVar2 = this.zzb.toString();
                        String valueOf = String.valueOf(this.zzc);
                        throw new GeneralSecurityException("Cannot use parsing strategy " + zzgxVar2 + " when new keys are picked according to " + valueOf + ".");
                    }
                    return new zzgz(this.zza, this.zzb, this.zzc, null);
                }
                throw new GeneralSecurityException("dekParametersForNewKeys must be set");
            }
            throw new GeneralSecurityException("dekParsingStrategy must be set");
        }
        throw new GeneralSecurityException("kekUri must be set");
    }
}
