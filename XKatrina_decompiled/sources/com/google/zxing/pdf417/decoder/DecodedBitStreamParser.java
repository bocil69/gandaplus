package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
/* loaded from: classes2.dex */
final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger nineHundred = BigInteger.valueOf(900L);
        EXP900[1] = nineHundred;
        for (int i = 2; i < EXP900.length; i++) {
            BigInteger[] bigIntegerArr2 = EXP900;
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(nineHundred);
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DecoderResult decode(int[] codewords, String ecLevel) throws FormatException {
        int codeIndex;
        StringBuilder result = new StringBuilder(codewords.length << 1);
        Charset encoding = StandardCharsets.ISO_8859_1;
        int code = codewords[1];
        PDF417ResultMetadata resultMetadata = new PDF417ResultMetadata();
        for (int codeIndex2 = 1 + 1; codeIndex2 < codewords[0]; codeIndex2 = codeIndex + 1) {
            switch (code) {
                case 900:
                    codeIndex = textCompaction(codewords, codeIndex2, result);
                    break;
                case 901:
                case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                    codeIndex = byteCompaction(code, codewords, encoding, codeIndex2, result);
                    break;
                case 902:
                    codeIndex = numericCompaction(codewords, codeIndex2, result);
                    break;
                case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                    result.append((char) codewords[codeIndex2]);
                    codeIndex = codeIndex2 + 1;
                    break;
                case MACRO_PDF417_TERMINATOR /* 922 */:
                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                    throw FormatException.getFormatInstance();
                case ECI_USER_DEFINED /* 925 */:
                    codeIndex = codeIndex2 + 1;
                    break;
                case ECI_GENERAL_PURPOSE /* 926 */:
                    codeIndex = codeIndex2 + 2;
                    break;
                case ECI_CHARSET /* 927 */:
                    encoding = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(codewords[codeIndex2]).name());
                    codeIndex = codeIndex2 + 1;
                    break;
                case 928:
                    codeIndex = decodeMacroBlock(codewords, codeIndex2, resultMetadata);
                    break;
                default:
                    codeIndex = textCompaction(codewords, codeIndex2 - 1, result);
                    break;
            }
            if (codeIndex < codewords.length) {
                code = codewords[codeIndex];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (result.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult(null, result.toString(), null, ecLevel);
        decoderResult.setOther(resultMetadata);
        return decoderResult;
    }

    static int decodeMacroBlock(int[] codewords, int codeIndex, PDF417ResultMetadata resultMetadata) throws FormatException {
        if (codeIndex + 2 > codewords[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] segmentIndexArray = new int[2];
        int i = 0;
        while (i < 2) {
            segmentIndexArray[i] = codewords[codeIndex];
            i++;
            codeIndex++;
        }
        resultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(segmentIndexArray, 2)));
        StringBuilder fileId = new StringBuilder();
        int codeIndex2 = textCompaction(codewords, codeIndex, fileId);
        resultMetadata.setFileId(fileId.toString());
        int optionalFieldsStart = -1;
        if (codewords[codeIndex2] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            optionalFieldsStart = codeIndex2 + 1;
        }
        while (codeIndex2 < codewords[0]) {
            switch (codewords[codeIndex2]) {
                case MACRO_PDF417_TERMINATOR /* 922 */:
                    codeIndex2++;
                    resultMetadata.setLastSegment(true);
                    break;
                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                    int codeIndex3 = codeIndex2 + 1;
                    switch (codewords[codeIndex3]) {
                        case 0:
                            StringBuilder fileName = new StringBuilder();
                            codeIndex2 = textCompaction(codewords, codeIndex3 + 1, fileName);
                            resultMetadata.setFileName(fileName.toString());
                            continue;
                        case 1:
                            StringBuilder segmentCount = new StringBuilder();
                            codeIndex2 = numericCompaction(codewords, codeIndex3 + 1, segmentCount);
                            resultMetadata.setSegmentCount(Integer.parseInt(segmentCount.toString()));
                            continue;
                        case 2:
                            StringBuilder timestamp = new StringBuilder();
                            codeIndex2 = numericCompaction(codewords, codeIndex3 + 1, timestamp);
                            resultMetadata.setTimestamp(Long.parseLong(timestamp.toString()));
                            continue;
                        case 3:
                            StringBuilder sender = new StringBuilder();
                            codeIndex2 = textCompaction(codewords, codeIndex3 + 1, sender);
                            resultMetadata.setSender(sender.toString());
                            continue;
                        case 4:
                            StringBuilder addressee = new StringBuilder();
                            codeIndex2 = textCompaction(codewords, codeIndex3 + 1, addressee);
                            resultMetadata.setAddressee(addressee.toString());
                            continue;
                        case 5:
                            StringBuilder fileSize = new StringBuilder();
                            codeIndex2 = numericCompaction(codewords, codeIndex3 + 1, fileSize);
                            resultMetadata.setFileSize(Long.parseLong(fileSize.toString()));
                            continue;
                        case 6:
                            StringBuilder checksum = new StringBuilder();
                            codeIndex2 = numericCompaction(codewords, codeIndex3 + 1, checksum);
                            resultMetadata.setChecksum(Integer.parseInt(checksum.toString()));
                            continue;
                        default:
                            throw FormatException.getFormatInstance();
                    }
                default:
                    throw FormatException.getFormatInstance();
            }
        }
        if (optionalFieldsStart != -1) {
            int optionalFieldsLength = codeIndex2 - optionalFieldsStart;
            if (resultMetadata.isLastSegment()) {
                optionalFieldsLength--;
            }
            resultMetadata.setOptionalData(Arrays.copyOfRange(codewords, optionalFieldsStart, optionalFieldsStart + optionalFieldsLength));
        }
        return codeIndex2;
    }

    private static int textCompaction(int[] codewords, int codeIndex, StringBuilder result) {
        int[] textCompactionData = new int[(codewords[0] - codeIndex) << 1];
        int[] byteCompactionData = new int[(codewords[0] - codeIndex) << 1];
        int index = 0;
        boolean end = false;
        while (codeIndex < codewords[0] && !end) {
            int codeIndex2 = codeIndex + 1;
            int code = codewords[codeIndex];
            if (code < 900) {
                textCompactionData[index] = code / 30;
                textCompactionData[index + 1] = code % 30;
                index += 2;
                codeIndex = codeIndex2;
            } else {
                switch (code) {
                    case 900:
                        textCompactionData[index] = 900;
                        index++;
                        codeIndex = codeIndex2;
                        continue;
                    case 901:
                    case 902:
                    case MACRO_PDF417_TERMINATOR /* 922 */:
                    case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                    case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                    case 928:
                        codeIndex = codeIndex2 - 1;
                        end = true;
                        continue;
                    case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                        textCompactionData[index] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                        codeIndex = codeIndex2 + 1;
                        byteCompactionData[index] = codewords[codeIndex2];
                        index++;
                        continue;
                    default:
                        codeIndex = codeIndex2;
                        continue;
                }
            }
        }
        decodeTextCompaction(textCompactionData, byteCompactionData, index, result);
        return codeIndex;
    }

    private static void decodeTextCompaction(int[] textCompactionData, int[] byteCompactionData, int length, StringBuilder result) {
        Mode subMode = Mode.ALPHA;
        Mode priorToShiftMode = Mode.ALPHA;
        for (int i = 0; i < length; i++) {
            int subModeCh = textCompactionData[i];
            char ch = 0;
            switch (subMode) {
                case ALPHA:
                    if (subModeCh < 26) {
                        ch = (char) (subModeCh + 65);
                        break;
                    } else {
                        switch (subModeCh) {
                            case 26:
                                ch = ' ';
                                break;
                            case 27:
                                subMode = Mode.LOWER;
                                break;
                            case 28:
                                subMode = Mode.MIXED;
                                break;
                            case 29:
                                priorToShiftMode = subMode;
                                subMode = Mode.PUNCT_SHIFT;
                                break;
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
                case LOWER:
                    if (subModeCh < 26) {
                        ch = (char) (subModeCh + 97);
                        break;
                    } else {
                        switch (subModeCh) {
                            case 26:
                                ch = ' ';
                                break;
                            case 27:
                                priorToShiftMode = subMode;
                                subMode = Mode.ALPHA_SHIFT;
                                break;
                            case 28:
                                subMode = Mode.MIXED;
                                break;
                            case 29:
                                priorToShiftMode = subMode;
                                subMode = Mode.PUNCT_SHIFT;
                                break;
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
                case MIXED:
                    if (subModeCh < 25) {
                        ch = MIXED_CHARS[subModeCh];
                        break;
                    } else {
                        switch (subModeCh) {
                            case 25:
                                subMode = Mode.PUNCT;
                                break;
                            case 26:
                                ch = ' ';
                                break;
                            case 27:
                                subMode = Mode.LOWER;
                                break;
                            case 28:
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case 29:
                                priorToShiftMode = subMode;
                                subMode = Mode.PUNCT_SHIFT;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
                case PUNCT:
                    if (subModeCh < 29) {
                        ch = PUNCT_CHARS[subModeCh];
                        break;
                    } else {
                        switch (subModeCh) {
                            case 29:
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
                case ALPHA_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh < 26) {
                        ch = (char) (subModeCh + 65);
                        break;
                    } else {
                        switch (subModeCh) {
                            case 26:
                                ch = ' ';
                                break;
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                        }
                    }
                case PUNCT_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh < 29) {
                        ch = PUNCT_CHARS[subModeCh];
                        break;
                    } else {
                        switch (subModeCh) {
                            case 29:
                            case 900:
                                subMode = Mode.ALPHA;
                                break;
                            case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /* 913 */:
                                result.append((char) byteCompactionData[i]);
                                break;
                        }
                    }
            }
            if (ch != 0) {
                result.append(ch);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int byteCompaction(int mode, int[] codewords, Charset encoding, int codeIndex, StringBuilder result) {
        ByteArrayOutputStream decodedBytes = new ByteArrayOutputStream();
        int count = 0;
        long value = 0;
        boolean end = false;
        switch (mode) {
            case 901:
                int[] byteCompactedCodewords = new int[6];
                int nextCode = codewords[codeIndex];
                codeIndex++;
                while (codeIndex < codewords[0] && !end) {
                    int count2 = count + 1;
                    byteCompactedCodewords[count] = nextCode;
                    value = (900 * value) + nextCode;
                    int codeIndex2 = codeIndex + 1;
                    nextCode = codewords[codeIndex];
                    switch (nextCode) {
                        case 900:
                        case 901:
                        case 902:
                        case MACRO_PDF417_TERMINATOR /* 922 */:
                        case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                        case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                        case 928:
                            codeIndex = codeIndex2 - 1;
                            end = true;
                            count = count2;
                            break;
                        default:
                            if (count2 % 5 != 0 || count2 <= 0) {
                                count = count2;
                                codeIndex = codeIndex2;
                                break;
                            } else {
                                for (int j = 0; j < 6; j++) {
                                    decodedBytes.write((byte) (value >> ((5 - j) * 8)));
                                }
                                value = 0;
                                count = 0;
                                codeIndex = codeIndex2;
                                break;
                            }
                            break;
                    }
                }
                if (codeIndex == codewords[0] && nextCode < 900) {
                    byteCompactedCodewords[count] = nextCode;
                    count++;
                }
                for (int i = 0; i < count; i++) {
                    decodedBytes.write((byte) byteCompactedCodewords[i]);
                }
                break;
            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                while (codeIndex < codewords[0] && !end) {
                    int codeIndex3 = codeIndex + 1;
                    int code = codewords[codeIndex];
                    if (code < 900) {
                        count++;
                        value = (900 * value) + code;
                        codeIndex = codeIndex3;
                    } else {
                        switch (code) {
                            case 900:
                            case 901:
                            case 902:
                            case MACRO_PDF417_TERMINATOR /* 922 */:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                            case 928:
                                codeIndex = codeIndex3 - 1;
                                end = true;
                                break;
                            default:
                                codeIndex = codeIndex3;
                                break;
                        }
                    }
                    if (count % 5 == 0 && count > 0) {
                        for (int j2 = 0; j2 < 6; j2++) {
                            decodedBytes.write((byte) (value >> ((5 - j2) * 8)));
                        }
                        value = 0;
                        count = 0;
                    }
                }
                break;
        }
        result.append(new String(decodedBytes.toByteArray(), encoding));
        return codeIndex;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x002b, code lost:
        r9.append(decodeBase900toBase10(r4, r2));
        r2 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int numericCompaction(int[] r7, int r8, java.lang.StringBuilder r9) throws com.google.zxing.FormatException {
        /*
            r6 = 0
            r2 = 0
            r3 = 0
            r5 = 15
            int[] r4 = new int[r5]
        L7:
            r5 = r7[r6]
            if (r8 >= r5) goto L3d
            if (r3 != 0) goto L3d
            int r1 = r8 + 1
            r0 = r7[r8]
            r5 = r7[r6]
            if (r1 != r5) goto L16
            r3 = 1
        L16:
            r5 = 900(0x384, float:1.261E-42)
            if (r0 >= r5) goto L34
            r4[r2] = r0
            int r2 = r2 + 1
            r8 = r1
        L1f:
            int r5 = r2 % 15
            if (r5 == 0) goto L29
            r5 = 902(0x386, float:1.264E-42)
            if (r0 == r5) goto L29
            if (r3 == 0) goto L7
        L29:
            if (r2 <= 0) goto L7
            java.lang.String r5 = decodeBase900toBase10(r4, r2)
            r9.append(r5)
            r2 = 0
            goto L7
        L34:
            switch(r0) {
                case 900: goto L39;
                case 901: goto L39;
                case 922: goto L39;
                case 923: goto L39;
                case 924: goto L39;
                case 928: goto L39;
                default: goto L37;
            }
        L37:
            r8 = r1
            goto L1f
        L39:
            int r8 = r1 + (-1)
            r3 = 1
            goto L1f
        L3d:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.numericCompaction(int[], int, java.lang.StringBuilder):int");
    }

    private static String decodeBase900toBase10(int[] codewords, int count) throws FormatException {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < count; i++) {
            result = result.add(EXP900[(count - i) - 1].multiply(BigInteger.valueOf(codewords[i])));
        }
        String resultString = result.toString();
        if (resultString.charAt(0) != '1') {
            throw FormatException.getFormatInstance();
        }
        return resultString.substring(1);
    }
}
