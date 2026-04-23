package com.google.android.gms.internal.p000firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzba;
import com.google.firebase.auth.internal.zzg;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzacj extends zzaez {
    private final String zza;

    public zzacj(String str) {
        super(1);
        Preconditions.checkNotEmpty(str, "refresh token cannot be null");
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final String zza() {
        return "getAccessToken";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaez
    public final void zzb() {
        if (TextUtils.isEmpty(this.zzn.zzf())) {
            this.zzn.zzi(this.zza);
        }
        ((zzg) this.zzi).zza(this.zzn, this.zzh);
        zzm(zzba.zza(this.zzn.zze()));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final void zzc(TaskCompletionSource taskCompletionSource, zzady zzadyVar) {
        this.zzk = new zzaey(this, taskCompletionSource);
        zzadyVar.zzj(this.zza, this.zzf);
    }
}
