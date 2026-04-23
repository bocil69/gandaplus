package com.fufufu.katrina.backup;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.topjohnwu.superuser.nio.FileSystemManager;
/* loaded from: classes5.dex */
public class KatrinaNotifService extends FirebaseMessagingService {
    private static final String TAG = "KatrinaNotifService";
    private SharedPreferences userFile;

    private void sendRegistrationToServer(String str) {
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        remoteMessage.getData().size();
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getImageUrl() == null ? null : remoteMessage.getNotification().getImageUrl().toString());
        }
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        this.userFile = getSharedPreferences("userFile", 0);
    }

    private void sendNotification(String str, String str2, String str3) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(FileSystemManager.MODE_TRUNCATE);
        final NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this, "channelID").setSmallIcon(R.drawable.admsoloraya_res_0x7f080117).setContentTitle(str).setContentText(str2).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(this, 0, intent, 1073741824));
        final NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("channelID", "Katrina Service", 3);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300});
        }
        if (str3 == null) {
            notificationManager.notify((int) System.currentTimeMillis(), contentIntent.build());
        } else {
            Glide.with(getApplicationContext()).asBitmap().load(str3).into((RequestBuilder<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.fufufu.katrina.backup.KatrinaNotifService.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(@Nullable Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    contentIntent.setLargeIcon(bitmap);
                    contentIntent.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
                    notificationManager.notify((int) System.currentTimeMillis(), contentIntent.build());
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(@Nullable Drawable drawable) {
                    super.onLoadFailed(drawable);
                    notificationManager.notify((int) System.currentTimeMillis(), contentIntent.build());
                }
            });
        }
    }
}
