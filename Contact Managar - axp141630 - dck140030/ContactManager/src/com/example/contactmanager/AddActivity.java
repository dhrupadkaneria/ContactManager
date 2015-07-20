/*
 * By Arvind Pandiyan : axp141630
 * By Dhrupad Kaneria : dck140030
 * 
 * Date: 10/28/2014
 * Description: A new activity that takes contact details as input from the user.
 */

package com.example.contactmanager;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends ActionBarActivity {

	private ContactList c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		Button b = (Button)findViewById(R.id.button1);
		b.setBackgroundColor(Color.parseColor("#F44336"));
		b.setTextColor(Color.WHITE);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        /*
         * By: Arvind Pandiyan
         * Checks for contacts passed to this activity
         */
		c = (ContactList )getIntent().getSerializableExtra("1234567890");
        if(c == null){
        	c = new ContactList();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	/*
     * By: Dhrupad Kaneria
     * Creates a new contact from the given details, adds it to the present list 
     * 		and passes it back to the main activity.
     */
	public void saveContact(View v){
		EditText fName = (EditText)findViewById(R.id.editText1);
		EditText LName = (EditText)findViewById(R.id.editText2);
		EditText phNumber = (EditText)findViewById(R.id.editText3);
		EditText eMail = (EditText)findViewById(R.id.editText4);
		if(!fName.getText().toString().isEmpty())
		{
			c.add(new Contacts(fName.getText().toString(),LName.getText().toString(),phNumber.getText().toString(),eMail.getText().toString()));
			Intent mIntent = new Intent(this,MainActivity.class);
			Bundle mBundle = new Bundle();
			mBundle.putSerializable("1234567890",c);
			mIntent.putExtras(mBundle);
			
			startActivity(mIntent);
			finish();
		}
		else
		{
			Toast.makeText(this, "First Name required", Toast.LENGTH_SHORT).show();
		}
		
	}
}
