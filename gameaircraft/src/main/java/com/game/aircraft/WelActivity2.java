package com.game.aircraft;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;
import com.sjcdjsq.libs.base.ui.BaseActivity;
import com.sjcdjsq.libs.data.Constants;
import com.sjcdjsq.libs.data.PublicSp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.sjcdjsq.libs.data.Constants.IS_OPENAD;


public class WelActivity2 extends BaseActivity implements SplashADListener {

    @BindView(R2.id.iv_money)
    ImageView ivMoney;

    @BindView(R2.id.rl_start)
    RelativeLayout rlStart;

    @BindView(R2.id.splash_container)
    FrameLayout container;

    @BindView(R2.id.skip_button)
    TextView mSkipButton;
    @BindView(R2.id.skip_view1)
    TextView skipView1;

    @BindView(R2.id.skip_view)
    TextView skipView;
    @BindView(R2.id.iv_cancle)
    ImageView ivCancle;
    @BindView(R2.id.rl_skip_button)
    RelativeLayout rlSkipButton;

    private long countdownDuration = 15000;// 倒计时时长
    private static final String SKIP_TEXT = "倒计时%d秒 ";
    private CountDownTimer mCountDownTimer;

    @Override
    protected void afterCreate() {
        String isOpenAd = PublicSp.getIsOpenAd();
        if (IS_OPENAD.equals(isOpenAd)) {
            if (Build.VERSION.SDK_INT >= 23) {
                checkAndRequestPermission();
            } else {
                // 如果是Android6.0以下的机器，默认在安装时获得了所有权限，可以直接调用SDK
                fetchSplashAD(this, container, skipView1, Constants.APPID, Constants.SplashPosID2, this, 0);
            }
        } else {
            jumpWhenCanClick();
        }
    }


    /**
     * 拉取开屏广告，开屏广告的构造方法有3种，详细说明请参考开发者文档。
     *
     * @param activity      展示广告的activity
     * @param adContainer   展示广告的大容器
     * @param skipContainer 自定义的跳过按钮：传入该view给SDK后，SDK会自动给它绑定点击跳过事件。SkipView的样式可以由开发者自由定制，其尺寸限制请参考activity_splash.xml或者接入文档中的说明。
     * @param appId         应用ID
     * @param posId         广告位ID
     * @param adListener    广告状态监听器
     * @param fetchDelay    拉取广告的超时时长：取值范围[3000, 5000]，设为0表示使用广点通SDK默认的超时时长。
     */
    private void fetchSplashAD(Activity activity, ViewGroup adContainer, View skipContainer,
                               String appId, String posId, SplashADListener adListener, int fetchDelay) {
        new SplashAD(activity, adContainer, skipContainer, appId, posId, adListener, fetchDelay);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        // 权限都已经有了，那么直接调用SDK
        if (lackedPermission.size() == 0) {
            fetchSplashAD(this, container, skipView1, Constants.APPID, Constants.SplashPosID2, this, 0);
        } else {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cdb_welcome_layout;
    }

    private void redirectToHandler() {
//        mSkipButton.setTimeMillis(countdownDuration);// 把倒计时时间改长一点。
//        mSkipButton.setCountdownProgressListener(1, progressListener);
//        mSkipButton.start();
        mCountDownTimer = new CountDownTimer(countdownDuration, 1 * 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mSkipButton.setText(String.format(SKIP_TEXT, millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                if (mCountDownTimer != null) {
                    jumpWhenCanClick();
                }
            }
        };
        mCountDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    /**
     * 当设置开屏可点击时，需要等待跳转页面关闭后，再切换至您的主窗口。故此时需要增加canJumpImmediately判断。
     * 另外，点击开屏还需要在onResume中调用jumpWhenCanClick接口。
     */

    private void jumpWhenCanClick() {
        startActivity(new Intent(this, GameActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onADDismissed() {
        Log.i("AD_DEMO", "SplashADDismissed");
    }

    @Override
    public void onNoAD(AdError error) {
        Log.i("AD_DEMO", String.format("LoadSplashADFail, eCode=%d, errorMsg=%s", error.getErrorCode(), error.getErrorMsg()));
        countdownDuration = 1000;
        redirectToHandler();
    }

    @Override
    public void onADPresent() {
        Log.i("AD_DEMO", "SplashADPresent");
        ivMoney.setVisibility(View.INVISIBLE); // 广告展示后一定要把预设的开屏图片隐藏起来
        rlSkipButton.setVisibility(View.VISIBLE);
//        skipView.setVisibility(View.VISIBLE);
        skipView1.setVisibility(View.VISIBLE);
        redirectToHandler();
    }

    @Override
    public void onADClicked() {
        Log.i("AD_DEMO", "SplashADClicked");
    }

    @Override
    public void onADTick(long millisUntilFinished) {
        Log.i("AD_DEMO", "SplashADTick " + millisUntilFinished + "ms");
    }

    @Override
    public void onADExposure() {
        Log.i("AD_DEMO", "SplashADExposure");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1024 && hasAllPermissionsGranted(grantResults)) {
            fetchSplashAD(this, container, skipView1, Constants.APPID, Constants.SplashPosID2, this, 0);
        } else {
            // 如果用户没有授权，那么应该说明意图，引导用户去设置里面授权。
            Toast.makeText(this, "应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            finish();
        }
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


    @OnClick({R2.id.skip_view, R2.id.rl_skip_button})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.skip_view || i == R.id.rl_skip_button) {
            jumpWhenCanClick();

        }
    }
}
