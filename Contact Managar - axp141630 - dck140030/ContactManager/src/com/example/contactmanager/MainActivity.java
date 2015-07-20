/*
 * By Arvind Pandiyan : axp141630
 * By Dhrupad Kaneria : dck140030
 * 
 * Date: 10/28/2014
 * Description: The main activity. It shows the main page,
 *  	consisting of list of contacts present in the directory along with means of adding new contact.
 */


package com.example.contactmanager;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
	private ListView lv;
	private ContactList c;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        lv=(ListView)findViewById(R.id.listView1);
        /*
         * By: Arvind Pandiyan
         * Checks for contacts passed to this activity (In case it is called from Add/Edit Activity)
         */
        c = (ContactList )getIntent().getSerializableExtra("1234567890");
        if(c == null){//If no contact is passed, i.e when the application is run for the first time
        	c = new ContactList();
        	c.readFromFile(getFilesDir());
        }
        CustomAdapter adapter = new CustomAdapter(this, c.getList());
		lv.setAdapter(adapter);
		final Context ct = this;
		/*
		 * By: Dhrupad Kaneria
		 * When the item from the list is selected, the data is passed to a new activity 
		 * where all details are displayed for editing or deleting.
		 */
		lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	Intent intent = new Intent(ct, EditActivity.class);
            	Bundle mBundle = new Bundle();
        		mBundle.putSerializable("1234567890",c);
        		intent.putExtra("pos", position);
        		intent.putExtras(mBundle);
        		
            	startActivity(intent);
            	
			}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*
         * On click of ADD, a new activity is launched to accept new details.
         */
        if (id == R.id.action_add) {
        	Intent intent = new Intent(this, AddActivity.class);
        	Bundle mBundle = new Bundle();
    		mBundle.putSerializable("1234567890",c);
    		intent.putExtras(mBundle);
    		
        	startActivity(intent);
        	
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onPause() {
        super.onPause(); 
        /*
         * When the main activity is paused, the data is written to file.
         */
        c.writeToFile(getFilesDir());
    }
}
