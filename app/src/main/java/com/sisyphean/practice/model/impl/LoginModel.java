package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.model.ILoginModel;
import com.sisyphean.practice.utils.StorageUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements ILoginModel {

    public Observable<ResponseBean<UserBean>> userLogin(String email, String password) {
        return doRequest().userLogin(email, password)
                .doOnNext(new Consumer<ResponseBean<UserBean>>() {
                    @Override
                    public void accept(ResponseBean<UserBean> userBeanResponseBean) throws Exception {
                        StorageUtil.put("test.txt", userBeanResponseBean);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
