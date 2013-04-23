package com.example.xaviercontentmanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.example.xaviercontentmanagementsystem.xml.XMLParser;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.*;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class RSSFeed extends ListActivity {
	
private static String URL;
	
	//RSS Nodes
	private static final String NODE_ITEM = "item";
	private static final String NODE_TITLE = "title";
	private static final String NODE_LINK = "link";
	private static final String NODE_GUID = "guid";
	private static final String NODE_DESCRIPTION = "description";
	private static final String NODE_PUBDATE = "pubDate";
	//test
	private static ArrayList<HashMap<String, String>> rssItems = new ArrayList<HashMap<String, String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssfeed);
		
		URL = "http://www.xavier.edu/pr/rss.xml";
		
		ActionBar actionBar = getActionBar();
	    // add the custom view to the action bar
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
		           | ActionBar.DISPLAY_SHOW_HOME);
	    actionBar.setCustomView(R.layout.rss_action_bar_spinner);
	    final Spinner rss_spinner = (Spinner) actionBar.getCustomView().findViewById(R.id.feed);
	    Button rss_refresh = (Button) actionBar.getCustomView().findViewById(R.id.refresh);
	    rss_refresh.setOnClickListener(new View.OnClickListener()
	    {

			@Override
			public void onClick(View v) {
				RenderView(rss_spinner.getSelectedItem().toString());
				
			}
	    	
	    });
		/*rss_spinner.setOnItemClickListener(new OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					RenderView(rss_spinner.getSelectedItem().toString());
				}
			   
			});*/
		RenderView(URL);
		
	}
	
	private void RenderView(String url)
	{
		rssItems.clear();
		GetRSSFromLocation(url);
		
		CreateAndSetListViewAdapter();
	}
	
	private void CreateAndSetListViewAdapter()
	{
		//Creates a list adapter to display the data from the RSS feed to the user 
		ListAdapter adapter = new SimpleAdapter(this, rssItems, R.layout.event_feed_list_item,
					new String [] {NODE_TITLE, NODE_DESCRIPTION, NODE_PUBDATE},
					new int [] { R.id.name, R.id.description, R.id.date});
		setListAdapter(adapter);
		
		ListView listView = getListView();
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.description)).getText().toString();
				String date = ((TextView) view.findViewById(R.id.date)).getText().toString();

				Intent intent = new Intent(getApplicationContext(), ListItemDetailActivity.class);
				intent.putExtra(NODE_TITLE, name);
				intent.putExtra(NODE_DESCRIPTION, description);
				intent.putExtra(NODE_PUBDATE, date);
				startActivity(intent);
			}
		});
	}
	
	private static void GetRSSFromLocation(String URL)
	{
		XMLParser xmlParser = new XMLParser();
		String xml = xmlParser.getXmlFromUrl(URL);
		Document doc = xmlParser.getDomElement(xml);
		
		ParseAndAddItems(doc, xmlParser);
	}
	
	private static void ParseAndAddItems(Document doc, XMLParser xmlParser)
	{
		//Get all child nodes of each node with text "item"
		NodeList nodeList = doc.getElementsByTagName(NODE_ITEM);
		
		//Store the nodes retrieved by the previous line 
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
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	    	finish();
	        break;

	    }

	    return true;
	}

}
