package com.example.xaviercontentmanagementsystem.database;


import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EventTable {
	public static final String TABLE_EVENTS = "events";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_PRIORITY = "priority";
	public static final String COLUMN_SUMMARY = "summary";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_DUE_DAY = "due_day";
	public static final String COLUMN_DUE_MONTH = "due_month";
	public static final String COLUMN_DUE_YEAR = "due_year";
	public static final String COLUMN_FLAGS = "flags";
	public static final String COLUMN_FORMAT_DATE = "format_date";
	public static final String COLUMN_PROFESSOR = "professor";
	public static final String COLUMN_COURSE = "course";

	//Database Creation String
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_EVENTS 
			+ "(" + COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_PRIORITY + " text not null, "
			+ COLUMN_SUMMARY + " text not null, "
			+ COLUMN_DESCRIPTION + " text not null, "
			+ COLUMN_PROFESSOR + " text not null, "
			+ COLUMN_COURSE + " text not null, "
			+ COLUMN_DUE_DAY + " text not null, "
			+ COLUMN_DUE_MONTH + " text not null, "
			+ COLUMN_DUE_YEAR + " text not null, "
			+ COLUMN_FORMAT_DATE + " text not null"
			/*+ COLUMN_FLAGS + " text not null"*/
			+ ");";
	
	//Noah Test Upload
		
	/*
	 * This method receives a database and runs the SQLite Query shown in the 
	 * String constant DATABASE_CREATE, which creates the table for this application.
	 * 
	 * @param receives a SQLiteDatabase 'database' on which the query will be executed.
	 */
	public static void onCreate(SQLiteDatabase database)
	{
		database.execSQL(DATABASE_CREATE);
	}
	
	/*
	 * This method receives a database the current version number and the new version number.
	 * This method will erase the database by calling the SQLite query DROP TABLES then it will create
	 * a new database by calling onCreate
	 * 
	 * @param SQLiteDatabase database: a SQLiteDatabase database on which all queries are to be executed. 
	 * @param int oldVersion: an integer variable containing the current and soon to be old database version number.
	 * @param int newVersion: an integer variable containing the new database version number.
	 */
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		Log.w(EventTable.class.getName(), "Upgrading database from version: "
				+ oldVersion
				+ " to: "
				+ newVersion
				+", will erase all data.");
		database.execSQL("DROP TABLES IF EXISTS " + TABLE_EVENTS);
		onCreate(database);
	}
}
