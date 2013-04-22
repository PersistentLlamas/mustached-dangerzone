package com.example.xaviercontentmanagementsystem;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListItemDetailActivity extends Activity {

	private static final String NODE_TITLE = "title";
	private static final String NODE_DATE = "pubDate";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item_detail);
		
		Intent intent = getIntent();
		Button addEvent = (Button) findViewById(R.id.btnAdd);
        String title = intent.getStringExtra(NODE_TITLE);
        String date = intent.getStringExtra(NODE_DATE);
        
        TextView lblTitle = (TextView) findViewById(R.id.name_label);
        TextView lblDate = (TextView) findViewById(R.id.description_label);
        
        lblTitle.setText(title);
        lblDate.setText(date);
        
        addEvent.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent toAddAssignment = new Intent(getApplicationContext(), EventDetailActivity.class);
				String title = ((TextView) findViewById(R.id.name_label)).getText().toString();
				String date = ((TextView) findViewById(R.id.description_label)).getText().toString();
				toAddAssignment.putExtra(NODE_TITLE, title);
				toAddAssignment.putExtra(NODE_DATE, date);	
				startActivity(toAddAssignment);
				
			}
		});
        
        
        
	}

}
