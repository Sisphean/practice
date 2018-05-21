package com.sisyphean.practice.view.user;

import com.sisyphean.practice.view.IView;

public interface IUserMainPageView extends IView {

    void toRechargeActivity();

    void toWithdrawActivity();

    void toAuthActivity();

    void toRechargeRecordActivity();

    void toWithdrawRecordActivity();

    void toTradeRecordActivity();

    void toWithdrawAccountActivity();

    void toMyOrdersActivity();

    void toPromoteActivity();

    void toResetPwdActivity();

    void toSupportActivity();

    void setName(String name);

    void setUSDT(String usdt);
}
