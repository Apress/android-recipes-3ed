package com.androidrecipes.scenetransitions;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

public class SlideTransition extends Visibility {

    /*
     * Constants for all supported slide directions
     */
    public static final int DIR_RIGHT = -1;
    public static final int DIR_LEFT = -2;
    public static final int DIR_UP = -3;
    
    private int mDirection;
    
    public SlideTransition(int direction) {
        mDirection = direction;
    }
    
    @Override
    public Animator onAppear(ViewGroup sceneRoot,
            TransitionValues startValues, int startVisibility,
            TransitionValues endValues, int endVisibility) {

        final View endView = endValues.view;
        /*
         * In each case, set the initial translation value, and return
         * and Animator that will slide the view in the specified direction
         */
        switch (mDirection) {
            case DIR_RIGHT:
                endView.setTranslationX(endView.getWidth());
                return ObjectAnimator.ofFloat(endView, "translationX", 0);
            case DIR_LEFT:
                endView.setTranslationX(-endView.getWidth());
                return ObjectAnimator.ofFloat(endView, "translationX", 0);
            case DIR_UP:
                endView.setTranslationY(endView.getHeight());
                return ObjectAnimator.ofFloat(endView, "translationY", 0);
            default:
                //No animation
                return null;
        }
    }
    
    @Override
    public Animator onDisappear(ViewGroup sceneRoot,
            TransitionValues startValues, int startVisibility,
            TransitionValues endValues, int endVisibility) {

        /*
         * We have to track this view until the animation is over,
         * add it to the sceneRoot's overlay until then
         */
        final View startView = startValues.view;
        final ViewGroup finalSceneRoot = sceneRoot;
        finalSceneRoot.getOverlay().add(startView);
        
        //Construct an Animator that slides in the direction specified
        ObjectAnimator animator;
        switch (mDirection) {
            case DIR_RIGHT:
                animator = ObjectAnimator.ofFloat(startView, "translationX", -startView.getWidth());
                break;
            case DIR_LEFT:
                animator = ObjectAnimator.ofFloat(startView, "translationX", startView.getWidth());
                break;
            case DIR_UP:
                animator = ObjectAnimator.ofFloat(startView, "translationY", -startView.getHeight());
                break;
            default:
                //No animation
                return null;
        }
        
        /*
         * Construct a listener for the Animator to remove
         * the view from the overlay when the animation is complete,
         * or terminates prematurely.
         */
        animator.addListener(new Animator.AnimatorListener() {
            
            @Override
            public void onAnimationStart(Animator animation) { }
            
            @Override
            public void onAnimationRepeat(Animator animation) { }
            
            @Override
            public void onAnimationEnd(Animator animation) {
                finalSceneRoot.getOverlay().remove(startView);
            }
            
            @Override
            public void onAnimationCancel(Animator animation) {
                finalSceneRoot.getOverlay().remove(startView);
            }
        });
        
        return animator;
    }
}
