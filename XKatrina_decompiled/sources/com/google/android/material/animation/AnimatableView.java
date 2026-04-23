package com.google.android.material.animation;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public interface AnimatableView {

    /* loaded from: classes.dex */
    public interface Listener {
        void onAnimationEnd();
    }

    void startAnimation(@NonNull Listener listener);

    void stopAnimation();
}
