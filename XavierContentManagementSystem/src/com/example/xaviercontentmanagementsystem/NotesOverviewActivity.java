package com.example.xaviercontentmanagementsystem;

import com.example.xaviercontentmanagementsystem.contentprovider.NotesContentProvider;
import com.example.xaviercontentmanagementsystem.database.NotesTable;

import android.net.Uri;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class NotesOverviewActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private static final int DIVIDER_HEIGHT = 2;
	
	private SimpleCursorAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes_management);
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
		inflater.inflate(R.menu.notes_management, menu);
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
		case R.id.insert_note:
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
			Uri uri = Uri.parse(NotesContentProvider.CONTENT_URI + "/"
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
		Intent i = new Intent(this, NotesDetailActivity.class);
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
		Intent i = new Intent(this, NotesDetailActivity.class);
		Uri eventUri = Uri.parse(NotesContentProvider.CONTENT_URI + "/" + id);
		i.putExtra(NotesContentProvider.CONTENT_ITEM_TYPE, eventUri);

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
		String[] from = new String[] { NotesTable.COLUMN_TITLE };
		// Fields on the UI to which we map
		int[] to = new int[] { R.id.label_notes };

		getLoaderManager().initLoader(0, null, this);
		adapter = new SimpleCursorAdapter(this, R.layout.notes_row, null, from,
				to, 0);
		setListAdapter(adapter);
		//Log.d("COUNT THINGY",((Integer)adapter.getCount()).toString());
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { NotesTable.COLUMN_ID, NotesTable.COLUMN_TITLE };
		CursorLoader cursorLoader = new CursorLoader(this,
				NotesContentProvider.CONTENT_URI, projection, null, null, 
					  NotesTable.COLUMN_ID);
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