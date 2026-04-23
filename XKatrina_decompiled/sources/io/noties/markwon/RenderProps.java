package io.noties.markwon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface RenderProps {
    <T> void clear(@NonNull Prop<T> prop);

    void clearAll();

    @Nullable
    <T> T get(@NonNull Prop<T> prop);

    @NonNull
    <T> T get(@NonNull Prop<T> prop, @NonNull T t);

    <T> void set(@NonNull Prop<T> prop, @Nullable T t);
}
