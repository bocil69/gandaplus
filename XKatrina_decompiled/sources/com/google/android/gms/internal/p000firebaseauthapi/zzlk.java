package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlk  reason: invalid package */
/* loaded from: classes.dex */
final class zzlk implements zzyh {
    private final String zza;
    private final int zzb;
    private zztg zzc;
    private zzsi zzd;
    private int zze;
    private zzts zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlk(zzwn zzwnVar) throws GeneralSecurityException {
        String zzg = zzwnVar.zzg();
        this.zza = zzg;
        if (zzg.equals(zzcu.zzb)) {
            try {
                zztj zze = zztj.zze(zzwnVar.zzf(), zzajx.zza());
                this.zzc = zztg.zzd(zzcq.zzb(zzwnVar).zze(), zzajx.zza());
                this.zzb = zze.zza();
            } catch (zzaks e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (zzg.equals(zzcu.zza)) {
            try {
                zzsl zzc = zzsl.zzc(zzwnVar.zzf(), zzajx.zza());
                this.zzd = zzsi.zzd(zzcq.zzb(zzwnVar).zze(), zzajx.zza());
                this.zze = zzc.zzd().zza();
                this.zzb = this.zze + zzc.zze().zza();
            } catch (zzaks e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        } else if (zzg.equals(zzjc.zza)) {
            try {
                zztv zze2 = zztv.zze(zzwnVar.zzf(), zzajx.zza());
                this.zzf = zzts.zzd(zzcq.zzb(zzwnVar).zze(), zzajx.zza());
                this.zzb = zze2.zza();
            } catch (zzaks e3) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e3);
            }
        } else {
            throw new GeneralSecurityException("unsupported AEAD DEM key type: ".concat(String.valueOf(zzg)));
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzyh
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzyh
    public final zzmg zzb(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzb) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if (this.zza.equals(zzcu.zzb)) {
            zztf zzb = zztg.zzb();
            zzb.zzh(this.zzc);
            zzb.zza(zzajf.zzn(bArr, 0, this.zzb));
            return new zzmg((zzbd) zzcq.zze(this.zza, ((zztg) zzb.zzi()).zzo(), zzbd.class));
        } else if (this.zza.equals(zzcu.zza)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zze);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zze, this.zzb);
            zzsn zzb2 = zzso.zzb();
            zzb2.zzh(this.zzd.zze());
            zzajf zzajfVar = zzajf.zzb;
            zzb2.zza(zzajf.zzn(copyOfRange, 0, copyOfRange.length));
            zzve zzb3 = zzvf.zzb();
            zzb3.zzh(this.zzd.zzf());
            zzb3.zza(zzajf.zzn(copyOfRange2, 0, copyOfRange2.length));
            zzsh zzb4 = zzsi.zzb();
            zzb4.zzc(this.zzd.zza());
            zzb4.zza((zzso) zzb2.zzi());
            zzb4.zzb((zzvf) zzb3.zzi());
            return new zzmg((zzbd) zzcq.zze(this.zza, ((zzsi) zzb4.zzi()).zzo(), zzbd.class));
        } else if (this.zza.equals(zzjc.zza)) {
            zztr zzb5 = zzts.zzb();
            zzb5.zzh(this.zzf);
            zzb5.zza(zzajf.zzn(bArr, 0, this.zzb));
            return new zzmg((zzbj) zzcq.zze(this.zza, ((zzts) zzb5.zzi()).zzo(), zzbj.class));
        } else {
            throw new GeneralSecurityException("unknown DEM key type");
        }
    }
}
