package com.sisyphean.practice.presenter;

import com.sisyphean.practice.view.IView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IView> implements IPresenter<V> {

    public CompositeDisposable mCompositeDisposable;

    public V view;

    @Override
    public void attachView(V view) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        this.view = view;
    }

    @Override
    public void detachView() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        mCompositeDisposable = null;
        view = null;
    }

    @Override
    public void detachView(Disposable disposable) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.remove(disposable);
        }
        view = null;
    }


    @Override
    public V getView() {
        return view;
    }
}
