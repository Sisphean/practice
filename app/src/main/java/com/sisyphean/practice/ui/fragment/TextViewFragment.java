package com.sisyphean.practice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.event.MessageEvent;
import com.sisyphean.practice.presenter.TestPresenter;
import com.sisyphean.practice.utils.RxBus;
import com.sisyphean.practice.view.ITestView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class TextViewFragment extends BaseFragment<TestPresenter> implements ITestView{

    private TextView textView;

    public static Fragment getInstance() {
        TextViewFragment textViewFragment = new TextViewFragment();
        return textViewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text, null);
        textView = rootView.findViewById(R.id.textView);
        return rootView;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
//        FragmentManager fragmentManager = getFragmentManager();
        /*Fragment edittextFragment = getFragmentManager().findFragmentByTag("edittext");
        if (edittextFragment instanceof EditTextFragment) {
           textviewListener(((EditTextFragment) edittextFragment).getObservable());
        }*/

        mPresenter.textListener();
    }

    @Override
    protected void createPresenter() {
        mPresenter = new TestPresenter();
    }


    @Override
    public void textListener(String str) {
        textView.setText(str);
    }
}
