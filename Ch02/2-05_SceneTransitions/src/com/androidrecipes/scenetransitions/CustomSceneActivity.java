package com.androidrecipes.scenetransitions;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CustomSceneActivity extends Activity {

    private Scene mScene1, mScene2, mScene3;
    private TransitionManager mTransitionManager;
    
    private int mCurrentScene;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        
        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.root);
        
//        mScene1 = Scene.getSceneForLayout(sceneRoot, R.layout.transition_step1, this);
//        mScene2 = Scene.getSceneForLayout(sceneRoot, R.layout.transition_step2, this);
//        mScene3 = Scene.getSceneForLayout(sceneRoot, R.layout.transition_step3, this);
        
        //4.4 Scene caching workaround
        mScene1 = new Scene(sceneRoot, (ViewGroup) LayoutInflater.from(this).inflate(R.layout.transition_step1, sceneRoot, false));
        mScene2 = new Scene(sceneRoot, (ViewGroup) LayoutInflater.from(this).inflate(R.layout.transition_step2, sceneRoot, false));
        mScene3 = new Scene(sceneRoot, (ViewGroup) LayoutInflater.from(this).inflate(R.layout.transition_step3, sceneRoot, false));
        
        mTransitionManager = new TransitionManager();
        //Set to 'null' to use the default AutoTransition
        mTransitionManager.setTransition(mScene1, null);
        mTransitionManager.setTransition(mScene2, mScene3, new SlideTransition(SlideTransition.DIR_RIGHT));
        mTransitionManager.setTransition(mScene3, mScene2, new SlideTransition(SlideTransition.DIR_LEFT));
        //Initialize our scene tracker
        mCurrentScene = 1;
    }
    
    public void onBackClick(View v) {
        //Iterate backward through the scenes on each click
        switch (mCurrentScene) {
            case 1:
                mTransitionManager.transitionTo(mScene3);
                mCurrentScene = 3;
                break;
            case 2:
                mTransitionManager.transitionTo(mScene1);
                mCurrentScene = 1;
                break;
            case 3:
                mTransitionManager.transitionTo(mScene2);
                mCurrentScene = 2;
                break;
            default:
                break;
        }
    }
    
    public void onForwardClick(View v) {
        //Iterate forward through the scenes on each click
        switch (mCurrentScene) {
            case 1:
                mTransitionManager.transitionTo(mScene2);
                mCurrentScene = 2;
                break;
            case 2:
                mTransitionManager.transitionTo(mScene3);
                mCurrentScene = 3;
                break;
            case 3:
                mTransitionManager.transitionTo(mScene1);
                mCurrentScene = 1;
                break;
            default:
                break;
        }
    }
}
