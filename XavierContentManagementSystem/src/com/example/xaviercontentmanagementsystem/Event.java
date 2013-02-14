package com.example.xaviercontentmanagementsystem;

public class Event {
	private String _Title = "";
	//private String  _Link = "";
	private String _Description = "";
	//private String _DueDate = "";
	
	/*****************************_TITLE******************************************************/
	// Accessor method for the _Title property of the Event class.
	// @returns String property _Title
	public String GetTitle()
	{
		return _Title;
	}
	
	// Sets the _Title property
	// @param String of the desired _Title
	public void SetTitle(String title)
	{
		this._Title = title;
	}
	
	/*******************************************************************************************/
	
	/*****************************_DESCRIPTION**************************************************/
	// Accessor method for the _Description property of the Event class.
	// @returns String property _Description
	public String GetDescription()
	{
		return _Description;
	}
	
	// Sets the _Description property
	// @param String of the desired _Description
	public void SetDescription(String desc)
	{
		this._Description = desc;
	}
	
	/*******************************************************************************************/
}
