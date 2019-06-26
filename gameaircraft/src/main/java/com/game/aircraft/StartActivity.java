package com.game.aircraft;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.sjcdjsq.libs.base.ui.BaseActivity;

public class StartActivity extends BaseActivity {

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void afterCreate() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btnGame) {
            startGame();
        }
    }

    public void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
