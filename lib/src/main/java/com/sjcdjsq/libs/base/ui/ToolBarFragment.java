package com.sjcdjsq.libs.base.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjcdjsq.libs.utils.AndroidUtils;
import com.sjcdjsq.libs.utils.DensityUtil;
import com.lib.cooby.R;


/**
 * Created by Karry on 2017/1/7 0007.
 */

public abstract class ToolBarFragment extends BaseFragment implements ITitelBar {
    private Toolbar mToolbar;
    private TextView mTvTitle;
    private TextView mLeftText, mRightText;
    private TextView mRightText2;
    private LinearLayout mLinTitle;

    public LinearLayout getLinToolbar() {
        return mLinToolbar;
    }

    private LinearLayout mLinToolbar;

    @Override
    protected void initToolbar(View view) {
        mLinToolbar = (LinearLayout) view.findViewById(R.id.lin_toolbar);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mLeftText = (TextView) view.findViewById(R.id.tv_left);
        mRightText = (TextView) view.findViewById(R.id.tv_right);
        mRightText2 = (TextView) view.findViewById(R.id.tv_right2);
        mLinTitle = (LinearLayout) view.findViewById(R.id.lin_title);
        if (mToolbar == null)
            return;
        AndroidUtils.paddingStatus(mLinToolbar, getActivity());
        mToolbar.setNavigationIcon(R.drawable.base_action_bar_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1)
                    pop();
                else
                    getActivity().finish();
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
        mToolbar.getMenu().clear();
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
    public void setLeftText(String text, int draw, View.OnClickListener onClickListener) {
        if (onClickListener != null)
            mLeftText.setOnClickListener(onClickListener);
        mLeftText.setText(text);
        if (draw == 0)
            return;
        Drawable img = getResources().getDrawable(draw);
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        mLeftText.setCompoundDrawables(img, null, null, null);
        mLeftText.setCompoundDrawablePadding(dip2px(getContext(), 5));
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    public void setRightText(String text, View.OnClickListener onClickListener) {
        mRightText.setText(text);
        mRightText.setOnClickListener(onClickListener);
    }

    @Override
    public void setRightText2(String text, View.OnClickListener onClickListener) {
        mRightText.setText(text);
        if (onClickListener != null)
            mRightText.setOnClickListener(onClickListener);
    }

    @Override
    public void hideToolbar() {
        mLinToolbar.setVisibility(View.GONE);
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
        if (icon == 0)
            mToolbar.setNavigationIcon(null);
        else {
            if (icon != -1)
                mToolbar.setNavigationIcon(icon);
        }
        if (onClickListener != null)
            mToolbar.setNavigationOnClickListener(onClickListener);
    }

    @Override
    public void setLeftTextPadding(int dp) {
        mLeftText.setPadding(DensityUtil.dip2px(mContext, dp), 0, 0, 0);
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

    @Override
    public void setBarLinBackgroudResource(int draw) {
        mLinToolbar.setBackgroundResource(draw);
    }

    @Override
    public void setRightIcon_1(int draw, View.OnClickListener onClickListener) {
        if (onClickListener != null)
            mRightText.setOnClickListener(onClickListener);
        if (draw == 0)
            return;
        Drawable img = getResources().getDrawable(draw);
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        mRightText.setCompoundDrawables(img, null, null, null);
        mRightText.setCompoundDrawablePadding(dip2px(getContext(), 5));
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
        mRightText2.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 6), 0, DensityUtil.dip2px(mContext, 5));
    }

    /**
     * 设置首页的日历图标
     *
     * @param draw
     * @param text
     * @param onClickListener
     */
    public void setRightIcon_2(int draw, String text, View.OnClickListener onClickListener) {
        if (onClickListener != null)
            mRightText2.setOnClickListener(onClickListener);
        if (draw == 0)
            return;
        mRightText2.setPadding(0, DensityUtil.dip2px(mContext, 5), 0, 0);
        mRightText2.setBackgroundResource(draw);
        mRightText2.setTextSize(8);
        mRightText2.setText(text);
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


    public LinearLayout getLinTitle() {
        return mLinTitle;
    }

    public void hideNavigation() {
        mToolbar.setNavigationIcon(null);
    }
}
