package io.virtualapp.delegate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ContainerEncryptionParams;
import android.content.pm.FeatureInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.InstallSourceInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.ModuleInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.VersionedPackage;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import java.util.List;

/**
 * Wrapper class for PackageManager that delegates all calls to base.
 * Can be extended to override specific methods.
 */
public class PackageManagerWrapper extends PackageManager {
    
    private final PackageManager mBase;
    
    public PackageManagerWrapper(PackageManager base) {
        mBase = base;
    }
    
    protected PackageManager getBase() {
        return mBase;
    }
    
    protected Context getBaseContext() {
        return null; // Override if needed
    }
    
    @Override
    public PackageInfo getPackageInfo(@NonNull String packageName, int flags) throws NameNotFoundException {
        return mBase.getPackageInfo(packageName, flags);
    }
    
    @Override
    public PackageInfo getPackageInfo(@NonNull VersionedPackage versionedPackage, int flags) throws NameNotFoundException {
        return mBase.getPackageInfo(versionedPackage, flags);
    }
    
    @Override
    public String[] currentToCanonicalPackageNames(@NonNull String[] names) {
        return mBase.currentToCanonicalPackageNames(names);
    }
    
    @Override
    public String[] canonicalToCurrentPackageNames(@NonNull String[] names) {
        return mBase.canonicalToCurrentPackageNames(names);
    }
    
    @Override
    public Intent getLaunchIntentForPackage(@NonNull String packageName) {
        return mBase.getLaunchIntentForPackage(packageName);
    }
    
    @Override
    public Intent getLeanbackLaunchIntentForPackage(@NonNull String packageName) {
        return mBase.getLeanbackLaunchIntentForPackage(packageName);
    }
    
    @Override
    public int[] getPackageGids(@NonNull String packageName) throws NameNotFoundException {
        return mBase.getPackageGids(packageName);
    }
    
    @Override
    public int[] getPackageGids(@NonNull String packageName, int flags) throws NameNotFoundException {
        return mBase.getPackageGids(packageName, flags);
    }
    
    @Override
    public int getPackageUid(@NonNull String packageName, int flags) throws NameNotFoundException {
        return mBase.getPackageUid(packageName, flags);
    }
    
    @Override
    public PermissionInfo getPermissionInfo(@NonNull String name, int flags) throws NameNotFoundException {
        return mBase.getPermissionInfo(name, flags);
    }
    
    @Override
    public List<PermissionInfo> queryPermissionsByGroup(@NonNull String group, int flags) throws NameNotFoundException {
        return mBase.queryPermissionsByGroup(group, flags);
    }
    
    @Override
    public PermissionGroupInfo getPermissionGroupInfo(@NonNull String name, int flags) throws NameNotFoundException {
        return mBase.getPermissionGroupInfo(name, flags);
    }
    
    @Override
    public List<PermissionGroupInfo> getAllPermissionGroups(int flags) {
        return mBase.getAllPermissionGroups(flags);
    }
    
    @Override
    public ApplicationInfo getApplicationInfo(@NonNull String packageName, int flags) throws NameNotFoundException {
        return mBase.getApplicationInfo(packageName, flags);
    }
    
    @Override
    public ActivityInfo getActivityInfo(@NonNull ComponentName component, int flags) throws NameNotFoundException {
        return mBase.getActivityInfo(component, flags);
    }
    
    @Override
    public ActivityInfo getReceiverInfo(@NonNull ComponentName component, int flags) throws NameNotFoundException {
        return mBase.getReceiverInfo(component, flags);
    }
    
    @Override
    public ServiceInfo getServiceInfo(@NonNull ComponentName component, int flags) throws NameNotFoundException {
        return mBase.getServiceInfo(component, flags);
    }
    
    @Override
    public ProviderInfo getProviderInfo(@NonNull ComponentName component, int flags) throws NameNotFoundException {
        return mBase.getProviderInfo(component, flags);
    }
    
    @Override
    public List<PackageInfo> getInstalledPackages(int flags) {
        return mBase.getInstalledPackages(flags);
    }
    
    @Override
    public List<PackageInfo> getPackagesHoldingPermissions(@NonNull String[] permissions, int flags) {
        return mBase.getPackagesHoldingPermissions(permissions, flags);
    }
    
    @Override
    public int checkPermission(@NonNull String permName, @NonNull String pkgName) {
        return mBase.checkPermission(permName, pkgName);
    }
    
    @Override
    public boolean isPermissionRevokedByPolicy(@NonNull String permName, @NonNull String pkgName) {
        return mBase.isPermissionRevokedByPolicy(permName, pkgName);
    }
    
    @Override
    public String getInstallerPackageName(@NonNull String packageName) {
        return mBase.getInstallerPackageName(packageName);
    }
    
    @Override
    public InstallSourceInfo getInstallSourceInfo(@NonNull String packageName) throws NameNotFoundException {
        return mBase.getInstallSourceInfo(packageName);
    }
    
    @Override
    public void addPackageToPreferred(@NonNull String packageName) {
        mBase.addPackageToPreferred(packageName);
    }
    
    @Override
    public void removePackageFromPreferred(@NonNull String packageName) {
        mBase.removePackageFromPreferred(packageName);
    }
    
    @Override
    public List<PackageInfo> getPreferredPackages(int flags) {
        return mBase.getPreferredPackages(flags);
    }
    
    @Override
    public void addPreferredActivity(@NonNull IntentFilter filter, int match, @Nullable ComponentName[] set, @NonNull ComponentName activity) {
        mBase.addPreferredActivity(filter, match, set, activity);
    }
    
    @Override
    public void clearPackagePreferredActivities(@NonNull String packageName) {
        mBase.clearPackagePreferredActivities(packageName);
    }
    
    @Override
    public int getPreferredActivities(@NonNull List<IntentFilter> outFilters, @NonNull List<ComponentName> outActivities, @Nullable String packageName) {
        return mBase.getPreferredActivities(outFilters, outActivities, packageName);
    }
    
    @Override
    public void setApplicationEnabledSetting(@NonNull String packageName, int newState, int flags) {
        mBase.setApplicationEnabledSetting(packageName, newState, flags);
    }
    
    @Override
    public int getApplicationEnabledSetting(@NonNull String packageName) {
        return mBase.getApplicationEnabledSetting(packageName);
    }
    
    @Override
    public void setComponentEnabledSetting(@NonNull ComponentName componentName, int newState, int flags) {
        mBase.setComponentEnabledSetting(componentName, newState, flags);
    }
    
    @Override
    public int getComponentEnabledSetting(@NonNull ComponentName componentName) {
        return mBase.getComponentEnabledSetting(componentName);
    }
    
    @Override
    public boolean setApplicationHiddenSettingAsUser(@NonNull String packageName, boolean hidden, @Nullable UserHandle user) {
        return mBase.setApplicationHiddenSettingAsUser(packageName, hidden, user);
    }
    
    @Override
    public boolean getApplicationHiddenSettingAsUser(@NonNull String packageName, @Nullable UserHandle user) {
        return mBase.getApplicationHiddenSettingAsUser(packageName, user);
    }
    
    @Override
    public void setPackagesSuspendedAsUser(@NonNull String[] packageNames, boolean suspended, @Nullable UserHandle userHandle) {
        mBase.setPackagesSuspendedAsUser(packageNames, suspended, userHandle);
    }
    
    @Override
    public boolean isPackageSuspendedForUser(@NonNull String packageName, int userId) {
        return mBase.isPackageSuspendedForUser(packageName, userId);
    }
    
    @Override
    public void flushPackageRestrictionsAsUser(int userId) {
        mBase.flushPackageRestrictionsAsUser(userId);
    }
    
    @Override
    public boolean setPackageStoppedState(@NonNull String packageName, boolean stopped, int userId) {
        return mBase.setPackageStoppedState(packageName, stopped, userId);
    }
    
    @Override
    public String[] getRevokedPermissions(@NonNull String packageName) throws NameNotFoundException {
        return mBase.getRevokedPermissions(packageName);
    }
    
    @Override
    public void revokeRuntimePermission(@NonNull String packageName, @NonNull String permName, @Nullable UserHandle user) {
        mBase.revokeRuntimePermission(packageName, permName, user);
    }
    
    @Override
    public int getAutoRevokePermissionsMode(@NonNull String packageName) throws NameNotFoundException {
        return mBase.getAutoRevokePermissionsMode(packageName);
    }
    
    @Override
    public void setAutoRevokePermissionsMode(@NonNull String packageName, boolean allowAutoRevoke) throws NameNotFoundException {
        mBase.setAutoRevokePermissionsMode(packageName, allowAutoRevoke);
    }
    
    @Override
    public void getPackageSizeInfoAsUser(@NonNull String packageName, int userHandle, @Nullable IPackageStatsObserver observer) {
        mBase.getPackageSizeInfoAsUser(packageName, userHandle, observer);
    }
    
    @Override
    public void deletePackageAsUser(@NonNull String packageName, @Nullable IPackageDeleteObserver observer, int flags, int userId) {
        mBase.deletePackageAsUser(packageName, observer, flags, userId);
    }
    
    @Override
    public void deletePackage(@NonNull String packageName, @Nullable IPackageDeleteObserver observer, int flags) {
        mBase.deletePackage(packageName, observer, flags);
    }
    
    @Override
    public void installPackage(@NonNull Uri packageURI, @Nullable IPackageInstallObserver observer, int flags, @Nullable String installerPackageName) {
        mBase.installPackage(packageURI, observer, flags, installerPackageName);
    }
    
    @Override
    public void installPackage(@NonNull Uri packageURI, @Nullable PackageInstallObserver observer, int flags, @Nullable String installerPackageName) {
        mBase.installPackage(packageURI, observer, flags, installerPackageName);
    }
    
    @Override
    public void installPackage(@NonNull Uri packageURI, @Nullable PackageInstallObserver observer, int flags, @Nullable String installerPackageName, @Nullable VerificationParams verificationParams, @Nullable String packageAbiOverride) {
        mBase.installPackage(packageURI, observer, flags, installerPackageName, verificationParams, packageAbiOverride);
    }
    
    @Override
    public void installPackage(@NonNull Uri packageURI, @Nullable PackageInstallObserver observer, int flags, @Nullable String installerPackageName, @Nullable ContainerEncryptionParams encryptionParams, @Nullable String packageAbiOverride) {
        mBase.installPackage(packageURI, observer, flags, installerPackageName, encryptionParams, packageAbiOverride);
    }
    
    @Override
    public void installPackage(@NonNull Uri packageURI, @Nullable PackageInstallObserver observer, int flags, @Nullable String installerPackageName, @Nullable VerificationParams verificationParams, @Nullable ContainerEncryptionParams encryptionParams, @Nullable String packageAbiOverride) {
        mBase.installPackage(packageURI, observer, flags, installerPackageName, verificationParams, encryptionParams, packageAbiOverride);
    }
    
    @Override
    public int installExistingPackage(@NonNull String packageName) throws NameNotFoundException {
        return mBase.installExistingPackage(packageName);
    }
    
    @Override
    public int installExistingPackage(@NonNull String packageName, int installReason) throws NameNotFoundException {
        return mBase.installExistingPackage(packageName, installReason);
    }
    
    @Override
    public int installExistingPackageAsUser(@NonNull String packageName, int userId) throws NameNotFoundException {
        return mBase.installExistingPackageAsUser(packageName, userId);
    }
    
    @Override
    public int installExistingPackageAsUser(@NonNull String packageName, int userId, int installReason) throws NameNotFoundException {
        return mBase.installExistingPackageAsUser(packageName, userId, installReason);
    }
    
    @Override
    public void verifyPendingInstall(int id, int verificationCode) {
        mBase.verifyPendingInstall(id, verificationCode);
    }
    
    @Override
    public void extendVerificationTimeout(int id, int verificationCodeAtTimeout, long millisecondsToDelay) {
        mBase.extendVerificationTimeout(id, verificationCodeAtTimeout, millisecondsToDelay);
    }
    
    @Override
    public void verifyIntent(int id, int verificationCode, List<String> failedURLs) {
        mBase.verifyIntent(id, verificationCode, failedURLs);
    }
    
    @Override
    public void setInstallerPackageName(@NonNull String targetPackage, @Nullable String installerPackageName) {
        mBase.setInstallerPackageName(targetPackage, installerPackageName);
    }
    
    @Override
    public void movePackage(@NonNull String packageName, @NonNull IPackageMoveObserver observer, int flags) {
        mBase.movePackage(packageName, observer, flags);
    }
    
    @Override
    public void deleteApplicationCacheFiles(@NonNull String packageName, @Nullable IPackageDataObserver observer) {
        mBase.deleteApplicationCacheFiles(packageName, observer);
    }
    
    @Override
    public void deleteApplicationCacheFilesAsUser(@NonNull String packageName, int userId, @Nullable IPackageDataObserver observer) {
        mBase.deleteApplicationCacheFilesAsUser(packageName, userId, observer);
    }
    
    @Override
    public void freeStorageAndNotify(@Nullable String volumeUuid, long idealStorageSize, @Nullable IPackageDataObserver observer) {
        mBase.freeStorageAndNotify(volumeUuid, idealStorageSize, observer);
    }
    
    @Override
    public void freeStorage(@Nullable String volumeUuid, long idealStorageSize, @Nullable IntentSender pi) {
        mBase.freeStorage(volumeUuid, idealStorageSize, pi);
    }
    
    @Override
    public void deletePackageCacheFiles(@NonNull String packageName, @Nullable IPackageDataObserver observer) {
        mBase.deletePackageCacheFiles(packageName, observer);
    }
    
    @Override
    public void clearApplicationUserData(@NonNull String packageName, @Nullable IPackageDataObserver observer) {
        mBase.clearApplicationUserData(packageName, observer);
    }
    
    @Override
    public void deleteApplicationUserData(@NonNull String packageName, int userId, @Nullable IPackageDataObserver observer) {
        mBase.deleteApplicationUserData(packageName, userId, observer);
    }
    
    @Override
    public void clearPackagePreferredActivitiesAsUser(@NonNull String packageName, int userId) {
        mBase.clearPackagePreferredActivitiesAsUser(packageName, userId);
    }
    
    @Override
    public void addPreferredActivityAsUser(@NonNull IntentFilter filter, int match, @Nullable ComponentName[] set, @NonNull ComponentName activity, int userId) {
        mBase.addPreferredActivityAsUser(filter, match, set, activity, userId);
    }
    
    @Override
    public void replacePreferredActivityAsUser(@NonNull IntentFilter filter, int match, @NonNull ComponentName[] set, @NonNull ComponentName activity, int userId) {
        mBase.replacePreferredActivityAsUser(filter, match, set, activity, userId);
    }
    
    @Override
    public void replacePreferredActivity(@NonNull IntentFilter filter, int match, @NonNull ComponentName[] set, @NonNull ComponentName activity) {
        mBase.replacePreferredActivity(filter, match, set, activity);
    }
    
    @Override
    public void clearPreferredActivities(@NonNull IntentFilter filter) {
        mBase.clearPreferredActivities(filter);
    }
    
    @Override
    public Resources getResourcesForActivity(@NonNull ComponentName activityName) throws NameNotFoundException {
        return mBase.getResourcesForActivity(activityName);
    }
    
    @Override
    public Resources getResourcesForApplication(@NonNull ApplicationInfo app) throws NameNotFoundException {
        return mBase.getResourcesForApplication(app);
    }
    
    @Override
    public Resources getResourcesForApplication(@NonNull String appPackageName) throws NameNotFoundException {
        return mBase.getResourcesForApplication(appPackageName);
    }
    
    @Override
    public Resources getResourcesForApplicationAsUser(@NonNull String appPackageName, int userId) throws NameNotFoundException {
        return mBase.getResourcesForApplicationAsUser(appPackageName, userId);
    }
    
    @Override
    public PackageItemInfo getActivityLogo(@NonNull ComponentName activityName) throws NameNotFoundException {
        return mBase.getActivityLogo(activityName);
    }
    
    @Override
    public Drawable getActivityLogo(@NonNull Intent intent) throws NameNotFoundException {
        return mBase.getActivityLogo(intent);
    }
    
    @Override
    public Drawable getApplicationLogo(@NonNull ApplicationInfo info) {
        return mBase.getApplicationLogo(info);
    }
    
    @Override
    public Drawable getApplicationLogo(@NonNull String packageName) throws NameNotFoundException {
        return mBase.getApplicationLogo(packageName);
    }
    
    @Override
    public Drawable getUserBadgedIcon(Drawable icon, UserHandle user) {
        return mBase.getUserBadgedIcon(icon, user);
    }
    
    @Override
    public Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle user, Rect badgeLocation, int badgeDensity) {
        return mBase.getUserBadgedDrawableForDensity(drawable, user, badgeLocation, badgeDensity);
    }
    
    @Override
    public CharSequence getUserBadgedLabel(CharSequence label, UserHandle user) {
        return mBase.getUserBadgedLabel(label, user);
    }
    
    @Override
    public CharSequence getText(@NonNull String packageName, int resid, @Nullable ApplicationInfo appInfo) {
        return mBase.getText(packageName, resid, appInfo);
    }
    
    @Override
    public XmlResourceParser getXml(@NonNull String packageName, int resid, @Nullable ApplicationInfo appInfo) {
        return mBase.getXml(packageName, resid, appInfo);
    }
    
    @Override
    public CharSequence getApplicationLabel(@NonNull ApplicationInfo info) {
        return mBase.getApplicationLabel(info);
    }
    
    @Override
    public void getPackageSizeInfo(@NonNull String packageName, int userHandle, @Nullable IPackageStatsObserver observer) {
        mBase.getPackageSizeInfo(packageName, userHandle, observer);
    }
    
    @Override
    public String getSystemSharedLibraryNames() {
        return mBase.getSystemSharedLibraryNames();
    }
    
    @Override
    public SharedLibraryInfo getSharedLibraryInfo(@NonNull String libraryName, int flags) throws NameNotFoundException {
        return mBase.getSharedLibraryInfo(libraryName, flags);
    }
    
    @Override
    public List<SharedLibraryInfo> getSharedLibraries(int flags) {
        return mBase.getSharedLibraries(flags);
    }
    
    @Override
    public boolean isSharedLibraryBackupAndRestoreEnabled(@NonNull String libraryName) {
        return mBase.isSharedLibraryBackupAndRestoreEnabled(libraryName);
    }
    
    @Override
    public void registerMoveCallback(@NonNull MoveCallback callback, @Nullable Handler handler) {
        mBase.registerMoveCallback(callback, handler);
    }
    
    @Override
    public void unregisterMoveCallback(@NonNull MoveCallback callback) {
        mBase.unregisterMoveCallback(callback);
    }
    
    @Override
    public int getMoveStatus(int moveId) {
        return mBase.getMoveStatus(moveId);
    }
    
    @Override
    public void addOnPermissionsChangeListener(@NonNull OnPermissionsChangedListener listener) {
        mBase.addOnPermissionsChangeListener(listener);
    }
    
    @Override
    public void removeOnPermissionsChangeListener(@NonNull OnPermissionsChangedListener listener) {
        mBase.removeOnPermissionsChangeListener(listener);
    }
    
    @Override
    public PackageInstaller getPackageInstaller() {
        return mBase.getPackageInstaller();
    }
    
    @Override
    public boolean isUpgrade() {
        return mBase.isUpgrade();
    }
    
    @Override
    public boolean isPackageAvailable(@NonNull String packageName) {
        return mBase.isPackageAvailable(packageName);
    }
    
    @Override
    public void addCrossProfileIntentFilter(@NonNull IntentFilter filter, int sourceUserId, int targetUserId, int flags) {
        mBase.addCrossProfileIntentFilter(filter, sourceUserId, targetUserId, flags);
    }
    
    @Override
    public void clearCrossProfileIntentFilters(int userId) {
        mBase.clearCrossProfileIntentFilters(userId);
    }
    
    @Override
    public Drawable loadItemIcon(PackageItemInfo itemInfo, ApplicationInfo appInfo) {
        return mBase.loadItemIcon(itemInfo, appInfo);
    }
    
    @Override
    public Drawable loadUnbadgedItemIcon(PackageItemInfo itemInfo, ApplicationInfo appInfo) {
        return mBase.loadUnbadgedItemIcon(itemInfo, appInfo);
    }
    
    @Override
    public boolean isSignedBy(@NonNull String packageName, @NonNull KeySet ks) throws NameNotFoundException {
        return mBase.isSignedBy(packageName, ks);
    }
    
    @Override
    public boolean isSignedByExactly(@NonNull String packageName, @NonNull KeySet ks) throws NameNotFoundException {
        return mBase.isSignedByExactly(packageName, ks);
    }
    
    @Override
    public boolean hasSigningCertificate(@NonNull String packageName, @NonNull byte[] certificate, @PackageManager.CertificateInputType int type) {
        return mBase.hasSigningCertificate(packageName, certificate, type);
    }
    
    @Override
    public boolean hasSigningCertificate(@NonNull String packageName, @NonNull byte[] certificate, @PackageManager.CertificateInputType int type, int flags) {
        return mBase.hasSigningCertificate(packageName, certificate, type, flags);
    }
    
    @Override
    public KeySet getKeySetByAlias(@NonNull String packageName, @NonNull String alias) throws NameNotFoundException {
        return mBase.getKeySetByAlias(packageName, alias);
    }
    
    @Override
    public KeySet getSigningKeySet(@NonNull String packageName) throws NameNotFoundException {
        return mBase.getSigningKeySet(packageName);
    }
    
    @Override
    public String getInstantAppCookie(@NonNull String packageName, @NonNull UserHandle user) {
        return mBase.getInstantAppCookie(packageName, user);
    }
    
    @Override
    public boolean setInstantAppCookie(@NonNull String packageName, List<String> cookies, @NonNull UserHandle user) {
        return mBase.setInstantAppCookie(packageName, cookies, user);
    }
    
    @Override
    public boolean isInstantApp(@NonNull String packageName) {
        return mBase.isInstantApp(packageName);
    }
    
    @Override
    public boolean isInstantApp(@NonNull String packageName, int userId) {
        return mBase.isInstantApp(packageName, userId);
    }
    
    @Override
    public int getInstantAppCookieMaxSize(@NonNull String packageName) {
        return mBase.getInstantAppCookieMaxSize(packageName);
    }
    
    @Override
    public int getInstantAppCookieMaxSize(@NonNull String packageName, @NonNull UserHandle user) {
        return mBase.getInstantAppCookieMaxSize(packageName, user);
    }
    
    @Override
    public int getInstantAppCookieSize(@NonNull String packageName, @NonNull UserHandle user) {
        return mBase.getInstantAppCookieSize(packageName, user);
    }
    
    @Override
    public int getInstantAppCookieSize(@NonNull String packageName) {
        return mBase.getInstantAppCookieSize(packageName);
    }
    
    @Override
    public void addWhitelistedRestrictedPermission(@NonNull String packageName, @NonNull String permission, int whitelistFlags) {
        mBase.addWhitelistedRestrictedPermission(packageName, permission, whitelistFlags);
    }
    
    @Override
    public void removeWhitelistedRestrictedPermission(@NonNull String packageName, @NonNull String permission, int whitelistFlags) {
        mBase.removeWhitelistedRestrictedPermission(packageName, permission, whitelistFlags);
    }
    
    @Override
    public Set<String> getWhitelistedRestrictedPermissions(@NonNull String packageName, int whitelistFlags) {
        return mBase.getWhitelistedRestrictedPermissions(packageName, whitelistFlags);
    }
    
    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return mBase.shouldShowRequestPermissionRationale(permission);
    }
    
    @Override
    public boolean isPermissionReviewModeEnabled() {
        return mBase.isPermissionReviewModeEnabled();
    }
    
    @Override
    public List<ModuleInfo> getInstalledModules(int flags) {
        return mBase.getInstalledModules(flags);
    }
    
    @Override
    public ModuleInfo getModuleInfo(@NonNull String packageName, int flags) throws NameNotFoundException {
        return mBase.getModuleInfo(packageName, flags);
    }
    
    @Override
    public void setHarmfulAppWarning(@NonNull String packageName, @Nullable CharSequence warning) {
        mBase.setHarmfulAppWarning(packageName, warning);
    }
    
    @Override
    public CharSequence getHarmfulAppWarning(@NonNull String packageName) {
        return mBase.getHarmfulAppWarning(packageName);
    }
    
    @Override
    public boolean isDeviceUpgrading() {
        return mBase.isDeviceUpgrading();
    }
    
    @Override
    public boolean hasSystemFeature(@NonNull String name) {
        return mBase.hasSystemFeature(name);
    }
    
    @Override
    public boolean hasSystemFeature(@NonNull String name, int version) {
        return mBase.hasSystemFeature(name, version);
    }
    
    @Override
    public int checkSignatures(@NonNull String pkg1, @NonNull String pkg2) {
        return mBase.checkSignatures(pkg1, pkg2);
    }
    
    @Override
    public int checkSignatures(int uid1, int uid2) {
        return mBase.checkSignatures(uid1, uid2);
    }
    
    @Override
    public String[] getPackagesForUid(int uid) {
        return mBase.getPackagesForUid(uid);
    }
    
    @Override
    public String getNameForUid(int uid) {
        return mBase.getNameForUid(uid);
    }
    
    @Override
    public List<ApplicationInfo> getInstalledApplications(int flags) {
        return mBase.getInstalledApplications(flags);
    }
    
    @Override
    public List<ApplicationInfo> getInstalledApplicationsAsUser(int flags, int userId) {
        return mBase.getInstalledApplicationsAsUser(flags, userId);
    }
    
    @Override
    public String[] getSystemSharedLibraryNames() {
        return mBase.getSystemSharedLibraryNames();
    }
    
    @Override
    public FeatureInfo[] getSystemAvailableFeatures() {
        return mBase.getSystemAvailableFeatures();
    }
    
    @Override
    public boolean hasSystemFeature(String name) {
        return mBase.hasSystemFeature(name);
    }
    
    @Override
    public ResolveInfo resolveActivity(Intent intent, int flags) {
        return mBase.resolveActivity(intent, flags);
    }
    
    @Override
    public List<ResolveInfo> queryIntentActivities(Intent intent, int flags) {
        return mBase.queryIntentActivities(intent, flags);
    }
    
    @Override
    public List<ResolveInfo> queryIntentActivityOptions(ComponentName caller, Intent[] specifics, Intent intent, int flags) {
        return mBase.queryIntentActivityOptions(caller, specifics, intent, flags);
    }
    
    @Override
    public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags) {
        return mBase.queryBroadcastReceivers(intent, flags);
    }
    
    @Override
    public ResolveInfo resolveService(Intent intent, int flags) {
        return mBase.resolveService(intent, flags);
    }
    
    @Override
    public List<ResolveInfo> queryIntentServices(Intent intent, int flags) {
        return mBase.queryIntentServices(intent, flags);
    }
    
    @Override
    public List<ResolveInfo> queryIntentContentProviders(Intent intent, int flags) {
        return mBase.queryIntentContentProviders(intent, flags);
    }
    
    @Override
    public ProviderInfo resolveContentProvider(String name, int flags) {
        return mBase.resolveContentProvider(name, flags);
    }
    
    @Override
    public List<ProviderInfo> queryContentProviders(String processName, int uid, int flags) {
        return mBase.queryContentProviders(processName, uid, flags);
    }
    
    @Override
    public InstrumentationInfo getInstrumentationInfo(ComponentName className, int flags) throws NameNotFoundException {
        return mBase.getInstrumentationInfo(className, flags);
    }
    
    @Override
    public List<InstrumentationInfo> queryInstrumentation(String targetPackage, int flags) {
        return mBase.queryInstrumentation(targetPackage, flags);
    }
    
    @Override
    public Drawable getDrawable(String packageName, int resid, ApplicationInfo appInfo) {
        return mBase.getDrawable(packageName, resid, appInfo);
    }
    
    @Override
    public Drawable getActivityIcon(ComponentName activityName) throws NameNotFoundException {
        return mBase.getActivityIcon(activityName);
    }
    
    @Override
    public Drawable getActivityIcon(Intent intent) throws NameNotFoundException {
        return mBase.getActivityIcon(intent);
    }
    
    @Override
    public Drawable getDefaultActivityIcon() {
        return mBase.getDefaultActivityIcon();
    }
    
    @Override
    public Drawable getApplicationIcon(ApplicationInfo info) {
        return mBase.getApplicationIcon(info);
    }
    
    @Override
    public Drawable getApplicationIcon(String packageName) throws NameNotFoundException {
        return mBase.getApplicationIcon(packageName);
    }
    
    @Override
    public Object getReceiverInfo(ComponentName component, int flags) throws NameNotFoundException {
        return mBase.getReceiverInfo(component, flags);
    }
    
    @Override
    public Object getServiceInfo(ComponentName component, int flags) throws NameNotFoundException {
        return mBase.getServiceInfo(component, flags);
    }
}
