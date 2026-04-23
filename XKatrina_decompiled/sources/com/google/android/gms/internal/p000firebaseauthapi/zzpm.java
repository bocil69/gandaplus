package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpm  reason: invalid package */
/* loaded from: classes.dex */
public final class zzpm extends zzng {
    private static final zzof zza = zzof.zzb(new zzod() { // from class: com.google.android.gms.internal.firebase-auth-api.zzpi
        @Override // com.google.android.gms.internal.p000firebaseauthapi.zzod
        public final Object zza(zzbn zzbnVar) {
            return new zzre((zzph) zzbnVar);
        }
    }, zzph.class, zzpx.class);
    private static final zzof zzb = zzof.zzb(new zzod() { // from class: com.google.android.gms.internal.firebase-auth-api.zzpj
        @Override // com.google.android.gms.internal.p000firebaseauthapi.zzod
        public final Object zza(zzbn zzbnVar) {
            return zzzj.zzb((zzph) zzbnVar);
        }
    }, zzph.class, zzcd.class);

    zzpm() {
        super(zzrz.class, new zzpk(zzcd.class));
    }

    public static void zzm(boolean z) throws GeneralSecurityException {
        zzcq.zzg(new zzpm(), true);
        int i = zzpw.zza;
        zzpw.zzc(zznt.zzc());
        zznq.zza().zze(zza);
        zznq.zza().zze(zzb);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzn(zzsf zzsfVar) throws GeneralSecurityException {
        if (zzsfVar.zza() < 10) {
            throw new GeneralSecurityException("tag size too short");
        }
        if (zzsfVar.zza() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzo(int i) throws GeneralSecurityException {
        if (i != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zznf zza() {
        return new zzpl(this, zzsc.class);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zzrz.zzd(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zzrz zzrzVar = (zzrz) zzalpVar;
        zzzl.zzc(zzrzVar.zza(), 0);
        zzo(zzrzVar.zzf().zzd());
        zzn(zzrzVar.zze());
    }
}
