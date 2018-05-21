package com.sisyphean.practice.presenter;

import android.text.TextUtils;

import com.sisyphean.practice.model.impl.ResetPwdModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.utils.RegexUtil;
import com.sisyphean.practice.view.logon.IResetPwdView;

public class ResetPwdPresenter extends EmailVerifyPresenter<IResetPwdView> {

    ResetPwdModel resetPwdModel;

    public ResetPwdPresenter() {
        super();
        resetPwdModel = new ResetPwdModel();
    }

    public void resetPwd() {
        if (checkSubmit()) {
            RxObserver<String> resetPwdOb = new RxObserver<String>(getView().getContext()) {
                @Override
                protected void onStart() {
                    super.onStart();
                    getView().showLoading("加载中...");
                }

                @Override
                protected void onSuccess(String data) {
                    getView().hideLoading();
                    getView().resetSuccess(data);
                }

                @Override
                protected void onFail(int errorCode, String errorMsg) {
                    getView().hideLoading();
                    getView().resetFail(errorMsg);
                }
            };
            mCompositeDisposable.add(
                    resetPwdModel.resetPassword(getView().getEmail(),
                            getView().getEmailVerification(),
                            getView().getPassword(),
                            getView().getPasswordConfirm())
                            .subscribeWith(resetPwdOb)
            );
        }

    }

    private boolean checkSubmit() {
        String email = getView().getEmail();
        String verification = getView().getEmailVerification();
        String password = getView().getPassword();
        String _password = getView().getPasswordConfirm();

        if (TextUtils.isEmpty(email)) {
            getView().showToast("请填写邮箱");
            return false;
        }

        if (TextUtils.isEmpty(verification)) {
            getView().showToast("请填写验证码");
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

        if (!RegexUtil.isEmail(email)) {
            getView().showToast("邮箱格式有误");
            return false;
        }

        if (!TextUtils.equals(password, _password)) {
            getView().showToast("确认密码有误");
            return false;
        }

        return true;
    }
}
