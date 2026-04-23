package io.noties.markwon.movement;

import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.MarkwonPlugin;
import io.noties.markwon.core.CorePlugin;
/* loaded from: classes2.dex */
public class MovementMethodPlugin extends AbstractMarkwonPlugin {
    @Nullable
    private final MovementMethod movementMethod;

    @NonNull
    @Deprecated
    public static MovementMethodPlugin create() {
        return create(LinkMovementMethod.getInstance());
    }

    @NonNull
    public static MovementMethodPlugin link() {
        return create(LinkMovementMethod.getInstance());
    }

    @NonNull
    public static MovementMethodPlugin none() {
        return new MovementMethodPlugin(null);
    }

    @NonNull
    public static MovementMethodPlugin create(@NonNull MovementMethod movementMethod) {
        return new MovementMethodPlugin(movementMethod);
    }

    MovementMethodPlugin(@Nullable MovementMethod movementMethod) {
        this.movementMethod = movementMethod;
    }

    @Override // io.noties.markwon.AbstractMarkwonPlugin, io.noties.markwon.MarkwonPlugin
    public void configure(@NonNull MarkwonPlugin.Registry registry) {
        ((CorePlugin) registry.require(CorePlugin.class)).hasExplicitMovementMethod(true);
    }

    @Override // io.noties.markwon.AbstractMarkwonPlugin, io.noties.markwon.MarkwonPlugin
    public void beforeSetText(@NonNull TextView textView, @NonNull Spanned spanned) {
        MovementMethod movementMethod = textView.getMovementMethod();
        MovementMethod movementMethod2 = this.movementMethod;
        if (movementMethod != movementMethod2) {
            textView.setMovementMethod(movementMethod2);
        }
    }
}
