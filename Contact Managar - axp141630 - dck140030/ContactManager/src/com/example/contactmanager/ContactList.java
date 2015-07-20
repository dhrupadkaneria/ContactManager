/*
 * By Arvind Pandiyan : axp141630
 * By Dhrupad Kaneria : dck140030
 * 
 * Date: 10/28/2014
 * Description: A  user defined object that holds the list of contacts and helps perform actions on it.
 */

package com.example.contactmanager;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ContactList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	ArrayList<Contacts> c ;
	
	/*
	 * By Arvind Pandiyan
	 * Basic operations
	 */
	public ContactList()
	{
		c = new ArrayList<Contacts>();
	}
	public ArrayList<Contacts> getList()
	{
		return this.c;
	}
	public void add(Contacts entry)
	{
		c.add(entry);
		sort();
	}
	public void remove(int i){
		c.remove(i);
	}
	private void sort(){
		Collections.sort(c);
	}
	public Contacts get(int i){
		return c.get(i);
	}
	public void set(Contacts c, int i){
		this.c.set(i, c);
	}
	
	/*
	 * By Arvind Pandiyan
	 * Reads data from file.
	 */
	public void readFromFile(File d){
		String line ="";
		File f = new File(d, "contacts.txt");
		try {
			FileInputStream fis = new FileInputStream(f);
        	DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null ) {
				String[] parts = line.split(";");
				c.add(new Contacts(parts[0], parts[1], parts[2], parts[3]));
			}
            br.close();
		} 
        catch (FileNotFoundException e) {
         e.printStackTrace();
        } catch (IOException e) {
         e.printStackTrace(); 
        }
	}
	
	/*
	 * By Dhrupad Kaneria
	 * Writes data to file.
	 */
	public void writeToFile(File d){
		String line ="";
		File f = new File(d, "contacts.txt");
		try {
			FileOutputStream fis = new FileOutputStream(f, false);
        	for(Contacts i : c){
        		line = i.getfName() + ";" + i.getLName() + ";" +i.getphNumber() + ";" +i.getEmail() + "\n";
        		fis.write(line.getBytes());
        	}
        	fis.close();
		} 
        catch (IOException e) {
         e.printStackTrace(); 
        }
	}
	
}
