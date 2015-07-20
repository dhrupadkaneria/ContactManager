/*
 * By Dhrupad Kaneria : dck140030
 * 
 * Date: 10/28/2014
 * Description: A user defined object that contains details of a contact. It implements comparable 
 * 		and serializable to sort the list as well as pass it across the activities.
 */
package com.example.contactmanager;

import java.io.Serializable;

public class Contacts implements Comparable<Contacts>,Serializable{
	
	private static final long serialVersionUID = 1L;
	String Fname;
	String Lname;
	String Email;
	String phNumber;
		
	/*
	 * By Dhrupad Kaneria
	 * Basic operations
	 */
	public Contacts(String f, String l, String e, String p)
	{
		Fname = f;
		Lname = l;
		Email = p;
		phNumber = e;
	}
	String getValue(){
		return Fname+' '+Lname;
	}
	String getfName()
	{
		return Fname;
	}
	String getLName()
	{
		return Lname;
	}
	String getEmail()
	{
		return Email;
	}
	String getphNumber()
	{
		return phNumber;
	}
	@Override
	public int compareTo(Contacts another) 
	{
		return getValue().compareTo(another.getValue());
	}
	
}
