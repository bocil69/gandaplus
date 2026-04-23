package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahm  reason: invalid package */
/* loaded from: classes.dex */
public final class zzahm implements zzaek {
    private static final String zza = "zzahm";
    private String zzb;

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaek
    public final /* bridge */ /* synthetic */ zzaek zza(String str) throws zzaca {
        try {
            this.zzb = Strings.emptyToNull(new JSONObject(str).optString("sessionInfo", null));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzain.zza(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzb;
    }
}
