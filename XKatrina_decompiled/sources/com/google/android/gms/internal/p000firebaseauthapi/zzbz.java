package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzbz {
    private final zzws zza;

    private zzbz(zzws zzwsVar) {
        this.zza = zzwsVar;
    }

    public static zzbz zze() {
        return new zzbz(zzwv.zzc());
    }

    public static zzbz zzf(zzby zzbyVar) {
        return new zzbz((zzws) zzbyVar.zzc().zzu());
    }

    private final synchronized int zzg() {
        int zza;
        zza = zzpd.zza();
        while (zzj(zza)) {
            zza = zzpd.zza();
        }
        return zza;
    }

    private final synchronized zzwu zzh(zzwi zzwiVar, zzxo zzxoVar) throws GeneralSecurityException {
        zzwt zzc;
        int zzg = zzg();
        if (zzxoVar == zzxo.UNKNOWN_PREFIX) {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        zzc = zzwu.zzc();
        zzc.zza(zzwiVar);
        zzc.zzb(zzg);
        zzc.zzd(3);
        zzc.zzc(zzxoVar);
        return (zzwu) zzc.zzi();
    }

    private final synchronized zzwu zzi(zzwn zzwnVar) throws GeneralSecurityException {
        return zzh(zzcq.zzb(zzwnVar), zzwnVar.zze());
    }

    private final synchronized boolean zzj(int i) {
        boolean z;
        Iterator it = this.zza.zze().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (((zzwu) it.next()).zza() == i) {
                z = true;
                break;
            }
        }
        return z;
    }

    public final synchronized int zza(zzwn zzwnVar, boolean z) throws GeneralSecurityException {
        zzwu zzi;
        zzws zzwsVar = this.zza;
        zzi = zzi(zzwnVar);
        zzwsVar.zzb(zzi);
        return zzi.zza();
    }

    public final synchronized zzby zzb() throws GeneralSecurityException {
        return zzby.zza((zzwv) this.zza.zzi());
    }

    public final synchronized zzbz zzc(zzbv zzbvVar) throws GeneralSecurityException {
        zza(zzbvVar.zzb(), false);
        return this;
    }

    public final synchronized zzbz zzd(int i) throws GeneralSecurityException {
        for (int i2 = 0; i2 < this.zza.zza(); i2++) {
            zzwu zzd = this.zza.zzd(i2);
            if (zzd.zza() == i) {
                if (zzd.zzk() != 3) {
                    throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + i);
                } else {
                    this.zza.zzc(i);
                }
            }
        }
        throw new GeneralSecurityException("key not found: " + i);
        return this;
    }
}
