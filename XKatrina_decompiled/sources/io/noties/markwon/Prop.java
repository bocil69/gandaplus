package io.noties.markwon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public class Prop<T> {
    private final String name;

    @NonNull
    public static <T> Prop<T> of(@NonNull Class<T> cls, @NonNull String str) {
        return new Prop<>(str);
    }

    @NonNull
    public static <T> Prop<T> of(@NonNull String str) {
        return new Prop<>(str);
    }

    Prop(@NonNull String str) {
        this.name = str;
    }

    @NonNull
    public String name() {
        return this.name;
    }

    @Nullable
    public T get(@NonNull RenderProps renderProps) {
        return (T) renderProps.get(this);
    }

    @NonNull
    public T get(@NonNull RenderProps renderProps, @NonNull T t) {
        return (T) renderProps.get(this, t);
    }

    @NonNull
    public T require(@NonNull RenderProps renderProps) {
        T t = get(renderProps);
        if (t != null) {
            return t;
        }
        throw new NullPointerException(this.name);
    }

    public void set(@NonNull RenderProps renderProps, @Nullable T t) {
        renderProps.set(this, t);
    }

    public void clear(@NonNull RenderProps renderProps) {
        renderProps.clear(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.name.equals(((Prop) obj).name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "Prop{name='" + this.name + "'}";
    }
}
