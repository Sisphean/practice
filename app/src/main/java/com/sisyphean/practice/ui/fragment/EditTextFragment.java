package com.sisyphean.practice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sisyphean.practice.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class EditTextFragment extends Fragment {

    private Observable<String> observable;

    public static Fragment getInstance() {
        EditTextFragment editTextFragment = new EditTextFragment();
        return editTextFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit, null);
        final EditText editText = rootView.findViewById(R.id.edittext);
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter emitter) throws Exception {
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        emitter.onNext(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });
        return rootView;
    }

    public Observable<String> getObservable() {
        return observable;
    }
}
