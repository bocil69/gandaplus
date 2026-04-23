package com.termfu.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.fufufu.katrina.backup.R;
import com.termfu.app.TermuxService;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.io.File;
/* loaded from: classes6.dex */
public final class TermuxWidgetProvider extends AppWidgetProvider {
    public static final String ACTION_EXECUTE = "com.termfu.service_execute";
    public static final String EXTRA_CLICKED_FILE = "com.termfu.widgets.EXTRA_CLICKED_FILE";
    private static final String LIST_ITEM_CLICKED_ACTION = "com.termfu.widgets.LIST_ITEM_CLICKED_ACTION";
    private static final String REFRESH_WIDGET_ACTION = "com.termfu.widgets.REFRESH_WIDGET_ACTION";
    public static final String TERMUX_SERVICE = "com.termfu.app.TermuxService";

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        for (int i : iArr) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.admsoloraya_res_0x7f0d00c9);
            remoteViews.setEmptyView(R.id.admsoloraya_res_0x7f0a05cc, R.id.admsoloraya_res_0x7f0a0178);
            Intent intent = new Intent(context, TermuxWidgetService.class);
            intent.putExtra("appWidgetId", i);
            intent.setData(Uri.parse(intent.toUri(1)));
            remoteViews.setRemoteAdapter(R.id.admsoloraya_res_0x7f0a05cc, intent);
            Intent intent2 = new Intent(context, TermuxWidgetProvider.class);
            intent2.setAction(REFRESH_WIDGET_ACTION);
            intent2.putExtra("appWidgetId", i);
            intent2.setData(Uri.parse(intent2.toUri(1)));
            remoteViews.setOnClickPendingIntent(R.id.admsoloraya_res_0x7f0a0400, PendingIntent.getBroadcast(context, 0, intent2, FileSystemManager.MODE_CREATE));
            Intent intent3 = new Intent(context, TermuxWidgetProvider.class);
            intent3.setAction(LIST_ITEM_CLICKED_ACTION);
            intent3.putExtra("appWidgetId", i);
            intent.setData(Uri.parse(intent.toUri(1)));
            remoteViews.setPendingIntentTemplate(R.id.admsoloraya_res_0x7f0a05cc, PendingIntent.getBroadcast(context, 0, intent3, FileSystemManager.MODE_CREATE));
            appWidgetManager.updateAppWidget(i, remoteViews);
        }
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode != 88383294) {
            if (hashCode == 302547944 && action.equals(REFRESH_WIDGET_ACTION)) {
                AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(intent.getIntExtra("appWidgetId", 0), R.id.admsoloraya_res_0x7f0a05cc);
                Toast makeText = Toast.makeText(context, (int) R.string.admsoloraya_res_0x7f1200e8, 0);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }
        } else if (action.equals(LIST_ITEM_CLICKED_ACTION)) {
            String stringExtra = intent.getStringExtra(EXTRA_CLICKED_FILE);
            File file = new File(stringExtra);
            if (file.isDirectory()) {
                return;
            }
            ensureFileReadableAndExecutable(file);
            Intent intent2 = new Intent("com.termfu.service_execute", new Uri.Builder().scheme("com.termfu.file").path(stringExtra).build());
            intent2.setClassName("com.fufufu.katrina.backup", TERMUX_SERVICE);
            if (file.getParentFile().getName().equals("tasks")) {
                intent2.putExtra(TermuxService.EXTRA_EXECUTE_IN_BACKGROUND, true);
                Toast makeText2 = Toast.makeText(context, "Task executed: " + file.getName(), 0);
                makeText2.setGravity(48, 0, 0);
                makeText2.show();
            }
            startTermuxService(context, intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void startTermuxService(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void ensureFileReadableAndExecutable(File file) {
        if (!file.canRead()) {
            file.setReadable(true);
        }
        if (file.canExecute()) {
            return;
        }
        file.setExecutable(true);
    }
}
