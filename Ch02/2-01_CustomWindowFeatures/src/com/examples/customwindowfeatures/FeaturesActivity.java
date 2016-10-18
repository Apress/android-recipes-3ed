package com.examples.customwindowfeatures;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class FeaturesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_LEFT_ICON);
//        requestWindowFeature(Window.FEATURE_RIGHT_ICON);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        
        setContentView(new TextView(this));
        
//        setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
//        setFeatureDrawableResource(Window.FEATURE_RIGHT_ICON, R.drawable.ic_launcher);
        setProgress(7500);
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);
    }
}
