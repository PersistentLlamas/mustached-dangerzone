package com.example.xaviercontentmanagementsystem.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "xubccms.db";
	private static final int DATABASE_VERSION = 1;
	
	public EventDatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	public void onCreate(SQLiteDatabase database)
	{
		EventTable.onCreate(database);
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		EventTable.onUpgrade(database, oldVersion, newVersion);
	}
}
