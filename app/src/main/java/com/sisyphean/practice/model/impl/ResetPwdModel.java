package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.ResponseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ResetPwdModel extends BaseModel {

    public Observable<ResponseBean<String>> resetPassword(String email, String verify, String password, String _password) {
        return doRequest().resetPassword(email, verify, password, _password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
