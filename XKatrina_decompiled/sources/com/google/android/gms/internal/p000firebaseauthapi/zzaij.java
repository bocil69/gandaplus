package com.google.android.gms.internal.p000firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaij  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaij implements zzaej {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private boolean zzf;

    private zzaij() {
    }

    public static zzaij zzb(String str, String str2, boolean z) {
        zzaij zzaijVar = new zzaij();
        zzaijVar.zzb = Preconditions.checkNotEmpty(str);
        zzaijVar.zzc = Preconditions.checkNotEmpty(str2);
        zzaijVar.zzf = z;
        return zzaijVar;
    }

    public static zzaij zzc(String str, String str2, boolean z) {
        zzaij zzaijVar = new zzaij();
        zzaijVar.zza = Preconditions.checkNotEmpty(str);
        zzaijVar.zzd = Preconditions.checkNotEmpty(str2);
        zzaijVar.zzf = z;
        return zzaijVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaej
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.zzd)) {
            jSONObject.put("phoneNumber", this.zza);
            jSONObject.put("temporaryProof", this.zzd);
        } else {
            jSONObject.put("sessionInfo", this.zzb);
            jSONObject.put("code", this.zzc);
        }
        String str = this.zze;
        if (str != null) {
            jSONObject.put("idToken", str);
        }
        if (!this.zzf) {
            jSONObject.put("operation", 2);
        }
        return jSONObject.toString();
    }

    public final void zzd(String str) {
        this.zze = str;
    }
}
