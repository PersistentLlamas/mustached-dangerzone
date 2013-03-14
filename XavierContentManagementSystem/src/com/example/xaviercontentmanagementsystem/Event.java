package com.example.xaviercontentmanagementsystem;

public class Event {
	private String _Event = "";
	private String _Id = "";
	private String _Title = "";
	private String _Date = "";
	private String _Time = "";
	private String _Location = "";
	private String _RSVP = "";
	private String _Department = "";
	private String _Description = "";
	private String _Hide = "";
	
	public Event()
	{
		//default constructor
	}
	
	public String GetEvent()
	{
		return _Event;
	}
	
	public void SetEvent(String event)
	{
		this._Event = event;
	}

	public String GetId()
	{
		return _Id;
	}
	
	public void SetId(String id)
	{
		this._Id = id;
	}

	
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
	
	public String GetDate()
	{
		return _Date;
	}
	
	public void SetDate(String date)
	{
		this._Date = date;
	}
	
	public String GetTime()
	{
		return _Time;
	}
	
	public void SetTime(String time)
	{
		this._Time = time;
	}
	
	public String GetLocation()
	{
		return _Location;
	}
	
	public void SetLocation(String location)
	{
		this._Location = location;
	}
	
	public String GetRSVP()
	{
		return _RSVP;
	}
	
	public void SetRSVP(String rsvp)
	{
		this._RSVP = rsvp;
	}
	
	public String GetDepartment()
	{
		return _Department;
	}
	
	public void SetDepartment(String department)
	{
		this._Department = department;
	}
		
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
	
	public String GetHide()
	{
		return _Hide;
	}
	
	public void SetHide(String hide)
	{
		this._Hide = hide;
	}
	
}
