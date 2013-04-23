package com.example.xaviercontentmanagementsystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Notes extends Activity {

	private static final int ACTIVITY_CREATE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes);
		
		final Button submitButton = (Button) findViewById(R.id.submit_notes_button);
		final Button clearButton = (Button) findViewById(R.id.clear_notes_button);
		final EditText notesTitle = (EditText) findViewById(R.id.notes_title);
		final EditText noteContent = (EditText) findViewById(R.id.notes_taken);
		
		submitButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(submitButton.getContext(), NotesManagement.class);
				startActivityForResult(i, ACTIVITY_CREATE);	
				//  TODO  
			}
		});
		
		clearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				notesTitle.setText("");
				noteContent.setText("");				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notes, menu);
		return true;
	}

}