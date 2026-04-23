package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
/* loaded from: classes2.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 241;
    private static final char ESCAPE_FNC_2 = 242;
    private static final char ESCAPE_FNC_3 = 243;
    private static final char ESCAPE_FNC_4 = 244;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    protected Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.CODE_128);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String contents) {
        int patternIndex;
        int length = contents.length();
        if (length <= 0 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got ".concat(String.valueOf(length)));
        }
        for (int i = 0; i < length; i++) {
            char c = contents.charAt(i);
            switch (c) {
                case 241:
                case 242:
                case 243:
                case 244:
                    break;
                default:
                    if (c <= 127) {
                        break;
                    } else {
                        throw new IllegalArgumentException("Bad character in input: ".concat(String.valueOf(c)));
                    }
            }
        }
        Collection<int[]> patterns = new ArrayList<>();
        int checkSum = 0;
        int checkWeight = 1;
        int codeSet = 0;
        int position = 0;
        while (position < length) {
            int newCodeSet = chooseCode(contents, position, codeSet);
            if (newCodeSet == codeSet) {
                switch (contents.charAt(position)) {
                    case 241:
                        patternIndex = 102;
                        break;
                    case 242:
                        patternIndex = 97;
                        break;
                    case 243:
                        patternIndex = 96;
                        break;
                    case 244:
                        if (codeSet == 101) {
                            patternIndex = 101;
                            break;
                        } else {
                            patternIndex = 100;
                            break;
                        }
                    default:
                        switch (codeSet) {
                            case 100:
                                patternIndex = contents.charAt(position) - ' ';
                                break;
                            case 101:
                                patternIndex = contents.charAt(position) - ' ';
                                if (patternIndex < 0) {
                                    patternIndex += 96;
                                    break;
                                }
                                break;
                            default:
                                patternIndex = Integer.parseInt(contents.substring(position, position + 2));
                                position++;
                                break;
                        }
                }
                position++;
            } else {
                if (codeSet == 0) {
                    switch (newCodeSet) {
                        case 100:
                            patternIndex = 104;
                            break;
                        case 101:
                            patternIndex = 103;
                            break;
                        default:
                            patternIndex = 105;
                            break;
                    }
                } else {
                    patternIndex = newCodeSet;
                }
                codeSet = newCodeSet;
            }
            patterns.add(Code128Reader.CODE_PATTERNS[patternIndex]);
            checkSum += patternIndex * checkWeight;
            if (position != 0) {
                checkWeight++;
            }
        }
        patterns.add(Code128Reader.CODE_PATTERNS[checkSum % 103]);
        patterns.add(Code128Reader.CODE_PATTERNS[106]);
        int codeWidth = 0;
        for (int[] iArr : patterns) {
            for (int width : iArr) {
                codeWidth += width;
            }
        }
        boolean[] result = new boolean[codeWidth];
        int pos = 0;
        for (int[] pattern : patterns) {
            pos += appendPattern(result, pos, pattern, true);
        }
        return result;
    }

    private static CType findCType(CharSequence value, int start) {
        int last = value.length();
        if (start >= last) {
            return CType.UNCODABLE;
        }
        char c = value.charAt(start);
        if (c == 241) {
            return CType.FNC_1;
        }
        if (c < '0' || c > '9') {
            return CType.UNCODABLE;
        }
        if (start + 1 >= last) {
            return CType.ONE_DIGIT;
        }
        char c2 = value.charAt(start + 1);
        if (c2 < '0' || c2 > '9') {
            return CType.ONE_DIGIT;
        }
        return CType.TWO_DIGITS;
    }

    private static int chooseCode(CharSequence value, int start, int oldCode) {
        CType lookahead;
        CType lookahead2 = findCType(value, start);
        if (lookahead2 == CType.ONE_DIGIT) {
            return oldCode == 101 ? 101 : 100;
        } else if (lookahead2 == CType.UNCODABLE) {
            if (start < value.length()) {
                char c = value.charAt(start);
                if (c < ' ') {
                    return 101;
                }
                if (oldCode == 101) {
                    if (c < '`') {
                        return 101;
                    }
                    if (c >= 241 && c <= 244) {
                        return 101;
                    }
                }
            }
            return 100;
        } else if (oldCode == 101 && lookahead2 == CType.FNC_1) {
            return 101;
        } else {
            if (oldCode == 99) {
                return 99;
            }
            if (oldCode == 100) {
                if (lookahead2 == CType.FNC_1) {
                    return 100;
                }
                CType lookahead3 = findCType(value, start + 2);
                if (lookahead3 == CType.UNCODABLE || lookahead3 == CType.ONE_DIGIT) {
                    return 100;
                }
                if (lookahead3 == CType.FNC_1) {
                    return findCType(value, start + 3) == CType.TWO_DIGITS ? 99 : 100;
                }
                int index = start + 4;
                while (true) {
                    lookahead = findCType(value, index);
                    if (lookahead != CType.TWO_DIGITS) {
                        break;
                    }
                    index += 2;
                }
                return lookahead == CType.ONE_DIGIT ? 100 : 99;
            }
            if (lookahead2 == CType.FNC_1) {
                lookahead2 = findCType(value, start + 1);
            }
            return lookahead2 == CType.TWO_DIGITS ? 99 : 100;
        }
    }
}
