package com.example.qq.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.qq.R;
import com.example.qq.message.MsgActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2019/1/2.
 */

public class MsgFragment extends Fragment {

    List<UserInfo> list = new ArrayList<>();
    private ListView listView;
    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_fragment,container,false);
        listView = (ListView) view.findViewById(R.id.msg_listView);
        adapter = new MsgAdapter(getContext(),R.layout.msg_item,list);
        listView.setAdapter(adapter);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                UserInfo userInfo = list.get(position);
                startMessageActivity();
            }
        });
        return view;
    }

    private void startMessageActivity(){
        Intent intent = new Intent(this.getActivity(), MsgActivity.class);
        this.getActivity().startActivity(intent);
    }

    private void init() {
        for(int i=0; i<10; i++){
            list.add(new UserInfo("好友"+i,R.drawable.head));
        }
    }
}
