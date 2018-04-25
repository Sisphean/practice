package com.sisyphean.practice.model.impl;

import android.util.Log;

import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.model.ILoginModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements ILoginModel {

    public Observable<ResponseBean<UserBean>> userLogin(String username, String password) {
        return doRequest().userLogin(username, password)
                .doOnNext(new Consumer<ResponseBean<UserBean>>() {
                    @Override
                    public void accept(ResponseBean<UserBean> response) throws Exception {
                        Log.d("xxx", "ThreadName: " + Thread.currentThread() + " | response: " + response.toString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
