package com.google.zxing.aztec;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
/* loaded from: classes2.dex */
public final class AztecReader implements Reader {
    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap image) throws NotFoundException, FormatException {
        return decode(image, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005c  */
    @Override // com.google.zxing.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap r21, java.util.Map<com.google.zxing.DecodeHintType, ?> r22) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        /*
            r20 = this;
            r17 = 0
            r16 = 0
            com.google.zxing.aztec.detector.Detector r12 = new com.google.zxing.aztec.detector.Detector
            com.google.zxing.common.BitMatrix r3 = r21.getBlackMatrix()
            r12.<init>(r3)
            r6 = 0
            r11 = 0
            r3 = 0
            com.google.zxing.aztec.AztecDetectorResult r13 = r12.detect(r3)     // Catch: com.google.zxing.NotFoundException -> L53 com.google.zxing.FormatException -> L55
            com.google.zxing.ResultPoint[] r6 = r13.getPoints()     // Catch: com.google.zxing.NotFoundException -> L53 com.google.zxing.FormatException -> L55
            com.google.zxing.aztec.decoder.Decoder r3 = new com.google.zxing.aztec.decoder.Decoder     // Catch: com.google.zxing.NotFoundException -> L53 com.google.zxing.FormatException -> L55
            r3.<init>()     // Catch: com.google.zxing.NotFoundException -> L53 com.google.zxing.FormatException -> L55
            com.google.zxing.common.DecoderResult r11 = r3.decode(r13)     // Catch: com.google.zxing.NotFoundException -> L53 com.google.zxing.FormatException -> L55
        L21:
            if (r11 != 0) goto L35
            r3 = 1
            com.google.zxing.aztec.AztecDetectorResult r13 = r12.detect(r3)     // Catch: com.google.zxing.NotFoundException -> L57 com.google.zxing.FormatException -> L8e
            com.google.zxing.ResultPoint[] r6 = r13.getPoints()     // Catch: com.google.zxing.NotFoundException -> L57 com.google.zxing.FormatException -> L8e
            com.google.zxing.aztec.decoder.Decoder r3 = new com.google.zxing.aztec.decoder.Decoder     // Catch: com.google.zxing.NotFoundException -> L57 com.google.zxing.FormatException -> L8e
            r3.<init>()     // Catch: com.google.zxing.NotFoundException -> L57 com.google.zxing.FormatException -> L8e
            com.google.zxing.common.DecoderResult r11 = r3.decode(r13)     // Catch: com.google.zxing.NotFoundException -> L57 com.google.zxing.FormatException -> L8e
        L35:
            if (r22 == 0) goto L60
            com.google.zxing.DecodeHintType r3 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            r0 = r22
            java.lang.Object r19 = r0.get(r3)
            com.google.zxing.ResultPointCallback r19 = (com.google.zxing.ResultPointCallback) r19
            if (r19 == 0) goto L60
            int r4 = r6.length
            r3 = 0
        L45:
            if (r3 >= r4) goto L60
            r18 = r6[r3]
            r0 = r19
            r1 = r18
            r0.foundPossibleResultPoint(r1)
            int r3 = r3 + 1
            goto L45
        L53:
            r17 = move-exception
            goto L21
        L55:
            r16 = move-exception
            goto L21
        L57:
            r3 = move-exception
            r14 = r3
        L59:
            if (r17 == 0) goto L5c
            throw r17
        L5c:
            if (r16 == 0) goto L5f
            throw r16
        L5f:
            throw r14
        L60:
            com.google.zxing.Result r2 = new com.google.zxing.Result
            java.lang.String r3 = r11.getText()
            byte[] r4 = r11.getRawBytes()
            int r5 = r11.getNumBits()
            com.google.zxing.BarcodeFormat r7 = com.google.zxing.BarcodeFormat.AZTEC
            long r8 = java.lang.System.currentTimeMillis()
            r2.<init>(r3, r4, r5, r6, r7, r8)
            java.util.List r10 = r11.getByteSegments()
            if (r10 == 0) goto L82
            com.google.zxing.ResultMetadataType r3 = com.google.zxing.ResultMetadataType.BYTE_SEGMENTS
            r2.putMetadata(r3, r10)
        L82:
            java.lang.String r15 = r11.getECLevel()
            if (r15 == 0) goto L8d
            com.google.zxing.ResultMetadataType r3 = com.google.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL
            r2.putMetadata(r3, r15)
        L8d:
            return r2
        L8e:
            r3 = move-exception
            r14 = r3
            goto L59
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.AztecReader.decode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    @Override // com.google.zxing.Reader
    public void reset() {
    }
}
