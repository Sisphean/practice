package com.sisyphean.practice.view;

/**
 * 欢迎页面
 */
public interface ISplashView extends IView {

    /**
     * 显示欢迎页面
     */
    void show();

    /**
     * 用户登录过 进入首页
     */
    void toHomeActivity();

    /**
     * 用户未登陆 进入登陆页面
     */
    void toLoginActivity();

}
