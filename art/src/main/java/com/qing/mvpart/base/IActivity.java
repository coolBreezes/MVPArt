package com.qing.mvpart.base;

import android.os.Bundle;

import com.qing.mvpart.mvp.IPresenter;


/**
 * IActivity 接口
 * Created by QING on 2017/12/13.
 */
public interface IActivity<P extends IPresenter> {

    /**
     * 加载页面layout的id
     */
    int getLayoutId();

    /**
     * 用于创建Presenter(由子类实现)
     */
    P createPresenter();

    /**
     * 初始化页面布局
     */
    void initView(Bundle savedInstanceState);

    /**
     * 设置各种事件的监听器
     */
    void setListener();

    /**
     * 业务逻辑处理，主要与后端交互
     */
    void processLogic();

    /**
     * 是否使用 EventBus，默认不使用
     */
    boolean useEventBus();

}
