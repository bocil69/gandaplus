package io.virtualapp.delegate;

import android.content.ContentResolver;
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
    
    private final String mSpoofedPackageName;
    private final ApplicationInfo mSpoofedAppInfo;
    
    public SpoofedContext(Context base, String spoofedPackageName) {
        super(base);
        this.mSpoofedPackageName = spoofedPackageName;
        this.mSpoofedAppInfo = createSpoofedAppInfo(base, spoofedPackageName);
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
        return new SpoofedPackageManager(super.getPackageManager(), mSpoofedPackageName);
    }
    
    @Override
    public ContentResolver getContentResolver() {
        // Return original content resolver but with spoofed authority
        return super.getContentResolver();
    }
    
    private ApplicationInfo createSpoofedAppInfo(Context base, String packageName) {
        try {
            ApplicationInfo original = base.getPackageManager().getApplicationInfo(
                    base.getPackageName(), 0);
            
            // Create a copy with spoofed package name
            ApplicationInfo spoofed = new ApplicationInfo(original);
            spoofed.packageName = packageName;
            
            return spoofed;
        } catch (PackageManager.NameNotFoundException e) {
            // Fallback to base app info
            return base.getApplicationInfo();
        }
    }
    
    /**
     * PackageManager wrapper that reports the spoofed package name
     */
    private static class SpoofedPackageManager extends PackageManagerWrapper {
        private final String mSpoofedPackage;
        
        SpoofedPackageManager(PackageManager base, String spoofedPackage) {
            super(base);
            this.mSpoofedPackage = spoofedPackage;
        }
        
        @Override
        public String getNameForUid(int uid) {
            // Return spoofed package name for our UID
            String original = super.getNameForUid(uid);
            if (original != null && original.equals(getBaseContext().getPackageName())) {
                return mSpoofedPackage;
            }
            return original;
        }
        
        @Override
        public String[] getPackagesForUid(int uid) {
            String[] original = super.getPackagesForUid(uid);
            if (original != null) {
                for (int i = 0; i < original.length; i++) {
                    if (original[i].equals(getBaseContext().getPackageName())) {
                        original[i] = mSpoofedPackage;
                    }
                }
            }
            return original;
        }
    }
}
