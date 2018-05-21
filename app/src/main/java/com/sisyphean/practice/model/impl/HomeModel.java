package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.ArticlesBean;
import com.sisyphean.practice.bean.ResponseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel extends BaseModel {

    /*public Observable<ResponseBean<ArticlesBean>> reqArticleList(int curPage) {
        return doRequest().reqArticleList(curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }*/
}
