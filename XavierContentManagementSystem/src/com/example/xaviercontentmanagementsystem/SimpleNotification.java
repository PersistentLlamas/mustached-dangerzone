package com.example.xaviercontentmanagementsystem;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.view.Menu;
import android.view.View;


// Send a very very basic notification 
public class SimpleNotification extends Activity {
      
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_notification);
    
	}
    
    // Send the notification when a the TriggerNotification button is clicked
    public void triggerNotification(View view)
    {
    	// Setup and build notification 
		Notification noti = new Notification.Builder(this)
    		.setSmallIcon(R.drawable.ic_launcher)  // Notification icon shown in header
    		.setContentTitle("New Notification")   
    		.setContentText("Hola!! Your notification is here")
    		.build(); 
    	NotificationManager notiMngr = (NotificationManager) getSystemService
    			(Context.NOTIFICATION_SERVICE);
    	noti.flags|= Notification.FLAG_AUTO_CANCEL; // When notification is selected, small icon
    	// disappears
    	
    	notiMngr.notify(0,noti); // send notification 
    	
    }
    
} 
