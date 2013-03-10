package com.example.xaviercontentmanagementsystem.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.example.xaviercontentmanagementsystem.database.*;

public class EventsContentProvider extends ContentProvider 
{
	private EventDatabaseHelper database;
	
	private static final int EVENT = 10;
	private static final int EVENT_ID = 20;
	
	private static final String AUTHORITY = "com.example.xaviercontentmanagementsystem.contentprovider";
	private static final String BASE_PATH = "xaviercontentmanagementsystem";
	
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + "BASE_PATH");
	
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + BASE_PATH;
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + BASE_PATH;
	
	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static
	{
		sUriMatcher.addURI(AUTHORITY, BASE_PATH, EVENT);
		sUriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", EVENT_ID);
	}
	
	@Override
	public boolean onCreate()
	{
		database = new EventDatabaseHelper(getContext());
		return false;
	}
	
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
	
	@Override
	public String getType(Uri uri)
	{
		return null;
	}
	
}
	
