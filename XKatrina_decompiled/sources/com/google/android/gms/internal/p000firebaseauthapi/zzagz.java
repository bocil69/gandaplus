package com.google.android.gms.internal.p000firebaseauthapi;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzagz implements zzaej {
    private final String zza;
    private final String zzb = "CLIENT_TYPE_ANDROID";
    private final String zzc = "RECAPTCHA_ENTERPRISE";

    private zzagz(String str, String str2) {
        this.zza = str;
    }

    public static zzagz zzb(String str, String str2) {
        return new zzagz(str, "RECAPTCHA_ENTERPRISE");
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaej
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.zza)) {
            jSONObject.put("tenantId", this.zza);
        }
        if (!TextUtils.isEmpty(this.zzb)) {
            jSONObject.put("clientType", this.zzb);
        }
        if (!TextUtils.isEmpty(this.zzc)) {
            jSONObject.put("recaptchaVersion", this.zzc);
        }
        return jSONObject.toString();
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzc;
    }
}
