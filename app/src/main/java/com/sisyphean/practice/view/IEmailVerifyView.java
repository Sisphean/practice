package com.sisyphean.practice.view;

public interface IEmailVerifyView extends IView {
    String getEmail();

    void verifyCD();

    void sendSuccess();

    void sendFail();
}
