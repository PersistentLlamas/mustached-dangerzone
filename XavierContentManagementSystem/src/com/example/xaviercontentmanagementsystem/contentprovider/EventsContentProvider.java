package com.example.xaviercontentmanagementsystem.contentprovider;

import android.content.ContentProvider;

import com.example.xaviercontentmanagementsystem.database.*;

public class EventsContentProvider extends ContentProvider 
{
	private EventDatabaseHelper eventDBHelper;
	
	private static final int EVENT = 10;
	private static final int EVENT_ID = 20;
	
	private static final String AUTHORITY = "com.example.xaviercontentmanagementsystem.contentprovider";
	
}
