package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UserBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserMainPageModel extends BaseModel {

    public Observable<ResponseBean<UserBean>> getUserMainPageInfo() {
        return doRequest().reqUserMainPageInfo("def")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
