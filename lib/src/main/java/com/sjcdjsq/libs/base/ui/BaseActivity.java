package com.sjcdjsq.libs.base.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lib.cooby.R;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.comm.util.AdError;
import com.sjcdjsq.libs.data.PublicSp;
import com.sjcdjsq.libs.utils.ActivityManager;
import com.sjcdjsq.libs.utils.BannerUtil;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import butterknife.ButterKnife;

import static com.sjcdjsq.libs.data.Constants.IS_OPENAD;

/**
 * Created by Karry on 2017/2/20 0020.
 */

public abstract class BaseActivity extends RxCyActivity {
    protected Activity mContext;
    protected InputMethodManager inputMethodManager;
    private ImageView ivCloseBanner;
    private UnifiedBannerView bv;
    protected UnifiedInterstitialAD interstitialAD;
    private FrameLayout bannerContainer;
    private TextView tvCloseBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        Window window = getWindow();
//        window.addFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mContext = this;
        beforeCreate();
        setContentView(getLayoutId());
        setContentViewAfter(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        initToolbar();
        initView();
        afterCreate();
    }

    protected boolean isSwipeBack() {
        return true;
    }

    protected abstract void afterCreate();

    protected void beforeCreate() {
    }

    protected void initView() {
        bannerContainer = (FrameLayout) findViewById(R.id.bannerContainer);
        tvCloseBanner = (TextView) findViewById(R.id.tv_closeBanner);
        ivCloseBanner = (ImageView) findViewById(R.id.iv_closeBanner);
        openAd();
    }

    public void openAd() {
        String isOpenAd = PublicSp.getIsOpenAd();
        if (!IS_OPENAD.equals(isOpenAd)) {
            return;
        }
        if (tvCloseBanner != null) {
            interstitialAD = BannerUtil.showAD(mContext, interstitialAD, new UnifiedInterstitialADListener() {
                @Override
                public void onADReceive() {
                    Log.i("AD_DEMO", "InterstitialonADReceive");
                    interstitialAD.show();
                }

                @Override
                public void onNoAD(AdError error) {
                    String msg = String.format(Locale.getDefault(), "onNoAD, error code: %d, error msg: %s",
                            error.getErrorCode(), error.getErrorMsg());
                    Log.i("AD_DEMO", msg);
                }

                @Override
                public void onADOpened() {
                    Log.i("AD_DEMO", "InterstitialonADOpened");
                }

                @Override
                public void onADExposure() {
                    Log.i("AD_DEMO", "InterstitialonADExposure");
                }

                @Override
                public void onADClicked() {
                    Log.i("AD_DEMO", "InterstitialonADClicked");
                }

                @Override
                public void onADLeftApplication() {
                    Log.i("AD_DEMO", "InterstitialonADLeftApplication");
                }

                @Override
                public void onADClosed() {
                    Log.i("AD_DEMO", "InterstitialonADClosed");
                }
            });
            tvCloseBanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bannerContainer.removeAllViews();
                    ivCloseBanner.setVisibility(View.GONE);
                    tvCloseBanner.setVisibility(View.GONE);
                    if (bv != null) {
                        bv.destroy();
                        bv = null;
                    }
                }
            });
        }
        if (ivCloseBanner != null) {
            ivCloseBanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvCloseBanner.setVisibility(View.VISIBLE);
                    ivCloseBanner.setVisibility(View.GONE);
                }
            });
            bv = BannerUtil.getBanner(mContext, bannerContainer, ivCloseBanner);
            bv.loadAD();
        }
    }

    protected void initToolbar() {
    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
        EventBus.getDefault().unregister(this);
        if (interstitialAD != null) {
            interstitialAD.destroy();
        }
        if (bv != null) {
            bv.destroy();
            bv = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String event) {
    }

    public void setContentViewAfter(Bundle savedInstanceState) {

    }

    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
        MobclickAgent.onPause(this);
    }
}
