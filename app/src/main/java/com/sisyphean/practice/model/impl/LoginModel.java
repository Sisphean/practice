package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.model.ILoginModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements ILoginModel {

    public Observable<ResponseBean<UserBean>> userLogin(String username, String password) {
        return doRequest().userLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
