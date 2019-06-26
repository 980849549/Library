package com.sjcdjsq.libs.base.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
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

public abstract class BaseFragment extends RxCyFragment {
    protected Activity mContext;
    protected InputMethodManager inputMethodManager;
    private ImageView ivCloseBanner;
    private UnifiedBannerView bv;
    protected UnifiedInterstitialAD interstitialAD;
    private FrameLayout bannerContainer;
    private TextView tvCloseBanner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initView(view);
        setContentViewAfter(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        initToolbar(view);
        afterCreate();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        LayoutInflater result = getActivity().getLayoutInflater();
        return result;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    protected boolean isSwipeBack() {
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mContext = getActivity();
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate();

    protected void initView(View view) {
        bannerContainer = (FrameLayout) view.findViewById(R.id.bannerContainer);
        tvCloseBanner = (TextView) view.findViewById(R.id.tv_closeBanner);
        ivCloseBanner = (ImageView) view.findViewById(R.id.iv_closeBanner);
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
            bv = BannerUtil.getBanner(getActivity(), bannerContainer, ivCloseBanner);
            bv.loadAD();
        }
    }

    protected void initToolbar(View view) {
    }

    public void setContentViewAfter(Bundle savedInstanceState) {

    }

    protected void hideSoftKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void showKeyboard() {
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        hideSoftKeyboard();
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

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        MobclickAgent.onResume(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
        MobclickAgent.onPause(getActivity());
    }
}
