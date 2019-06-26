package com.game.aircraft;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;

import com.game.aircraft.game.GameView;
import com.game.aircraft.game.interfaceGame.IListenerGameView;
import com.game.aircraft.game.popup.PopupDrawScore;


public class GameActivity extends Activity implements IListenerGameView {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initUi();

    }

    private void initUi() {
        gameView = (GameView) findViewById(R.id.gameView);
        //0:combatAircraft
        //1:explosion
        //2:yellowBullet
        //3:blueBullet
        //4:smallEnemyPlane
        //5:middleEnemyPlane
        //6:bigEnemyPlane
        //7:bombAward
        //8:bulletAward
        //9:pause1
        //10:pause2
        //11:bomb
        int[] bitmapIds = {
                R.drawable.plane,
                R.drawable.explosion,
                R.drawable.yellow_bullet,
                R.drawable.blue_bullet,
                R.drawable.small,
                R.drawable.middle,
                R.drawable.big,
                R.drawable.bomb_award,
                R.drawable.bullet_award,
                R.drawable.pause1,
                R.drawable.pause2,
                R.drawable.bomb
        };
        gameView.start(bitmapIds);
        gameView.setIListenerGameView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gameView != null) {
            gameView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (gameView != null) {
            gameView.destroy();
        }
        gameView = null;
    }


    PopupDrawScore main_popup;

    private void btn_popup(View view) {
        main_popup = new PopupDrawScore(this, gameView);
        main_popup.showAtLocation(view, Gravity.CENTER, 0, 0);

    }

    @Override
    public void onDrawScoreDialog(String operation, String allScore) {
        btn_popup(gameView);
        main_popup.setBtnStartGame_FractionText(operation, allScore);
    }
}