package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.fufufu.katrina.backup.RequestNetwork;
import com.google.firebase.FirebaseApp;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
/* loaded from: classes5.dex */
public class MaterialDebugActivity extends AppCompatActivity {
    private RequestNetwork.RequestListener _sendBot_request_listener;
    private Button btn_close_crash;
    private Button btn_send_crash;
    private LinearLayout ln_lottie_crash;
    private LottieAnimationView lottie_crash;
    private SharedPreferences prefuser;
    private RequestNetwork sendBot;
    private TextView tv_message_crash;
    private TextView tv_title_crash;
    private ScrollView vscr_crash;
    private String[] exceptionTypes = {"StringIndexOutOfBoundsException", "IndexOutOfBoundsException", "ArithmeticException", "NumberFormatException", "ActivityNotFoundException"};
    private String[] exceptionMessages = {"Invalid string operation\n", "Invalid list operation\n", "Invalid arithmetical operation\n", "Invalid toNumber block operation\n", "Invalid intent operation"};

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d006f);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.prefuser = getSharedPreferences("user_preferences", 0);
        this.lottie_crash = (LottieAnimationView) findViewById(R.id.admsoloraya_res_0x7f0a0309);
        this.ln_lottie_crash = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02ba);
        this.tv_title_crash = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0589);
        this.vscr_crash = (ScrollView) findViewById(R.id.admsoloraya_res_0x7f0a05c0);
        this.btn_close_crash = (Button) findViewById(R.id.admsoloraya_res_0x7f0a0089);
        this.btn_send_crash = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00f7);
        this.tv_message_crash = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0529);
        this.sendBot = new RequestNetwork(this);
        this.btn_close_crash.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.MaterialDebugActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MaterialDebugActivity.this.finish();
            }
        });
        this.btn_send_crash.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.MaterialDebugActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str = String.valueOf("USER : ".concat(MaterialDebugActivity.this.prefuser.getString("emanresu", "").concat("\nEMAIL : ".concat(MaterialDebugActivity.this.prefuser.getString("liamresu", ""))))) + "\nVERSION : " + MaterialDebugActivity.getVersionName(MaterialDebugActivity.this) + "\n\n\n" + MaterialDebugActivity.this.tv_message_crash.getText().toString();
                if (str.length() > 3000) {
                    str = str.substring(0, PathInterpolatorCompat.MAX_NUM_POINTS);
                }
                MaterialDebugActivity.this._sendToYourBot(str);
            }
        });
        this._sendBot_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.MaterialDebugActivity.3
            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> hashMap) {
                if (str2.contains("\"ok\":true")) {
                    MaterialDebugActivity.this.btn_send_crash.setVisibility(8);
                    MaterialDebugActivity.this.tv_message_crash.setGravity(17);
                    MaterialDebugActivity.this.tv_message_crash.setTextSize(30.0f);
                    MaterialDebugActivity.this.tv_message_crash.setText("Terima Kasih !");
                    return;
                }
                MaterialDebugActivity.this.btn_send_crash.setText("Gagal, kirim ulang");
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                MaterialDebugActivity.this.btn_send_crash.setText("Terjadi Error, kirim ulang");
            }
        };
    }

    private void initializeLogic() {
        _setFirstUI();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a4, code lost:
        if (r1.isEmpty() != false) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void _setFirstUI() {
        /*
            r9 = this;
            android.view.Window r0 = r9.getWindow()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 21
            r3 = 0
            if (r1 < r2) goto Le
            r0.setStatusBarColor(r3)
        Le:
            int r1 = android.os.Build.VERSION.SDK_INT
            if (r1 < r2) goto L15
            r0.setNavigationBarColor(r3)
        L15:
            android.view.Window r0 = r9.getWindow()
            android.view.View r0 = r0.getDecorView()
            r1 = 8208(0x2010, float:1.1502E-41)
            r0.setSystemUiVisibility(r1)
            android.widget.LinearLayout r0 = r9.ln_lottie_crash
            r1 = 8
            r0.setVisibility(r1)
            android.content.Intent r0 = r9.getIntent()
            java.lang.String r1 = ""
            if (r0 == 0) goto Lc7
            java.lang.String r2 = "error"
            java.lang.String r2 = r0.getStringExtra(r2)
            java.lang.String r4 = "title"
            java.lang.String r4 = r0.getStringExtra(r4)
            java.lang.String r5 = "mode"
            java.lang.String r0 = r0.getStringExtra(r5)
            java.lang.String r5 = "\n"
            java.lang.String[] r5 = r2.split(r5)
            r6 = 0
        L4a:
            java.lang.String[] r7 = r9.exceptionTypes     // Catch: java.lang.Exception -> Laa
            int r8 = r7.length     // Catch: java.lang.Exception -> Laa
            if (r6 < r8) goto L50
            goto La0
        L50:
            r8 = r5[r3]     // Catch: java.lang.Exception -> Laa
            r7 = r7[r6]     // Catch: java.lang.Exception -> Laa
            boolean r7 = r8.contains(r7)     // Catch: java.lang.Exception -> Laa
            if (r7 == 0) goto La7
            java.lang.String[] r7 = r9.exceptionMessages     // Catch: java.lang.Exception -> Laa
            r1 = r7[r6]     // Catch: java.lang.Exception -> Laa
            r7 = r5[r3]     // Catch: java.lang.Exception -> Laa
            java.lang.String[] r8 = r9.exceptionTypes     // Catch: java.lang.Exception -> Laa
            r8 = r8[r6]     // Catch: java.lang.Exception -> Laa
            int r7 = r7.indexOf(r8)     // Catch: java.lang.Exception -> Laa
            java.lang.String[] r8 = r9.exceptionTypes     // Catch: java.lang.Exception -> Laa
            r6 = r8[r6]     // Catch: java.lang.Exception -> Laa
            int r6 = r6.length()     // Catch: java.lang.Exception -> Laa
            int r7 = r7 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Laa
            java.lang.String r8 = java.lang.String.valueOf(r1)     // Catch: java.lang.Exception -> Laa
            r6.<init>(r8)     // Catch: java.lang.Exception -> Laa
            r5 = r5[r3]     // Catch: java.lang.Exception -> Laa
            int r8 = r5.length()     // Catch: java.lang.Exception -> Laa
            java.lang.String r5 = r5.substring(r7, r8)     // Catch: java.lang.Exception -> Laa
            r6.append(r5)     // Catch: java.lang.Exception -> Laa
            java.lang.String r1 = r6.toString()     // Catch: java.lang.Exception -> Laa
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Laa
            java.lang.String r6 = java.lang.String.valueOf(r1)     // Catch: java.lang.Exception -> Laa
            r5.<init>(r6)     // Catch: java.lang.Exception -> Laa
            java.lang.String r6 = "\n\nDetailed error message:\n"
            r5.append(r6)     // Catch: java.lang.Exception -> Laa
            r5.append(r2)     // Catch: java.lang.Exception -> Laa
            java.lang.String r1 = r5.toString()     // Catch: java.lang.Exception -> Laa
        La0:
            boolean r5 = r1.isEmpty()     // Catch: java.lang.Exception -> Laa
            if (r5 == 0) goto Lc4
            goto Lc5
        La7:
            int r6 = r6 + 1
            goto L4a
        Laa:
            r2 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r5.<init>(r1)
            java.lang.String r1 = "\n\nError while getting error: "
            r5.append(r1)
            java.lang.String r1 = android.util.Log.getStackTraceString(r2)
            r5.append(r1)
            java.lang.String r1 = r5.toString()
        Lc4:
            r2 = r1
        Lc5:
            r1 = r0
            goto Lc9
        Lc7:
            r2 = r1
            r4 = r2
        Lc9:
            java.lang.String r0 = "crash"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto Ld9
            com.airbnb.lottie.LottieAnimationView r0 = r9.lottie_crash
            java.lang.String r1 = "katrina_crash.json"
            r0.setAnimation(r1)
            goto Le0
        Ld9:
            com.airbnb.lottie.LottieAnimationView r0 = r9.lottie_crash
            java.lang.String r1 = "katrina_logout.json"
            r0.setAnimation(r1)
        Le0:
            android.widget.LinearLayout r0 = r9.ln_lottie_crash
            r0.setVisibility(r3)
            android.widget.TextView r0 = r9.tv_title_crash
            r0.setText(r4)
            android.widget.TextView r0 = r9.tv_message_crash
            r0.setText(r2)
            android.widget.TextView r0 = r9.tv_message_crash
            r1 = 1
            r0.setTextIsSelectable(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fufufu.katrina.backup.MaterialDebugActivity._setFirstUI():void");
    }

    public void _sendToYourBot(String str) {
        this.btn_send_crash.setText("Mengirim... Wait..");
        try {
            String encode = URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
            this.sendBot.startRequestNetwork("GET", "https://api.telegram.org/bot6499801397:AAHGtjQMX44XTVapofA-WEssmFX1Kw6CreE/sendMessage?chat_id=741550746&text=" + encode, "a", this._sendBot_request_listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            this.btn_send_crash.setText("Error encoding message.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Tidak dapat mengambil versi nama";
        }
    }
}
