package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import java.util.Collection;
import java.util.Collections;
/* loaded from: classes2.dex */
public class Code93Writer extends OneDimensionalCodeWriter {
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    protected Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.CODE_93);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String contents) {
        String contents2 = convertToExtended(contents);
        int length = contents2.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long after converting to extended encoding, but got ".concat(String.valueOf(length)));
        }
        boolean[] result = new boolean[((contents2.length() + 2 + 2) * 9) + 1];
        int pos = appendPattern(result, 0, Code93Reader.ASTERISK_ENCODING);
        for (int i = 0; i < length; i++) {
            int indexInString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(contents2.charAt(i));
            pos += appendPattern(result, pos, Code93Reader.CHARACTER_ENCODINGS[indexInString]);
        }
        int check1 = computeChecksumIndex(contents2, 20);
        int pos2 = pos + appendPattern(result, pos, Code93Reader.CHARACTER_ENCODINGS[check1]);
        int check2 = computeChecksumIndex(contents2 + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(check1), 15);
        int pos3 = pos2 + appendPattern(result, pos2, Code93Reader.CHARACTER_ENCODINGS[check2]);
        result[pos3 + appendPattern(result, pos3, Code93Reader.ASTERISK_ENCODING)] = true;
        return result;
    }

    @Deprecated
    protected static int appendPattern(boolean[] target, int pos, int[] pattern, boolean startColor) {
        int length = pattern.length;
        int i = 0;
        int pos2 = pos;
        while (i < length) {
            int bit = pattern[i];
            int pos3 = pos2 + 1;
            target[pos2] = bit != 0;
            i++;
            pos2 = pos3;
        }
        return 9;
    }

    private static int appendPattern(boolean[] target, int pos, int a) {
        for (int i = 0; i < 9; i++) {
            int temp = a & (1 << (8 - i));
            target[pos + i] = temp != 0;
        }
        return 9;
    }

    private static int computeChecksumIndex(String contents, int maxWeight) {
        int weight = 1;
        int total = 0;
        for (int i = contents.length() - 1; i >= 0; i--) {
            int indexInString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(contents.charAt(i));
            total += indexInString * weight;
            weight++;
            if (weight > maxWeight) {
                weight = 1;
            }
        }
        return total % 47;
    }

    static String convertToExtended(String contents) {
        int length = contents.length();
        StringBuilder extendedContent = new StringBuilder(length << 1);
        for (int i = 0; i < length; i++) {
            char character = contents.charAt(i);
            if (character == 0) {
                extendedContent.append("bU");
            } else if (character <= 26) {
                extendedContent.append('a');
                extendedContent.append((char) ((character + 'A') - 1));
            } else if (character <= 31) {
                extendedContent.append('b');
                extendedContent.append((char) ((character + 'A') - 27));
            } else if (character == ' ' || character == '$' || character == '%' || character == '+') {
                extendedContent.append(character);
            } else if (character <= ',') {
                extendedContent.append('c');
                extendedContent.append((char) ((character + 'A') - 33));
            } else if (character <= '9') {
                extendedContent.append(character);
            } else if (character == ':') {
                extendedContent.append("cZ");
            } else if (character <= '?') {
                extendedContent.append('b');
                extendedContent.append((char) ((character + 'F') - 59));
            } else if (character == '@') {
                extendedContent.append("bV");
            } else if (character <= 'Z') {
                extendedContent.append(character);
            } else if (character <= '_') {
                extendedContent.append('b');
                extendedContent.append((char) ((character + 'K') - 91));
            } else if (character == '`') {
                extendedContent.append("bW");
            } else if (character <= 'z') {
                extendedContent.append('d');
                extendedContent.append((char) ((character + 'A') - 97));
            } else if (character <= 127) {
                extendedContent.append('b');
                extendedContent.append((char) ((character + 'P') - 123));
            } else {
                throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + character + "'");
            }
        }
        return extendedContent.toString();
    }
}
