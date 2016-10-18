package com.androidrecipes.scenetransitions;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class SceneActivity extends Activity {

    private Scene mScene1, mScene2, mScene3;
    private TransitionManager mTransitionManager;
    
    private int mCurrentScene;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        
        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.root);
        
        TransitionInflater inflater = TransitionInflater.from(this);
        
        mScene1 = Scene.getSceneForLayout(sceneRoot, R.layout.transition_step1, this);
        mScene2 = Scene.getSceneForLayout(sceneRoot, R.layout.transition_step2, this);
        mScene3 = Scene.getSceneForLayout(sceneRoot, R.layout.transition_step3, this);
        
        mTransitionManager = inflater.inflateTransitionManager(R.transition.manager, sceneRoot);
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
