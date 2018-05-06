package com.sisyphean.practice.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.WithdrawPresenter;
import com.sisyphean.practice.ui.activity.user.WithdrawAddrCreateActivity;
import com.sisyphean.practice.ui.fragment.BaseFragment;
import com.sisyphean.practice.view.user.IWithdrawView;

public class WithdrawFragment extends BaseFragment<WithdrawPresenter> implements IWithdrawView,View.OnClickListener{

    public static Fragment getInstance() {
        WithdrawFragment withdrawFragment = new WithdrawFragment();
        return withdrawFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_withdraw, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        Spinner spinner = rootView.findViewById(R.id.spinner);
        String[] mItems = {"java", "php", "python", "C++"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView tv_create_ustd_addr = rootView.findViewById(R.id.tv_create_ustd_addr);
        tv_create_ustd_addr.setOnClickListener(this);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new WithdrawPresenter();
    }

    @Override
    public void toUSTDAddrCreateActivity() {
        Intent intent = new Intent(getActivity(), WithdrawAddrCreateActivity.class);
        startActivity(intent);
    }

    @Override
    public String getWithdrawAddr() {
        return null;
    }

    @Override
    public float getNumber() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create_ustd_addr:
                toUSTDAddrCreateActivity();
                break;
        }
    }
}
