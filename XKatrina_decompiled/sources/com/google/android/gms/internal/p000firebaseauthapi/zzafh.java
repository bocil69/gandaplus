package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzafh {
    public static void zza(String str, zzafe zzafeVar, Type type, zzaen zzaenVar) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(60000);
            zzaenVar.zza(httpURLConnection);
            zzc(httpURLConnection, zzafeVar, type);
        } catch (SocketTimeoutException unused) {
            zzafeVar.zza("TIMEOUT");
        } catch (UnknownHostException unused2) {
            zzafeVar.zza("<<Network Error>>");
        } catch (IOException e) {
            zzafeVar.zza(e.getMessage());
        }
    }

    public static void zzb(String str, zzaej zzaejVar, zzafe zzafeVar, Type type, zzaen zzaenVar) {
        try {
            Preconditions.checkNotNull(zzaejVar);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            byte[] bytes = zzaejVar.zza().getBytes(Charset.defaultCharset());
            int length = bytes.length;
            httpURLConnection.setFixedLengthStreamingMode(length);
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
            httpURLConnection.setConnectTimeout(60000);
            zzaenVar.zza(httpURLConnection);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream(), length);
            try {
                bufferedOutputStream.write(bytes, 0, length);
                bufferedOutputStream.close();
                zzc(httpURLConnection, zzafeVar, type);
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
                throw th;
            }
        } catch (IOException e) {
            e = e;
            zzafeVar.zza(e.getMessage());
        } catch (NullPointerException e2) {
            e = e2;
            zzafeVar.zza(e.getMessage());
        } catch (SocketTimeoutException unused) {
            zzafeVar.zza("TIMEOUT");
        } catch (UnknownHostException unused2) {
            zzafeVar.zza("<<Network Error>>");
        } catch (JSONException e3) {
            e = e3;
            zzafeVar.zza(e.getMessage());
        }
    }

    private static void zzc(HttpURLConnection httpURLConnection, zzafe zzafeVar, Type type) {
        InputStream errorStream;
        try {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (zzd(responseCode)) {
                    errorStream = httpURLConnection.getInputStream();
                } else {
                    errorStream = httpURLConnection.getErrorStream();
                }
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, "UTF-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th2) {
                            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                        }
                        throw th;
                    }
                }
                bufferedReader.close();
                String sb2 = sb.toString();
                if (!zzd(responseCode)) {
                    zzafeVar.zza((String) zzaei.zza(sb2, String.class));
                } else {
                    zzafeVar.zzb((zzaek) zzaei.zza(sb2, type));
                }
                httpURLConnection.disconnect();
            } catch (zzaca e) {
                e = e;
                zzafeVar.zza(e.getMessage());
                httpURLConnection.disconnect();
            } catch (SocketTimeoutException unused) {
                zzafeVar.zza("TIMEOUT");
                httpURLConnection.disconnect();
            } catch (IOException e2) {
                e = e2;
                zzafeVar.zza(e.getMessage());
                httpURLConnection.disconnect();
            }
        } catch (Throwable th3) {
            httpURLConnection.disconnect();
            throw th3;
        }
    }

    private static final boolean zzd(int i) {
        return i >= 200 && i < 300;
    }
}
