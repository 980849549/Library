package com.sjcdjsq.libs.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class RoundTextView extends TextView {

    public RoundTextView(Context context) {
        super(context);
        setTextColor(Color.WHITE);
        setPadding(dip2px(context, 5), 5, dip2px(context, 5), 5);
        setGravity(Gravity.CENTER);
        setTextSize(10);

        //圆角
        float[] outRectr = new float[]{10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
        RoundRectShape roudRectShape = new RoundRectShape(outRectr, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roudRectShape);
        // 背景选择器
        StateListDrawable stateDrawable = new StateListDrawable();
        stateDrawable.addState(new int[]{}, shapeDrawable);
        // 设置背景选择器到TextView上
        setBackground(stateDrawable);
    }

    @Override
    public void setBackgroundColor(int color) {
        //圆角
        float[] outRectr = new float[]{10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
        RoundRectShape roudRectShape = new RoundRectShape(outRectr, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roudRectShape);
        shapeDrawable.getPaint().setColor(color);
        // 背景选择器
        StateListDrawable stateDrawable = new StateListDrawable();
        stateDrawable.addState(new int[]{}, shapeDrawable);
        // 设置背景选择器到TextView上
        setBackground(stateDrawable);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
