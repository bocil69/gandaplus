package com.fufufu.katrina.backup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
/* loaded from: classes5.dex */
public class DebugActivity extends Activity {
    private String[] exceptionTypes = {"StringIndexOutOfBoundsException", "IndexOutOfBoundsException", "ArithmeticException", "NumberFormatException", "ActivityNotFoundException"};
    private String[] exceptionMessages = {"Invalid string operation\n", "Invalid list operation\n", "Invalid arithmetical operation\n", "Invalid toNumber block operation\n", "Invalid intent operation"};

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String str = "";
        if (intent != null) {
            String stringExtra = intent.getStringExtra("error");
            String[] split = stringExtra.split("\n");
            int i = 0;
            while (true) {
                try {
                    String[] strArr = this.exceptionTypes;
                    if (i >= strArr.length) {
                        break;
                    } else if (split[0].contains(strArr[i])) {
                        String str2 = this.exceptionMessages[i];
                        int indexOf = split[0].indexOf(this.exceptionTypes[i]) + this.exceptionTypes[i].length();
                        StringBuilder sb = new StringBuilder(String.valueOf(str2));
                        String str3 = split[0];
                        sb.append(str3.substring(indexOf, str3.length()));
                        str = String.valueOf(sb.toString()) + "\n\nDetailed error message:\n" + stringExtra;
                        break;
                    } else {
                        i++;
                    }
                } catch (Exception e) {
                    str = String.valueOf(str) + "\n\nError while getting error: " + Log.getStackTraceString(e);
                }
            }
            if (str.isEmpty()) {
                str = stringExtra;
            }
        }
        AlertDialog create = new AlertDialog.Builder(this).setTitle("An error occurred").setMessage(str).setPositiveButton("End Application", new DialogInterface.OnClickListener() { // from class: com.fufufu.katrina.backup.DebugActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                DebugActivity.this.finish();
            }
        }).create();
        create.show();
        ((TextView) create.findViewById(16908299)).setTextIsSelectable(true);
    }
}
