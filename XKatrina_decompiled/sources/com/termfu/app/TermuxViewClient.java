package com.termfu.app;

import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import com.termfu.app.ExtraKeysView;
import com.termfu.view.TerminalViewClient;
import com.termux.terminal.TerminalSession;
/* loaded from: classes6.dex */
public final class TermuxViewClient implements TerminalViewClient {
    final TermuxActivity mActivity;
    boolean mVirtualControlKeyDown;
    boolean mVirtualFnKeyDown;

    @Override // com.termfu.view.TerminalViewClient
    public boolean onLongPress(MotionEvent motionEvent) {
        return false;
    }

    public TermuxViewClient(TermuxActivity termuxActivity) {
        this.mActivity = termuxActivity;
    }

    @Override // com.termfu.view.TerminalViewClient
    public float onScale(float f) {
        if (f < 0.9f || f > 1.1f) {
            this.mActivity.changeFontSize(f > 1.0f);
            return 1.0f;
        }
        return f;
    }

    @Override // com.termfu.view.TerminalViewClient
    public void onSingleTapUp(MotionEvent motionEvent) {
        ((InputMethodManager) this.mActivity.getSystemService("input_method")).showSoftInput(this.mActivity.mTerminalView, 1);
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean shouldBackButtonBeMappedToEscape() {
        return this.mActivity.mSettings.mBackIsEscape;
    }

    @Override // com.termfu.view.TerminalViewClient
    public void copyModeChanged(boolean z) {
        this.mActivity.getDrawer().setDrawerLockMode(z ? 1 : 0);
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean onKeyDown(int i, KeyEvent keyEvent, TerminalSession terminalSession) {
        if (handleVirtualKeys(i, keyEvent, true)) {
            return true;
        }
        if (i == 66 && !terminalSession.isRunning()) {
            this.mActivity.removeFinishedSession(terminalSession);
            return true;
        } else if (keyEvent.isCtrlPressed() && keyEvent.isAltPressed()) {
            int unicodeChar = keyEvent.getUnicodeChar(0);
            if (i == 20 || unicodeChar == 110) {
                this.mActivity.switchToSession(true);
            } else if (i == 19 || unicodeChar == 112) {
                this.mActivity.switchToSession(false);
            } else if (i == 22) {
                this.mActivity.getDrawer().openDrawer(3);
            } else if (i == 21) {
                this.mActivity.getDrawer().closeDrawers();
            } else if (unicodeChar == 107) {
                ((InputMethodManager) this.mActivity.getSystemService("input_method")).toggleSoftInput(2, 0);
            } else if (unicodeChar == 109) {
                this.mActivity.mTerminalView.showContextMenu();
            } else if (unicodeChar == 114) {
                this.mActivity.renameSession(terminalSession);
            } else if (unicodeChar == 99) {
                this.mActivity.addNewSession(false, null);
            } else if (unicodeChar == 117) {
                this.mActivity.showUrlSelection();
            } else if (unicodeChar == 118) {
                this.mActivity.doPaste();
            } else if (unicodeChar == 43 || keyEvent.getUnicodeChar(1) == 43) {
                this.mActivity.changeFontSize(true);
            } else if (unicodeChar == 45) {
                this.mActivity.changeFontSize(false);
            } else if (unicodeChar >= 49 && unicodeChar <= 57) {
                int i2 = unicodeChar - 49;
                TermuxService termuxService = this.mActivity.mTermService;
                if (termuxService.getSessions().size() > i2) {
                    this.mActivity.switchToSession(termuxService.getSessions().get(i2));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return handleVirtualKeys(i, keyEvent, false);
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean readControlKey() {
        return (this.mActivity.mExtraKeysView != null && this.mActivity.mExtraKeysView.readSpecialButton(ExtraKeysView.SpecialButton.CTRL)) || this.mVirtualControlKeyDown;
    }

    @Override // com.termfu.view.TerminalViewClient
    public boolean readAltKey() {
        return this.mActivity.mExtraKeysView != null && this.mActivity.mExtraKeysView.readSpecialButton(ExtraKeysView.SpecialButton.ALT);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a0  */
    @Override // com.termfu.view.TerminalViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onCodePoint(int r6, boolean r7, com.termux.terminal.TerminalSession r8) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termfu.app.TermuxViewClient.onCodePoint(int, boolean, com.termux.terminal.TerminalSession):boolean");
    }

    private boolean handleVirtualKeys(int i, KeyEvent keyEvent, boolean z) {
        InputDevice device = keyEvent.getDevice();
        if (this.mActivity.mSettings.mDisableVolumeVirtualKeys) {
            return false;
        }
        if (device == null || device.getKeyboardType() != 2) {
            if (i == 25) {
                this.mVirtualControlKeyDown = z;
                return true;
            } else if (i == 24) {
                this.mVirtualFnKeyDown = z;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
