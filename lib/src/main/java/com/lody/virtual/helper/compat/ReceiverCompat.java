package com.lody.virtual.helper.compat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;

public final class ReceiverCompat {

    private ReceiverCompat() {
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver receiver,
                                          IntentFilter filter, boolean exported) {
        return registerReceiver(context, receiver, filter, null, null, exported);
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver receiver,
                                          IntentFilter filter, String broadcastPermission,
                                          Handler scheduler, boolean exported) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            int flags = exported ? Context.RECEIVER_EXPORTED : Context.RECEIVER_NOT_EXPORTED;
            return context.registerReceiver(receiver, filter, broadcastPermission, scheduler, flags);
        }
        if (broadcastPermission != null || scheduler != null) {
            return context.registerReceiver(receiver, filter, broadcastPermission, scheduler);
        }
        return context.registerReceiver(receiver, filter);
    }
}
