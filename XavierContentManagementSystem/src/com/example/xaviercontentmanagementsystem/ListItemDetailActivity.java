package com.example.xaviercontentmanagementsystem;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ListItemDetailActivity extends Activity {

	private static final String NODE_TITLE = "TITLE";
	private static final String NODE_DATE = "DATE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item_detail);
		
		Intent intent = getIntent();
		
        String title = intent.getStringExtra(NODE_TITLE);
        String date = intent.getStringExtra(NODE_DATE);
        
        TextView lblTitle = (TextView) findViewById(R.id.name_label);
        TextView lblDate = (TextView) findViewById(R.id.description_label);
        
        lblTitle.setText(title);
        lblDate.setText(date);
        
	}

}
