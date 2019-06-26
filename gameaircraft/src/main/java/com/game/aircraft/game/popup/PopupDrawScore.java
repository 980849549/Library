package com.game.aircraft.game.popup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.game.aircraft.R;
import com.game.aircraft.WelActivity2;
import com.game.aircraft.game.GameView;


public class PopupDrawScore extends PopupWindow {


    private View view;
    private GameView mGgameView;
    private Button btn_start_game;
    private TextView tv_fraction;
    private Activity activity;


    public PopupDrawScore(final Activity mContext, GameView gameView) {
        activity = mContext;
        this.mGgameView = gameView;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popup_draw_score_item_layout, null);

        view.findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btn_start_game = view.findViewById(R.id.btn_start_game);
        tv_fraction = view.findViewById(R.id.tv_fraction);
        btn_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startGameName = btn_start_game.getText().toString().trim();
                if ("继续".equals(startGameName)) {
                    isfinsh = false;
                    mGgameView.resume();
                } else if ("重新开始".equals(startGameName)) {
                    isfinsh = false;
                    mContext.startActivity(new Intent(mContext, WelActivity2.class));
                    mContext.finish();
                }
                dismiss();
            }
        });


        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
// 如果需要点击空白地方隐藏，取消这个服注释即可
                        dismiss();
                    }
                }
                return true;
            }
        });

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //监听popup Dismiss之前要做的操作
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if (isfinsh) {
                    activity.finish();
                }
                dismiss();
            }
        });

        // 设置弹出窗体可点击 点击返回键关闭popup
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为全透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.ActionSheetDialogAnimation);
    }

    public void setBtnStartGame_FractionText(String operation, String allScore) {
        if (this.btn_start_game != null) {
            this.btn_start_game.setText(operation);
        }
        if (this.tv_fraction != null) {
            this.tv_fraction.setText("分数\n" + allScore);
        }
    }

    private boolean isfinsh = true;

}