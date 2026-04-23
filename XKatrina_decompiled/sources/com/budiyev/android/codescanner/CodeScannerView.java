package com.budiyev.android.codescanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleRes;
import com.google.android.material.color.utilities.Contrast;
/* loaded from: classes2.dex */
public final class CodeScannerView extends ViewGroup {
    private static final int DEFAULT_AUTO_FOCUS_BUTTON_COLOR = -1;
    private static final boolean DEFAULT_AUTO_FOCUS_BUTTON_VISIBLE = true;
    private static final float DEFAULT_BUTTON_PADDING_DP = 16.0f;
    private static final int DEFAULT_FLASH_BUTTON_COLOR = -1;
    private static final boolean DEFAULT_FLASH_BUTTON_VISIBLE = true;
    private static final float DEFAULT_FRAME_ASPECT_RATIO_HEIGHT = 1.0f;
    private static final float DEFAULT_FRAME_ASPECT_RATIO_WIDTH = 1.0f;
    private static final int DEFAULT_FRAME_COLOR = -1;
    private static final boolean DEFAULT_FRAME_CORNERS_CAP_ROUNDED = false;
    private static final float DEFAULT_FRAME_CORNERS_RADIUS_DP = 0.0f;
    private static final float DEFAULT_FRAME_CORNER_SIZE_DP = 50.0f;
    private static final float DEFAULT_FRAME_SIZE = 0.75f;
    private static final float DEFAULT_FRAME_THICKNESS_DP = 2.0f;
    private static final float DEFAULT_FRAME_VERTICAL_BIAS = 0.5f;
    private static final boolean DEFAULT_FRAME_VISIBLE = true;
    private static final int DEFAULT_MASK_COLOR = 1996488704;
    private static final boolean DEFAULT_MASK_VISIBLE = true;
    private static final float FOCUS_AREA_SIZE_DP = 20.0f;
    private static final int HINT_VIEW_INDEX = 4;
    private static final int MAX_CHILD_COUNT = 5;
    private ImageView mAutoFocusButton;
    private int mAutoFocusButtonColor;
    private Drawable mAutoFocusButtonOffIcon;
    private Drawable mAutoFocusButtonOnIcon;
    private int mAutoFocusButtonPaddingHorizontal;
    private int mAutoFocusButtonPaddingVertical;
    private ButtonPosition mAutoFocusButtonPosition;
    private CodeScanner mCodeScanner;
    private ImageView mFlashButton;
    private int mFlashButtonColor;
    private Drawable mFlashButtonOffIcon;
    private Drawable mFlashButtonOnIcon;
    private int mFlashButtonPaddingHorizontal;
    private int mFlashButtonPaddingVertical;
    private ButtonPosition mFlashButtonPosition;
    private int mFocusAreaSize;
    private Point mPreviewSize;
    private SurfaceView mPreviewView;
    private SizeListener mSizeListener;
    private ViewFinderView mViewFinderView;
    private static final ButtonPosition DEFAULT_AUTO_FOCUS_BUTTON_POSITION = ButtonPosition.TOP_START;
    private static final ButtonPosition DEFAULT_FLASH_BUTTON_POSITION = ButtonPosition.TOP_END;

    /* loaded from: classes2.dex */
    interface SizeListener {
        void onSizeChanged(int i, int i2);
    }

    public CodeScannerView(@NonNull Context context) {
        super(context);
        initialize(context, null, 0, 0);
    }

    public CodeScannerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context, attributeSet, 0, 0);
    }

    public CodeScannerView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet, i, 0);
    }

    @RequiresApi(21)
    public CodeScannerView(Context context, AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(context, attributeSet, i, i2);
        initialize(context, attributeSet, i, i2);
    }

    private void initialize(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        TypedArray typedArray;
        this.mPreviewView = new SurfaceView(context);
        this.mViewFinderView = new ViewFinderView(context);
        float f = context.getResources().getDisplayMetrics().density;
        int round = Math.round(DEFAULT_BUTTON_PADDING_DP * f);
        this.mFocusAreaSize = Math.round(FOCUS_AREA_SIZE_DP * f);
        ImageView imageView = new ImageView(context);
        this.mAutoFocusButton = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        this.mAutoFocusButton.setOnClickListener(new AutoFocusClickListener(this, null));
        ImageView imageView2 = new ImageView(context);
        this.mFlashButton = imageView2;
        imageView2.setScaleType(ImageView.ScaleType.CENTER);
        this.mFlashButton.setOnClickListener(new FlashClickListener(this, null));
        if (attributeSet == null) {
            setFrameAspectRatio(1.0f, 1.0f);
            setMaskColor(DEFAULT_MASK_COLOR);
            setMaskVisible(true);
            setFrameColor(-1);
            setFrameVisible(true);
            setFrameThickness(Math.round(DEFAULT_FRAME_THICKNESS_DP * f));
            setFrameCornersSize(Math.round(50.0f * f));
            setFrameCornersRadius(Math.round(f * 0.0f));
            setFrameCornersCapRounded(false);
            setFrameSize(0.75f);
            setFrameVerticalBias(0.5f);
            setAutoFocusButtonColor(-1);
            setFlashButtonColor(-1);
            setAutoFocusButtonVisible(true);
            setAutoFocusButtonPosition(DEFAULT_AUTO_FOCUS_BUTTON_POSITION);
            setFlashButtonVisible(true);
            setFlashButtonPosition(DEFAULT_FLASH_BUTTON_POSITION);
            setAutoFocusButtonPaddingHorizontal(round);
            setAutoFocusButtonPaddingVertical(round);
            setFlashButtonPaddingHorizontal(round);
            setFlashButtonPaddingVertical(round);
            setAutoFocusButtonOnIcon(Utils.getDrawable(context, R.drawable.ic_code_scanner_auto_focus_on));
            setAutoFocusButtonOffIcon(Utils.getDrawable(context, R.drawable.ic_code_scanner_auto_focus_off));
            setFlashButtonOnIcon(Utils.getDrawable(context, R.drawable.ic_code_scanner_flash_on));
            setFlashButtonOffIcon(Utils.getDrawable(context, R.drawable.ic_code_scanner_flash_off));
        } else {
            try {
                typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CodeScannerView, i, i2);
                try {
                    setMaskColor(typedArray.getColor(R.styleable.CodeScannerView_maskColor, DEFAULT_MASK_COLOR));
                    setMaskVisible(typedArray.getBoolean(R.styleable.CodeScannerView_maskVisible, true));
                    setFrameColor(typedArray.getColor(R.styleable.CodeScannerView_frameColor, -1));
                    setFrameVisible(typedArray.getBoolean(R.styleable.CodeScannerView_frameVisible, true));
                    setFrameThickness(typedArray.getDimensionPixelOffset(R.styleable.CodeScannerView_frameThickness, Math.round(DEFAULT_FRAME_THICKNESS_DP * f)));
                    setFrameCornersSize(typedArray.getDimensionPixelOffset(R.styleable.CodeScannerView_frameCornersSize, Math.round(50.0f * f)));
                    setFrameCornersRadius(typedArray.getDimensionPixelOffset(R.styleable.CodeScannerView_frameCornersRadius, Math.round(f * 0.0f)));
                    setFrameCornersCapRounded(typedArray.getBoolean(R.styleable.CodeScannerView_frameCornersCapRounded, false));
                    setFrameAspectRatio(typedArray.getFloat(R.styleable.CodeScannerView_frameAspectRatioWidth, 1.0f), typedArray.getFloat(R.styleable.CodeScannerView_frameAspectRatioHeight, 1.0f));
                    setFrameSize(typedArray.getFloat(R.styleable.CodeScannerView_frameSize, 0.75f));
                    setFrameVerticalBias(typedArray.getFloat(R.styleable.CodeScannerView_frameVerticalBias, 0.5f));
                    setAutoFocusButtonVisible(typedArray.getBoolean(R.styleable.CodeScannerView_autoFocusButtonVisible, true));
                    setAutoFocusButtonColor(typedArray.getColor(R.styleable.CodeScannerView_autoFocusButtonColor, -1));
                    setAutoFocusButtonPosition(buttonPositionFromAttr(typedArray.getInt(R.styleable.CodeScannerView_autoFocusButtonPosition, indexOfButtonPosition(DEFAULT_AUTO_FOCUS_BUTTON_POSITION))));
                    setAutoFocusButtonPaddingHorizontal(typedArray.getDimensionPixelOffset(R.styleable.CodeScannerView_autoFocusButtonPaddingHorizontal, round));
                    setAutoFocusButtonPaddingVertical(typedArray.getDimensionPixelOffset(R.styleable.CodeScannerView_autoFocusButtonPaddingVertical, round));
                    Drawable drawable = typedArray.getDrawable(R.styleable.CodeScannerView_autoFocusButtonOnIcon);
                    if (drawable == null) {
                        drawable = Utils.getDrawable(context, R.drawable.ic_code_scanner_auto_focus_on);
                    }
                    setAutoFocusButtonOnIcon(drawable);
                    Drawable drawable2 = typedArray.getDrawable(R.styleable.CodeScannerView_autoFocusButtonOffIcon);
                    if (drawable2 == null) {
                        drawable2 = Utils.getDrawable(context, R.drawable.ic_code_scanner_auto_focus_off);
                    }
                    setAutoFocusButtonOffIcon(drawable2);
                    setFlashButtonVisible(typedArray.getBoolean(R.styleable.CodeScannerView_flashButtonVisible, true));
                    setFlashButtonColor(typedArray.getColor(R.styleable.CodeScannerView_flashButtonColor, -1));
                    setFlashButtonPosition(buttonPositionFromAttr(typedArray.getInt(R.styleable.CodeScannerView_flashButtonPosition, indexOfButtonPosition(DEFAULT_FLASH_BUTTON_POSITION))));
                    setFlashButtonPaddingHorizontal(typedArray.getDimensionPixelOffset(R.styleable.CodeScannerView_flashButtonPaddingHorizontal, round));
                    setFlashButtonPaddingVertical(typedArray.getDimensionPixelOffset(R.styleable.CodeScannerView_flashButtonPaddingVertical, round));
                    Drawable drawable3 = typedArray.getDrawable(R.styleable.CodeScannerView_flashButtonOnIcon);
                    if (drawable3 == null) {
                        drawable3 = Utils.getDrawable(context, R.drawable.ic_code_scanner_flash_on);
                    }
                    setFlashButtonOnIcon(drawable3);
                    Drawable drawable4 = typedArray.getDrawable(R.styleable.CodeScannerView_flashButtonOffIcon);
                    if (drawable4 == null) {
                        drawable4 = Utils.getDrawable(context, R.drawable.ic_code_scanner_flash_off);
                    }
                    setFlashButtonOffIcon(drawable4);
                    if (typedArray != null) {
                        typedArray.recycle();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (typedArray != null) {
                        typedArray.recycle();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                typedArray = null;
            }
        }
        if (isInEditMode()) {
            setAutoFocusEnabled(true);
            setFlashEnabled(true);
        }
        addView(this.mPreviewView, new LayoutParams(-1, -1));
        addView(this.mViewFinderView, new LayoutParams(-1, -1));
        addView(this.mAutoFocusButton, new LayoutParams(-2, -2));
        addView(this.mFlashButton, new LayoutParams(-2, -2));
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        if (childCount > 5) {
            throw new IllegalStateException("CodeScannerView can have zero or one child");
        }
        measureChildWithMargins(this.mPreviewView, i, 0, i2, 0);
        measureChildWithMargins(this.mViewFinderView, i, 0, i2, 0);
        measureChildWithMargins(this.mAutoFocusButton, i, 0, i2, 0);
        measureChildWithMargins(this.mFlashButton, i, 0, i2, 0);
        if (childCount == 5) {
            Rect frameRect = this.mViewFinderView.getFrameRect();
            measureChildWithMargins(getChildAt(4), i, 0, i2, frameRect != null ? frameRect.getBottom() : 0);
        }
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), i2));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int childCount = getChildCount();
        if (childCount > 5) {
            throw new IllegalStateException("CodeScannerView can have zero or one child");
        }
        int i9 = i3 - i;
        int i10 = i4 - i2;
        Point point = this.mPreviewSize;
        if (point == null) {
            this.mPreviewView.layout(0, 0, i9, i10);
        } else {
            int x = point.getX();
            if (x > i9) {
                int i11 = (x - i9) / 2;
                i6 = 0 - i11;
                i5 = i11 + i9;
            } else {
                i5 = i9;
                i6 = 0;
            }
            int y = point.getY();
            if (y > i10) {
                int i12 = (y - i10) / 2;
                i8 = 0 - i12;
                i7 = i12 + i10;
            } else {
                i7 = i10;
                i8 = 0;
            }
            this.mPreviewView.layout(i6, i8, i5, i7);
        }
        this.mViewFinderView.layout(0, 0, i9, i10);
        layoutButton(this.mAutoFocusButton, this.mAutoFocusButtonPosition, i9, i10);
        layoutButton(this.mFlashButton, this.mFlashButtonPosition, i9, i10);
        if (childCount == 5) {
            Rect frameRect = this.mViewFinderView.getFrameRect();
            int bottom = frameRect != null ? frameRect.getBottom() : 0;
            View childAt = getChildAt(4);
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i13 = paddingLeft + layoutParams.leftMargin;
                int i14 = paddingTop + layoutParams.topMargin + bottom;
                childAt.layout(i13, i14, childAt.getMeasuredWidth() + i13, childAt.getMeasuredHeight() + i14);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        SizeListener sizeListener = this.mSizeListener;
        if (sizeListener != null) {
            sizeListener.onSizeChanged(i, i2);
        }
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        CodeScanner codeScanner = this.mCodeScanner;
        Rect frameRect = getFrameRect();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (codeScanner != null && frameRect != null && codeScanner.isAutoFocusSupportedOrUnknown() && codeScanner.isTouchFocusEnabled() && motionEvent.getAction() == 0 && frameRect.isPointInside(x, y)) {
            int i = this.mFocusAreaSize;
            codeScanner.performTouchFocus(new Rect(x - i, y - i, x + i, y + i).fitIn(frameRect));
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(@Nullable ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    @NonNull
    public ViewGroup.LayoutParams generateLayoutParams(@Nullable AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    @NonNull
    protected ViewGroup.LayoutParams generateLayoutParams(@NonNull ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    @NonNull
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @ColorInt
    public int getMaskColor() {
        return this.mViewFinderView.getMaskColor();
    }

    public void setMaskColor(@ColorInt int i) {
        this.mViewFinderView.setMaskColor(i);
    }

    public boolean isMaskVisible() {
        return this.mViewFinderView.isMaskVisible();
    }

    public void setMaskVisible(boolean z) {
        this.mViewFinderView.setMaskVisible(z);
    }

    @ColorInt
    public int getFrameColor() {
        return this.mViewFinderView.getFrameColor();
    }

    public void setFrameColor(@ColorInt int i) {
        this.mViewFinderView.setFrameColor(i);
    }

    public boolean isFrameVisible() {
        return this.mViewFinderView.isFrameVisible();
    }

    public void setFrameVisible(boolean z) {
        this.mViewFinderView.setFrameVisible(z);
    }

    @Px
    public int getFrameThickness() {
        return this.mViewFinderView.getFrameThickness();
    }

    public void setFrameThickness(@Px int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Frame thickness can't be negative");
        }
        this.mViewFinderView.setFrameThickness(i);
    }

    @Px
    public int getFrameCornersSize() {
        return this.mViewFinderView.getFrameCornersSize();
    }

    public void setFrameCornersSize(@Px int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Frame corners size can't be negative");
        }
        this.mViewFinderView.setFrameCornersSize(i);
    }

    @Px
    public int getFrameCornersRadius() {
        return this.mViewFinderView.getFrameCornersRadius();
    }

    public void setFrameCornersRadius(@Px int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Frame corners radius can't be negative");
        }
        this.mViewFinderView.setFrameCornersRadius(i);
    }

    public boolean isFrameCornersCapRounded() {
        return this.mViewFinderView.isFrameCornersCapRounded();
    }

    public void setFrameCornersCapRounded(boolean z) {
        this.mViewFinderView.setFrameCornersCapRounded(z);
    }

    @FloatRange(from = 0.1d, to = Contrast.RATIO_MIN)
    public float getFrameSize() {
        return this.mViewFinderView.getFrameSize();
    }

    public void setFrameSize(@FloatRange(from = 0.1d, to = 1.0d) float f) {
        if (f < 0.1d || f > 1.0f) {
            throw new IllegalArgumentException("Max frame size value should be between 0.1 and 1, inclusive");
        }
        this.mViewFinderView.setFrameSize(f);
    }

    @FloatRange(from = 0.0d, to = Contrast.RATIO_MIN)
    public float getFrameVerticalBias() {
        return this.mViewFinderView.getFrameVerticalBias();
    }

    public void setFrameVerticalBias(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Max frame size value should be between 0 and 1, inclusive");
        }
        this.mViewFinderView.setFrameVerticalBias(f);
    }

    @FloatRange(from = 0.0d, fromInclusive = false)
    public float getFrameAspectRatioWidth() {
        return this.mViewFinderView.getFrameAspectRatioWidth();
    }

    public void setFrameAspectRatioWidth(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Frame aspect ratio values should be greater than zero");
        }
        this.mViewFinderView.setFrameAspectRatioWidth(f);
    }

    @FloatRange(from = 0.0d, fromInclusive = false)
    public float getFrameAspectRatioHeight() {
        return this.mViewFinderView.getFrameAspectRatioHeight();
    }

    public void setFrameAspectRatioHeight(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Frame aspect ratio values should be greater than zero");
        }
        this.mViewFinderView.setFrameAspectRatioHeight(f);
    }

    public void setFrameAspectRatio(@FloatRange(from = 0.0d, fromInclusive = false) float f, @FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        if (f <= 0.0f || f2 <= 0.0f) {
            throw new IllegalArgumentException("Frame aspect ratio values should be greater than zero");
        }
        this.mViewFinderView.setFrameAspectRatio(f, f2);
    }

    public boolean isAutoFocusButtonVisible() {
        return this.mAutoFocusButton.getVisibility() == 0;
    }

    public void setAutoFocusButtonVisible(boolean z) {
        this.mAutoFocusButton.setVisibility(z ? 0 : 4);
    }

    @ColorInt
    public int getAutoFocusButtonColor() {
        return this.mAutoFocusButtonColor;
    }

    public void setAutoFocusButtonColor(@ColorInt int i) {
        this.mAutoFocusButtonColor = i;
        this.mAutoFocusButton.setColorFilter(i);
    }

    @NonNull
    public ButtonPosition getAutoFocusButtonPosition() {
        return this.mAutoFocusButtonPosition;
    }

    public void setAutoFocusButtonPosition(@NonNull ButtonPosition buttonPosition) {
        buttonPosition.getClass();
        boolean z = buttonPosition != this.mAutoFocusButtonPosition;
        this.mAutoFocusButtonPosition = buttonPosition;
        if (z && isLaidOut()) {
            requestLayout();
        }
    }

    @Px
    public int getAutoFocusButtonPaddingHorizontal() {
        return this.mAutoFocusButtonPaddingHorizontal;
    }

    public void setAutoFocusButtonPaddingHorizontal(@Px int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Padding should be equal to or grater then zero");
        }
        boolean z = i != this.mAutoFocusButtonPaddingHorizontal;
        this.mAutoFocusButtonPaddingHorizontal = i;
        if (z) {
            invalidateAutoFocusButtonPadding();
        }
    }

    @Px
    public int getAutoFocusButtonPaddingVertical() {
        return this.mAutoFocusButtonPaddingVertical;
    }

    public void setAutoFocusButtonPaddingVertical(@Px int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Padding should be equal to or grater then zero");
        }
        boolean z = i != this.mAutoFocusButtonPaddingVertical;
        this.mAutoFocusButtonPaddingVertical = i;
        if (z) {
            invalidateAutoFocusButtonPadding();
        }
    }

    public boolean isFlashButtonVisible() {
        return this.mFlashButton.getVisibility() == 0;
    }

    @NonNull
    public Drawable getAutoFocusButtonOnIcon() {
        return this.mAutoFocusButtonOnIcon;
    }

    public void setAutoFocusButtonOnIcon(@NonNull Drawable drawable) {
        drawable.getClass();
        boolean z = drawable != this.mAutoFocusButtonOnIcon;
        this.mAutoFocusButtonOnIcon = drawable;
        CodeScanner codeScanner = this.mCodeScanner;
        if (!z || codeScanner == null) {
            return;
        }
        setAutoFocusEnabled(codeScanner.isAutoFocusEnabled());
    }

    @NonNull
    public Drawable getAutoFocusButtonOffIcon() {
        return this.mAutoFocusButtonOffIcon;
    }

    public void setAutoFocusButtonOffIcon(@NonNull Drawable drawable) {
        drawable.getClass();
        boolean z = drawable != this.mAutoFocusButtonOffIcon;
        this.mAutoFocusButtonOffIcon = drawable;
        CodeScanner codeScanner = this.mCodeScanner;
        if (!z || codeScanner == null) {
            return;
        }
        setAutoFocusEnabled(codeScanner.isAutoFocusEnabled());
    }

    public void setFlashButtonVisible(boolean z) {
        this.mFlashButton.setVisibility(z ? 0 : 4);
    }

    @ColorInt
    public int getFlashButtonColor() {
        return this.mFlashButtonColor;
    }

    public void setFlashButtonColor(@ColorInt int i) {
        this.mFlashButtonColor = i;
        this.mFlashButton.setColorFilter(i);
    }

    @NonNull
    public ButtonPosition getFlashButtonPosition() {
        return this.mFlashButtonPosition;
    }

    public void setFlashButtonPosition(@NonNull ButtonPosition buttonPosition) {
        buttonPosition.getClass();
        boolean z = buttonPosition != this.mFlashButtonPosition;
        this.mFlashButtonPosition = buttonPosition;
        if (z) {
            requestLayout();
        }
    }

    @Px
    public int getFlashButtonPaddingHorizontal() {
        return this.mFlashButtonPaddingHorizontal;
    }

    public void setFlashButtonPaddingHorizontal(@Px int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Padding should be equal to or grater then zero");
        }
        boolean z = i != this.mFlashButtonPaddingHorizontal;
        this.mFlashButtonPaddingHorizontal = i;
        if (z) {
            invalidateFlashButtonPadding();
        }
    }

    @Px
    public int getFlashButtonPaddingVertical() {
        return this.mFlashButtonPaddingVertical;
    }

    public void setFlashButtonPaddingVertical(@Px int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Padding should be equal to or grater then zero");
        }
        boolean z = i != this.mFlashButtonPaddingVertical;
        this.mFlashButtonPaddingVertical = i;
        if (z) {
            invalidateFlashButtonPadding();
        }
    }

    @NonNull
    public Drawable getFlashButtonOnIcon() {
        return this.mFlashButtonOnIcon;
    }

    public void setFlashButtonOnIcon(@NonNull Drawable drawable) {
        drawable.getClass();
        boolean z = drawable != this.mFlashButtonOnIcon;
        this.mFlashButtonOnIcon = drawable;
        CodeScanner codeScanner = this.mCodeScanner;
        if (!z || codeScanner == null) {
            return;
        }
        setFlashEnabled(codeScanner.isFlashEnabled());
    }

    @NonNull
    public Drawable getFlashButtonOffIcon() {
        return this.mFlashButtonOffIcon;
    }

    public void setFlashButtonOffIcon(@NonNull Drawable drawable) {
        drawable.getClass();
        boolean z = drawable != this.mFlashButtonOffIcon;
        this.mFlashButtonOffIcon = drawable;
        CodeScanner codeScanner = this.mCodeScanner;
        if (!z || codeScanner == null) {
            return;
        }
        setFlashEnabled(codeScanner.isFlashEnabled());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public SurfaceView getPreviewView() {
        return this.mPreviewView;
    }

    @NonNull
    ViewFinderView getViewFinderView() {
        return this.mViewFinderView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Rect getFrameRect() {
        return this.mViewFinderView.getFrameRect();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPreviewSize(@Nullable Point point) {
        this.mPreviewSize = point;
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSizeListener(@Nullable SizeListener sizeListener) {
        this.mSizeListener = sizeListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCodeScanner(@NonNull CodeScanner codeScanner) {
        if (this.mCodeScanner != null) {
            throw new IllegalStateException("Code scanner has already been set");
        }
        this.mCodeScanner = codeScanner;
        setAutoFocusEnabled(codeScanner.isAutoFocusEnabled());
        setFlashEnabled(codeScanner.isFlashEnabled());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAutoFocusEnabled(boolean z) {
        this.mAutoFocusButton.setImageDrawable(z ? this.mAutoFocusButtonOnIcon : this.mAutoFocusButtonOffIcon);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFlashEnabled(boolean z) {
        this.mFlashButton.setImageDrawable(z ? this.mFlashButtonOnIcon : this.mFlashButtonOffIcon);
    }

    private void layoutButton(View view, ButtonPosition buttonPosition, int i, int i2) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int layoutDirection = getLayoutDirection();
        int i3 = AnonymousClass1.$SwitchMap$com$budiyev$android$codescanner$ButtonPosition[buttonPosition.ordinal()];
        if (i3 == 1) {
            if (layoutDirection == 1) {
                view.layout(i - measuredWidth, 0, i, measuredHeight);
            } else {
                view.layout(0, 0, measuredWidth, measuredHeight);
            }
        } else if (i3 == 2) {
            if (layoutDirection == 1) {
                view.layout(0, 0, measuredWidth, measuredHeight);
            } else {
                view.layout(i - measuredWidth, 0, i, measuredHeight);
            }
        } else if (i3 == 3) {
            if (layoutDirection == 1) {
                view.layout(i - measuredWidth, i2 - measuredHeight, i, i2);
            } else {
                view.layout(0, i2 - measuredHeight, measuredWidth, i2);
            }
        } else if (i3 != 4) {
        } else {
            if (layoutDirection == 1) {
                view.layout(0, i2 - measuredHeight, measuredWidth, i2);
            } else {
                view.layout(i - measuredWidth, i2 - measuredHeight, i, i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.budiyev.android.codescanner.CodeScannerView$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$budiyev$android$codescanner$ButtonPosition;

        static {
            int[] iArr = new int[ButtonPosition.values().length];
            $SwitchMap$com$budiyev$android$codescanner$ButtonPosition = iArr;
            try {
                iArr[ButtonPosition.TOP_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$budiyev$android$codescanner$ButtonPosition[ButtonPosition.TOP_END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$budiyev$android$codescanner$ButtonPosition[ButtonPosition.BOTTOM_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$budiyev$android$codescanner$ButtonPosition[ButtonPosition.BOTTOM_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void invalidateAutoFocusButtonPadding() {
        int i = this.mAutoFocusButtonPaddingHorizontal;
        int i2 = this.mAutoFocusButtonPaddingVertical;
        this.mAutoFocusButton.setPadding(i, i2, i, i2);
    }

    private void invalidateFlashButtonPadding() {
        int i = this.mFlashButtonPaddingHorizontal;
        int i2 = this.mFlashButtonPaddingVertical;
        this.mFlashButton.setPadding(i, i2, i, i2);
    }

    @NonNull
    private static ButtonPosition buttonPositionFromAttr(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return ButtonPosition.BOTTOM_END;
                }
                return ButtonPosition.TOP_START;
            }
            return ButtonPosition.BOTTOM_START;
        }
        return ButtonPosition.TOP_END;
    }

    private static int indexOfButtonPosition(@NonNull ButtonPosition buttonPosition) {
        int i = AnonymousClass1.$SwitchMap$com$budiyev$android$codescanner$ButtonPosition[buttonPosition.ordinal()];
        if (i != 2) {
            if (i != 3) {
                return i != 4 ? 0 : 3;
            }
            return 2;
        }
        return 1;
    }

    /* loaded from: classes2.dex */
    public static final class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(@NonNull ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class AutoFocusClickListener implements View.OnClickListener {
        private AutoFocusClickListener() {
        }

        /* synthetic */ AutoFocusClickListener(CodeScannerView codeScannerView, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CodeScanner codeScanner = CodeScannerView.this.mCodeScanner;
            if (codeScanner == null || !codeScanner.isAutoFocusSupportedOrUnknown()) {
                return;
            }
            boolean z = !codeScanner.isAutoFocusEnabled();
            codeScanner.setAutoFocusEnabled(z);
            CodeScannerView.this.setAutoFocusEnabled(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class FlashClickListener implements View.OnClickListener {
        private FlashClickListener() {
        }

        /* synthetic */ FlashClickListener(CodeScannerView codeScannerView, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CodeScanner codeScanner = CodeScannerView.this.mCodeScanner;
            if (codeScanner == null || !codeScanner.isFlashSupportedOrUnknown()) {
                return;
            }
            boolean z = !codeScanner.isFlashEnabled();
            codeScanner.setFlashEnabled(z);
            CodeScannerView.this.setFlashEnabled(z);
        }
    }
}
