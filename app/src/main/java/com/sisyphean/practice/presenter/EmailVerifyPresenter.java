package com.sisyphean.practice.presenter;

import com.sisyphean.practice.model.impl.EmailVerifyModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.utils.SPUtil;
import com.sisyphean.practice.view.IEmailVerifyView;

public class EmailVerifyPresenter<V extends IEmailVerifyView> extends BasePresenter<V> {

    EmailVerifyModel emailVerifyModel;
    private static final String KEY_CD_STARTTIME = "startTime";

    public EmailVerifyPresenter() {
        emailVerifyModel = new EmailVerifyModel();
    }

    public void reqVerification(String controller, int type) {
        RxObserver<String> verifyOb = new RxObserver<String>(getView().getContext()) {
            @Override
            protected void onStart() {
                super.onStart();
                saveCurTime();
                getView().verifyCD();
            }

            @Override
            protected void onSuccess(String data) {
                getView().sendSuccess();
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                getView().sendFail();
            }
        };

        mCompositeDisposable.add(
                emailVerifyModel.getVerification(controller, getView().getEmail(), type)
                        .subscribeWith(verifyOb)
        );

    }

    public void saveCurTime() {
        SPUtil.putValue(KEY_CD_STARTTIME, System.currentTimeMillis());
    }

    public boolean isVerifyCDEnd() {
        long startTime = (long) SPUtil.getValue(KEY_CD_STARTTIME, 0L);
        long curTime = System.currentTimeMillis();

        return (curTime - startTime) >= 60 * 1000;
    }

    public int getVerifyCD() {
        long startTime = (long) SPUtil.getValue(KEY_CD_STARTTIME, 0L);
        long curTime = System.currentTimeMillis();
        long del =  ((curTime - startTime) / 1000);
        return del > 60 ? 0 : 60 - (int)del;

    }



}
