package com.sisyphean.practice.view.logon;

import com.sisyphean.practice.view.IView;

public interface IResetPwdView extends IView {

    void setTitle();

    String getEmail();

    String getEmailVerification();

    String getPassword();

    String getPasswordConfirm();

    void submitSuccess();

    void submitFail();

}
