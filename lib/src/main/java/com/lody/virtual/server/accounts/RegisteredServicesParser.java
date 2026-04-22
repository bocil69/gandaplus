package com.lody.virtual.server.accounts;

import android.content.Context;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

/**
 * Stub: minimal RegisteredServicesParser for SyncAdaptersCache.
 * Provides getParser() and getResources() to avoid compile errors.
 */
public class RegisteredServicesParser {

    public XmlResourceParser getParser(Context context, ServiceInfo serviceInfo, String serviceName) {
        try {
            if (serviceInfo.metaData != null) {
                int resId = serviceInfo.metaData.getInt(serviceName);
                if (resId != 0) {
                    return context.getPackageManager()
                            .getResourcesForApplication(serviceInfo.applicationInfo)
                            .getXml(resId);
                }
            }
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    public Resources getResources(Context context, Object appInfo) {
        try {
            if (appInfo instanceof android.content.pm.ApplicationInfo) {
                return context.getPackageManager()
                        .getResourcesForApplication((android.content.pm.ApplicationInfo) appInfo);
            }
        } catch (Exception e) {
            // ignore
        }
        return null;
    }
}
