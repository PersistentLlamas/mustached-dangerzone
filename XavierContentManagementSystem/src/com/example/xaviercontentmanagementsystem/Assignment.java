package com.example.xaviercontentmanagementsystem;

public class Assignment {
	private String _Name = "";				//name of the assignment
	private Professor _Prof;				//professor whose class this is for.
	private Course _Subject;				//Course of assignment
	private int _Priority = 3;				//Priority level of assignment
	private Department _Department;			//Department of Course
	private String _AssignmentText = "";	//Actual assignment Text

	/**********************************_NAME****************************************************/
	// Accessor method for the _Name property of the Assignment Class.
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
	
	/**********************************_PROF****************************************************/
	// Accessor method for the _Prof property of the Assignment Class.
	// @returns String property _Prof
	public Professor GetProfessor()
	{
		return _Prof;
	}
	
	// Sets the _Prof property
	// @param Professor of the desired _Prof
	public void SetProfessor(Professor prof)
	{
		this._Prof = prof;
	}
	
	/*******************************************************************************************/
	
	/**********************************_SUBJECT*************************************************/
	// Accessor method for the _Subject property of the Assignment Class.
	// @returns Course property _Subject
	public Course GetSubject()
	{
		return _Subject;
	}
	
	// Sets the _Subject property
	// @param Course of the desired _Subject
	public void SetSubject(Course course)
	{
		this._Subject = course;
	}
	
	/*******************************************************************************************/
	
	/**********************************_Priority************************************************/
	// Accessor method for the _Priority property of the Assignment Class.
	// @returns Integer property _Priority
	public int GetPriority()
	{
		return _Priority;
	}
	
	// Sets the _Priority property
	// @param Integer (1 - 5) of the desired _Priority
	public void SetPriority(int priority)
	{
		this._Priority = priority;
	}
	
	/*******************************************************************************************/
	
	/******************************_DEPARTMENT**************************************************/
	// Accessor method for the _Department property of the Assignment Class.
	// @returns Department property _Department
	public Department GetDepartment()
	{
		return _Department;
	}
	
	// Sets the _Department property
	// @param Department of the desired _Department
	public void SetDepartment(Department dept)
	{
		this._Department = dept;
	}
	
	/*******************************************************************************************/
	
	/*****************************_ASSIGNMENT TEXT**********************************************/
	// Accessor method for the _AssignmentText property of the Assignment Class.
	// @returns String property _AssignmentText
	public String GetAssignmentText()
	{
		return _AssignmentText;
	}
	
	// Sets the _AssignmentText property
	// @param String of the desired _AssignmentText
	public void SetAssignmentText(String AssignmentText)
	{
		this._AssignmentText = AssignmentText;
	}
	
	/*******************************************************************************************/
}

