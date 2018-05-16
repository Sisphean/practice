package com.sisyphean.practice.ui.activity.logon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.RegisterPresenter;
import com.sisyphean.practice.ui.activity.BaseActivity;
import com.sisyphean.practice.view.logon.IRegisterView;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView, View.OnClickListener {

    private TextView tv_title;
    private EditText et_email;
    private EditText et_password;
    private EditText et_pwd_confirm;
    private EditText et_spread;
    private Button btn_register;

    @Override
    protected void initView() {
        tv_title = (TextView) findViewById(R.id.tv_logon_title);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_pwd_confirm = (EditText) findViewById(R.id.et_pwd_confirm);
        et_spread = (EditText) findViewById(R.id.et_spread);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterPresenter();
    }

    @Override
    public String getEmail() {
        return et_email.getText() == null ? "" : et_email.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText() == null ? "" : et_password.getText().toString();
    }

    @Override
    public String getPasswordConfirm() {
        return et_pwd_confirm.getText() == null ? "" : et_pwd_confirm.getText().toString();
    }

    @Override
    public String getEmailRecommend() {
        return et_spread.getText() == null ? "" : et_spread.getText().toString();
    }

    @Override
    public void registerSuccess(String data) {
        showToast(data);
        finish();
    }

    @Override
    public void registerFail(int errorCode, String errorMsg) {
        showToast(errorMsg);
    }

    @Override
    public void setTitle() {
        tv_title.setText(R.string.title_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                mPresenter.userRegister();
                break;
        }
    }
}
