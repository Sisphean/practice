package com.sisyphean.practice.ui.activity.logon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.LoginPresenter;
import com.sisyphean.practice.ui.activity.BaseActivity;
import com.sisyphean.practice.ui.activity.MainActivity;
import com.sisyphean.practice.utils.ToastUtil;
import com.sisyphean.practice.view.logon.ILoginView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView, View.OnClickListener {

    private EditText et_email;
    private EditText et_password;
    private Button btn_login;
    private TextView tv_register;
    private TextView tv_reset_pwd;
    private TextView tv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new LoginPresenter();
    }

    @Override
    protected void initView() {
        tv_title = (TextView) findViewById(R.id.tv_logon_title);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_reset_pwd = (TextView) findViewById(R.id.tv_reset_pwd);

        btn_login.setOnClickListener(this);
        tv_reset_pwd.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public String getEmail() {
        return et_email.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString().trim();
    }

    @Override
    public void loginAction() {
        mPresenter.userLogin();
    }

    @Override
    public void toHomeActivity() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailHandle(int errorCode, String errorMsg) {
        showToast(errorMsg);
    }

    @Override
    public void toRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void toResetPwdActivity() {
        Intent intent = new Intent(this, ResetPwdActivity.class);
        startActivity(intent);
    }

    @Override
    public void setTitle() {
        tv_title.setText(R.string.title_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginAction();
                break;
            case R.id.tv_reset_pwd:
                toResetPwdActivity();
                break;
            case R.id.tv_register:
                toRegisterActivity();
                break;
        }
    }
}
