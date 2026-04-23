package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahp  reason: invalid package */
/* loaded from: classes.dex */
public final class zzahp implements zzaej {
    private String zza;
    private String zzb;
    private final String zzc;
    private String zzd;

    public zzahp(String str) {
        this.zzc = str;
    }

    public zzahp(String str, String str2, String str3, String str4, String str5) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzc = str4;
        this.zzd = str5;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaej
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String str = this.zza;
        if (str != null) {
            jSONObject.put("email", str);
        }
        String str2 = this.zzb;
        if (str2 != null) {
            jSONObject.put("password", str2);
        }
        String str3 = this.zzc;
        if (str3 != null) {
            jSONObject.put("tenantId", str3);
        }
        String str4 = this.zzd;
        if (str4 != null) {
            zzain.zzd(jSONObject, "captchaResponse", str4);
        } else {
            zzain.zzc(jSONObject);
        }
        return jSONObject.toString();
    }
}
