package com.example.xaviercontentmanagementsystem.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;

public class XMLParser 
{
	public XMLParser()
	{
		//Does not do anything, but can later if you want
	}
	
	public String getXmlFromUrl(String url)
	{
		String xml = null;
		
		try 
		{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			xml = EntityUtils.toString(httpEntity);
			Log.d("RESULT", xml);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return xml;
	}
	
	public Document getDomElement(String xml)
	{
		Document doc = null;
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		
		try
		{
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			
			inputSource.setCharacterStream(new StringReader(xml));
			doc = docBuilder.parse(inputSource);
		}
		catch (ParserConfigurationException e)
		{
			Log.e("Error: ", e.getMessage());
			return null;
		}
		catch (SAXException e)
		{
			Log.e("Error: ", e.getMessage());
			return null;
		}
		catch (IOException e)
		{
			Log.e("Error: ", e.getMessage());
			return null;
		}
		
		return doc;
	}
	
	public final String getElementValue(Node elem)
	{
		Node child;
		if(elem != null)
		{
			if(elem.hasChildNodes())
			{
				for(child = elem.getFirstChild(); child != null; child = child.getNextSibling())
				{
					if((child.getNodeType() == Node.TEXT_NODE) || (child.getNodeType() == Node.CDATA_SECTION_NODE))
					{
						return child.getNodeValue();
					}
				}
			}
		}
		return "";
	}
	
	public String getValue(Element item, String str)
	{
		NodeList n = item.getElementsByTagName(str);
		return this.getElementValue(n.item(0));
	}

}
