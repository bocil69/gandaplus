package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzag;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadi  reason: invalid package */
/* loaded from: classes.dex */
public final class zzadi extends zzaez {
    private final boolean zzC;
    private final boolean zzD;
    private final String zzE;
    private final String zzF;
    private final boolean zzG;
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final long zzd;

    public zzadi(zzag zzagVar, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, boolean z3) {
        super(8);
        Preconditions.checkNotNull(zzagVar);
        Preconditions.checkNotEmpty(str);
        this.zza = Preconditions.checkNotEmpty(zzagVar.zzd());
        this.zzb = str;
        this.zzc = str2;
        this.zzd = j;
        this.zzC = z;
        this.zzD = z2;
        this.zzE = str3;
        this.zzF = str4;
        this.zzG = z3;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final String zza() {
        return "startMfaEnrollment";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaez
    public final void zzb() {
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final void zzc(TaskCompletionSource taskCompletionSource, zzady zzadyVar) {
        this.zzk = new zzaey(this, taskCompletionSource);
        zzadyVar.zzB(this.zza, this.zzb, this.zzc, this.zzd, this.zzC, this.zzD, this.zzE, this.zzF, this.zzG, this.zzf);
    }
}
