package com.lody.virtual.helper.compat;

import android.os.Build;
import org.lsposed.hiddenapibypass.HiddenApiBypass;

public class HiddenApiCompat {
    private static boolean sExempted = false;

    public static void ensureExemptions() {
        if (sExempted) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                HiddenApiBypass.addHiddenApiExemptions("");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        sExempted = true;
    }
}
