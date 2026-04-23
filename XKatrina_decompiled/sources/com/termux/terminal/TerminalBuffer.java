package com.termux.terminal;

import java.util.Arrays;
/* loaded from: classes6.dex */
public final class TerminalBuffer {
    int mColumns;
    TerminalRow[] mLines;
    int mScreenRows;
    int mTotalRows;
    private int mActiveTranscriptRows = 0;
    private int mScreenFirstRow = 0;

    public TerminalBuffer(int i, int i2, int i3) {
        this.mColumns = i;
        this.mTotalRows = i2;
        this.mScreenRows = i3;
        this.mLines = new TerminalRow[i2];
        blockSet(0, 0, i, i3, 32, TextStyle.NORMAL);
    }

    public String getTranscriptText() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows).trim();
    }

    public String getTranscriptTextWithoutJoinedLines() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows, false).trim();
    }

    public String getTranscriptTextWithFullLinesJoined() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows, true, true).trim();
    }

    public String getSelectedText(int i, int i2, int i3, int i4) {
        return getSelectedText(i, i2, i3, i4, true);
    }

    public String getSelectedText(int i, int i2, int i3, int i4, boolean z) {
        return getSelectedText(i, i2, i3, i4, true, false);
    }

    public String getSelectedText(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        int i5;
        int i6;
        StringBuilder sb = new StringBuilder();
        int i7 = this.mColumns;
        int i8 = i2 < (-getActiveTranscriptRows()) ? -getActiveTranscriptRows() : i2;
        int i9 = this.mScreenRows;
        int i10 = 1;
        int i11 = i4 >= i9 ? i9 - 1 : i4;
        int i12 = i8;
        while (i12 <= i11) {
            int i13 = i12 == i8 ? i : 0;
            if (i12 != i11 || (i5 = i3 + 1) > i7) {
                i5 = i7;
            }
            TerminalRow terminalRow = this.mLines[externalToInternalRow(i12)];
            int findStartOfColumn = terminalRow.findStartOfColumn(i13);
            int findStartOfColumn2 = i5 < this.mColumns ? terminalRow.findStartOfColumn(i5) : terminalRow.getSpaceUsed();
            if (findStartOfColumn2 == findStartOfColumn) {
                findStartOfColumn2 = terminalRow.findStartOfColumn(i5 + 1);
            }
            char[] cArr = terminalRow.mText;
            boolean lineWrap = getLineWrap(i12);
            if (lineWrap && i5 == i7) {
                i6 = findStartOfColumn2 - 1;
            } else {
                int i14 = findStartOfColumn;
                i6 = -1;
                while (i14 < findStartOfColumn2) {
                    if (cArr[i14] != ' ') {
                        i6 = i14;
                    }
                    i14++;
                    i10 = 1;
                }
            }
            if (i6 != -1) {
                sb.append(cArr, findStartOfColumn, (i6 - findStartOfColumn) + i10);
            }
            boolean z3 = i6 == findStartOfColumn2 + (-1);
            if ((!z || !lineWrap) && ((!z2 || !z3) && i12 < i11 && i12 < this.mScreenRows - i10)) {
                sb.append('\n');
            }
            i12++;
        }
        return sb.toString();
    }

    public int getActiveTranscriptRows() {
        return this.mActiveTranscriptRows;
    }

    public int getActiveRows() {
        return this.mActiveTranscriptRows + this.mScreenRows;
    }

    public int externalToInternalRow(int i) {
        if (i < (-this.mActiveTranscriptRows) || i > this.mScreenRows) {
            throw new IllegalArgumentException("extRow=" + i + ", mScreenRows=" + this.mScreenRows + ", mActiveTranscriptRows=" + this.mActiveTranscriptRows);
        }
        int i2 = this.mScreenFirstRow + i;
        int i3 = this.mTotalRows;
        return i2 < 0 ? i3 + i2 : i2 % i3;
    }

    public void setLineWrap(int i) {
        this.mLines[externalToInternalRow(i)].mLineWrap = true;
    }

    public boolean getLineWrap(int i) {
        return this.mLines[externalToInternalRow(i)].mLineWrap;
    }

    public void clearLineWrap(int i) {
        this.mLines[externalToInternalRow(i)].mLineWrap = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:162:0x0151 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x015d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void resize(int r32, int r33, int r34, int[] r35, long r36, boolean r38) {
        /*
            Method dump skipped, instructions count: 605
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termux.terminal.TerminalBuffer.resize(int, int, int, int[], long, boolean):void");
    }

    private void blockCopyLinesDown(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        int i3 = this.mTotalRows;
        int i4 = i2 - 1;
        TerminalRow terminalRow = this.mLines[((i + i4) + 1) % i3];
        while (i4 >= 0) {
            TerminalRow[] terminalRowArr = this.mLines;
            int i5 = i + i4;
            terminalRowArr[(i5 + 1) % i3] = terminalRowArr[i5 % i3];
            i4--;
        }
        this.mLines[i % i3] = terminalRow;
    }

    public void scrollDownOneLine(int i, int i2, long j) {
        int i3 = i2 - 1;
        if (i > i3 || i < 0 || i2 > this.mScreenRows) {
            throw new IllegalArgumentException("topMargin=" + i + ", bottomMargin=" + i2 + ", mScreenRows=" + this.mScreenRows);
        }
        blockCopyLinesDown(this.mScreenFirstRow, i);
        blockCopyLinesDown(externalToInternalRow(i2), this.mScreenRows - i2);
        int i4 = this.mTotalRows;
        this.mScreenFirstRow = (this.mScreenFirstRow + 1) % i4;
        int i5 = this.mActiveTranscriptRows;
        if (i5 < i4 - this.mScreenRows) {
            this.mActiveTranscriptRows = i5 + 1;
        }
        int externalToInternalRow = externalToInternalRow(i3);
        TerminalRow[] terminalRowArr = this.mLines;
        TerminalRow terminalRow = terminalRowArr[externalToInternalRow];
        if (terminalRow == null) {
            terminalRowArr[externalToInternalRow] = new TerminalRow(this.mColumns, j);
        } else {
            terminalRow.clear(j);
        }
    }

    public void blockCopy(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        if (i3 == 0) {
            return;
        }
        if (i >= 0 && (i7 = i + i3) <= (i8 = this.mColumns) && i2 >= 0) {
            int i9 = i2 + i4;
            int i10 = this.mScreenRows;
            if (i9 <= i10 && i5 >= 0 && i3 + i5 <= i8 && i6 >= 0 && i6 + i4 <= i10) {
                boolean z = i2 > i6;
                for (int i11 = 0; i11 < i4; i11++) {
                    int i12 = z ? i11 : i4 - (i11 + 1);
                    allocateFullLineIfNecessary(externalToInternalRow(i12 + i6)).copyInterval(allocateFullLineIfNecessary(externalToInternalRow(i2 + i12)), i, i7, i5);
                }
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public void blockSet(int i, int i2, int i3, int i4, int i5, long j) {
        if (i >= 0 && i + i3 <= this.mColumns && i2 >= 0 && i2 + i4 <= this.mScreenRows) {
            for (int i6 = 0; i6 < i4; i6++) {
                for (int i7 = 0; i7 < i3; i7++) {
                    setChar(i + i7, i2 + i6, i5, j);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Illegal arguments! blockSet(" + i + ", " + i2 + ", " + i3 + ", " + i4 + ", " + i5 + ", " + this.mColumns + ", " + this.mScreenRows + ")");
    }

    public TerminalRow allocateFullLineIfNecessary(int i) {
        TerminalRow[] terminalRowArr = this.mLines;
        TerminalRow terminalRow = terminalRowArr[i];
        if (terminalRow == null) {
            TerminalRow terminalRow2 = new TerminalRow(this.mColumns, 0L);
            terminalRowArr[i] = terminalRow2;
            return terminalRow2;
        }
        return terminalRow;
    }

    public void setChar(int i, int i2, int i3, long j) {
        if (i2 >= this.mScreenRows || i >= this.mColumns) {
            throw new IllegalArgumentException("row=" + i2 + ", column=" + i + ", mScreenRows=" + this.mScreenRows + ", mColumns=" + this.mColumns);
        }
        allocateFullLineIfNecessary(externalToInternalRow(i2)).setChar(i, i3, j);
    }

    public long getStyleAt(int i, int i2) {
        return allocateFullLineIfNecessary(externalToInternalRow(i)).getStyle(i2);
    }

    public void setOrClearEffect(int i, boolean z, boolean z2, boolean z3, int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = i4;
        while (i8 < i6) {
            TerminalRow terminalRow = this.mLines[externalToInternalRow(i8)];
            int i9 = (z3 || i8 + 1 == i6) ? i7 : i3;
            for (int i10 = (z3 || i8 == i4) ? i5 : i2; i10 < i9; i10++) {
                long style = terminalRow.getStyle(i10);
                int decodeForeColor = TextStyle.decodeForeColor(style);
                int decodeBackColor = TextStyle.decodeBackColor(style);
                int decodeEffect = TextStyle.decodeEffect(style);
                terminalRow.mStyle[i10] = TextStyle.encode(decodeForeColor, decodeBackColor, z2 ? ((~decodeEffect) & i) | ((~i) & decodeEffect) : z ? decodeEffect | i : decodeEffect & (~i));
            }
            i8++;
        }
    }

    public void clearTranscript() {
        int i = this.mScreenFirstRow;
        int i2 = this.mActiveTranscriptRows;
        if (i < i2) {
            TerminalRow[] terminalRowArr = this.mLines;
            int i3 = this.mTotalRows;
            Arrays.fill(terminalRowArr, (i + i3) - i2, i3, (Object) null);
            Arrays.fill(this.mLines, 0, this.mScreenFirstRow, (Object) null);
        } else {
            Arrays.fill(this.mLines, i - i2, i, (Object) null);
        }
        this.mActiveTranscriptRows = 0;
    }
}
