package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzqj extends zzng {
    private static final zzof zza = zzof.zzb(new zzod() { // from class: com.google.android.gms.internal.firebase-auth-api.zzqf
        @Override // com.google.android.gms.internal.p000firebaseauthapi.zzod
        public final Object zza(zzbn zzbnVar) {
            return new zzrf((zzqe) zzbnVar);
        }
    }, zzqe.class, zzpx.class);
    private static final zzof zzb = zzof.zzb(new zzod() { // from class: com.google.android.gms.internal.firebase-auth-api.zzqg
        @Override // com.google.android.gms.internal.p000firebaseauthapi.zzod
        public final Object zza(zzbn zzbnVar) {
            return zzzj.zzc((zzqe) zzbnVar);
        }
    }, zzqe.class, zzcd.class);

    public zzqj() {
        super(zzvf.class, new zzqh(zzcd.class));
    }

    public static void zzh(boolean z) throws GeneralSecurityException {
        zzcq.zzg(new zzqj(), true);
        int i = zzrk.zza;
        zzrk.zzd(zznt.zzc());
        zznq.zza().zze(zza);
        zznq.zza().zze(zzb);
    }

    public static final void zzm(zzvf zzvfVar) throws GeneralSecurityException {
        zzzl.zzc(zzvfVar.zza(), 0);
        if (zzvfVar.zzg().zzd() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zzn(zzvfVar.zzf());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzn(zzvl zzvlVar) throws GeneralSecurityException {
        if (zzvlVar.zza() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        zzvc zzvcVar = zzvc.UNKNOWN_HASH;
        int ordinal = zzvlVar.zzb().ordinal();
        if (ordinal == 1) {
            if (zzvlVar.zza() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (ordinal == 2) {
            if (zzvlVar.zza() > 48) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (ordinal == 3) {
            if (zzvlVar.zza() > 32) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (ordinal == 4) {
            if (zzvlVar.zza() > 64) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (ordinal == 5) {
            if (zzvlVar.zza() > 28) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            throw new GeneralSecurityException("unknown hash type");
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zznf zza() {
        return new zzqi(this, zzvi.class);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zzvf.zze(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zzm((zzvf) zzalpVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final int zzf() {
        return 2;
    }
}
