package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalt  reason: invalid package */
/* loaded from: classes.dex */
final class zzalt implements zzamb {
    private final zzalp zza;
    private final zzamv zzb;
    private final boolean zzc;
    private final zzajy zzd;

    private zzalt(zzamv zzamvVar, zzajy zzajyVar, zzalp zzalpVar) {
        this.zzb = zzamvVar;
        this.zzc = zzajyVar.zzh(zzalpVar);
        this.zzd = zzajyVar;
        this.zza = zzalpVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzalt zzc(zzamv zzamvVar, zzajy zzajyVar, zzalp zzalpVar) {
        return new zzalt(zzamvVar, zzajyVar, zzalpVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final int zza(Object obj) {
        zzamv zzamvVar = this.zzb;
        int zzb = zzamvVar.zzb(zzamvVar.zzd(obj));
        if (this.zzc) {
            this.zzd.zza(obj);
            throw null;
        }
        return zzb;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzd(obj).hashCode();
        if (this.zzc) {
            this.zzd.zza(obj);
            throw null;
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final Object zze() {
        zzalp zzalpVar = this.zza;
        if (zzalpVar instanceof zzakk) {
            return ((zzakk) zzalpVar).zzw();
        }
        return zzalpVar.zzC().zzk();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final void zzf(Object obj) {
        this.zzb.zzm(obj);
        this.zzd.zze(obj);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final void zzg(Object obj, Object obj2) {
        zzamd.zzq(this.zzb, obj, obj2);
        if (this.zzc) {
            this.zzd.zza(obj2);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final void zzh(Object obj, zzama zzamaVar, zzajx zzajxVar) throws IOException {
        boolean zzO;
        zzamv zzamvVar = this.zzb;
        Object zzc = zzamvVar.zzc(obj);
        zzajy zzajyVar = this.zzd;
        zzakc zzb = zzajyVar.zzb(obj);
        while (zzamaVar.zzc() != Integer.MAX_VALUE) {
            try {
                int zzd = zzamaVar.zzd();
                if (zzd != 11) {
                    if ((zzd & 7) == 2) {
                        Object zzc2 = zzajyVar.zzc(zzajxVar, this.zza, zzd >>> 3);
                        if (zzc2 != null) {
                            zzajyVar.zzf(zzamaVar, zzc2, zzajxVar, zzb);
                        } else {
                            zzO = zzamvVar.zzp(zzc, zzamaVar);
                        }
                    } else {
                        zzO = zzamaVar.zzO();
                    }
                    if (!zzO) {
                        return;
                    }
                } else {
                    int i = 0;
                    Object obj2 = null;
                    zzajf zzajfVar = null;
                    while (zzamaVar.zzc() != Integer.MAX_VALUE) {
                        int zzd2 = zzamaVar.zzd();
                        if (zzd2 == 16) {
                            i = zzamaVar.zzj();
                            obj2 = zzajyVar.zzc(zzajxVar, this.zza, i);
                        } else if (zzd2 == 26) {
                            if (obj2 != null) {
                                zzajyVar.zzf(zzamaVar, obj2, zzajxVar, zzb);
                            } else {
                                zzajfVar = zzamaVar.zzp();
                            }
                        } else if (!zzamaVar.zzO()) {
                            break;
                        }
                    }
                    if (zzamaVar.zzd() != 12) {
                        throw zzaks.zzb();
                    } else if (zzajfVar != null) {
                        if (obj2 != null) {
                            zzajyVar.zzg(zzajfVar, obj2, zzajxVar, zzb);
                        } else {
                            zzamvVar.zzk(zzc, i, zzajfVar);
                        }
                    }
                }
            } finally {
                zzamvVar.zzn(obj, zzc);
            }
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzais zzaisVar) throws IOException {
        zzakk zzakkVar = (zzakk) obj;
        if (zzakkVar.zzc == zzamw.zzc()) {
            zzakkVar.zzc = zzamw.zzf();
        }
        zzakh zzakhVar = (zzakh) obj;
        throw null;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final boolean zzj(Object obj, Object obj2) {
        zzamv zzamvVar = this.zzb;
        if (zzamvVar.zzd(obj).equals(zzamvVar.zzd(obj2))) {
            if (this.zzc) {
                this.zzd.zza(obj);
                this.zzd.zza(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final boolean zzk(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final void zzm(Object obj, zzajt zzajtVar) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }
}
