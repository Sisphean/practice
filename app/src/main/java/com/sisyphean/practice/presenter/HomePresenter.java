package com.sisyphean.practice.presenter;

import com.sisyphean.practice.bean.ArticlesBean;
import com.sisyphean.practice.model.impl.HomeModel;
import com.sisyphean.practice.net.BaseObserver;
import com.sisyphean.practice.view.IHomeView;

public class HomePresenter extends BasePresenter<IHomeView> {

    private int mCurPage = 0;

    private HomeModel homeModel;

    public HomePresenter() {
        homeModel = new HomeModel();
    }

    public void reqArticleList(boolean isRefresh) {
        if (isRefresh) {
            mCurPage = 0;
        }

        BaseObserver<ArticlesBean> observer = new BaseObserver<ArticlesBean>(getView().getContext()) {

            @Override
            protected void onStart() {
                super.onStart();
//                getView().showLoading("加载中...");
            }

            @Override
            protected void onSuccess(ArticlesBean data) {
//                getView().hideLoading();
                if (data.getCurPage() == 1) {
                    getView().showList(data);
                } else {
                    getView().loadMore(data);
                }
                mCurPage = data.getCurPage();
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
//                getView().hideLoading();
                getView().showEmpty();

            }
        };

        mCompositeDisposable.add(
                homeModel.reqArticleList(mCurPage)
                        .subscribeWith(observer)
        );

    }

}
