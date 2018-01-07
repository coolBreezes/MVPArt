package com.qing.mvpart.util;

import android.content.Context;
import android.support.compat.BuildConfig;
import android.text.TextUtils;
import android.widget.Toast;

import com.qing.mvpart.BaseApp;


/**
 * Toast 工具类
 * Created by QING on 2017/12/25.
 * <p>
 * v1.0
 * -- 1.防止内容重复弹出
 * 2.todo debug 模式
 * 3.resId 情况 2017-12-26 16:07:50
 *
 * 前提 项目的Application要继承BaseApp，否则会空指针报错
 */
public class ToastUtil {

    private static Toast toast = null;
    private static boolean isDebug = BuildConfig.DEBUG; // 调试模式标志位

    /**
     * API
     */

    public static void showS(CharSequence msg) {
        showS(BaseApp.getInstance(), msg);
    }

    public static void showS(int resId) {
        showS(BaseApp.getInstance(), resId);
    }

    /**
     * 显示toast 短暂
     */
    public static void showS(Context context, CharSequence msg) {
        makeText(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showS(Context context, int resId) {
        makeText(context, resId, Toast.LENGTH_SHORT);
    }

    public static void showL(CharSequence msg) {
        showL(BaseApp.getInstance(), msg);
    }

    public static void showL(int resId) {
        showL(BaseApp.getInstance(), resId);
    }

    /**
     * 显示toast 较长
     */
    public static void showL(Context context, CharSequence msg) {
        makeText(context, msg, Toast.LENGTH_LONG);
    }

    public static void showL(Context context, int resId) {
        makeText(context, resId, Toast.LENGTH_LONG);
    }


    private static void makeText(Context context, CharSequence msg,
                                 int duration) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);
        } else {
//            toast.cancel();
//            toast = Toast.makeText(context, msg, duration);
            toast.setText(msg);
            toast.setDuration(duration);
        }
        toast.show();
    }

    private static void makeText(Context context, int resId, int duration) {
        if (resId <= 0) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, resId, duration);
        } else {
//            toast.cancel();
//            toast = Toast.makeText(context, msg, duration);
            toast.setText(resId);
            toast.setDuration(duration);
        }
        toast.show();
    }
}
