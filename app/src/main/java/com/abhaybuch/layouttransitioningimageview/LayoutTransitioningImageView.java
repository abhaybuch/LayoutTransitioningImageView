package com.abhaybuch.layouttransitioningimageview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Subclass of {@link android.widget.ImageView} that calls {@link #setFrame(int, int, int, int)} in
 * {@link #onSizeChanged(int, int, int, int)} which in turn updates it's {@link android.graphics.Matrix}. This makes
 * it transition more gracefully under LayoutTransition. It would probably be better to hook directly into the
 * properties that are animated by the LayoutTransition (i.e. {@link #setLeft(int)} and friends) but those methods
 * are final. So we rely on the fact that the {@link android.view.View} implementation of those calls
 * {@link #onSizeChanged(int, int, int, int)}.
 */
public class LayoutTransitioningImageView extends ImageView {

    /**
     * Used only for demo purposes. In production use the whole point of this view is to set the frame on size change
     * so there's no point in making this configurable.
     */
    private boolean mShouldSetFrameOnSizeChanged = false;

    public LayoutTransitioningImageView(Context context) {
        super(context);
    }

    public LayoutTransitioningImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutTransitioningImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Used only for demo purposes. In production use the whole point of this view is to set the frame on size change
     * so there's no point in making this configurable.
     */
    public void setShouldSetFrameOnSizeChanged(boolean shouldSetFrameOnSizeChanged) {
        mShouldSetFrameOnSizeChanged = shouldSetFrameOnSizeChanged;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mShouldSetFrameOnSizeChanged) {
            setFrame(getLeft(), getTop(), getRight(), getBottom());
        }
    }
}
