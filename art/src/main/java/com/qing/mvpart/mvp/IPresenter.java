package com.qing.mvpart.mvp;

/**
 * Presenter层 接口
 * Created by QING on 2017/12/13.
 */
public interface IPresenter {

    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     * 销毁操作
     */
    void onDestroy();

}