package com.example.qq.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.qq.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2019/1/2.
 */

public class ContactFragment extends Fragment {
    List<ContactInfo> list = new ArrayList<>();
    private ListView listView;
    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment,container,false);
        listView = (ListView) view.findViewById(R.id.contact_listView);
        if(ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.READ_CONTACTS},1);
        }else{
            readContacts();
        }
        adapter = new ContactAdapter(getContext(),R.layout.contact_item,list);
        listView.setAdapter(adapter);
        return view;
    }

    private void readContacts(){
        Cursor cursor = null;
        try{
            cursor = this.getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor != null){
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    list.add(new ContactInfo(name,number));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(cursor != null){
                cursor.close();
            }
        }
    }
}
