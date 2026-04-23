package io.noties.markwon.image.destination;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
/* loaded from: classes2.dex */
public class ImageDestinationProcessorRelativeToAbsolute extends ImageDestinationProcessor {
    private final URL base;

    @NonNull
    public static ImageDestinationProcessorRelativeToAbsolute create(@NonNull String str) {
        return new ImageDestinationProcessorRelativeToAbsolute(str);
    }

    public static ImageDestinationProcessorRelativeToAbsolute create(@NonNull URL url) {
        return new ImageDestinationProcessorRelativeToAbsolute(url);
    }

    public ImageDestinationProcessorRelativeToAbsolute(@NonNull String str) {
        this.base = obtain(str);
    }

    public ImageDestinationProcessorRelativeToAbsolute(@NonNull URL url) {
        this.base = url;
    }

    @Override // io.noties.markwon.image.destination.ImageDestinationProcessor
    @NonNull
    public String process(@NonNull String str) {
        if (this.base != null) {
            try {
                return new URL(this.base, str).toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return str;
            }
        }
        return str;
    }

    @Nullable
    private static URL obtain(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
