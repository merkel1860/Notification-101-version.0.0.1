package com.example.notification_101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "firstSession";
    private final CharSequence noTextTitle = "Welcome back Nebel";
    private final CharSequence noTextContent = "This a friendly reminder of how much Marie love you and " +
            "to make sure that you understand as a truth unalterable and eternal; otherwise, chance" +
            "ara that maybe one day, you may get it wrong and start messing around as if there was" +
            "something wrong with your story.";
    private final int notificationID = 0;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();


    }

    private void displayNotification() {
        // create a PendingItent to explicitly reach a  activity of tapping on notification pop up.
        PendingIntent pendingIntent = createPendingIntent();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.black_woman)
                .setContentTitle(noTextTitle)
                .setContentText(noTextContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManagerCompat  = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(notificationID,builder.build());
    }

    private PendingIntent createPendingIntent() {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, AlertDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        return pendingIntent;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }else{
            Toast.makeText(this,"Sorry...",Toast.LENGTH_SHORT).show();
        }

    }

    public void startNotification(View view) {
        displayNotification();

    }

    public void stopNotification(View view) {
        if(notificationManagerCompat == null){
            Toast.makeText(this,
                    "Please activate the Notification before",Toast.LENGTH_LONG).show();
        }else {
            notificationManagerCompat.cancel(notificationID);
        }

    }
}