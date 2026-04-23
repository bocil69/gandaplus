package com.termfu.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import com.termux.terminal.EmulatorDebug;
import com.termux.terminal.TerminalSession;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class TermuxPreferences {
    static final int BELL_BEEP = 2;
    static final int BELL_IGNORE = 3;
    static final int BELL_VIBRATE = 1;
    private static final String CURRENT_SESSION_KEY = "current_session";
    private static final String FONTSIZE_KEY = "fontsize";
    private static final int MAX_FONTSIZE = 256;
    private static final String SCREEN_ALWAYS_ON_KEY = "screen_always_on";
    static final int SHORTCUT_ACTION_CREATE_SESSION = 1;
    static final int SHORTCUT_ACTION_NEXT_SESSION = 2;
    static final int SHORTCUT_ACTION_PREVIOUS_SESSION = 3;
    static final int SHORTCUT_ACTION_RENAME_SESSION = 4;
    private static final String SHOW_EXTRA_KEYS_KEY = "show_extra_keys";
    private final int MIN_FONTSIZE;
    boolean mBackIsEscape;
    String mDefaultWorkingDir;
    boolean mDisableVolumeVirtualKeys;
    ExtraKeysInfos mExtraKeys;
    private int mFontSize;
    private boolean mScreenAlwaysOn;
    boolean mShowExtraKeys;
    private boolean mUseDarkUI;
    private boolean mUseFullScreen;
    private boolean mUseFullScreenWorkAround;
    int mBellBehaviour = 1;
    final List<KeyboardShortcut> shortcuts = new ArrayList();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    @interface AsciiBellBehaviour {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class KeyboardShortcut {
        final int codePoint;
        final int shortcutAction;

        KeyboardShortcut(int i, int i2) {
            this.codePoint = i;
            this.shortcutAction = i2;
        }
    }

    static int clamp(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TermuxPreferences(Context context) {
        reloadFromProperties(context);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        float applyDimension = TypedValue.applyDimension(1, 1.0f, context.getResources().getDisplayMetrics());
        this.MIN_FONTSIZE = (int) (4.0f * applyDimension);
        this.mShowExtraKeys = defaultSharedPreferences.getBoolean(SHOW_EXTRA_KEYS_KEY, true);
        this.mScreenAlwaysOn = defaultSharedPreferences.getBoolean(SCREEN_ALWAYS_ON_KEY, false);
        int round = Math.round(applyDimension * 12.0f);
        round = round % 2 == 1 ? round - 1 : round;
        try {
            this.mFontSize = Integer.parseInt(defaultSharedPreferences.getString(FONTSIZE_KEY, Integer.toString(round)));
        } catch (ClassCastException | NumberFormatException unused) {
            this.mFontSize = round;
        }
        this.mFontSize = clamp(this.mFontSize, this.MIN_FONTSIZE, 256);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean toggleShowExtraKeys(Context context) {
        this.mShowExtraKeys = !this.mShowExtraKeys;
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(SHOW_EXTRA_KEYS_KEY, this.mShowExtraKeys).apply();
        return this.mShowExtraKeys;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFontSize() {
        return this.mFontSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void changeFontSize(Context context, boolean z) {
        int i = this.mFontSize + ((z ? 1 : -1) * 2);
        this.mFontSize = i;
        this.mFontSize = Math.max(this.MIN_FONTSIZE, Math.min(i, 256));
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(FONTSIZE_KEY, Integer.toString(this.mFontSize)).apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isScreenAlwaysOn() {
        return this.mScreenAlwaysOn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUsingBlackUI() {
        return this.mUseDarkUI;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUsingFullScreen() {
        return this.mUseFullScreen;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUsingFullScreenWorkAround() {
        return this.mUseFullScreenWorkAround;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setScreenAlwaysOn(Context context, boolean z) {
        this.mScreenAlwaysOn = z;
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(SCREEN_ALWAYS_ON_KEY, z).apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void storeCurrentSession(Context context, TerminalSession terminalSession) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(CURRENT_SESSION_KEY, terminalSession.mHandle).apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TerminalSession getCurrentSession(TermuxActivity termuxActivity) {
        String string = PreferenceManager.getDefaultSharedPreferences(termuxActivity).getString(CURRENT_SESSION_KEY, "");
        int size = termuxActivity.mTermService.getSessions().size();
        for (int i = 0; i < size; i++) {
            TerminalSession terminalSession = termuxActivity.mTermService.getSessions().get(i);
            if (terminalSession.mHandle.equals(string)) {
                return terminalSession;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void reloadFromProperties(android.content.Context r17) {
        /*
            Method dump skipped, instructions count: 433
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termfu.app.TermuxPreferences.reloadFromProperties(android.content.Context):void");
    }

    private void parseAction(String str, int i, Properties properties) {
        String property = properties.getProperty(str);
        if (property == null) {
            return;
        }
        String[] split = property.toLowerCase().trim().split("\\+");
        String trim = split.length == 2 ? split[1].trim() : null;
        if (split.length != 2 || !split[0].trim().equals("ctrl") || trim.isEmpty() || trim.length() > 2) {
            Log.e(EmulatorDebug.LOG_TAG, "Keyboard shortcut '" + str + "' is not Ctrl+<something>");
            return;
        }
        char charAt = trim.charAt(0);
        boolean isLowSurrogate = Character.isLowSurrogate(charAt);
        int i2 = charAt;
        if (isLowSurrogate) {
            if (trim.length() != 2 || Character.isHighSurrogate(trim.charAt(1))) {
                Log.e(EmulatorDebug.LOG_TAG, "Keyboard shortcut '" + str + "' is not Ctrl+<something>");
                return;
            }
            i2 = Character.toCodePoint(trim.charAt(1), charAt);
        }
        this.shortcuts.add(new KeyboardShortcut(i2, i));
    }
}
