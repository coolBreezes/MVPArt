package com.qing.mvpart.mvp;

/**
 * Presenter层 基类
 * Created by QING on 2017/12/13.
 */
public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter {

    protected final String TAG = this.getClass().getSimpleName();

    private M mModel;
    private V mView;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     */
    public BasePresenter(M model, V rootView) {
        this.mModel = model;
        this.mView = rootView;
        onStart();
    }

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     */
    public BasePresenter(V rootView) {
        this.mView = rootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }


    /**
     * 执行初始化操作
     */
    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.onDestroy();
            mModel = null;
        }
        this.mView = null;
    }

    protected M getM() {
        return mModel;
    }

    protected V getV() {
        return mView;
    }

}