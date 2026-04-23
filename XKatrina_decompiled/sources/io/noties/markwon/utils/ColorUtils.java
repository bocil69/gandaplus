package io.noties.markwon.utils;

import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.core.view.ViewCompat;
/* loaded from: classes2.dex */
public abstract class ColorUtils {
    @ColorInt
    public static int applyAlpha(@ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
    }

    @ColorInt
    public static int blend(@ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        float f2 = 1.0f - f;
        return Color.rgb((int) ((Color.red(i) * f2) + (Color.red(i2) * f)), (int) ((Color.green(i) * f2) + (Color.green(i2) * f)), (int) ((f2 * Color.blue(i)) + (f * Color.blue(i2))));
    }

    private ColorUtils() {
    }
}
