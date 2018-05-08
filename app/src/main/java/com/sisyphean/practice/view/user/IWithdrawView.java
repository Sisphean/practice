package com.sisyphean.practice.view.user;

import com.sisyphean.practice.view.IView;

public interface IWithdrawView extends IView {

    void toUSTDAddrCreateActivity();

    String getWithdrawAddr();

    float getNumber();
}
