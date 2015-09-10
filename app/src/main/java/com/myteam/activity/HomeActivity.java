package com.myteam.activity;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.myteam.activity.adapter.MyFragmentAdapter;
import com.myteam.activity.fragments.MyContactsFragment;
import com.myteam.activity.fragments.MyMeFragment;
import com.myteam.activity.fragments.MyMessageFragment;
import com.myteam.activity.fragments.MyShareFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity{

    ViewPager myViewPager;
    List<Fragment> fragmentList;
    LinearLayout layout_contacts;
    LinearLayout layout_message;
    LinearLayout layout_share;
    LinearLayout layout_me;
    ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_home);
        initViews();
        initViewPager();
        initListener();

    }

    private void setActionBar() {
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void initViews(){
        myViewPager = (ViewPager) findViewById(R.id.viewpager);
        fragmentList = new ArrayList<Fragment>();
        MyContactsFragment contactsFragment = new MyContactsFragment(HomeActivity.this);
        MyMessageFragment messageFragment = new MyMessageFragment();
        MyMeFragment meFragment = new MyMeFragment();
        MyShareFragment shareFragment = new MyShareFragment();
        fragmentList.add(messageFragment);
        fragmentList.add(contactsFragment);
        fragmentList.add(shareFragment);
        fragmentList.add(meFragment);
    }
    public void initViewPager(){
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),fragmentList);
        myViewPager.setAdapter(fragmentAdapter);
    }
    private class MyButtonOnClickListener implements View.OnClickListener{
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tab_message:
                    myViewPager.setCurrentItem(0);
                    break;
                case R.id.tab_address:
                    myViewPager.setCurrentItem(1);
                    break;
                case R.id.tab_share:
                    myViewPager.setCurrentItem(2);
                    break;
                case R.id.tab_me:
                    myViewPager.setCurrentItem(3);
                    break;
            }

        }
    }
    private void initListener() {
        MyButtonOnClickListener listener = new MyButtonOnClickListener();
        layout_contacts = (LinearLayout) findViewById(R.id.tab_address);
        layout_message = (LinearLayout) findViewById(R.id.tab_message);
        layout_share = (LinearLayout) findViewById(R.id.tab_share);
        layout_me = (LinearLayout) findViewById(R.id.tab_me);

        layout_contacts.setOnClickListener(listener);
        layout_message.setOnClickListener(listener);
        layout_share.setOnClickListener(listener);
        layout_me.setOnClickListener(listener);
    }
	/*	@Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu,menu);
            return super.onCreateOptionsMenu(menu);
        }*/
}
