package com.qing.mvpart.util;

import android.os.Environment;

import java.io.File;

/**
 * SdCard 工具类
 * Created by QING on 2017/12/25.
 */
public class SdCardUtil {

    /**
     * is sd card available.
     *
     * @return true if available
     */
    public static boolean isAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static String getSdCardPath() {
        if (isAvailable()) {
            return Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator;
        }
        return null;
    }

}