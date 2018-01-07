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
package com.qing.codeart.ability.permission;

import android.app.Activity;

import com.qing.mvpart.mvp.IModel;
import com.qing.mvpart.mvp.IPresenter;
import com.qing.mvpart.mvp.IView;
import com.tbruyelle.rxpermissions2.RxPermissions;


/**
 * Permission 测试Contract类
 * * Created by QING on 2018/1/6.
 */
public interface PermissionContract {
    interface View extends IView {
        Activity getActivity();

        //申请权限
        RxPermissions getRxPermissions();
    }

    interface Presenter extends IPresenter {
        void requestPersission();
    }

    interface Model extends IModel {

    }
}
