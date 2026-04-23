package com.budiyev.android.codescanner;

import androidx.annotation.NonNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class Point {
    private final int mX;
    private final int mY;

    public Point(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int hashCode() {
        int i = this.mX;
        int i2 = this.mY;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Point) {
            Point point = (Point) obj;
            return this.mX == point.mX && this.mY == point.mY;
        }
        return false;
    }

    @NonNull
    public String toString() {
        return "(" + this.mX + "; " + this.mY + ")";
    }
}
