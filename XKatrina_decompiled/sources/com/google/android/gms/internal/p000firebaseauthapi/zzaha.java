package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaha  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaha implements zzaek {
    private static final String zza = "zzaha";
    private String zzb;
    private zzam zzc;
    private boolean zzd = false;

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaek
    public final /* bridge */ /* synthetic */ zzaek zza(String str) throws zzaca {
        zzam zzh;
        String zza2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("recaptchaKey"));
            if (jSONObject.has("recaptchaEnforcementState")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("recaptchaEnforcementState");
                boolean z = false;
                if (optJSONArray != null && optJSONArray.length() != 0) {
                    zzaj zzajVar = new zzaj();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        zzajVar.zzb(jSONObject2 == null ? new zzafz(null, null) : new zzafz(Strings.emptyToNull(jSONObject2.optString("provider")), Strings.emptyToNull(jSONObject2.optString("enforcementState"))));
                    }
                    zzh = zzajVar.zzc();
                    this.zzc = zzh;
                    if (zzh != null && !zzh.isEmpty()) {
                        zza2 = ((zzahi) zzh.get(0)).zza();
                        String zzb = ((zzahi) zzh.get(0)).zzb();
                        if (zza2 != null && zzb != null && ((zza2.equals("ENFORCE") || zza2.equals("AUDIT")) && zzb.equals("EMAIL_PASSWORD_PROVIDER"))) {
                            z = true;
                        }
                    }
                    this.zzd = z;
                }
                zzh = zzam.zzh(new ArrayList());
                this.zzc = zzh;
                if (zzh != null) {
                    zza2 = ((zzahi) zzh.get(0)).zza();
                    String zzb2 = ((zzahi) zzh.get(0)).zzb();
                    if (zza2 != null) {
                        z = true;
                    }
                }
                this.zzd = z;
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzain.zza(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zzd;
    }
}
