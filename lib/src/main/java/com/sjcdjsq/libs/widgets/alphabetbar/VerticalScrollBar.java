package com.sjcdjsq.libs.widgets.alphabetbar;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lib.cooby.R;


public abstract class VerticalScrollBar extends View {
    private Paint dXO;
    private TextView isJ;
    protected float iuT;
    protected int iuU;
    protected String[] iuV;
    private int iuW;
    private float iuX;
    private float iuY;
    private MMPopupWindow iuZ;
    private int iva;
    private OnScrollLocationLinstener ivb;

    protected abstract void aLZ();

    protected abstract int aMa();

    public VerticalScrollBar(Context context) {
        super(context);
        init(context);
    }

    public VerticalScrollBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public final void setOnScrollLocation(OnScrollLocationLinstener flVar) {
        this.ivb = flVar;
    }

    public final void aOf() {
        this.ivb = null;
    }

    private void init(Context context) {
        aLZ();
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.iva = a(context, 3.0f);
        View inflate = inflate(context, aMa(), null);
        int a = a(context, (float) this.iuU);
        this.iuZ = new MMPopupWindow(inflate, a, a);
        this.isJ = (TextView) inflate.findViewById(R.id.show_head_toast_text);
        this.dXO = new Paint();
        this.dXO.setAntiAlias(true);
        this.dXO.setColor(-8024940);
        this.dXO.setTextAlign(Align.CENTER);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        this.iuX = ((float) measuredHeight) / (((float) this.iuV.length) * this.iuT);
        this.dXO.setTextSize(this.iuX);
        for (measuredHeight = 0; measuredHeight < this.iuV.length; measuredHeight++) {
            canvas.drawText(this.iuV[measuredHeight], ((float) measuredWidth) / 2.0f, this.iuX + ((((float) measuredHeight) * this.iuX) * this.iuT), this.dXO);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
            this.iuY = motionEvent.getY();
            if (this.iuY < 0.0f) {
                this.iuY = 0.0f;
            }
            if (this.iuY > ((float) getMeasuredHeight())) {
                this.iuY = (float) getMeasuredHeight();
            }
            setBackgroundDrawable(n(getContext(), R.drawable.scrollbar_bg));
            int i = (int) (this.iuY / (this.iuX * this.iuT));
            if (i >= this.iuV.length) {
                i = this.iuV.length - 1;
            }
            this.iuW = i;
            if (this.iuW == -1) {
                this.isJ.setText(R.string.scroll_bar_search);
            } else {
                this.isJ.setText(this.iuV[this.iuW]);
            }
            this.iuZ.showAtLocation(this, 17, 0, 0);
            if (this.ivb != null) {
                if (this.iuW == -1) {
                    this.ivb.OnAtLocation(getContext().getString(R.string.scroll_bar_search));
                } else {
                    this.ivb.OnAtLocation(this.iuV[this.iuW]);
                }
            }
            invalidate();
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            setBackgroundResource(0);
            this.iuZ.dismiss();
        }
        return true;
    }

    public static int a(Context context, float f) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return Math.round((((float) displayMetrics.densityDpi) * f) / 160.0f);
    }

    public static Drawable n(Context context, int i) {
        if (context != null) {
            return context.getResources().getDrawable(i);
        }
        // y.e("nr", "get drawable, resId %d, but context is null", Integer.valueOf(i));
        return null;
    }
}

