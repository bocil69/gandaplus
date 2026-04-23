package io.noties.markwon.image;

import android.graphics.Rect;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public abstract class ImageSizeResolver {
    @NonNull
    public abstract Rect resolveImageSize(@NonNull AsyncDrawable asyncDrawable);
}
