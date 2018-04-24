package com.sisyphean.practice.view;

public interface ILoginView extends IView {

    String getUsername();

    String getPassword();

    void loginAction();

    void toHomeActivity();

    void loginFailHandle();

    void clear();
}
