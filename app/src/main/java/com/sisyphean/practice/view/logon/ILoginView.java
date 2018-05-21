package com.sisyphean.practice.view.logon;

import com.sisyphean.practice.view.IView;

public interface ILoginView extends IView {

    String getEmail();

    String getPassword();

    void loginAction();

    void toHomeActivity();

    void loginFailHandle(int errorCode, String errorMsg);

    void toRegisterActivity();

    void toResetPwdActivity();

    void setTitle();
}
