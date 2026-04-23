package com.fufufu.katrina.backup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.Random;
/* loaded from: classes5.dex */
public class LoginActivity extends AppCompatActivity {
    private OnCompleteListener<AuthResult> _katrina_auth_create_user_listener;
    private OnCompleteListener<Void> _katrina_auth_reset_password_listener;
    private OnCompleteListener<AuthResult> _katrina_auth_sign_in_listener;
    private AutoCompleteTextView auto_email;
    private AutoCompleteTextView auto_password;
    private Button btn_login;
    private ImageView im_logo;
    private FirebaseAuth katrina_auth;
    private OnCompleteListener<Void> katrina_auth_deleteUserListener;
    private OnCompleteListener<Void> katrina_auth_emailVerificationSentListener;
    private OnCompleteListener<AuthResult> katrina_auth_googleSignInListener;
    private OnCompleteListener<AuthResult> katrina_auth_phoneAuthListener;
    private OnCompleteListener<Void> katrina_auth_updateEmailListener;
    private OnCompleteListener<Void> katrina_auth_updatePasswordListener;
    private OnCompleteListener<Void> katrina_auth_updateProfileListener;
    private LinearLayout ln_1;
    private LinearLayout ln_login;
    private SharedPreferences prefuser;
    private TextInputLayout til_email;
    private TextInputLayout til_password;
    private TextView tv_desc;
    private TextView tv_error;
    private TextView tv_non_account;
    private String s_email = "";
    private String s_pass = "";
    private Intent i = new Intent();

    public void _gotoKatrinaActivity() {
        startActivity(new Intent(this, KatrinaActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d0061);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_login = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02b8);
        this.im_logo = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a021a);
        this.til_email = (TextInputLayout) findViewById(R.id.admsoloraya_res_0x7f0a04a1);
        this.til_password = (TextInputLayout) findViewById(R.id.admsoloraya_res_0x7f0a04a6);
        this.tv_error = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a050a);
        this.btn_login = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00c2);
        this.ln_1 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0263);
        this.auto_email = (AutoCompleteTextView) findViewById(R.id.admsoloraya_res_0x7f0a0062);
        this.auto_password = (AutoCompleteTextView) findViewById(R.id.admsoloraya_res_0x7f0a0067);
        this.tv_desc = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0501);
        this.tv_non_account = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a052f);
        this.katrina_auth = FirebaseAuth.getInstance();
        this.prefuser = getSharedPreferences("user_preferences", 0);
        this.btn_login.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.LoginActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this._onButtonDaftar();
            }
        });
        this.auto_email.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.LoginActivity.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                charSequence.toString();
                LoginActivity.this.til_email.setErrorEnabled(false);
            }
        });
        this.auto_password.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.LoginActivity.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                charSequence.toString();
                LoginActivity.this.til_password.setErrorEnabled(false);
            }
        });
        this.tv_non_account.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.LoginActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this._onTextDaftar();
            }
        });
        this.katrina_auth_updateEmailListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.LoginActivity.5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_updatePasswordListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.LoginActivity.6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_emailVerificationSentListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.LoginActivity.7
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_deleteUserListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.LoginActivity.8
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_phoneAuthListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.LoginActivity.9
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_updateProfileListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.LoginActivity.10
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_googleSignInListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.LoginActivity.11
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_auth_create_user_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.LoginActivity.12
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                boolean isSuccessful = task.isSuccessful();
                String message = task.getException() != null ? task.getException().getMessage() : "";
                if (!isSuccessful) {
                    LoginActivity.this.tv_error.setTextColor(-59111);
                    LoginActivity.this.tv_error.setText(message);
                    LoginActivity.this.tv_error.setVisibility(0);
                    return;
                }
                LoginActivity.this.btn_login.setText("LOGIN");
                LoginActivity.this.tv_error.setText("Silahkan verifikasi melalui link yang dikirim ke email anda.");
                LoginActivity.this.tv_error.setVisibility(0);
                LoginActivity.this.tv_error.setTextColor(-15132391);
                LoginActivity.this.tv_non_account.setText("DAFTAR");
                LoginActivity.this.tv_desc.setText("Belum punya akun? ");
                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(LoginActivity.this.katrina_auth_emailVerificationSentListener);
            }
        };
        this._katrina_auth_sign_in_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.LoginActivity.13
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                boolean isSuccessful = task.isSuccessful();
                String message = task.getException() != null ? task.getException().getMessage() : "";
                if (!isSuccessful) {
                    LoginActivity.this.tv_error.setTextColor(-59111);
                    if (message.contains("INVALID_LOGIN_CREDENTIALS")) {
                        LoginActivity.this.tv_error.setText("Email anda belum terdaftar.");
                    } else {
                        LoginActivity.this.tv_error.setText(message);
                    }
                    LoginActivity.this.tv_error.setVisibility(0);
                    return;
                }
                LoginActivity.this._gotoKatrinaActivity();
            }
        };
        this._katrina_auth_reset_password_listener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.LoginActivity.14
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
            }
        };
    }

    private void initializeLogic() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
        }
        getWindow().getDecorView().setSystemUiVisibility(8208);
        if ("a d m s o l o r a y a" != 0) {
            if ("a d m s o l o r a y a" != 0) {
                _gotoKatrinaActivity();
                return;
            } else {
                _setFirstUI();
                return;
            }
        }
        _setFirstUI();
    }

    public void _setFirstUI() {
        this.btn_login.setText("LOGIN");
        this.tv_error.setVisibility(4);
        this.im_logo.setImageResource(R.drawable.admsoloraya_res_0x7f080178);
    }

    public void _onTextDaftar() {
        this.tv_error.setVisibility(4);
        if (this.btn_login.getText().toString().equals("LOGIN")) {
            this.btn_login.setText("DAFTAR");
            this.tv_non_account.setText("LOGIN");
            this.tv_desc.setText("Sudah punya akun? ");
        } else {
            this.btn_login.setText("LOGIN");
            this.tv_non_account.setText("DAFTAR");
            this.tv_desc.setText("Belum punya akun? ");
        }
        this.til_email.setErrorEnabled(false);
        this.til_password.setErrorEnabled(false);
    }

    public void _onButtonDaftar() {
        this.tv_error.setVisibility(4);
        if (this.auto_email.getText().toString().equals("")) {
            this.til_email.setErrorEnabled(true);
            this.til_email.setError("Email tidak boleh kosong");
        } else if (this.auto_password.getText().toString().equals("")) {
            this.til_password.setErrorEnabled(true);
            this.til_password.setError("Password tidak boleh kosong");
        } else if (!this.auto_email.getText().toString().contains("gmail")) {
            this.til_email.setErrorEnabled(true);
            this.til_email.setError("Email wajib menggunakan Gmail");
        } else if (this.auto_password.getText().toString().length() < 6) {
            this.til_password.setErrorEnabled(true);
            this.til_password.setError("Password minimal 6 karakter");
        } else {
            this.s_email = this.auto_email.getText().toString();
            this.s_pass = this.auto_password.getText().toString();
            if (this.btn_login.getText().toString().equals("DAFTAR")) {
                this.katrina_auth.createUserWithEmailAndPassword(this.s_email, this.s_pass).addOnCompleteListener(this, this._katrina_auth_create_user_listener);
            } else if (this.btn_login.getText().toString().equals("LOGIN")) {
                this.katrina_auth.signInWithEmailAndPassword(this.s_email, this.s_pass).addOnCompleteListener(this, this._katrina_auth_sign_in_listener);
            }
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
