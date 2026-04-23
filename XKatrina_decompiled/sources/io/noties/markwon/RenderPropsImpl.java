package io.noties.markwon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
class RenderPropsImpl implements RenderProps {
    private final Map<Prop, Object> values = new HashMap(3);

    @Override // io.noties.markwon.RenderProps
    @Nullable
    public <T> T get(@NonNull Prop<T> prop) {
        return (T) this.values.get(prop);
    }

    @Override // io.noties.markwon.RenderProps
    @NonNull
    public <T> T get(@NonNull Prop<T> prop, @NonNull T t) {
        T t2 = (T) this.values.get(prop);
        return t2 != null ? t2 : t;
    }

    @Override // io.noties.markwon.RenderProps
    public <T> void set(@NonNull Prop<T> prop, @Nullable T t) {
        if (t == null) {
            this.values.remove(prop);
        } else {
            this.values.put(prop, t);
        }
    }

    @Override // io.noties.markwon.RenderProps
    public <T> void clear(@NonNull Prop<T> prop) {
        this.values.remove(prop);
    }

    @Override // io.noties.markwon.RenderProps
    public void clearAll() {
        this.values.clear();
    }
}
