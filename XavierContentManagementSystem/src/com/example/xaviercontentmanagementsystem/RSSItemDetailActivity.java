package com.example.xaviercontentmanagementsystem;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RSSItemDetailActivity extends Activity {

	public static final String NODE_TITLE = "title";
	public static final String NODE_DESCRIPTION = "description";
	public static final String NODE_DATE = "pubDate";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item_detail);
		
		Intent intent = getIntent();
		Button addEvent = (Button) findViewById(R.id.btnAdd);
        String title = intent.getStringExtra(NODE_TITLE);
        String description = intent.getStringExtra(NODE_DESCRIPTION);
        Log.d("DESCR", description);
        String date = intent.getStringExtra(NODE_DATE);
        
        TextView lblTitle = (TextView) findViewById(R.id.name_label);
        TextView lblDescription = (TextView) findViewById(R.id.description_label);
        TextView lblDate = (TextView) findViewById(R.id.date_label);
        
        lblTitle.setText(title);
        lblDescription.setText(description);
        lblDate.setText(date);
        
        addEvent.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent toAddAssignment = new Intent(getApplicationContext(), AssignmentDetailActivity.class);
				String title = ((TextView) findViewById(R.id.name_label)).getText().toString();
				String description = ((TextView) findViewById(R.id.description_label)).getText().toString();
				toAddAssignment.putExtra(NODE_TITLE, title);
				toAddAssignment.putExtra(NODE_DESCRIPTION, description);	
				startActivity(toAddAssignment);
				
			}
		});
        
        
        
	}

}
