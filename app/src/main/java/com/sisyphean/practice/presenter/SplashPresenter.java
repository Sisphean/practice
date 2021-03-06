package com.sisyphean.practice.presenter;

import com.sisyphean.practice.common.Constant;
import com.sisyphean.practice.utils.SPUtil;
import com.sisyphean.practice.view.ISplashView;

public class SplashPresenter extends BasePresenter<ISplashView> {

    public SplashPresenter() {
    }

    private boolean checkLogin() {
        return (boolean) SPUtil.getValue(Constant.KEY_LOGINSTATUS, false);
    }

    public void toNextActivity() {
        if (checkLogin()) {
            getView().toHomeActivity();
        } else {
            getView().toLoginActivity();
        }
    }
}
