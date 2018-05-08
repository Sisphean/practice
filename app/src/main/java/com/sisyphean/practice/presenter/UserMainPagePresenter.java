package com.sisyphean.practice.presenter;

import com.sisyphean.practice.view.user.IUserMainPageView;

public class UserMainPagePresenter extends BasePresenter<IUserMainPageView> {

    public UserMainPagePresenter() {

    }

    public void getUserInfo() {
        // 获取用户姓名 ustd值
        getView().setName("张三");
    }
}
