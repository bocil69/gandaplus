package com.google.android.material.sidesheet;

import android.view.View;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
interface SheetCallback {
    void onSlide(@NonNull View view, float f);

    void onStateChanged(@NonNull View view, int i);
}
