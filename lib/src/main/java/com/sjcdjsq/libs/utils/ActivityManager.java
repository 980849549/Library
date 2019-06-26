package com.sjcdjsq.libs.utils;

import android.app.Activity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Karry on 2017/2/20 0020.
 */

public class ActivityManager {
    private List<Activity> activityList = new LinkedList<Activity>();
    private HashMap<String, Activity> singleMap = new HashMap<>();
    private String singleKey = "singleKey";

    private static class LazyHolder {
        private static final ActivityManager INSTANCE = new ActivityManager();
    }

    private ActivityManager() {

    }

    public static final ActivityManager getInstance() {
        return ActivityManager.LazyHolder.INSTANCE;
    }


    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    public void exceptAllFinish(Activity aty) {
        for (Activity activity : activityList) {
            if (aty != activity)
                activity.finish();
        }
    }

    public void finishAll() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    public void setSingleActivity(Activity activity) {
        singleMap.put(singleKey, activity);
    }

    public Activity getSingleActivity() {
        return singleMap.get(singleKey);
    }
}
