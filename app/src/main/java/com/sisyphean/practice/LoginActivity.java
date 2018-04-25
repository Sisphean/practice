package com.sisyphean.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sisyphean.practice.presenter.LoginPresenter;
import com.sisyphean.practice.utils.ToastUtil;
import com.sisyphean.practice.view.ILoginView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView,View.OnClickListener{

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acticity);

        initView();
    }

    @Override
    protected void createPresenter() {
        presenter = new LoginPresenter();
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
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
        presenter.userLogin();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginAction();
                break;
        }
    }
}
