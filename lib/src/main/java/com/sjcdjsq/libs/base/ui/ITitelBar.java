package com.sjcdjsq.libs.base.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Karry on 2017/1/5 0005.
 */

public interface ITitelBar {
    /**
     * 设置title
     *
     * @param title
     */
    void setTitle(String title);

    /**
     * 左侧按钮
     *
     * @param icon
     * @param onClickListener
     */
    void setLeftIcon(int icon, View.OnClickListener onClickListener);

    /**
     * 右侧菜单
     *
     * @param menu
     * @param onMenuItemClickListener
     */
    void setRightMenu(int menu, Toolbar.OnMenuItemClickListener onMenuItemClickListener);

    /**
     * 设置背景
     *
     * @param draw
     */
    void setToolBarBg(int draw);

    /**
     * 设置左侧文字
     *
     * @param text
     * @param onClickListener
     */
    void setLeftText(String text, View.OnClickListener onClickListener);

    /**
     * 设置左侧文字及图片
     *
     * @param text
     * @param draw
     * @param onClickListener
     */
    void setLeftText(String text, int draw, View.OnClickListener onClickListener);

    /**
     * 设置右侧文字
     *
     * @param text
     * @param onClickListener
     */
    void setRightText(String text, View.OnClickListener onClickListener);

    void setRightText2(String text, View.OnClickListener onClickListener);

    void setNavigationOnClickListener(int icon, View.OnClickListener onClickListener);

    void setLeftTextPadding(int padding);


    void setRightIcon_1(int icon_1, View.OnClickListener onClickListener);

    /**
     * 设置最右边的图片
     *
     * @param icon_2
     */
    void setRightIcon_2(int icon_2, View.OnClickListener onClickListener);

    void addTitlelayout(View view);

    void setBarLinBackgroudResource(int draw);

    void hideToolbar();
}
