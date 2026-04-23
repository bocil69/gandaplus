package com.termfu.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.fufufu.katrina.backup.R;
import com.termfu.app.TermuxService;
import com.termux.terminal.EmulatorDebug;
import java.io.File;
import java.util.UUID;
/* loaded from: classes6.dex */
public class TermuxLaunchShortcutActivity extends Activity {
    static final String TOKEN_NAME = "com.termfu.shortcut.token";

    public static String getGeneratedToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("token", 0);
        String string = sharedPreferences.getString("token", null);
        if (string == null) {
            String uuid = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("token", uuid).apply();
            return uuid;
        }
        return string;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(TOKEN_NAME);
        if (stringExtra == null || !stringExtra.equals(getGeneratedToken(this))) {
            Log.w(EmulatorDebug.LOG_TAG, "Strange token: " + stringExtra);
            Toast.makeText(this, (int) R.string.admsoloraya_res_0x7f1200cc, 1).show();
            finish();
            return;
        }
        File file = new File(intent.getData().getPath());
        TermuxWidgetProvider.ensureFileReadableAndExecutable(file);
        Intent intent2 = new Intent("com.termfu.service_execute", new Uri.Builder().scheme("com.termfu.file").path(file.getAbsolutePath()).build());
        intent2.setClassName("com.fufufu.katrina.backup", TermuxWidgetProvider.TERMUX_SERVICE);
        if (file.getParentFile().getName().equals("tasks")) {
            intent2.putExtra(TermuxService.EXTRA_EXECUTE_IN_BACKGROUND, true);
            Toast makeText = Toast.makeText(this, "Task executed: " + file.getName(), 0);
            makeText.setGravity(17, 0, 0);
            makeText.show();
        }
        TermuxWidgetProvider.startTermuxService(this, intent2);
        finish();
    }
}
