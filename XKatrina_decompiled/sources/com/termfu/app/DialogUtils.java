package com.termfu.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Selection;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.termfu.app.DialogUtils;
/* loaded from: classes6.dex */
public final class DialogUtils {

    /* loaded from: classes6.dex */
    public interface TextSetListener {
        void onTextSet(String str);
    }

    public static void textInput(Activity activity, int i, String str, int i2, final TextSetListener textSetListener, int i3, final TextSetListener textSetListener2, int i4, final TextSetListener textSetListener3, DialogInterface.OnDismissListener onDismissListener) {
        final EditText editText = new EditText(activity);
        editText.setSingleLine();
        if (str != null) {
            editText.setText(str);
            Selection.setSelection(editText.getText(), str.length());
        }
        final AlertDialog[] alertDialogArr = new AlertDialog[1];
        editText.setImeActionLabel(activity.getResources().getString(i2), 66);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.termfu.app.DialogUtils$$ExternalSyntheticLambda0
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i5, KeyEvent keyEvent) {
                return DialogUtils.lambda$0(DialogUtils.TextSetListener.this, editText, alertDialogArr, textView, i5, keyEvent);
            }
        });
        float applyDimension = TypedValue.applyDimension(1, 1.0f, activity.getResources().getDisplayMetrics());
        int round = Math.round(16.0f * applyDimension);
        int round2 = Math.round(applyDimension * 24.0f);
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setPadding(round, round, round, round2);
        linearLayout.addView(editText);
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(activity).setTitle(i).setView(linearLayout).setPositiveButton(i2, new DialogInterface.OnClickListener() { // from class: com.termfu.app.DialogUtils$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i5) {
                DialogUtils.TextSetListener.this.onTextSet(editText.getText().toString());
            }
        });
        if (textSetListener2 != null) {
            positiveButton.setNeutralButton(i3, new DialogInterface.OnClickListener() { // from class: com.termfu.app.DialogUtils$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i5) {
                    DialogUtils.TextSetListener.this.onTextSet(editText.getText().toString());
                }
            });
        }
        if (textSetListener3 == null) {
            positiveButton.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        } else {
            positiveButton.setNegativeButton(i4, new DialogInterface.OnClickListener() { // from class: com.termfu.app.DialogUtils$$ExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i5) {
                    DialogUtils.TextSetListener.this.onTextSet(editText.getText().toString());
                }
            });
        }
        if (onDismissListener != null) {
            positiveButton.setOnDismissListener(onDismissListener);
        }
        AlertDialog create = positiveButton.create();
        alertDialogArr[0] = create;
        create.setCanceledOnTouchOutside(false);
        alertDialogArr[0].show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$0(TextSetListener textSetListener, EditText editText, AlertDialog[] alertDialogArr, TextView textView, int i, KeyEvent keyEvent) {
        textSetListener.onTextSet(editText.getText().toString());
        alertDialogArr[0].dismiss();
        return true;
    }
}
