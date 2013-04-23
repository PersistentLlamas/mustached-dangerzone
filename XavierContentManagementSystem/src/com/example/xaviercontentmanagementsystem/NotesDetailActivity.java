package com.example.xaviercontentmanagementsystem;

import com.example.xaviercontentmanagementsystem.contentprovider.NotesContentProvider;
import com.example.xaviercontentmanagementsystem.database.NotesTable;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesDetailActivity extends Activity {

	private static final int ACTIVITY_CREATE = 0;
	EditText notesTitle;
	EditText noteContent;
	
	private Uri notesUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes);
		notesTitle = (EditText) findViewById(R.id.notes_title);
		noteContent = (EditText) findViewById(R.id.notes_taken);
		Intent intent = getIntent();
		final Button submitButton = (Button) findViewById(R.id.submit_notes_button);
		final Button clearButton = (Button) findViewById(R.id.clear_notes_button);

		Bundle extras = getIntent().getExtras();
		
		notesUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState.getParcelable(NotesContentProvider.CONTENT_ITEM_TYPE);
	
		if(extras != null)
		{
			notesUri = extras.getParcelable(NotesContentProvider.CONTENT_ITEM_TYPE);
			fillData(notesUri);
		}
		submitButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				String title = notesTitle.getText().toString();
				String content = noteContent.getText().toString();
				if((TextUtils.isEmpty(content)) || (TextUtils.isEmpty(title)))
				{
					makeToast();
				}
				else
				{
					setResult(RESULT_OK);
					finish();
				}
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

	/*
	 * This method fills in the database based on the given uri.
	 *
	 * @param uri, a Uri on which a cursor is created.
	 *
	 * @return void
	 *
	 */
	
	private void fillData(Uri uri)
	{
		String[] projection = 
			{
				NotesTable.COLUMN_TITLE,
				NotesTable.COLUMN_TEXT
				
			};
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		if(cursor != null)
		{
			cursor.moveToFirst();
			notesTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow(NotesTable.COLUMN_TITLE)));
			noteContent.setText(cursor.getString(cursor.getColumnIndexOrThrow(NotesTable.COLUMN_TEXT)));
			cursor.close();
		}
	}
	
	/*
	 * This method saves the current state into the given bundle.
	 *
	 * @param outState, a Bundle in which to put the saved state.
	 *
	 * @return void
	 */
	
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		saveState();
		outState.putParcelable(NotesContentProvider.CONTENT_ITEM_TYPE, notesUri);
	}
	
	/*
	 * This method is called when the user exits the program. It saves the state.
	 */
	
	protected void onPause()
	{
		super.onPause();
		saveState();
	}
	
	/*
	 * This method saves the current state.
	 */
	
	private void saveState()
	{
		String title = notesTitle.getText().toString();
		String text = noteContent.getText().toString();

		if(title.length() == 0 && text.length() == 0)
		{
			return;
		}
		
		ContentValues values = new ContentValues();
		values.put(NotesTable.COLUMN_TITLE, title);
		values.put(NotesTable.COLUMN_TEXT, text);
		
		if(notesUri == null)
		{
			notesUri = getContentResolver().insert(NotesContentProvider.CONTENT_URI, values);
		}
		else
		{
			getContentResolver().update(notesUri, values, null, null);
		}
	}
	
	/*
	 * This method creates a Toast (a small message to the user) warning them that an event summary is required.
	 */
	
	private void makeToast()
	{
		Toast.makeText(NotesDetailActivity.this, "Please give a Text", Toast.LENGTH_LONG).show();
		Log.d("TOAST", "Toast was made, and it was good. and i had a really really really good time.");
	}

}
