package com.sisyphean.practice.view.logon;

import com.sisyphean.practice.view.IView;

public interface IRegisterView extends IView {

    String getEmail();

    String getPassword();

    String getPasswordConfirm();

    String getEmailRecommend();

    void registerSuccess(String data);

    void registerFail(int errorCode, String errorMsg);

    void setTitle();
}
