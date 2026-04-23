package com.google.android.gms.internal.p000firebaseauthapi;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahs  reason: invalid package */
/* loaded from: classes.dex */
public class zzahs implements zzaek {
    private static final String zza = "zzahs";

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaek
    /* renamed from: zzb */
    public zzahs zza(String str) throws zzaca {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optJSONObject("phoneSessionInfo") != null) {
                zzahw zzahwVar = new zzahw();
                zzahwVar.zzd(str);
                return zzahwVar;
            } else if (jSONObject.optJSONObject("totpSessionInfo") != null) {
                zzahy zzahyVar = new zzahy();
                zzahyVar.zzg(str);
                return zzahyVar;
            } else {
                throw new IllegalArgumentException("Missing phoneSessionInfo or totpSessionInfo.");
            }
        } catch (NullPointerException | JSONException e) {
            throw zzain.zza(e, zza, str);
        }
    }

    public String zzc() {
        return null;
    }
}
