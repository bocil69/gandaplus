package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class ToneDeltaConstraint {
    public final double delta;
    public final DynamicColor keepAway;
    public final TonePolarity keepAwayPolarity;

    public ToneDeltaConstraint(double d, DynamicColor dynamicColor, TonePolarity tonePolarity) {
        this.delta = d;
        this.keepAway = dynamicColor;
        this.keepAwayPolarity = tonePolarity;
    }
}
