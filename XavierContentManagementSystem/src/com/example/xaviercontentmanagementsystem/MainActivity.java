package com.example.xaviercontentmanagementsystem;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.net.Uri;

public class MainActivity extends Activity {
	
	private static final int ACTIVITY_CREATE = 0;
	private ViewFlipper flipper;
	
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
		final Button notesButton = (Button) findViewById(R.id.btnNotes);
		Intent intent = getIntent();
		if(intent.hasExtra("ERROR"))
		{
			Toast.makeText(MainActivity.this, intent.getStringExtra("ERROR"), Toast.LENGTH_LONG).show();
		}
		
		flipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
		int[] images = 
			{
				R.drawable.xavierimg2,R.drawable.xavierimg3,
				R.drawable.xavierimg4,R.drawable.xavierimg5,
				R.drawable.xavierimg7
			};
		Random rand = new Random();
		ArrayList<Integer> final_images = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++){
			int index = rand.nextInt(images.length);
			while(final_images.contains(images[index])){
				index = rand.nextInt(images.length);
			}
			final_images.add(images[index]);
		}
		if(flipper.getChildCount() < 6){
		for(int i =0; i< final_images.size(); i++)
		{
			setFlipperImage(final_images.get(i));
		}
		}
		flipper.setFlipInterval(4000);
		flipper.startFlipping();
		
		
		
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
				Intent i = new Intent(assignmentButton.getContext(), AssignmentOverviewActivity.class);
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
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dineoncampus.com/xu/show.cfm?cmd=menus2&venueName=Hoff%20Dining%20Commons"));
				startActivity(browserIntent);
			}
		});
		
		notesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(eventsButton.getContext(), NotesOverviewActivity.class);
				startActivityForResult(i, ACTIVITY_CREATE);				
			}
		});
	}

	private void setFlipperImage(int i) {
		ImageView image = new ImageView(getApplicationContext());
		image.setLayoutParams(new GridView.LayoutParams(1280, 1280));
		image.setScaleType(ImageView.ScaleType.CENTER_CROP);
		image.setBackgroundResource(i);
		flipper.addView(image);
		
	}
	
	
}
