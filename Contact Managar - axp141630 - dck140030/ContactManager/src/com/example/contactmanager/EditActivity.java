/*
 * By Arvind Pandiyan : axp141630
 * 
 * Date: 10/28/2014
 * Description: A new activity that allows user to edit contact details or delete it.
 */

package com.example.contactmanager;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class EditActivity extends ActionBarActivity {

	private ContactList c;
	private int pos;
	private AlertDialog dialog;
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
         * An alert to confirm the deletion of the contact
         */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final Context ct = this; 
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		           @Override
				public void onClick(DialogInterface dialog, int id) {
		        	   /*
		                * By: Arvind Pandiyan
		                * Removes the contact from the list and passes it back to the main activity.
		                */
		        	   c.remove(pos);
		        	   Intent mIntent = new Intent(ct,MainActivity.class);
		        	   Bundle mBundle = new Bundle();
		        	   mBundle.putSerializable("1234567890",c);
		        	   mIntent.putExtras(mBundle);
			   			
		        	   startActivity(mIntent);
		        	   finish();
		           }
		       });
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		           @Override
				public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		builder.setMessage(R.string.dialog_message);
		dialog = builder.create();
		/*
         * By: Arvind Pandiyan
         * Checks for contacts passed to this activity and fills the details.
         */
		c = (ContactList )getIntent().getSerializableExtra("1234567890");
        if(c == null){
        	c = new ContactList();
        }
        pos = getIntent().getExtras().getInt("pos");
        Contacts temp = c.get(pos);
        EditText fName = (EditText)findViewById(R.id.editText1);
		EditText LName = (EditText)findViewById(R.id.editText2);
		EditText phNumber = (EditText)findViewById(R.id.editText3);
		EditText eMail = (EditText)findViewById(R.id.editText4);
		fName.setText(temp.getfName());
		LName.setText(temp.getLName());
		phNumber.setText(temp.getphNumber());
		eMail.setText(temp.getEmail());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		if (id == R.id.action_delete) {
			dialog.show();		
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
     * By: Arvind Pandiyan
     * Creates a new contact from the modified details, replaces an existing one 
     * 		and passes it back to the main activity.
     */
	public void saveContact(View v){
		EditText fName = (EditText)findViewById(R.id.editText1);
		EditText LName = (EditText)findViewById(R.id.editText2);
		EditText phNumber = (EditText)findViewById(R.id.editText3);
		EditText eMail = (EditText)findViewById(R.id.editText4);
		if(!fName.getText().toString().isEmpty())
		{
			c.set(new Contacts(fName.getText().toString(),LName.getText().toString(),phNumber.getText().toString(),eMail.getText().toString()),pos);
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
