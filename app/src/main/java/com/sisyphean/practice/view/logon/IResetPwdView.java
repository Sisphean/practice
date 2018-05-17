package com.sisyphean.practice.view.logon;

import com.sisyphean.practice.view.IEmailVerifyView;

public interface IResetPwdView extends IEmailVerifyView {

    void setTitle();

    String getEmail();

    String getEmailVerification();

    String getPassword();

    String getPasswordConfirm();

    void setVerifyBtn();

    void resetSuccess(String data);

    void resetFail(String errorMsg);

}
