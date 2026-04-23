package com.fufufu.katrina.backup;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.FirebaseApp;
import java.util.ArrayList;
import java.util.Random;
/* loaded from: classes5.dex */
public class OtpActivity extends AppCompatActivity {
    private Button btn_litensi;
    private Button btn_refresh_otp;
    private Button btn_tokoclaude;
    private Button btn_tokootp;
    private Button btn_turbootp;
    private MaterialButtonToggleGroup group_address_otp;
    private ProgressBar pbar_otp;
    private SharedPreferences pref;
    private String s_urlotp = "";
    private WebView webview_otp;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d009f);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.pbar_otp = (ProgressBar) findViewById(R.id.admsoloraya_res_0x7f0a03f0);
        WebView webView = (WebView) findViewById(R.id.admsoloraya_res_0x7f0a05c7);
        this.webview_otp = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.webview_otp.getSettings().setSupportZoom(true);
        this.group_address_otp = (MaterialButtonToggleGroup) findViewById(R.id.admsoloraya_res_0x7f0a01c7);
        this.btn_litensi = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00c1);
        this.btn_turbootp = (Button) findViewById(R.id.admsoloraya_res_0x7f0a0100);
        this.btn_tokootp = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00fe);
        this.btn_tokoclaude = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00fd);
        this.btn_refresh_otp = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00e7);
        this.pref = getSharedPreferences("preferences_ui", 0);
        this.webview_otp.setWebViewClient(new WebViewClient() { // from class: com.fufufu.katrina.backup.OtpActivity.1
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                super.onPageStarted(webView2, str, bitmap);
                OtpActivity.this.pbar_otp.setVisibility(0);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                super.onPageFinished(webView2, str);
                OtpActivity.this.pbar_otp.setVisibility(8);
            }
        });
        this.webview_otp.setWebChromeClient(new WebChromeClient() { // from class: com.fufufu.katrina.backup.OtpActivity.2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView2, int i) {
                OtpActivity.this.pbar_otp.setProgress(i);
            }
        });
        this.btn_litensi.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.OtpActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OtpActivity.this.pref.edit().putString("url_otp", "litensi").commit();
                OtpActivity.this.openPref();
            }
        });
        this.btn_turbootp.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.OtpActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OtpActivity.this.pref.edit().putString("url_otp", "turbootp").commit();
                OtpActivity.this.openPref();
            }
        });
        this.btn_tokootp.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.OtpActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OtpActivity.this.pref.edit().putString("url_otp", "tokootp").commit();
                OtpActivity.this.openPref();
            }
        });
        this.btn_tokoclaude.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.OtpActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OtpActivity.this.pref.edit().putString("url_otp", "tokoclaude").commit();
                OtpActivity.this.openPref();
            }
        });
        this.btn_refresh_otp.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.OtpActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OtpActivity otpActivity = OtpActivity.this;
                otpActivity.s_urlotp = otpActivity.webview_otp.getUrl();
                OtpActivity.this.webview_otp.loadUrl(OtpActivity.this.s_urlotp);
            }
        });
    }

    private void initializeLogic() {
        _firstSetUI();
    }

    public void _firstSetUI() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
        }
        getWindow().getDecorView().setSystemUiVisibility(8208);
        this.group_address_otp.setSingleSelection(true);
        WebSettings settings = this.webview_otp.getSettings();
        settings.setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1");
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setMixedContentMode(0);
        settings.setAllowFileAccess(true);
        openPref();
    }

    public void turbootp() {
        this.webview_otp.loadUrl("https://turbootp.com/order");
    }

    public void tokootp() {
        this.webview_otp.loadUrl("https://tokootp.com/order");
    }

    public void tokoclaude() {
        this.webview_otp.loadUrl("https://tokoclaude.com/order");
    }

    public void litensi() {
        this.webview_otp.loadUrl("https://litensi.id/otp");
    }

    public void openPref() {
        if (this.pref.getString("url_otp", "").equals("tokoclaude")) {
            this.group_address_otp.check(this.btn_tokoclaude.getId());
            tokoclaude();
        } else if (this.pref.getString("url_otp", "").equals("turbootp")) {
            this.group_address_otp.check(this.btn_turbootp.getId());
            turbootp();
        } else if (this.pref.getString("url_otp", "").equals("tokootp")) {
            this.group_address_otp.check(this.btn_tokootp.getId());
            tokootp();
        } else if (this.pref.getString("url_otp", "").equals("litensi")) {
            this.group_address_otp.check(this.btn_litensi.getId());
            litensi();
        } else {
            this.group_address_otp.check(this.btn_tokoclaude.getId());
            tokoclaude();
        }
    }

    @Deprecated
    public void showMessage(String str) {
        Toast.makeText(getApplicationContext(), str, 0).show();
    }

    @Deprecated
    public int getLocationX(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[0];
    }

    @Deprecated
    public int getLocationY(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[1];
    }

    @Deprecated
    public int getRandom(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView listView) {
        ArrayList<Double> arrayList = new ArrayList<>();
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        for (int i = 0; i < checkedItemPositions.size(); i++) {
            if (checkedItemPositions.valueAt(i)) {
                arrayList.add(Double.valueOf(checkedItemPositions.keyAt(i)));
            }
        }
        return arrayList;
    }

    @Deprecated
    public float getDip(int i) {
        return TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }
}
