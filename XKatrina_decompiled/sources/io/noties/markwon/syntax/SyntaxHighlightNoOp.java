package io.noties.markwon.syntax;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public class SyntaxHighlightNoOp implements SyntaxHighlight {
    @Override // io.noties.markwon.syntax.SyntaxHighlight
    @NonNull
    public CharSequence highlight(@Nullable String str, @NonNull String str2) {
        return str2;
    }
}
