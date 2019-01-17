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

public class MsgAdapter extends ArrayAdapter<UserInfo> {
    private int resourceID ;
    public MsgAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserInfo userInfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView img = (ImageView) view.findViewById(R.id.msg_item_img);
        TextView name = (TextView) view.findViewById(R.id.msg_item_contact);
        img.setImageResource(userInfo.getImgID());
        name.setText(userInfo.getName());
        return view;
    }
}
