package com.fufufu.katrina.backup;

import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.EnumMap;
/* loaded from: classes5.dex */
public class CreateCodeFragmentActivity extends Fragment {
    private TextInputEditText et_input_qrcode;
    private ImageView im_qrcode;
    private LinearLayout ln_base;
    private LinearLayout ln_qrcode;
    private LinearLayout ln_qrcode_base;
    private LinearLayout ln_top;
    private MaterialCardView mc_qrcode;
    private TextInputLayout til_input_qrcode;
    private EditText tv_qrcode;

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0039, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.ln_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.ln_top = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ed);
        this.ln_qrcode_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02cf);
        this.til_input_qrcode = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a04a4);
        this.et_input_qrcode = (TextInputEditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0189);
        this.tv_qrcode = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a053f);
        this.mc_qrcode = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a035d);
        this.ln_qrcode = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ce);
        ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a021e);
        this.im_qrcode = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.CreateCodeFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                CreateCodeFragmentActivity.this._createQrCode();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.fufufu.katrina.backup.CreateCodeFragmentActivity$2] */
    private void initializeLogic() {
        this.tv_qrcode.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.CreateCodeFragmentActivity.2
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(30, -1));
        this.im_qrcode.setImageResource(R.drawable.admsoloraya_res_0x7f0800f2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) { // from class: com.fufufu.katrina.backup.CreateCodeFragmentActivity.3
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                CreateCodeFragmentActivity.this.requireActivity().onBackPressed();
            }
        });
    }

    public void _createQrCode() {
        if (this.et_input_qrcode.getText().toString().equals("")) {
            this.til_input_qrcode.setError("Enter content!");
            return;
        }
        try {
            Toast.makeText(requireContext(), "Mohon tunggu...", 0).show();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = (int) (displayMetrics.widthPixels * 0.9d);
            EnumMap enumMap = new EnumMap(EncodeHintType.class);
            enumMap.put((EnumMap) EncodeHintType.CHARACTER_SET, (EncodeHintType) "UTF-8");
            BitMatrix encode = new QRCodeWriter().encode(this.et_input_qrcode.getText().toString(), BarcodeFormat.QR_CODE, i, i, enumMap);
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.RGB_565);
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    createBitmap.setPixel(i2, i3, encode.get(i2, i3) ? ViewCompat.MEASURED_STATE_MASK : -1);
                }
            }
            this.im_qrcode.setImageBitmap(createBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
