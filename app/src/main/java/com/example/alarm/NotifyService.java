package com.example.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

/**
 * Created by Arash on 11/22/2015.
 */
public class NotifyService extends Service {
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager mNH = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // which activity to start
        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0 , intent1, 0);

        Notification mNotify = new Notification.Builder(this)
            .setContentTitle("Sudowoodo!")
            .setContentText("Check your watering schedule")
            .setSmallIcon(R.drawable.ic_launcher)
            .setSound(sound)
            .addAction(0, "Load App",pIntent)
            .build();
            //.setSmallIcon(R.drawable.ic_launcher)

        mNH.notify(1, mNotify);
    }

}
