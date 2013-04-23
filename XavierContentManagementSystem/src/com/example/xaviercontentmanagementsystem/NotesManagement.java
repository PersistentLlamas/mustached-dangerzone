package com.example.xaviercontentmanagementsystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotesManagement extends Activity {

	private static final int ACTIVITY_CREATE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes_management);
		
		final Button addNoteButton = (Button) findViewById(R.id.add_note);
		
		addNoteButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(addNoteButton.getContext(), Notes.class);
				startActivityForResult(i, ACTIVITY_CREATE);	
				//  TODO  
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notes_management, menu);
		return true;
	}

}
