package com.sisyphean.practice.presenter;

import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.model.impl.UserMainPageModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.view.user.IUserMainPageView;

public class UserMainPagePresenter extends BasePresenter<IUserMainPageView> {

    private UserMainPageModel userMainPageModel;

    public UserMainPagePresenter() {
        userMainPageModel = new UserMainPageModel();
    }

    public void getUserInfo() {
        RxObserver<UserBean> userMainPageOb = new RxObserver<UserBean>(getView().getContext()) {
            @Override
            protected void onSuccess(UserBean data) {
                getView().setName(data.getNickname());
                getView().setUSDT(data.getBalance());
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {

            }
        };

        mCompositeDisposable.add(
                userMainPageModel.getUserMainPageInfo()
                        .subscribeWith(userMainPageOb)
        );
    }
}
