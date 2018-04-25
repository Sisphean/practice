package com.sisyphean.practice.presenter;

import android.text.TextUtils;

import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.model.impl.LoginModel;
import com.sisyphean.practice.net.BaseObserver;
import com.sisyphean.practice.view.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> {

    private LoginModel loginModel;


    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    public void userLogin() {

        if (validateForm()) {
            BaseObserver<UserBean> loginObserver = new BaseObserver<UserBean>(getView().getContext()) {

                @Override
                protected void onStart() {
                    super.onStart();
                    getView().showLoading("正在登录中...");
                }

                @Override
                protected void onSuccess(UserBean data) {
                    getView().hideLoading();
                    getView().toHomeActivity();
                }

                @Override
                protected void onFail(int errorCode, String errorMsg) {
                    getView().hideLoading();
                    getView().loginFailHandle();
                }
            };

            mCompositeDisposable.add(
                    loginModel.userLogin(getView().getUsername(), getView().getPassword())
                            .subscribeWith(loginObserver)
            );
        }

    }

    private boolean validateForm() {
        if (TextUtils.isEmpty(getView().getUsername())) {
            getView().validateAccount();
            return false;
        }

        if (TextUtils.isEmpty(getView().getPassword())) {
            getView().validatePwd();
            return false;
        }

        return true;
    }
}
