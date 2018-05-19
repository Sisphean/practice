package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.AuthBean;
import com.sisyphean.practice.bean.ResponseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AuthModel extends UploadModel {

    public static final int SWITH_REQUEST = 0;
    public static final int SWITH_SUBMIT = 1;

    public Observable<ResponseBean<AuthBean>> reqAuthInfo() {
        return doRequest().reqAuthInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<ResponseBean<String>> userAuthenticate(String trueName, String idCardNum, String justUrl, String backUrl) {
        return doRequest().userAuthenticate(SWITH_SUBMIT, trueName, idCardNum, justUrl, backUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
