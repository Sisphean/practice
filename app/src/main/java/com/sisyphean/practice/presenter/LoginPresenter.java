package com.sisyphean.practice.presenter;

import android.text.TextUtils;

import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.common.Constant;
import com.sisyphean.practice.model.impl.LoginModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.utils.GsonUtil;
import com.sisyphean.practice.utils.SPUtil;
import com.sisyphean.practice.view.logon.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> {

    private LoginModel loginModel;


    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    public void userLogin() {

        if (validateForm()) {
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
                    getView().loginFailHandle();
                }
            };

            mCompositeDisposable.add(
                    loginModel.userLogin(getView().getUsername(), getView().getPassword())
                            .subscribeWith(loginObserver)
            );
        }

    }

    private boolean validateForm() {
        if (TextUtils.isEmpty(getView().getUsername())) {
            getView().validateAccount();
            return false;
        }

        if (TextUtils.isEmpty(getView().getPassword())) {
            getView().validatePwd();
            return false;
        }


        return true;
    }

    private void saveLoginStatus(UserBean data) {
//        SPUtil.putValue(Constant.KEY_USERNAME, data.getUsername());
//        SPUtil.putValue(Constant.KEY_PASSWORD, data.getPassword());
        String json = GsonUtil.toJson(data);
        SPUtil.putValue(Constant.KEY_LOGIN_INFO, json);
        SPUtil.putValue(Constant.KEY_LOGINSTATUS, true);
    }
}
