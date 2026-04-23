package com.google.android.gms.internal.p000firebaseauthapi;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahw  reason: invalid package */
/* loaded from: classes.dex */
public final class zzahw extends zzahs {
    private static final String zza = "zzahw";
    private String zzb;

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzahs, com.google.android.gms.internal.p000firebaseauthapi.zzaek
    public final /* bridge */ /* synthetic */ zzaek zza(String str) throws zzaca {
        zzd(str);
        return this;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzahs
    public final /* bridge */ /* synthetic */ zzahs zzb(String str) throws zzaca {
        zzd(str);
        return this;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzahs
    public final String zzc() {
        return this.zzb;
    }

    public final zzahw zzd(String str) throws zzaca {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("phoneSessionInfo");
            if (optJSONObject != null) {
                this.zzb = zzac.zza(optJSONObject.optString("sessionInfo"));
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzain.zza(e, zza, str);
        }
    }
}
