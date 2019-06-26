package com.sjcdjsq.libs.net.basehttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.sjcdjsq.libs.net.exceptions.HttpException;
import com.sjcdjsq.libs.net.listeners.OnHttpListener;
import com.sjcdjsq.libs.net.subscribes.HttpSubscriber;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

import static com.sjcdjsq.libs.net.RxApp.BASE_URL;


/**
 * Created by Karry on 2017/2/15 0015.
 */

public abstract class BaseApi<T> implements Function<BaseResultBean<T>, T> {
    @JSONField(serialize = false)
    private OnHttpListener mOnHttpListener;
    @JSONField(serialize = false)
    private Object mRxCyActivity;
    @JSONField(serialize = false)
    private String baseUrl = BASE_URL;
    @JSONField(serialize = false)
    private boolean isCache;
    @JSONField(serialize = false)
    private String method;
    @JSONField(serialize = false)
    private HttpSubscriber mHttpSubscriber;
    @JSONField(serialize = false)
    private boolean isShowProgress;
    @JSONField(serialize = false)
    private boolean isOutSideTouch = true;

    public BaseApi(OnHttpListener onHttpListener, Object rxCyActivity) {
        setOnHttpListener(onHttpListener);
        setRxCyActivity(rxCyActivity);
    }


    @Override
    public T apply(BaseResultBean<T> tBaseResultBean) throws Exception {
        int code = tBaseResultBean.getCode();
        String msg = tBaseResultBean.getMsg();
        String errcode = tBaseResultBean.getErrcode();

        //版本升级需要改版，字段不同判断不同
        if (errcode != null) {
//            错误码 000-成功 400-失败 998-网络异常,请稍后再试 999-非法请求
            if ("000".equals(errcode)) {
                return tBaseResultBean.getData();
            } else {
                throw new HttpException(code, msg);
            }
        }


        if (code != 1) {
            if (code == -402 || code == -401)
                punt();
            throw new HttpException(code, msg);
        }
        return tBaseResultBean.getData();
    }

    /**
     * 踢下线
     */
    @JSONField(serialize = false)
    public void punt() {

    }

    @JSONField(serialize = false)
    public abstract Flowable getFlowable(Retrofit retrofit);


    @JSONField(serialize = false)
    public String getBaseUrl() {
        return baseUrl;
    }

    @JSONField(serialize = false)
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @JSONField(serialize = false)
    protected Map<String, Object> object2Map(Object object) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> map = new IdentityHashMap<String, Object>();
        for (Map.Entry<String, Object> entry : entrySet) {
            if (entry.getValue() != null) {
                if (entry.getValue() instanceof JSONArray) {
                    List<String> list = (List<String>) entry.getValue();
                    if (list != null && list.size() > 0)
                        for (int i = 0; i < list.size(); i++) {
                            map.put(new String(entry.getKey()), list.get(i));
                        }
                    else
                        map.put(entry.getKey(), "");
                } else {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return map;
    }


    @JSONField(serialize = false)
    public boolean isCache() {
        return isCache;
    }

    @JSONField(serialize = false)
    public void setCache(boolean cache) {
        isCache = cache;
    }

    @JSONField(serialize = false)
    public String getMethod() {
        if (method == null)
            return getClass().getSimpleName();
        return method;
    }

    @JSONField(serialize = false)
    public void setMethod(String method) {
        this.method = method;
    }

    @JSONField(serialize = false)
    public void setHttpSubscriber(HttpSubscriber httpSubscriber) {
        mHttpSubscriber = httpSubscriber;
    }

    @JSONField(serialize = false)
    public OnHttpListener getOnHttpListener() {
        return mOnHttpListener;
    }

    @JSONField(serialize = false)
    public void setOnHttpListener(OnHttpListener onHttpListener) {
        mOnHttpListener = onHttpListener;
    }

    @JSONField(serialize = false)
    public HttpSubscriber getHttpSubscriber() {
        return mHttpSubscriber;
    }

    @JSONField(serialize = false)
    public Object getRxCyActivity() {
        return mRxCyActivity;
    }

    @JSONField(serialize = false)
    public void setRxCyActivity(Object rxCyActivity) {
        mRxCyActivity = rxCyActivity;
    }

    @JSONField(serialize = false)
    public boolean isShowProgress() {
        return isShowProgress;
    }


    @JSONField(serialize = false)
    public void setShowProgress(boolean showProgress) {
        isShowProgress = showProgress;
    }

    @JSONField(serialize = false)
    public void disposeSb() {
        if (mHttpSubscriber == null)
            return;
        mHttpSubscriber.dispose();
        if (mOnHttpListener == null)
            return;
        mOnHttpListener.onCancel();
    }

    @JSONField(serialize = false)
    public boolean isOutSideTouch() {
        return isOutSideTouch;
    }

    @JSONField(serialize = false)
    public void setOutSideTouch(boolean outSideTouch) {
        isOutSideTouch = outSideTouch;
    }
}
