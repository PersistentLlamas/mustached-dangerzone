package com.example.xaviercontentmanagementsystem;

public class Professor {
	private String _Name = "";				//Name of the Professor
	private Department _Dept;				//Department of the Professor
	private String _Email = "";				//Professor's Email
	private String _Phone = "";				//Professor's Phone Number
	//private ? _OfficeLocation = "";		//Professor's Office Location
	//private ? _OfficeHours = null;		//Professor's Office Hours
	
	/*********************************_NAME****************************************************/
	// Accessor method for the _Name property of the Professor class.
	// @returns String property _Name
	public String GetName()
	{
		return _Name;
	}
	
	// Sets the _Name property
	// @param String of the desired _Name
	public void SetName(String name)
	{
		this._Name = name;
	}
	
	/*******************************************************************************************/
	
	/*******************************_DEPARTMENT*************************************************/
	// Accessor method for the _Dept property of the Professor class.
	// @returns Department property _Dept
	public Department GetDepartment()
	{
		return _Dept;
	}
	
	// Sets the _Dept property
	// @param Department of the desired _Dept
	public void SetDepartment(Department dept)
	{
		this._Dept = dept;
	}
	
	/*******************************************************************************************/
	
	/*********************************_Email****************************************************/
	// Accessor method for the _Email property of the Professor class.
	// @returns String property _Email
	public String GetEmail()
	{
		return _Email;
	}
	
	// Sets the _Email property
	// @param String of the desired _Email
	public void SetEmail(String email)
	{
		this._Email = email;
	}
	
	/*******************************************************************************************/
	
	/*********************************_PHONE****************************************************/
	// Accessor method for the _Phone property of the Professor class.
	// @returns String property _Phone
	public String GetPhone()
	{
		return _Phone;
	}
	
	// Sets the _Phone property
	// @param String of the desired _Phone
	public void SetPhone(String phone)
	{
		this._Phone = phone;
	}
	
	/*******************************************************************************************/
}
