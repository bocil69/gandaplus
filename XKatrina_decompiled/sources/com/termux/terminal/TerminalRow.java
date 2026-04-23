package com.termux.terminal;

import java.util.Arrays;
/* loaded from: classes6.dex */
public final class TerminalRow {
    private static final float SPARE_CAPACITY_FACTOR = 1.5f;
    private final int mColumns;
    boolean mHasNonOneWidthOrSurrogateChars;
    boolean mLineWrap;
    private short mSpaceUsed;
    final long[] mStyle;
    public char[] mText;

    public TerminalRow(int i, long j) {
        this.mColumns = i;
        this.mText = new char[(int) (i * SPARE_CAPACITY_FACTOR)];
        this.mStyle = new long[i];
        clear(j);
    }

    public void copyInterval(TerminalRow terminalRow, int i, int i2, int i3) {
        this.mHasNonOneWidthOrSurrogateChars |= terminalRow.mHasNonOneWidthOrSurrogateChars;
        int findStartOfColumn = terminalRow.findStartOfColumn(i);
        int findStartOfColumn2 = terminalRow.findStartOfColumn(i2);
        boolean z = i > 0 && terminalRow.wideDisplayCharacterStartingAt(i + (-1));
        char[] cArr = terminalRow.mText;
        if (this == terminalRow) {
            cArr = Arrays.copyOf(cArr, cArr.length);
        }
        int i4 = 0;
        while (findStartOfColumn < findStartOfColumn2) {
            char c = cArr[findStartOfColumn];
            boolean isHighSurrogate = Character.isHighSurrogate(c);
            char c2 = c;
            if (isHighSurrogate) {
                findStartOfColumn++;
                c2 = Character.toCodePoint(c, cArr[findStartOfColumn]);
            }
            if (z) {
                c2 = 32;
                z = false;
            }
            int width = WcWidth.width(c2);
            if (width > 0) {
                i3 += i4;
                i += i4;
                i4 = width;
            }
            setChar(i3, c2, terminalRow.getStyle(i));
            findStartOfColumn++;
        }
    }

    public int getSpaceUsed() {
        return this.mSpaceUsed;
    }

    public int findStartOfColumn(int i) {
        if (i == this.mColumns) {
            return getSpaceUsed();
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i2 + 1;
            char c = this.mText[i2];
            boolean isHighSurrogate = Character.isHighSurrogate(c);
            int i5 = c;
            if (isHighSurrogate) {
                int codePoint = Character.toCodePoint(c, this.mText[i4]);
                i4++;
                i5 = codePoint;
            }
            int width = WcWidth.width(i5);
            if (width > 0) {
                i3 += width;
                if (i3 == i) {
                    while (i4 < this.mSpaceUsed) {
                        if (Character.isHighSurrogate(this.mText[i4])) {
                            char[] cArr = this.mText;
                            if (WcWidth.width(Character.toCodePoint(cArr[i4], cArr[i4 + 1])) > 0) {
                                break;
                            }
                            i4 += 2;
                        } else if (WcWidth.width(this.mText[i4]) > 0) {
                            break;
                        } else {
                            i4++;
                        }
                    }
                    return i4;
                } else if (i3 > i) {
                    return i2;
                }
            }
            i2 = i4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    /* JADX WARN: Type inference failed for: r4v1 */
    private boolean wideDisplayCharacterStartingAt(int i) {
        short s = 0;
        int i2 = 0;
        while (s < this.mSpaceUsed) {
            int i3 = s + 1;
            char c = this.mText[s];
            boolean isHighSurrogate = Character.isHighSurrogate(c);
            char c2 = c;
            if (isHighSurrogate) {
                i3++;
                c2 = Character.toCodePoint(c, this.mText[i3]);
            }
            int width = WcWidth.width(c2);
            if (width > 0) {
                if (i2 == i && width == 2) {
                    return true;
                }
                i2 += width;
                if (i2 > i) {
                    return false;
                }
            }
            s = i3;
        }
        return false;
    }

    public void clear(long j) {
        Arrays.fill(this.mText, ' ');
        Arrays.fill(this.mStyle, j);
        this.mSpaceUsed = (short) this.mColumns;
        this.mHasNonOneWidthOrSurrogateChars = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setChar(int r17, int r18, long r19) {
        /*
            Method dump skipped, instructions count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termux.terminal.TerminalRow.setChar(int, int, long):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBlank() {
        int spaceUsed = getSpaceUsed();
        for (int i = 0; i < spaceUsed; i++) {
            if (this.mText[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    public final long getStyle(int i) {
        return this.mStyle[i];
    }
}
