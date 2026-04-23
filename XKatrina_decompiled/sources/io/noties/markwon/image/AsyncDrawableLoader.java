package io.noties.markwon.image;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class AsyncDrawableLoader {
    public abstract void cancel(@NonNull AsyncDrawable asyncDrawable);

    public abstract void load(@NonNull AsyncDrawable asyncDrawable);

    @Nullable
    public abstract Drawable placeholder(@NonNull AsyncDrawable asyncDrawable);

    @NonNull
    public static AsyncDrawableLoader noOp() {
        return new AsyncDrawableLoaderNoOp();
    }
}
