package io.virtualapp.delegate;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Context wrapper that spoofs the package name for GMS calls.
 * This allows the Host App to call Google Play Services on behalf
 * of the cloned app in VirtualApp.
 */
public class SpoofedContext extends ContextWrapper {
    
    private final String mHostPackageName;
    private final String mSpoofedPackageName;
    private final ApplicationInfo mSpoofedAppInfo;
    
    public SpoofedContext(Context base, String spoofedPackageName) {
        super(base);
        mHostPackageName = base.getPackageName();
        mSpoofedPackageName = spoofedPackageName;
        mSpoofedAppInfo = createSpoofedAppInfo(base, spoofedPackageName);
    }
    
    @Override
    public String getPackageName() {
        return mSpoofedPackageName;
    }
    
    @Override
    public String getOpPackageName() {
        return mSpoofedPackageName;
    }
    
    @Override
    public ApplicationInfo getApplicationInfo() {
        return mSpoofedAppInfo;
    }
    
    @Override
    public PackageManager getPackageManager() {
        return PackageManagerWrapper.createSpoofed(
                super.getPackageManager(), mHostPackageName, mSpoofedPackageName);
    }
    
    private ApplicationInfo createSpoofedAppInfo(Context base, String packageName) {
        try {
            ApplicationInfo original = base.getPackageManager().getApplicationInfo(
                    base.getPackageName(), 0);
            ApplicationInfo spoofed = new ApplicationInfo(original);
            spoofed.packageName = packageName;
            return spoofed;
        } catch (PackageManager.NameNotFoundException e) {
            return base.getApplicationInfo();
        }
    }
}
