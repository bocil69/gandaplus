package com.termfu.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.autofill.AutofillManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.fufufu.katrina.backup.R;
import com.termfu.app.DialogUtils;
import com.termfu.app.TermuxActivity;
import com.termfu.app.TermuxService;
import com.termfu.view.TerminalView;
import com.termux.terminal.EmulatorDebug;
import com.termux.terminal.TerminalColors;
import com.termux.terminal.TerminalSession;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes6.dex */
public final class TermuxActivity extends Activity implements ServiceConnection {
    private static final String BROADCAST_TERMUX_OPENED = "com.termfu.app.OPENED";
    private static final int CONTEXTMENU_AUTOFILL_ID = 10;
    private static final int CONTEXTMENU_HELP_ID = 8;
    private static final int CONTEXTMENU_KILL_PROCESS_ID = 4;
    private static final int CONTEXTMENU_PASTE_ID = 3;
    private static final int CONTEXTMENU_RESET_TERMINAL_ID = 5;
    private static final int CONTEXTMENU_SELECT_URL_ID = 0;
    private static final int CONTEXTMENU_SHARE_TRANSCRIPT_ID = 1;
    private static final int CONTEXTMENU_STYLING_ID = 6;
    private static final int CONTEXTMENU_TOGGLE_KEEP_SCREEN_ON = 9;
    private static final int MAX_SESSIONS = 8;
    private static final String RELOAD_STYLE_ACTION = "com.termfu.app.reload_style";
    private static final int REQUESTCODE_PERMISSION_STORAGE = 1234;
    public static final String TERMUX_FAILSAFE_SESSION_ACTION = "com.termfu.app.failsafe_session";
    int mBellSoundId;
    final SoundPool mBellSoundPool = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(new AudioAttributes.Builder().setContentType(4).setUsage(13).build()).build();
    private final BroadcastReceiver mBroadcastReceiever = new BroadcastReceiver() { // from class: com.termfu.app.TermuxActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (TermuxActivity.this.mIsVisible) {
                if ("storage".equals(intent.getStringExtra(TermuxActivity.RELOAD_STYLE_ACTION))) {
                    if (TermuxActivity.this.ensureStoragePermissionGranted()) {
                        TermuxInstaller.setupStorageSymlinks(TermuxActivity.this);
                        return;
                    }
                    return;
                }
                TermuxActivity.this.checkForFontAndColors();
                TermuxActivity.this.mSettings.reloadFromProperties(TermuxActivity.this);
                if (TermuxActivity.this.mExtraKeysView != null) {
                    TermuxActivity.this.mExtraKeysView.reload(TermuxActivity.this.mSettings.mExtraKeys);
                }
            }
        }
    };
    ExtraKeysView mExtraKeysView;
    boolean mIsUsingBlackUI;
    boolean mIsVisible;
    Toast mLastToast;
    ArrayAdapter<TerminalSession> mListViewAdapter;
    int mNavBarHeight;
    TermuxPreferences mSettings;
    TermuxService mTermService;
    @NonNull
    TerminalView mTerminalView;

    void checkForFontAndColors() {
        try {
            File file = new File("/data/data/com.fufufu.katrina.backup/files/home/.termfu/font.ttf");
            File file2 = new File("/data/data/com.fufufu.katrina.backup/files/home/.termfu/colors.properties");
            Properties properties = new Properties();
            if (file2.isFile()) {
                FileInputStream fileInputStream = new FileInputStream(file2);
                try {
                    properties.load(fileInputStream);
                    fileInputStream.close();
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
            }
            TerminalColors.COLOR_SCHEME.updateWith(properties);
            TerminalSession currentTermSession = getCurrentTermSession();
            if (currentTermSession != null && currentTermSession.getEmulator() != null) {
                currentTermSession.getEmulator().mColors.reset();
            }
            updateBackgroundColor();
            this.mTerminalView.setTypeface((!file.exists() || file.length() <= 0) ? Typeface.MONOSPACE : Typeface.createFromFile(file));
        } catch (Exception e) {
            Log.e(EmulatorDebug.LOG_TAG, "Error in checkForFontAndColors()", e);
        }
    }

    void updateBackgroundColor() {
        TerminalSession currentTermSession = getCurrentTermSession();
        if (currentTermSession == null || currentTermSession.getEmulator() == null) {
            return;
        }
        getWindow().getDecorView().setBackgroundColor(currentTermSession.getEmulator().mColors.mCurrentColors[257]);
    }

    public boolean ensureStoragePermissionGranted() {
        if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, REQUESTCODE_PERMISSION_STORAGE);
        return false;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        TermuxPreferences termuxPreferences = new TermuxPreferences(this);
        this.mSettings = termuxPreferences;
        boolean isUsingBlackUI = termuxPreferences.isUsingBlackUI();
        this.mIsUsingBlackUI = isUsingBlackUI;
        if (isUsingBlackUI) {
            setTheme(R.style.admsoloraya_res_0x7f130278);
        } else {
            setTheme(R.style.admsoloraya_res_0x7f130277);
        }
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d00c3);
        findViewById(16908290).setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                return TermuxActivity.this.m105lambda$0$comtermfuappTermuxActivity(view, windowInsets);
            }
        });
        if (this.mSettings.isUsingFullScreen()) {
            getWindow().addFlags(1024);
        }
        if (this.mIsUsingBlackUI) {
            findViewById(R.id.admsoloraya_res_0x7f0a023e).setBackgroundColor(getResources().getColor(17170446));
        }
        TerminalView terminalView = (TerminalView) findViewById(R.id.admsoloraya_res_0x7f0a0486);
        this.mTerminalView = terminalView;
        terminalView.setOnKeyListener(new TermuxViewClient(this));
        this.mTerminalView.setTextSize(this.mSettings.getFontSize());
        this.mTerminalView.setKeepScreenOn(this.mSettings.isScreenAlwaysOn());
        this.mTerminalView.requestFocus();
        final ViewPager viewPager = (ViewPager) findViewById(R.id.admsoloraya_res_0x7f0a05ba);
        if (this.mSettings.mShowExtraKeys) {
            viewPager.setVisibility(0);
        }
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        layoutParams.height *= this.mSettings.mExtraKeys == null ? 0 : this.mSettings.mExtraKeys.getMatrix().length;
        viewPager.setLayoutParams(layoutParams);
        viewPager.setAdapter(new AnonymousClass2());
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.termfu.app.TermuxActivity.3
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (i == 0) {
                    TermuxActivity.this.mTerminalView.requestFocus();
                    return;
                }
                EditText editText = (EditText) viewPager.findViewById(R.id.admsoloraya_res_0x7f0a048e);
                if (editText != null) {
                    editText.requestFocus();
                }
            }
        });
        View findViewById = findViewById(R.id.admsoloraya_res_0x7f0a03d0);
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TermuxActivity.this.m106lambda$1$comtermfuappTermuxActivity(view);
            }
        });
        findViewById.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return TermuxActivity.this.m113lambda$2$comtermfuappTermuxActivity(view);
            }
        });
        findViewById(R.id.admsoloraya_res_0x7f0a04ae).setOnClickListener(new View.OnClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TermuxActivity.this.m116lambda$5$comtermfuappTermuxActivity(view);
            }
        });
        findViewById(R.id.admsoloraya_res_0x7f0a04ae).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return TermuxActivity.this.m117lambda$6$comtermfuappTermuxActivity(view);
            }
        });
        registerForContextMenu(this.mTerminalView);
        Intent intent = new Intent(this, TermuxService.class);
        startService(intent);
        if (!bindService(intent, this, 0)) {
            throw new RuntimeException("bindService() failed");
        }
        checkForFontAndColors();
        this.mBellSoundId = this.mBellSoundPool.load(this, R.raw.admsoloraya, 1);
        sendOpenedBroadcast();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$0$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ WindowInsets m105lambda$0$comtermfuappTermuxActivity(View view, WindowInsets windowInsets) {
        this.mNavBarHeight = windowInsets.getSystemWindowInsetBottom();
        return windowInsets;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.termfu.app.TermuxActivity$2  reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends PagerAdapter {
        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 2;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        AnonymousClass2() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        @NonNull
        public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
            ExtraKeysView extraKeysView;
            LayoutInflater from = LayoutInflater.from(TermuxActivity.this);
            if (i == 0) {
                TermuxActivity termuxActivity = TermuxActivity.this;
                ExtraKeysView extraKeysView2 = (ExtraKeysView) from.inflate(R.layout.admsoloraya_res_0x7f0d00c4, viewGroup, false);
                termuxActivity.mExtraKeysView = extraKeysView2;
                TermuxActivity.this.mExtraKeysView.reload(TermuxActivity.this.mSettings.mExtraKeys);
                extraKeysView = extraKeysView2;
                if (TermuxActivity.this.mSettings.isUsingFullScreen()) {
                    extraKeysView = extraKeysView2;
                    if (TermuxActivity.this.mSettings.isUsingFullScreenWorkAround()) {
                        FullScreenWorkAround.apply(TermuxActivity.this);
                        extraKeysView = extraKeysView2;
                    }
                }
            } else {
                View inflate = from.inflate(R.layout.admsoloraya_res_0x7f0d00c5, viewGroup, false);
                final EditText editText = (EditText) inflate.findViewById(R.id.admsoloraya_res_0x7f0a048e);
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.termfu.app.TermuxActivity$2$$ExternalSyntheticLambda0
                    @Override // android.widget.TextView.OnEditorActionListener
                    public final boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                        return TermuxActivity.AnonymousClass2.this.m121lambda$0$comtermfuappTermuxActivity$2(editText, textView, i2, keyEvent);
                    }
                });
                extraKeysView = inflate;
            }
            viewGroup.addView(extraKeysView);
            return extraKeysView;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$0$com-termfu-app-TermuxActivity$2  reason: not valid java name */
        public /* synthetic */ boolean m121lambda$0$comtermfuappTermuxActivity$2(EditText editText, TextView textView, int i, KeyEvent keyEvent) {
            TerminalSession currentTermSession = TermuxActivity.this.getCurrentTermSession();
            if (currentTermSession != null) {
                if (currentTermSession.isRunning()) {
                    String obj = editText.getText().toString();
                    if (obj.length() == 0) {
                        obj = "\r";
                    }
                    currentTermSession.write(obj);
                } else {
                    TermuxActivity.this.removeFinishedSession(currentTermSession);
                }
                editText.setText("");
                return true;
            }
            return true;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$1$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m106lambda$1$comtermfuappTermuxActivity(View view) {
        addNewSession(false, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$2$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ boolean m113lambda$2$comtermfuappTermuxActivity(View view) {
        DialogUtils.textInput(this, R.string.admsoloraya_res_0x7f1200ef, null, R.string.admsoloraya_res_0x7f1200ee, new DialogUtils.TextSetListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda14
            @Override // com.termfu.app.DialogUtils.TextSetListener
            public final void onTextSet(String str) {
                TermuxActivity.this.m114lambda$3$comtermfuappTermuxActivity(str);
            }
        }, R.string.admsoloraya_res_0x7f1200dd, new DialogUtils.TextSetListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda15
            @Override // com.termfu.app.DialogUtils.TextSetListener
            public final void onTextSet(String str) {
                TermuxActivity.this.m115lambda$4$comtermfuappTermuxActivity(str);
            }
        }, -1, null, null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$3$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m114lambda$3$comtermfuappTermuxActivity(String str) {
        addNewSession(false, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$4$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m115lambda$4$comtermfuappTermuxActivity(String str) {
        addNewSession(true, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$5$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m116lambda$5$comtermfuappTermuxActivity(View view) {
        ((InputMethodManager) getSystemService("input_method")).toggleSoftInput(1, 0);
        getDrawer().closeDrawers();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$6$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ boolean m117lambda$6$comtermfuappTermuxActivity(View view) {
        toggleShowExtraKeys();
        return true;
    }

    public int getNavBarHeight() {
        return this.mNavBarHeight;
    }

    void sendOpenedBroadcast() {
        Intent intent = new Intent(BROADCAST_TERMUX_OPENED);
        for (ResolveInfo resolveInfo : getPackageManager().queryBroadcastReceivers(intent, 0)) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            sendBroadcast(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void toggleShowExtraKeys() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.admsoloraya_res_0x7f0a05ba);
        boolean z = this.mSettings.toggleShowExtraKeys(this);
        viewPager.setVisibility(z ? 0 : 8);
        if (z && viewPager.getCurrentItem() == 1) {
            findViewById(R.id.admsoloraya_res_0x7f0a048e).requestFocus();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        TermuxService termuxService = ((TermuxService.LocalBinder) iBinder).service;
        this.mTermService = termuxService;
        termuxService.mSessionChangeCallback = new TerminalSession.SessionChangedCallback() { // from class: com.termfu.app.TermuxActivity.4
            @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
            public void onTextChanged(TerminalSession terminalSession) {
                if (TermuxActivity.this.mIsVisible && TermuxActivity.this.getCurrentTermSession() == terminalSession) {
                    TermuxActivity.this.mTerminalView.onScreenUpdated();
                }
            }

            @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
            public void onTitleChanged(TerminalSession terminalSession) {
                if (TermuxActivity.this.mIsVisible) {
                    if (terminalSession != TermuxActivity.this.getCurrentTermSession()) {
                        TermuxActivity termuxActivity = TermuxActivity.this;
                        termuxActivity.showToast(termuxActivity.toToastTitle(terminalSession), false);
                    }
                    TermuxActivity.this.mListViewAdapter.notifyDataSetChanged();
                }
            }

            @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
            public void onSessionFinished(TerminalSession terminalSession) {
                if (TermuxActivity.this.mTermService.mWantsToStop) {
                    TermuxActivity.this.finish();
                    return;
                }
                if (TermuxActivity.this.mIsVisible && terminalSession != TermuxActivity.this.getCurrentTermSession() && TermuxActivity.this.mTermService.getSessions().indexOf(terminalSession) >= 0) {
                    TermuxActivity termuxActivity = TermuxActivity.this;
                    termuxActivity.showToast(String.valueOf(termuxActivity.toToastTitle(terminalSession)) + " - exited", true);
                }
                if (TermuxActivity.this.getPackageManager().hasSystemFeature("android.software.leanback")) {
                    if (TermuxActivity.this.mTermService.getSessions().size() > 1) {
                        TermuxActivity.this.removeFinishedSession(terminalSession);
                    }
                } else if (terminalSession.getExitStatus() == 0 || terminalSession.getExitStatus() == 130) {
                    TermuxActivity.this.removeFinishedSession(terminalSession);
                }
                TermuxActivity.this.mListViewAdapter.notifyDataSetChanged();
            }

            @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
            public void onClipboardText(TerminalSession terminalSession, String str) {
                if (TermuxActivity.this.mIsVisible) {
                    ((ClipboardManager) TermuxActivity.this.getSystemService("clipboard")).setPrimaryClip(new ClipData(null, new String[]{"text/plain"}, new ClipData.Item(str)));
                }
            }

            @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
            public void onBell(TerminalSession terminalSession) {
                if (TermuxActivity.this.mIsVisible) {
                    int i = TermuxActivity.this.mSettings.mBellBehaviour;
                    if (i == 1) {
                        BellUtil.getInstance(TermuxActivity.this).doBell();
                    } else if (i != 2) {
                    } else {
                        TermuxActivity.this.mBellSoundPool.play(TermuxActivity.this.mBellSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
                    }
                }
            }

            @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
            public void onColorsChanged(TerminalSession terminalSession) {
                if (TermuxActivity.this.getCurrentTermSession() == terminalSession) {
                    TermuxActivity.this.updateBackgroundColor();
                }
            }
        };
        ListView listView = (ListView) findViewById(R.id.admsoloraya_res_0x7f0a023f);
        ArrayAdapter<TerminalSession> arrayAdapter = new ArrayAdapter<TerminalSession>(getApplicationContext(), R.layout.admsoloraya_res_0x7f0d00c6, this.mTermService.getSessions()) { // from class: com.termfu.app.TermuxActivity.5
            final StyleSpan boldSpan = new StyleSpan(1);
            final StyleSpan italicSpan = new StyleSpan(2);

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            @NonNull
            public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
                String str;
                if (view == null) {
                    view = TermuxActivity.this.getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d00c6, viewGroup, false);
                }
                TerminalSession item = getItem(i);
                boolean isRunning = item.isRunning();
                TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a040b);
                if (TermuxActivity.this.mIsUsingBlackUI) {
                    textView.setBackground(TermuxActivity.this.getResources().getDrawable(R.drawable.admsoloraya_res_0x7f0801de));
                }
                String str2 = item.mSessionName;
                String title = item.getTitle();
                String str3 = "[" + (i + 1) + "] ";
                if (TextUtils.isEmpty(str2)) {
                    str2 = "";
                }
                if (!TextUtils.isEmpty(title)) {
                    str = (str2.isEmpty() ? "" : "\n") + title;
                }
                String str4 = String.valueOf(str3) + str2 + str;
                SpannableString spannableString = new SpannableString(str4);
                spannableString.setSpan(this.boldSpan, 0, str3.length() + str2.length(), 33);
                spannableString.setSpan(this.italicSpan, str3.length() + str2.length(), str4.length(), 33);
                textView.setText(spannableString);
                if (isRunning) {
                    textView.setPaintFlags(textView.getPaintFlags() & (-17));
                } else {
                    textView.setPaintFlags(textView.getPaintFlags() | 16);
                }
                int i2 = TermuxActivity.this.mIsUsingBlackUI ? -1 : ViewCompat.MEASURED_STATE_MASK;
                if (!isRunning && item.getExitStatus() != 0) {
                    i2 = SupportMenu.CATEGORY_MASK;
                }
                textView.setTextColor(i2);
                return view;
            }
        };
        this.mListViewAdapter = arrayAdapter;
        listView.setAdapter((ListAdapter) arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda2
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                TermuxActivity.this.m118lambda$7$comtermfuappTermuxActivity(adapterView, view, i, j);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda3
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public final boolean onItemLongClick(AdapterView adapterView, View view, int i, long j) {
                return TermuxActivity.this.m119lambda$8$comtermfuappTermuxActivity(adapterView, view, i, j);
            }
        });
        if (this.mTermService.getSessions().isEmpty()) {
            if (this.mIsVisible) {
                TermuxInstaller.setupIfNeeded(this, new Runnable() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        TermuxActivity.this.m120lambda$9$comtermfuappTermuxActivity();
                    }
                });
                return;
            } else {
                finish();
                return;
            }
        }
        Intent intent = getIntent();
        if (intent != null && "android.intent.action.RUN".equals(intent.getAction())) {
            addNewSession(intent.getBooleanExtra(TERMUX_FAILSAFE_SESSION_ACTION, true), null);
        } else {
            switchToSession(getStoredCurrentSessionOrLast());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$7$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m118lambda$7$comtermfuappTermuxActivity(AdapterView adapterView, View view, int i, long j) {
        switchToSession(this.mListViewAdapter.getItem(i));
        getDrawer().closeDrawers();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$8$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ boolean m119lambda$8$comtermfuappTermuxActivity(AdapterView adapterView, View view, int i, long j) {
        renameSession(this.mListViewAdapter.getItem(i));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$9$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m120lambda$9$comtermfuappTermuxActivity() {
        if (this.mTermService == null) {
            return;
        }
        try {
            Bundle extras = getIntent().getExtras();
            addNewSession(extras != null ? extras.getBoolean(TERMUX_FAILSAFE_SESSION_ACTION, true) : true, null);
        } catch (WindowManager.BadTokenException unused) {
        }
    }

    public void switchToSession(boolean z) {
        int i;
        int indexOf = this.mTermService.getSessions().indexOf(getCurrentTermSession());
        if (z) {
            i = indexOf + 1;
            if (i >= this.mTermService.getSessions().size()) {
                i = 0;
            }
        } else {
            i = indexOf - 1;
            if (i < 0) {
                i = this.mTermService.getSessions().size() - 1;
            }
        }
        switchToSession(this.mTermService.getSessions().get(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"InflateParams"})
    public void renameSession(final TerminalSession terminalSession) {
        DialogUtils.textInput(this, R.string.admsoloraya_res_0x7f1200f1, terminalSession.mSessionName, R.string.admsoloraya_res_0x7f1200f0, new DialogUtils.TextSetListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda1
            @Override // com.termfu.app.DialogUtils.TextSetListener
            public final void onTextSet(String str) {
                TermuxActivity.this.m107lambda$10$comtermfuappTermuxActivity(terminalSession, str);
            }
        }, -1, null, -1, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$10$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m107lambda$10$comtermfuappTermuxActivity(TerminalSession terminalSession, String str) {
        terminalSession.mSessionName = str;
        this.mListViewAdapter.notifyDataSetChanged();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public TerminalSession getCurrentTermSession() {
        return this.mTerminalView.getCurrentSession();
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.mIsVisible = true;
        if (this.mTermService != null) {
            switchToSession(getStoredCurrentSessionOrLast());
            this.mListViewAdapter.notifyDataSetChanged();
        }
        registerReceiver(this.mBroadcastReceiever, new IntentFilter(RELOAD_STYLE_ACTION));
        this.mTerminalView.onScreenUpdated();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mIsVisible = false;
        TerminalSession currentTermSession = getCurrentTermSession();
        if (currentTermSession != null) {
            TermuxPreferences.storeCurrentSession(this, currentTermSession);
        }
        unregisterReceiver(this.mBroadcastReceiever);
        getDrawer().closeDrawers();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (getDrawer().isDrawerOpen(3)) {
            getDrawer().closeDrawers();
        } else {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        TermuxService termuxService = this.mTermService;
        if (termuxService != null) {
            termuxService.mSessionChangeCallback = null;
            this.mTermService = null;
        }
        unbindService(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DrawerLayout getDrawer() {
        return (DrawerLayout) findViewById(R.id.admsoloraya_res_0x7f0a016d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addNewSession(boolean z, String str) {
        String cwd;
        if (this.mTermService.getSessions().size() >= 8) {
            new AlertDialog.Builder(this).setTitle(R.string.admsoloraya_res_0x7f1200db).setMessage(R.string.admsoloraya_res_0x7f1200da).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).show();
            return;
        }
        TerminalSession currentTermSession = getCurrentTermSession();
        if (currentTermSession == null) {
            cwd = this.mSettings.mDefaultWorkingDir;
        } else {
            cwd = currentTermSession.getCwd();
        }
        TerminalSession createTermSession = this.mTermService.createTermSession(null, null, cwd, z);
        if (str != null) {
            createTermSession.mSessionName = str;
        }
        switchToSession(createTermSession);
        getDrawer().closeDrawers();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void switchToSession(TerminalSession terminalSession) {
        if (this.mTerminalView.attachSession(terminalSession)) {
            noteSessionInfo();
            updateBackgroundColor();
        }
    }

    String toToastTitle(TerminalSession terminalSession) {
        int indexOf = this.mTermService.getSessions().indexOf(terminalSession);
        StringBuilder sb = new StringBuilder("[" + (indexOf + 1) + "]");
        if (!TextUtils.isEmpty(terminalSession.mSessionName)) {
            sb.append(" ");
            sb.append(terminalSession.mSessionName);
        }
        String title = terminalSession.getTitle();
        if (!TextUtils.isEmpty(title)) {
            sb.append(terminalSession.mSessionName != null ? "\n" : " ");
            sb.append(title);
        }
        return sb.toString();
    }

    void noteSessionInfo() {
        if (this.mIsVisible) {
            TerminalSession currentTermSession = getCurrentTermSession();
            int indexOf = this.mTermService.getSessions().indexOf(currentTermSession);
            showToast(toToastTitle(currentTermSession), false);
            this.mListViewAdapter.notifyDataSetChanged();
            ListView listView = (ListView) findViewById(R.id.admsoloraya_res_0x7f0a023f);
            listView.setItemChecked(indexOf, true);
            listView.smoothScrollToPosition(indexOf);
        }
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        AutofillManager autofillManager;
        TerminalSession currentTermSession = getCurrentTermSession();
        if (currentTermSession == null) {
            return;
        }
        contextMenu.add(0, 0, 0, R.string.admsoloraya_res_0x7f1200ea);
        contextMenu.add(0, 1, 0, R.string.admsoloraya_res_0x7f1200e9);
        if (Build.VERSION.SDK_INT >= 26 && (autofillManager = (AutofillManager) getSystemService(AutofillManager.class)) != null && autofillManager.isEnabled()) {
            contextMenu.add(0, 10, 0, R.string.admsoloraya_res_0x7f1200cb);
        }
        contextMenu.add(0, 5, 0, R.string.admsoloraya_res_0x7f1200e4);
        contextMenu.add(0, 4, 0, getResources().getString(R.string.admsoloraya_res_0x7f1200d9, Integer.valueOf(getCurrentTermSession().getPid()))).setEnabled(currentTermSession.isRunning());
        contextMenu.add(0, 6, 0, R.string.admsoloraya_res_0x7f1200f7);
        contextMenu.add(0, 9, 0, R.string.admsoloraya_res_0x7f1200fc).setCheckable(true).setChecked(this.mSettings.isScreenAlwaysOn());
        contextMenu.add(0, 8, 0, R.string.admsoloraya_res_0x7f1200d8);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mTerminalView.showContextMenu();
        return false;
    }

    static LinkedHashSet<CharSequence> extractUrls(String str) {
        Pattern compile = Pattern.compile("((?:dav|dict|dns|file|finger|ftp(?:s?)|git|gopher|http(?:s?)|imap(?:s?)|irc(?:[6s]?)|ip[fn]s|ldap(?:s?)|pop3(?:s?)|redis(?:s?)|rsync|rtsp(?:[su]?)|sftp|smb(?:s?)|smtp(?:s?)|svn(?:(?:\\+ssh)?)|tcp|telnet|tftp|udp|vnc|ws(?:s?))://)((?:\\S+(?::\\S*)?@)?(?:(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))?|/(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+))(?::\\d{1,5})?(?:/[a-zA-Z0-9:@%\\-._~!$&()*+,;=?/]*)?(?:#[a-zA-Z0-9:@%\\-._~!$&()*+,;=?/]*)?)", 42);
        LinkedHashSet<CharSequence> linkedHashSet = new LinkedHashSet<>();
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            linkedHashSet.add(str.substring(matcher.start(1), matcher.end()));
        }
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showUrlSelection() {
        LinkedHashSet<CharSequence> extractUrls = extractUrls(getCurrentTermSession().getEmulator().getScreen().getTranscriptTextWithFullLinesJoined());
        if (extractUrls.isEmpty()) {
            new AlertDialog.Builder(this).setMessage(R.string.admsoloraya_res_0x7f1200ed).show();
            return;
        }
        final CharSequence[] charSequenceArr = (CharSequence[]) extractUrls.toArray(new CharSequence[0]);
        Collections.reverse(Arrays.asList(charSequenceArr));
        final AlertDialog create = new AlertDialog.Builder(this).setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda12
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                TermuxActivity.this.m108lambda$11$comtermfuappTermuxActivity(charSequenceArr, dialogInterface, i);
            }
        }).setTitle(R.string.admsoloraya_res_0x7f1200ec).create();
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda13
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                TermuxActivity.this.m109lambda$12$comtermfuappTermuxActivity(create, charSequenceArr, dialogInterface);
            }
        });
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$11$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m108lambda$11$comtermfuappTermuxActivity(CharSequence[] charSequenceArr, DialogInterface dialogInterface, int i) {
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(new ClipData(null, new String[]{"text/plain"}, new ClipData.Item((String) charSequenceArr[i])));
        Toast.makeText(this, (int) R.string.admsoloraya_res_0x7f1200eb, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$12$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m109lambda$12$comtermfuappTermuxActivity(final AlertDialog alertDialog, final CharSequence[] charSequenceArr, DialogInterface dialogInterface) {
        alertDialog.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda0
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public final boolean onItemLongClick(AdapterView adapterView, View view, int i, long j) {
                return TermuxActivity.this.m110lambda$13$comtermfuappTermuxActivity(alertDialog, charSequenceArr, adapterView, view, i, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$13$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ boolean m110lambda$13$comtermfuappTermuxActivity(AlertDialog alertDialog, CharSequence[] charSequenceArr, AdapterView adapterView, View view, int i, long j) {
        alertDialog.dismiss();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String) charSequenceArr[i]));
        try {
            startActivity(intent, null);
            return true;
        } catch (ActivityNotFoundException unused) {
            startActivity(Intent.createChooser(intent, null));
            return true;
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        AutofillManager autofillManager;
        TerminalSession currentTermSession = getCurrentTermSession();
        switch (menuItem.getItemId()) {
            case 0:
                showUrlSelection();
                return true;
            case 1:
                if (currentTermSession != null) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    String trim = currentTermSession.getEmulator().getScreen().getTranscriptTextWithoutJoinedLines().trim();
                    if (trim.length() > 100000) {
                        int length = trim.length() - 100000;
                        int indexOf = trim.indexOf(10, length);
                        if (indexOf != -1 && indexOf != trim.length() - 1) {
                            length = indexOf + 1;
                        }
                        trim = trim.substring(length).trim();
                    }
                    intent.putExtra("android.intent.extra.TEXT", trim);
                    intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.admsoloraya_res_0x7f1200f3));
                    startActivity(Intent.createChooser(intent, getString(R.string.admsoloraya_res_0x7f1200f2)));
                }
                return true;
            case 2:
            case 7:
            default:
                return super.onContextItemSelected(menuItem);
            case 3:
                doPaste();
                return true;
            case 4:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(17301543);
                builder.setMessage(R.string.admsoloraya_res_0x7f1200d3);
                builder.setPositiveButton(17039379, new DialogInterface.OnClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        TermuxActivity.this.m111lambda$14$comtermfuappTermuxActivity(dialogInterface, i);
                    }
                });
                builder.setNegativeButton(17039369, (DialogInterface.OnClickListener) null);
                builder.show();
                return true;
            case 5:
                if (currentTermSession != null) {
                    currentTermSession.reset();
                    showToast(getResources().getString(R.string.admsoloraya_res_0x7f1200e5), true);
                }
                return true;
            case 6:
                Intent intent2 = new Intent();
                intent2.setClassName("com.termfu.styling", "com.termfu.styling.TermuxStyleActivity");
                try {
                    startActivity(intent2);
                } catch (ActivityNotFoundException | IllegalArgumentException unused) {
                    new AlertDialog.Builder(this).setMessage(R.string.admsoloraya_res_0x7f1200f9).setPositiveButton(R.string.admsoloraya_res_0x7f1200f8, new DialogInterface.OnClickListener() { // from class: com.termfu.app.TermuxActivity$$ExternalSyntheticLambda6
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            TermuxActivity.this.m112lambda$15$comtermfuappTermuxActivity(dialogInterface, i);
                        }
                    }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
                }
                return true;
            case 8:
                return true;
            case 9:
                if (this.mTerminalView.getKeepScreenOn()) {
                    this.mTerminalView.setKeepScreenOn(false);
                    this.mSettings.setScreenAlwaysOn(this, false);
                } else {
                    this.mTerminalView.setKeepScreenOn(true);
                    this.mSettings.setScreenAlwaysOn(this, true);
                }
                return true;
            case 10:
                if (Build.VERSION.SDK_INT >= 26 && (autofillManager = (AutofillManager) getSystemService(AutofillManager.class)) != null && autofillManager.isEnabled()) {
                    autofillManager.requestAutofill(this.mTerminalView);
                }
                return super.onContextItemSelected(menuItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$14$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m111lambda$14$comtermfuappTermuxActivity(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        getCurrentTermSession().finishIfRunning();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$15$com-termfu-app-TermuxActivity  reason: not valid java name */
    public /* synthetic */ void m112lambda$15$comtermfuappTermuxActivity(DialogInterface dialogInterface, int i) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://f-droid.org/en/packages/com.termfu.styling/")));
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == REQUESTCODE_PERMISSION_STORAGE && iArr.length > 0 && iArr[0] == 0) {
            TermuxInstaller.setupStorageSymlinks(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void changeFontSize(boolean z) {
        this.mSettings.changeFontSize(this, z);
        this.mTerminalView.setTextSize(this.mSettings.getFontSize());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doPaste() {
        ClipData primaryClip = ((ClipboardManager) getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null) {
            return;
        }
        CharSequence coerceToText = primaryClip.getItemAt(0).coerceToText(this);
        if (TextUtils.isEmpty(coerceToText)) {
            return;
        }
        getCurrentTermSession().getEmulator().paste(coerceToText.toString());
    }

    public TerminalSession getStoredCurrentSessionOrLast() {
        TerminalSession currentSession = TermuxPreferences.getCurrentSession(this);
        if (currentSession != null) {
            return currentSession;
        }
        List<TerminalSession> sessions = this.mTermService.getSessions();
        if (sessions.isEmpty()) {
            return null;
        }
        return sessions.get(sessions.size() - 1);
    }

    void showToast(String str, boolean z) {
        Toast toast = this.mLastToast;
        if (toast != null) {
            toast.cancel();
        }
        Toast makeText = Toast.makeText(this, str, z ? 1 : 0);
        this.mLastToast = makeText;
        makeText.setGravity(48, 0, 0);
        this.mLastToast.show();
    }

    public void removeFinishedSession(TerminalSession terminalSession) {
        TermuxService termuxService = this.mTermService;
        int removeTermSession = termuxService.removeTermSession(terminalSession);
        this.mListViewAdapter.notifyDataSetChanged();
        if (this.mTermService.getSessions().isEmpty()) {
            finish();
            return;
        }
        if (removeTermSession >= termuxService.getSessions().size()) {
            removeTermSession = termuxService.getSessions().size() - 1;
        }
        switchToSession(termuxService.getSessions().get(removeTermSession));
    }
}
