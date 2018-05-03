package com.sisyphean.practice.view.logon;

import com.sisyphean.practice.view.IView;

public interface ILoginView extends IView {

    String getUsername();

    String getPassword();

    void loginAction();

    void toHomeActivity();

    void loginFailHandle();

    void clear();

    void validateAccount();

    void validatePwd();

    void toRegisterActivity();

    void toResetPwdActivity();

    void setTitle();
}
