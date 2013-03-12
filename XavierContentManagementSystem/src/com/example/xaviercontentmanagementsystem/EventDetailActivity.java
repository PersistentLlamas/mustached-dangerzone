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
	
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		saveState();
		outState.putParcelable(EventsContentProvider.CONTENT_ITEM_TYPE, eventUri);
	}
	
	protected void onPause()
	{
		super.onPause();
		saveState();
	}
	
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
	
	private void makeToast()
	{
		Toast.makeText(EventDetailActivity.this, "Please give a summary", Toast.LENGTH_LONG).show();
	}

}
