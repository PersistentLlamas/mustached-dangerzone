package com.example.xaviercontentmanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.example.xaviercontentmanagementsystem.xml.XMLParser;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.*;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class RSSFeed extends ListActivity {
	
	private static final String URL = "http://www.xavier.edu/pr/rss.xml";//"http://www.xavier.edu/pr/rss.xml";
	
	//RSS Nodes
	private static final String NODE_ITEM = "item";
	private static final String NODE_TITLE = "title";
	private static final String NODE_LINK = "link";
	private static final String NODE_GUID = "guid";
	private static final String NODE_DESCRIPTION = "description";
	private static final String NODE_PUBDATE = "pubDate";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssfeed);
		
		ArrayList<HashMap<String, String>> rssItems = new ArrayList<HashMap<String, String>>();
		
		XMLParser xmlParser = new XMLParser();
		String xml = xmlParser.getXmlFromUrl(URL);
		Document doc = xmlParser.getDomElement(xml);
		
		NodeList nodeList = doc.getElementsByTagName(NODE_ITEM);
		
		for(int i = 0; i < nodeList.getLength(); i++)
		{
			HashMap<String, String> newEvent = new HashMap<String, String>();
			Element nodeElement = (Element) nodeList.item(i);
			
			newEvent.put(NODE_TITLE, xmlParser.getValue(nodeElement, NODE_TITLE));
			newEvent.put(NODE_LINK, xmlParser.getValue(nodeElement, NODE_LINK));
			newEvent.put(NODE_GUID, xmlParser.getValue(nodeElement, NODE_GUID));
			newEvent.put(NODE_DESCRIPTION, xmlParser.getValue(nodeElement, NODE_DESCRIPTION));
			newEvent.put(NODE_PUBDATE, xmlParser.getValue(nodeElement, NODE_PUBDATE));
			
			rssItems.add(newEvent);
		}
		
		ListAdapter adapter = new SimpleAdapter(this, rssItems, R.layout.event_feed_list_item,
				new String [] {NODE_TITLE, NODE_PUBDATE},
				new int [] { R.id.name, R.id.description});
		setListAdapter(adapter);
		ListView listView = getListView();
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.description)).getText().toString();
				Log.d("NAME: ", name);
				Intent intent = new Intent(getApplicationContext(), ListItemDetailActivity.class);
				intent.putExtra(NODE_TITLE, name);
				intent.putExtra(NODE_PUBDATE, description);
				startActivity(intent);
			}
			
		});
	}

}
