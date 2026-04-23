package com.fufufu.katrina.backup;

import android.app.assist.AssistStructure;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import androidx.constraintlayout.core.motion.utils.TypedValues;
/* loaded from: classes5.dex */
public class Error {
    public static final String a = c(128522);
    public static final String b = c(128557);
    public static final String c = c(128531);
    public static final String d = c(128584);
    public static final String e = c(128561);
    public static final String f = c(128534);
    public static final String g = c(10084);

    public static boolean f(int i) {
        return (i & 2) != 0;
    }

    public static boolean g(int i) {
        return (i & 1) != 0;
    }

    public static String a(Context context, int i, String str) {
        return String.valueOf(context.getString(i)) + " " + str;
    }

    public static String b(AssistStructure.ViewNode viewNode) {
        CharSequence text = viewNode.getText();
        String trim = text != null ? text.toString().trim() : "";
        if (text == null || trim.isEmpty()) {
            String hint = viewNode.getHint();
            if (hint != null && !hint.trim().isEmpty()) {
                return hint.trim();
            }
            CharSequence contentDescription = viewNode.getContentDescription();
            if (contentDescription == null || contentDescription.toString().trim().isEmpty()) {
                return null;
            }
            return contentDescription.toString().trim();
        }
        return trim.trim();
    }

    public static String c(int i) {
        return new String(Character.toChars(i));
    }

    public static boolean d(Context context) {
        ComponentName componentName = new ComponentName(context, AssistService.class);
        String string = Settings.Secure.getString(context.getContentResolver(), "voice_interaction_service");
        return string != null && string.contains(componentName.flattenToShortString());
    }

    public static boolean e(Context context) {
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        try {
            Resources resources = context.getResources();
            return resources.getInteger(resources.getIdentifier("config_navBarInteractionMode", TypedValues.Custom.S_INT, "android")) == 2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean h(AssistStructure.ViewNode viewNode) {
        return viewNode.getVisibility() == 0 && viewNode.getAlpha() > 0.0f && viewNode.getWidth() > 0 && viewNode.getHeight() > 0;
    }

    public static boolean i(Context context, int i, int i2, int i3, int i4) {
        return i3 + i >= 0 && i <= context.getResources().getDisplayMetrics().widthPixels && i4 + i2 >= 0 && i2 <= context.getResources().getDisplayMetrics().heightPixels;
    }

    public static String j(String str) {
        if (str.length() > 64) {
            return String.valueOf(str.substring(0, 64)) + "...";
        }
        return str;
    }
}
