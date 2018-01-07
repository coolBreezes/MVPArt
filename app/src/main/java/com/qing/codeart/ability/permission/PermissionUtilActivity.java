package com.qing.codeart.ability.permission;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.qing.codeart.R;
import com.qing.codeart.publico.base.BaseActivity;
import com.qing.mvpart.util.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 6.0 运行时权限
 * 基于RxPermissions封装MVP
 * Created by QING on 2018/1/6.
 * <p>
 * v1.0 以打开摄像机为例
 * <p>
 * refer http://blog.csdn.net/joedan0104/article/details/78713433
 */

public class PermissionUtilActivity extends BaseActivity<PermissionContract.Presenter> implements PermissionContract.View {

    public static final int HTTP_200 = 200;
    public static final int CAMERA = 0x01;

    @BindView(R.id.bt_camera)
    Button btCamera;

    RxPermissions rxPermissions;


    @Override
    public int getLayoutId() {
        return R.layout.activity_permission;
    }

    @Override
    public PermissionContract.Presenter createPresenter() {
        return new PermissionPresenter(this);
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        rxPermissions = new RxPermissions(mContext);
        rxPermissions.setLogging(true);     //打开打印日志使能开关
    }

    @Override
    public void setListener() {

    }

    @Override
    public void processLogic() {

        Logger.d("test");
        Logger.e("test");
        Logger.i("test");
        Logger.d("test");
    }


    @OnClick(R.id.bt_camera)
    public void onClick() {

        Log.d(TAG,"HELLO");
        Logger.d("test");
        Logger.e("test");
        Logger.i("test");
        Logger.d("test");
        getP().requestPersission();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

        ToastUtil.showS(message);
    }

    @Override
    public void showError(int code, String msg) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public RxPermissions getRxPermissions() {
        // 也可以放到这里初始化rxPermission
        return rxPermissions;
    }
}
