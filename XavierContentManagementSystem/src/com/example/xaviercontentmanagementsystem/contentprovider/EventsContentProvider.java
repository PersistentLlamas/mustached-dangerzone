package com.example.xaviercontentmanagementsystem.contentprovider;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.example.xaviercontentmanagementsystem.database.*;

public class EventsContentProvider extends ContentProvider 
{
	private EventDatabaseHelper database;
	
	private static final int EVENT = 10;
	private static final int EVENT_ID = 20;
	
	private static final String AUTHORITY = "com.example.xaviercontentmanagementsystem.contentprovider";
	private static final String BASE_PATH = "xaviercontentmanagementsystem";
	
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
	
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + BASE_PATH;
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + BASE_PATH;
	
	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static
	{
		sUriMatcher.addURI(AUTHORITY, BASE_PATH, EVENT);
		sUriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", EVENT_ID);
	}
	
	
	/*
	 * This method creates a new EventDatabaseHelper and returns false.
	 * 
	 * @returns false
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate()
	{
		database = new EventDatabaseHelper(getContext());
		return false;
	}
	
	
	/*
	 * The method query recieves a Uri, String Array, String, String Array, and String. all to be processed for the query on
	 * the database file.
	 * 
	 * @param uri, a Uri which is to be compared using the sUriMatcher to determine how to build the query.
	 * @param projection, a String array containing the projected database values to be passed to checkColumns.
	 * @param selection, a String containing the selection of the sql query.
	 * @param selectionArgs, a String Array containing the specifics of the selection parameter.
	 * @param sortOrder, the order in which the data in the query is to be sorted.
	 * 
	 * @returns the Cursor object cursor which contains the query
	 * 
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
	{
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();	
		checkColumns(projection);
		queryBuilder.setTables(EventTable.TABLE_EVENTS);
		int uriType = sUriMatcher.match(uri);
		switch(uriType)
		{
		case EVENT:
			break;
		case EVENT_ID:
			queryBuilder.appendWhere(EventTable.COLUMN_ID + "=" + uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown Uri: " + uri);
		}
		SQLiteDatabase sqldb = database.getWritableDatabase();
		Cursor cursor = queryBuilder.query(sqldb,projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}
	
	
	/*
	 * This method returns null, it is a deprecated method.
	 * 
	 * @param uri, a Uri to be compared.
	 * 
	 * @returns null
	 * 
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri)
	{
		return null;
	}
	
	
	/*
	 * This method depending on the uri recived will insert the values into the database.
	 * 
	 * @param uri, a Uri to be compared based on its type.
         * @param values, a ContentValues object which contains the values to be inserted.
         * 
         * @returns a Uri of the path to the inserted values.
	 * 
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values)
	{
		int uriType = sUriMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		long id = 0;
		switch (uriType)
		{
		case EVENT:
			id = sqlDB.insert(EventTable.TABLE_EVENTS, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri,  null);
		return Uri.parse(BASE_PATH + "/" + id);
	}
	
	/*
	 * This method will delete objects from the database depending on the uri given.
	 *
	 * @param uri, a URI to be compared based on its type.
	 * @param selection, a string describing the WHERE clause for the delete
	 * @param selectionArgs, an array of strings that contains the arguments for the WHERE clause
	 *
	 * @returns an int representing the number of rows effected if a WHERE clause is passed in,
	 * and 0 otherwise. To delete all rows and get a row count, pass "1" as the WHERE clause.
	 *
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs)
	{
		int uriType = sUriMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsDeleted = 0;
		switch(uriType)
		{
		case EVENT:
			rowsDeleted = sqlDB.delete(EventTable.TABLE_EVENTS, selection, selectionArgs);
			break;
		case EVENT_ID:
			String id = uri.getLastPathSegment();
			if(TextUtils.isEmpty(selection))
			{
				rowsDeleted = sqlDB.delete(EventTable.TABLE_EVENTS, EventTable.COLUMN_ID + "=" + id, null);
			}
			else
			{
				rowsDeleted = sqlDB.delete(EventTable.TABLE_EVENTS,  EventTable.COLUMN_ID + "=" + id + " and " + selection,  selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri,  null);
		return rowsDeleted;
	}
	
	/*
	 * This methods updates the database with the given values dependant on the uri given.
	 *
	 * @param uri, a Uri to be compared based on its type.
	 * @param values, a ContentValues object which contains the updated values.
	 * @param selection, a String representing the WHERE clause for the UPDATE command.
	 * @param selectionArgs, a String[] containing the arguments used in the WHERE clause.
	 *
	 * @return an int representing the number of rows effected by the update.
	 *
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
	{
		int uriType = sUriMatcher.match(uri);
		SQLiteDatabase sqldb = database.getWritableDatabase();
		int rowsUpdated = 0;
		switch(uriType)
		{
		case EVENT:
			rowsUpdated = sqldb.update(EventTable.TABLE_EVENTS, values, selection, selectionArgs);
			break;
		case EVENT_ID:
			String id = uri.getLastPathSegment();
			if(TextUtils.isEmpty(selection))
			{
				rowsUpdated = sqldb.update(EventTable.TABLE_EVENTS, values, EventTable.COLUMN_ID + "=" + id, null);
			}
			else
			{
				rowsUpdated = sqldb.update(EventTable.TABLE_EVENTS, values, EventTable.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsUpdated;
	}
	
	/*
	 * This method checks if all the columns in the given projection are in the known available columns.
	 * 
	 * @param projection, a String[] containing the requested columns.
	 *
	 * @return void
	 *
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#checkColumns(java.lang.String[[)
	 */
	
	private void checkColumns(String[] projection)
	{
		String[] available = 
			{
				EventTable.COLUMN_DESCRIPTION,
				EventTable.COLUMN_PROFESSOR,
				EventTable.COLUMN_COURSE,
				EventTable.COLUMN_DUE_DAY,
				EventTable.COLUMN_DUE_MONTH,
				EventTable.COLUMN_DUE_YEAR,
				EventTable.COLUMN_FORMAT_DATE,
				/*EventTable.COLUMN_FLAGS,*/
				EventTable.COLUMN_ID,
				EventTable.COLUMN_PRIORITY,
				EventTable.COLUMN_SUMMARY
			};
		if(projection != null)
		{
			HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
			HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
			//Check if all columns which are requested are available
			if(!availableColumns.containsAll(requestedColumns))
			{
				throw new IllegalArgumentException("Unknown columns in projection");
			}
		}
	}
}
	
