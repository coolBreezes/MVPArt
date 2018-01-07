package com.qing.mvpart;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 *  Application 基类
 * 实现业务无关的API
 * Created by QING on 2017/12/13.
 *  v1.0
 *  init Logger
 */
public class BaseApp extends Application {

    /**
     * 应用实例
     **/
    private static BaseApp instance;
    /**
     * 记录当前栈里所有activity
     */
    private static List<Activity> activities = new ArrayList<Activity>();
    /**
     * 记录需要一次性关闭的页面
     */
    private static List<Activity> activitys = new ArrayList<Activity>();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //init Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 获得实例
     */
    public static BaseApp getInstance() {
        return instance;
    }

    /**
     * 新建了一个activity
     */
    public static void addActivity(Activity activity) {
        if (activity != null) {
            activities.add(activity);
        }
    }

    /**
     * 从activity列表中删去一个activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
            activity.finish();
        }
    }

    /**
     * 给临时Activitys添加activity
     */
    public static void addTempActivity(Activity activity) {
        if (activity != null) {
            activitys.add(activity);
        }
    }

    public static void finishTempActivity(Activity activity) {
        if (activity != null) {
            activitys.remove(activity);
            activity.finish();
        }
    }

    /**
     * 退出指定的Activitys集合（一次性的activity）
     */
    public static void exitActivitys() {
        for (Activity activity : activitys) {
            if (activity != null) {
                activitys.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public static void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activities.remove(activity);
                activity.finish();
            }
        }
        System.exit(0);
    }

    /**
     * 重启当前应用
     */
    public static void restart() {
        Intent intent = instance.getPackageManager().getLaunchIntentForPackage(instance.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        instance.startActivity(intent);
    }

}
