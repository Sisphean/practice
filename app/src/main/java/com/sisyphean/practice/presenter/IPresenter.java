package com.sisyphean.practice.presenter;

import io.reactivex.disposables.Disposable;

public interface IPresenter<V> {

    void attachView(V view);

    void detachView();

    void detachView(Disposable disposable);

    V getView();
}
