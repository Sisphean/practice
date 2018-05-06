package com.sisyphean.practice.view.user;

import com.sisyphean.practice.view.IView;

public interface IUserMainPageView extends IView {

    void toRechargeActivity();

    void toWithdrawActivity();

    void toAuthActivity();

    void toRechargeLogActivity();

    void toWithdrawLogActivity();

    void toTradeLogActivity();

    void toWithdrawAccountActivity();

    void toMyTradeActivity();

    void toPromoteActivity();

    void toResetPwdActivity();

    void toSupportActivity();

    void setName(String name);

    void setUstd(float ustd);
}
