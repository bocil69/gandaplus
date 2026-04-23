package com.google.android.gms.internal.p000firebaseauthapi;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaiz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaiz extends zzajc {
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaiz(byte[] bArr, int i, int i2) {
        super(bArr);
        zzl(0, i2, bArr.length);
        this.zzc = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajc, com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public final byte zzb(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajc
    protected final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajc, com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajc, com.google.android.gms.internal.p000firebaseauthapi.zzajf
    protected final void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajc, com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public final byte zza(int i) {
        int i2 = this.zzc;
        if (((i2 - (i + 1)) | i) < 0) {
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
        }
        return this.zza[i];
    }
}
