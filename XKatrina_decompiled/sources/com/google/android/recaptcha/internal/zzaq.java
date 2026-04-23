package com.google.android.recaptcha.internal;

import android.net.TrafficStats;
import android.webkit.URLUtil;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.protocol.HTTP;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzaq implements zzap {
    private final String zza;

    public zzaq(String str) {
        this.zza = str;
    }

    private static final void zzb(byte[] bArr) {
        for (zzkx zzkxVar : zzla.zzi(bArr).zzj()) {
            if (!CollectionsKt.listOf(new String[]{"INIT_TOTAL", "EXECUTE_TOTAL"}).contains(zzkxVar.zzj().name()) || !zzkxVar.zzS()) {
                zzkxVar.zzH();
                zzkxVar.zzI();
                zzkxVar.zzj().name();
                zzkxVar.zzT();
            } else {
                zzkxVar.zzH();
                zzkxVar.zzI();
                zzkxVar.zzj().name();
                zzkxVar.zzg().zzk();
                zzkxVar.zzg().zzf();
                zzkxVar.zzT();
            }
        }
    }

    @Override // com.google.android.recaptcha.internal.zzap
    public final boolean zza(byte[] bArr) {
        HttpURLConnection httpURLConnection;
        try {
            TrafficStats.setThreadStatsTag((int) Thread.currentThread().getId());
            zzb(bArr);
            if (URLUtil.isHttpUrl(this.zza)) {
                URLConnection openConnection = new URL(this.zza).openConnection();
                Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                httpURLConnection = (HttpURLConnection) openConnection;
            } else if (URLUtil.isHttpsUrl(this.zza)) {
                URLConnection openConnection2 = new URL(this.zza).openConnection();
                Intrinsics.checkNotNull(openConnection2, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
                httpURLConnection = (HttpsURLConnection) openConnection2;
            } else {
                throw new MalformedURLException("Recaptcha server url only allows using Http or Https.");
            }
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "application/x-protobuffer");
            httpURLConnection.connect();
            httpURLConnection.getOutputStream().write(bArr);
            return httpURLConnection.getResponseCode() == 200;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
}
