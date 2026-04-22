package io.virtualapp.home.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.virtualapp.R;
import io.virtualapp.abs.ui.BaseAdapterPlus;
import io.virtualapp.home.models.LocationData;

public class AppLocationAdapter extends BaseAdapterPlus<LocationData> {
    public AppLocationAdapter(Context context) {
        super(context);
    }

    @Override
    protected View createView(int position, ViewGroup parent) {
        View view = inflate(R.layout.item_location_app, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected void attach(View view, LocationData item, int position) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.icon.setImageDrawable(item.icon);
        if (item.userId > 0) {
            viewHolder.label.setText(item.name + " (" + (item.userId + 1) + ")");
        } else {
            viewHolder.label.setText(item.name);
        }
        if (item.location != null && item.mode != 0
                && (item.location.latitude != 0 || item.location.longitude != 0)) {
            viewHolder.location.setText(String.format(
                    java.util.Locale.US,
                    "\uD83D\uDCCD %.5f, %.5f",
                    item.location.latitude, item.location.longitude));
            viewHolder.location.setTextColor(0xFF4CAF50); // hijau
        } else {
            viewHolder.location.setText("📍 Real Location");
            viewHolder.location.setTextColor(0xFFB0B8C8); // abu muda
        }
    }

    static class ViewHolder extends BaseAdapterPlus.BaseViewHolder {
        public ViewHolder(View view) {
            super(view);
            icon = $(R.id.item_app_icon);
            label = $(R.id.item_app_name);
            location = $(R.id.item_location);
        }

        final ImageView icon;
        final TextView label, location;
    }
}
