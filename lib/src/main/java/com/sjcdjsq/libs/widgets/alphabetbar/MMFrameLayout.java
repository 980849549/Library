package com.sjcdjsq.libs.widgets.alphabetbar;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

import android.content.Context;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class MMFrameLayout extends FrameLayout {
    final /* synthetic */ MMPopupWindow iqy;

    public MMFrameLayout(MMPopupWindow cmVar, Context context) {
        super(context);
        this.iqy = cmVar;
    }

    protected final int[] onCreateDrawableState(int i) {
        return super.onCreateDrawableState(i);
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (getKeyDispatcherState() == null) {
            return super.dispatchKeyEvent(keyEvent);
        }
        DispatcherState keyDispatcherState;
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            keyDispatcherState = getKeyDispatcherState();
            if (keyDispatcherState == null) {
                return true;
            }
            keyDispatcherState.startTracking(keyEvent, this);
            return true;
        }
        if (keyEvent.getAction() == 1) {
            keyDispatcherState = getKeyDispatcherState();
            if (!(keyDispatcherState == null || !keyDispatcherState.isTracking(keyEvent) || keyEvent.isCanceled())) {
                this.iqy.dismiss();
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.iqy.iqx == null || !this.iqy.iqx.onTouch(this, motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getAction() == 0 && (x < 0 || x >= getWidth() || y < 0 || y >= getHeight())) {
            this.iqy.dismiss();
            return true;
        } else if (motionEvent.getAction() != 4) {
            return super.onTouchEvent(motionEvent);
        } else {
            this.iqy.dismiss();
            return true;
        }
    }

    public final void sendAccessibilityEvent(int i) {
        if (getChildCount() == 1) {
            getChildAt(0).sendAccessibilityEvent(i);
        } else {
            super.sendAccessibilityEvent(i);
        }
        super.sendAccessibilityEvent(i);
    }
}
