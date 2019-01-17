package com.example.qq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.qq.fragments.ContactFragment;
import com.example.qq.fragments.MsgFragment;
import com.example.qq.fragments.SetMyIconFragment;
import com.example.qq.fragments.PositionFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    public String[] btnTitles = new String[]{"聊天","联系人","位置","我"};
    public List<Fragment> contextFragments = new ArrayList<>();
    public LinearLayout linearLayout;
    private NavigationView naviView;
    private DrawerLayout drawer;
    private ImageView myIcon;
    private ImageView myIconBig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myIcon = (ImageView)findViewById(R.id.myicon);
        drawer = (DrawerLayout)findViewById(R.id.drawlayout);
        naviView = (NavigationView) findViewById(R.id.nav_view);
        myIconBig = (ImageView) naviView.getHeaderView(0).findViewById(R.id.myicon_big);
        myIcon.setOnClickListener(this);
        navigationItemSelected();
        init();
    }

    private void navigationItemSelected(){
        naviView.setCheckedItem(R.id.navi_album);
        naviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.navi_album:
                        drawer.closeDrawers();
                        break;
                    case R.id.navi_offline:
                        sendBroadcast();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void sendBroadcast(){
        Intent intent = new Intent("com.example.qq.FORCE_OFFLINE");
        sendBroadcast(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHeadIcon(myIcon);
        loadHeadIcon(myIconBig);
    }

    private void init(){
        initButton();
        initFragment();
    }

    private void loadHeadIcon(ImageView img){
        SharedPreferences share = getSharedPreferences("data",MODE_PRIVATE);
        String filePath = share.getString("imgPath",null);
        Glide.with(this).load(filePath).into(img);
    }

    public void initButton(){

        linearLayout = (LinearLayout) findViewById(R.id.buttonLayout);

        for(String btnStr: btnTitles){

            Button btn = new Button(this);
            btn.setText(btnStr);
            btn.setTag(btnStr);

            btn.setBackgroundColor(Color.WHITE);
            LinearLayout.LayoutParams btnLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

            btn.setOnClickListener(this);

            linearLayout.addView(btn, btnLayoutParams);
        }

    }

    public void initFragment(){

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        MsgFragment msgFragment = new MsgFragment();
        ContactFragment contactFragment = new ContactFragment();
        PositionFragment positionFragment = new PositionFragment();
        SetMyIconFragment offLineFragment = new SetMyIconFragment();


        transaction.add(R.id.contextFrameLayout, msgFragment, btnTitles[0]);
        transaction.add(R.id.contextFrameLayout, contactFragment, btnTitles[1]);
        transaction.add(R.id.contextFrameLayout, positionFragment, btnTitles[2]);
        transaction.add(R.id.contextFrameLayout, offLineFragment, btnTitles[3]);

        contextFragments.add(msgFragment);
        contextFragments.add(contactFragment);
        contextFragments.add(positionFragment);
        contextFragments.add(offLineFragment);

        transaction.commit();

        showFragment(btnTitles[0]);
    }

    public void showFragment(String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.i(TAG, "showFragment: tag=" + tag);
        for (Fragment fragment:contextFragments){
            if(fragment.getTag().equals(tag)){
                transaction.show(fragment);
            }else{
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.myicon:
                drawer.openDrawer(Gravity.LEFT);
                break;
            default:
                showFragment(view.getTag().toString());
        }

    }

}
