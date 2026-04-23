package com.fufufu.katrina.backup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
/* loaded from: classes5.dex */
public class ProcessItemArrayAdapter extends ArrayAdapter<ProcessItem> {
    private final Context context;
    private int defaultColor;
    private final List<ProcessItem> values;

    public ProcessItemArrayAdapter(Context context, List<ProcessItem> list) {
        super(context, (int) R.layout.admsoloraya_res_0x7f0d00a9, list);
        this.context = context;
        this.values = list;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.admsoloraya_res_0x7f0d00a9, viewGroup, false);
        final TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01a2);
        final TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0437);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0115);
        this.defaultColor = textView.getTextColors().getDefaultColor();
        final ProcessItem processItem = this.values.get(i);
        textView.setText(processItem.applicationName);
        textView2.setText(processItem.packageName);
        checkBox.setChecked(processItem.check.booleanValue());
        SetFontWeight(textView, textView2, checkBox);
        ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01d8)).setImageDrawable(processItem.icon);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ProcessItemArrayAdapter.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                processItem.check = Boolean.valueOf(z);
                ProcessItemArrayAdapter.this.SetFontWeight(textView, textView2, checkBox);
                if (processItem.saveChanges.booleanValue()) {
                    processItem.save();
                }
            }
        });
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ProcessItemArrayAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ProcessItem processItem2 = processItem;
                processItem2.check = Boolean.valueOf(!processItem2.check.booleanValue());
                checkBox.setChecked(processItem.check.booleanValue());
                ProcessItemArrayAdapter.this.SetFontWeight(textView, textView2, checkBox);
                if (processItem.saveChanges.booleanValue()) {
                    processItem.save();
                }
            }
        });
        return inflate;
    }

    public void SetFontWeight(TextView textView, TextView textView2, CheckBox checkBox) {
        if (checkBox.isChecked()) {
            textView.setTextColor(this.defaultColor);
            textView2.setTextColor(this.defaultColor);
            return;
        }
        textView.setTextColor(this.defaultColor);
        textView2.setTextColor(this.defaultColor);
    }
}
