package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.ResponseBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class EmailVerifyModel extends BaseModel {

    public Observable<ResponseBean<String>> getVerification(String controller, String email, int type) {
        return doRequest().reqVerification(controller, email, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
