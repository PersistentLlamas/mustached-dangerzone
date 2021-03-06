package com.example.xaviercontentmanagementsystem;

import java.util.GregorianCalendar;

import com.example.xaviercontentmanagementsystem.contentprovider.EventsContentProvider;
import com.example.xaviercontentmanagementsystem.database.EventTable;

import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AssignmentDetailActivity extends Activity {

	private Spinner detailCategory;
	private EditText detailTitleText;
	private EditText detailBodyText;
	private EditText detailCourseText;
	private EditText detailProfessorText;
	private DatePicker detailDatePicker;
	
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
		detailCourseText = (EditText) findViewById(R.id.event_edit_course);
		detailProfessorText = (EditText) findViewById(R.id.event_edit_professor);
		detailDatePicker = (DatePicker) findViewById(R.id.datePicker1);
		Intent intent = getIntent();
		if(intent.hasExtra("title"))
		{
			detailTitleText.setText(intent.getStringExtra(RSSItemDetailActivity.NODE_TITLE));
			detailBodyText.setText(intent.getStringExtra(RSSItemDetailActivity.NODE_DESCRIPTION));
		}
		else
		{
			Bundle extras = getIntent().getExtras();
		
			eventUri = (bundle == null) ? null : (Uri) bundle.getParcelable(EventsContentProvider.CONTENT_ITEM_TYPE);
		
			if(extras != null)
			{
				eventUri = extras.getParcelable(EventsContentProvider.CONTENT_ITEM_TYPE);
				fillData(eventUri);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.event_detail, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.submit_assignment:
			createEvent();
			return true;
		case R.id.add_to_calendar:
			Intent intent = new Intent(Intent.ACTION_INSERT);
			intent.setType("vnd.android.cursor.item/event");
			intent.putExtra(Events.TITLE, detailTitleText.getText().toString());
			intent.putExtra(Events.DESCRIPTION, detailBodyText.getText().toString());
			GregorianCalendar calDate = new GregorianCalendar(detailDatePicker.getYear(),detailDatePicker.getMonth(), detailDatePicker.getDayOfMonth());
			intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
					  calDate.getTimeInMillis());
			intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
					  calDate.getTimeInMillis());
			intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void createEvent()
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
				EventTable.COLUMN_SUMMARY,
				EventTable.COLUMN_DESCRIPTION,
				EventTable.COLUMN_COURSE,
				EventTable.COLUMN_PROFESSOR,
				EventTable.COLUMN_PRIORITY,
				EventTable.COLUMN_DUE_DAY,
				EventTable.COLUMN_DUE_MONTH,
				EventTable.COLUMN_DUE_YEAR,
			};
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
			detailCourseText.setText(cursor.getString(cursor.getColumnIndexOrThrow(EventTable.COLUMN_COURSE)));
			detailProfessorText.setText(cursor.getString(cursor.getColumnIndexOrThrow(EventTable.COLUMN_PROFESSOR)));
			detailDatePicker.init(
					Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(EventTable.COLUMN_DUE_YEAR))),
					Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(EventTable.COLUMN_DUE_MONTH))),
					Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(EventTable.COLUMN_DUE_DAY))),
					null);
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
		String course = detailCourseText.getText().toString();
		String professor = detailProfessorText.getText().toString();
		int dueDay = detailDatePicker.getDayOfMonth();
		int dueMonth = detailDatePicker.getMonth();
		int dueYear = detailDatePicker.getYear();
		final int MONTH_OFFSET = 1;
		String dateFormat = (dueMonth + MONTH_OFFSET) + "/" + dueDay + "/" + dueYear;
		if(description.length() == 0 && summary.length() == 0)
		{
			return;
		}
		
		ContentValues values = new ContentValues();
		values.put(EventTable.COLUMN_PRIORITY, category);
		values.put(EventTable.COLUMN_SUMMARY, summary);
		values.put(EventTable.COLUMN_DESCRIPTION, description);
		values.put(EventTable.COLUMN_COURSE, course);
		values.put(EventTable.COLUMN_PROFESSOR, professor);
		values.put(EventTable.COLUMN_DUE_DAY, dueDay);
		values.put(EventTable.COLUMN_DUE_MONTH, dueMonth);
		values.put(EventTable.COLUMN_DUE_YEAR, dueYear);
		values.put(EventTable.COLUMN_FORMAT_DATE, dateFormat);
		
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
		Toast.makeText(AssignmentDetailActivity.this, "Please give a summary", Toast.LENGTH_LONG).show();
		Log.d("TOAST", "Toast was made, and it was good. and i had a really really really good time.");
	}

}
