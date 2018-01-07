package com.qing.codeart.ability.permission;

import com.qing.mvpart.mvp.BasePresenter;
import com.qing.mvpart.util.PermissionUtil;

import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * Permission 测试Presenter类
 * * Created by QING on 2018/1/6.
 */
public class PermissionPresenter extends BasePresenter<PermissionContract.Model, PermissionContract.View>
        implements PermissionContract.Presenter {
    private RxErrorHandler mErrorHandler;


    public PermissionPresenter(PermissionContract.View rootView) {
        super(rootView);
    }

    public void requestPersission() {
        //请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.launchCamera(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //request permission success, do something.
                getV().showMessage("Request permissions succeed");
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                getV().showMessage("Request permissions failure");
                // : 2018/1/7 弹窗引导用户去授权
                PermissionUtil.showSettingDialog(getV().getActivity());
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                getV().showMessage("Need to go to the settings");
                // : 2018/1/7 弹窗引导用户去授权
                PermissionUtil.showSettingDialog(getV().getActivity());
            }
        }, getV().getRxPermissions());

    }

}
