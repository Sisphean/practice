package com.sisyphean.practice.ui.activity.logon;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.ResetPwdPresenter;
import com.sisyphean.practice.ui.activity.BaseActivity;
import com.sisyphean.practice.view.logon.IResetPwdView;

public class ResetPwdActivity extends BaseActivity<ResetPwdPresenter> implements IResetPwdView, View.OnClickListener {

    private TextView tv_title;
    private EditText et_email, et_verify, et_password, et_pwd_confirm;
    private Button btn_verify, btn_reset_pwd;
    private int countdown = 60;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVerifyBtn();
    }

    @Override
    protected void initView() {
        tv_title = (TextView) findViewById(R.id.tv_logon_title);
        et_email = (EditText) findViewById(R.id.et_email);
        et_verify = (EditText) findViewById(R.id.et_verification);
        et_password = (EditText) findViewById(R.id.et_password);
        et_pwd_confirm = (EditText) findViewById(R.id.et_pwd_confirm);
        btn_verify = (Button) findViewById(R.id.btn_verify);
        btn_reset_pwd = (Button) findViewById(R.id.btn_reset_pwd);

        btn_verify.setOnClickListener(this);
        btn_reset_pwd.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_pwd;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new ResetPwdPresenter();
    }

    @Override
    public void setTitle() {
        tv_title.setText(R.string.title_reset_pwd);
    }

    @Override
    public String getEmail() {
        return et_email.getText().toString();
    }

    @Override
    public void verifyCD() {
        countdown = mPresenter.getVerifyCD();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        btn_verify.setClickable(false);
                        btn_verify.setText(countdown + "s");
                        countdown--;
                        if (countdown == 0) {
                            btn_verify.setClickable(true);
                            btn_verify.setText(R.string.send);
                        } else {
                            handler.postDelayed(this, 1000);
                        }
                    }
                }, 1000
        );
    }

    @Override
    public void sendSuccess() {
        showToast("验证码已发送成功，注意查看邮箱");
    }

    @Override
    public void sendFail() {
        showToast("验证码发送失败");
    }

    @Override
    public String getEmailVerification() {
        return et_verify.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    @Override
    public String getPasswordConfirm() {
        return et_pwd_confirm.getText().toString();
    }

    @Override
    public void setVerifyBtn() {
        if (mPresenter.isVerifyCDEnd()) {
            btn_verify.setClickable(true);
            btn_verify.setText("发送");
        } else {
            verifyCD();
        }
    }

    @Override
    public void resetSuccess(String data) {
        showToast(data);
    }

    @Override
    public void resetFail(String errorMsg) {
        showToast(errorMsg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_verify:
                mPresenter.reqVerification("forget", 0);
                break;
            case R.id.btn_reset_pwd:
                mPresenter.resetPwd();
                break;
        }
    }
}
