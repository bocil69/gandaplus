package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.firebase.database.core.ServerValues;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
final class zzay {
    private static final long zzdn = TimeUnit.DAYS.toMillis(7);
    private final long timestamp;
    final String zzbv;
    private final String zzdo;

    private zzay(String str, String str2, long j) {
        this.zzbv = str;
        this.zzdo = str2;
        this.timestamp = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzay zzi(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("{")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new zzay(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong(ServerValues.NAME_OP_TIMESTAMP));
            } catch (JSONException e) {
                String valueOf = String.valueOf(e);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to parse token: ").append(valueOf).toString());
                return null;
            }
        }
        return new zzay(str, null, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("token", str);
            jSONObject.put("appVersion", str2);
            jSONObject.put(ServerValues.NAME_OP_TIMESTAMP, j);
            return jSONObject.toString();
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 24).append("Failed to encode token: ").append(valueOf).toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzb(@Nullable zzay zzayVar) {
        if (zzayVar == null) {
            return null;
        }
        return zzayVar.zzbv;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzj(String str) {
        return System.currentTimeMillis() > this.timestamp + zzdn || !str.equals(this.zzdo);
    }
}
