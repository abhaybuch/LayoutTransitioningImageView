package com.abhaybuch.layouttransitioningimageview;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    /**
     * The view that will be added and removed to trigger the {@link android.animation.LayoutTransition}.
     * It will fade in with the {@link android.animation.LayoutTransition#APPEARING} transition
     * and fade out with the {@link android.animation.LayoutTransition#DISAPPEARING} transition.
     * */
    private View mDummyView;

    /**
     * The {@link android.widget.ImageView} that will be resized by {@link #mDummyView} being added and removed.
     * It will shrink with the {@link android.animation.LayoutTransition#CHANGE_APPEARING} transition
     * android grow with the {@link android.animation.LayoutTransition#CHANGE_DISAPPEARING} transition.
     *
     * The animations will look nice if we have
     * {@link LayoutTransitioningImageView#setShouldSetFrameOnSizeChanged(boolean)} to true and will look off
     * if we set it to false.
     */
    private LayoutTransitioningImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We could have set this in xml by setting android:animateLayoutChanges="true" but it's nice to make it
        // obvious that we're doing it.
        ViewGroup contentView = (ViewGroup) findViewById(R.id.content_view);
        contentView.setLayoutTransition(new LayoutTransition());

        mDummyView = findViewById(R.id.dummy_view);
        mImageView = (LayoutTransitioningImageView) findViewById(R.id.image_view);

        // Default to true. The associated menu toggle is checked.
        mImageView.setShouldSetFrameOnSizeChanged(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // The menu has two buttons:
        // 1. A toggle button to show and hide the dummy view and thus trigger LayoutTransitions.
        // 2. A checkbox for whether the LayoutTransitioningImageView will use it's modifications to animate nicely.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_toggle) {
            // Switching the visibility between VISIBLE and GONE is the same as adding and removing the view as far
            // as the LayoutTransition is concerned. Any change in the visibility will cancel the currently running
            // transition and start a new one.
            if (mDummyView.getVisibility() == View.GONE) {
                mDummyView.setVisibility(View.VISIBLE);
            } else {
                mDummyView.setVisibility(View.GONE);
            }
        } else if (id == R.id.action_transition_correctly) {
            if (item.isChecked()) {
                item.setChecked(false);
                mImageView.setShouldSetFrameOnSizeChanged(false);
            } else {
                item.setChecked(true);
                mImageView.setShouldSetFrameOnSizeChanged(true);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
