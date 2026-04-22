package io.virtualapp.hook.network;

import android.util.Log;

import com.lody.virtual.remote.VDeviceConfig;

import java.security.cert.X509Certificate;
import java.util.Collections;

import javax.net.ssl.X509TrustManager;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class OvoSslPinningBypass {
    private static final String TAG = "OvoSslPinningBypass";

    public static void hook(ClassLoader classLoader, VDeviceConfig config) {
        try {
            // 1. Bypass okhttp3.CertificatePinner
            Class<?> certificatePinnerClass = XposedHelpers.findClassIfExists("okhttp3.CertificatePinner", classLoader);
            if (certificatePinnerClass != null) {
                XposedHelpers.findAndHookMethod(certificatePinnerClass, "check", String.class, java.util.List.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                        return null; // Do nothing, allowing all certificates
                    }
                });
                Log.i(TAG, "OkHttp CertificatePinner check bypassed.");
            }

            // 2. Bypass Custom TrustManager (Common in OVO)
            Class<?> trustManagerImplClass = XposedHelpers.findClassIfExists("com.android.org.conscrypt.TrustManagerImpl", classLoader);
            if (trustManagerImplClass == null) {
                trustManagerImplClass = XposedHelpers.findClassIfExists("org.conscrypt.TrustManagerImpl", classLoader);
            }
            if (trustManagerImplClass != null) {
                XposedHelpers.findAndHookMethod(trustManagerImplClass, "checkServerTrusted", X509Certificate[].class, String.class, String.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                        return Collections.emptyList(); // Assume trusted
                    }
                });
            }

            // 3. Optional: bypass standard HttpsURLConnection host verification
            Class<?> httpsURLConnectionClass = XposedHelpers.findClassIfExists("javax.net.ssl.HttpsURLConnection", classLoader);
            if (httpsURLConnectionClass != null) {
                XposedHelpers.findAndHookMethod(httpsURLConnectionClass, "setDefaultHostnameVerifier", javax.net.ssl.HostnameVerifier.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                        return null;
                    }
                });
                XposedHelpers.findAndHookMethod(httpsURLConnectionClass, "setHostnameVerifier", javax.net.ssl.HostnameVerifier.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                        return null;
                    }
                });
            }

            Log.i(TAG, "OvoSslPinningBypass injected successfully.");
        } catch (Throwable t) {
            Log.e(TAG, "Error injecting OvoSslPinningBypass", t);
        }
    }
}
