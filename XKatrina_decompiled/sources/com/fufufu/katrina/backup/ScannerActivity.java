package com.fufufu.katrina.backup;

import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.FirebaseApp;
import java.util.ArrayList;
import java.util.Random;
/* loaded from: classes5.dex */
public class ScannerActivity extends AppCompatActivity {
    private FrameLayout fragment_container;
    private LinearLayout ln_base;
    private BottomNavigationView nav_view;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d00b2);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_base = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.fragment_container = (FrameLayout) findViewById(R.id.admsoloraya_res_0x7f0a01b6);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.admsoloraya_res_0x7f0a03c0);
        this.nav_view = bottomNavigationView;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: com.fufufu.katrina.backup.ScannerActivity.1
            @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.admsoloraya_res_0x7f0a03c7 /* 2131362759 */:
                        ScannerActivity.this._loadFragment(new CreateCodeFragmentActivity());
                        return true;
                    case R.id.admsoloraya_res_0x7f0a03c8 /* 2131362760 */:
                        ScannerActivity.this._loadFragment(new GetpropFragmentActivity());
                        return true;
                    case R.id.admsoloraya_res_0x7f0a03c9 /* 2131362761 */:
                    default:
                        return true;
                    case R.id.admsoloraya_res_0x7f0a03ca /* 2131362762 */:
                        ScannerActivity.this._loadFragment(new ScanFragmentActivity());
                        return true;
                }
            }
        });
    }

    private void initializeLogic() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().getDecorView().setSystemUiVisibility(8208);
        _loadFragment(new ScanFragmentActivity());
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.CAMERA") != -1) {
            return;
        }
        requestPermissions(new String[]{"android.permission.CAMERA"}, 1000);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    public void _loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.admsoloraya_res_0x7f0a01b6, fragment).addToBackStack(null).commit();
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
