package com.example.khabrav1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

public class MyFirebaseMessagingService  extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "CHAT");
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setSmallIcon(R.drawable.ic_chats);
        Intent intent = null;

        if(remoteMessage.getData().get("type").equalsIgnoreCase("sms")){
            intent = new Intent(this, ChatActivity.class);
            intent.putExtra("uid",remoteMessage.getData().get("userID"));
            intent.putExtra("name", remoteMessage.getData().get("name"));
            intent.putExtra("image", remoteMessage.getData().get("image"));


            //jsonObject2.put("userID", nUser.getUid());
            //jsonObject2.put("name", nUser.getName());
            //jsonObject2.put("image", nUser.getProfileImage());

            //Intent intent = new Intent(context, ChatActivity.class);
            //intent.putExtra("name", user.getName());
            //intent.putExtra("image", user.getProfileImage());
            //intent.putExtra("uid", user.getUid());
            startActivity(intent);


        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(123,builder.build());
    }
}
