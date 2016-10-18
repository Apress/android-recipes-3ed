package com.examples.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.widget.TextView;

public class NotificationActivity extends Activity {

    //Unique notification id values
    public static final int NOTE_BASIC = 100;
    public static final int NOTE_BIGTEXT = 200;
    public static final int NOTE_PICTURE = 300;
    public static final int NOTE_INBOX = 400;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("When You Leave Here, Notifications Will Post");
        tv.setGravity(Gravity.CENTER);
        
        setContentView(tv);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        
//        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
//        startActivity(intent);
//    }
    
    @Override
    public void onStop() {
        super.onStop();
        
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Post 4 unique notifications
        Notification note = buildNotification(NOTE_BASIC);
        manager.notify(NOTE_BASIC, note);
        note = buildNotification(NOTE_BIGTEXT);
        manager.notify(NOTE_BIGTEXT, note);
        note = buildNotification(NOTE_PICTURE);
        manager.notify(NOTE_PICTURE, note);
        note = buildNotification(NOTE_INBOX);
        manager.notify(NOTE_INBOX, note);
    }
    
    private Notification buildNotification(int type) {
        Intent launchIntent =
                new Intent(this, NotificationActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, launchIntent, 0);
        
        // Create notification with the time it was fired
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                NotificationActivity.this);
        
        
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setTicker("Something Happened")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("We're Finished!")
                .setContentText("Click Here!")
                .setContentIntent(contentIntent);
        
        switch (type) {
            case NOTE_BASIC:
                //Return the simple notification
                return builder.build();
            case NOTE_BIGTEXT:
                //Include two actions
                builder.addAction(android.R.drawable.ic_menu_call,
                        "Call", contentIntent);
                builder.addAction(android.R.drawable.ic_menu_recent_history,
                        "History", contentIntent);
                //Use the BigTextStyle when expanded
                NotificationCompat.BigTextStyle textStyle =
                        new NotificationCompat.BigTextStyle(builder);
                textStyle.bigText("Here is some additional text to be displayed when the notification is "
                        +"in expanded mode.  I can fit so much more content into this giant view!");
                
                return textStyle.build();
            case NOTE_PICTURE:
                //Add one additional action
                builder.addAction(android.R.drawable.ic_menu_compass,
                        "View Location", contentIntent);
                //Use the BigPictureStyle when expanded
                NotificationCompat.BigPictureStyle pictureStyle =
                        new NotificationCompat.BigPictureStyle(builder);
                pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.dog));
                
                return pictureStyle.build();
            case NOTE_INBOX:
                //Use the InboxStyle when expanded
                NotificationCompat.InboxStyle inboxStyle =
                        new NotificationCompat.InboxStyle(builder);
                inboxStyle.setSummaryText("4 New Tasks");
                inboxStyle.addLine("Make Dinner");
                inboxStyle.addLine("Call Mom");
                inboxStyle.addLine("Call Wife First");
                inboxStyle.addLine("Pick up Kids");
                
                return inboxStyle.build();
            default:
                throw new IllegalArgumentException("Unknown Type");
        }
    }
}