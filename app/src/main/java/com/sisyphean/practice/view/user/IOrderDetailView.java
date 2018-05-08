package com.sisyphean.practice.view.user;

import com.sisyphean.practice.view.IView;

public interface IOrderDetailView extends IView {

    void setUnitPrice();

    void setAmount();

    void setNumber();

    void setStatus();
}