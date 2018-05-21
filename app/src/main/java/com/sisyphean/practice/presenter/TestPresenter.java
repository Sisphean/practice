package com.sisyphean.practice.presenter;

import com.sisyphean.practice.bean.event.MessageEvent;
import com.sisyphean.practice.utils.RxBus;
import com.sisyphean.practice.view.ITestView;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class TestPresenter extends BasePresenter<ITestView> {

    public void textListener() {
        Disposable subscribe = RxBus.getInstance().toObservable(MessageEvent.class).subscribe(new Consumer<MessageEvent>() {
            @Override
            public void accept(MessageEvent messageEvent) throws Exception {
                getView().textListener(messageEvent.getMessage());
            }
        });

        mCompositeDisposable.add(subscribe);
    }
}
