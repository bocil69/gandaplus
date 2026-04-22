package com.xdja.zs;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.remote.InstalledAppInfo;
import java.net.URLDecoder;
import java.net.URLEncoder;

/*适配统一认证，使用scheme方式，盒内外应用相互调用*/
public class UacProxyActivity extends Activity {

    public static String IAM_URI = "xdja";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("wxd", "UacProxyActivity onCreate");
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }

        Uri uri = intent.getData();
        if (uri == null) {
            finish();
            return;
        }

        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            // Handle UAC/xdja legacy flow
            String value_uri = uri.getQueryParameter("real_uri");
            if (value_uri != null) {
                try {
                    String real_uri = URLDecoder.decode(value_uri, "UTF-8");
                    Log.e("wxd", "real_uri : " + real_uri);
                    Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(real_uri));
                    // Check if it's a generic deep link for a cloned app
                    if (handleGenericDeepLink(newIntent)) {
                        finish();
                        return;
                    }
                    // If not handled by picker, fall back to start as user 0 ONLY if explicitly allowed or safe
                    // VActivityManager.get().startActivity(newIntent, 0);
                } catch (Exception e) {

                    e.printStackTrace();
                }
                finish();
                return;
            }

            // Handle direct deep link ingress
            if (handleGenericDeepLink(intent)) {
                finish();
                return;
            }
        }
        finish();
    }

    private boolean handleGenericDeepLink(Intent intent) {
        try {
            Log.e("wxd", "handleGenericDeepLink: " + intent);
            
            // Stage 0: Explicit package mapping for supported families
            // This is the most robust way to handle schemes that the host PM might hide after host selection
            Uri uri = intent.getData();
            if (uri != null) {
                String scheme = uri.getScheme();
                String targetPkg = null;
                if ("grab".equals(scheme) || (uri.getHost() != null && uri.getHost().contains("grab"))) {
                    // Try passenger then driver
                    if (VirtualCore.get().isAppInstalled("com.grabtaxi.passenger")) {
                        targetPkg = "com.grabtaxi.passenger";
                    } else if (VirtualCore.get().isAppInstalled("com.grab.driver")) {
                        targetPkg = "com.grab.driver";
                    }
                } else if ("ovo".equals(scheme) || (uri.getHost() != null && uri.getHost().contains("ovo"))) {
                    targetPkg = "ovo.id";
                } else if ("eskimo".equals(scheme)) {
                    targetPkg = "travel.eskimo.esim";
                }

                if (targetPkg != null && VirtualCore.get().isAppInstalled(targetPkg)) {
                    Intent testIntent = new Intent(intent);
                    testIntent.setPackage(targetPkg);
                    if ("ovo.id".equals(targetPkg) || VirtualCore.get().resolveActivityInfo(testIntent, 0) != null) {
                        Log.e("wxd", "Family match for scheme " + scheme + ": " + targetPkg);
                        return launchPicker(intent, targetPkg);
                    }
                }
            }

            // Stage 1: Try to query handlers from the host PM
            android.content.pm.PackageManager pm = getPackageManager();
            java.util.List<android.content.pm.ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, android.content.pm.PackageManager.MATCH_ALL);
            
            if (resolveInfos != null && !resolveInfos.isEmpty()) {
                for (android.content.pm.ResolveInfo info : resolveInfos) {
                    if (info.activityInfo != null) {
                        String targetPkg = info.activityInfo.packageName;
                        Log.e("wxd", "Candidate handler from PM: " + targetPkg);
                        // Exclude host app itself to find the real target app
                        if (!targetPkg.equals(getPackageName()) && VirtualCore.get().isAppInstalled(targetPkg)) {
                            return launchPicker(intent, targetPkg);
                        }
                    }
                }
            }

            // Stage 2: Internal resolution fallback
            java.util.List<InstalledAppInfo> installedApps = VirtualCore.get().getInstalledApps(0);
            for (InstalledAppInfo appInfo : installedApps) {
                // If the intent has an explicit package, check it
                if (intent.getPackage() != null && intent.getPackage().equals(appInfo.packageName)) {
                    return launchPicker(intent, appInfo.packageName);
                }
                // Check if this specific app can handle the intent internally
                android.content.pm.ActivityInfo info = VirtualCore.get().resolveActivityInfo(intent, 0);
                if (info != null) {
                    String pkg = info.packageName;
                    if (VirtualCore.get().isAppInstalled(pkg)) {
                        return launchPicker(intent, pkg);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("wxd", "handleGenericDeepLink error", e);
        }
        return false;
    }

    private boolean launchPicker(Intent intent, String targetPkg) {
        Log.e("wxd", "Intercepted deep link for installed pkg: " + targetPkg);
        
        // Clean the intent to ensure it routes correctly within VA
        // 1. Remove explicit component (host proxy)
        intent.setComponent(null);
        // 2. Explicitly set the target package to ensure internal resolution knows where to route
        intent.setPackage(targetPkg);
        
        Intent pickerIntent = VirtualCore.getConfig().getDeepLinkPickerIntent(intent, targetPkg);
        if (pickerIntent != null) {
            // Ensure picker starts in a new task to prevent host-task switching from burying it
            pickerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(pickerIntent);
            return true;
        }
        return false;
    }

    public static Intent isHook(Intent intent){
        Uri uri = intent.getData();
        Log.e("wxd", "UacProxyActivity ishook intent " + intent);
        try {
            if (uri.getHost() != null && uri.getHost().equals("iam")) {
                Uri.Builder builder = Uri.parse(uri.toString()).buildUpon();

                String head = "xdja://" + VirtualCore.get().getHostPkg() + "/authorize?";
                String head_enc = URLEncoder.encode(head, "UTF-8");

                builder.appendQueryParameter("agent", head_enc);
                uri = builder.build();
                intent.setData(uri);
                Log.e("wxd", "UacProxyActivity hooked intent " + intent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return intent;
    }
}
