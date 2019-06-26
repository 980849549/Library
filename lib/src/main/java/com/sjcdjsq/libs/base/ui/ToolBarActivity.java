package com.sjcdjsq.libs.base.ui;


import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjcdjsq.libs.utils.AndroidUtils;
import com.sjcdjsq.libs.utils.DensityUtil;
import com.lib.cooby.R;


/**
 * Created by Karrye on 2017/1/4 0004.
 */

public abstract class ToolBarActivity extends BaseActivity implements ITitelBar {
    private Toolbar mToolbar;
    private TextView mTvTitle;
    private TextView mLeftText, mRightText;
    private LinearLayout mLinToolbar;
    private TextView mRightText2;
    private LinearLayout mLinTitle;

    @Override
    protected void initToolbar() {
        mLinToolbar = (LinearLayout) findViewById(R.id.lin_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mLeftText = (TextView) findViewById(R.id.tv_left);
        mRightText = (TextView) findViewById(R.id.tv_right);
        mRightText2 = (TextView) findViewById(R.id.tv_right2);
        mLinTitle = (LinearLayout) findViewById(R.id.lin_title);
        if (mToolbar == null)
            return;
        AndroidUtils.paddingStatus(mLinToolbar, this);
        mToolbar.setNavigationIcon(R.drawable.base_action_bar_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    @Override
    public void setLeftIcon(int icon, View.OnClickListener onClickListener) {
        mToolbar.setNavigationIcon(icon);
        mToolbar.setNavigationOnClickListener(onClickListener);
    }

    @Override
    public void setRightMenu(int menu, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        mToolbar.inflateMenu(menu);
        mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public void setToolBarBg(int draw) {
        mLinToolbar.setBackgroundResource(draw);
    }

    @Override
    public void setLeftText(String text, View.OnClickListener onClickListener) {
        if (onClickListener != null)
            mLeftText.setOnClickListener(onClickListener);
        mLeftText.setText(text);
    }

    @Override
    public void hideToolbar() {
        mLinToolbar.setVisibility(View.GONE);
    }

    @Override
    public void setLeftText(String text, int draw, View.OnClickListener onClickListener) {
        if (onClickListener != null)
            mLeftText.setOnClickListener(onClickListener);
        mLeftText.setText(text);
        if (draw == 0)
            return;
        Drawable img = getResources().getDrawable(draw);
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        mLeftText.setCompoundDrawables(img, null, null, null); //设置左图标
    }

    @Override
    public void setRightText(String text, View.OnClickListener onClickListener) {
        mRightText.setText(text);
        if (onClickListener != null)
            mRightText.setOnClickListener(onClickListener);
    }


    @Override
    public void setRightText2(String text, View.OnClickListener onClickListener) {
        mRightText2.setText(text);
        if (onClickListener != null)
            mRightText2.setOnClickListener(onClickListener);
    }

    public int statusbarHeight() {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    @Override
    public void setNavigationOnClickListener(int icon, View.OnClickListener onClickListener) {
        if (icon != 0)
            mToolbar.setNavigationIcon(icon);
        if (onClickListener != null)
            mToolbar.setNavigationOnClickListener(onClickListener);
    }

    @Override
    public void setLeftTextPadding(int dp) {
        mLeftText.setPadding(DensityUtil.dip2px(mContext, dp), 0, 0, 0);
    }

    @Override
    public void setRightIcon_1(int draw, View.OnClickListener onClickListener) {
        if (onClickListener != null)
            mRightText.setOnClickListener(onClickListener);
        if (draw == 0)
            return;
        Drawable img = getResources().getDrawable(draw);
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        mRightText.setCompoundDrawables(null, null, img, null);

    }


    @Override
    public void setRightIcon_2(int draw, View.OnClickListener onClickListener) {
        if (onClickListener != null)
            mRightText2.setOnClickListener(onClickListener);
        if (draw == 0)
            return;
        Drawable img = getResources().getDrawable(draw);
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        mRightText2.setCompoundDrawables(null, null, img, null);
    }

    @Override
    public void addTitlelayout(View view) {
        if (view == null)
            return;
        if (mLinTitle.getChildCount() != 0) {
            mLinTitle.removeAllViews();
        }
        mLinTitle.setVisibility(View.VISIBLE);
        mLinTitle.addView(view);
    }

    @Override
    public void setBarLinBackgroudResource(int draw) {
        mLinToolbar.setBackgroundResource(draw);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public TextView getLeftText() {
        return mLeftText;
    }

    public TextView getRightText() {
        return mRightText;
    }

    public LinearLayout getLinToolbar() {
        return mLinToolbar;
    }

}
