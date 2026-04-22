package io.virtualapp.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lody.virtual.client.ipc.VirtualLocationManager;
import com.lody.virtual.remote.vloc.VLocation;

import java.util.List;
import java.util.Locale;

import io.virtualapp.R;
import io.virtualapp.abs.ui.VUiKit;
import io.virtualapp.home.models.AppData;
import io.virtualapp.home.models.MultiplePackageAppData;
import io.virtualapp.home.models.PackageAppData;
import io.virtualapp.widgets.LabelView;
import io.virtualapp.widgets.LauncherIconView;

/**
 * Launchpad adapter — MD3 redesign with slot label and clone badge.
 */
public class LaunchpadAdapter extends RecyclerView.Adapter<LaunchpadAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<AppData> mList;
    private OnAppClickListener mAppClickListener;

    public LaunchpadAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void add(AppData data) {
        if (mList == null) return;
        mList.add(data);
        notifyItemInserted(mList.size() - 1);
    }

    public void replace(int index, AppData data) {
        if (mList == null) return;
        mList.set(index, data);
        notifyItemChanged(index);
    }

    public void remove(AppData data) {
        if (mList != null && mList.remove(data)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_launcher_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppData data = mList.get(position);

        // Icon
        holder.iconView.setImageDrawable(data.getIcon());

        // Name
        holder.nameView.setText(data.getName());

        // First-open dot
        holder.firstOpenDot.setVisibility(
                data.isFirstOpen() && !data.isLoading() ? View.VISIBLE : View.INVISIBLE);

        // Clone identity label/badge intentionally hidden in this batch.
        int userId = 0;
        if (data instanceof MultiplePackageAppData) {
            MultiplePackageAppData multiData = (MultiplePackageAppData) data;
            userId = multiData.userId;

            if (holder.slotLabel != null) {
                holder.slotLabel.setVisibility(View.GONE);
            }

            if (holder.cloneBadge != null) {
                holder.cloneBadge.setVisibility(View.GONE);
            }

            // Legacy compat label (hide in MD3)
            if (holder.spaceLabelView != null) {
                holder.spaceLabelView.setVisibility(View.GONE);
            }

        } else if (data instanceof PackageAppData) {
            if (holder.slotLabel != null) {
                holder.slotLabel.setVisibility(View.GONE);
            }
            if (holder.cloneBadge != null) {
                holder.cloneBadge.setVisibility(View.GONE);
            }
            if (holder.spaceLabelView != null) {
                holder.spaceLabelView.setVisibility(View.GONE);
            }
        } else {
            // AddAppButton or other
            if (holder.slotLabel != null) holder.slotLabel.setVisibility(View.GONE);
            if (holder.cloneBadge != null) holder.cloneBadge.setVisibility(View.GONE);
            if (holder.spaceLabelView != null) holder.spaceLabelView.setVisibility(View.GONE);
        }

        // Show Device Info Preview
        if (holder.deviceInfoLabel != null) {
            if (data instanceof PackageAppData || data instanceof MultiplePackageAppData) {
                com.lody.virtual.remote.VDeviceConfig config = com.lody.virtual.client.ipc.VDeviceManager.get().getDeviceConfig(userId);
                if (config != null && config.enable) {
                    holder.deviceInfoLabel.setVisibility(View.VISIBLE);
                    String model = config.getProp("MODEL");
                    if (model == null || model.isEmpty()) model = config.getProp("ro.product.model");
                    String imei = config.deviceId != null ? config.deviceId : "-";
                    holder.deviceInfoLabel.setText(
                            (model != null ? model : "Unknown") + "  ·  " + imei.substring(0, Math.min(8, imei.length())) + "..."
                    );
                } else {
                    holder.deviceInfoLabel.setVisibility(View.GONE);
                }
            } else {
                holder.deviceInfoLabel.setVisibility(View.GONE);
            }
        }

        // Show Location Spoof Chip
        if (holder.locChip != null && holder.locText != null) {
            boolean locActive = false;
            if (data instanceof PackageAppData || data instanceof MultiplePackageAppData) {
                String pkg = (data instanceof MultiplePackageAppData)
                        ? ((MultiplePackageAppData) data).packageName
                        : ((PackageAppData) data).packageName;
                boolean isMocking = VirtualLocationManager.get().isUseVirtualLocation(userId, pkg);
                if (isMocking) {
                    VLocation loc = VirtualLocationManager.get().getLocation(userId, pkg);
                    if (loc != null && (loc.latitude != 0 || loc.longitude != 0)) {
                        locActive = true;
                        holder.locChip.setVisibility(View.VISIBLE);
                        holder.locText.setText(String.format(Locale.US, "%.5f, %.5f",
                                loc.latitude, loc.longitude));
                    }
                }
            }
            if (!locActive) {
                holder.locChip.setVisibility(View.GONE);
            }
        }

        // Loading state
        if (data.isLoading()) {
            startLoadingAnimation(holder.iconView);
        } else {
            holder.iconView.setProgress(100, false);
        }

        // Click
        final int pos = position;
        holder.itemView.setOnClickListener(v -> {
            if (mAppClickListener != null) {
                mAppClickListener.onAppClick(pos, data);
            }
        });

        // Long-click for context menu
        holder.itemView.setOnLongClickListener(v -> {
            if (mAppClickListener != null) {
                mAppClickListener.onAppLongClick(pos, data);
            }
            return true;
        });
    }

    private void startLoadingAnimation(LauncherIconView iconView) {
        iconView.setProgress(40, true);
        VUiKit.defer().when(() -> {
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).done((res) -> iconView.setProgress(80, true));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public List<AppData> getList() {
        return mList;
    }

    public void setList(List<AppData> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setAppClickListener(OnAppClickListener listener) {
        this.mAppClickListener = listener;
    }

    public void moveItem(int pos, int targetPos) {
        if (mList == null) return;
        AppData model = mList.remove(pos);
        mList.add(targetPos, model);
        notifyItemMoved(pos, targetPos);
    }

    public void refresh(AppData model) {
        if (mList == null) return;
        int index = mList.indexOf(model);
        if (index >= 0) {
            notifyItemChanged(index);
        }
    }

    public interface OnAppClickListener {
        void onAppClick(int position, AppData model);
        default void onAppLongClick(int position, AppData model) {}
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LauncherIconView iconView;
        TextView nameView;
        TextView slotLabel;
        TextView cloneBadge;
        LabelView spaceLabelView;   // legacy compat
        View firstOpenDot;
        TextView deviceInfoLabel;
        View locChip;
        TextView locText;

        ViewHolder(View itemView) {
            super(itemView);
            iconView       = itemView.findViewById(R.id.item_app_icon);
            nameView       = itemView.findViewById(R.id.item_app_name);
            slotLabel      = itemView.findViewById(R.id.item_app_slot_label);
            deviceInfoLabel = itemView.findViewById(R.id.item_app_device_info);
            cloneBadge     = null;
            spaceLabelView = itemView.findViewById(R.id.item_app_space_idx);
            firstOpenDot   = itemView.findViewById(R.id.item_first_open_dot);
            locChip        = itemView.findViewById(R.id.item_loc_chip);
            locText        = itemView.findViewById(R.id.item_loc_text);
        }
    }
}
