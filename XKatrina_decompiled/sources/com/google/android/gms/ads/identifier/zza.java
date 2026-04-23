package com.google.android.gms.ads.identifier;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
/* loaded from: classes2.dex */
final class zza extends Thread {
    private final /* synthetic */ Map zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(AdvertisingIdClient advertisingIdClient, Map map) {
        this.zzl = map;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        new zzc();
        Map map = this.zzl;
        Uri.Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        for (String str : map.keySet()) {
            buildUpon.appendQueryParameter(str, (String) map.get(str));
        }
        String uri = buildUpon.build().toString();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri).openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                Log.w("HttpUrlPinger", new StringBuilder(65 + String.valueOf(uri).length()).append("Received non-success response code ").append(responseCode).append(" from pinging URL: ").append(uri).toString());
            }
            httpURLConnection.disconnect();
        } catch (IOException e) {
            e = e;
            Exception exc = e;
            String message = exc.getMessage();
            Log.w("HttpUrlPinger", new StringBuilder(27 + String.valueOf(uri).length() + String.valueOf(message).length()).append("Error while pinging URL: ").append(uri).append(". ").append(message).toString(), exc);
        } catch (IndexOutOfBoundsException e2) {
            String message2 = e2.getMessage();
            Log.w("HttpUrlPinger", new StringBuilder(32 + String.valueOf(uri).length() + String.valueOf(message2).length()).append("Error while parsing ping URL: ").append(uri).append(". ").append(message2).toString(), e2);
        } catch (RuntimeException e3) {
            e = e3;
            Exception exc2 = e;
            String message3 = exc2.getMessage();
            Log.w("HttpUrlPinger", new StringBuilder(27 + String.valueOf(uri).length() + String.valueOf(message3).length()).append("Error while pinging URL: ").append(uri).append(". ").append(message3).toString(), exc2);
        }
    }
}
