package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.NoSuchElementException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaiw  reason: invalid package */
/* loaded from: classes.dex */
final class zzaiw extends zzaiy {
    final /* synthetic */ zzajf zza;
    private int zzb = 0;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaiw(zzajf zzajfVar) {
        this.zza = zzajfVar;
        this.zzc = zzajfVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaja
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}
