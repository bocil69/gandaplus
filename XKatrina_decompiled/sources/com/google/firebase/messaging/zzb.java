package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.util.PlatformVersion;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.util.Arrays;
import java.util.Iterator;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes2.dex */
public final class zzb {
    private static final AtomicInteger zzdt = new AtomicInteger((int) SystemClock.elapsedRealtime());
    private final Context zzag;
    private final String zzdu;
    @GuardedBy("this")
    private Bundle zzdv;

    public zzb(Context context, String str) {
        this.zzag = context;
        this.zzdu = str;
    }

    public final zza zzf(Bundle bundle) {
        Uri defaultUri;
        Intent intent;
        PendingIntent activity;
        PendingIntent pendingIntent = null;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.zzag, zzn(zza(bundle, "gcm.n.android_channel_id")));
        builder.setAutoCancel(true);
        builder.setContentTitle(zzg(bundle));
        String zzc = zzc(bundle, "gcm.n.body");
        if (!TextUtils.isEmpty(zzc)) {
            builder.setContentText(zzc);
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(zzc));
        }
        builder.setSmallIcon(zzl(zza(bundle, "gcm.n.icon")));
        String zzi = zzi(bundle);
        if (TextUtils.isEmpty(zzi)) {
            defaultUri = null;
        } else if (!"default".equals(zzi) && this.zzag.getResources().getIdentifier(zzi, "raw", this.zzdu) != 0) {
            String str = this.zzdu;
            defaultUri = Uri.parse(new StringBuilder(String.valueOf(str).length() + 24 + String.valueOf(zzi).length()).append("android.resource://").append(str).append("/raw/").append(zzi).toString());
        } else {
            defaultUri = RingtoneManager.getDefaultUri(2);
        }
        if (defaultUri != null) {
            builder.setSound(defaultUri);
        }
        String zza = zza(bundle, "gcm.n.click_action");
        if (!TextUtils.isEmpty(zza)) {
            Intent intent2 = new Intent(zza);
            intent2.setPackage(this.zzdu);
            intent2.setFlags(268435456);
            intent = intent2;
        } else {
            Uri zzj = zzj(bundle);
            if (zzj != null) {
                Intent intent3 = new Intent("android.intent.action.VIEW");
                intent3.setPackage(this.zzdu);
                intent3.setData(zzj);
                intent = intent3;
            } else {
                Intent launchIntentForPackage = this.zzag.getPackageManager().getLaunchIntentForPackage(this.zzdu);
                if (launchIntentForPackage == null) {
                    Log.w("FirebaseMessaging", "No activity found to launch app");
                }
                intent = launchIntentForPackage;
            }
        }
        if (intent == null) {
            activity = null;
        } else {
            intent.addFlags(FileSystemManager.MODE_TRUNCATE);
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next != null && next.startsWith("google.c.")) {
                    it.remove();
                }
            }
            intent.putExtras(bundle2);
            for (String str2 : bundle2.keySet()) {
                if (str2.startsWith("gcm.n.") || str2.startsWith("gcm.notification.")) {
                    intent.removeExtra(str2);
                }
            }
            activity = PendingIntent.getActivity(this.zzag, zzdt.incrementAndGet(), intent, 1073741824);
            if (zzk(bundle)) {
                Intent intent4 = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                zza(intent4, bundle);
                intent4.putExtra("pending_intent", activity);
                activity = zza(zzdt.incrementAndGet(), intent4);
            }
        }
        builder.setContentIntent(activity);
        if (zzk(bundle)) {
            Intent intent5 = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
            zza(intent5, bundle);
            pendingIntent = zza(zzdt.incrementAndGet(), intent5);
        }
        if (pendingIntent != null) {
            builder.setDeleteIntent(pendingIntent);
        }
        Integer zzm = zzm(zza(bundle, "gcm.n.color"));
        if (zzm != null) {
            builder.setColor(zzm.intValue());
        }
        String zza2 = zza(bundle, "gcm.n.tag");
        if (TextUtils.isEmpty(zza2)) {
            zza2 = new StringBuilder(37).append("FCM-Notification:").append(SystemClock.uptimeMillis()).toString();
        }
        return new zza(builder, zza2, 0);
    }

    @NonNull
    private final CharSequence zzg(Bundle bundle) {
        String zzc = zzc(bundle, "gcm.n.title");
        if (TextUtils.isEmpty(zzc)) {
            try {
                return zzc(0).loadLabel(this.zzag.getPackageManager());
            } catch (PackageManager.NameNotFoundException e) {
                String valueOf = String.valueOf(e);
                Log.e("FirebaseMessaging", new StringBuilder(String.valueOf(valueOf).length() + 35).append("Couldn't get own application info: ").append(valueOf).toString());
                return "";
            }
        }
        return zzc;
    }

    public static boolean zzh(Bundle bundle) {
        return "1".equals(zza(bundle, "gcm.n.e")) || zza(bundle, "gcm.n.icon") != null;
    }

    public static String zza(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return bundle.getString(str.replace("gcm.n.", "gcm.notification."));
        }
        return string;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object[] zzb(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        String zza = zza(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(zza);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return strArr;
        } catch (JSONException e) {
            String valueOf3 = String.valueOf(str);
            String valueOf4 = String.valueOf("_loc_args");
            String substring = (valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).substring(6);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(substring).length() + 41 + String.valueOf(zza).length()).append("Malformed ").append(substring).append(": ").append(zza).append("  Default value will be used.").toString());
            return null;
        }
    }

    private final String zzc(Bundle bundle, String str) {
        String zza = zza(bundle, str);
        return !TextUtils.isEmpty(zza) ? zza : zze(bundle, str);
    }

    public static String zzd(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        return zza(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    private final String zze(Bundle bundle, String str) {
        String zzd = zzd(bundle, str);
        if (TextUtils.isEmpty(zzd)) {
            return null;
        }
        Resources resources = this.zzag.getResources();
        int identifier = resources.getIdentifier(zzd, TypedValues.Custom.S_STRING, this.zzdu);
        if (identifier == 0) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf("_loc_key");
            String substring = (valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).substring(6);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(substring).length() + 49 + String.valueOf(str).length()).append(substring).append(" resource not found: ").append(str).append(" Default value will be used.").toString());
            return null;
        }
        Object[] zzb = zzb(bundle, str);
        if (zzb == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, zzb);
        } catch (MissingFormatArgumentException e) {
            String arrays = Arrays.toString(zzb);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 58 + String.valueOf(arrays).length()).append("Missing format argument for ").append(str).append(": ").append(arrays).append(" Default value will be used.").toString(), e);
            return null;
        }
    }

    @TargetApi(26)
    private final boolean zzb(int i) {
        if (Build.VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (this.zzag.getResources().getDrawable(i, null) instanceof AdaptiveIconDrawable) {
                Log.e("FirebaseMessaging", new StringBuilder(77).append("Adaptive icons cannot be used in notifications. Ignoring icon id: ").append(i).toString());
                return false;
            }
            return true;
        } catch (Resources.NotFoundException e) {
            Log.e("FirebaseMessaging", new StringBuilder(66).append("Couldn't find resource ").append(i).append(", treating it as an invalid icon").toString());
            return false;
        }
    }

    private final int zzl(String str) {
        int i;
        if (!TextUtils.isEmpty(str)) {
            Resources resources = this.zzag.getResources();
            int identifier = resources.getIdentifier(str, "drawable", this.zzdu);
            if (identifier == 0 || !zzb(identifier)) {
                int identifier2 = resources.getIdentifier(str, "mipmap", this.zzdu);
                if (identifier2 == 0 || !zzb(identifier2)) {
                    Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 61).append("Icon resource ").append(str).append(" not found. Notification will use default icon.").toString());
                } else {
                    return identifier2;
                }
            } else {
                return identifier;
            }
        }
        int i2 = zzar().getInt("com.google.firebase.messaging.default_notification_icon", 0);
        if (i2 == 0 || !zzb(i2)) {
            try {
                i = zzc(0).icon;
            } catch (PackageManager.NameNotFoundException e) {
                String valueOf = String.valueOf(e);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(valueOf).length() + 35).append("Couldn't get own application info: ").append(valueOf).toString());
            }
            if (i != 0 || !zzb(i)) {
                return 17301651;
            }
            return i;
        }
        i = i2;
        if (i != 0) {
        }
        return 17301651;
    }

    private final Integer zzm(String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException e) {
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 54).append("Color ").append(str).append(" not valid. Notification will use default color.").toString());
            }
        }
        int i = zzar().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i != 0) {
            try {
                return Integer.valueOf(ContextCompat.getColor(this.zzag, i));
            } catch (Resources.NotFoundException e2) {
                Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
                return null;
            }
        }
        return null;
    }

    public static String zzi(Bundle bundle) {
        String zza = zza(bundle, "gcm.n.sound2");
        if (TextUtils.isEmpty(zza)) {
            return zza(bundle, "gcm.n.sound");
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Uri zzj(@NonNull Bundle bundle) {
        String zza = zza(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(zza)) {
            zza = zza(bundle, "gcm.n.link");
        }
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        return Uri.parse(zza);
    }

    private final synchronized Bundle zzar() {
        Bundle bundle;
        if (this.zzdv != null) {
            bundle = this.zzdv;
        } else {
            try {
                ApplicationInfo zzc = zzc(128);
                if (zzc != null && zzc.metaData != null) {
                    this.zzdv = zzc.metaData;
                    bundle = this.zzdv;
                }
            } catch (PackageManager.NameNotFoundException e) {
                String valueOf = String.valueOf(e);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(valueOf).length() + 35).append("Couldn't get own application info: ").append(valueOf).toString());
            }
            bundle = Bundle.EMPTY;
        }
        return bundle;
    }

    @TargetApi(26)
    private final String zzn(String str) {
        int i = 0;
        if (PlatformVersion.isAtLeastO()) {
            try {
                i = zzc(0).targetSdkVersion;
            } catch (PackageManager.NameNotFoundException e) {
            }
            if (i < 26) {
                return null;
            }
            NotificationManager notificationManager = (NotificationManager) this.zzag.getSystemService(NotificationManager.class);
            if (!TextUtils.isEmpty(str)) {
                if (notificationManager.getNotificationChannel(str) == null) {
                    Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 122).append("Notification Channel requested (").append(str).append(") has not been created by the app. Manifest configuration, or default, value will be used.").toString());
                } else {
                    return str;
                }
            }
            String string = zzar().getString("com.google.firebase.messaging.default_notification_channel_id");
            if (!TextUtils.isEmpty(string)) {
                if (notificationManager.getNotificationChannel(string) == null) {
                    Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                } else {
                    return string;
                }
            } else {
                Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
            }
            if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                notificationManager.createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", this.zzag.getString(this.zzag.getResources().getIdentifier("fcm_fallback_notification_channel_label", TypedValues.Custom.S_STRING, this.zzdu)), 3));
            }
            return "fcm_fallback_notification_channel";
        }
        return null;
    }

    private final ApplicationInfo zzc(int i) throws PackageManager.NameNotFoundException {
        return this.zzag.getPackageManager().getApplicationInfo(this.zzdu, i);
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals(TypedValues.Transition.S_FROM)) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private final PendingIntent zza(int i, Intent intent) {
        return PendingIntent.getBroadcast(this.zzag, i, new Intent("com.google.firebase.MESSAGING_EVENT").setComponent(new ComponentName(this.zzag, "com.google.firebase.iid.FirebaseInstanceIdReceiver")).putExtra("wrapped_intent", intent), 1073741824);
    }

    private static boolean zzk(Bundle bundle) {
        return bundle != null && "1".equals(bundle.getString("google.c.a.e"));
    }
}
