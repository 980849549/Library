package com.sjcdjsq.libs.utils;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.comm.util.AdError;
import com.sjcdjsq.libs.data.Constants;

import java.util.Locale;

/**
 * /**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　 ████━████     ┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　 　 ┗━━━┓
 * 　　　　　　　　　┃ 神兽保佑　　 ┣┓
 * 　　　　　　　　　┃ 代码无BUG   ┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * 作者:Karry date:2018/12/28 11
 * 描述:
 */
public class BannerUtil {
    public static UnifiedBannerView getBanner(final Activity mContent, FrameLayout bannerContainer, final ImageView ivCloseBanner) {
        String posId = Constants.BannerPosID;
        UnifiedBannerView bv = new UnifiedBannerView(mContent, Constants.APPID, posId, new UnifiedBannerADListener() {
            @Override
            public void onNoAD(AdError adError) {
                String msg = String.format(Locale.getDefault(), "onNoAD, error code: %d, error msg: %s",
                        adError.getErrorCode(), adError.getErrorMsg());
                Log.i("AD_DEMO", "BanneronNoAD" + msg);
            }

            @Override
            public void onADReceive() {
                Log.i("AD_DEMO", "BanneronADReceive");
            }

            @Override
            public void onADExposure() {
                Log.i("AD_DEMO", "BanneronADExposure");
            }

            @Override
            public void onADClosed() {
                Log.i("AD_DEMO", "BanneronADClosed");
            }

            @Override
            public void onADClicked() {
                Log.i("AD_DEMO", "BanneronADClicked");
            }

            @Override
            public void onADLeftApplication() {
                Log.i("AD_DEMO", "BanneronADLeftApplication");
            }

            @Override
            public void onADOpenOverlay() {
                Log.i("AD_DEMO", "BanneronADOpenOverlay");
            }

            @Override
            public void onADCloseOverlay() {
                Log.i("AD_DEMO", "BanneronADCloseOverlay");
            }
        });
        bannerContainer.addView(bv);
        return bv;
    }

    public static UnifiedInterstitialAD showAD(Activity mContent, UnifiedInterstitialAD iad, UnifiedInterstitialADListener listener) {
        if (iad == null) {
            iad = new UnifiedInterstitialAD(mContent, Constants.APPID, Constants.InterteristalPosID, listener);
            iad.loadAD();
        } else {
            iad.show();
        }
        return iad;
    }
}
