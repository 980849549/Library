package com.sjcdjsq.libs.widgets.alphabetbar;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

public class MMPopupWindow extends PopupWindow {
    public OnTouchListener iqx;
    private Context mContext = null;

    public MMPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        super.setBackgroundDrawable(null);
    }

    public MMPopupWindow(View view) {
        super(view);
        super.setBackgroundDrawable(null);
    }

    public MMPopupWindow(View view, int i, int i2) {
        super(view, i, i2);
        super.setBackgroundDrawable(null);
    }

    public MMPopupWindow(View view, int i, int i2, byte b) {
        super(view, i, i2, true);
        super.setBackgroundDrawable(null);
    }

    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            //y.e("WD", "dismiss exception, e = " + e.getMessage());
        }
    }

    public Drawable getBackground() {
        View contentView = getContentView();
        if (contentView != null && (contentView instanceof MMFrameLayout)) {
            return contentView.getBackground();
        }
        return null;
    }

    public void setContentView(View view) {
        int i = -2;
        View contentView = getContentView();
        if (contentView == null) {
            super.setContentView(view);
        } else if (contentView instanceof MMFrameLayout) {
            MMFrameLayout cnVar = (MMFrameLayout) contentView;
            cnVar.removeAllViews();
            if (view == null) {
                super.setContentView(cnVar);
                return;
            }
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null || layoutParams.height != -2) {
                i = -1;
            }
            cnVar.addView(view, new FrameLayout.LayoutParams(-1, i));
            super.setContentView(cnVar);
            return;
        }
        super.setContentView(view);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        int i = -2;
        View contentView = getContentView();
        if (contentView != null) {
            Context context = contentView.getContext();
            if (contentView instanceof MMFrameLayout) {
                contentView.setBackgroundDrawable(drawable);
                return;
            }
            LayoutParams layoutParams = contentView.getLayoutParams();
            if (layoutParams == null || layoutParams.height != -2) {
                i = -1;
            }
            FrameLayout cnVar = new MMFrameLayout(this, context);
            LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, i);
            cnVar.setBackgroundDrawable(drawable);
            cnVar.addView(contentView, layoutParams2);
            super.setContentView(cnVar);
        } else if (drawable != null) {
            View cnVar2 = new MMFrameLayout(this, mContext);
            cnVar2.setBackgroundDrawable(drawable);
            super.setContentView(cnVar2);
        }
    }

    public void setTouchInterceptor(OnTouchListener onTouchListener) {
        this.iqx = onTouchListener;
    }
}
