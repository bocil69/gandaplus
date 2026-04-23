package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p000firebaseauthapi.zzahb;
import com.google.android.gms.internal.p000firebaseauthapi.zzzr;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorInfo;
import java.util.Iterator;
import java.util.List;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzbt {
    private final Context zza;
    private final String zzb;
    private final SharedPreferences zzc;
    private final Logger zzd;

    public zzbt(Context context, String str) {
        Preconditions.checkNotNull(context);
        String checkNotEmpty = Preconditions.checkNotEmpty(str);
        this.zzb = checkNotEmpty;
        Context applicationContext = context.getApplicationContext();
        this.zza = applicationContext;
        this.zzc = applicationContext.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", checkNotEmpty), 0);
        this.zzd = new Logger("StorageHelpers", new String[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00d0 A[Catch: zzzr -> 0x017e, IllegalArgumentException -> 0x0180, ArrayIndexOutOfBoundsException -> 0x0182, JSONException -> 0x0184, TRY_ENTER, TryCatch #1 {JSONException -> 0x0184, blocks: (B:3:0x0007, B:6:0x0024, B:9:0x0031, B:13:0x003d, B:15:0x007b, B:17:0x0082, B:18:0x008e, B:19:0x008f, B:21:0x009e, B:23:0x00a7, B:24:0x00aa, B:26:0x00b3, B:31:0x00d0, B:32:0x00d3, B:34:0x00d9, B:36:0x00df, B:37:0x00e5, B:39:0x00eb, B:42:0x0106, B:44:0x010e, B:59:0x0163, B:45:0x0125, B:46:0x012c, B:50:0x0133, B:54:0x013c, B:56:0x0144, B:58:0x0150, B:60:0x016a, B:61:0x0171, B:62:0x0172, B:63:0x0179, B:64:0x017a), top: B:78:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.google.firebase.auth.internal.zzx zzf(org.json.JSONObject r27) {
        /*
            Method dump skipped, instructions count: 397
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzbt.zzf(org.json.JSONObject):com.google.firebase.auth.internal.zzx");
    }

    public final FirebaseUser zza() {
        String string = this.zzc.getString("com.google.firebase.auth.FIREBASE_USER", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("type") && "com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString("type"))) {
                return zzf(jSONObject);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final zzahb zzb(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String string = this.zzc.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), null);
        if (string != null) {
            return zzahb.zzd(string);
        }
        return null;
    }

    public final void zzc(String str) {
        this.zzc.edit().remove(str).apply();
    }

    public final void zzd(FirebaseUser firebaseUser) {
        String str;
        Preconditions.checkNotNull(firebaseUser);
        JSONObject jSONObject = new JSONObject();
        if (zzx.class.isAssignableFrom(firebaseUser.getClass())) {
            zzx zzxVar = (zzx) firebaseUser;
            try {
                jSONObject.put("cachedTokenState", zzxVar.zzf());
                jSONObject.put("applicationName", zzxVar.zza().getName());
                jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
                if (zzxVar.zzo() != null) {
                    JSONArray jSONArray = new JSONArray();
                    List zzo = zzxVar.zzo();
                    int size = zzo.size();
                    if (zzo.size() > 30) {
                        this.zzd.w("Provider user info list size larger than max size, truncating list to %d. Actual list size: %d", 30, Integer.valueOf(zzo.size()));
                        size = 30;
                    }
                    boolean z = false;
                    for (int i = 0; i < size; i++) {
                        zzt zztVar = (zzt) zzo.get(i);
                        z |= zztVar.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID);
                        if (i == size - 1 && !z) {
                            break;
                        }
                        jSONArray.put(zztVar.zzb());
                    }
                    if (!z) {
                        for (int i2 = size - 1; i2 < zzo.size() && i2 >= 0; i2++) {
                            zzt zztVar2 = (zzt) zzo.get(i2);
                            if (!zztVar2.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                                if (i2 == zzo.size() - 1) {
                                    jSONArray.put(zztVar2.zzb());
                                }
                            } else {
                                jSONArray.put(zztVar2.zzb());
                                break;
                            }
                        }
                        this.zzd.w("Malformed user object! No Firebase Auth provider id found. Provider user info list size: %d, trimmed size: %d", Integer.valueOf(zzo.size()), Integer.valueOf(size));
                        if (zzo.size() < 5) {
                            StringBuilder sb = new StringBuilder("Provider user info list:\n");
                            Iterator it = zzo.iterator();
                            while (it.hasNext()) {
                                sb.append(String.format("Provider - %s\n", ((zzt) it.next()).getProviderId()));
                            }
                            this.zzd.w(sb.toString(), new Object[0]);
                        }
                    }
                    jSONObject.put("userInfos", jSONArray);
                }
                jSONObject.put("anonymous", zzxVar.isAnonymous());
                jSONObject.put(ClientCookie.VERSION_ATTR, ExifInterface.GPS_MEASUREMENT_2D);
                if (zzxVar.getMetadata() != null) {
                    jSONObject.put("userMetadata", ((zzz) zzxVar.getMetadata()).zza());
                }
                List<MultiFactorInfo> enrolledFactors = new zzac(zzxVar).getEnrolledFactors();
                if (!enrolledFactors.isEmpty()) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i3 = 0; i3 < enrolledFactors.size(); i3++) {
                        jSONArray2.put(enrolledFactors.get(i3).toJson());
                    }
                    jSONObject.put("userMultiFactorInfo", jSONArray2);
                }
                str = jSONObject.toString();
            } catch (Exception e) {
                this.zzd.wtf("Failed to turn object into JSON", e, new Object[0]);
                throw new zzzr(e);
            }
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.zzc.edit().putString("com.google.firebase.auth.FIREBASE_USER", str).apply();
    }

    public final void zze(FirebaseUser firebaseUser, zzahb zzahbVar) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzahbVar);
        this.zzc.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), zzahbVar.zzh()).apply();
    }
}
