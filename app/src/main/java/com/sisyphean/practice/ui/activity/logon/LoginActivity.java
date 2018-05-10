package com.sisyphean.practice.ui.activity.logon;

import android.content.Intent;
import android.os.Bundle;
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

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private TextView tv_register;
    private TextView tv_reset_pwd;
    private TextView tv_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new LoginPresenter();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_logon_title);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_reset_pwd = (TextView) findViewById(R.id.tv_reset_pwd);

        btn_login.setOnClickListener(this);
        tv_reset_pwd.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public String getUsername() {
        return et_username.getText().toString().trim();
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
    public void loginFailHandle() {
        ToastUtil.show(this, "登录失败");
    }

    @Override
    public void clear() {

    }

    @Override
    public void validateAccount() {
        ToastUtil.show(this, "请填写用户名");
    }

    @Override
    public void validatePwd() {
        ToastUtil.show(this, "请填写密码");
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
