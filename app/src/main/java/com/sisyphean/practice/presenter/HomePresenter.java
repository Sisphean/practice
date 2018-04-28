package com.sisyphean.practice.presenter;

import com.sisyphean.practice.bean.ArticlesBean;
import com.sisyphean.practice.model.impl.HomeModel;
import com.sisyphean.practice.net.BaseObserver;
import com.sisyphean.practice.view.IHomeView;

public class HomePresenter extends BasePresenter<IHomeView> {

    HomeModel homeModel;

    public HomePresenter() {
        homeModel = new HomeModel();
    }

    public void reqArticleList() {
        BaseObserver<ArticlesBean> observer = new BaseObserver<ArticlesBean>(getView().getContext()) {

            @Override
            protected void onStart() {
                super.onStart();
                getView().showLoading("加载中...");
            }

            @Override
            protected void onSuccess(ArticlesBean data) {
                getView().hideLoading();
                getView().showList(data);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                getView().hideLoading();
                getView().showEmpty();

            }
        };

        mCompositeDisposable.add(
                homeModel.reqArticleList(getView().getCurPage())
                        .subscribeWith(observer)
        );

    }
}
