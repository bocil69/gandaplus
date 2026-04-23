package io.virtualapp.home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lody.virtual.client.core.VirtualCore;

public class DeepLinkProxyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null || intent.getData() == null || intent.getAction() == null) {
            finish();
            return;
        }

        String targetPackage = resolveTargetPackage(intent.getData());
        if (targetPackage == null || !VirtualCore.get().isAppInstalled(targetPackage)) {
            finish();
            return;
        }

        Intent virtualIntent = new Intent(intent);
        virtualIntent.setComponent(null);
        virtualIntent.setPackage(targetPackage);

        Intent pickerIntent = VirtualCore.getConfig().getDeepLinkPickerIntent(virtualIntent, targetPackage);
        if (pickerIntent != null) {
            startActivity(pickerIntent);
        }
        finish();
    }

    @Nullable
    private String resolveTargetPackage(Uri uri) {
        String scheme = uri.getScheme();
        String host = uri.getHost();
        if ("ovo".equalsIgnoreCase(scheme) || (host != null && host.contains("ovo"))) {
            return "ovo.id";
        }
        if ("grab".equalsIgnoreCase(scheme) || (host != null && host.contains("grab"))) {
            if (VirtualCore.get().isAppInstalled("com.grabtaxi.passenger")) {
                return "com.grabtaxi.passenger";
            }
            if (VirtualCore.get().isAppInstalled("com.grab.driver")) {
                return "com.grab.driver";
            }
        }
        if ("eskimo".equalsIgnoreCase(scheme) || (host != null && host.contains("eskimo"))) {
            return "travel.eskimo.esim";
        }
        return null;
    }
}
