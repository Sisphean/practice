package com.sisyphean.practice.presenter;

import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.model.impl.LoginModel;
import com.sisyphean.practice.net.BaseObserver;
import com.sisyphean.practice.view.ILoginView;

public class LoginPresenter {

    private ILoginView loginView;
    private LoginModel loginModel;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    public void userLogin() {
        loginModel.userLogin(loginView.getUsername(),
                loginView.getPassword(),
                new BaseObserver<UserBean>(loginView.getContext(), new BaseObserver.ResponseHandleCallback<UserBean>() {
                    @Override
                    public void onSuccess(UserBean data) {
                        loginView.toHomeActivity();
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        loginView.loginFailHandle();
                    }
                }));
    }


}
