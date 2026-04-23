package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagc  reason: invalid package */
/* loaded from: classes.dex */
public final class zzagc implements zzaek {
    private static final String zza = "zzagc";
    private String zzb;
    private String zzc;
    private zzahz zzd = new zzahz(null);
    private List zze;

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaek
    public final /* bridge */ /* synthetic */ zzaek zza(String str) throws zzaca {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = jSONObject.optString("authUri", null);
            jSONObject.optBoolean("registered", false);
            this.zzc = jSONObject.optString("providerId", null);
            jSONObject.optBoolean("forExistingProvider", false);
            if (!jSONObject.has("allProviders")) {
                this.zzd = new zzahz(null);
            } else {
                this.zzd = new zzahz(1, zzain.zzb(jSONObject.optJSONArray("allProviders")));
            }
            this.zze = zzain.zzb(jSONObject.optJSONArray("signinMethods"));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzain.zza(e, zza, str);
        }
    }

    public final List zzb() {
        return this.zze;
    }
}
