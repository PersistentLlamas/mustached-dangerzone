package com.example.xaviercontentmanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.example.xaviercontentmanagementsystem.xml.XMLParser;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BusinessCollegeEventsActivity extends ListActivity {
	
	private static final String URL = /*"xavier.edu/williams/events/events.xml"*/"http://cerebro.cs.xu.edu/~philoj/events.xml";
	
	//XML nodes
	private static final String NODE_EVENT = "EVENT";
	private static final String NODE_ID = "ID";
	private static final String NODE_TITLE = "TITLE";
	private static final String NODE_DATE = "DATE";
	private static final String NODE_TIME = "TIME";
	private static final String NODE_LOCATION = "LOCATION";
	private static final String NODE_RSVP = "RSVP";
	private static final String NODE_DEPARTMENT = "DEPARTMENT";
	private static final String NODE_SUMMARY = "SUMMARY";
	private static final String NODE_HIDE = "HIDE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_college_events);

		ArrayList<HashMap<String, String>> eventItems = new ArrayList<HashMap<String, String>>();
		
		XMLParser xmlParser = new XMLParser();
		String xml = xmlParser.getXmlFromUrl(URL);
		Document doc = xmlParser.getDomElement(xml);
		
		NodeList nodeList = doc.getElementsByTagName(NODE_EVENT);
		
		for(int i = 0; i < nodeList.getLength(); i++)
		{
			HashMap<String,String> newEvent = new HashMap<String, String>();
			Element e = (Element) nodeList.item(i);
			
			newEvent.put(NODE_ID, xmlParser.getValue(e,  NODE_ID));
			newEvent.put(NODE_TITLE, xmlParser.getValue(e, NODE_TITLE));
			newEvent.put(NODE_DATE, xmlParser.getValue(e, NODE_DATE));
			newEvent.put(NODE_TIME, xmlParser.getValue(e, NODE_TIME));
			newEvent.put(NODE_LOCATION, xmlParser.getValue(e, NODE_LOCATION));
			newEvent.put(NODE_RSVP, xmlParser.getValue(e, NODE_RSVP));
			newEvent.put(NODE_DEPARTMENT, xmlParser.getValue(e, NODE_DEPARTMENT));
			newEvent.put(NODE_SUMMARY, xmlParser.getValue(e, NODE_SUMMARY));
			newEvent.put(NODE_HIDE, xmlParser.getValue(e, NODE_HIDE));
			
			//if(newEvent.get(NODE_HIDE) == "No")
			//{
				eventItems.add(newEvent);
			//}
		}
		ListAdapter adapter = new SimpleAdapter(this, eventItems,
				R.layout.event_feed_list_item,
				new String[] {NODE_TITLE, NODE_DATE } , new int [] {
					R.id.name, R.id.description } );
		setListAdapter(adapter);
		
		ListView listView = getListView();
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.description)).getText().toString();
				
				Intent intent = new Intent(getApplicationContext(), ListItemDetailActivity.class);
				intent.putExtra(NODE_TITLE, name);
				intent.putExtra(NODE_DATE, description);
				startActivity(intent);
			}
			
		});
		
	}
	
}
