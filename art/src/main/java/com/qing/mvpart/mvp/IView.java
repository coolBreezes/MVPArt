package com.qing.mvpart.mvp;

/**
 * View层 接口
 * Created by QING on 2017/12/13.
 */
public interface IView {

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 显示错误
     */
    void showError(int code, String msg);

}