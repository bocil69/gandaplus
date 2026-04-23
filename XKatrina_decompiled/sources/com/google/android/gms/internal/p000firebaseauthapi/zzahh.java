package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzahh {
    private final List zza;

    public zzahh() {
        this.zza = new ArrayList();
    }

    public static zzahh zza(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return new zzahh(new ArrayList());
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            arrayList.add(jSONObject == null ? new zzahg() : new zzahg(Strings.emptyToNull(jSONObject.optString("federatedId", null)), Strings.emptyToNull(jSONObject.optString("displayName", null)), Strings.emptyToNull(jSONObject.optString("photoUrl", null)), Strings.emptyToNull(jSONObject.optString("providerId", null)), null, Strings.emptyToNull(jSONObject.optString("phoneNumber", null)), Strings.emptyToNull(jSONObject.optString("email", null))));
        }
        return new zzahh(arrayList);
    }

    public static zzahh zzb(zzahh zzahhVar) {
        zzahh zzahhVar2 = new zzahh();
        List list = zzahhVar.zza;
        if (list != null) {
            zzahhVar2.zza.addAll(list);
        }
        return zzahhVar2;
    }

    public final List zzc() {
        return this.zza;
    }

    public zzahh(List list) {
        if (list.isEmpty()) {
            this.zza = Collections.emptyList();
        } else {
            this.zza = Collections.unmodifiableList(list);
        }
    }
}
