package com.example.xaviercontentmanagementsystem;

import com.example.xaviercontentmanagementsystem.contentprovider.EventsContentProvider;
import com.example.xaviercontentmanagementsystem.database.EventTable;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;

import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class EventOverviewActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>
{
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private static final int DIVIDER_HEIGHT = 2;
	
	private SimpleCursorAdapter adapter;
	
	/*
	 * This method is called on creating of EventOverviewActivity and sets up all necessary things.
	 *
	 * @param savedInstanceState, a Bundle containing the last saved state if one exists. Null otherwise.
	 *
	 * @return void
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list);
		this.getListView().setDividerHeight(DIVIDER_HEIGHT);
		fillData();
		registerForContextMenu(getListView());
	}
	
	/*
	 * This method inflates the options menu.
	 *
	 * @param menu, the options Menu to be inflated.
	 *
	 * @return true
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.listmenu, menu);
		return true;
	}
	
	/*
	 * This method creates an event for the clicked item.
	 *
	 * @param item, a MenuItem reference to the clicked item.
	 *
	 * @return true
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.insert:
			createEvent();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 * This method allows for the deletion of items and the showing of details.
	 *
	 * @param item, a MenuItem reference to the selected context item.
	 *
	 * @return true
	 */
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			Uri uri = Uri.parse(EventsContentProvider.CONTENT_URI + "/"
					+ info.id);
			getContentResolver().delete(uri, null, null);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	/*
	 * This method creates an event.
	 */
	
	private void createEvent() {
		Intent i = new Intent(this, EventDetailActivity.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}
	
	/*
	 * This method sends the user to the detail activity on the clicked item.
	 *
	 * @param l, a ListView of the list in which the item resides.
	 * @param v, a View reference to the clicked item.
	 * @param position, the int position of the item in the list.
	 * @param id, the id of the item.
	 *
	 * @return void
	 */
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, EventDetailActivity.class);
		Uri eventUri = Uri.parse(EventsContentProvider.CONTENT_URI + "/" + id);
		i.putExtra(EventsContentProvider.CONTENT_ITEM_TYPE, eventUri);

		// Activity returns an result if called with startActivityForResult
		startActivityForResult(i, ACTIVITY_EDIT);
	}
	
	/*
	 * This method performs actions based on the result of an activity.
	 *
	 * @param requestCode, an int representing who the result came from.
	 * @param resultCode, an int returned by the child activity through its setResult()
	 * @param intent, an Intent which can return result data to the caller
	 *
	 * @return void
	 */
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
	}
	
	private void fillData() {

		// Fields from the database (projection)
		// Must include the _id column for the adapter to work
		String[] from = new String[] { EventTable.COLUMN_SUMMARY };
		// Fields on the UI to which we map
		int[] to = new int[] { R.id.label };

		getLoaderManager().initLoader(0, null, this);
		adapter = new SimpleCursorAdapter(this, R.layout.event_row, null, from,
				to, 0);

		setListAdapter(adapter);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { EventTable.COLUMN_ID, EventTable.COLUMN_SUMMARY };
		CursorLoader cursorLoader = new CursorLoader(this,
				EventsContentProvider.CONTENT_URI, projection, null, null, null);
		return cursorLoader;
	}
	
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// data is not available anymore, delete reference
		adapter.swapCursor(null);
	}
}
