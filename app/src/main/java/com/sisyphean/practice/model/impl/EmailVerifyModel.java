package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.ResponseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EmailVerifyModel extends BaseModel {

    public Observable<ResponseBean<String>> getVerification() {
        return doRequest().reqVerification()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
