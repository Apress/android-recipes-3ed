package com.examples.customwindowfeatures;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class HideActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }
    
    public void onToggleClick(View v) {
        //Here we only need to hide the controls on a tap because
        // the system will make the controls re-appear automatically
        // anytime the screen is tapped after they are hidden.
        v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
