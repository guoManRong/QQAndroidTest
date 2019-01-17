package com.example.qq.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qq.R;

import java.util.List;

/**
 * Created by dell on 2019/1/2.
 */

public class ContactAdapter extends ArrayAdapter<ContactInfo> {
    private int resourceID ;
    public ContactAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactInfo contactInfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView tel = (TextView) view.findViewById(R.id.contact_item_tel);
        TextView name = (TextView) view.findViewById(R.id.contact_item_name);
        name.setText(contactInfo.getName());
        tel.setText(contactInfo.getTel());
        return view;
    }
}
