package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class UPCEANExtension5Support {
    private static final int[] CHECK_DIGIT_ENCODINGS = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Result decodeRow(int rowNumber, BitArray row, int[] extensionStartRange) throws NotFoundException {
        StringBuilder result = this.decodeRowStringBuffer;
        result.setLength(0);
        int end = decodeMiddle(row, extensionStartRange, result);
        String resultString = result.toString();
        Map<ResultMetadataType, Object> extensionData = parseExtensionString(resultString);
        Result extensionResult = new Result(resultString, null, new ResultPoint[]{new ResultPoint((extensionStartRange[0] + extensionStartRange[1]) / 2.0f, rowNumber), new ResultPoint(end, rowNumber)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (extensionData != null) {
            extensionResult.putAllMetadata(extensionData);
        }
        return extensionResult;
    }

    private int decodeMiddle(BitArray row, int[] startRange, StringBuilder resultString) throws NotFoundException {
        int[] counters = this.decodeMiddleCounters;
        counters[0] = 0;
        counters[1] = 0;
        counters[2] = 0;
        counters[3] = 0;
        int end = row.getSize();
        int rowOffset = startRange[1];
        int lgPatternFound = 0;
        for (int x = 0; x < 5 && rowOffset < end; x++) {
            int bestMatch = UPCEANReader.decodeDigit(row, counters, rowOffset, UPCEANReader.L_AND_G_PATTERNS);
            resultString.append((char) ((bestMatch % 10) + 48));
            for (int counter : counters) {
                rowOffset += counter;
            }
            if (bestMatch >= 10) {
                lgPatternFound |= 1 << (4 - x);
            }
            if (x != 4) {
                rowOffset = row.getNextUnset(row.getNextSet(rowOffset));
            }
        }
        if (resultString.length() != 5) {
            throw NotFoundException.getNotFoundInstance();
        }
        int checkDigit = determineCheckDigit(lgPatternFound);
        if (extensionChecksum(resultString.toString()) != checkDigit) {
            throw NotFoundException.getNotFoundInstance();
        }
        return rowOffset;
    }

    private static int extensionChecksum(CharSequence s) {
        int length = s.length();
        int sum = 0;
        for (int i = length - 2; i >= 0; i -= 2) {
            sum += s.charAt(i) - '0';
        }
        int sum2 = sum * 3;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            sum2 += s.charAt(i2) - '0';
        }
        return (sum2 * 3) % 10;
    }

    private static int determineCheckDigit(int lgPatternFound) throws NotFoundException {
        for (int d = 0; d < 10; d++) {
            if (lgPatternFound == CHECK_DIGIT_ENCODINGS[d]) {
                return d;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String raw) {
        Object value;
        if (raw.length() == 5 && (value = parseExtension5String(raw)) != null) {
            Map<ResultMetadataType, Object> result = new EnumMap<>(ResultMetadataType.class);
            result.put(ResultMetadataType.SUGGESTED_PRICE, value);
            return result;
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0060, code lost:
        if (r9.equals("90000") != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String parseExtension5String(java.lang.String r9) {
        /*
            r7 = 1
            r5 = 0
            char r6 = r9.charAt(r5)
            switch(r6) {
                case 48: goto L45;
                case 53: goto L48;
                case 57: goto L4b;
                default: goto L9;
            }
        L9:
            java.lang.String r0 = ""
        Lb:
            java.lang.String r5 = r9.substring(r7)
            int r3 = java.lang.Integer.parseInt(r5)
            int r5 = r3 / 100
            java.lang.String r4 = java.lang.String.valueOf(r5)
            int r1 = r3 % 100
            r5 = 10
            if (r1 >= r5) goto L7f
            java.lang.String r5 = "0"
            java.lang.String r6 = java.lang.String.valueOf(r1)
            java.lang.String r2 = r5.concat(r6)
        L29:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.StringBuilder r5 = r5.append(r4)
            r6 = 46
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r2)
            java.lang.String r5 = r5.toString()
        L44:
            return r5
        L45:
            java.lang.String r0 = "£"
            goto Lb
        L48:
            java.lang.String r0 = "$"
            goto Lb
        L4b:
            r6 = -1
            int r8 = r9.hashCode()
            switch(r8) {
                case 54118329: goto L5a;
                case 54395376: goto L6d;
                case 54395377: goto L63;
                default: goto L53;
            }
        L53:
            r5 = r6
        L54:
            switch(r5) {
                case 0: goto L77;
                case 1: goto L79;
                case 2: goto L7c;
                default: goto L57;
            }
        L57:
            java.lang.String r0 = ""
            goto Lb
        L5a:
            java.lang.String r8 = "90000"
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L53
            goto L54
        L63:
            java.lang.String r5 = "99991"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L53
            r5 = r7
            goto L54
        L6d:
            java.lang.String r5 = "99990"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L53
            r5 = 2
            goto L54
        L77:
            r5 = 0
            goto L44
        L79:
            java.lang.String r5 = "0.00"
            goto L44
        L7c:
            java.lang.String r5 = "Used"
            goto L44
        L7f:
            java.lang.String r2 = java.lang.String.valueOf(r1)
            goto L29
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANExtension5Support.parseExtension5String(java.lang.String):java.lang.String");
    }
}
