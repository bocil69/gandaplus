package io.virtualapp.home.location;

import android.app.Activity;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.widget.EditText;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VirtualLocationManager;
import com.lody.virtual.remote.vloc.VLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.virtualapp.R;
import io.virtualapp.VCommends;
import io.virtualapp.abs.ui.VActivity;
import io.virtualapp.utils.StringUtils;

import static io.virtualapp.VCommends.EXTRA_LOCATION;

public class ChooseLocationActivity extends VActivity implements OnMapReadyCallback {

    private static final String TAG = "ChooseLocation";
    private static final int REQUEST_LOCATION_PERMISSION = 1201;

    private GoogleMap mMap;
    private MapView mapView;
    private SearchView mSearchView;
    private ListView mSearchResult;
    private View mSearchLayout;
    private TextView mLatText, mLngText, mAddressText, mCoordsText;
    private SearchResultAdapter mSearchAdapter;
    private View mMockImg, mMockingView, mSearchTip;
    private MaterialButton mMockBtn, mSaveBtn, mUseOriginalBtn, mManualInputBtn;
    private TextView mMockText;
    private String mCurPkg;
    private int mCurUserId;
    private VLocation mLocation;
    private boolean mMocking;
    private boolean mEditorOnly;
    private boolean mEditorDraftActive;
    private String mAddress;
    private String mCity = "Jakarta";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(Activity.RESULT_CANCELED);
        setContentView(R.layout.activity_mock_location);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        mSearchView    = findViewById(R.id.search_view);
        ImageView btnSearchAction = findViewById(R.id.btn_search_action);

        mSearchResult  = bind(R.id.search_results);
        mapView        = findViewById(R.id.map);
        mCoordsText    = bind(R.id.tv_coords);
        mMockImg       = bind(R.id.img_mock);
        mMockText      = bind(R.id.tv_mock);
        mSearchLayout  = bind(R.id.search_layout);
        mAddressText   = bind(R.id.tv_address);
        mMockingView   = bind(R.id.img_stop);
        mMockBtn       = findViewById(R.id.img_go_mock);
        mSaveBtn       = findViewById(R.id.btn_save_location);
        mUseOriginalBtn = null; // Removed from simplified layout
        mManualInputBtn = null; // Removed from simplified layout
        mSearchTip     = findViewById(R.id.tv_tip_search);

        if (btnSearchAction != null) {
            btnSearchAction.setOnClickListener(v -> performSearch());
        }

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        mSearchAdapter = new SearchResultAdapter();
        mSearchResult.setAdapter(mSearchAdapter);
        MapSearchResult.NULL.setAddress(getString(R.string.tip_no_find_points));

        mSearchResult.setOnItemClickListener((adapterView, view, i, l) -> {
            MapSearchResult res = mSearchAdapter.getItem(i);
            if (res != MapSearchResult.NULL && mMap != null) {
                mSearchView.clearFocus();
                hideSearchOverlay();
                gotoLocation(res.address, res.lat, res.lng, true);
            }
        });

        findViewById(R.id.img_stop_mock).setOnClickListener(v -> {
            restoreOriginalLocation();
        });

        mMockBtn.setOnClickListener(v -> onPlayClicked());

        // Simplified - only Play button and Stop button
        if (mSaveBtn != null) {
            mSaveBtn.setVisibility(View.GONE); // Hide save only button
        }

        // GPS location button - fix click handler
        ImageView imgLoc = findViewById(R.id.img_loc);
        if (imgLoc != null) {
            imgLoc.setOnClickListener(v -> {
                Log.d(TAG, "GPS button clicked");
                startGpsLocation();
            });
        }
        mMockingView.setOnClickListener(v -> restoreOriginalLocation());

        mCurPkg    = getIntent().getStringExtra(VCommends.EXTRA_PACKAGE);
        mCurUserId = getIntent().getIntExtra(VCommends.EXTRA_USERID, 0);
        mEditorOnly = getIntent().getBooleanExtra(VCommends.EXTRA_LOCATION_EDITOR_ONLY, false);
        if (mEditorOnly && mSaveBtn != null) {
            mSaveBtn.setVisibility(View.GONE);
        }

        mSearchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showSearchOverlay(mSearchAdapter == null || mSearchAdapter.isEmpty());
            } else if (shouldKeepSearchOverlayVisible()) {
                showSearchOverlay(false);
            } else {
                hideSearchOverlay();
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String q) {
                performSearch();
                return true;
            }
            @Override public boolean onQueryTextChange(String q) {
                if (TextUtils.isEmpty(q)) {
                    clearSearchResults();
                    if (mSearchView != null && mSearchView.hasFocus()) {
                        showSearchOverlay(true);
                    } else {
                        hideSearchOverlay();
                    }
                }
                return true;
            }
        });
    }

    private void performSearch() {
        String query = mSearchView != null && mSearchView.getQuery() != null
                ? mSearchView.getQuery().toString().trim() : "";
        if (TextUtils.isEmpty(query)) {
            clearSearchResults();
            showSearchOverlay(true);
            Toast.makeText(this, R.string.tip_input_keywords, Toast.LENGTH_SHORT).show();
            return;
        }
        showSearchOverlay(false);
        searchLocation(query);
    }

    // ───── GoogleMap ready ─────────────────────────────────────────────
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Camera move listener
        mMap.setOnCameraIdleListener(() -> {
            LatLng pos = mMap.getCameraPosition().target;
            gotoLocation(null, pos.latitude, pos.longitude, false);
        });

        // Restore previous location if coming back
        VLocation vLocation = getIntent().hasExtra(EXTRA_LOCATION)
                ? getIntent().getParcelableExtra(EXTRA_LOCATION) : null;
        if (vLocation != null) {
            mLocation = vLocation;
            boolean hasExistingLocation = hasUsableLocation(vLocation);
            if (mEditorOnly) {
                mEditorDraftActive = hasExistingLocation;
                updateMock(mEditorDraftActive);
            } else {
                updateMock(hasExistingLocation && VirtualLocationManager.get().isUseVirtualLocation(mCurUserId, mCurPkg));
            }
            if (hasExistingLocation) {
                LatLng ll = new LatLng(vLocation.getLatitude(), vLocation.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15f));
                gotoLocation(null, ll.latitude, ll.longitude, false);
            } else {
                LatLng jakarta = new LatLng(-6.2088, 106.8456);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 13f));
                gotoLocation(null, jakarta.latitude, jakarta.longitude, false);
            }
        } else {
            mLocation = new VLocation();
            // Default: Jakarta center
            LatLng jakarta = new LatLng(-6.2088, 106.8456);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 13f));
            gotoLocation(null, jakarta.latitude, jakarta.longitude, false);
            startGpsLocation();
        }
    }

    // ───── Core location logic ─────────────────────────────────────────
    private void gotoLocation(String address, double lat, double lng, boolean move) {
        mLocation.latitude  = StringUtils.doubleFor8(lat);
        mLocation.longitude = StringUtils.doubleFor8(lng);
        
        // Update coordinate displays
        String latStr = String.valueOf(mLocation.latitude);
        String lngStr = String.valueOf(mLocation.longitude);
        if (mLatText != null) mLatText.setText(latStr);
        if (mLngText != null) mLngText.setText(lngStr);
        if (mCoordsText != null) {
            mCoordsText.setText(String.format("Lat: %s | Lng: %s", latStr, lngStr));
        }

        if (move && mMap != null) {
            int zoom = (int) Math.max(mMap.getCameraPosition().zoom, 15);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom));
        }

        if (TextUtils.isEmpty(address)) {
            reverseGeocode(lat, lng);
        } else {
            setAddress(address);
        }
    }

    private void reverseGeocode(double lat, double lng) {
        new Thread(() -> {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                if (addresses != null && !addresses.isEmpty()) {
                    Address addr = addresses.get(0);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i <= addr.getMaxAddressLineIndex(); i++) {
                        if (i > 0) sb.append(", ");
                        sb.append(addr.getAddressLine(i));
                    }
                    if (!TextUtils.isEmpty(addr.getLocality())) mCity = addr.getLocality();
                    setAddress(sb.toString());
                } else {
                    setAddress(lat + ", " + lng);
                }
            } catch (IOException e) {
                Log.e("GMap", "reverseGeocode error", e);
                setAddress(lat + ", " + lng);
            }
        }).start();
    }

    private void searchLocation(String keyword) {
        new Thread(() -> {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocationName(keyword, 20);
                runOnUiThread(() -> {
                    mSearchAdapter.clear();
                    showSearchOverlay(false);
                    if (addresses == null || addresses.isEmpty()) {
                        mSearchAdapter.add(MapSearchResult.NULL);
                    } else {
                        for (Address addr : addresses) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i <= addr.getMaxAddressLineIndex(); i++) {
                                if (i > 0) sb.append(", ");
                                sb.append(addr.getAddressLine(i));
                            }
                            mSearchAdapter.add(new MapSearchResult(sb.toString(), addr.getLatitude(), addr.getLongitude()));
                        }
                    }
                    mSearchAdapter.notifyDataSetChanged();
                });
            } catch (IOException e) {
                Log.e("GMap", "searchLocation error", e);
                runOnUiThread(() -> {
                    mSearchAdapter.clear();
                    mSearchAdapter.add(MapSearchResult.NULL);
                    mSearchAdapter.notifyDataSetChanged();
                    showSearchOverlay(false);
                });
            }
        }).start();
    }

    @SuppressWarnings("MissingPermission")
    private void startGpsLocation() {
        if (!hasLocationPermission()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
            return;
        }
        
        // Show loading indicator
        Toast.makeText(this, "Mendeteksi lokasi GPS...", Toast.LENGTH_SHORT).show();
        
        try {
            // Use FusedLocationProvider for better accuracy
            com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient = 
                com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(this);
            
            // Request high accuracy location
            com.google.android.gms.location.LocationRequest locationRequest = 
                com.google.android.gms.location.LocationRequest.create()
                    .setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(1000)
                    .setFastestInterval(500)
                    .setNumUpdates(1)
                    .setExpirationDuration(30000);
            
            fusedLocationClient.requestLocationUpdates(locationRequest, new com.google.android.gms.location.LocationCallback() {
                @Override
                public void onLocationResult(com.google.android.gms.location.LocationResult result) {
                    if (result != null && result.getLastLocation() != null) {
                        android.location.Location loc = result.getLastLocation();
                        double lat = loc.getLatitude();
                        double lng = loc.getLongitude();
                        mLocation.accuracy = loc.getAccuracy();
                        mLocation.bearing = loc.getBearing();
                        mLocation.altitude = loc.getAltitude();
                        mLocation.latitude = lat;
                        mLocation.longitude = lng;
                        if (mMap != null) {
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 16f));
                        }
                        gotoLocation(null, lat, lng, false);
                        Toast.makeText(ChooseLocationActivity.this, "Lokasi asli terdeteksi", Toast.LENGTH_SHORT).show();
                    } else {
                        // Fallback to last known location
                        getLastKnownLocationFallback();
                    }
                    fusedLocationClient.removeLocationUpdates(this);
                }
                
                @Override
                public void onLocationAvailability(com.google.android.gms.location.LocationAvailability availability) {
                    if (!availability.isLocationAvailable()) {
                        getLastKnownLocationFallback();
                        fusedLocationClient.removeLocationUpdates(this);
                    }
                }
            }, android.os.Looper.getMainLooper());
            
        } catch (Exception e) {
            Log.e("GMap", "FusedLocation error, falling back", e);
            getLastKnownLocationFallback();
        }
    }
    
    private void getLastKnownLocationFallback() {
        try {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (lm == null) return;
            android.location.Location last = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (last == null) last = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (last != null) {
                double lat = last.getLatitude(), lng = last.getLongitude();
                mLocation.accuracy = (float) last.getAccuracy();
                mLocation.bearing = last.getBearing();
                mLocation.altitude = last.getAltitude();
                mLocation.latitude = lat;
                mLocation.longitude = lng;
                if (mMap != null) mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 16f));
                gotoLocation(null, lat, lng, false);
                Toast.makeText(this, "Menggunakan lokasi terakhir", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS tidak tersedia. Pastikan GPS aktif.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("GMap", "startGpsLocation error", e);
            Toast.makeText(this, "Gagal mendeteksi lokasi", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveLocationSelection(boolean enableFakeLocation) {
        if (!hasUsableLocation(mLocation)) {
            Toast.makeText(this, R.string.input_loc_error, Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEditorOnly) {
            mEditorDraftActive = enableFakeLocation;
        }
        if (!mEditorOnly && !TextUtils.isEmpty(mCurPkg)) {
            VirtualLocationManager.get().setLocation(mCurUserId, mCurPkg, mLocation);
            VirtualLocationManager.get().setMode(mCurUserId, mCurPkg,
                    enableFakeLocation ? VirtualLocationManager.MODE_USE_SELF : VirtualLocationManager.MODE_CLOSE);
            if (enableFakeLocation) {
                VirtualCore.get().killApp(mCurPkg, mCurUserId);
            }
        }
        updateMock(enableFakeLocation);
        finishWithResult(mLocation,
                enableFakeLocation ? VirtualLocationManager.MODE_USE_SELF : VirtualLocationManager.MODE_CLOSE);
    }

    private void onPlayClicked() {
        saveLocationSelection(true);
    }

    private void restoreOriginalLocation() {
        mLocation = new VLocation();
        mEditorDraftActive = false;
        setAddress(getString(R.string.real_location_enabled));
        updateMock(false);
        if (!mEditorOnly && !TextUtils.isEmpty(mCurPkg)) {
            VirtualLocationManager.get().setMode(mCurUserId, mCurPkg, VirtualLocationManager.MODE_CLOSE);
            VirtualLocationManager.get().setLocation(mCurUserId, mCurPkg, mLocation);
            VirtualCore.get().killApp(mCurPkg, mCurUserId);
        }
        finishWithResult(mLocation, VirtualLocationManager.MODE_CLOSE);
    }

    private void finishWithResult(VLocation location, int mode) {
        Intent intent = new Intent();
        intent.putExtra(VCommends.EXTRA_LOCATION, location);
        intent.putExtra(VCommends.EXTRA_LOCATION_MODE, mode);
        intent.putExtra(VCommends.EXTRA_PACKAGE, mCurPkg);
        intent.putExtra(VCommends.EXTRA_USERID, mCurUserId);
        intent.putExtra(VCommends.EXTRA_LOCATION_ADDRESS, mAddress);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private boolean hasLocationPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != REQUEST_LOCATION_PERMISSION) {
            return;
        }
        boolean granted = false;
        if (grantResults != null) {
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    granted = true;
                    break;
                }
            }
        }
        if (granted) {
            startGpsLocation();
        } else {
            Toast.makeText(this, R.string.location_permission_denied, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hasUsableLocation(VLocation location) {
        return location != null && !location.isEmpty()
                && isValidCoordinateRange(location.getLatitude(), location.getLongitude());
    }

    private boolean isValidCoordinateRange(double lat, double lng) {
        return lat >= -90d && lat <= 90d && lng >= -180d && lng <= 180d;
    }

    private void updateMock(boolean mock) {
        mMocking = mock;
        if (mMockImg != null) {
            mMockImg.setSelected(mock);
            mMockImg.setVisibility(View.VISIBLE);
        }
        if (mock) {
            if (mMockText != null) {
                mMockText.setText(R.string.mocking);
                mMockText.setTextColor(ContextCompat.getColor(this, R.color.md3_primary));
            }
            // Show stop overlay when mocking is active
            if (mMockingView != null) mMockingView.setVisibility(View.VISIBLE);
            if (mMockBtn != null) mMockBtn.setText(R.string.update_fake_location);
        } else {
            if (mMockText != null) {
                mMockText.setText(R.string.no_mock);
                mMockText.setTextColor(ContextCompat.getColor(this, R.color.md3_on_surface_variant));
            }
            // Hide stop overlay when not mocking
            if (mMockingView != null) mMockingView.setVisibility(View.GONE);
            if (mMockBtn != null) mMockBtn.setText(R.string.start_fake_location);
        }
        if (mSearchView != null) {
            mSearchView.setEnabled(true);
            mSearchView.setFocusable(true);
            mSearchView.setFocusableInTouchMode(true);
        }
        if (mMockText != null) mMockText.setSelected(mock);
    }

    private void setAddress(String text) {
        runOnUiThread(() -> {
            mAddress = text;
            if (mAddressText != null) mAddressText.setText(text);
        });
    }

    private void clearSearchResults() {
        if (mSearchAdapter != null) {
            mSearchAdapter.clear();
            mSearchAdapter.notifyDataSetChanged();
        }
    }

    private void showSearchOverlay(boolean showTip) {
        if (mSearchLayout != null) {
            mSearchLayout.setVisibility(View.VISIBLE);
        }
        if (mSearchTip != null) {
            mSearchTip.setVisibility(showTip ? View.VISIBLE : View.GONE);
        }
    }

    private void hideSearchOverlay() {
        if (mSearchLayout != null) {
            mSearchLayout.setVisibility(View.GONE);
        }
    }

    private boolean shouldKeepSearchOverlayVisible() {
        CharSequence query = mSearchView != null ? mSearchView.getQuery() : null;
        return !TextUtils.isEmpty(query)
                || (mSearchAdapter != null && mSearchAdapter.getCount() > 0);
    }

    // ───── Input dialog ────────────────────────────────────────────────
    private void showInputWindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view1 = getLayoutInflater().inflate(R.layout.dialog_change_loc, null);
        builder.setView(view1);
        AlertDialog dialog = builder.create();
        
        // MD3 styling - transparent background for CardView effect
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        
        dialog.setCanceledOnTouchOutside(false);
        EditText editText1 = view1.findViewById(R.id.edt_lat);
        editText1.setText(StringUtils.doubleFor8String(mLocation.getLatitude()));
        EditText editText2 = view1.findViewById(R.id.edt_lon);
        editText2.setText(StringUtils.doubleFor8String(mLocation.getLongitude()));
        dialog.setCancelable(false);
        view1.findViewById(R.id.btn_cancel).setOnClickListener(v2 -> dialog.dismiss());
        view1.findViewById(R.id.btn_ok).setOnClickListener(v2 -> {
            try {
                String latStr = editText1.getText() != null ? editText1.getText().toString() : "";
                String lonStr = editText2.getText() != null ? editText2.getText().toString() : "";
                double lat = Double.parseDouble(latStr);
                double lon = Double.parseDouble(lonStr);
                if (!isValidCoordinateRange(lat, lon)) {
                    Toast.makeText(this, R.string.input_loc_error, Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.dismiss();
                gotoLocation(null, lat, lon, true);
            } catch (Exception e) {
                Toast.makeText(this, R.string.input_loc_error, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    // ───── Lifecycle (Google Maps requires all of these) ───────────────
    @Override protected void onResume()  { super.onResume();  mapView.onResume();  }
    @Override protected void onPause()   { super.onPause();   mapView.onPause();   }
    @Override protected void onDestroy() { super.onDestroy(); mapView.onDestroy(); }
    @Override protected void onSaveInstanceState(Bundle out) {
        super.onSaveInstanceState(out); mapView.onSaveInstanceState(out);
    }
    @Override public void onLowMemory() { super.onLowMemory(); mapView.onLowMemory(); }

    // ───── Inner class ─────────────────────────────────────────────────
    private static class MapSearchResult {
        String address;
        double lat, lng;
        String city;
        static final MapSearchResult NULL = new MapSearchResult();
        MapSearchResult() {}
        MapSearchResult(String address) { this.address = address; }
        MapSearchResult(String address, double lat, double lng) {
            this.address = address; this.lat = lat; this.lng = lng;
        }
        void setAddress(String a) { this.address = a; }
        void setCity(String c)    { this.city = c; }
        @Override public String toString() { return address != null ? address : ""; }
    }

    private class SearchResultAdapter extends ArrayAdapter<MapSearchResult> {

        SearchResultAdapter() {
            super(ChooseLocationActivity.this, 0, new ArrayList<>());
        }

        @Override
        public View getView(int position, View convertView, android.view.ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_mock_search_result, parent, false);
            }

            MapSearchResult item = getItem(position);
            TextView index = view.findViewById(R.id.tv_search_index);
            TextView title = view.findViewById(R.id.tv_search_title);
            TextView subtitle = view.findViewById(R.id.tv_search_subtitle);

            if (item == null) {
                index.setText("-");
                title.setText("");
                subtitle.setText("");
                return view;
            }

            if (item == MapSearchResult.NULL) {
                index.setText("!");
                title.setText(getString(R.string.tip_no_find_points));
                subtitle.setText(R.string.tip_search_empty_helper);
                return view;
            }

            index.setText(String.valueOf(position + 1));
            String address = item.address == null ? "" : item.address.trim();
            String[] parts = address.split(",", 2);
            String headline = parts.length > 0 ? parts[0].trim() : address;
            String details = parts.length > 1 ? parts[1].trim() : String.format(Locale.US, "%.6f, %.6f", item.lat, item.lng);

            if (TextUtils.isEmpty(headline)) {
                headline = String.format(Locale.US, "Pin %.6f, %.6f", item.lat, item.lng);
            }

            title.setText(headline);
            subtitle.setText(details);
            return view;
        }
    }
}
