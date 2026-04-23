package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 97;
    protected static final int MIN_SKIP = 3;
    private static final EstimatedModuleComparator moduleComparator = new EstimatedModuleComparator();
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    public FinderPatternFinder(BitMatrix image) {
        this(image, null);
    }

    public FinderPatternFinder(BitMatrix image, ResultPointCallback resultPointCallback) {
        this.image = image;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BitMatrix getImage() {
        return this.image;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final FinderPatternInfo find(Map<DecodeHintType, ?> hints) throws NotFoundException {
        boolean tryHarder = hints != null && hints.containsKey(DecodeHintType.TRY_HARDER);
        int maxI = this.image.getHeight();
        int maxJ = this.image.getWidth();
        int iSkip = (maxI * 3) / 388;
        iSkip = (iSkip < 3 || tryHarder) ? 3 : 3;
        boolean done = false;
        int[] stateCount = new int[5];
        int i = iSkip - 1;
        while (i < maxI && !done) {
            doClearCounts(stateCount);
            int currentState = 0;
            int j = 0;
            while (j < maxJ) {
                if (this.image.get(j, i)) {
                    if ((currentState & 1) == 1) {
                        currentState++;
                    }
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if ((currentState & 1) == 0) {
                    if (currentState == 4) {
                        if (foundPatternCross(stateCount)) {
                            if (handlePossibleCenter(stateCount, i, j)) {
                                iSkip = 2;
                                if (this.hasSkipped) {
                                    done = haveMultiplyConfirmedCenters();
                                } else {
                                    int rowSkip = findRowSkip();
                                    if (rowSkip > stateCount[2]) {
                                        i += (rowSkip - stateCount[2]) - 2;
                                        j = maxJ - 1;
                                    }
                                }
                                currentState = 0;
                                doClearCounts(stateCount);
                            } else {
                                doShiftCounts2(stateCount);
                                currentState = 3;
                            }
                        } else {
                            doShiftCounts2(stateCount);
                            currentState = 3;
                        }
                    } else {
                        currentState++;
                        stateCount[currentState] = stateCount[currentState] + 1;
                    }
                } else {
                    stateCount[currentState] = stateCount[currentState] + 1;
                }
                j++;
            }
            if (foundPatternCross(stateCount) && handlePossibleCenter(stateCount, i, maxJ)) {
                iSkip = stateCount[0];
                if (this.hasSkipped) {
                    done = haveMultiplyConfirmedCenters();
                }
            }
            i += iSkip;
        }
        FinderPattern[] patternInfo = selectBestPatterns();
        ResultPoint.orderBestPatterns(patternInfo);
        return new FinderPatternInfo(patternInfo);
    }

    private static float centerFromEnd(int[] stateCount, int end) {
        return ((end - stateCount[4]) - stateCount[3]) - (stateCount[2] / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean foundPatternCross(int[] stateCount) {
        int totalModuleSize = 0;
        for (int i = 0; i < 5; i++) {
            int count = stateCount[i];
            if (count == 0) {
                return false;
            }
            totalModuleSize += count;
        }
        if (totalModuleSize >= 7) {
            float moduleSize = totalModuleSize / 7.0f;
            float maxVariance = moduleSize / 2.0f;
            return Math.abs(moduleSize - ((float) stateCount[0])) < maxVariance && Math.abs(moduleSize - ((float) stateCount[1])) < maxVariance && Math.abs((3.0f * moduleSize) - ((float) stateCount[2])) < 3.0f * maxVariance && Math.abs(moduleSize - ((float) stateCount[3])) < maxVariance && Math.abs(moduleSize - ((float) stateCount[4])) < maxVariance;
        }
        return false;
    }

    protected static boolean foundPatternDiagonal(int[] stateCount) {
        int totalModuleSize = 0;
        for (int i = 0; i < 5; i++) {
            int count = stateCount[i];
            if (count == 0) {
                return false;
            }
            totalModuleSize += count;
        }
        if (totalModuleSize >= 7) {
            float moduleSize = totalModuleSize / 7.0f;
            float maxVariance = moduleSize / 1.333f;
            return Math.abs(moduleSize - ((float) stateCount[0])) < maxVariance && Math.abs(moduleSize - ((float) stateCount[1])) < maxVariance && Math.abs((3.0f * moduleSize) - ((float) stateCount[2])) < 3.0f * maxVariance && Math.abs(moduleSize - ((float) stateCount[3])) < maxVariance && Math.abs(moduleSize - ((float) stateCount[4])) < maxVariance;
        }
        return false;
    }

    private int[] getCrossCheckStateCount() {
        doClearCounts(this.crossCheckStateCount);
        return this.crossCheckStateCount;
    }

    @Deprecated
    protected final void clearCounts(int[] counts) {
        doClearCounts(counts);
    }

    @Deprecated
    protected final void shiftCounts2(int[] stateCount) {
        doShiftCounts2(stateCount);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void doClearCounts(int[] counts) {
        Arrays.fill(counts, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void doShiftCounts2(int[] stateCount) {
        stateCount[0] = stateCount[2];
        stateCount[1] = stateCount[3];
        stateCount[2] = stateCount[4];
        stateCount[3] = 1;
        stateCount[4] = 0;
    }

    private boolean crossCheckDiagonal(int centerI, int centerJ) {
        int[] stateCount = getCrossCheckStateCount();
        int i = 0;
        while (centerI >= i && centerJ >= i && this.image.get(centerJ - i, centerI - i)) {
            stateCount[2] = stateCount[2] + 1;
            i++;
        }
        if (stateCount[2] == 0) {
            return false;
        }
        while (centerI >= i && centerJ >= i && !this.image.get(centerJ - i, centerI - i)) {
            stateCount[1] = stateCount[1] + 1;
            i++;
        }
        if (stateCount[1] != 0) {
            while (centerI >= i && centerJ >= i && this.image.get(centerJ - i, centerI - i)) {
                stateCount[0] = stateCount[0] + 1;
                i++;
            }
            if (stateCount[0] != 0) {
                int maxI = this.image.getHeight();
                int maxJ = this.image.getWidth();
                int i2 = 1;
                while (centerI + i2 < maxI && centerJ + i2 < maxJ && this.image.get(centerJ + i2, centerI + i2)) {
                    stateCount[2] = stateCount[2] + 1;
                    i2++;
                }
                while (centerI + i2 < maxI && centerJ + i2 < maxJ && !this.image.get(centerJ + i2, centerI + i2)) {
                    stateCount[3] = stateCount[3] + 1;
                    i2++;
                }
                if (stateCount[3] != 0) {
                    while (centerI + i2 < maxI && centerJ + i2 < maxJ && this.image.get(centerJ + i2, centerI + i2)) {
                        stateCount[4] = stateCount[4] + 1;
                        i2++;
                    }
                    if (stateCount[4] != 0) {
                        return foundPatternDiagonal(stateCount);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
        if (r3[1] <= r10) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
        if (r0 < 0) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0049, code lost:
        if (r1.get(r9, r0) == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004e, code lost:
        if (r3[0] > r10) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0050, code lost:
        r3[0] = r3[0] + 1;
        r0 = r0 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
        if (r3[0] <= r10) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
        r0 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0064, code lost:
        if (r0 >= r2) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006a, code lost:
        if (r1.get(r9, r0) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006c, code lost:
        r3[2] = r3[2] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0076, code lost:
        if (r0 != r2) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0078, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007b, code lost:
        if (r0 >= r2) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0081, code lost:
        if (r1.get(r9, r0) != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0086, code lost:
        if (r3[3] >= r10) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0088, code lost:
        r3[3] = r3[3] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0092, code lost:
        if (r0 == r2) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0097, code lost:
        if (r3[3] < r10) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0099, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x009c, code lost:
        if (r0 >= r2) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a2, code lost:
        if (r1.get(r9, r0) == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a7, code lost:
        if (r3[4] >= r10) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00a9, code lost:
        r3[4] = r3[4] + 1;
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b6, code lost:
        if (r3[4] < r10) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b8, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00bc, code lost:
        r4 = (((r3[0] + r3[1]) + r3[2]) + r3[3]) + r3[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00da, code lost:
        if ((java.lang.Math.abs(r4 - r11) * 5) < (r11 * 2)) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00dc, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e4, code lost:
        if (foundPatternCross(r3) == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ec, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:?, code lost:
        return centerFromEnd(r3, r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float crossCheckVertical(int r8, int r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckVertical(int, int, int, int):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
        if (r3[1] <= r10) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
        if (r1 < 0) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0049, code lost:
        if (r0.get(r1, r9) == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004e, code lost:
        if (r3[0] > r10) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0050, code lost:
        r3[0] = r3[0] + 1;
        r1 = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
        if (r3[0] <= r10) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
        r1 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0064, code lost:
        if (r1 >= r2) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006a, code lost:
        if (r0.get(r1, r9) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006c, code lost:
        r3[2] = r3[2] + 1;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0076, code lost:
        if (r1 != r2) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0078, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007b, code lost:
        if (r1 >= r2) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0081, code lost:
        if (r0.get(r1, r9) != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0086, code lost:
        if (r3[3] >= r10) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0088, code lost:
        r3[3] = r3[3] + 1;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0092, code lost:
        if (r1 == r2) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0097, code lost:
        if (r3[3] < r10) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0099, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x009c, code lost:
        if (r1 >= r2) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a2, code lost:
        if (r0.get(r1, r9) == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a7, code lost:
        if (r3[4] >= r10) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00a9, code lost:
        r3[4] = r3[4] + 1;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b6, code lost:
        if (r3[4] < r10) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b8, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00bc, code lost:
        r4 = (((r3[0] + r3[1]) + r3[2]) + r3[3]) + r3[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d8, code lost:
        if ((java.lang.Math.abs(r4 - r11) * 5) < r11) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00da, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e2, code lost:
        if (foundPatternCross(r3) == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ea, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:?, code lost:
        return centerFromEnd(r3, r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float crossCheckHorizontal(int r8, int r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckHorizontal(int, int, int, int):float");
    }

    @Deprecated
    protected final boolean handlePossibleCenter(int[] stateCount, int i, int j, boolean pureBarcode) {
        return handlePossibleCenter(stateCount, i, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean handlePossibleCenter(int[] stateCount, int i, int j) {
        int stateCountTotal = stateCount[0] + stateCount[1] + stateCount[2] + stateCount[3] + stateCount[4];
        float centerJ = centerFromEnd(stateCount, j);
        float centerI = crossCheckVertical(i, (int) centerJ, stateCount[2], stateCountTotal);
        if (!Float.isNaN(centerI)) {
            float centerJ2 = crossCheckHorizontal((int) centerJ, (int) centerI, stateCount[2], stateCountTotal);
            if (!Float.isNaN(centerJ2) && crossCheckDiagonal((int) centerI, (int) centerJ2)) {
                float estimatedModuleSize = stateCountTotal / 7.0f;
                boolean found = false;
                int index = 0;
                while (true) {
                    if (index >= this.possibleCenters.size()) {
                        break;
                    }
                    FinderPattern center = this.possibleCenters.get(index);
                    if (!center.aboutEquals(estimatedModuleSize, centerI, centerJ2)) {
                        index++;
                    } else {
                        this.possibleCenters.set(index, center.combineEstimate(centerI, centerJ2, estimatedModuleSize));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    FinderPattern point = new FinderPattern(centerJ2, centerI, estimatedModuleSize);
                    this.possibleCenters.add(point);
                    if (this.resultPointCallback != null) {
                        this.resultPointCallback.foundPossibleResultPoint(point);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        ResultPoint firstConfirmedCenter = null;
        for (FinderPattern center : this.possibleCenters) {
            if (center.getCount() >= 2) {
                if (firstConfirmedCenter == null) {
                    firstConfirmedCenter = center;
                } else {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(firstConfirmedCenter.getX() - center.getX()) - Math.abs(firstConfirmedCenter.getY() - center.getY()))) / 2;
                }
            }
        }
        return 0;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int confirmedCount = 0;
        float totalModuleSize = 0.0f;
        int max = this.possibleCenters.size();
        for (FinderPattern pattern : this.possibleCenters) {
            if (pattern.getCount() >= 2) {
                confirmedCount++;
                totalModuleSize += pattern.getEstimatedModuleSize();
            }
        }
        if (confirmedCount < 3) {
            return false;
        }
        float average = totalModuleSize / max;
        float totalDeviation = 0.0f;
        for (FinderPattern pattern2 : this.possibleCenters) {
            totalDeviation += Math.abs(pattern2.getEstimatedModuleSize() - average);
        }
        return totalDeviation <= 0.05f * totalModuleSize;
    }

    private static double squaredDistance(FinderPattern a, FinderPattern b) {
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();
        return (x * x) + (y * y);
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        if (this.possibleCenters.size() < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        this.possibleCenters.sort(moduleComparator);
        double distortion = Double.MAX_VALUE;
        FinderPattern[] bestPatterns = new FinderPattern[3];
        for (int i = 0; i < this.possibleCenters.size() - 2; i++) {
            FinderPattern fpi = this.possibleCenters.get(i);
            float minModuleSize = fpi.getEstimatedModuleSize();
            for (int j = i + 1; j < this.possibleCenters.size() - 1; j++) {
                FinderPattern fpj = this.possibleCenters.get(j);
                double squares0 = squaredDistance(fpi, fpj);
                for (int k = j + 1; k < this.possibleCenters.size(); k++) {
                    FinderPattern fpk = this.possibleCenters.get(k);
                    if (fpk.getEstimatedModuleSize() <= 1.4f * minModuleSize) {
                        double a = squares0;
                        double b = squaredDistance(fpj, fpk);
                        double c = squaredDistance(fpi, fpk);
                        if (a < b) {
                            if (b > c) {
                                if (a < c) {
                                    b = c;
                                    c = b;
                                } else {
                                    a = c;
                                    c = b;
                                    b = a;
                                }
                            }
                        } else if (b < c) {
                            if (a < c) {
                                a = b;
                                b = a;
                            } else {
                                a = b;
                                b = c;
                                c = a;
                            }
                        } else {
                            a = c;
                            c = a;
                        }
                        double d = Math.abs(c - (2.0d * b)) + Math.abs(c - (2.0d * a));
                        if (d < distortion) {
                            distortion = d;
                            bestPatterns[0] = fpi;
                            bestPatterns[1] = fpj;
                            bestPatterns[2] = fpk;
                        }
                    }
                }
            }
        }
        if (distortion == Double.MAX_VALUE) {
            throw NotFoundException.getNotFoundInstance();
        }
        return bestPatterns;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class EstimatedModuleComparator implements Serializable, Comparator<FinderPattern> {
        private EstimatedModuleComparator() {
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern center1, FinderPattern center2) {
            return Float.compare(center1.getEstimatedModuleSize(), center2.getEstimatedModuleSize());
        }
    }
}
