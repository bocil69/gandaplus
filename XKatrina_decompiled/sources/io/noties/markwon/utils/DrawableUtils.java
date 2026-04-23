package io.noties.markwon.utils;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
@Deprecated
/* loaded from: classes2.dex */
public abstract class DrawableUtils {
    public static void intrinsicBounds(@NonNull Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    private DrawableUtils() {
    }
}
