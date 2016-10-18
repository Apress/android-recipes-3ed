package com.examples.optionsmenu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class OptionsActivity extends Activity implements
        PopupMenu.OnMenuItemClickListener,
        CompoundButton.OnCheckedChangeListener {

    private MenuItem mOptionsItem;
    private CheckBox mFirstOption, mSecondOption;
    
    private PopupMenu mPopup;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Create a PopupMenu to display just below our TextView
        mPopup = new PopupMenu(this, findViewById(R.id.anchor));
        mPopup.setOnMenuItemClickListener(this);
        //Use the same options menu as our Activity
        mPopup.inflate(R.menu.options);
    }

    public void onShowMenuClick(View v) {
        mPopup.show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Use this callback to create the menu and do any
        // initial setup necessary
        getMenuInflater().inflate(R.menu.options, menu);
        
        //Find and initialize our action item
        mOptionsItem = menu
                .findItem(R.id.menu_add);
        mOptionsItem
                .setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

                    @Override
                    public boolean onMenuItemActionExpand(
                            MenuItem item) {
                        // Must return true to have item expand
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(
                            MenuItem item) {
                        mFirstOption.setChecked(false);
                        mSecondOption.setChecked(false);
                        // Must return true to have item collapse
                        return true;
                    }
                });

        mFirstOption = (CheckBox) mOptionsItem
                .getActionView()
                .findViewById(R.id.option_first);
        mFirstOption
                .setOnCheckedChangeListener(this);
        mSecondOption = (CheckBox) mOptionsItem
                .getActionView()
                .findViewById(R.id.option_second);
        mSecondOption
                .setOnCheckedChangeListener(this);
        
        return true;
    }
    
    /* CheckBox Callback Methods */
    
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mFirstOption.isChecked() && mSecondOption.isChecked()) {
            mOptionsItem.collapseActionView();
        }
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //Use this callback to do setup that needs to happen
        // each time the menu opens
        return super.onPrepareOptionsMenu(menu);
    }
    
    //Callback from the PopupMenu click
    public boolean onMenuItemClick(MenuItem item) {
        menuItemSelected(item);
        return true;        
    }
    
    //Callback from a standard options menu click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        menuItemSelected(item);
        return true;
    }
    
    //Private helper so each unique callback can trigger the same actions
    private void menuItemSelected(MenuItem item) {
        //Get the selected option by id
        switch (item.getItemId()) {
        case R.id.menu_add:
            //Do add action
            break;
        case R.id.menu_remove:
            //Do remove action
            break;
        case R.id.menu_edit:
            //Do edit action
            break;
        case R.id.menu_settings:
            //Do settings action
            break;
        default:
            break;
        }
    }
}
