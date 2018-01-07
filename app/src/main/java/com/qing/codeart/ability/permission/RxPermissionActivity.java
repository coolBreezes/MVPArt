package com.qing.codeart.ability.permission;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.qing.codeart.R;
import com.qing.codeart.publico.base.BaseActivity;
import com.qing.mvpart.mvp.IPresenter;
import com.qing.mvpart.util.ToastUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 6.0 运行时权限
 * 使用RxPermissions
 * Created by QING on 2018/1/6.
 * <p>
 * v1.0 以打开摄像机为例
 * <p>
 * refer http://blog.csdn.net/joedan0104/article/details/78713433
 */

public class RxPermissionActivity extends BaseActivity {

    public static final int HTTP_200 = 200;
    public static final int CAMERA = 0x01;

    @BindView(R.id.bt_camera)
    Button btCamera;

    RxPermissions rxPermissions;

//    String path = "https://reg.163.com/logins.jsp?id=helloworld&pwd=android";

    @Override
    public int getLayoutId() {
        return R.layout.activity_permission;
    }

    @Override
    public IPresenter createPresenter() {
        return null;
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

    }


    @OnClick(R.id.bt_camera)
    public void onClick() {

        //rx1 -> 2  Action1 -> Consumer
        rxPermissions.requestEach(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
//                            releaseCamera();
//                            camera = Camera.open(0);
//                            try {
//                                camera.setPreviewDisplay(surfaceView.getHolder());
//                                camera.startPreview();
//                            } catch (IOException e) {
//                                Log.e(TAG, "Error while trying to display the camera preview", e);
//                            }
                            ToastUtil.showS("已获取到权限");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                            Toast.makeText(mContext,
                                    "Denied permission without ask never again",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                            Toast.makeText(mContext,
                                    "Permission denied, can't enable the camera",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
