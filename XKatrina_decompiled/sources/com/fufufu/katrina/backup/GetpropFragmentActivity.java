package com.fufufu.katrina.backup;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.FirebaseApp;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.topjohnwu.superuser.Shell;
import java.util.EnumMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes5.dex */
public class GetpropFragmentActivity extends Fragment {
    private ImageView im_qrcode;
    private LinearLayout ln_base;
    private LinearLayout ln_qrcode;
    private LinearLayout ln_qrcode_base;
    private LinearLayout ln_top;
    private MaterialCardView mc_qrcode;
    private TextView tv_desc;
    private TextView tv_qrcode;
    private TextView tv_subtitle;
    private TextView tv_title;
    private String base64String = "";
    private String allProp = "";
    private String s_date = "";
    private boolean b_date = false;

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0059, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.ln_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.ln_top = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ed);
        this.ln_qrcode_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02cf);
        this.tv_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0571);
        this.tv_subtitle = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0559);
        this.tv_qrcode = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a053f);
        this.mc_qrcode = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a035d);
        this.tv_desc = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0501);
        this.ln_qrcode = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ce);
        ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a021e);
        this.im_qrcode = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.GetpropFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                GetpropFragmentActivity.this._createQrCode();
            }
        });
    }

    private void initializeLogic() {
        this.tv_title.setText("Get Prop");
        this.tv_subtitle.setText("by fufufu");
        this.im_qrcode.setImageResource(R.drawable.admsoloraya_res_0x7f080106);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) { // from class: com.fufufu.katrina.backup.GetpropFragmentActivity.2
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                GetpropFragmentActivity.this.requireActivity().onBackPressed();
            }
        });
    }

    public String _Encode(String str) {
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 0);
        } catch (Exception unused) {
            return "";
        }
    }

    public void _createQrCode() {
        GetpropFragmentActivity getpropFragmentActivity;
        this.b_date = false;
        Shell.Result exec = Shell.cmd("getprop ro.build.date").exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_date = exec.isSuccess();
        String m = GetpropFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
        this.s_date = m;
        this.s_date = m.replace("\n", "");
        String str = Build.BOARD;
        String str2 = Build.BOOTLOADER;
        String str3 = Build.BRAND;
        String str4 = Build.ID;
        String str5 = Build.DEVICE;
        String str6 = Build.HOST;
        String str7 = Build.VERSION.INCREMENTAL;
        String str8 = Build.MODEL;
        String str9 = Build.PRODUCT;
        String str10 = Build.VERSION.RELEASE;
        String str11 = Build.USER;
        String str12 = Build.MANUFACTURER;
        String str13 = Build.HARDWARE;
        String str14 = Build.DISPLAY;
        String str15 = Build.FINGERPRINT;
        String valueOf = String.valueOf(Build.TIME);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("BOARD", str);
            jSONObject.put("BOOT", str2);
            jSONObject.put("BRAND", str3);
            jSONObject.put("BUILDID", str4);
            jSONObject.put("DEVICE", str5);
            jSONObject.put("HOST", str6);
            jSONObject.put("INCREMENTAL", str7);
            jSONObject.put("MODEL", str8);
            jSONObject.put("PRODUCT", str9);
            jSONObject.put("RELEASE", str10);
            jSONObject.put("USER", str11);
            jSONObject.put("MANUFACTURER", str12);
            jSONObject.put("HARDWARE", str13);
            jSONObject.put("DISPLAYID", str14);
            jSONObject.put("FINGERPRINT", str15);
            getpropFragmentActivity = this;
            try {
                jSONObject.put("DATE", getpropFragmentActivity.s_date);
                jSONObject.put("UTC", valueOf);
                String jSONObject2 = jSONObject.toString();
                getpropFragmentActivity.allProp = jSONObject2;
                String _Encode = getpropFragmentActivity._Encode(jSONObject2);
                getpropFragmentActivity.base64String = _Encode;
                getpropFragmentActivity.allProp = String.valueOf(_Encode) + "\n#GetProp by fufufu";
                try {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int i = (int) (displayMetrics.widthPixels * 0.9d);
                    EnumMap enumMap = new EnumMap(EncodeHintType.class);
                    enumMap.put((EnumMap) EncodeHintType.CHARACTER_SET, (EncodeHintType) "UTF-8");
                    BitMatrix encode = new QRCodeWriter().encode(getpropFragmentActivity.allProp, BarcodeFormat.QR_CODE, i, i, enumMap);
                    Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.RGB_565);
                    for (int i2 = 0; i2 < i; i2++) {
                        for (int i3 = 0; i3 < i; i3++) {
                            createBitmap.setPixel(i2, i3, encode.get(i2, i3) ? ViewCompat.MEASURED_STATE_MASK : -1);
                        }
                    }
                    getpropFragmentActivity.im_qrcode.setImageBitmap(createBitmap);
                    getpropFragmentActivity.tv_qrcode.setText(str8);
                } catch (Exception e) {
                    e.printStackTrace();
                    getpropFragmentActivity.im_qrcode.setImageResource(R.drawable.admsoloraya_res_0x7f080176);
                }
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                getpropFragmentActivity.im_qrcode.setImageResource(R.drawable.admsoloraya_res_0x7f080176);
                getpropFragmentActivity.tv_desc.setText("Scan menggunakan app XKatrina diperangkat lain, maka prop akan di terapkan.");
            }
        } catch (JSONException e3) {
            e = e3;
            getpropFragmentActivity = this;
        }
        getpropFragmentActivity.tv_desc.setText("Scan menggunakan app XKatrina diperangkat lain, maka prop akan di terapkan.");
    }
}
