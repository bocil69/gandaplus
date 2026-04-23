package io.noties.markwon.core;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.annotation.Size;
import io.noties.markwon.utils.ColorUtils;
import io.noties.markwon.utils.Dip;
import java.util.Arrays;
import java.util.Locale;
/* loaded from: classes2.dex */
public class MarkwonTheme {
    protected static final int BLOCK_QUOTE_DEF_COLOR_ALPHA = 25;
    protected static final int CODE_DEF_BACKGROUND_COLOR_ALPHA = 25;
    protected static final float CODE_DEF_TEXT_SIZE_RATIO = 0.87f;
    protected static final int HEADING_DEF_BREAK_COLOR_ALPHA = 75;
    private static final float[] HEADING_SIZES = {2.0f, 1.5f, 1.17f, 1.0f, 0.83f, 0.67f};
    protected static final int THEMATIC_BREAK_DEF_ALPHA = 25;
    protected final int blockMargin;
    protected final int blockQuoteColor;
    protected final int blockQuoteWidth;
    protected final int bulletListItemStrokeWidth;
    protected final int bulletWidth;
    protected final int codeBackgroundColor;
    protected final int codeBlockBackgroundColor;
    protected final int codeBlockMargin;
    protected final int codeBlockTextColor;
    protected final int codeBlockTextSize;
    protected final Typeface codeBlockTypeface;
    protected final int codeTextColor;
    protected final int codeTextSize;
    protected final Typeface codeTypeface;
    protected final int headingBreakColor;
    protected final int headingBreakHeight;
    protected final float[] headingTextSizeMultipliers;
    protected final Typeface headingTypeface;
    protected final boolean isLinkedUnderlined;
    protected final int linkColor;
    protected final int listItemColor;
    protected final int thematicBreakColor;
    protected final int thematicBreakHeight;

    @NonNull
    public static MarkwonTheme create(@NonNull Context context) {
        return builderWithDefaults(context).build();
    }

    @NonNull
    public static Builder emptyBuilder() {
        return new Builder();
    }

    @NonNull
    public static Builder builder(@NonNull MarkwonTheme markwonTheme) {
        return new Builder(markwonTheme);
    }

    @NonNull
    public static Builder builderWithDefaults(@NonNull Context context) {
        Dip create = Dip.create(context);
        return new Builder().codeBlockMargin(create.toPx(8)).blockMargin(create.toPx(24)).blockQuoteWidth(create.toPx(4)).bulletListItemStrokeWidth(create.toPx(1)).headingBreakHeight(create.toPx(1)).thematicBreakHeight(create.toPx(4));
    }

    protected MarkwonTheme(@NonNull Builder builder) {
        this.linkColor = builder.linkColor;
        this.isLinkedUnderlined = builder.isLinkUnderlined;
        this.blockMargin = builder.blockMargin;
        this.blockQuoteWidth = builder.blockQuoteWidth;
        this.blockQuoteColor = builder.blockQuoteColor;
        this.listItemColor = builder.listItemColor;
        this.bulletListItemStrokeWidth = builder.bulletListItemStrokeWidth;
        this.bulletWidth = builder.bulletWidth;
        this.codeTextColor = builder.codeTextColor;
        this.codeBlockTextColor = builder.codeBlockTextColor;
        this.codeBackgroundColor = builder.codeBackgroundColor;
        this.codeBlockBackgroundColor = builder.codeBlockBackgroundColor;
        this.codeBlockMargin = builder.codeBlockMargin;
        this.codeTypeface = builder.codeTypeface;
        this.codeBlockTypeface = builder.codeBlockTypeface;
        this.codeTextSize = builder.codeTextSize;
        this.codeBlockTextSize = builder.codeBlockTextSize;
        this.headingBreakHeight = builder.headingBreakHeight;
        this.headingBreakColor = builder.headingBreakColor;
        this.headingTypeface = builder.headingTypeface;
        this.headingTextSizeMultipliers = builder.headingTextSizeMultipliers;
        this.thematicBreakColor = builder.thematicBreakColor;
        this.thematicBreakHeight = builder.thematicBreakHeight;
    }

    public void applyLinkStyle(@NonNull TextPaint textPaint) {
        textPaint.setUnderlineText(this.isLinkedUnderlined);
        int i = this.linkColor;
        if (i != 0) {
            textPaint.setColor(i);
        } else {
            textPaint.setColor(textPaint.linkColor);
        }
    }

    public void applyLinkStyle(@NonNull Paint paint) {
        paint.setUnderlineText(this.isLinkedUnderlined);
        int i = this.linkColor;
        if (i != 0) {
            paint.setColor(i);
        } else if (paint instanceof TextPaint) {
            paint.setColor(((TextPaint) paint).linkColor);
        }
    }

    public void applyBlockQuoteStyle(@NonNull Paint paint) {
        int i = this.blockQuoteColor;
        if (i == 0) {
            i = ColorUtils.applyAlpha(paint.getColor(), 25);
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i);
    }

    public int getBlockMargin() {
        return this.blockMargin;
    }

    public int getBlockQuoteWidth() {
        int i = this.blockQuoteWidth;
        return i == 0 ? (int) ((this.blockMargin * 0.25f) + 0.5f) : i;
    }

    public void applyListItemStyle(@NonNull Paint paint) {
        int i = this.listItemColor;
        if (i == 0) {
            i = paint.getColor();
        }
        paint.setColor(i);
        int i2 = this.bulletListItemStrokeWidth;
        if (i2 != 0) {
            paint.setStrokeWidth(i2);
        }
    }

    public int getBulletWidth(int i) {
        int min = Math.min(this.blockMargin, i) / 2;
        int i2 = this.bulletWidth;
        return (i2 == 0 || i2 > min) ? min : i2;
    }

    public void applyCodeTextStyle(@NonNull Paint paint) {
        int i = this.codeTextColor;
        if (i != 0) {
            paint.setColor(i);
        }
        Typeface typeface = this.codeTypeface;
        if (typeface != null) {
            paint.setTypeface(typeface);
            int i2 = this.codeTextSize;
            if (i2 > 0) {
                paint.setTextSize(i2);
                return;
            }
            return;
        }
        paint.setTypeface(Typeface.MONOSPACE);
        int i3 = this.codeTextSize;
        if (i3 > 0) {
            paint.setTextSize(i3);
        } else {
            paint.setTextSize(paint.getTextSize() * CODE_DEF_TEXT_SIZE_RATIO);
        }
    }

    public void applyCodeBlockTextStyle(@NonNull Paint paint) {
        int i = this.codeBlockTextColor;
        if (i == 0) {
            i = this.codeTextColor;
        }
        if (i != 0) {
            paint.setColor(i);
        }
        Typeface typeface = this.codeBlockTypeface;
        if (typeface == null) {
            typeface = this.codeTypeface;
        }
        if (typeface != null) {
            paint.setTypeface(typeface);
            int i2 = this.codeBlockTextSize;
            if (i2 <= 0) {
                i2 = this.codeTextSize;
            }
            if (i2 > 0) {
                paint.setTextSize(i2);
                return;
            }
            return;
        }
        paint.setTypeface(Typeface.MONOSPACE);
        int i3 = this.codeBlockTextSize;
        if (i3 <= 0) {
            i3 = this.codeTextSize;
        }
        if (i3 > 0) {
            paint.setTextSize(i3);
        } else {
            paint.setTextSize(paint.getTextSize() * CODE_DEF_TEXT_SIZE_RATIO);
        }
    }

    public int getCodeBlockMargin() {
        return this.codeBlockMargin;
    }

    public int getCodeBackgroundColor(@NonNull Paint paint) {
        int i = this.codeBackgroundColor;
        return i != 0 ? i : ColorUtils.applyAlpha(paint.getColor(), 25);
    }

    public int getCodeBlockBackgroundColor(@NonNull Paint paint) {
        int i = this.codeBlockBackgroundColor;
        if (i == 0) {
            i = this.codeBackgroundColor;
        }
        return i != 0 ? i : ColorUtils.applyAlpha(paint.getColor(), 25);
    }

    public void applyHeadingTextStyle(@NonNull Paint paint, @IntRange(from = 1, to = 6) int i) {
        Typeface typeface = this.headingTypeface;
        if (typeface == null) {
            paint.setFakeBoldText(true);
        } else {
            paint.setTypeface(typeface);
        }
        float[] fArr = this.headingTextSizeMultipliers;
        if (fArr == null) {
            fArr = HEADING_SIZES;
        }
        if (fArr != null && fArr.length >= i) {
            paint.setTextSize(paint.getTextSize() * fArr[i - 1]);
            return;
        }
        throw new IllegalStateException(String.format(Locale.US, "Supplied heading level: %d is invalid, where configured heading sizes are: `%s`", Integer.valueOf(i), Arrays.toString(fArr)));
    }

    public void applyHeadingBreakStyle(@NonNull Paint paint) {
        int i = this.headingBreakColor;
        if (i == 0) {
            i = ColorUtils.applyAlpha(paint.getColor(), 75);
        }
        paint.setColor(i);
        paint.setStyle(Paint.Style.FILL);
        int i2 = this.headingBreakHeight;
        if (i2 >= 0) {
            paint.setStrokeWidth(i2);
        }
    }

    public void applyThematicBreakStyle(@NonNull Paint paint) {
        int i = this.thematicBreakColor;
        if (i == 0) {
            i = ColorUtils.applyAlpha(paint.getColor(), 25);
        }
        paint.setColor(i);
        paint.setStyle(Paint.Style.FILL);
        int i2 = this.thematicBreakHeight;
        if (i2 >= 0) {
            paint.setStrokeWidth(i2);
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private int blockMargin;
        private int blockQuoteColor;
        private int blockQuoteWidth;
        private int bulletListItemStrokeWidth;
        private int bulletWidth;
        private int codeBackgroundColor;
        private int codeBlockBackgroundColor;
        private int codeBlockMargin;
        private int codeBlockTextColor;
        private int codeBlockTextSize;
        private Typeface codeBlockTypeface;
        private int codeTextColor;
        private int codeTextSize;
        private Typeface codeTypeface;
        private int headingBreakColor;
        private int headingBreakHeight;
        private float[] headingTextSizeMultipliers;
        private Typeface headingTypeface;
        private boolean isLinkUnderlined;
        private int linkColor;
        private int listItemColor;
        private int thematicBreakColor;
        private int thematicBreakHeight;

        Builder() {
            this.isLinkUnderlined = true;
            this.headingBreakHeight = -1;
            this.thematicBreakHeight = -1;
        }

        Builder(@NonNull MarkwonTheme markwonTheme) {
            this.isLinkUnderlined = true;
            this.headingBreakHeight = -1;
            this.thematicBreakHeight = -1;
            this.linkColor = markwonTheme.linkColor;
            this.isLinkUnderlined = markwonTheme.isLinkedUnderlined;
            this.blockMargin = markwonTheme.blockMargin;
            this.blockQuoteWidth = markwonTheme.blockQuoteWidth;
            this.blockQuoteColor = markwonTheme.blockQuoteColor;
            this.listItemColor = markwonTheme.listItemColor;
            this.bulletListItemStrokeWidth = markwonTheme.bulletListItemStrokeWidth;
            this.bulletWidth = markwonTheme.bulletWidth;
            this.codeTextColor = markwonTheme.codeTextColor;
            this.codeBlockTextColor = markwonTheme.codeBlockTextColor;
            this.codeBackgroundColor = markwonTheme.codeBackgroundColor;
            this.codeBlockBackgroundColor = markwonTheme.codeBlockBackgroundColor;
            this.codeBlockMargin = markwonTheme.codeBlockMargin;
            this.codeTypeface = markwonTheme.codeTypeface;
            this.codeTextSize = markwonTheme.codeTextSize;
            this.headingBreakHeight = markwonTheme.headingBreakHeight;
            this.headingBreakColor = markwonTheme.headingBreakColor;
            this.headingTypeface = markwonTheme.headingTypeface;
            this.headingTextSizeMultipliers = markwonTheme.headingTextSizeMultipliers;
            this.thematicBreakColor = markwonTheme.thematicBreakColor;
            this.thematicBreakHeight = markwonTheme.thematicBreakHeight;
        }

        @NonNull
        public Builder linkColor(@ColorInt int i) {
            this.linkColor = i;
            return this;
        }

        @NonNull
        public Builder isLinkUnderlined(boolean z) {
            this.isLinkUnderlined = z;
            return this;
        }

        @NonNull
        public Builder blockMargin(@Px int i) {
            this.blockMargin = i;
            return this;
        }

        @NonNull
        public Builder blockQuoteWidth(@Px int i) {
            this.blockQuoteWidth = i;
            return this;
        }

        @NonNull
        public Builder blockQuoteColor(@ColorInt int i) {
            this.blockQuoteColor = i;
            return this;
        }

        @NonNull
        public Builder listItemColor(@ColorInt int i) {
            this.listItemColor = i;
            return this;
        }

        @NonNull
        public Builder bulletListItemStrokeWidth(@Px int i) {
            this.bulletListItemStrokeWidth = i;
            return this;
        }

        @NonNull
        public Builder bulletWidth(@Px int i) {
            this.bulletWidth = i;
            return this;
        }

        @NonNull
        public Builder codeTextColor(@ColorInt int i) {
            this.codeTextColor = i;
            return this;
        }

        @NonNull
        public Builder codeBlockTextColor(@ColorInt int i) {
            this.codeBlockTextColor = i;
            return this;
        }

        @NonNull
        public Builder codeBackgroundColor(@ColorInt int i) {
            this.codeBackgroundColor = i;
            return this;
        }

        @NonNull
        public Builder codeBlockBackgroundColor(@ColorInt int i) {
            this.codeBlockBackgroundColor = i;
            return this;
        }

        @NonNull
        public Builder codeBlockMargin(@Px int i) {
            this.codeBlockMargin = i;
            return this;
        }

        @NonNull
        public Builder codeTypeface(@NonNull Typeface typeface) {
            this.codeTypeface = typeface;
            return this;
        }

        @NonNull
        public Builder codeBlockTypeface(@NonNull Typeface typeface) {
            this.codeBlockTypeface = typeface;
            return this;
        }

        @NonNull
        public Builder codeTextSize(@Px int i) {
            this.codeTextSize = i;
            return this;
        }

        @NonNull
        public Builder codeBlockTextSize(@Px int i) {
            this.codeBlockTextSize = i;
            return this;
        }

        @NonNull
        public Builder headingBreakHeight(@Px int i) {
            this.headingBreakHeight = i;
            return this;
        }

        @NonNull
        public Builder headingBreakColor(@ColorInt int i) {
            this.headingBreakColor = i;
            return this;
        }

        @NonNull
        public Builder headingTypeface(@NonNull Typeface typeface) {
            this.headingTypeface = typeface;
            return this;
        }

        @NonNull
        public Builder headingTextSizeMultipliers(@NonNull @Size(6) float[] fArr) {
            this.headingTextSizeMultipliers = fArr;
            return this;
        }

        @NonNull
        public Builder thematicBreakColor(@ColorInt int i) {
            this.thematicBreakColor = i;
            return this;
        }

        @NonNull
        public Builder thematicBreakHeight(@Px int i) {
            this.thematicBreakHeight = i;
            return this;
        }

        @NonNull
        public MarkwonTheme build() {
            return new MarkwonTheme(this);
        }
    }
}
