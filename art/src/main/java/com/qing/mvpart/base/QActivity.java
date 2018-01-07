package com.qing.mvpart.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.qing.mvpart.BaseApp;
import com.qing.mvpart.mvp.IPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity 基类
 * 封装业务无关的API
 * Created by QING on 2017/12/13.
 */
public abstract class QActivity<P extends IPresenter> extends AppCompatActivity implements IActivity<P> {

    public static final String BASE_INTENT = "BASE_INTENT";
    protected final String TAG = this.getClass().getSimpleName();

    private P mPresenter;
    protected Activity mContext;
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        BaseApp.addActivity(this);

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            mUnbinder = ButterKnife.bind(this);
        }
        initData(savedInstanceState);
    }

    private void initData(Bundle savedInstanceState) {
        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册 Eventbus
        }
        mPresenter = createPresenter();
        initView(savedInstanceState); //初始化title
        setListener();
        processLogic();
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);//解除注册 Eventbus
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        mContext = null;
        BaseApp.removeActivity(this);
    }

    protected P getP() {
        return mPresenter;
    }


    /***************************************************
     *    UI相关
     ****************************************************/

    /**
     * 添加fragment，静态使用
     */
    protected void addFragment(int resId, Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
//                    .add(resId, fragment)
                    .replace(resId, fragment, fragment.getClass().getSimpleName())
//                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    /**
     * 移除fragment
     */
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    /**
     * 页面跳转
     */
    public void startActivity(Class<? extends Activity> clazz) {
        startActivity(clazz, null);
    }

    public void startActivity(Class<? extends Activity> clazz, Bundle bundle) {
        startActivity(clazz, bundle, false);
    }

    public void startActivity(Class<? extends Activity> clazz, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(mContext, clazz);
        if (bundle != null) {
            intent.putExtra(BASE_INTENT, bundle);
        }
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        /*
            返回键返回事件的处理
            如果FragmentStack中只有1个fragment 关闭当前activity
            如果FragmentStack中还有>1数量fragment则可以removeFragment()将fragment出栈 此部分交给子类实现
        */
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
