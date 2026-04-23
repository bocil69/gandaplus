package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagr  reason: invalid package */
/* loaded from: classes.dex */
public final class zzagr implements zzaek {
    private static final String zza = "zzagr";
    private zzagt zzb;

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaek
    public final /* bridge */ /* synthetic */ zzaek zza(String str) throws zzaca {
        zzagt zzagtVar;
        int i;
        zzags zzagsVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("users")) {
                this.zzb = new zzagt();
            } else {
                JSONArray optJSONArray = jSONObject.optJSONArray("users");
                if (optJSONArray != null && optJSONArray.length() != 0) {
                    ArrayList arrayList = new ArrayList(optJSONArray.length());
                    boolean z = false;
                    int i2 = 0;
                    while (i2 < optJSONArray.length()) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                        if (jSONObject2 != null) {
                            i = i2;
                            zzagsVar = new zzags(Strings.emptyToNull(jSONObject2.optString("localId", null)), Strings.emptyToNull(jSONObject2.optString("email", null)), jSONObject2.optBoolean("emailVerified", z), Strings.emptyToNull(jSONObject2.optString("displayName", null)), Strings.emptyToNull(jSONObject2.optString("photoUrl", null)), zzahh.zza(jSONObject2.optJSONArray("providerUserInfo")), Strings.emptyToNull(jSONObject2.optString("rawPassword", null)), Strings.emptyToNull(jSONObject2.optString("phoneNumber", null)), jSONObject2.optLong("createdAt", 0L), jSONObject2.optLong("lastLoginAt", 0L), false, null, zzahf.zzg(jSONObject2.optJSONArray("mfaInfo")));
                        } else {
                            zzagsVar = new zzags();
                            i = i2;
                        }
                        arrayList.add(zzagsVar);
                        i2 = i + 1;
                        z = false;
                    }
                    zzagtVar = new zzagt(arrayList);
                    this.zzb = zzagtVar;
                }
                zzagtVar = new zzagt(new ArrayList());
                this.zzb = zzagtVar;
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzain.zza(e, zza, str);
        }
    }

    public final List zzb() {
        return this.zzb.zza();
    }
}
