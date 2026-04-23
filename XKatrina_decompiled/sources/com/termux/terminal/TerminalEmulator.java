package com.termux.terminal;

import android.util.Log;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Stack;
/* loaded from: classes6.dex */
public final class TerminalEmulator {
    public static final int CURSOR_STYLE_BAR = 2;
    public static final int CURSOR_STYLE_BLOCK = 0;
    public static final int CURSOR_STYLE_UNDERLINE = 1;
    private static final int DECSET_BIT_APPLICATION_CURSOR_KEYS = 1;
    private static final int DECSET_BIT_APPLICATION_KEYPAD = 32;
    private static final int DECSET_BIT_AUTOWRAP = 8;
    private static final int DECSET_BIT_BRACKETED_PASTE_MODE = 1024;
    private static final int DECSET_BIT_LEFTRIGHT_MARGIN_MODE = 2048;
    private static final int DECSET_BIT_MOUSE_PROTOCOL_SGR = 512;
    private static final int DECSET_BIT_MOUSE_TRACKING_BUTTON_EVENT = 128;
    private static final int DECSET_BIT_MOUSE_TRACKING_PRESS_RELEASE = 64;
    private static final int DECSET_BIT_ORIGIN_MODE = 4;
    private static final int DECSET_BIT_RECTANGULAR_CHANGEATTRIBUTE = 4096;
    private static final int DECSET_BIT_REVERSE_VIDEO = 2;
    private static final int DECSET_BIT_SEND_FOCUS_EVENTS = 256;
    private static final int DECSET_BIT_SHOWING_CURSOR = 16;
    private static final int ESC = 1;
    private static final int ESC_CSI = 6;
    private static final int ESC_CSI_ARGS_ASTERIX = 16;
    private static final int ESC_CSI_ARGS_SPACE = 15;
    private static final int ESC_CSI_BIGGERTHAN = 12;
    private static final int ESC_CSI_DOLLAR = 8;
    private static final int ESC_CSI_DOUBLE_QUOTE = 17;
    private static final int ESC_CSI_EXCLAMATION = 19;
    private static final int ESC_CSI_QUESTIONMARK = 7;
    private static final int ESC_CSI_QUESTIONMARK_ARG_DOLLAR = 14;
    private static final int ESC_CSI_SINGLE_QUOTE = 18;
    private static final int ESC_NONE = 0;
    private static final int ESC_OSC = 10;
    private static final int ESC_OSC_ESC = 11;
    private static final int ESC_P = 13;
    private static final int ESC_PERCENT = 9;
    private static final int ESC_POUND = 2;
    private static final int ESC_SELECT_LEFT_PAREN = 3;
    private static final int ESC_SELECT_RIGHT_PAREN = 4;
    private static final boolean LOG_ESCAPE_SEQUENCES = false;
    private static final int MAX_ESCAPE_PARAMETERS = 16;
    private static final int MAX_OSC_STRING_LENGTH = 8192;
    public static final int MOUSE_LEFT_BUTTON = 0;
    public static final int MOUSE_LEFT_BUTTON_MOVED = 32;
    public static final int MOUSE_WHEELDOWN_BUTTON = 65;
    public static final int MOUSE_WHEELUP_BUTTON = 64;
    public static final int UNICODE_REPLACEMENT_CHAR = 65533;
    private boolean mAboutToAutoWrap;
    final TerminalBuffer mAltBuffer;
    private int mArgIndex;
    int mBackColor;
    private int mBottomMargin;
    public int mColumns;
    private boolean mContinueSequence;
    private int mCurrentDecSetFlags;
    private int mCursorCol;
    private int mCursorRow;
    private int mEffect;
    private int mEscapeState;
    int mForeColor;
    private boolean mInsertMode;
    private int mLeftMargin;
    private final TerminalBuffer mMainBuffer;
    private int mRightMargin;
    public int mRows;
    private int mSavedDecSetFlags;
    private TerminalBuffer mScreen;
    private final TerminalOutput mSession;
    private boolean[] mTabStop;
    private String mTitle;
    private int mTopMargin;
    private boolean mUseLineDrawingG0;
    private boolean mUseLineDrawingG1;
    private byte mUtf8Index;
    private byte mUtf8ToFollow;
    private final Stack<String> mTitleStack = new Stack<>();
    private int mCursorStyle = 0;
    private final int[] mArgs = new int[16];
    private final StringBuilder mOSCOrDeviceControlArgs = new StringBuilder();
    private final SavedScreenState mSavedStateMain = new SavedScreenState();
    private final SavedScreenState mSavedStateAlt = new SavedScreenState();
    private boolean mUseLineDrawingUsesG0 = true;
    private int mScrollCounter = 0;
    private final byte[] mUtf8InputBuffer = new byte[4];
    private int mLastEmittedCodePoint = -1;
    public final TerminalColors mColors = new TerminalColors();

    private void logError(String str) {
    }

    static int mapDecSetBitToInternalBit(int i) {
        if (i != 1) {
            if (i != 25) {
                if (i != 66) {
                    if (i != 69) {
                        if (i != 1000) {
                            if (i != 1002) {
                                if (i != 1004) {
                                    if (i != 1006) {
                                        if (i != 2004) {
                                            if (i != 5) {
                                                if (i != 6) {
                                                    return i != 7 ? -1 : 8;
                                                }
                                                return 4;
                                            }
                                            return 2;
                                        }
                                        return 1024;
                                    }
                                    return 512;
                                }
                                return 256;
                            }
                            return 128;
                        }
                        return 64;
                    }
                    return 2048;
                }
                return 32;
            }
            return 16;
        }
        return 1;
    }

    private boolean isDecsetInternalBitSet(int i) {
        return (i & this.mCurrentDecSetFlags) != 0;
    }

    private void setDecsetinternalBit(int i, boolean z) {
        if (z) {
            if (i == 64) {
                setDecsetinternalBit(128, false);
            } else if (i == 128) {
                setDecsetinternalBit(64, false);
            }
        }
        if (z) {
            this.mCurrentDecSetFlags = i | this.mCurrentDecSetFlags;
            return;
        }
        this.mCurrentDecSetFlags = (~i) & this.mCurrentDecSetFlags;
    }

    public TerminalEmulator(TerminalOutput terminalOutput, int i, int i2, int i3) {
        this.mSession = terminalOutput;
        TerminalBuffer terminalBuffer = new TerminalBuffer(i, i3, i2);
        this.mMainBuffer = terminalBuffer;
        this.mScreen = terminalBuffer;
        this.mAltBuffer = new TerminalBuffer(i, i2, i2);
        this.mRows = i2;
        this.mColumns = i;
        this.mTabStop = new boolean[i];
        reset();
    }

    public TerminalBuffer getScreen() {
        return this.mScreen;
    }

    public boolean isAlternateBufferActive() {
        return this.mScreen == this.mAltBuffer;
    }

    public void sendMouseEvent(int i, int i2, int i3, boolean z) {
        if (i2 < 1) {
            i2 = 1;
        }
        int i4 = this.mColumns;
        if (i2 > i4) {
            i2 = i4;
        }
        if (i3 < 1) {
            i3 = 1;
        }
        int i5 = this.mRows;
        if (i3 > i5) {
            i3 = i5;
        }
        if (i != 32 || isDecsetInternalBitSet(128)) {
            if (isDecsetInternalBitSet(512)) {
                TerminalOutput terminalOutput = this.mSession;
                StringBuilder sb = new StringBuilder("\u001b[<%d;%d;%d");
                sb.append(z ? 'M' : 'm');
                terminalOutput.write(String.format(sb.toString(), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
                return;
            }
            if (!z) {
                i = 3;
            }
            if (i2 > 223 || i3 > 223) {
                return;
            }
            this.mSession.write(new byte[]{27, 91, 77, (byte) (i + 32), (byte) (i2 + 32), (byte) (i3 + 32)}, 0, 6);
        }
    }

    public void resize(int i, int i2) {
        int i3 = this.mRows;
        if (i3 == i2 && this.mColumns == i) {
            return;
        }
        if (i < 2 || i2 < 2) {
            throw new IllegalArgumentException("rows=" + i2 + ", columns=" + i);
        }
        if (i3 != i2) {
            this.mRows = i2;
            this.mTopMargin = 0;
            this.mBottomMargin = i2;
        }
        int i4 = this.mColumns;
        if (i4 != i) {
            this.mColumns = i;
            boolean[] zArr = this.mTabStop;
            this.mTabStop = new boolean[i];
            setDefaultTabStops();
            System.arraycopy(zArr, 0, this.mTabStop, 0, Math.min(i4, i));
            this.mLeftMargin = 0;
            this.mRightMargin = this.mColumns;
        }
        resizeScreen();
    }

    private void resizeScreen() {
        int[] iArr = {this.mCursorCol, this.mCursorRow};
        this.mScreen.resize(this.mColumns, this.mRows, this.mScreen == this.mAltBuffer ? this.mRows : this.mMainBuffer.mTotalRows, iArr, getStyle(), isAlternateBufferActive());
        this.mCursorCol = iArr[0];
        this.mCursorRow = iArr[1];
    }

    public int getCursorRow() {
        return this.mCursorRow;
    }

    public int getCursorCol() {
        return this.mCursorCol;
    }

    public int getCursorStyle() {
        return this.mCursorStyle;
    }

    public boolean isReverseVideo() {
        return isDecsetInternalBitSet(2);
    }

    public boolean isShowingCursor() {
        return isDecsetInternalBitSet(16);
    }

    public boolean isKeypadApplicationMode() {
        return isDecsetInternalBitSet(32);
    }

    public boolean isCursorKeysApplicationMode() {
        return isDecsetInternalBitSet(1);
    }

    public boolean isMouseTrackingActive() {
        return isDecsetInternalBitSet(64) || isDecsetInternalBitSet(128);
    }

    private void setDefaultTabStops() {
        int i = 0;
        while (i < this.mColumns) {
            this.mTabStop[i] = (i & 7) == 0 && i != 0;
            i++;
        }
    }

    public void append(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            processByte(bArr[i2]);
        }
    }

    private void processByte(byte b) {
        byte b2;
        byte b3 = this.mUtf8ToFollow;
        int i = UNICODE_REPLACEMENT_CHAR;
        if (b3 <= 0) {
            if ((b & 128) == 0) {
                processCodePoint(b);
                return;
            }
            if ((b & 224) == 192) {
                this.mUtf8ToFollow = (byte) 1;
            } else if ((b & 240) == 224) {
                this.mUtf8ToFollow = (byte) 2;
            } else if ((b & 248) == 240) {
                this.mUtf8ToFollow = (byte) 3;
            } else {
                processCodePoint(UNICODE_REPLACEMENT_CHAR);
                return;
            }
            byte[] bArr = this.mUtf8InputBuffer;
            byte b4 = this.mUtf8Index;
            this.mUtf8Index = (byte) (b4 + 1);
            bArr[b4] = b;
        } else if ((b & 192) == 128) {
            byte[] bArr2 = this.mUtf8InputBuffer;
            byte b5 = this.mUtf8Index;
            byte b6 = (byte) (b5 + 1);
            this.mUtf8Index = b6;
            bArr2[b5] = b;
            byte b7 = (byte) (b3 - 1);
            this.mUtf8ToFollow = b7;
            if (b7 == 0) {
                int i2 = ((byte) (b6 == 2 ? 31 : b6 == 3 ? 15 : 7)) & bArr2[0];
                int i3 = 1;
                while (true) {
                    b2 = this.mUtf8Index;
                    if (i3 >= b2) {
                        break;
                    }
                    i2 = (i2 << 6) | (this.mUtf8InputBuffer[i3] & 63);
                    i3++;
                }
                if ((i2 <= 127 && b2 > 1) || ((i2 < 2047 && b2 > 2) || (i2 < 65535 && b2 > 3))) {
                    i2 = UNICODE_REPLACEMENT_CHAR;
                }
                this.mUtf8ToFollow = (byte) 0;
                this.mUtf8Index = (byte) 0;
                if (i2 < 128 || i2 > 159) {
                    int type = Character.getType(i2);
                    if (type != 0 && type != 19) {
                        i = i2;
                    }
                    processCodePoint(i);
                }
            }
        } else {
            this.mUtf8ToFollow = (byte) 0;
            this.mUtf8Index = (byte) 0;
            emitCodePoint(UNICODE_REPLACEMENT_CHAR);
            processByte(b);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:178:0x033c, code lost:
        if (r1 == false) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0115, code lost:
        if (isDecsetInternalBitSet(r2) != false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0117, code lost:
        r2 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0119, code lost:
        r2 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0134, code lost:
        if (r29.mScreen == r29.mAltBuffer) goto L61;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void processCodePoint(int r30) {
        /*
            Method dump skipped, instructions count: 1104
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termux.terminal.TerminalEmulator.processCodePoint(int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d6, code lost:
        if (r7.equals("Co") == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00df, code lost:
        if (r7.equals("colors") == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f1, code lost:
        r9 = "256";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void doDeviceControl(int r14) {
        /*
            Method dump skipped, instructions count: 456
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termux.terminal.TerminalEmulator.doDeviceControl(int):void");
    }

    private int nextTabStop(int i) {
        int i2 = this.mCursorCol;
        while (true) {
            i2++;
            if (i2 < this.mColumns) {
                if (this.mTabStop[i2] && i - 1 == 0) {
                    return Math.min(i2, this.mRightMargin);
                }
            } else {
                return this.mRightMargin - 1;
            }
        }
    }

    private void doCsiQuestionMark(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (i == 36) {
            continueSequence(14);
        } else if (i == 104 || i == 108) {
            int i6 = this.mArgIndex;
            int[] iArr = this.mArgs;
            if (i6 >= iArr.length) {
                this.mArgIndex = iArr.length - 1;
            }
            for (int i7 = 0; i7 <= this.mArgIndex; i7++) {
                doDecSetOrReset(i == 104, this.mArgs[i7]);
            }
        } else {
            int i8 = -1;
            if (i == 110) {
                if (getArg0(-1) == 6) {
                    this.mSession.write(String.format(Locale.US, "\u001b[?%d;%d;1R", Integer.valueOf(this.mCursorRow + 1), Integer.valueOf(this.mCursorCol + 1)));
                } else {
                    finishSequence();
                }
            } else if (i == 74 || i == 75) {
                this.mAboutToAutoWrap = false;
                boolean z = i == 75;
                int arg0 = getArg0(0);
                if (arg0 != 0) {
                    if (arg0 == 1) {
                        i8 = z ? this.mCursorRow : 0;
                        i2 = this.mCursorCol + 1;
                        i5 = 1 + this.mCursorRow;
                    } else if (arg0 == 2) {
                        i8 = z ? this.mCursorRow : 0;
                        i2 = this.mColumns;
                        i5 = z ? this.mCursorRow + 1 : this.mRows;
                    } else {
                        unknownSequence(i);
                        i2 = -1;
                        i3 = -1;
                        i4 = -1;
                    }
                    i4 = i5;
                    i3 = 0;
                } else {
                    int i9 = this.mCursorCol;
                    i8 = this.mCursorRow;
                    i2 = this.mColumns;
                    int i10 = z ? i8 + 1 : this.mRows;
                    i3 = i9;
                    i4 = i10;
                }
                long style = getStyle();
                for (int i11 = i8; i11 < i4; i11++) {
                    for (int i12 = i3; i12 < i2; i12++) {
                        if ((TextStyle.decodeEffect(this.mScreen.getStyleAt(i11, i12)) & 128) == 0) {
                            this.mScreen.setChar(i12, i11, 32, style);
                        }
                    }
                }
            } else {
                if (i == 114 || i == 115) {
                    int i13 = this.mArgIndex;
                    int[] iArr2 = this.mArgs;
                    if (i13 >= iArr2.length) {
                        this.mArgIndex = iArr2.length - 1;
                    }
                    for (int i14 = 0; i14 <= this.mArgIndex; i14++) {
                        int i15 = this.mArgs[i14];
                        int mapDecSetBitToInternalBit = mapDecSetBitToInternalBit(i15);
                        if (mapDecSetBitToInternalBit == -1) {
                            Log.w(EmulatorDebug.LOG_TAG, "Ignoring request to save/recall decset bit=" + i15);
                        } else if (i == 115) {
                            this.mSavedDecSetFlags |= mapDecSetBitToInternalBit;
                        } else {
                            doDecSetOrReset((mapDecSetBitToInternalBit & this.mSavedDecSetFlags) != 0, i15);
                        }
                    }
                    return;
                }
                parseArg(i);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void doDecSetOrReset(boolean z, int i) {
        int mapDecSetBitToInternalBit = mapDecSetBitToInternalBit(i);
        if (mapDecSetBitToInternalBit != -1) {
            setDecsetinternalBit(mapDecSetBitToInternalBit, z);
        }
        boolean z2 = false;
        switch (i) {
            case 1:
            case 12:
            case 25:
            case 40:
            case 45:
            case 66:
            case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW /* 1015 */:
            case 1034:
            case 2004:
                return;
            case 47:
                break;
            case 69:
                if (z) {
                    return;
                }
                this.mLeftMargin = 0;
                this.mRightMargin = this.mColumns;
                return;
            default:
                switch (i) {
                    case 3:
                        this.mTopMargin = 0;
                        this.mLeftMargin = 0;
                        this.mBottomMargin = this.mRows;
                        this.mRightMargin = this.mColumns;
                        setDecsetinternalBit(2048, false);
                        blockClear(0, 0, this.mColumns, this.mRows);
                        setCursorRowCol(0, 0);
                        return;
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                        return;
                    case 6:
                        if (z) {
                            setCursorPosition(0, 0);
                            return;
                        }
                        return;
                    default:
                        switch (i) {
                            case 1000:
                            case PointerIconCompat.TYPE_CONTEXT_MENU /* 1001 */:
                            case PointerIconCompat.TYPE_HAND /* 1002 */:
                            case PointerIconCompat.TYPE_HELP /* 1003 */:
                            case PointerIconCompat.TYPE_WAIT /* 1004 */:
                            case 1005:
                            case PointerIconCompat.TYPE_CELL /* 1006 */:
                                return;
                            default:
                                switch (i) {
                                    case 1047:
                                    case 1049:
                                        break;
                                    case 1048:
                                        if (z) {
                                            saveCursor();
                                            return;
                                        } else {
                                            restoreCursor();
                                            return;
                                        }
                                    default:
                                        unknownParameter(i);
                                        return;
                                }
                        }
                }
        }
        TerminalBuffer terminalBuffer = z ? this.mAltBuffer : this.mMainBuffer;
        if (terminalBuffer != this.mScreen) {
            z2 = (terminalBuffer.mColumns == this.mColumns && terminalBuffer.mScreenRows == this.mRows) ? true : true;
            if (z) {
                saveCursor();
            }
            this.mScreen = terminalBuffer;
            if (!z) {
                int i2 = this.mSavedStateMain.mSavedCursorCol;
                int i3 = this.mSavedStateMain.mSavedCursorRow;
                restoreCursor();
                if (z2) {
                    this.mCursorCol = i2;
                    this.mCursorRow = i3;
                }
            }
            if (z2) {
                resizeScreen();
            }
            if (terminalBuffer == this.mAltBuffer) {
                terminalBuffer.blockSet(0, 0, this.mColumns, this.mRows, 32, getStyle());
            }
        }
    }

    private void doCsiBiggerThan(int i) {
        if (i == 99) {
            this.mSession.write("\u001b[>41;320;0c");
        } else if (i == 109) {
            Log.e(EmulatorDebug.LOG_TAG, "(ignored) CSI > MODIFY RESOURCE: " + getArg0(-1) + " to " + getArg1(-1));
        } else {
            parseArg(i);
        }
    }

    private void startEscapeSequence() {
        this.mEscapeState = 1;
        this.mArgIndex = 0;
        Arrays.fill(this.mArgs, -1);
    }

    private void doLinefeed() {
        int i = this.mCursorRow;
        int i2 = this.mBottomMargin;
        int i3 = i + 1;
        if (i >= i2) {
            if (i != this.mRows - 1) {
                setCursorRow(i3);
                return;
            }
            return;
        }
        if (i3 == i2) {
            scrollDownOneLine();
            i3 = this.mBottomMargin - 1;
        }
        setCursorRow(i3);
    }

    private void continueSequence(int i) {
        this.mEscapeState = i;
        this.mContinueSequence = true;
    }

    private void doEscPound(int i) {
        if (i == 56) {
            this.mScreen.blockSet(0, 0, this.mColumns, this.mRows, 69, getStyle());
        } else {
            unknownSequence(i);
        }
    }

    private void doEsc(int i) {
        if (i == 35) {
            continueSequence(2);
        } else if (i != 48) {
            if (i == 72) {
                this.mTabStop[this.mCursorCol] = true;
                return;
            }
            if (i == 80) {
                this.mOSCOrDeviceControlArgs.setLength(0);
                continueSequence(13);
            } else if (i == 91) {
                continueSequence(6);
            } else if (i == 93) {
                this.mOSCOrDeviceControlArgs.setLength(0);
                continueSequence(10);
            } else if (i == 99) {
                reset();
                this.mMainBuffer.clearTranscript();
                blockClear(0, 0, this.mColumns, this.mRows);
                setCursorPosition(0, 0);
            } else if (i == 40) {
                continueSequence(3);
            } else if (i == 41) {
                continueSequence(4);
            } else if (i == 61) {
                setDecsetinternalBit(32, true);
            } else if (i == 62) {
                setDecsetinternalBit(32, false);
            } else if (i == 77) {
                int i2 = this.mCursorRow;
                int i3 = this.mTopMargin;
                if (i2 <= i3) {
                    this.mScreen.blockCopy(0, i3, this.mColumns, this.mBottomMargin - (i3 + 1), 0, i3 + 1);
                    blockClear(0, this.mTopMargin, this.mColumns);
                    return;
                }
                this.mCursorRow = i2 - 1;
            } else if (i != 78) {
                switch (i) {
                    case 54:
                        int i4 = this.mCursorCol;
                        int i5 = this.mLeftMargin;
                        if (i4 > i5) {
                            this.mCursorCol = i4 - 1;
                            return;
                        }
                        int i6 = this.mBottomMargin;
                        int i7 = this.mTopMargin;
                        int i8 = i6 - i7;
                        this.mScreen.blockCopy(i5, i7, (this.mRightMargin - i5) - 1, i8, i5 + 1, i7);
                        this.mScreen.blockSet(this.mLeftMargin, this.mTopMargin, 1, i8, 32, TextStyle.encode(this.mForeColor, this.mBackColor, 0));
                        return;
                    case 55:
                        saveCursor();
                        return;
                    case 56:
                        restoreCursor();
                        return;
                    case 57:
                        int i9 = this.mCursorCol;
                        int i10 = this.mRightMargin;
                        if (i9 < i10 - 1) {
                            this.mCursorCol = i9 + 1;
                            return;
                        }
                        int i11 = this.mBottomMargin;
                        int i12 = this.mTopMargin;
                        int i13 = i11 - i12;
                        TerminalBuffer terminalBuffer = this.mScreen;
                        int i14 = this.mLeftMargin;
                        terminalBuffer.blockCopy(i14 + 1, i12, (i10 - i14) - 1, i13, i14, i12);
                        this.mScreen.blockSet(this.mRightMargin - 1, this.mTopMargin, 1, i13, 32, TextStyle.encode(this.mForeColor, this.mBackColor, 0));
                        return;
                    default:
                        switch (i) {
                            case 68:
                                doLinefeed();
                                return;
                            case 69:
                                setCursorCol(isDecsetInternalBitSet(4) ? this.mLeftMargin : 0);
                                doLinefeed();
                                return;
                            case 70:
                                setCursorRowCol(0, this.mBottomMargin - 1);
                                return;
                            default:
                                unknownSequence(i);
                                return;
                        }
                }
            }
        }
    }

    private void saveCursor() {
        SavedScreenState savedScreenState = this.mScreen == this.mMainBuffer ? this.mSavedStateMain : this.mSavedStateAlt;
        savedScreenState.mSavedCursorRow = this.mCursorRow;
        savedScreenState.mSavedCursorCol = this.mCursorCol;
        savedScreenState.mSavedEffect = this.mEffect;
        savedScreenState.mSavedForeColor = this.mForeColor;
        savedScreenState.mSavedBackColor = this.mBackColor;
        savedScreenState.mSavedDecFlags = this.mCurrentDecSetFlags;
        savedScreenState.mUseLineDrawingG0 = this.mUseLineDrawingG0;
        savedScreenState.mUseLineDrawingG1 = this.mUseLineDrawingG1;
        savedScreenState.mUseLineDrawingUsesG0 = this.mUseLineDrawingUsesG0;
    }

    private void restoreCursor() {
        SavedScreenState savedScreenState = this.mScreen == this.mMainBuffer ? this.mSavedStateMain : this.mSavedStateAlt;
        setCursorRowCol(savedScreenState.mSavedCursorRow, savedScreenState.mSavedCursorCol);
        this.mEffect = savedScreenState.mSavedEffect;
        this.mForeColor = savedScreenState.mSavedForeColor;
        this.mBackColor = savedScreenState.mSavedBackColor;
        this.mCurrentDecSetFlags = (this.mCurrentDecSetFlags & (-13)) | (savedScreenState.mSavedDecFlags & 12);
        this.mUseLineDrawingG0 = savedScreenState.mUseLineDrawingG0;
        this.mUseLineDrawingG1 = savedScreenState.mUseLineDrawingG1;
        this.mUseLineDrawingUsesG0 = savedScreenState.mUseLineDrawingUsesG0;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0343  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void doCsi(int r11) {
        /*
            Method dump skipped, instructions count: 1256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termux.terminal.TerminalEmulator.doCsi(int):void");
    }

    private void selectGraphicRendition() {
        int i = this.mArgIndex;
        int[] iArr = this.mArgs;
        if (i >= iArr.length) {
            this.mArgIndex = iArr.length - 1;
        }
        int i2 = 0;
        while (true) {
            int i3 = this.mArgIndex;
            if (i2 > i3) {
                return;
            }
            int[] iArr2 = this.mArgs;
            int i4 = iArr2[i2];
            if (i4 < 0) {
                if (i3 > 0) {
                    i2++;
                } else {
                    i4 = 0;
                }
            }
            if (i4 == 0) {
                this.mForeColor = 256;
                this.mBackColor = 257;
                this.mEffect = 0;
            } else if (i4 == 1) {
                this.mEffect |= 1;
            } else if (i4 == 2) {
                this.mEffect |= 256;
            } else if (i4 == 3) {
                this.mEffect |= 2;
            } else if (i4 == 4) {
                this.mEffect |= 4;
            } else if (i4 == 5) {
                this.mEffect |= 8;
            } else if (i4 == 7) {
                this.mEffect |= 16;
            } else if (i4 == 8) {
                this.mEffect |= 32;
            } else if (i4 == 9) {
                this.mEffect |= 64;
            } else if (i4 != 10 && i4 != 11) {
                if (i4 == 22) {
                    this.mEffect &= -258;
                } else if (i4 == 23) {
                    this.mEffect &= -3;
                } else if (i4 == 24) {
                    this.mEffect &= -5;
                } else if (i4 == 25) {
                    this.mEffect &= -9;
                } else if (i4 == 27) {
                    this.mEffect &= -17;
                } else if (i4 == 28) {
                    this.mEffect &= -33;
                } else if (i4 == 29) {
                    this.mEffect &= -65;
                } else if (i4 >= 30 && i4 <= 37) {
                    this.mForeColor = i4 - 30;
                } else if (i4 == 38 || i4 == 48) {
                    int i5 = i2 + 2;
                    if (i5 <= i3) {
                        int i6 = iArr2[i2 + 1];
                        if (i6 == 2) {
                            int i7 = i2 + 4;
                            if (i7 > i3) {
                                Log.w(EmulatorDebug.LOG_TAG, "Too few CSI" + i4 + ";2 RGB arguments");
                            } else {
                                int i8 = iArr2[i5];
                                int i9 = iArr2[i2 + 3];
                                int i10 = iArr2[i7];
                                if (i8 < 0 || i9 < 0 || i10 < 0 || i8 > 255 || i9 > 255 || i10 > 255) {
                                    finishSequenceAndLogError("Invalid RGB: " + i8 + "," + i9 + "," + i10);
                                } else {
                                    int i11 = (i9 << 8) | (i8 << 16) | ViewCompat.MEASURED_STATE_MASK | i10;
                                    if (i4 == 38) {
                                        this.mForeColor = i11;
                                    } else {
                                        this.mBackColor = i11;
                                    }
                                }
                                i2 = i7;
                            }
                        } else if (i6 == 5) {
                            int i12 = iArr2[i5];
                            if (i12 >= 0 && i12 < 259) {
                                if (i4 == 38) {
                                    this.mForeColor = i12;
                                } else {
                                    this.mBackColor = i12;
                                }
                            }
                            i2 = i5;
                        } else {
                            finishSequenceAndLogError("Invalid ISO-8613-3 SGR first argument: " + i6);
                        }
                    }
                } else if (i4 == 39) {
                    this.mForeColor = 256;
                } else if (i4 >= 40 && i4 <= 47) {
                    this.mBackColor = i4 - 40;
                } else if (i4 == 49) {
                    this.mBackColor = 257;
                } else if (i4 >= 90 && i4 <= 97) {
                    this.mForeColor = (i4 - 90) + 8;
                } else if (i4 >= 100 && i4 <= 107) {
                    this.mBackColor = (i4 - 100) + 8;
                }
            }
            i2++;
        }
    }

    private void doOsc(int i) {
        if (i == 7) {
            doOscSetTextParameters("\u0007");
        } else if (i == 27) {
            continueSequence(11);
        } else {
            collectOSCArgs(i);
        }
    }

    private void doOscEsc(int i) {
        if (i == 92) {
            doOscSetTextParameters("\u001b\\");
            return;
        }
        collectOSCArgs(27);
        collectOSCArgs(i);
        continueSequence(10);
    }

    /* JADX WARN: Code restructure failed: missing block: B:118:0x0223, code lost:
        unknownSequence(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0226, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01e4, code lost:
        unknownSequence(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01e7, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void doOscSetTextParameters(java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termux.terminal.TerminalEmulator.doOscSetTextParameters(java.lang.String):void");
    }

    private void blockClear(int i, int i2, int i3) {
        blockClear(i, i2, i3, 1);
    }

    private void blockClear(int i, int i2, int i3, int i4) {
        this.mScreen.blockSet(i, i2, i3, i4, 32, getStyle());
    }

    private long getStyle() {
        return TextStyle.encode(this.mForeColor, this.mBackColor, this.mEffect);
    }

    private void doSetMode(boolean z) {
        int arg0 = getArg0(0);
        if (arg0 == 4) {
            this.mInsertMode = z;
        } else if (arg0 == 20) {
            unknownParameter(arg0);
        } else if (arg0 != 34) {
            unknownParameter(arg0);
        }
    }

    private void setCursorPosition(int i, int i2) {
        boolean isDecsetInternalBitSet = isDecsetInternalBitSet(4);
        int i3 = isDecsetInternalBitSet ? this.mTopMargin : 0;
        int i4 = isDecsetInternalBitSet ? this.mBottomMargin : this.mRows;
        int i5 = isDecsetInternalBitSet ? this.mLeftMargin : 0;
        setCursorRowCol(Math.max(i3, Math.min(i2 + i3, i4 - 1)), Math.max(i5, Math.min(i + i5, (isDecsetInternalBitSet ? this.mRightMargin : this.mColumns) - 1)));
    }

    private void scrollDownOneLine() {
        this.mScrollCounter++;
        int i = this.mLeftMargin;
        if (i != 0 || this.mRightMargin != this.mColumns) {
            TerminalBuffer terminalBuffer = this.mScreen;
            int i2 = this.mTopMargin;
            terminalBuffer.blockCopy(i, i2 + 1, this.mRightMargin - i, (this.mBottomMargin - i2) - 1, i, i2);
            TerminalBuffer terminalBuffer2 = this.mScreen;
            int i3 = this.mLeftMargin;
            terminalBuffer2.blockSet(i3, this.mBottomMargin - 1, this.mRightMargin - i3, 1, 32, this.mEffect);
            return;
        }
        this.mScreen.scrollDownOneLine(this.mTopMargin, this.mBottomMargin, getStyle());
    }

    private void parseArg(int i) {
        if (i < 48 || i > 57) {
            if (i == 59) {
                int i2 = this.mArgIndex;
                if (i2 < this.mArgs.length) {
                    this.mArgIndex = i2 + 1;
                }
                continueSequence(this.mEscapeState);
                return;
            }
            unknownSequence(i);
            return;
        }
        int i3 = this.mArgIndex;
        int[] iArr = this.mArgs;
        if (i3 < iArr.length) {
            int i4 = iArr[i3];
            int i5 = i - 48;
            if (i4 >= 0) {
                i5 += i4 * 10;
            }
            iArr[i3] = i5;
        }
        continueSequence(this.mEscapeState);
    }

    private int getArg0(int i) {
        return getArg(0, i, true);
    }

    private int getArg1(int i) {
        return getArg(1, i, true);
    }

    private int getArg(int i, int i2, boolean z) {
        int i3 = this.mArgs[i];
        return i3 >= 0 ? (i3 == 0 && z) ? i2 : i3 : i2;
    }

    private void collectOSCArgs(int i) {
        if (this.mOSCOrDeviceControlArgs.length() < 8192) {
            this.mOSCOrDeviceControlArgs.appendCodePoint(i);
            continueSequence(this.mEscapeState);
            return;
        }
        unknownSequence(i);
    }

    private void unimplementedSequence(int i) {
        logError("Unimplemented sequence char '" + ((char) i) + "' (U+" + String.format("%04x", Integer.valueOf(i)) + ")");
        finishSequence();
    }

    private void unknownSequence(int i) {
        logError("Unknown sequence char '" + ((char) i) + "' (numeric value=" + i + ")");
        finishSequence();
    }

    private void unknownParameter(int i) {
        logError("Unknown parameter: " + i);
        finishSequence();
    }

    private void finishSequenceAndLogError(String str) {
        finishSequence();
    }

    private void finishSequence() {
        this.mEscapeState = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x015b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void emitCodePoint(int r19) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termux.terminal.TerminalEmulator.emitCodePoint(int):void");
    }

    private void setCursorRow(int i) {
        this.mCursorRow = i;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorCol(int i) {
        this.mCursorCol = i;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorColRespectingOriginMode(int i) {
        setCursorPosition(i, this.mCursorRow);
    }

    private void setCursorRowCol(int i, int i2) {
        this.mCursorRow = Math.max(0, Math.min(i, this.mRows - 1));
        this.mCursorCol = Math.max(0, Math.min(i2, this.mColumns - 1));
        this.mAboutToAutoWrap = false;
    }

    public int getScrollCounter() {
        return this.mScrollCounter;
    }

    public void clearScrollCounter() {
        this.mScrollCounter = 0;
    }

    public void reset() {
        this.mCursorStyle = 0;
        this.mArgIndex = 0;
        this.mContinueSequence = false;
        this.mEscapeState = 0;
        this.mInsertMode = false;
        this.mLeftMargin = 0;
        this.mTopMargin = 0;
        this.mBottomMargin = this.mRows;
        this.mRightMargin = this.mColumns;
        this.mAboutToAutoWrap = false;
        SavedScreenState savedScreenState = this.mSavedStateMain;
        this.mSavedStateAlt.mSavedForeColor = 256;
        savedScreenState.mSavedForeColor = 256;
        this.mForeColor = 256;
        SavedScreenState savedScreenState2 = this.mSavedStateMain;
        this.mSavedStateAlt.mSavedBackColor = 257;
        savedScreenState2.mSavedBackColor = 257;
        this.mBackColor = 257;
        setDefaultTabStops();
        this.mUseLineDrawingG1 = false;
        this.mUseLineDrawingG0 = false;
        this.mUseLineDrawingUsesG0 = true;
        SavedScreenState savedScreenState3 = this.mSavedStateMain;
        savedScreenState3.mSavedDecFlags = 0;
        savedScreenState3.mSavedEffect = 0;
        savedScreenState3.mSavedCursorCol = 0;
        savedScreenState3.mSavedCursorRow = 0;
        SavedScreenState savedScreenState4 = this.mSavedStateAlt;
        savedScreenState4.mSavedDecFlags = 0;
        savedScreenState4.mSavedEffect = 0;
        savedScreenState4.mSavedCursorCol = 0;
        savedScreenState4.mSavedCursorRow = 0;
        this.mCurrentDecSetFlags = 0;
        setDecsetinternalBit(8, true);
        setDecsetinternalBit(16, true);
        SavedScreenState savedScreenState5 = this.mSavedStateMain;
        SavedScreenState savedScreenState6 = this.mSavedStateAlt;
        int i = this.mCurrentDecSetFlags;
        savedScreenState6.mSavedDecFlags = i;
        savedScreenState5.mSavedDecFlags = i;
        this.mSavedDecSetFlags = i;
        this.mUtf8ToFollow = (byte) 0;
        this.mUtf8Index = (byte) 0;
        this.mColors.reset();
        this.mSession.onColorsChanged();
    }

    public String getSelectedText(int i, int i2, int i3, int i4) {
        return this.mScreen.getSelectedText(i, i2, i3, i4);
    }

    public String getTitle() {
        return this.mTitle;
    }

    private void setTitle(String str) {
        String str2 = this.mTitle;
        this.mTitle = str;
        if (Objects.equals(str2, str)) {
            return;
        }
        this.mSession.titleChanged(str2, str);
    }

    public void paste(String str) {
        String replaceAll = str.replaceAll("(\u001b|[\u0080-\u009f])", "").replaceAll("\r?\n", "\r");
        boolean isDecsetInternalBitSet = isDecsetInternalBitSet(1024);
        if (isDecsetInternalBitSet) {
            this.mSession.write("\u001b[200~");
        }
        this.mSession.write(replaceAll);
        if (isDecsetInternalBitSet) {
            this.mSession.write("\u001b[201~");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class SavedScreenState {
        int mSavedBackColor;
        int mSavedCursorCol;
        int mSavedCursorRow;
        int mSavedDecFlags;
        int mSavedEffect;
        int mSavedForeColor;
        boolean mUseLineDrawingG0;
        boolean mUseLineDrawingG1;
        boolean mUseLineDrawingUsesG0 = true;

        SavedScreenState() {
        }
    }

    public String toString() {
        return "TerminalEmulator[size=" + this.mScreen.mColumns + "x" + this.mScreen.mScreenRows + ", margins={" + this.mTopMargin + "," + this.mRightMargin + "," + this.mBottomMargin + "," + this.mLeftMargin + "}]";
    }
}
