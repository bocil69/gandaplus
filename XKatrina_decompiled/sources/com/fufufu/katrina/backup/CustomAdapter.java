package com.fufufu.katrina.backup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes5.dex */
public class CustomAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private int layoutResource;

    public CustomAdapter(Context context, int i, List<String> list) {
        super(context, i, list);
        this.inflater = LayoutInflater.from(context);
        this.layoutResource = i;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @NonNull
    public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(this.layoutResource, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a016f)).setText(getItem(i));
        return view;
    }
}
