package com.qing.mvpart.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qing.mvpart.mvp.IPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 基类
 * Created by QING on 2017/12/13.
 */
public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment<P> {

    protected final String TAG = this.getClass().getSimpleName();

    protected View rootView;
    protected Context mContext;
    protected LayoutInflater mInflater;
    private P mPresenter;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        if (rootView == null && getLayoutId() > 0) {
            rootView = inflater.inflate(getLayoutId(), null);
            mUnbinder = ButterKnife.bind(this, rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (null != viewGroup) {
                viewGroup.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void onDestroyView() {
        super.onDestroyView();
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
    }

    protected P getP() {
        return mPresenter;
    }

}
