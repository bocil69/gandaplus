package com.google.android.gms.internal.p000firebaseauthapi;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahe  reason: invalid package */
/* loaded from: classes.dex */
public final class zzahe {
    private String zza;
    private String zzb;
    private String zzc;
    private Long zzd;
    private Long zze;

    public static zzahe zza(String str) throws UnsupportedEncodingException {
        try {
            zzahe zzaheVar = new zzahe();
            JSONObject jSONObject = new JSONObject(str);
            zzaheVar.zza = jSONObject.optString("iss");
            zzaheVar.zzb = jSONObject.optString("aud");
            zzaheVar.zzc = jSONObject.optString("sub");
            zzaheVar.zzd = Long.valueOf(jSONObject.optLong("iat"));
            zzaheVar.zze = Long.valueOf(jSONObject.optLong("exp"));
            jSONObject.optBoolean("is_anonymous");
            return zzaheVar;
        } catch (JSONException e) {
            if (Log.isLoggable("JwtToken", 3)) {
                Log.d("JwtToken", "Failed to read JwtToken from JSONObject. ".concat(e.toString()));
            }
            throw new UnsupportedEncodingException("Failed to read JwtToken from JSONObject. ".concat(e.toString()));
        }
    }

    public final Long zzb() {
        return this.zze;
    }

    public final Long zzc() {
        return this.zzd;
    }
}
