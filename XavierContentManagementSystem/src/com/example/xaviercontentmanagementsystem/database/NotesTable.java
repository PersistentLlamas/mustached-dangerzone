package com.example.xaviercontentmanagementsystem.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class NotesTable {
	public static final String TABLE_NOTES = "notes";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_TEXT = "text";
	
	//Database Creation String
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_NOTES 
			+ "(" + COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_TITLE + " text not null, "
			+ COLUMN_TEXT + " text not null"
			+ ");";
	
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
		database.execSQL("DROP TABLES IF EXISTS " + TABLE_NOTES);
		onCreate(database);
	}
}
