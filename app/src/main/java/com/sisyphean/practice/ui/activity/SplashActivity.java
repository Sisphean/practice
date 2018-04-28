package com.sisyphean.practice.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.LoginPresenter;
import com.sisyphean.practice.presenter.SplashPresenter;
import com.sisyphean.practice.view.ISplashView;
import com.sisyphean.practice.view.IView;

public class SplashActivity extends BaseActivity<SplashPresenter> implements ISplashView {

    private Handler handler;
    private int countdown = 5;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(countdown + "s");
                countdown--;
                if (countdown == 0) {
                    presenter.toNextActivity();
                } else {

                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    private void initView() {
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void createPresenter() {
        presenter = new SplashPresenter();
    }

    @Override
    public void show() {

    }

    @Override
    public void toHomeActivity() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void toLoginActivity() {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
