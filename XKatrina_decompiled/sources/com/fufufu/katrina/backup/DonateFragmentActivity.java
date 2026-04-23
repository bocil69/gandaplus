package com.fufufu.katrina.backup;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.fufufu.katrina.backup.RequestNetwork;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import java.util.HashMap;
/* loaded from: classes5.dex */
public class DonateFragmentActivity extends Fragment {
    private RequestNetwork.RequestListener _ceknet_request_listener;
    private Button btn_send_data;
    private RequestNetwork ceknet;
    private AutoCompleteTextView et_amount;
    private AutoCompleteTextView et_email;
    private AutoCompleteTextView et_message;
    private AutoCompleteTextView et_name;
    private FrameLayout fr_code;
    private ImageView im_donasi;
    private ImageView im_qrcode;
    private ImageView im_shadow;
    private LinearLayout ln_base;
    private LinearLayout ln_input;
    private LinearLayout ln_loadbar;
    private LinearLayout ln_loading;
    private LinearLayout ln_scan;
    private LinearLayout ln_web;
    private ProgressBar pbar_loading;
    private TextInputLayout til_1;
    private TextInputLayout til_2;
    private TextInputLayout til_3;
    private TextInputLayout til_4;
    private TextView tv_loading;
    private TextView tv_scan;
    private TextView tv_title;
    private WebView webfu;

    public void _REPLACER() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0050, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.ln_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.tv_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0571);
        this.ln_loading = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b6);
        this.ln_web = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02f6);
        this.ln_input = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02a8);
        this.ln_scan = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02d8);
        this.ln_loadbar = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b5);
        this.tv_scan = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a054e);
        this.fr_code = (FrameLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a01b3);
        this.im_shadow = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0221);
        this.im_qrcode = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a021e);
        this.pbar_loading = (ProgressBar) view.findViewById(R.id.admsoloraya_res_0x7f0a03ee);
        this.tv_loading = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a051d);
        WebView webView = (WebView) view.findViewById(R.id.admsoloraya_res_0x7f0a05c6);
        this.webfu = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.webfu.getSettings().setSupportZoom(true);
        this.im_donasi = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0210);
        this.til_1 = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049a);
        this.til_2 = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049b);
        this.til_3 = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049c);
        this.til_4 = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049d);
        this.btn_send_data = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00f8);
        this.et_name = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a018c);
        this.et_message = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a018b);
        this.et_email = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0186);
        this.et_amount = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a017f);
        this.ceknet = new RequestNetwork((Activity) getContext());
        this.tv_loading.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.DonateFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DonateFragmentActivity.this._setFirstUI();
            }
        });
        this.btn_send_data.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.DonateFragmentActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DonateFragmentActivity.this._onCekInputData();
            }
        });
        this._ceknet_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.DonateFragmentActivity.3
            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> hashMap) {
            }
        };
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) { // from class: com.fufufu.katrina.backup.DonateFragmentActivity.4
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
            }
        });
        this.tv_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/grand.ttf"), 1);
        _setFirstUI();
    }

    public void _setFirstUI() {
        this.et_amount.setLongClickable(false);
        this.et_amount.setTextIsSelectable(false);
        this.tv_loading.setText("Menunggu koneksi internet");
        this.ln_loading.setVisibility(0);
        this.ln_web.setVisibility(8);
        this.ln_input.setVisibility(8);
        this.ln_scan.setVisibility(8);
        this.et_name.setSingleLine(true);
        this.et_message.setSingleLine(true);
        this.et_email.setSingleLine(true);
        this.et_amount.setSingleLine(true);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("donate")) {
            this.et_name.setText(arguments.getString("donate_name", ""));
            this.et_email.setText(arguments.getString("donate_mail", ""));
        }
        this.webfu.getSettings().setLoadWithOverviewMode(true);
        this.webfu.getSettings().setUseWideViewPort(true);
        this.webfu.getSettings().setUserAgentString("Mozilla/5.0 (Android) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        this.webfu.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.webfu.setWebChromeClient(new WebChromeClient() { // from class: com.fufufu.katrina.backup.DonateFragmentActivity.5
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("WebView", String.valueOf(consoleMessage.message()) + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
                return true;
            }
        });
        this.et_amount.setFilters(new InputFilter[]{new InputFilter() { // from class: com.fufufu.katrina.backup.DonateFragmentActivity.6
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                while (i < i2) {
                    char charAt = charSequence.charAt(i);
                    if (!Character.isDigit(charAt)) {
                        return "";
                    }
                    if (i3 == 0 && charAt == '0') {
                        return "";
                    }
                    i++;
                }
                return null;
            }
        }});
        this.webfu.setWebViewClient(new WebViewClient() { // from class: com.fufufu.katrina.backup.DonateFragmentActivity.7
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                DonateFragmentActivity.this.ln_loading.setVisibility(0);
                DonateFragmentActivity.this.ln_input.setVisibility(8);
                super.onPageStarted(webView, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                DonateFragmentActivity.this.ln_loading.setVisibility(8);
                DonateFragmentActivity.this.ln_input.setVisibility(0);
                super.onPageFinished(webView, str);
            }
        });
        _openWebView();
    }

    public void _openWebView() {
        if (SketchwareUtil.isConnected(getContext().getApplicationContext())) {
            this.webfu.loadUrl("https://nyawer.co/fufufu#");
        } else {
            this.tv_loading.setText("No internet, coba lagi?");
        }
    }

    public void _onCekInputData() {
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey("donate")) {
            return;
        }
        String errorMessage = getErrorMessage();
        if (errorMessage.isEmpty()) {
            _inputToHTML(this.et_name.getText().toString(), this.et_message.getText().toString(), this.et_amount.getText().toString(), this.et_email.getText().toString());
        } else if (getActivity() != null) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), errorMessage.substring(0, errorMessage.length() - 1));
        }
    }

    private String getErrorMessage() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty(this.et_email)) {
            sb.append("Email tidak boleh kosong.\n");
        } else if (!isValidEmail(this.et_email.getText().toString())) {
            sb.append("Format email tidak valid.\n");
        }
        if (isEmpty(this.et_name)) {
            sb.append("Nama tidak boleh kosong.\n");
        }
        if (isEmpty(this.et_message)) {
            sb.append("Pesan tidak boleh kosong.\n");
        }
        if (isBelowMinCurrency(this.et_amount, 10000)) {
            sb.append("Nominal kurang dari 10000.\n");
        }
        isValidInput();
        return sb.toString();
    }

    private boolean isValidInput() {
        return (isEmpty(this.et_email) || isEmpty(this.et_name) || isEmpty(this.et_message) || isBelowMinCurrency(this.et_amount, 10000) || !isValidEmail(this.et_email.getText().toString())) ? false : true;
    }

    private boolean isBelowMinCurrency(EditText editText, int i) {
        try {
            return Double.parseDouble(editText.getText().toString().trim().replaceAll("[^\\d.]+", "")) < ((double) i);
        } catch (NumberFormatException unused) {
            return true;
        }
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private boolean isValidEmail(String str) {
        return str.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
    }

    public void _inputToHTML(String str, String str2, String str3, String str4) {
        WebView webView = this.webfu;
        webView.loadUrl("javascript:(function() { var form = document.getElementById('formDonation'); form.querySelector('#donor_name').value = '" + str + "'; form.querySelector('#amount').value = '" + str3 + "'; var messageInput = form.querySelector('input#message'); if (messageInput) {     messageInput.value = '" + str2 + "'; } else {     console.error('Elemen dengan ID \"message\" tidak ditemukan atau bukan elemen input.'); } form.querySelector('#donor_email').value = '" + str4 + "'; })();");
        this.webfu.loadUrl("javascript:(function() { document.querySelector('button[name=\"method\"][value=\"qrcode\"]').click(); })();");
        _createQRCode();
    }

    public void _createQRCode() {
        this.tv_loading.setText("Menunggu koneksi internet");
        this.ln_loading.setVisibility(0);
        this.ln_input.setVisibility(8);
        this.webfu.evaluateJavascript("document.getElementById('qrcode').title", new ValueCallback<String>() { // from class: com.fufufu.katrina.backup.DonateFragmentActivity.8
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(String str) {
                String replaceAll = str.replaceAll("^\"|\"$", "");
                if (replaceAll == null || replaceAll.isEmpty()) {
                    DonateFragmentActivity.this._createQRCode();
                } else {
                    DonateFragmentActivity.this.handleQRCode(replaceAll);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleQRCode(String str) {
        Glide.with(getContext().getApplicationContext()).load(Uri.parse("https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=".concat(str))).into(this.im_qrcode);
        this.ln_loadbar.setVisibility(8);
        this.ln_scan.setVisibility(0);
        animateBounce(this.im_shadow);
    }

    private void animateBounce(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(1000L);
        scaleAnimation.setRepeatMode(2);
        scaleAnimation.setRepeatCount(-1);
        view.startAnimation(scaleAnimation);
    }
}
