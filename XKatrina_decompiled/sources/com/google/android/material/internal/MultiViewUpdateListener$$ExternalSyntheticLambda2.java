package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import com.google.android.material.internal.MultiViewUpdateListener;
/* compiled from: D8$$SyntheticClass */
@SynthesizedClassV2(kind = 18, versionHash = "79350b666c61fb98f585652cf8eb3be7850d2ab8c16c1e890d0171be2ca2d761")
/* loaded from: classes.dex */
public final /* synthetic */ class MultiViewUpdateListener$$ExternalSyntheticLambda2 implements MultiViewUpdateListener.Listener {
    @Override // com.google.android.material.internal.MultiViewUpdateListener.Listener
    public final void onAnimationUpdate(ValueAnimator valueAnimator, View view) {
        MultiViewUpdateListener.setTranslationY(valueAnimator, view);
    }
}
