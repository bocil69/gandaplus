package com.termfu.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import androidx.drawerlayout.widget.DrawerLayout;
import com.fufufu.katrina.backup.R;
import com.termfu.app.ExtraKeysView;
import com.termfu.view.TerminalView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
/* loaded from: classes6.dex */
public final class ExtraKeysView extends GridLayout {
    private static final int BUTTON_COLOR = 0;
    private static final int BUTTON_PRESSED_COLOR = -8421505;
    private static final int INTERESTING_COLOR = -8331542;
    private static final int TEXT_COLOR = -1;
    static final Map<String, Integer> keyCodesForString = new HashMap<String, Integer>() { // from class: com.termfu.app.ExtraKeysView.1
        {
            put("SPACE", 62);
            put("ESC", 111);
            put("TAB", 61);
            put("HOME", 122);
            put("END", 123);
            put("PGUP", 92);
            put("PGDN", 93);
            put("INS", 124);
            put("DEL", 112);
            put("BKSP", 67);
            put("UP", 19);
            put("LEFT", 21);
            put("RIGHT", 22);
            put("DOWN", 20);
            put("ENTER", 66);
            put("F1", 131);
            put("F2", 132);
            put("F3", 133);
            put("F4", 134);
            put("F5", 135);
            put("F6", 136);
            put("F7", 137);
            put("F8", 138);
            put("F9", 139);
            put("F10", 140);
            put("F11", 141);
            put("F12", 142);
        }
    };
    private int longPressCount;
    private PopupWindow popupWindow;
    private ScheduledExecutorService scheduledExecutor;
    private final Map<SpecialButton, SpecialButtonState> specialButtons;
    private final Set<String> specialButtonsKeys;

    /* loaded from: classes6.dex */
    public enum SpecialButton {
        CTRL,
        ALT,
        FN;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static SpecialButton[] valuesCustom() {
            SpecialButton[] valuesCustom = values();
            int length = valuesCustom.length;
            SpecialButton[] specialButtonArr = new SpecialButton[length];
            System.arraycopy(valuesCustom, 0, specialButtonArr, 0, length);
            return specialButtonArr;
        }
    }

    public ExtraKeysView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        HashMap<SpecialButton, SpecialButtonState> hashMap = new HashMap<SpecialButton, SpecialButtonState>() { // from class: com.termfu.app.ExtraKeysView.2
            {
                put(SpecialButton.CTRL, new SpecialButtonState(null));
                put(SpecialButton.ALT, new SpecialButtonState(null));
                put(SpecialButton.FN, new SpecialButtonState(null));
            }
        };
        this.specialButtons = hashMap;
        this.specialButtonsKeys = (Set) hashMap.keySet().stream().map(new Function() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ExtraKeysView.SpecialButton) obj).name();
            }
        }).collect(Collectors.toSet());
    }

    private void sendKey(View view, String str, final boolean z, final boolean z2) {
        final TerminalView terminalView = (TerminalView) view.findViewById(R.id.admsoloraya_res_0x7f0a0486);
        if ("KEYBOARD".equals(str)) {
            ((InputMethodManager) getContext().getSystemService("input_method")).toggleSoftInput(0, 0);
        } else if ("DRAWER".equals(str)) {
            ((DrawerLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a016d)).openDrawer(3);
        } else {
            Map<String, Integer> map = keyCodesForString;
            if (map.containsKey(str)) {
                int intValue = map.get(str).intValue();
                int i = z ? 12288 : 0;
                terminalView.onKeyDown(intValue, new KeyEvent(0L, 0L, 1, intValue, 0, z2 ? i | 18 : i));
                return;
            }
            str.codePoints().forEach(new IntConsumer() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda1
                @Override // java.util.function.IntConsumer
                public final void accept(int i2) {
                    TerminalView.this.inputCodePoint(i2, z, z2);
                }
            });
        }
    }

    private void sendKey(View view, ExtraKeyButton extraKeyButton) {
        if (extraKeyButton.isMacro()) {
            String[] split = extraKeyButton.getKey().split(" ");
            boolean z = false;
            boolean z2 = false;
            for (String str : split) {
                if ("CTRL".equals(str)) {
                    z = true;
                } else if ("ALT".equals(str)) {
                    z2 = true;
                } else {
                    sendKey(view, str, z, z2);
                    z = false;
                    z2 = false;
                }
            }
            return;
        }
        sendKey(view, extraKeyButton.getKey(), false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SpecialButtonState {
        List<Button> buttons;
        boolean isActive;
        boolean isOn;

        private SpecialButtonState() {
            this.isOn = false;
            this.isActive = false;
            this.buttons = new ArrayList();
        }

        /* synthetic */ SpecialButtonState(SpecialButtonState specialButtonState) {
            this();
        }

        void setIsActive(final boolean z) {
            this.isActive = z;
            this.buttons.forEach(new Consumer() { // from class: com.termfu.app.ExtraKeysView$SpecialButtonState$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    boolean z2 = z;
                    ((Button) obj).setTextColor(r0 ? ExtraKeysView.INTERESTING_COLOR : -1);
                }
            });
        }
    }

    private boolean isSpecialButton(ExtraKeyButton extraKeyButton) {
        return this.specialButtonsKeys.contains(extraKeyButton.getKey());
    }

    public boolean readSpecialButton(SpecialButton specialButton) {
        SpecialButtonState specialButtonState = this.specialButtons.get(specialButton);
        if (specialButtonState == null) {
            throw new RuntimeException("Must be a valid special button (see source)");
        }
        if (specialButtonState.isOn && specialButtonState.isActive) {
            specialButtonState.setIsActive(false);
            return true;
        }
        return false;
    }

    private Button createSpecialButton(String str, boolean z) {
        SpecialButtonState specialButtonState = this.specialButtons.get(SpecialButton.valueOf(str));
        specialButtonState.isOn = true;
        Button button = new Button(getContext(), null, 16843567);
        button.setTextColor(specialButtonState.isActive ? INTERESTING_COLOR : -1);
        if (z) {
            specialButtonState.buttons.add(button);
        }
        return button;
    }

    void popup(View view, ExtraKeyButton extraKeyButton) {
        Button button;
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (isSpecialButton(extraKeyButton)) {
            button = createSpecialButton(extraKeyButton.getKey(), false);
        } else {
            button = new Button(getContext(), null, 16843567);
            button.setTextColor(-1);
        }
        button.setText(extraKeyButton.getDisplay());
        button.setPadding(0, 0, 0, 0);
        button.setMinHeight(0);
        button.setMinWidth(0);
        button.setMinimumWidth(0);
        button.setMinimumHeight(0);
        button.setWidth(measuredWidth);
        button.setHeight(measuredHeight);
        button.setBackgroundColor(BUTTON_PRESSED_COLOR);
        PopupWindow popupWindow = new PopupWindow(this);
        this.popupWindow = popupWindow;
        popupWindow.setWidth(-2);
        this.popupWindow.setHeight(-2);
        this.popupWindow.setContentView(button);
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setFocusable(false);
        this.popupWindow.showAsDropDown(view, 0, measuredHeight * (-2));
    }

    static int maximumLength(Object[][] objArr) {
        int i = 0;
        for (Object[] objArr2 : objArr) {
            i = Math.max(i, objArr2.length);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"ClickableViewAccessibility"})
    public void reload(ExtraKeysInfos extraKeysInfos) {
        final Button button;
        if (extraKeysInfos == null) {
            return;
        }
        for (SpecialButtonState specialButtonState : this.specialButtons.values()) {
            specialButtonState.buttons = new ArrayList();
        }
        removeAllViews();
        ExtraKeyButton[][] matrix = extraKeysInfos.getMatrix();
        setRowCount(matrix.length);
        setColumnCount(maximumLength(matrix));
        for (int i = 0; i < matrix.length; i++) {
            int i2 = 0;
            while (true) {
                ExtraKeyButton[] extraKeyButtonArr = matrix[i];
                if (i2 >= extraKeyButtonArr.length) {
                    break;
                }
                final ExtraKeyButton extraKeyButton = extraKeyButtonArr[i2];
                if (isSpecialButton(extraKeyButton)) {
                    button = createSpecialButton(extraKeyButton.getKey(), true);
                } else {
                    button = new Button(getContext(), null, 16843567);
                }
                button.setText(extraKeyButton.getDisplay());
                button.setTextColor(-1);
                button.setPadding(0, 0, 0, 0);
                button.setOnClickListener(new View.OnClickListener() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ExtraKeysView.this.m102lambda$2$comtermfuappExtraKeysView(button, extraKeyButton, view);
                    }
                });
                button.setOnTouchListener(new View.OnTouchListener() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        return ExtraKeysView.this.m103lambda$3$comtermfuappExtraKeysView(extraKeyButton, view, motionEvent);
                    }
                });
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = 0;
                layoutParams.height = 0;
                layoutParams.setMargins(0, 0, 0, 0);
                layoutParams.columnSpec = GridLayout.spec(i2, GridLayout.FILL, 1.0f);
                layoutParams.rowSpec = GridLayout.spec(i, GridLayout.FILL, 1.0f);
                button.setLayoutParams(layoutParams);
                addView(button);
                i2++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$2$com-termfu-app-ExtraKeysView  reason: not valid java name */
    public /* synthetic */ void m102lambda$2$comtermfuappExtraKeysView(Button button, ExtraKeyButton extraKeyButton, View view) {
        if (Settings.System.getInt(getContext().getContentResolver(), "haptic_feedback_enabled", 0) != 0) {
            if (Build.VERSION.SDK_INT >= 28) {
                button.performHapticFeedback(3);
            } else if (Settings.Global.getInt(getContext().getContentResolver(), "zen_mode", 0) != 2) {
                button.performHapticFeedback(3);
            }
        }
        View rootView = getRootView();
        if (isSpecialButton(extraKeyButton)) {
            SpecialButtonState specialButtonState = this.specialButtons.get(SpecialButton.valueOf(extraKeyButton.getKey()));
            specialButtonState.setIsActive(!specialButtonState.isActive);
            return;
        }
        sendKey(rootView, extraKeyButton);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$3$com-termfu-app-ExtraKeysView  reason: not valid java name */
    public /* synthetic */ boolean m103lambda$3$comtermfuappExtraKeysView(final ExtraKeyButton extraKeyButton, View view, MotionEvent motionEvent) {
        final View rootView = getRootView();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.longPressCount = 0;
            view.setBackgroundColor(BUTTON_PRESSED_COLOR);
            if (Arrays.asList("UP", "DOWN", "LEFT", "RIGHT", "BKSP", "DEL").contains(extraKeyButton.getKey())) {
                ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                this.scheduledExecutor = newSingleThreadScheduledExecutor;
                newSingleThreadScheduledExecutor.scheduleWithFixedDelay(new Runnable() { // from class: com.termfu.app.ExtraKeysView$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ExtraKeysView.this.m104lambda$4$comtermfuappExtraKeysView(rootView, extraKeyButton);
                    }
                }, 400L, 80L, TimeUnit.MILLISECONDS);
            }
            return true;
        } else if (action == 1) {
            view.setBackgroundColor(0);
            ScheduledExecutorService scheduledExecutorService = this.scheduledExecutor;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
                this.scheduledExecutor = null;
            }
            if (this.longPressCount == 0 || this.popupWindow != null) {
                PopupWindow popupWindow = this.popupWindow;
                if (popupWindow != null) {
                    popupWindow.setContentView(null);
                    this.popupWindow.dismiss();
                    this.popupWindow = null;
                    if (extraKeyButton.getPopup() != null) {
                        if (isSpecialButton(extraKeyButton.getPopup())) {
                            SpecialButtonState specialButtonState = this.specialButtons.get(SpecialButton.valueOf(extraKeyButton.getPopup().getKey()));
                            specialButtonState.setIsActive(!specialButtonState.isActive);
                        } else {
                            sendKey(rootView, extraKeyButton.getPopup());
                        }
                    }
                } else {
                    view.performClick();
                }
            }
            return true;
        } else if (action != 2) {
            if (action != 3) {
                return true;
            }
            view.setBackgroundColor(0);
            ScheduledExecutorService scheduledExecutorService2 = this.scheduledExecutor;
            if (scheduledExecutorService2 != null) {
                scheduledExecutorService2.shutdownNow();
                this.scheduledExecutor = null;
            }
            return true;
        } else {
            if (extraKeyButton.getPopup() != null) {
                if (this.popupWindow == null && motionEvent.getY() < 0.0f) {
                    ScheduledExecutorService scheduledExecutorService3 = this.scheduledExecutor;
                    if (scheduledExecutorService3 != null) {
                        scheduledExecutorService3.shutdownNow();
                        this.scheduledExecutor = null;
                    }
                    view.setBackgroundColor(0);
                    popup(view, extraKeyButton.getPopup());
                }
                if (this.popupWindow != null && motionEvent.getY() > 0.0f) {
                    view.setBackgroundColor(BUTTON_PRESSED_COLOR);
                    this.popupWindow.dismiss();
                    this.popupWindow = null;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$4$com-termfu-app-ExtraKeysView  reason: not valid java name */
    public /* synthetic */ void m104lambda$4$comtermfuappExtraKeysView(View view, ExtraKeyButton extraKeyButton) {
        this.longPressCount++;
        sendKey(view, extraKeyButton);
    }
}
