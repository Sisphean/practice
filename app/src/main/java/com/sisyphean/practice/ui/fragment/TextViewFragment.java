package com.sisyphean.practice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sisyphean.practice.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TextViewFragment extends Fragment {

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Fragment edittextFragment = getParentFragment().getChildFragmentManager().findFragmentByTag("edittext");
        if (edittextFragment instanceof EditTextFragment) {
           textviewListener(((EditTextFragment) edittextFragment).getObservable());
        }
    }

    public void textviewListener(Observable<String> observable) {
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
