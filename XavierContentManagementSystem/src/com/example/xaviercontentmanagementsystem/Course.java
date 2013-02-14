package com.example.xaviercontentmanagementsystem;

public class Course {
	private String _Name = "";				//Name of the Course	
	private Professor _Prof;				//Professor of the Course
	private Department _Dept;				//Department of the Course
	private String _CourseNumber = "";		//Course Number i.e 193P
	private String _RoomNumber ="";			//Room Number
	private String _Building = "";			//Building Course is held
	
	/*****************************_NAME*********************************************************/
	// Accessor method for the _Name property of the Course class.
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
	
	/*****************************_PROF*********************************************************/
	// Accessor method for the _Prof property of the Course class.
	// @returns Professor property _Prof
	public Professor GetProfessor()
	{
		return _Prof;
	}
	
	// Sets the _Prof property
	// @param Professor of the desired _Prof
	public void SetName(Professor prof)
	{
		this._Prof = prof;
	}
	
	/*******************************************************************************************/
	
	/*************************************_DEPT*************************************************/
	// Accessor method for the _Dept property of the Course class.
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
	
	/*****************************_COURSENUMBER*************************************************/
	// Accessor method for the _CourseNumber property of the Course class.
	// @returns String property _CourseNumber
	public String GetCourseNumber()
	{
		return _CourseNumber;
	}
	
	// Sets the _CourseNumber property
	// @param String of the desired _CourseNumber
	public void SetCourseNumber(String courseNumber)
	{
		this._CourseNumber = courseNumber;
	}
	
	/*******************************************************************************************/
	
	/*******************************_ROOMNUMBER*************************************************/
	// Accessor method for the _RoomNumber property of the Course class.
	// @returns String property _RoomNumber
	public String GetRoomNumber()
	{
		return _RoomNumber;
	}
	
	// Sets the _RoomNumber property
	// @param String of the desired _RoomNumber
	public void SetRoomNumber(String roomNumber)
	{
		this._RoomNumber = roomNumber;
	}
	
	/*******************************************************************************************/
	
	/*****************************_BUILDING***********************************************/
	// Accessor method for the _Building property of the Course class.
	// @returns String property _Building
	public String GetBuilding()
	{
		return _Building;
	}
	
	// Sets the _Building property
	// @param String of the desired _Building
	public void SetBuilding(String building)
	{
		this._Building = building;
	}
	
	/*******************************************************************************************/
}
