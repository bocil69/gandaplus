package com.google.zxing.pdf417.decoder;

import java.util.Formatter;
/* loaded from: classes2.dex */
class DetectionResultColumn {
    private static final int MAX_NEARBY_DISTANCE = 5;
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultColumn(BoundingBox boundingBox) {
        this.boundingBox = new BoundingBox(boundingBox);
        this.codewords = new Codeword[(boundingBox.getMaxY() - boundingBox.getMinY()) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Codeword getCodewordNearby(int imageRow) {
        Codeword codeword = getCodeword(imageRow);
        if (codeword != null) {
            return codeword;
        }
        for (int i = 1; i < 5; i++) {
            int nearImageRow = imageRowToCodewordIndex(imageRow) - i;
            if (nearImageRow >= 0 && (codeword = this.codewords[nearImageRow]) != null) {
                return codeword;
            }
            int nearImageRow2 = imageRowToCodewordIndex(imageRow) + i;
            if (nearImageRow2 < this.codewords.length && (codeword = this.codewords[nearImageRow2]) != null) {
                return codeword;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int imageRowToCodewordIndex(int imageRow) {
        return imageRow - this.boundingBox.getMinY();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setCodeword(int imageRow, Codeword codeword) {
        this.codewords[imageRowToCodewordIndex(imageRow)] = codeword;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Codeword getCodeword(int imageRow) {
        return this.codewords[imageRowToCodewordIndex(imageRow)];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Codeword[] getCodewords() {
        return this.codewords;
    }

    public String toString() {
        int row;
        int row2;
        int i = 0;
        Formatter formatter = new Formatter();
        try {
            Codeword[] codewordArr = this.codewords;
            int length = codewordArr.length;
            int row3 = 0;
            while (i < length) {
                try {
                    Codeword codeword = codewordArr[i];
                    if (codeword == null) {
                        Object[] objArr = new Object[1];
                        row2 = row3 + 1;
                        objArr[0] = Integer.valueOf(row3);
                        formatter.format("%3d:    |   %n", objArr);
                    } else {
                        Object[] objArr2 = new Object[3];
                        row2 = row3 + 1;
                        objArr2[0] = Integer.valueOf(row3);
                        objArr2[1] = Integer.valueOf(codeword.getRowNumber());
                        objArr2[2] = Integer.valueOf(codeword.getValue());
                        formatter.format("%3d: %3d|%3d%n", objArr2);
                    }
                    i++;
                } catch (Throwable th) {
                    row = th;
                    try {
                        throw row;
                    } finally {
                        row = row;
                        if (row != null) {
                            try {
                                formatter.close();
                            } catch (Throwable th2) {
                                row.addSuppressed(th2);
                            }
                        } else {
                            formatter.close();
                        }
                    }
                }
            }
            String formatter2 = formatter.toString();
            formatter.close();
            return formatter2;
        } catch (Throwable th3) {
            row = th3;
        }
    }
}
