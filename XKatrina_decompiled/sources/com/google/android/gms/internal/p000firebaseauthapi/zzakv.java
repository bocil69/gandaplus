package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakv  reason: invalid package */
/* loaded from: classes.dex */
public class zzakv {
    private static final zzajx zzb = zzajx.zza;
    protected volatile zzalp zza;
    private volatile zzajf zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzakv) {
            zzakv zzakvVar = (zzakv) obj;
            zzalp zzalpVar = this.zza;
            zzalp zzalpVar2 = zzakvVar.zza;
            if (zzalpVar == null && zzalpVar2 == null) {
                return zzb().equals(zzakvVar.zzb());
            }
            if (zzalpVar == null || zzalpVar2 == null) {
                if (zzalpVar != null) {
                    zzakvVar.zzc(zzalpVar.zzM());
                    return zzalpVar.equals(zzakvVar.zza);
                }
                zzc(zzalpVar2.zzM());
                return this.zza.equals(zzalpVar2);
            }
            return zzalpVar.equals(zzalpVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzajc) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzs();
        }
        return 0;
    }

    public final zzajf zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzajf.zzb;
            } else {
                this.zzc = this.zza.zzo();
            }
            return this.zzc;
        }
    }

    protected final void zzc(zzalp zzalpVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzalpVar;
                    this.zzc = zzajf.zzb;
                } catch (zzaks unused) {
                    this.zza = zzalpVar;
                    this.zzc = zzajf.zzb;
                }
            }
        }
    }
}
