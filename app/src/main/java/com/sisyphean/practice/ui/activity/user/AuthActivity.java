package com.sisyphean.practice.ui.activity.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huantansheng.easyphotos.EasyPhotos;
import com.sisyphean.practice.App;
import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.AuthBean;
import com.sisyphean.practice.imageloader.GlideEngine;
import com.sisyphean.practice.presenter.AuthPresenter;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;
import com.sisyphean.practice.view.user.IAuthView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AuthActivity extends BaseToolBarActivity<AuthPresenter> implements IAuthView{

    public static final int REQUESTCODE_JUST = 101;
    public static final int REQUESTCODE_BACK = 102;

    private ViewGroup just_group;
    private ViewGroup back_group;
    private Button btn_submit;
    private TextView tv_auth_status;
    private ImageView iv_just;
    private ImageView iv_back;
    private EditText et_truename;
    private EditText et_idcard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.reqAuthInfo();
    }

    @Override
    protected void initView() {
        btn_submit = (Button) findViewById(R.id.btn_submit);
        just_group = (ViewGroup) findViewById(R.id.justurl_group);
        back_group = (ViewGroup) findViewById(R.id.backurl_group);
        tv_auth_status = (TextView) findViewById(R.id.tv_auth_status);
        iv_just = (ImageView) findViewById(R.id.iv_card_just);
        iv_back = (ImageView) findViewById(R.id.iv_card_back);
        et_truename = (EditText) findViewById(R.id.et_truename);
        et_idcard = (EditText) findViewById(R.id.et_idcard);

        btn_submit.setOnClickListener(this);
        just_group.setOnClickListener(this);
        back_group.setOnClickListener(this);
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void onMenuItemClickListener(MenuItem item) {

    }

    @Override
    protected int setToolbarTitle() {
        return R.string.title_auth;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new AuthPresenter();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_submit:
                mPresenter.userAuthenticate();

                break;

            case R.id.justurl_group:
                EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.sisyphean.practice.fileprovider")
                        .setSelectedPhotoPaths(mPresenter.getJustSelectedPhotoPaths())
                        .setPuzzleMenu(false)
                        .start(REQUESTCODE_JUST);
                break;
            case R.id.backurl_group:
                EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.sisyphean.practice.fileprovider")
                        .setSelectedPhotoPaths(mPresenter.getBackSelectedPhotoPaths())
                        .setPuzzleMenu(false)
                        .start(REQUESTCODE_BACK);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void hideSubmit(int status) {
        tv_auth_status.setVisibility(View.VISIBLE);
        if (status == AuthBean.AUTH_STATUS_AUTHING) {
            tv_auth_status.setText("审核中");
        } else if (status == AuthBean.AUTH_STATUS_SUNCCESS) {
            tv_auth_status.setText("身份已认证");
        }
        et_truename.setEnabled(false);
        et_idcard.setEnabled(false);
        just_group.setVisibility(View.GONE);
        back_group.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);
    }

    @Override
    public void showSubmit() {
        tv_auth_status.setVisibility(View.GONE);
        et_truename.setEnabled(true);
        et_idcard.setEnabled(true);
        just_group.setVisibility(View.VISIBLE);
        back_group.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBackImg(Bitmap backImg) {
        iv_back.setImageBitmap(backImg);
    }

    @Override
    public void showJustImg(Bitmap justImg) {
        iv_just.setImageBitmap(justImg);
    }

    @Override
    public int getGroupHeight() {
        return just_group.getHeight();
    }

    @Override
    public int getGroupWidth() {
        return just_group.getWidth();
    }

    @Override
    public String getTrueName() {
        return et_truename.getText().toString();
    }

    @Override
    public String getIDCrad() {
        return et_idcard.getText().toString();
    }
}
