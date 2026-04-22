package io.virtualapp.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.lody.virtual.client.ipc.VDeviceManager;
import com.lody.virtual.client.ipc.VLocationManager;
import com.lody.virtual.client.ipc.VirtualLocationManager;
import com.lody.virtual.remote.VDeviceConfig;
import com.lody.virtual.remote.vloc.VLocation;

import java.util.Locale;

import io.virtualapp.R;
import io.virtualapp.VCommends;
import io.virtualapp.home.location.ChooseLocationActivity;
import io.virtualapp.home.repo.CloneSpoofRepository;

/**
 * Per-clone device identity setup — with INTEGRATED Fake Location.
 * 
 * <p>Flow:
 * <ol>
 *   <li>User sets Device Spoof (IMEI, Android ID, etc.)</li>
 *   <li>User sets Fake Location (Lat/Lng) - INTEGRATED</li>
 *   <li>Save: Persist BOTH device spoof AND location to all layers</li>
 *   <li>Launch: App starts with complete spoof profile</li>
 * </ol>
 */
public class CloneOnboardingActivity extends AppCompatActivity {

    public static final String EXTRA_PKG    = "pkg";
    public static final String EXTRA_USER   = "userId";
    public static final String EXTRA_LABEL  = "label";
    public static final int    REQUEST_CODE = 9901;

    private static final String TAG = "CloneOnboarding";

    private String        mPkg;
    private int           mUserId;
    private VDeviceConfig mConfig;
    private VLocation     mLocation;  // NEW: Integrated location

    // UI fields - Device
    private EditText edtCloneName, edtImei, edtAndroidId, edtMac,
                     edtModel, edtBrand, edtSerial, edtImsi, edtFingerprint, edtAndroidRelease;
     
    // UI fields - Location (NEW)
    private EditText edtLatitude, edtLongitude, edtLocationName;
    private Button   btnPickOnMap, btnUseOriginalLocation;
    private android.view.View layoutLocationStatus;
    private TextView tvTitle, tvSubtitle, tvFooter, tvLocationStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clone_onboarding);

        mPkg    = getIntent().getStringExtra(EXTRA_PKG);
        mUserId = getIntent().getIntExtra(EXTRA_USER, 0);
        String label = getIntent().getStringExtra(EXTRA_LABEL);

        // Guard — should never happen in normal flow
        if (TextUtils.isEmpty(mPkg)) {
            Toast.makeText(this, "Invalid clone configuration.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED);
            finish();
            return;
        }

        initUI(label);
        loadExistingData();
        setupButtons(label);
    }

    private void initUI(String label) {
        tvTitle = findViewById(R.id.onboard_title);
        tvSubtitle = findViewById(R.id.onboard_subtitle);
        tvFooter = findViewById(R.id.onboard_footer_note);
        updateHeaderCopy(label);

        // Device fields
        edtCloneName   = findViewById(R.id.onboard_edt_clone_name);
        edtImei        = findViewById(R.id.onboard_edt_imei);
        edtAndroidId   = findViewById(R.id.onboard_edt_android_id);
        edtMac         = findViewById(R.id.onboard_edt_mac);
        edtModel       = findViewById(R.id.onboard_edt_model);
        edtBrand       = findViewById(R.id.onboard_edt_brand);
        edtSerial      = findViewById(R.id.onboard_edt_serial);
        edtAndroidRelease = findViewById(R.id.onboard_edt_android_release);
        edtImsi        = findViewById(R.id.onboard_edt_imsi);
        edtFingerprint = findViewById(R.id.onboard_edt_fingerprint);

        // NEW: Location fields
        layoutLocationStatus = findViewById(R.id.layout_location_status);
        tvLocationStatus = findViewById(R.id.onboard_tv_location_status);
        edtLatitude     = findViewById(R.id.onboard_edt_latitude);
        edtLongitude    = findViewById(R.id.onboard_edt_longitude);
        btnPickOnMap    = findViewById(R.id.onboard_btn_pick_map);
        btnUseOriginalLocation = findViewById(R.id.onboard_btn_use_original);
    }

    private void loadExistingData() {
        // Load custom clone name
        SharedPreferences namePrefs = getSharedPreferences("clone_names", Context.MODE_PRIVATE);
        String customName = namePrefs.getString(mPkg + ":" + mUserId, getIntent().getStringExtra(EXTRA_LABEL));
        if (edtCloneName != null) edtCloneName.setText(customName);

        // Load existing spoof
        boolean isEdit = getIntent().getBooleanExtra("is_edit", false);
        CloneSpoofRepository repo = CloneSpoofRepository.get(this);
        
        if (isEdit && repo.isOnboarded(mPkg, mUserId)) {
            mConfig = VDeviceManager.get().getDeviceConfig(mUserId);
            if (mConfig == null) {
                repo.applySpoof(mPkg, mUserId);
                mConfig = VDeviceManager.get().getDeviceConfig(mUserId);
            }
            
            // NEW: Load existing location
            mLocation = VLocationManager.get().getLocation(mPkg, mUserId);
            if (mLocation != null && mLocation.isEmpty()) {
                mLocation = null;
            }
        }
        
        if (mConfig == null) {
            mConfig = CloneSpoofRepository.generateRandomSpoof(mUserId);
        }
        fillFields();
        applyLocationUiState();
    }

    private void setupButtons(String label) {
        Button btnRegenerate = findViewById(R.id.onboard_btn_regenerate);
        Button btnStart      = findViewById(R.id.onboard_btn_start);

        if (btnStart != null) {
            btnStart.setText(isEditMode() ? R.string.clone_onboarding_save_changes : R.string.clone_onboarding_save_and_start);
        }

        if (btnRegenerate != null) {
            btnRegenerate.setOnClickListener(v -> {
                mConfig = CloneSpoofRepository.generateRandomSpoof(mUserId);
                fillFields();
            });
        }
        
        if (btnStart != null) {
            btnStart.setOnClickListener(v -> saveAndFinish(label));
        }

        // NEW: Map picker button
        if (btnPickOnMap != null) {
            btnPickOnMap.setOnClickListener(v -> {
                Intent intent = new Intent(this, ChooseLocationActivity.class);
                intent.putExtra(VCommends.EXTRA_PACKAGE, mPkg);
                intent.putExtra(VCommends.EXTRA_USERID, mUserId);
                intent.putExtra(VCommends.EXTRA_LOCATION_EDITOR_ONLY, true);
                if (mLocation != null && !mLocation.isEmpty()) {
                    intent.putExtra(VCommends.EXTRA_LOCATION, mLocation);
                }
                startActivityForResult(intent, 9902);
            });
        }

        if (btnUseOriginalLocation != null) {
            btnUseOriginalLocation.setOnClickListener(v -> {
                clearLocationFields();
                mLocation = null;
                applyLocationUiState();
            });
        }

        if (edtAndroidRelease != null) {
            edtAndroidRelease.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    syncFingerprintPreview();
                }
            });
        }

        if (edtBrand != null) {
            edtBrand.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    syncFingerprintPreview();
                }
            });
        }

        if (edtLatitude != null) {
            edtLatitude.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    syncLocationDraftFromInputs();
                }
            });
        }

        if (edtLongitude != null) {
            edtLongitude.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    syncLocationDraftFromInputs();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9902 && resultCode == RESULT_OK && data != null) {
            // Location picked from map
            VLocation vLoc = data.getParcelableExtra(io.virtualapp.VCommends.EXTRA_LOCATION);
            if (vLoc != null && !vLoc.isEmpty()) {
                mLocation = vLoc;
                setText(edtLatitude, String.valueOf(vLoc.latitude));
                setText(edtLongitude, String.valueOf(vLoc.longitude));
            } else {
                mLocation = null;
                clearLocationFields();
            }
            applyLocationUiState();
        }
    }

    private void applyLocationUiState() {
        boolean active = hasDraftLocation();
        if (layoutLocationStatus != null) {
            layoutLocationStatus.setVisibility(android.view.View.VISIBLE);
        }
        if (tvLocationStatus != null) {
            tvLocationStatus.setText(active ? R.string.fake_location_active_status : R.string.fake_location_inactive_status);
            tvLocationStatus.setTextColor(ContextCompat.getColor(this,
                    active ? R.color.holo_green_dark : R.color.md3_on_surface_variant));
        }
        if (btnPickOnMap != null) {
            btnPickOnMap.setEnabled(true);
            btnPickOnMap.setAlpha(1f);
            btnPickOnMap.setText(active ? getString(R.string.edit_location_on_map) : getString(R.string.pick_location_on_map));
        }
        if (btnUseOriginalLocation != null) {
            btnUseOriginalLocation.setEnabled(active);
            btnUseOriginalLocation.setAlpha(active ? 1f : 0.6f);
            btnUseOriginalLocation.setText(getString(R.string.use_original_location));
        }
    }

    private void fillFields() {
        if (mConfig == null) return;
        
        // Device fields
        setText(edtImei,        mConfig.deviceId);
        setText(edtAndroidId,   mConfig.androidId);
        setText(edtMac,         mConfig.wifiMac);
        setText(edtModel,       mConfig.getProp("MODEL"));
        setText(edtBrand,       mConfig.getProp("BRAND"));
        setText(edtSerial,      mConfig.serial);
        setText(edtAndroidRelease, resolveAndroidRelease(mConfig));
        setText(edtImsi,        !TextUtils.isEmpty(mConfig.subscriberId) ? mConfig.subscriberId : mConfig.iccId);
        setText(edtFingerprint, mConfig.getProp("FINGERPRINT"));
        
        // NEW: Location fields
        if (mLocation != null && !mLocation.isEmpty()) {
            setText(edtLatitude,  String.valueOf(mLocation.getLatitude()));
            setText(edtLongitude, String.valueOf(mLocation.getLongitude()));
        }
    }

    private void saveAndFinish(String label) {
        if (mConfig == null) {
            showError("Configuration error — please regenerate.");
            return;
        }

        try {
            // Save Device Spoof
            mConfig.deviceId  = text(edtImei);
            mConfig.androidId = text(edtAndroidId);
            mConfig.wifiMac   = text(edtMac);
            mConfig.serial    = text(edtSerial);
            mConfig.subscriberId = text(edtImsi);
            if (TextUtils.isEmpty(mConfig.networkOperator) && !TextUtils.isEmpty(mConfig.subscriberId)) {
                int operatorLength = Math.min(6, mConfig.subscriberId.length());
                if (operatorLength >= 5) {
                    mConfig.networkOperator = mConfig.subscriberId.substring(0, operatorLength);
                }
            }
            mConfig.setProp("MODEL",       text(edtModel));
            mConfig.setProp("BRAND",       text(edtBrand));
            mConfig.setProp("MANUFACTURER", nonEmpty(mConfig.getProp("MANUFACTURER"), text(edtBrand)));
            mConfig.setProp("ID", nonEmpty(mConfig.getProp("ID"), "UP1A.231005.007"));
            mConfig.setProp("DISPLAY", nonEmpty(mConfig.getProp("DISPLAY"), mConfig.getProp("ID")));
            String androidRelease = text(edtAndroidRelease);
            if (TextUtils.isEmpty(androidRelease)) {
                androidRelease = resolveAndroidRelease(mConfig);
            }
            String fingerprint = text(edtFingerprint);
            if (TextUtils.isEmpty(fingerprint)) {
                fingerprint = buildFingerprintPreview(androidRelease);
                setText(edtFingerprint, fingerprint);
            }
            mConfig.setProp("FINGERPRINT", fingerprint);
            mConfig.setProp("ro.build.fingerprint", fingerprint);
            mConfig.setProp("SERIAL",      mConfig.serial);
            mConfig.setProp("ro.build.version.release", androidRelease);
            mConfig.setProp("ro.build.id", mConfig.getProp("ID"));
            mConfig.setProp("ro.build.display.id", mConfig.getProp("DISPLAY"));
            mConfig.setProp("ro.product.model", text(edtModel));
            mConfig.setProp("ro.product.manufacturer", mConfig.getProp("MANUFACTURER"));
            mConfig.enable = true;

            // Persist Device
            VDeviceManager.get().updateDeviceConfig(mUserId, mConfig);
            CloneSpoofRepository repo = CloneSpoofRepository.get(this);
            repo.saveSpoof(mPkg, mUserId, mConfig);
            
            // NEW: Save Fake Location
            String latStr = text(edtLatitude);
            String lngStr = text(edtLongitude);

            if (!TextUtils.isEmpty(latStr) || !TextUtils.isEmpty(lngStr)) {
                if (TextUtils.isEmpty(latStr) || TextUtils.isEmpty(lngStr)) {
                    showError("Isi latitude dan longitude sekaligus, atau kosongkan keduanya untuk lokasi asli.");
                    return;
                }
                try {
                    double lat = Double.parseDouble(latStr);
                    double lng = Double.parseDouble(lngStr);
                    if (!isValidCoordinate(lat, lng)) {
                        showError("Koordinat tidak valid. Latitude harus -90..90 dan longitude -180..180.");
                        return;
                    }
                    mLocation = new VLocation(lat, lng);

                    // Enable fake location mode
                    VirtualLocationManager.get().setMode(mUserId, mPkg, 
                        VirtualLocationManager.MODE_USE_SELF);
                    VirtualLocationManager.get().setLocation(mUserId, mPkg, mLocation);
                    mLocation = new VLocation(lat, lng);
                } catch (NumberFormatException e) {
                    showError("Invalid coordinates. Please check latitude/longitude.");
                    return;
                }
            } else {
                mLocation = null;
                VirtualLocationManager.get().setMode(mUserId, mPkg, VirtualLocationManager.MODE_CLOSE);
                VirtualLocationManager.get().setLocation(mUserId, mPkg, new VLocation());
            }
            
            // Mark onboarded
            repo.markOnboarded(mPkg, mUserId);

            // Save custom clone name
            String newName = text(edtCloneName);
            if (!newName.isEmpty()) {
                getSharedPreferences("clone_names", Context.MODE_PRIVATE)
                        .edit().putString(mPkg + ":" + mUserId, newName).apply();
            }

            showSuccess(isEditMode() ? "Perubahan clone berhasil disimpan" : "Profil clone tersimpan dan siap dipakai");

            // Return result
            Intent result = new Intent();
            result.putExtra(EXTRA_PKG,  mPkg);
            result.putExtra(EXTRA_USER, mUserId);
            result.putExtra("is_edit",  getIntent().getBooleanExtra("is_edit", false));
            setResult(RESULT_OK, result);
            finish();
            
        } catch (Exception e) {
            showError("Failed to save: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ── Error Handling Helpers ───────────────────────────────────────

    private void showError(String message) {
        Toast.makeText(this, "❌ " + message, Toast.LENGTH_LONG).show();
    }
    
    private void showSuccess(String message) {
        Toast.makeText(this, "✅ " + message, Toast.LENGTH_SHORT).show();
    }

    private boolean isEditMode() {
        return getIntent().getBooleanExtra("is_edit", false);
    }

    private void updateHeaderCopy(String label) {
        String safeLabel = TextUtils.isEmpty(label) ? "Clone" : label;
        if (tvTitle != null) {
            tvTitle.setText(isEditMode() ? R.string.clone_onboarding_title_edit : R.string.clone_onboarding_title_prepare);
        }
        if (tvSubtitle != null) {
            tvSubtitle.setText(getString(isEditMode()
                    ? R.string.clone_onboarding_subtitle_edit
                    : R.string.clone_onboarding_subtitle_prepare, safeLabel));
        }
        if (tvFooter != null) {
            tvFooter.setText(isEditMode()
                    ? R.string.clone_onboarding_footer_edit
                    : R.string.clone_onboarding_footer_prepare);
        }
    }

    private void setText(EditText edt, String val) {
        if (edt != null && !TextUtils.isEmpty(val)) edt.setText(val);
    }

    private String text(EditText edt) {
        return (edt != null && edt.getText() != null)
                ? edt.getText().toString().trim() : "";
    }

    private void clearLocationFields() {
        if (edtLatitude != null) edtLatitude.setText("");
        if (edtLongitude != null) edtLongitude.setText("");
    }

    private boolean isValidCoordinate(double lat, double lng) {
        return lat >= -90d && lat <= 90d && lng >= -180d && lng <= 180d;
    }

    private boolean hasDraftLocation() {
        if (mLocation != null && !mLocation.isEmpty() && isValidCoordinate(mLocation.getLatitude(), mLocation.getLongitude())) {
            return true;
        }
        String latStr = text(edtLatitude);
        String lngStr = text(edtLongitude);
        if (TextUtils.isEmpty(latStr) || TextUtils.isEmpty(lngStr)) {
            return false;
        }
        try {
            return isValidCoordinate(Double.parseDouble(latStr), Double.parseDouble(lngStr));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void syncLocationDraftFromInputs() {
        String latStr = text(edtLatitude);
        String lngStr = text(edtLongitude);
        if (TextUtils.isEmpty(latStr) && TextUtils.isEmpty(lngStr)) {
            mLocation = null;
            applyLocationUiState();
            return;
        }
        if (TextUtils.isEmpty(latStr) || TextUtils.isEmpty(lngStr)) {
            applyLocationUiState();
            return;
        }
        try {
            double lat = Double.parseDouble(latStr);
            double lng = Double.parseDouble(lngStr);
            if (isValidCoordinate(lat, lng)) {
                if (mLocation == null) {
                    mLocation = new VLocation();
                }
                mLocation.latitude = lat;
                mLocation.longitude = lng;
            }
        } catch (NumberFormatException ignored) {
            // Keep the current draft untouched until the input becomes valid.
        }
        applyLocationUiState();
    }

    private String resolveAndroidRelease(VDeviceConfig config) {
        String release = config.getProp("ro.build.version.release");
        if (!TextUtils.isEmpty(release)) {
            return release;
        }
        String fingerprint = config.getProp("FINGERPRINT");
        if (!TextUtils.isEmpty(fingerprint)) {
            String[] parts = fingerprint.split(":", 2);
            if (parts.length == 2) {
                String[] versionParts = parts[1].split("/");
                if (versionParts.length > 0 && !TextUtils.isEmpty(versionParts[0])) {
                    return versionParts[0];
                }
            }
        }
        return android.os.Build.VERSION.RELEASE;
    }

    private String buildFingerprintPreview(String release) {
        if (mConfig == null) {
            return "";
        }
        String brand = nonEmpty(text(edtBrand), mConfig.getProp("BRAND"));
        String product = nonEmpty(mConfig.getProp("PRODUCT"), brand.toLowerCase(Locale.US));
        String device = nonEmpty(mConfig.getProp("DEVICE"), product);
        String buildId = nonEmpty(mConfig.getProp("ID"), "UP1A.231005.007");
        String incremental = nonEmpty(mConfig.getProp("BOOTLOADER"), buildId);
        String versionRelease = nonEmpty(release, resolveAndroidRelease(mConfig));
        return brand + "/" + product + "/" + device + ":" + versionRelease + "/"
                + buildId + "/" + incremental + ":user/release-keys";
    }

    private void syncFingerprintPreview() {
        if (edtFingerprint == null || mConfig == null) {
            return;
        }
        String preview = buildFingerprintPreview(text(edtAndroidRelease));
        if (!TextUtils.isEmpty(preview)) {
            edtFingerprint.setText(preview);
            edtFingerprint.setSelection(preview.length());
        }
    }

    private String nonEmpty(String primary, String fallback) {
        return !TextUtils.isEmpty(primary) ? primary : fallback;
    }

    private abstract static class SimpleTextWatcher implements TextWatcher {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
    }
}
