package com.google.android.gms.internal.p000firebaseauthapi;

import android.util.Log;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahy  reason: invalid package */
/* loaded from: classes.dex */
public final class zzahy extends zzahs {
    private static final String zza = "zzahy";
    private String zzb;
    private String zzc;
    private int zzd;
    private String zze;
    private int zzf;
    private long zzg;

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzahs, com.google.android.gms.internal.p000firebaseauthapi.zzaek
    public final /* bridge */ /* synthetic */ zzaek zza(String str) throws zzaca {
        zzg(str);
        return this;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzahs
    public final /* bridge */ /* synthetic */ zzahs zzb(String str) throws zzaca {
        zzg(str);
        return this;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzahs
    public final String zzc() {
        return this.zzb;
    }

    public final int zzd() {
        return this.zzf;
    }

    public final int zze() {
        return this.zzd;
    }

    public final long zzf() {
        return this.zzg;
    }

    public final zzahy zzg(String str) throws zzaca {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("totpSessionInfo");
            if (optJSONObject != null) {
                this.zzc = zzac.zza(optJSONObject.optString("sharedSecretKey"));
                this.zzd = optJSONObject.optInt("verificationCodeLength");
                this.zze = zzac.zza(optJSONObject.optString("hashingAlgorithm"));
                this.zzf = optJSONObject.optInt("periodSec");
                this.zzb = zzac.zza(optJSONObject.optString("sessionInfo"));
                String optString = optJSONObject.optString("finalizeEnrollmentTime");
                try {
                    zzamt zzb = zzano.zzb(optString);
                    zzano.zza(zzb);
                    this.zzg = zzb.zzb();
                } catch (ParseException unused) {
                    String str2 = zza;
                    Log.e(str2, "Failed to parse timestamp: " + optString);
                }
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzain.zza(e, zza, str);
        }
    }

    public final String zzh() {
        return this.zze;
    }

    public final String zzi() {
        return this.zzc;
    }
}
