package com.budiyev.android.codescanner;

import android.graphics.Matrix;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
final class Rect {
    private final int mBottom;
    private final int mLeft;
    private final int mRight;
    private final int mTop;

    public Rect(int i, int i2, int i3, int i4) {
        this.mLeft = i;
        this.mTop = i2;
        this.mRight = i3;
        this.mBottom = i4;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getTop() {
        return this.mTop;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getBottom() {
        return this.mBottom;
    }

    public int getWidth() {
        return this.mRight - this.mLeft;
    }

    public int getHeight() {
        return this.mBottom - this.mTop;
    }

    public boolean isPointInside(int i, int i2) {
        return this.mLeft < i && this.mTop < i2 && this.mRight > i && this.mBottom > i2;
    }

    @NonNull
    public Rect sort() {
        int i = this.mLeft;
        int i2 = this.mTop;
        int i3 = this.mRight;
        int i4 = this.mBottom;
        if (i > i3 || i2 > i4) {
            if (i > i3) {
                i3 = i;
                i = i3;
            }
            if (i2 > i4) {
                i4 = i2;
                i2 = i4;
            }
            return new Rect(i, i2, i3, i4);
        }
        return this;
    }

    @NonNull
    public Rect bound(int i, int i2, int i3, int i4) {
        int i5 = this.mLeft;
        int i6 = this.mTop;
        int i7 = this.mRight;
        int i8 = this.mBottom;
        return (i5 < i || i6 < i2 || i7 > i3 || i8 > i4) ? new Rect(Math.max(i5, i), Math.max(i6, i2), Math.min(i7, i3), Math.min(i8, i4)) : this;
    }

    @NonNull
    public Rect rotate(float f, float f2, float f3) {
        Matrix matrix = new Matrix();
        float[] fArr = {this.mLeft, this.mTop, this.mRight, this.mBottom};
        matrix.postRotate(f, f2, f3);
        matrix.mapPoints(fArr);
        int i = (int) fArr[0];
        int i2 = (int) fArr[1];
        int i3 = (int) fArr[2];
        int i4 = (int) fArr[3];
        if (i > i3) {
            i3 = i;
            i = i3;
        }
        if (i2 > i4) {
            i4 = i2;
            i2 = i4;
        }
        return new Rect(i, i2, i3, i4);
    }

    @NonNull
    public Rect fitIn(@NonNull Rect rect) {
        int i = this.mLeft;
        int i2 = this.mTop;
        int i3 = this.mRight;
        int i4 = this.mBottom;
        int width = getWidth();
        int height = getHeight();
        int i5 = rect.mLeft;
        int i6 = rect.mTop;
        int i7 = rect.mRight;
        int i8 = rect.mBottom;
        int width2 = rect.getWidth();
        int height2 = rect.getHeight();
        if (i < i5 || i2 < i6 || i3 > i7 || i4 > i8) {
            int min = Math.min(width, width2);
            int min2 = Math.min(height, height2);
            if (i < i5) {
                i3 = i5 + min;
                i = i5;
            } else if (i3 > i7) {
                i = i7 - min;
                i3 = i7;
            }
            if (i2 < i6) {
                i4 = i6 + min2;
                i2 = i6;
            } else if (i4 > i8) {
                i2 = i8 - min2;
                i4 = i8;
            }
            return new Rect(i, i2, i3, i4);
        }
        return this;
    }

    public int hashCode() {
        return (((((this.mLeft * 31) + this.mTop) * 31) + this.mRight) * 31) + this.mBottom;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Rect) {
            Rect rect = (Rect) obj;
            return this.mLeft == rect.mLeft && this.mTop == rect.mTop && this.mRight == rect.mRight && this.mBottom == rect.mBottom;
        }
        return false;
    }

    @NonNull
    public String toString() {
        return "[(" + this.mLeft + "; " + this.mTop + ") - (" + this.mRight + "; " + this.mBottom + ")]";
    }
}
