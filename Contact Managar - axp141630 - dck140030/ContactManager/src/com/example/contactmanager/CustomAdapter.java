/*
 * By Dhrupad Kaneria : dck140030
 * 
 * Date: 10/28/2014
 * Description: Helps in mapping an arrayList to a listview in android.
 */

package com.example.contactmanager;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Contacts> {
    public CustomAdapter(Context context, ArrayList<Contacts> users) {
       super(context, 0, users);
    }

    /*
     * By: Dhrupad Kaneria
     * Overriding getview() in order to customize and fill the listview on the fly.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Contacts user = getItem(position);    
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_element, parent, false);
       }
       TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
       TextView tvphNumber = (TextView) convertView.findViewById(R.id.tvphNumber);
       tvName.setText(user.getfName()+" "+user.getLName());
       tvName.setTextSize(24);
       tvName.setTextColor(Color.parseColor("#000000"));
       tvphNumber.setText(user.getphNumber());
       tvphNumber.setTextSize(14);
       return convertView;
   }
}