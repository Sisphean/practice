package com.sisyphean.practice.ui.activity.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.sisyphean.practice.App;
import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.net.RetrofitClient;
import com.sisyphean.practice.ui.activity.BaseActivity;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;
import com.sisyphean.practice.utils.StorageUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        Button btn_upload = (Button) findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(this);
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

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_upload:
                saveBitmapFile(BitmapFactory.decodeResource(getResources(), R.drawable.ic_log_withdraw));
                File file  = StorageUtil.getExternalStorageDir("test.png");

                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("imgfile", file.getName(), imageBody);

                RetrofitClient.getApi().uploadFile(imageBodyPart);

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
}
