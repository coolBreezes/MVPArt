/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qing.mvpart.base;

import android.os.Bundle;

import com.qing.mvpart.mvp.IPresenter;


/**
 * IFragment 接口
 * Created by QING on 2017/12/13.
 */
public interface IFragment<P extends IPresenter> {

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
