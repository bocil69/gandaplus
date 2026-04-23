package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzch  reason: invalid package */
/* loaded from: classes.dex */
public final class zzch {
    @Nullable
    private final Object zza;
    @Nullable
    private final Object zzb;
    private final byte[] zzc;
    private final zzxo zzd;
    private final int zze;
    private final String zzf;
    private final zzbn zzg;
    private final int zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzch(@Nullable Object obj, @Nullable Object obj2, byte[] bArr, int i, zzxo zzxoVar, int i2, String str, zzbn zzbnVar) {
        this.zza = obj;
        this.zzb = obj2;
        this.zzc = Arrays.copyOf(bArr, bArr.length);
        this.zzh = i;
        this.zzd = zzxoVar;
        this.zze = i2;
        this.zzf = str;
        this.zzg = zzbnVar;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzbn zzb() {
        return this.zzg;
    }

    public final zzxo zzc() {
        return this.zzd;
    }

    @Nullable
    public final Object zzd() {
        return this.zza;
    }

    @Nullable
    public final Object zze() {
        return this.zzb;
    }

    public final String zzf() {
        return this.zzf;
    }

    @Nullable
    public final byte[] zzg() {
        byte[] bArr = this.zzc;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public final int zzh() {
        return this.zzh;
    }
}
