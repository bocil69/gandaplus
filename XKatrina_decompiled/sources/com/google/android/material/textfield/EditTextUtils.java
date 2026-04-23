package com.google.android.material.textfield;

import android.widget.EditText;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
class EditTextUtils {
    private EditTextUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEditable(@NonNull EditText editText) {
        return editText.getInputType() != 0;
    }
}
