
package com.androidrecipes.scenetransitions;

import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class TransitionActivity extends Activity {

    private ViewGroup mRootView;
    private View mButton, mImage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        
        mRootView = (ViewGroup) findViewById(R.id.root);
        mButton = findViewById(R.id.button);
        mImage = findViewById(R.id.image);
    }

    public void onTransitionClick(View v) {
        //Start a new transition frame
        Transition transition = new AutoTransition();
        transition.setDuration(500);
        TransitionManager.beginDelayedTransition(mRootView, transition);
        /*
         * All methods called from now until the next loop
         * iteration will be animated together as a single
         * transition.
         */
        FrameLayout.LayoutParams buttonLp =
                (FrameLayout.LayoutParams) mButton.getLayoutParams();
        FrameLayout.LayoutParams imageLp =
                (FrameLayout.LayoutParams) mImage.getLayoutParams();
        /*
         * We are modifying the LayoutParams in place so technically they
         * don't need to be set again.  However, the transition needs a layout
         * trigger.  In steps 1/3 that trigger can come from the visibility
         * change and setLayoutParams() isn't necessary; but in step 2
         * nothing will happen without calling this method because no other
         * methods trigger that a change has occurred. 
         */
        if (mImage.getVisibility() == View.VISIBLE && mImage.getTop() == 0) {
            //Last step, reset the view
            buttonLp.gravity = Gravity.CENTER;
            imageLp.gravity = Gravity.CENTER;
            mButton.setLayoutParams(buttonLp);
            mImage.setLayoutParams(imageLp);
            mImage.setVisibility(View.GONE);
        } else if (mImage.getVisibility() == View.VISIBLE) {
            //Step 2
            buttonLp.gravity = Gravity.BOTTOM;
            imageLp.gravity = Gravity.TOP;
            mButton.setLayoutParams(buttonLp);
            mImage.setLayoutParams(imageLp);
        } else {
            //Step 1
            buttonLp.gravity = Gravity.TOP;
            mButton.setLayoutParams(buttonLp);
            mImage.setVisibility(View.VISIBLE);            
        }
    }
}
