package com.qing.mvpart.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.qing.mvpart.App;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SharedPreferences工具类 用于快速读写数据
 * Created by yuanpeng.deng on 2017/9/26.
 */

public class SpUtil {
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    private String SP_NAME = "config";
    private static SpUtil instance;

    private final static String ISFIRSTUSE = "is_first_use";
    private static final String RING = "ring";
    private static final String MOVE = "move";
    private static final String VIBRATION = "vibration";
    private static final String NOTIFICATION_SOUND = "notificationSound";
    private static final String USERNAME = "username";
    private static final String DEVICE_MAP = "deviceMap";
    private static final String DEVICE_NODE_MAP = "deviceNodeMap";
    private static final String HOME_WIFI_INFO = "homeWifiInfo";
    private static final String IS_DELETE_ALARM = "isDeleteAlarm";
    private static final String DEVICE_REMOT_PLAYBACK = "dev_remote_playback_";
    private static final String IS_CANCEL_ALARM = "isCancelAlarm";
    private static final String STREAM_TYPE_CHOOSE = "stream_type_choose_";

    private SpUtil(Context context) {
        this.context = context;
    }

    private SpUtil() {
    }

    public static SpUtil getInstance() {
        return getInstance(App.getInstance());
    }

    public static SpUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (SpUtil.class) {
                if (instance == null) {
                    instance = new SpUtil(context);
                }
            }

        }
        return instance;
    }

    private SharedPreferences getSP() {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp;
    }

    private SharedPreferences.Editor getEditor() {
        if (editor == null) {
            editor = getSP().edit();

        }
        return editor;
    }

    private void setValue(String key, String value) {
        getEditor().putString(key, value);
        getEditor().commit();
    }

    public void clear() {
        getEditor().clear();
        getEditor().commit();
    }

    public void remove(String key) {
        getEditor().remove(key);
        getEditor().commit();
    }

    private void setBooleanValue(String key, boolean value) {
        getEditor().putBoolean(key, value);
        getEditor().commit();
    }

    private boolean getBooleanValue(String key) {
        return getSP().getBoolean(key, false);

    }

    private void setIntValue(String key, int value) {
        getEditor().putInt(key, value);
        getEditor().commit();
    }

    private int getIntValue(String key) {
        return getSP().getInt(key, -1);
    }

    private String getValue(String key) {
        return getSP().getString(key, "");

    }

    private Long getLongValue(String key) {
        return getSP().getLong(key, -1);
    }

    private void setLongValue(String key, Long value) {
        getEditor().putLong(key, value);
        getEditor().commit();
    }

    /**
     * 序列化复杂数据为字符串
     *
     * @param object
     * @return str
     */
    public String serialize(Object object) {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        String str = "";
        try {
            objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(object);
            //str= arrayOutputStream.toString();
            //str = URLEncoder.encode(str, "UTF-8");
            str = new String(Base64.encode(
                    arrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            arrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 反序列化数据
     *
     * @param str
     * @return
     */
    public Object deSerialize(String str) {
        Object object = null;
        try {
            byte[] readStr = Base64.decode(str, Base64.DEFAULT);
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(
                    readStr);
            ObjectInputStream objectInputStream = new ObjectInputStream(arrayInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            arrayInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    public void setIsFirstUse(boolean bool) {
        setBooleanValue(SpUtil.ISFIRSTUSE, bool);
    }

    public boolean getIsFirstUse() {
        return getBooleanValue(SpUtil.ISFIRSTUSE);
    }

    public void setVibration(boolean vibration) {
        setBooleanValue(VIBRATION, vibration);
    }

    public boolean getVibration() {
        return getBooleanValue(VIBRATION);
    }

    public void setNotificationSound(boolean notificationSound) {
        setBooleanValue(NOTIFICATION_SOUND, notificationSound);
    }

    public boolean getNotificationSound() {
        return getBooleanValue(NOTIFICATION_SOUND);
    }

    public String getUserName() {
        return getValue(USERNAME);
    }

    public void setUserName(String username) {
        setValue(USERNAME, username);
    }

    //保存一个关于，设备ID，设备为键值对的MAP
    public String getDeviceMap() {
        return getValue(getUserName() + DEVICE_MAP);
    }

    public void setDeviceMap(String deviceMap) {
        setValue(getUserName() + DEVICE_MAP, deviceMap);
    }

    //保存一个关于，设备通道ID，设备为键值对的MAP
    public String getDeviceNodeMap() {
        return getValue(getUserName() + DEVICE_NODE_MAP);
    }

    public void setDeviceNodeMap(String deviceMap) {
        setValue(getUserName() + DEVICE_NODE_MAP, deviceMap);
    }

    public void setFrameRateSwitchAble(String umid, boolean able) {
        setBooleanValue("dev_rate_" + umid, able);
    }

    public boolean isFrameRateSwitchAble(String umid) {
        return getBooleanValue("dev_rate_" + umid);
    }

    public void setDualChannelAble(String umid, boolean able) {
        setBooleanValue("dev_dual_" + umid, able);
    }

    public boolean isDualChannelAble(String umid) {
        return getBooleanValue("dev_dual_" + umid);
    }

    public void saveHomeWifiInfo(String homeWifiInfo) {
        setValue(HOME_WIFI_INFO, homeWifiInfo);
    }

    public String getHomeWifiInfo() {
        return getValue(HOME_WIFI_INFO);
    }

    public void setPushButtonIsChecked(String gid, boolean autoOpen) {
        setBooleanValue(RING + gid, autoOpen);
    }

    public boolean getPushButtonIsChecked(String gid) {
        return getBooleanValue(RING + gid);
    }

    public void setMoveDetectionButtonIsChecked(String gid, boolean autoOpen) {
        setBooleanValue(MOVE + gid, autoOpen);
    }

    public boolean getMoveDetectionButtonIsChecked(String gid) {
        return getBooleanValue(MOVE + gid);
    }

    // 保存用户是否点击了“记住密码”
    public void setRemenberPass(boolean isChecked) {
        setBooleanValue("rpIsChecked", isChecked);
    }

    public boolean getRemenberPass() {
        return getBooleanValue("rpIsChecked");
    }

    // 保存用户是否点击了“自动登录”
    public void setAutoLogin(boolean isChecked) {
        setBooleanValue("alIsChecked", isChecked);
    }

    public boolean getAutoLogin() {
        return getBooleanValue("alIsChecked");
    }

    public String getLocalUserName() {
        return getValue("Username");
    }

    public void setLocalUserName(String username) {
        setValue("Username", username);
    }

    public String getPassword() {
        return getValue("password");
    }

    public void setPassword(String password) {
        setValue("password", password);
    }

    //保存用户是否点击了"注销"
    public void setIsClickedCancel(boolean isChecked) {
        setBooleanValue("isClicked", isChecked);
    }

    //获取用户是否点击了"注销"
    public boolean getIsClickedCancel() {
        return getBooleanValue("isClicked");
    }

    public void setChannelState(String umid, int channelId) {
        setIntValue("dev_" + umid, channelId);
    }

    public int getChannelState(String umid) {
        return getIntValue("dev_" + umid);
    }

    public void setDelay(int delay) {
        setIntValue("aecmDelay", delay);
    }

    public int getDelay() {
        return getIntValue("aecmDelay");
    }

    public void setIsHasDeleteAlarm(boolean isDelete) {
        setBooleanValue(IS_DELETE_ALARM, isDelete);
    }

    public boolean hasDeleteAlarm() {
        return getBooleanValue(IS_DELETE_ALARM);
    }

    public void setRemotePlaybackAble(String device_id, boolean able) {
        setBooleanValue(DEVICE_REMOT_PLAYBACK + device_id, able);
    }

    public boolean isRemotePlaybackAble(String device_id) {
        return getBooleanValue(DEVICE_REMOT_PLAYBACK + device_id);
    }

    public void setIsHasCancelAlarm(boolean isCancel) {
        setBooleanValue(IS_CANCEL_ALARM, isCancel);
    }

    public boolean hasCancelAlarm() {
        return getBooleanValue(IS_CANCEL_ALARM);
    }

    public void setDeviceStreamType(String umid, int choose) {
        setIntValue(STREAM_TYPE_CHOOSE + umid, choose);
    }

    public int getDeviceStreamType(String umid) {
        return getIntValue(STREAM_TYPE_CHOOSE + umid);
    }
}
