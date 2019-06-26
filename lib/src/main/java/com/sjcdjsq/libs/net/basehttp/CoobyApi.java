package com.sjcdjsq.libs.net.basehttp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.alibaba.fastjson.annotation.JSONField;
import com.sjcdjsq.libs.data.PublicSp;
import com.sjcdjsq.libs.net.listeners.OnHttpListener;
import com.sjcdjsq.libs.utils.Timeutils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Karry on 2017/2/22 0022.
 */

public abstract class CoobyApi extends BaseApi {
    private String timezone = "0";
    private String channelName;
    private String appName;
    private String brandName;
    private String user_id;

    public CoobyApi(OnHttpListener onHttpListener, Object rxCyActivity) {
        super(onHttpListener, rxCyActivity);
        timezone = Timeutils.getTimezone();
        appName = PublicSp.getAppName();
        brandName = PublicSp.getBrandName();
        channelName = PublicSp.getChannelName();
    }

    @Override
    public void punt() {
        try {
            Context context = null;
            if (getRxCyActivity() instanceof Fragment) {
                context = ((Fragment) getRxCyActivity()).getActivity().getBaseContext();
            } else {
                context = (Context) getRxCyActivity();
            }
            Intent intent = new Intent();
            intent.setAction("com.cooby.news.ui.mine.LoginActivity");
            intent.putExtra("punt", 1);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JSONField(serialize = false)
    protected Map<String, Object> filterMap(Map map) {
        map.put("appName", appName);
        map.put("brandName", brandName);
        map.put("channelName", channelName);
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            if (entry.getValue() == null) {
                it.remove();
            }
        }
        return map;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
