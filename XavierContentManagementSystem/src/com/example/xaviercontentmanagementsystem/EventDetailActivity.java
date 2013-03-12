package com.example.xaviercontentmanagementsystem;

import com.example.xaviercontentmanagementsystem.contentprovider.EventsContentProvider;
import com.example.xaviercontentmanagementsystem.database.EventTable;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EventDetailActivity extends Activity {

	private Spinner detailCategory;
	private EditText detailTitleText;
	private EditText detailBodyText;
	
	private Uri eventUri;
	
	/*
	 * This method is called on creation and sets up all the views and such.
	 *
	 * @param bundle, a Bundle containing the last saved state of the Activity if it is being re-entered. Null otherwise.
	 *
	 * @return void
	 *
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.event_edit);
		
		detailCategory = (Spinner) findViewById(R.id.category);
		detailTitleText = (EditText) findViewById(R.id.event_edit_summary);
		detailBodyText = (EditText) findViewById(R.id.event_edit_description);
		Button confirmButton = (Button) findViewById(R.id.event_edit_button);
		
		Bundle extras = getIntent().getExtras();
		
		eventUri = (bundle == null) ? null : (Uri) bundle.getParcelable(EventsContentProvider.CONTENT_ITEM_TYPE);
		
		if(extras != null)
		{
			eventUri = extras.getParcelable(EventsContentProvider.CONTENT_ITEM_TYPE);
			fillData(eventUri);
		}
		confirmButton.setOnClickListener(new View.OnClickListener()
			{
				
			/*
			 * This method adds an event when the button is clicked.
			 *
			 * @param v, a View reference to the clicked object.
			 *
			 * @return void
			 *
			 * (non-Javadoc)
			 * @see android.widget.Button
			 */
			
			@Override
			public void onClick(View v) 
			{
				if(TextUtils.isEmpty(detailTitleText.getText().toString()))
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
		String[] projection = {EventTable.COLUMN_SUMMARY, EventTable.COLUMN_DESCRIPTION, EventTable.COLUMN_PRIORITY};
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		if(cursor != null)
		{
			cursor.moveToFirst();
			String category = cursor.getString(cursor.getColumnIndexOrThrow(EventTable.COLUMN_PRIORITY));
			for(int i = 0; i < detailCategory.getCount(); i++)
			{
				String s = (String)detailCategory.getItemAtPosition(i);
				if(s.equalsIgnoreCase(category))
				{
					detailCategory.setSelection(i);
				}
			}
			detailTitleText.setText(cursor.getString(cursor.getColumnIndexOrThrow(EventTable.COLUMN_SUMMARY)));
			detailBodyText.setText(cursor.getString(cursor.getColumnIndexOrThrow(EventTable.COLUMN_DESCRIPTION)));
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
		outState.putParcelable(EventsContentProvider.CONTENT_ITEM_TYPE, eventUri);
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
		String category = (String)detailCategory.getSelectedItem();
		String summary = detailTitleText.getText().toString();
		String description = detailBodyText.getText().toString();
		
		if(description.length() == 0 && summary.length() == 0)
		{
			return;
		}
		
		ContentValues values = new ContentValues();
		values.put(EventTable.COLUMN_PRIORITY, category);
		values.put(EventTable.COLUMN_SUMMARY, summary);
		values.put(EventTable.COLUMN_DESCRIPTION, description);
		
		if(eventUri == null)
		{
			eventUri = getContentResolver().insert(EventsContentProvider.CONTENT_URI, values);
		}
		else
		{
			getContentResolver().update(eventUri, values, null, null);
		}
	}
	
	/*
	 * This method creates a Toast (a small message to the user) warning them that an event summary is required.
	 */
	
	private void makeToast()
	{
		Toast.makeText(EventDetailActivity.this, "Please give a summary", Toast.LENGTH_LONG).show();
	}

}
