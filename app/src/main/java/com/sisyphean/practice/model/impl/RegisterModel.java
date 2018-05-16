package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.ResponseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel extends BaseModel {

    public Observable<ResponseBean<String>> userRegister(String email, String password, String _password, String spread) {
        return doRequest().userRegister(email, password, _password, spread)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
