package io.noties.markwon.image;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class AsyncDrawableLoaderNoOp extends AsyncDrawableLoader {
    @Override // io.noties.markwon.image.AsyncDrawableLoader
    public void cancel(@NonNull AsyncDrawable asyncDrawable) {
    }

    @Override // io.noties.markwon.image.AsyncDrawableLoader
    public void load(@NonNull AsyncDrawable asyncDrawable) {
    }

    @Override // io.noties.markwon.image.AsyncDrawableLoader
    @Nullable
    public Drawable placeholder(@NonNull AsyncDrawable asyncDrawable) {
        return null;
    }
}
