package com.example.xaviercontentmanagementsystem;

public class Department 
{
	private String _Abbreviation = "";		//Abbreviation of Department i.e. 'CSCI'
	private String _Name = "";				//Full name of Department i.e. Computer Science
	
	/*****************************_Abbreviation***********************************************/
	// Accessor method for the _Abbreviation property of the Department Method.
	// @returns String property _Abbreviation
	public String GetAbreviation()
	{
		return _Abbreviation;
	}
	
	// Sets the _Abbreviation property
	// @param String of the desired _Abbreviation
	public void SetAbbreviation(String abv)
	{
		_Abbreviation = abv;
	}
	
	/*******************************************************************************************/
	
	/**********************************_NAME****************************************************/
	// Accessor method for the _Name property of the Department Method.
	// @returns String property _Name
	public String GetName()
	{
		return _Name;
	}
	
	// Sets the _Name property
	// @param String of the desired _Name
	public void SetName(String name)
	{
		_Name = name;
	}
	
	/*******************************************************************************************/
}
