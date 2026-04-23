package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjx  reason: invalid package */
/* loaded from: classes.dex */
public final class zzjx extends zzlg {
    private static final Set zza = (Set) zzpc.zza(new zzpb() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjp
        @Override // com.google.android.gms.internal.p000firebaseauthapi.zzpb
        public final Object zza() {
            HashSet hashSet = new HashSet();
            zzev zzc = zzey.zzc();
            zzc.zza(12);
            zzc.zzb(16);
            zzc.zzc(16);
            zzc.zzd(zzew.zzc);
            hashSet.add(zzc.zze());
            zzev zzc2 = zzey.zzc();
            zzc2.zza(12);
            zzc2.zzb(32);
            zzc2.zzc(16);
            zzc2.zzd(zzew.zzc);
            hashSet.add(zzc2.zze());
            zzdj zzf = zzdn.zzf();
            zzf.zza(16);
            zzf.zzc(32);
            zzf.zze(16);
            zzf.zzd(16);
            zzf.zzb(zzdk.zzc);
            zzf.zzf(zzdl.zzc);
            hashSet.add(zzf.zzg());
            zzdj zzf2 = zzdn.zzf();
            zzf2.zza(32);
            zzf2.zzc(32);
            zzf2.zze(32);
            zzf2.zzd(16);
            zzf2.zzb(zzdk.zzc);
            zzf2.zzf(zzdl.zzc);
            hashSet.add(zzf2.zzg());
            hashSet.add(zzhr.zzc());
            zzit zzc3 = zziw.zzc();
            zzc3.zza(64);
            zzc3.zzb(zziu.zzc);
            hashSet.add(zzc3.zzc());
            return Collections.unmodifiableSet(hashSet);
        }
    });
    private final zzjs zzb;
    private final zzjt zzc;
    @Nullable
    private final zzju zzd;
    private final zzjv zze;
    private final zzce zzf;
    @Nullable
    private final zzzo zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjx(zzjs zzjsVar, zzjt zzjtVar, zzju zzjuVar, zzce zzceVar, zzjv zzjvVar, zzzo zzzoVar, zzjw zzjwVar) {
        this.zzb = zzjsVar;
        this.zzc = zzjtVar;
        this.zzd = zzjuVar;
        this.zzf = zzceVar;
        this.zze = zzjvVar;
        this.zzg = zzzoVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzjx) {
            zzjx zzjxVar = (zzjx) obj;
            return zzjo.zza(zzjxVar.zzb, this.zzb) && zzjo.zza(zzjxVar.zzc, this.zzc) && zzjo.zza(zzjxVar.zzd, this.zzd) && zzjo.zza(zzjxVar.zzf, this.zzf) && zzjo.zza(zzjxVar.zze, this.zze) && zzjo.zza(zzjxVar.zzg, this.zzg);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzjx.class, this.zzb, this.zzc, this.zzd, this.zzf, this.zze, this.zzg});
    }

    public final String toString() {
        return String.format("EciesParameters(curveType=%s, hashType=%s, pointFormat=%s, demParameters=%s, variant=%s, salt=%s)", this.zzb, this.zzc, this.zzd, this.zzf, this.zze, this.zzg);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        throw null;
    }

    public final zzce zzb() {
        return this.zzf;
    }

    public final zzjs zzc() {
        return this.zzb;
    }

    public final zzjt zzd() {
        return this.zzc;
    }

    public final zzju zze() {
        return this.zzd;
    }

    public final zzjv zzf() {
        return this.zze;
    }

    @Nullable
    public final zzzo zzg() {
        return this.zzg;
    }
}
