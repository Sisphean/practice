package com.sisyphean.practice.ui.activity.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.huantansheng.easyphotos.EasyPhotos;
import com.sisyphean.practice.App;
import com.sisyphean.practice.R;
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
                /*saveBitmapFile(BitmapFactory.decodeResource(getResources(), R.drawable.ic_log_withdraw));
                File file  = StorageUtil.getExternalStorageDir("test.png");

                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("imgfile", file.getName(), imageBody);

                RetrofitClient.getApi().uploadFile(imageBodyPart);*/

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

    public void saveBitmapFile(Bitmap bitmap){
        File file=new File(App.getContext().getExternalFilesDir(null), "test.png");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void hideSubmit() {
        tv_auth_status.setVisibility(View.VISIBLE);
        just_group.setVisibility(View.GONE);
        back_group.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);
    }

    @Override
    public void showSubmit() {
        tv_auth_status.setVisibility(View.GONE);
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
}
