package com.sisyphean.practice.presenter;

import android.text.TextUtils;

import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.common.Constant;
import com.sisyphean.practice.model.impl.LoginModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.utils.GsonUtil;
import com.sisyphean.practice.utils.RegexUtil;
import com.sisyphean.practice.utils.SPUtil;
import com.sisyphean.practice.view.logon.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> {

    private LoginModel loginModel;


    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    public void userLogin() {

        if (checkSubmit()) {
            RxObserver<UserBean> loginObserver = new RxObserver<UserBean>(getView().getContext()) {

                @Override
                protected void onStart() {
                    super.onStart();
                    getView().showLoading("正在登录中...");
                }

                @Override
                protected void onSuccess(UserBean data) {
                    saveLoginStatus(data);
                    getView().hideLoading();
                    getView().toHomeActivity();
                }

                @Override
                protected void onFail(int errorCode, String errorMsg) {
                    getView().hideLoading();
                    getView().loginFailHandle(errorCode, errorMsg);
                }
            };

            mCompositeDisposable.add(
                    loginModel.userLogin(getView().getEmail(), getView().getPassword())
                            .subscribeWith(loginObserver)
            );
        }

    }

    private boolean checkSubmit() {
        String email = getView().getEmail();
        String password = getView().getPassword();
        if (TextUtils.isEmpty(email)) {
            getView().showToast("请填写邮箱");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            getView().showToast("请填写密码");
            return false;
        }

        if (!RegexUtil.isEmail(email)) {
            getView().showToast("邮箱格式有误");
            return false;
        }

        return true;
    }

    private void saveLoginStatus(UserBean data) {
        String json = GsonUtil.toJson(data);
        SPUtil.putValue(Constant.KEY_LOGIN_INFO, json);
        SPUtil.putValue(Constant.KEY_LOGINSTATUS, true);
    }
}
