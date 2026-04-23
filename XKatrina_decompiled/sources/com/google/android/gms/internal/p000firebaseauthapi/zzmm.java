package com.google.android.gms.internal.p000firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmm  reason: invalid package */
/* loaded from: classes.dex */
public final class zzmm implements zzca {
    private final SharedPreferences.Editor zza;
    private final String zzb;

    public zzmm(Context context, String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        this.zzb = str;
        Context applicationContext = context.getApplicationContext();
        if (str2 == null) {
            this.zza = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit();
        } else {
            this.zza = applicationContext.getSharedPreferences(str2, 0).edit();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzca
    public final void zzb(zzva zzvaVar) throws IOException {
        if (!this.zza.putString(this.zzb, zzze.zza(zzvaVar.zzq())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzca
    public final void zzc(zzwv zzwvVar) throws IOException {
        if (!this.zza.putString(this.zzb, zzze.zza(zzwvVar.zzq())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}
