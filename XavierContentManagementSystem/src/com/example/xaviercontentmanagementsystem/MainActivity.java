package com.example.xaviercontentmanagementsystem;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.net.Uri;

public class MainActivity extends Activity {
	
	private static final int ACTIVITY_CREATE = 0;
	
	/*
	 * This method is called on creation and creates the add assignment button and creates a listener for it.
	 *
	 * @param savedInstanceState is a bundle containing the most recent saved state if the activity is being
	 * re-initialized after being shut down. Otherwise, it is null.
	 *
	 * @return void
	 *
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.
				ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy); 
		
		final Button assignmentButton = (Button) findViewById(R.id.btnAssignment);
		final Button eventsButton = (Button) findViewById(R.id.btnEvents);
		final Button internetsButton = (Button) findViewById(R.id.btnInternets);
		
		assignmentButton.setOnClickListener(new View.OnClickListener()
		{
			/*
			 * This method handles clicks on the button.
			 *
			 * @param v, is a View reference to the widget that was clicked.
			 *
			 * @return void
			 *
			 * (non-Javadoc)
			 * @see android.widget.Button
			 */
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(assignmentButton.getContext(), EventOverviewActivity.class);
				startActivityForResult(i, ACTIVITY_CREATE);
				
			}
		});
		
		eventsButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(eventsButton.getContext(), RSSFeed.class);
				startActivityForResult(i, ACTIVITY_CREATE);
			}
		});
		
		internetsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dineoncampus.com/xu"));
				startActivity(browserIntent);
				
			}
		});
	}

	/*
	 * This method inflates the menu and adds items to the action bar if it is present.
	 *
	 * @param menu, the options menu we are inflating.
	 *
	 * @return true
	 *
	 *(non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
