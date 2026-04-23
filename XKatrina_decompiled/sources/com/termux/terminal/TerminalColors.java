package com.termux.terminal;
/* loaded from: classes6.dex */
public final class TerminalColors {
    public static final TerminalColorScheme COLOR_SCHEME = new TerminalColorScheme();
    public final int[] mCurrentColors = new int[TextStyle.NUM_INDEXED_COLORS];

    public TerminalColors() {
        reset();
    }

    public void reset(int i) {
        this.mCurrentColors[i] = COLOR_SCHEME.mDefaultColors[i];
    }

    public void reset() {
        System.arraycopy(COLOR_SCHEME.mDefaultColors, 0, this.mCurrentColors, 0, TextStyle.NUM_INDEXED_COLORS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parse(String str) {
        int i;
        int i2;
        try {
            i = 1;
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
        }
        if (str.charAt(0) != '#') {
            if (str.startsWith("rgb:")) {
                i2 = 1;
                i = 4;
            }
            return 0;
        }
        i2 = 0;
        int length = (str.length() - i) - (i2 * 2);
        if (length % 3 != 0) {
            return 0;
        }
        int i3 = length / 3;
        double pow = 255.0d / (Math.pow(2.0d, i3 * 4) - 1.0d);
        String substring = str.substring(i, i + i3);
        int i4 = i2 + i3;
        int i5 = i + i4;
        String substring2 = str.substring(i5, i5 + i3);
        int i6 = i5 + i4;
        return ((int) (Integer.parseInt(str.substring(i6, i3 + i6), 16) * pow)) | (-16777216) | (((int) (Integer.parseInt(substring, 16) * pow)) << 16) | (((int) (Integer.parseInt(substring2, 16) * pow)) << 8);
    }

    public void tryParseColor(int i, String str) {
        int parse = parse(str);
        if (parse != 0) {
            this.mCurrentColors[i] = parse;
        }
    }
}
