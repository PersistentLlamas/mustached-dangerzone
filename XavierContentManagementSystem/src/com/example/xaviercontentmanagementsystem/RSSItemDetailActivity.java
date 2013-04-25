package com.example.xaviercontentmanagementsystem;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
         
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.rss_item, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.insert_event_to_assignment:
			Intent toAddAssignment = new Intent(getApplicationContext(), AssignmentDetailActivity.class);
			String title = ((TextView) findViewById(R.id.name_label)).getText().toString();
			String description = ((TextView) findViewById(R.id.description_label)).getText().toString();
			toAddAssignment.putExtra(NODE_TITLE, title);
			toAddAssignment.putExtra(NODE_DESCRIPTION, description);	
			startActivity(toAddAssignment);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
