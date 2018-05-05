package com.sisyphean.practice.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.WithdrawPresenter;
import com.sisyphean.practice.ui.activity.BaseActivity;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;
import com.sisyphean.practice.view.user.IWithdrawView;

public class WithdrawActivity extends BaseToolBarActivity<WithdrawPresenter> implements IWithdrawView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int setToolbarTitle() {
        return R.string.title_withdraw;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    private void initView() {
        Spinner spinner = findViewById(R.id.spinner);
        String[] mItems = {"java", "php", "python", "C++"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView tv_create_ustd_addr = findViewById(R.id.tv_create_ustd_addr);
        tv_create_ustd_addr.setOnClickListener(this);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new WithdrawPresenter();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_create_ustd_addr:
                toUSTDAddrCreateActivity();
                break;
        }
    }

    @Override
    public void toUSTDAddrCreateActivity() {
        Intent intent = new Intent(this, WithdrawAddrCreateActivity.class);
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
}
