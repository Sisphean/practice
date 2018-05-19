package com.sisyphean.practice.presenter;

import com.sisyphean.practice.bean.AuthBean;
import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UploadBean;
import com.sisyphean.practice.model.impl.AuthModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.view.user.IAuthView;

import java.util.Map;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.RequestBody;

public class AuthPresenter extends BasePresenter<IAuthView> {

    private AuthModel authModel;

    public AuthPresenter() {
        authModel = new AuthModel();
    }

    public void reqAuthInfo() {
        RxObserver<AuthBean> authOb = new RxObserver<AuthBean>(getView().getContext()) {
            @Override
            protected void onStart() {
                getView().showLoading("加载中...");
                super.onStart();
            }

            @Override
            protected void onSuccess(AuthBean data) {
                getView().hideLoading();
                switch (data.getAuthCode()) {
                    case AuthBean.AUTH_STATUS_AUTHING:
                    case AuthBean.AUTH_STATUS_SUNCCESS:
                        getView().hideSubmit();
                        break;
                    case AuthBean.AUTH_STATUS_FAIL:
                    case AuthBean.AUTH_STATUS_UNAUTH:
                        getView().showSubmit();
                        break;
                }
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                getView().hideLoading();
            }
        };

        mCompositeDisposable.add(
                authModel.reqAuthInfo()
                        .subscribeWith(authOb)
        );

    }

    public void userAuthenticate() {


        Map<String, RequestBody> imgMap = getImagesMap();

    }

    private Map<String, RequestBody> getImagesMap() {
        return null;
    }


}
