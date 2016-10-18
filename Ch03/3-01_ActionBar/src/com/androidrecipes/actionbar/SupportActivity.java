
package com.androidrecipes.actionbar;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class SupportActivity extends ActionBarActivity implements TabListener, OnNavigationListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ActionBar actionBar = getSupportActionBar();
        
        //Enable taps on the home logo
        actionBar.setHomeButtonEnabled(true);
        //Display home with the "up" arrow indicator
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Set the title text
        actionBar.setTitle("Android Recipes");
        //Set the subtitle text
        actionBar.setSubtitle("ActionBar Styling");
        //Set custom view
//        ImageView iv = new ImageView(this);
//        iv.setImageResource(R.drawable.ic_launcher);
//        actionBar.setCustomView(iv,
//                new ActionBar.LayoutParams(
//                        LayoutParams.MATCH_PARENT,
//                        LayoutParams.MATCH_PARENT) );
//        actionBar.setDisplayShowCustomEnabled(true);

        //Tabs Navigation
//        actionBar.addTab(
//                actionBar.newTab().setText("Tab One").setTabListener(this));
//        actionBar.addTab(
//                actionBar.newTab().setText("Tab Two").setTabListener(this));
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        //List Navigation
//        Context context = actionBar.getThemedContext();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, new String[] {"Item", "Item", "Item"});
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        actionBar.setListNavigationCallbacks(adapter, this);
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.support, menu);        
        return true;
    }
    
    /* TabListener Callback Methods */
    
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) { }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) { }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) { }

    /* OnNavigationListener Callback Methods */
    
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }
}
