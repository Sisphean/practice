package com.sisyphean.practice.presenter;

import android.text.TextUtils;

import com.sisyphean.practice.model.impl.RegisterModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.utils.RegexUtil;
import com.sisyphean.practice.view.logon.IRegisterView;

public class RegisterPresenter extends BasePresenter<IRegisterView> {

    RegisterModel registerModel;

    public RegisterPresenter() {
        registerModel = new RegisterModel();
    }

    public void userRegister() {

        if (checkSubmit()) {
            RxObserver<String> registerOb = new RxObserver<String>(getView().getContext()) {
                @Override
                protected void onStart() {
                    getView().showLoading("正在注册中...");
                }

                @Override
                protected void onSuccess(String data) {
                    getView().hideLoading();
                    getView().registerSuccess(data);
                }

                @Override
                protected void onFail(int errorCode, String errorMsg) {
                    getView().hideLoading();
                    getView().registerFail(errorCode, errorMsg);
                }

            };
            mCompositeDisposable.add(
                    registerModel.userRegister(getView().getEmail(),
                            getView().getPassword(),
                            getView().getPasswordConfirm(),
                            getView().getEmailRecommend())
                            .subscribeWith(registerOb)
            );
        }

    }

    private boolean checkSubmit() {
        String email = getView().getEmail();
        String password = getView().getPassword();
        String _password = getView().getPasswordConfirm();
        String spread = getView().getEmailRecommend();
        if (TextUtils.isEmpty(email)) {
            getView().showToast("请填写邮箱");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            getView().showToast("请填写密码");
            return false;
        }

        if (TextUtils.isEmpty(_password)) {
            getView().showToast("请填写确认密码");
            return false;
        }

        if (TextUtils.isEmpty(spread)) {
            getView().showToast("请填写推荐人邮箱");
            return false;
        }

        if (!RegexUtil.isEmail(email)) {
            getView().showToast("邮箱格式有误");
            return false;
        }

        if (!TextUtils.equals(password, _password)) {
            getView().showToast("两次输入的密码不一致");
            return false;
        }

        if (!RegexUtil.isEmail(spread)) {
            getView().showToast("推荐人邮箱格式有误");
            return false;
        }

        return true;
    }
}
