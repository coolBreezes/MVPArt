package com.qing.codeart.ability.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;

import com.qing.codeart.R;
import com.qing.codeart.publico.base.BaseActivity;
import com.qing.mvpart.mvp.IPresenter;
import com.qing.mvpart.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 6.0 运行时权限
 * Created by QING on 2018/1/6.
 * <p>
 * v1.0 以打开摄像机为例
 */

public class PermissionActivity extends BaseActivity {

    public static final int HTTP_200 = 200;
    public static final int CAMERA = 0x01;

    @BindView(R.id.bt_camera)
    Button btCamera;

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

    }

    @Override
    public void setListener() {

    }

    @Override
    public void processLogic() {

    }


    @OnClick(R.id.bt_camera)
    public void onClick() {

        /**
         * 1.声明权限 （targetSdk 要为23或以上）
         *   忘了声明权限
         */
        //网络访问不是危险权限

        //test1
        requestPermission(Manifest.permission.CAMERA, CAMERA);
    }

    /**
     * 2.检查是否有权限 （因为考虑使用第三方jar包，就不封装工具类了）
     */
    public boolean isGranted(String permission) {
        return !isMarshmallow() || isGranted_(permission);
    }

    /**
     * 判断是否为6.0以上
     */
    public boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public boolean isGranted_(String permission) {
        int checkSelfPermission = ActivityCompat.checkSelfPermission(mContext, permission);
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 3.申请使用权限
     *
     * @param permission
     * @param requestCode
     */
    public void requestPermission(String permission, int requestCode) {

        if (isGranted(permission)) {
            Log.d("TAG", "requestPermission 获取到权限");
            ToastUtil.showS("已获取到权限");
//            String jpgPath = getCacheDir() + "test.jpg";
//            takePhotoByPath(jpgPath, 2);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mContext, permission)) {
                //test2 小米6s,禁止权限后，没有弹出相关提示，只能自己设置
                Log.d("TAG", "shouldShowRequestPermissionRationale ");
                ToastUtil.showS("您没有授权访问摄像机的权限，请在设置中打开授权");
            } else {
                ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);
            }
        }
    }

    /**
     * 4.处理授权回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "onRequestPermissionsResult 获取到权限");
                ToastUtil.showS("获取到权限");
                // TODO: 2018/1/6 读写sd卡权限？
//                String jpgPath = getCacheDir() + "test.jpg";
//                takePhotoByPath(jpgPath, 2);
            } else {
                ToastUtil.showS("您没有授权访问摄像机的权限，请在设置中打开授权");
            }
            // TODO: 2018/1/6  think 为什么这么写
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 5 Get 方式发起网络请求
     * ref : http://blog.csdn.net/zuolongsnail/article/details/6373051
     *
     * @param path
     */
    public void requestByGet(String path) {

        try {
            //1.创建url
            URL url = new URL(path);
            //2.打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            //3.设置连接超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //4.开始连接
            urlConn.connect();
            //5.判断请求是否成功
            if (urlConn.getResponseCode() == HTTP_200) {
                //6.获取返回数据
//                byte[] data = readStream()
//                urlConn.getInputStream();
                // TODO: 2018/1/6 ...
                Log.i(TAG, "Get方式请求数据成功");
            } else {
                Log.i(TAG, "Get方式请求数据失败");
            }
            //7.关闭连接
            urlConn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拍照,返回拍照文件的绝对路径
     */
    private String takePhotoByPath(String filePath, int requestCode) {
        File file = new File(filePath);
        startActivityForResult(getTakePhotoIntent(file), requestCode);
        return file.getPath();
    }

    private Intent getTakePhotoIntent(File file) {
        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }
}
