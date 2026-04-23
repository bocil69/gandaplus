package io.noties.markwon.image.destination;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public abstract class ImageDestinationProcessor {
    @NonNull
    public abstract String process(@NonNull String str);

    @NonNull
    public static ImageDestinationProcessor noOp() {
        return new NoOp();
    }

    /* loaded from: classes2.dex */
    private static class NoOp extends ImageDestinationProcessor {
        @Override // io.noties.markwon.image.destination.ImageDestinationProcessor
        @NonNull
        public String process(@NonNull String str) {
            return str;
        }

        private NoOp() {
        }
    }
}
