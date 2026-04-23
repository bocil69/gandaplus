package com.termfu.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.PopupWindow;
import android.widget.Scroller;
import androidx.annotation.RequiresApi;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import com.fufufu.katrina.backup.R;
import com.termfu.view.GestureAndScaleRecognizer;
import com.termux.terminal.EmulatorDebug;
import com.termux.terminal.KeyHandler;
import com.termux.terminal.TerminalBuffer;
import com.termux.terminal.TerminalEmulator;
import com.termux.terminal.TerminalSession;
import com.termux.terminal.WcWidth;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
/* loaded from: classes6.dex */
public final class TerminalView extends View {
    private static final boolean LOG_KEY_EVENTS = false;
    private boolean mAccessibilityEnabled;
    private ActionMode mActionMode;
    TerminalViewClient mClient;
    int mCombiningAccent;
    TerminalEmulator mEmulator;
    final GestureAndScaleRecognizer mGestureRecognizer;
    boolean mIsSelectingText;
    private int mMouseScrollStartX;
    private int mMouseScrollStartY;
    private long mMouseStartDownTime;
    TerminalRenderer mRenderer;
    float mScaleFactor;
    float mScrollRemainder;
    final Scroller mScroller;
    int mSelX1;
    int mSelX2;
    int mSelY1;
    int mSelY2;
    Drawable mSelectHandleLeft;
    Drawable mSelectHandleRight;
    private SelectionModifierCursorController mSelectionModifierCursorController;
    private final Runnable mShowFloatingToolbar;
    final int[] mTempCoords;
    Rect mTempRect;
    TerminalSession mTermSession;
    int mTopRow;

    /* loaded from: classes6.dex */
    private interface CursorController extends ViewTreeObserver.OnTouchModeChangeListener {
        void hide();

        boolean isActive();

        void onDetached();

        boolean onTouchEvent(MotionEvent motionEvent);

        void show();

        void updatePosition();

        void updatePosition(HandleView handleView, int i, int i2);
    }

    @Override // android.view.View
    @RequiresApi(api = 26)
    public int getAutofillType() {
        return 1;
    }

    @Override // android.view.View
    public boolean isOpaque() {
        return true;
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        return true;
    }

    public TerminalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsSelectingText = false;
        this.mSelX1 = -1;
        this.mSelX2 = -1;
        this.mSelY1 = -1;
        this.mSelY2 = -1;
        this.mTempCoords = new int[2];
        this.mScaleFactor = 1.0f;
        this.mMouseScrollStartX = -1;
        this.mMouseScrollStartY = -1;
        this.mMouseStartDownTime = -1L;
        this.mShowFloatingToolbar = new Runnable() { // from class: com.termfu.view.TerminalView.1
            @Override // java.lang.Runnable
            public void run() {
                if (TerminalView.this.mActionMode != null) {
                    TerminalView.this.mActionMode.hide(0L);
                }
            }
        };
        this.mGestureRecognizer = new GestureAndScaleRecognizer(context, new GestureAndScaleRecognizer.Listener() { // from class: com.termfu.view.TerminalView.2
            boolean scrolledWithFinger;

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                return false;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onDown(float f, float f2) {
                return false;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onUp(MotionEvent motionEvent) {
                TerminalView.this.mScrollRemainder = 0.0f;
                if (TerminalView.this.mEmulator != null && TerminalView.this.mEmulator.isMouseTrackingActive() && !TerminalView.this.mIsSelectingText && !this.scrolledWithFinger) {
                    TerminalView.this.sendMouseEventCode(motionEvent, 0, true);
                    TerminalView.this.sendMouseEventCode(motionEvent, 0, false);
                    return true;
                }
                this.scrolledWithFinger = false;
                return false;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (TerminalView.this.mEmulator == null) {
                    return true;
                }
                if (!TerminalView.this.mIsSelectingText) {
                    TerminalView.this.requestFocus();
                    if (TerminalView.this.mEmulator.isMouseTrackingActive() || motionEvent.isFromSource(8194)) {
                        return false;
                    }
                    TerminalView.this.mClient.onSingleTapUp(motionEvent);
                    return true;
                }
                TerminalView.this.stopTextSelectionMode();
                return true;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onScroll(MotionEvent motionEvent, float f, float f2) {
                if (TerminalView.this.mEmulator == null) {
                    return true;
                }
                if (TerminalView.this.mEmulator.isMouseTrackingActive() && motionEvent.isFromSource(8194)) {
                    TerminalView.this.sendMouseEventCode(motionEvent, 32, true);
                } else {
                    this.scrolledWithFinger = true;
                    float f3 = f2 + TerminalView.this.mScrollRemainder;
                    int i = (int) (f3 / TerminalView.this.mRenderer.mFontLineSpacing);
                    TerminalView terminalView = TerminalView.this;
                    terminalView.mScrollRemainder = f3 - (terminalView.mRenderer.mFontLineSpacing * i);
                    TerminalView.this.doScroll(motionEvent, i);
                }
                return true;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onScale(float f, float f2, float f3) {
                if (TerminalView.this.mEmulator != null && !TerminalView.this.mIsSelectingText) {
                    TerminalView.this.mScaleFactor *= f3;
                    TerminalView terminalView = TerminalView.this;
                    terminalView.mScaleFactor = terminalView.mClient.onScale(TerminalView.this.mScaleFactor);
                }
                return true;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public boolean onFling(final MotionEvent motionEvent, float f, float f2) {
                if (TerminalView.this.mEmulator != null && TerminalView.this.mScroller.isFinished()) {
                    final boolean isMouseTrackingActive = TerminalView.this.mEmulator.isMouseTrackingActive();
                    if (isMouseTrackingActive) {
                        TerminalView.this.mScroller.fling(0, 0, 0, -((int) (f2 * 0.25f)), 0, 0, (-TerminalView.this.mEmulator.mRows) / 2, TerminalView.this.mEmulator.mRows / 2);
                    } else {
                        TerminalView.this.mScroller.fling(0, TerminalView.this.mTopRow, 0, -((int) (f2 * 0.25f)), 0, 0, -TerminalView.this.mEmulator.getScreen().getActiveTranscriptRows(), 0);
                    }
                    TerminalView.this.post(new Runnable() { // from class: com.termfu.view.TerminalView.2.1
                        private int mLastY = 0;

                        @Override // java.lang.Runnable
                        public void run() {
                            if (isMouseTrackingActive != TerminalView.this.mEmulator.isMouseTrackingActive()) {
                                TerminalView.this.mScroller.abortAnimation();
                            } else if (TerminalView.this.mScroller.isFinished()) {
                            } else {
                                boolean computeScrollOffset = TerminalView.this.mScroller.computeScrollOffset();
                                int currY = TerminalView.this.mScroller.getCurrY();
                                TerminalView.this.doScroll(motionEvent, currY - (isMouseTrackingActive ? this.mLastY : TerminalView.this.mTopRow));
                                this.mLastY = currY;
                                if (computeScrollOffset) {
                                    TerminalView.this.post(this);
                                }
                            }
                        }
                    });
                    return true;
                }
                return true;
            }

            @Override // com.termfu.view.GestureAndScaleRecognizer.Listener
            public void onLongPress(MotionEvent motionEvent) {
                if (TerminalView.this.mGestureRecognizer.isInProgress() || TerminalView.this.mClient.onLongPress(motionEvent) || TerminalView.this.mIsSelectingText) {
                    return;
                }
                TerminalView.this.performHapticFeedback(0);
                TerminalView.this.startSelectingText(motionEvent);
            }
        });
        this.mScroller = new Scroller(context);
        this.mAccessibilityEnabled = ((AccessibilityManager) context.getSystemService("accessibility")).isEnabled();
    }

    public void setOnKeyListener(TerminalViewClient terminalViewClient) {
        this.mClient = terminalViewClient;
    }

    public boolean attachSession(TerminalSession terminalSession) {
        if (terminalSession == this.mTermSession) {
            return false;
        }
        this.mTopRow = 0;
        this.mTermSession = terminalSession;
        this.mEmulator = null;
        this.mCombiningAccent = 0;
        updateSize();
        setVerticalScrollBarEnabled(true);
        return true;
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (getProperties().getProperty("enforce-char-based-input", "false").equals("true")) {
            editorInfo.inputType = 524432;
        } else {
            editorInfo.inputType = 0;
        }
        editorInfo.imeOptions = FileSystemManager.MODE_APPEND;
        return new BaseInputConnection(this, true) { // from class: com.termfu.view.TerminalView.3
            @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
            public boolean finishComposingText() {
                super.finishComposingText();
                sendTextToTerminal(getEditable());
                getEditable().clear();
                return true;
            }

            @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
            public boolean commitText(CharSequence charSequence, int i) {
                super.commitText(charSequence, i);
                if (TerminalView.this.mEmulator == null) {
                    return true;
                }
                Editable editable = getEditable();
                sendTextToTerminal(editable);
                editable.clear();
                return true;
            }

            @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
            public boolean deleteSurroundingText(int i, int i2) {
                KeyEvent keyEvent = new KeyEvent(0, 67);
                for (int i3 = 0; i3 < i; i3++) {
                    sendKeyEvent(keyEvent);
                }
                return super.deleteSurroundingText(i, i2);
            }

            void sendTextToTerminal(CharSequence charSequence) {
                boolean z;
                TerminalView.this.stopTextSelectionMode();
                int length = charSequence.length();
                int i = 0;
                while (i < length) {
                    char charAt = charSequence.charAt(i);
                    boolean isHighSurrogate = Character.isHighSurrogate(charAt);
                    int i2 = charAt;
                    if (isHighSurrogate) {
                        i++;
                        i2 = i < length ? Character.toCodePoint(charAt, charSequence.charAt(i)) : TerminalEmulator.UNICODE_REPLACEMENT_CHAR;
                    }
                    if (i2 > 31 || i2 == 27) {
                        z = false;
                    } else {
                        int i3 = i2;
                        if (i2 == 10) {
                            i3 = 13;
                        }
                        switch (i3) {
                            case 28:
                                i2 = 92;
                                break;
                            case 29:
                                i2 = 93;
                                break;
                            case 30:
                                i2 = 94;
                                break;
                            case 31:
                                i2 = 95;
                                break;
                            default:
                                i2 = i3 + 96;
                                break;
                        }
                        z = true;
                    }
                    TerminalView.this.inputCodePoint(i2, z, false);
                    i++;
                }
            }
        };
    }

    @Override // android.view.View
    protected int computeVerticalScrollRange() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return 1;
        }
        return terminalEmulator.getScreen().getActiveRows();
    }

    @Override // android.view.View
    protected int computeVerticalScrollExtent() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return 1;
        }
        return terminalEmulator.mRows;
    }

    @Override // android.view.View
    protected int computeVerticalScrollOffset() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return 1;
        }
        return (terminalEmulator.getScreen().getActiveRows() + this.mTopRow) - this.mEmulator.mRows;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onScreenUpdated() {
        /*
            r5 = this;
            com.termux.terminal.TerminalEmulator r0 = r5.mEmulator
            if (r0 != 0) goto L5
            return
        L5:
            com.termux.terminal.TerminalBuffer r0 = r0.getScreen()
            int r0 = r0.getActiveTranscriptRows()
            int r1 = r5.mTopRow
            int r2 = -r0
            if (r1 >= r2) goto L14
            r5.mTopRow = r2
        L14:
            boolean r1 = r5.mIsSelectingText
            r2 = 0
            if (r1 == 0) goto L38
            com.termux.terminal.TerminalEmulator r1 = r5.mEmulator
            int r1 = r1.getScrollCounter()
            int r3 = r5.mTopRow
            int r4 = -r3
            int r4 = r4 + r1
            if (r4 <= r0) goto L29
            r5.stopTextSelectionMode()
            goto L38
        L29:
            r0 = 1
            int r3 = r3 - r1
            r5.mTopRow = r3
            int r3 = r5.mSelY1
            int r3 = r3 - r1
            r5.mSelY1 = r3
            int r3 = r5.mSelY2
            int r3 = r3 - r1
            r5.mSelY2 = r3
            goto L39
        L38:
            r0 = 0
        L39:
            if (r0 != 0) goto L47
            int r0 = r5.mTopRow
            if (r0 == 0) goto L47
            r1 = -3
            if (r0 >= r1) goto L45
            r5.awakenScrollBars()
        L45:
            r5.mTopRow = r2
        L47:
            com.termux.terminal.TerminalEmulator r0 = r5.mEmulator
            r0.clearScrollCounter()
            r5.invalidate()
            boolean r0 = r5.mAccessibilityEnabled
            if (r0 == 0) goto L5a
            java.lang.CharSequence r0 = r5.getText()
            r5.setContentDescription(r0)
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termfu.view.TerminalView.onScreenUpdated():void");
    }

    public void setTextSize(int i) {
        TerminalRenderer terminalRenderer = this.mRenderer;
        this.mRenderer = new TerminalRenderer(i, terminalRenderer == null ? Typeface.MONOSPACE : terminalRenderer.mTypeface);
        updateSize();
    }

    public void setTypeface(Typeface typeface) {
        this.mRenderer = new TerminalRenderer(this.mRenderer.mTextSize, typeface);
        updateSize();
        invalidate();
    }

    void sendMouseEventCode(MotionEvent motionEvent, int i, boolean z) {
        int x = ((int) (motionEvent.getX() / this.mRenderer.mFontWidth)) + 1;
        int y = ((int) ((motionEvent.getY() - this.mRenderer.mFontLineSpacingAndAscent) / this.mRenderer.mFontLineSpacing)) + 1;
        if (z && (i == 65 || i == 64)) {
            if (this.mMouseStartDownTime == motionEvent.getDownTime()) {
                x = this.mMouseScrollStartX;
                y = this.mMouseScrollStartY;
            } else {
                this.mMouseStartDownTime = motionEvent.getDownTime();
                this.mMouseScrollStartX = x;
                this.mMouseScrollStartY = y;
            }
        }
        this.mEmulator.sendMouseEvent(i, x, y, z);
    }

    void doScroll(MotionEvent motionEvent, int i) {
        boolean z = i < 0;
        int abs = Math.abs(i);
        for (int i2 = 0; i2 < abs; i2++) {
            if (this.mEmulator.isMouseTrackingActive()) {
                sendMouseEventCode(motionEvent, z ? 64 : 65, true);
            } else if (this.mEmulator.isAlternateBufferActive()) {
                handleKeyCode(z ? 19 : 20, 0);
            } else {
                this.mTopRow = Math.min(0, Math.max(-this.mEmulator.getScreen().getActiveTranscriptRows(), this.mTopRow + (z ? -1 : 1)));
                if (!awakenScrollBars()) {
                    invalidate();
                }
            }
        }
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.mEmulator != null && motionEvent.isFromSource(8194) && motionEvent.getAction() == 8) {
            doScroll(motionEvent, motionEvent.getAxisValue(9) > 0.0f ? -3 : 3);
            return true;
        }
        return false;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    @TargetApi(23)
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mEmulator == null) {
            return true;
        }
        int action = motionEvent.getAction();
        if (this.mIsSelectingText) {
            updateFloatingToolbarVisibility(motionEvent);
            this.mGestureRecognizer.onTouchEvent(motionEvent);
            return true;
        }
        if (motionEvent.isFromSource(8194)) {
            if (motionEvent.isButtonPressed(2)) {
                if (action == 0) {
                    showContextMenu();
                }
                return true;
            } else if (motionEvent.isButtonPressed(4)) {
                ClipData primaryClip = ((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip();
                if (primaryClip != null) {
                    CharSequence coerceToText = primaryClip.getItemAt(0).coerceToText(getContext());
                    if (!TextUtils.isEmpty(coerceToText)) {
                        this.mEmulator.paste(coerceToText.toString());
                    }
                }
            } else if (this.mEmulator.isMouseTrackingActive()) {
                int action2 = motionEvent.getAction();
                if (action2 == 0 || action2 == 1) {
                    sendMouseEventCode(motionEvent, 0, motionEvent.getAction() == 0);
                } else if (action2 == 2) {
                    sendMouseEventCode(motionEvent, 32, true);
                }
                return true;
            }
        }
        this.mGestureRecognizer.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        Properties properties = getProperties();
        if (i == 4) {
            if (this.mIsSelectingText) {
                stopTextSelectionMode();
                return true;
            } else if (this.mClient.shouldBackButtonBeMappedToEscape()) {
                int action = keyEvent.getAction();
                if (action == 0) {
                    return onKeyDown(i, keyEvent);
                }
                if (action == 1) {
                    return onKeyUp(i, keyEvent);
                }
            }
        } else if (properties.getProperty("ctrl-space-workaround", "false").equals("true") && i == 62 && keyEvent.isCtrlPressed()) {
            return onKeyDown(i, keyEvent);
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mEmulator == null) {
            return true;
        }
        stopTextSelectionMode();
        if (this.mClient.onKeyDown(i, keyEvent, this.mTermSession)) {
            invalidate();
            return true;
        } else if (keyEvent.isSystem() && (!this.mClient.shouldBackButtonBeMappedToEscape() || i != 4)) {
            return super.onKeyDown(i, keyEvent);
        } else {
            if (keyEvent.getAction() == 2 && i == 0) {
                this.mTermSession.write(keyEvent.getCharacters());
                return true;
            }
            int metaState = keyEvent.getMetaState();
            boolean z = keyEvent.isCtrlPressed() || this.mClient.readControlKey();
            boolean z2 = (metaState & 16) != 0 || this.mClient.readAltKey();
            boolean z3 = (metaState & 32) != 0;
            int i2 = z ? 1073741824 : 0;
            if (keyEvent.isAltPressed() || z2) {
                i2 |= Integer.MIN_VALUE;
            }
            if (keyEvent.isShiftPressed()) {
                i2 |= 536870912;
            }
            if (keyEvent.isNumLockOn()) {
                i2 |= 268435456;
            }
            if (keyEvent.isFunctionPressed() || !handleKeyCode(i, i2)) {
                int unicodeChar = keyEvent.getUnicodeChar((~(z3 ? 28672 : 28690)) & keyEvent.getMetaState());
                if (unicodeChar == 0) {
                    return false;
                }
                int i3 = this.mCombiningAccent;
                if ((unicodeChar & Integer.MIN_VALUE) != 0) {
                    if (i3 != 0) {
                        inputCodePoint(i3, z, z2);
                    }
                    this.mCombiningAccent = unicodeChar & Integer.MAX_VALUE;
                } else {
                    if (i3 != 0) {
                        int deadChar = KeyCharacterMap.getDeadChar(i3, unicodeChar);
                        if (deadChar > 0) {
                            unicodeChar = deadChar;
                        }
                        this.mCombiningAccent = 0;
                    }
                    inputCodePoint(unicodeChar, z, z2);
                }
                if (this.mCombiningAccent != i3) {
                    invalidate();
                }
                return true;
            }
            return true;
        }
    }

    public void inputCodePoint(int i, boolean z, boolean z2) {
        int i2;
        if (this.mTermSession == null) {
            return;
        }
        boolean z3 = z || this.mClient.readControlKey();
        boolean z4 = z2 || this.mClient.readAltKey();
        if (this.mClient.onCodePoint(i, z3, this.mTermSession)) {
            return;
        }
        if (z3) {
            if (i >= 97 && i <= 122) {
                i2 = i - 97;
            } else if (i >= 65 && i <= 90) {
                i2 = i - 65;
            } else if (i == 32 || i == 50) {
                i = 0;
            } else if (i == 91 || i == 51) {
                i = 27;
            } else if (i == 92 || i == 52) {
                i = 28;
            } else if (i == 93 || i == 53) {
                i = 29;
            } else if (i == 94 || i == 54) {
                i = 30;
            } else if (i == 95 || i == 55 || i == 47) {
                i = 31;
            } else if (i == 56) {
                i = 127;
            }
            i = i2 + 1;
        }
        if (i > -1) {
            this.mTermSession.writeCodePoint(z4, i != 710 ? i != 715 ? i != 732 ? i : 126 : 96 : 94);
        }
    }

    public boolean handleKeyCode(int i, int i2) {
        TerminalEmulator emulator = this.mTermSession.getEmulator();
        String code = KeyHandler.getCode(i, i2, emulator.isCursorKeysApplicationMode(), emulator.isKeypadApplicationMode());
        if (code == null) {
            return false;
        }
        this.mTermSession.write(code);
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mEmulator == null) {
            return true;
        }
        if (this.mClient.onKeyUp(i, keyEvent)) {
            invalidate();
            return true;
        } else if (keyEvent.isSystem()) {
            return super.onKeyUp(i, keyEvent);
        } else {
            return true;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        updateSize();
    }

    public void updateSize() {
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0 || this.mTermSession == null) {
            return;
        }
        int max = Math.max(4, (int) (width / this.mRenderer.mFontWidth));
        int max2 = Math.max(4, (height - this.mRenderer.mFontLineSpacingAndAscent) / this.mRenderer.mFontLineSpacing);
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator != null && max == terminalEmulator.mColumns && max2 == this.mEmulator.mRows) {
            return;
        }
        this.mTermSession.updateSize(max, max2);
        this.mEmulator = this.mTermSession.getEmulator();
        this.mTopRow = 0;
        scrollTo(0, 0);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
            return;
        }
        this.mRenderer.render(terminalEmulator, canvas, this.mTopRow, this.mSelY1, this.mSelY2, this.mSelX1, this.mSelX2);
        SelectionModifierCursorController selectionController = getSelectionController();
        if (selectionController == null || !selectionController.isActive()) {
            return;
        }
        selectionController.updatePosition();
    }

    @TargetApi(23)
    public void startSelectingText(MotionEvent motionEvent) {
        int x = (int) (motionEvent.getX() / this.mRenderer.mFontWidth);
        int y = ((int) ((motionEvent.getY() + (motionEvent.isFromSource(8194) ? 0 : -40)) / this.mRenderer.mFontLineSpacing)) + this.mTopRow;
        this.mSelX2 = x;
        this.mSelX1 = x;
        this.mSelY2 = y;
        this.mSelY1 = y;
        TerminalBuffer screen = this.mEmulator.getScreen();
        int i = this.mSelX1;
        int i2 = this.mSelY1;
        if (!" ".equals(screen.getSelectedText(i, i2, i, i2))) {
            while (true) {
                int i3 = this.mSelX1;
                if (i3 <= 0) {
                    break;
                }
                int i4 = this.mSelY1;
                if ("".equals(screen.getSelectedText(i3 - 1, i4, i3 - 1, i4))) {
                    break;
                }
                this.mSelX1--;
            }
            while (this.mSelX2 < this.mEmulator.mColumns - 1) {
                int i5 = this.mSelX2;
                int i6 = this.mSelY1;
                if ("".equals(screen.getSelectedText(i5 + 1, i6, i5 + 1, i6))) {
                    break;
                }
                this.mSelX2++;
            }
        }
        startTextSelectionMode();
    }

    public TerminalSession getCurrentSession() {
        return this.mTermSession;
    }

    private CharSequence getText() {
        return this.mEmulator.getScreen().getSelectedText(0, this.mTopRow, this.mEmulator.mColumns, this.mTopRow + this.mEmulator.mRows);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mSelectionModifierCursorController != null) {
            getViewTreeObserver().addOnTouchModeChangeListener(this.mSelectionModifierCursorController);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mSelectionModifierCursorController != null) {
            getViewTreeObserver().removeOnTouchModeChangeListener(this.mSelectionModifierCursorController);
            this.mSelectionModifierCursorController.onDetached();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCursorX(float f) {
        return (int) (f / this.mRenderer.mFontWidth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCursorY(float f) {
        return (int) (((f - 40.0f) / this.mRenderer.mFontLineSpacing) + this.mTopRow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPointX(int i) {
        if (i > this.mEmulator.mColumns) {
            i = this.mEmulator.mColumns;
        }
        return Math.round(i * this.mRenderer.mFontWidth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPointY(int i) {
        return Math.round((i - this.mTopRow) * this.mRenderer.mFontLineSpacing);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class HandleView extends View {
        public static final int LEFT = 0;
        public static final int RIGHT = 2;
        private PopupWindow mContainer;
        private CursorController mController;
        private Drawable mDrawable;
        private int mHandleHeight;
        int mHandleWidth;
        private float mHotspotX;
        private float mHotspotY;
        private boolean mIsDragging;
        private int mLastParentX;
        private int mLastParentY;
        private long mLastTime;
        private int mOrientation;
        private final int mOrigOrient;
        private int mPointX;
        private int mPointY;
        private float mTouchOffsetY;
        private float mTouchToWindowOffsetX;
        private float mTouchToWindowOffsetY;

        public HandleView(CursorController cursorController, int i) {
            super(TerminalView.this.getContext());
            this.mController = cursorController;
            PopupWindow popupWindow = new PopupWindow(TerminalView.this.getContext(), (AttributeSet) null, 16843464);
            this.mContainer = popupWindow;
            popupWindow.setSplitTouchEnabled(true);
            this.mContainer.setClippingEnabled(false);
            this.mContainer.setWindowLayoutType(PointerIconCompat.TYPE_HAND);
            this.mContainer.setWidth(-2);
            this.mContainer.setHeight(-2);
            this.mOrigOrient = i;
            setOrientation(i);
        }

        public void setOrientation(int i) {
            int intrinsicWidth;
            this.mOrientation = i;
            if (i == 0) {
                if (TerminalView.this.mSelectHandleLeft == null) {
                    TerminalView.this.mSelectHandleLeft = getContext().getDrawable(R.drawable.admsoloraya_res_0x7f0801e2);
                }
                Drawable drawable = TerminalView.this.mSelectHandleLeft;
                this.mDrawable = drawable;
                intrinsicWidth = drawable.getIntrinsicWidth();
                this.mHotspotX = (intrinsicWidth * 3) / 4;
            } else if (i != 2) {
                intrinsicWidth = 0;
            } else {
                if (TerminalView.this.mSelectHandleRight == null) {
                    TerminalView.this.mSelectHandleRight = getContext().getDrawable(R.drawable.admsoloraya_res_0x7f0801e3);
                }
                Drawable drawable2 = TerminalView.this.mSelectHandleRight;
                this.mDrawable = drawable2;
                intrinsicWidth = drawable2.getIntrinsicWidth();
                this.mHotspotX = intrinsicWidth / 4;
            }
            int intrinsicHeight = this.mDrawable.getIntrinsicHeight();
            this.mHandleHeight = intrinsicHeight;
            this.mHandleWidth = intrinsicWidth;
            this.mTouchOffsetY = (-intrinsicHeight) * 0.3f;
            this.mHotspotY = 0.0f;
            invalidate();
        }

        public void changeOrientation(int i) {
            if (this.mOrientation != i) {
                setOrientation(i);
            }
        }

        @Override // android.view.View
        public void onMeasure(int i, int i2) {
            setMeasuredDimension(this.mDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }

        public void show() {
            if (!isPositionVisible()) {
                hide();
                return;
            }
            this.mContainer.setContentView(this);
            int[] iArr = TerminalView.this.mTempCoords;
            TerminalView.this.getLocationInWindow(iArr);
            int i = iArr[0] + this.mPointX;
            iArr[0] = i;
            int i2 = iArr[1] + this.mPointY;
            iArr[1] = i2;
            this.mContainer.showAtLocation(TerminalView.this, 0, i, i2);
        }

        public void hide() {
            this.mIsDragging = false;
            this.mContainer.dismiss();
        }

        public boolean isShowing() {
            return this.mContainer.isShowing();
        }

        private void checkChangedOrientation(int i, boolean z) {
            if (this.mIsDragging || z) {
                long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
                if (currentThreadTimeMillis - this.mLastTime >= 50 || z) {
                    this.mLastTime = currentThreadTimeMillis;
                    TerminalView terminalView = TerminalView.this;
                    int left = terminalView.getLeft();
                    int width = terminalView.getWidth();
                    int top = terminalView.getTop();
                    int height = terminalView.getHeight();
                    if (TerminalView.this.mTempRect == null) {
                        TerminalView.this.mTempRect = new Rect();
                    }
                    Rect rect = TerminalView.this.mTempRect;
                    rect.left = left + TerminalView.this.getPaddingLeft();
                    rect.top = top + TerminalView.this.getPaddingTop();
                    rect.right = width - TerminalView.this.getPaddingRight();
                    rect.bottom = height - TerminalView.this.getPaddingBottom();
                    ViewParent parent = terminalView.getParent();
                    if (parent == null || !parent.getChildVisibleRect(terminalView, rect, null)) {
                        return;
                    }
                    if (i - this.mHandleWidth < rect.left) {
                        changeOrientation(2);
                    } else if (i + this.mHandleWidth > rect.right) {
                        changeOrientation(0);
                    } else {
                        changeOrientation(this.mOrigOrient);
                    }
                }
            }
        }

        private boolean isPositionVisible() {
            if (this.mIsDragging) {
                return true;
            }
            TerminalView terminalView = TerminalView.this;
            int width = terminalView.getWidth();
            int height = terminalView.getHeight();
            if (TerminalView.this.mTempRect == null) {
                TerminalView.this.mTempRect = new Rect();
            }
            Rect rect = TerminalView.this.mTempRect;
            rect.left = TerminalView.this.getPaddingLeft() + 0;
            rect.top = TerminalView.this.getPaddingTop() + 0;
            rect.right = width - TerminalView.this.getPaddingRight();
            rect.bottom = height - TerminalView.this.getPaddingBottom();
            ViewParent parent = terminalView.getParent();
            if (parent != null && parent.getChildVisibleRect(terminalView, rect, null)) {
                int[] iArr = TerminalView.this.mTempCoords;
                terminalView.getLocationInWindow(iArr);
                int i = iArr[0] + this.mPointX + ((int) this.mHotspotX);
                int i2 = iArr[1] + this.mPointY + ((int) this.mHotspotY);
                if (i >= rect.left && i <= rect.right && i2 >= rect.top && i2 <= rect.bottom) {
                    return true;
                }
            }
            return false;
        }

        private void moveTo(int i, int i2, boolean z) {
            int i3;
            float f = this.mHotspotX;
            checkChangedOrientation(i, z);
            float f2 = i;
            if (!isShowing()) {
                f = this.mHotspotX;
            }
            this.mPointX = (int) (f2 - f);
            this.mPointY = i2;
            if (isPositionVisible()) {
                int[] iArr = null;
                if (isShowing()) {
                    iArr = TerminalView.this.mTempCoords;
                    TerminalView.this.getLocationInWindow(iArr);
                    this.mContainer.update(iArr[0] + this.mPointX, iArr[1] + this.mPointY, getWidth(), getHeight());
                } else {
                    show();
                }
                if (this.mIsDragging) {
                    if (iArr == null) {
                        iArr = TerminalView.this.mTempCoords;
                        TerminalView.this.getLocationInWindow(iArr);
                    }
                    int i4 = iArr[0];
                    if (i4 == this.mLastParentX && iArr[1] == this.mLastParentY) {
                        return;
                    }
                    this.mTouchToWindowOffsetX += i4 - i3;
                    float f3 = this.mTouchToWindowOffsetY;
                    int i5 = iArr[1];
                    this.mTouchToWindowOffsetY = f3 + (i5 - this.mLastParentY);
                    this.mLastParentX = i4;
                    this.mLastParentY = i5;
                }
            } else if (isShowing()) {
                hide();
            }
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            this.mDrawable.setBounds(0, 0, this.mDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
            this.mDrawable.draw(canvas);
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0013, code lost:
            if (r0 != 3) goto L8;
         */
        @Override // android.view.View
        @android.annotation.SuppressLint({"ClickableViewAccessibility"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onTouchEvent(android.view.MotionEvent r5) {
            /*
                r4 = this;
                com.termfu.view.TerminalView r0 = com.termfu.view.TerminalView.this
                com.termfu.view.TerminalView.access$1(r0, r5)
                int r0 = r5.getActionMasked()
                r1 = 0
                r2 = 1
                if (r0 == 0) goto L3e
                if (r0 == r2) goto L3b
                r3 = 2
                if (r0 == r3) goto L16
                r5 = 3
                if (r0 == r5) goto L3b
                goto L65
            L16:
                float r0 = r5.getRawX()
                float r5 = r5.getRawY()
                float r1 = r4.mTouchToWindowOffsetX
                float r0 = r0 - r1
                float r1 = r4.mHotspotX
                float r0 = r0 + r1
                float r1 = r4.mTouchToWindowOffsetY
                float r5 = r5 - r1
                float r1 = r4.mHotspotY
                float r5 = r5 + r1
                float r1 = r4.mTouchOffsetY
                float r5 = r5 + r1
                com.termfu.view.TerminalView$CursorController r1 = r4.mController
                int r0 = java.lang.Math.round(r0)
                int r5 = java.lang.Math.round(r5)
                r1.updatePosition(r4, r0, r5)
                goto L65
            L3b:
                r4.mIsDragging = r1
                goto L65
            L3e:
                float r0 = r5.getRawX()
                float r5 = r5.getRawY()
                int r3 = r4.mPointX
                float r3 = (float) r3
                float r0 = r0 - r3
                r4.mTouchToWindowOffsetX = r0
                int r0 = r4.mPointY
                float r0 = (float) r0
                float r5 = r5 - r0
                r4.mTouchToWindowOffsetY = r5
                com.termfu.view.TerminalView r5 = com.termfu.view.TerminalView.this
                int[] r5 = r5.mTempCoords
                com.termfu.view.TerminalView r0 = com.termfu.view.TerminalView.this
                r0.getLocationInWindow(r5)
                r0 = r5[r1]
                r4.mLastParentX = r0
                r5 = r5[r2]
                r4.mLastParentY = r5
                r4.mIsDragging = r2
            L65:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.termfu.view.TerminalView.HandleView.onTouchEvent(android.view.MotionEvent):boolean");
        }

        public boolean isDragging() {
            return this.mIsDragging;
        }

        void positionAtCursor(int i, int i2, boolean z) {
            moveTo(TerminalView.this.getPointX(i), TerminalView.this.getPointY(i2 + 1), z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class SelectionModifierCursorController implements CursorController {
        private HandleView mEndHandle;
        private final int mHandleHeight;
        private boolean mIsShowing;
        private HandleView mStartHandle;

        @Override // com.termfu.view.TerminalView.CursorController
        public void onDetached() {
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        SelectionModifierCursorController() {
            this.mStartHandle = new HandleView(this, 0);
            this.mEndHandle = new HandleView(this, 2);
            this.mHandleHeight = Math.max(this.mStartHandle.mHandleHeight, this.mEndHandle.mHandleHeight);
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public void show() {
            this.mIsShowing = true;
            this.mStartHandle.positionAtCursor(TerminalView.this.mSelX1, TerminalView.this.mSelY1, true);
            this.mEndHandle.positionAtCursor(TerminalView.this.mSelX2 + 1, TerminalView.this.mSelY2, true);
            final ActionMode.Callback callback = new ActionMode.Callback() { // from class: com.termfu.view.TerminalView.SelectionModifierCursorController.1
                @Override // android.view.ActionMode.Callback
                public void onDestroyActionMode(ActionMode actionMode) {
                }

                @Override // android.view.ActionMode.Callback
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override // android.view.ActionMode.Callback
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    menu.add(0, 1, 0, R.string.admsoloraya_res_0x7f1200d4).setShowAsAction(5);
                    menu.add(0, 2, 0, R.string.admsoloraya_res_0x7f1200e2).setEnabled(((ClipboardManager) TerminalView.this.getContext().getSystemService("clipboard")).hasPrimaryClip()).setShowAsAction(5);
                    menu.add(0, 3, 0, R.string.admsoloraya_res_0x7f1200fb);
                    return true;
                }

                @Override // android.view.ActionMode.Callback
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    if (TerminalView.this.mIsSelectingText) {
                        int itemId = menuItem.getItemId();
                        if (itemId == 1) {
                            TerminalView.this.mTermSession.clipboardText(TerminalView.this.mEmulator.getSelectedText(TerminalView.this.mSelX1, TerminalView.this.mSelY1, TerminalView.this.mSelX2, TerminalView.this.mSelY2).trim());
                        } else if (itemId == 2) {
                            ClipData primaryClip = ((ClipboardManager) TerminalView.this.getContext().getSystemService("clipboard")).getPrimaryClip();
                            if (primaryClip != null) {
                                CharSequence coerceToText = primaryClip.getItemAt(0).coerceToText(TerminalView.this.getContext());
                                if (!TextUtils.isEmpty(coerceToText)) {
                                    TerminalView.this.mEmulator.paste(coerceToText.toString());
                                }
                            }
                        } else if (itemId == 3) {
                            TerminalView.this.showContextMenu();
                        }
                        TerminalView.this.stopTextSelectionMode();
                        return true;
                    }
                    return true;
                }
            };
            TerminalView terminalView = TerminalView.this;
            terminalView.mActionMode = terminalView.startActionMode(new ActionMode.Callback2() { // from class: com.termfu.view.TerminalView.SelectionModifierCursorController.2
                @Override // android.view.ActionMode.Callback
                public void onDestroyActionMode(ActionMode actionMode) {
                }

                @Override // android.view.ActionMode.Callback
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override // android.view.ActionMode.Callback
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    return callback.onCreateActionMode(actionMode, menu);
                }

                @Override // android.view.ActionMode.Callback
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    return callback.onActionItemClicked(actionMode, menuItem);
                }

                @Override // android.view.ActionMode.Callback2
                public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
                    int round = Math.round(TerminalView.this.mSelX1 * TerminalView.this.mRenderer.mFontWidth);
                    int round2 = Math.round(TerminalView.this.mSelX2 * TerminalView.this.mRenderer.mFontWidth);
                    int round3 = Math.round(((TerminalView.this.mSelY1 - 1) - TerminalView.this.mTopRow) * TerminalView.this.mRenderer.mFontLineSpacing);
                    int round4 = Math.round(((TerminalView.this.mSelY2 + 1) - TerminalView.this.mTopRow) * TerminalView.this.mRenderer.mFontLineSpacing);
                    if (round > round2) {
                        round2 = round;
                        round = round2;
                    }
                    rect.set(round, round3 + SelectionModifierCursorController.this.mHandleHeight, round2, round4 + SelectionModifierCursorController.this.mHandleHeight);
                }
            }, 1);
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public void hide() {
            this.mStartHandle.hide();
            this.mEndHandle.hide();
            this.mIsShowing = false;
            if (TerminalView.this.mActionMode != null) {
                TerminalView.this.mActionMode.finish();
            }
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public boolean isActive() {
            return this.mIsShowing;
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public void updatePosition(HandleView handleView, int i, int i2) {
            TerminalView terminalView;
            TerminalView terminalView2;
            TerminalBuffer screen = TerminalView.this.mEmulator.getScreen();
            int activeRows = screen.getActiveRows() - TerminalView.this.mEmulator.mRows;
            if (handleView == this.mStartHandle) {
                TerminalView terminalView3 = TerminalView.this;
                terminalView3.mSelX1 = terminalView3.getCursorX(i);
                TerminalView terminalView4 = TerminalView.this;
                terminalView4.mSelY1 = terminalView4.getCursorY(i2);
                if (TerminalView.this.mSelX1 < 0) {
                    TerminalView.this.mSelX1 = 0;
                }
                int i3 = -activeRows;
                if (TerminalView.this.mSelY1 < i3) {
                    TerminalView.this.mSelY1 = i3;
                } else if (TerminalView.this.mSelY1 > TerminalView.this.mEmulator.mRows - 1) {
                    TerminalView.this.mSelY1 = terminalView2.mEmulator.mRows - 1;
                }
                if (TerminalView.this.mSelY1 > TerminalView.this.mSelY2) {
                    TerminalView terminalView5 = TerminalView.this;
                    terminalView5.mSelY1 = terminalView5.mSelY2;
                }
                if (TerminalView.this.mSelY1 == TerminalView.this.mSelY2 && TerminalView.this.mSelX1 > TerminalView.this.mSelX2) {
                    TerminalView terminalView6 = TerminalView.this;
                    terminalView6.mSelX1 = terminalView6.mSelX2;
                }
                if (!TerminalView.this.mEmulator.isAlternateBufferActive()) {
                    if (TerminalView.this.mSelY1 <= TerminalView.this.mTopRow) {
                        TerminalView terminalView7 = TerminalView.this;
                        terminalView7.mTopRow--;
                        if (TerminalView.this.mTopRow < i3) {
                            TerminalView.this.mTopRow = i3;
                        }
                    } else if (TerminalView.this.mSelY1 >= TerminalView.this.mTopRow + TerminalView.this.mEmulator.mRows) {
                        TerminalView.this.mTopRow++;
                        if (TerminalView.this.mTopRow > 0) {
                            TerminalView.this.mTopRow = 0;
                        }
                    }
                }
                TerminalView terminalView8 = TerminalView.this;
                terminalView8.mSelX1 = getValidCurX(screen, terminalView8.mSelY1, TerminalView.this.mSelX1);
            } else {
                TerminalView terminalView9 = TerminalView.this;
                terminalView9.mSelX2 = terminalView9.getCursorX(i);
                TerminalView terminalView10 = TerminalView.this;
                terminalView10.mSelY2 = terminalView10.getCursorY(i2);
                if (TerminalView.this.mSelX2 < 0) {
                    TerminalView.this.mSelX2 = 0;
                }
                int i4 = -activeRows;
                if (TerminalView.this.mSelY2 < i4) {
                    TerminalView.this.mSelY2 = i4;
                } else if (TerminalView.this.mSelY2 > TerminalView.this.mEmulator.mRows - 1) {
                    TerminalView.this.mSelY2 = terminalView.mEmulator.mRows - 1;
                }
                if (TerminalView.this.mSelY1 > TerminalView.this.mSelY2) {
                    TerminalView terminalView11 = TerminalView.this;
                    terminalView11.mSelY2 = terminalView11.mSelY1;
                }
                if (TerminalView.this.mSelY1 == TerminalView.this.mSelY2 && TerminalView.this.mSelX1 > TerminalView.this.mSelX2) {
                    TerminalView terminalView12 = TerminalView.this;
                    terminalView12.mSelX2 = terminalView12.mSelX1;
                }
                if (!TerminalView.this.mEmulator.isAlternateBufferActive()) {
                    if (TerminalView.this.mSelY2 <= TerminalView.this.mTopRow) {
                        TerminalView terminalView13 = TerminalView.this;
                        terminalView13.mTopRow--;
                        if (TerminalView.this.mTopRow < i4) {
                            TerminalView.this.mTopRow = i4;
                        }
                    } else if (TerminalView.this.mSelY2 >= TerminalView.this.mTopRow + TerminalView.this.mEmulator.mRows) {
                        TerminalView.this.mTopRow++;
                        if (TerminalView.this.mTopRow > 0) {
                            TerminalView.this.mTopRow = 0;
                        }
                    }
                }
                TerminalView terminalView14 = TerminalView.this;
                terminalView14.mSelX2 = getValidCurX(screen, terminalView14.mSelY2, TerminalView.this.mSelX2);
            }
            TerminalView.this.invalidate();
        }

        private int getValidCurX(TerminalBuffer terminalBuffer, int i, int i2) {
            int i3;
            int width;
            int i4 = 0;
            String selectedText = terminalBuffer.getSelectedText(0, i, i2, i);
            if (!TextUtils.isEmpty(selectedText)) {
                int length = selectedText.length();
                int i5 = 0;
                while (i4 < length) {
                    char charAt = selectedText.charAt(i4);
                    if (charAt == 0) {
                        break;
                    }
                    if (Character.isHighSurrogate(charAt) && (i3 = i4 + 1) < length) {
                        width = WcWidth.width(Character.toCodePoint(charAt, selectedText.charAt(i3)));
                    } else {
                        i3 = i4;
                        width = WcWidth.width(charAt);
                    }
                    int i6 = width + i5;
                    if (i2 > i5 && i2 < i6) {
                        return i6;
                    }
                    if (i6 == i5) {
                        return i5;
                    }
                    i5 = i6;
                    i4 = i3 + 1;
                }
            }
            return i2;
        }

        @Override // com.termfu.view.TerminalView.CursorController
        public void updatePosition() {
            if (isActive()) {
                this.mStartHandle.positionAtCursor(TerminalView.this.mSelX1, TerminalView.this.mSelY1, false);
                this.mEndHandle.positionAtCursor(TerminalView.this.mSelX2 + 1, TerminalView.this.mSelY2, false);
                if (TerminalView.this.mActionMode != null) {
                    TerminalView.this.mActionMode.invalidate();
                }
            }
        }

        public boolean isSelectionStartDragged() {
            return this.mStartHandle.isDragging();
        }

        public boolean isSelectionEndDragged() {
            return this.mEndHandle.isDragging();
        }

        @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
        public void onTouchModeChanged(boolean z) {
            if (z) {
                return;
            }
            hide();
        }
    }

    SelectionModifierCursorController getSelectionController() {
        if (this.mSelectionModifierCursorController == null) {
            this.mSelectionModifierCursorController = new SelectionModifierCursorController();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnTouchModeChangeListener(this.mSelectionModifierCursorController);
            }
        }
        return this.mSelectionModifierCursorController;
    }

    private void hideSelectionModifierCursorController() {
        SelectionModifierCursorController selectionModifierCursorController = this.mSelectionModifierCursorController;
        if (selectionModifierCursorController == null || !selectionModifierCursorController.isActive()) {
            return;
        }
        this.mSelectionModifierCursorController.hide();
    }

    private void startTextSelectionMode() {
        if (requestFocus()) {
            getSelectionController().show();
            this.mIsSelectingText = true;
            this.mClient.copyModeChanged(true);
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopTextSelectionMode() {
        if (this.mIsSelectingText) {
            hideSelectionModifierCursorController();
            this.mSelY2 = -1;
            this.mSelX2 = -1;
            this.mSelY1 = -1;
            this.mSelX1 = -1;
            this.mIsSelectingText = false;
            this.mClient.copyModeChanged(false);
            invalidate();
        }
    }

    void hideFloatingToolbar(int i) {
        if (this.mActionMode != null) {
            removeCallbacks(this.mShowFloatingToolbar);
            this.mActionMode.hide(i);
        }
    }

    private void showFloatingToolbar() {
        if (this.mActionMode != null) {
            postDelayed(this.mShowFloatingToolbar, ViewConfiguration.getDoubleTapTimeout());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFloatingToolbarVisibility(MotionEvent motionEvent) {
        if (this.mActionMode != null) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    hideFloatingToolbar(-1);
                    return;
                } else if (actionMasked != 3) {
                    return;
                }
            }
            showFloatingToolbar();
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        String[] strArr = {getContext().getFilesDir() + "/home/.termfu/termfu.properties", getContext().getFilesDir() + "/home/.config/termfu/termfu.properties"};
        File file = new File(strArr[0]);
        for (int i = 0; !file.exists() && i < 2; i++) {
            file = new File(strArr[i]);
        }
        try {
            if (file.isFile() && file.canRead()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    properties.load(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
                    fileInputStream.close();
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
            }
        } catch (Exception e) {
            Log.e(EmulatorDebug.LOG_TAG, "Error loading props", e);
        }
        return properties;
    }

    @Override // android.view.View
    @RequiresApi(api = 26)
    public void autofill(AutofillValue autofillValue) {
        if (autofillValue.isText()) {
            this.mTermSession.write(autofillValue.getTextValue().toString());
        }
    }

    @Override // android.view.View
    @RequiresApi(api = 26)
    public AutofillValue getAutofillValue() {
        return AutofillValue.forText("");
    }
}
