package com.sisyphean.practice.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

import com.huantansheng.easyphotos.EasyPhotos;
import com.sisyphean.practice.bean.AuthBean;
import com.sisyphean.practice.imageloader.ImageUtils;
import com.sisyphean.practice.model.impl.AuthModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.ui.activity.user.AuthActivity;
import com.sisyphean.practice.view.user.IAuthView;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;

public class AuthPresenter extends BasePresenter<IAuthView> {


    private AuthModel authModel;
    public ArrayList<String> mJustSelectedPhotoPaths = new ArrayList<>();
    public ArrayList<String> mBackSelectedPhotoPaths = new ArrayList<>();

    public AuthPresenter() {
        authModel = new AuthModel();

    }

    public void reqAuthInfo() {
        RxObserver<AuthBean> authOb = new RxObserver<AuthBean>(getView().getContext()) {
            @Override
            protected void onStart() {
                getView().showLoading("加载中...");
                super.onStart();
            }

            @Override
            protected void onSuccess(AuthBean data) {
                getView().hideLoading();
                switch (data.getAuthCode()) {
                    case AuthBean.AUTH_STATUS_AUTHING:
                    case AuthBean.AUTH_STATUS_SUNCCESS:
                        getView().hideSubmit();
                        break;
                    case AuthBean.AUTH_STATUS_FAIL:
                    case AuthBean.AUTH_STATUS_UNAUTH:
                        getView().showSubmit();
                        break;
                }
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                getView().hideLoading();
            }
        };

        mCompositeDisposable.add(
                authModel.reqAuthInfo()
                        .subscribeWith(authOb)
        );

    }

    public void userAuthenticate() {


        Map<String, RequestBody> imgMap = getImagesMap();

    }

    private Map<String, RequestBody> getImagesMap() {
        return null;
    }

    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            final ArrayList<String> photosPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);

            if (photosPaths.size() > 0) {

                String photoPath = photosPaths.get(0);
                Bitmap bitmap = createBitmap(photoPath);
                if (requestCode == AuthActivity.REQUESTCODE_JUST) {
                    mJustSelectedPhotoPaths.clear();
                    mJustSelectedPhotoPaths.addAll(photosPaths);
                    getView().showJustImg(bitmap);
                } else {
                    mBackSelectedPhotoPaths.clear();
                    mBackSelectedPhotoPaths.addAll(photosPaths);
                    getView().showBackImg(bitmap);
                }
            }

        }
    }

    private Bitmap createBitmap(String photoPath) {

        Bitmap bitmap = ImageUtils.decodeBitmapFromUrl(photoPath, 640, 400);
        int imgWidth = bitmap.getWidth();
        int imgHeight = bitmap.getHeight();
        Log.d(getClass().getSimpleName(), "width = " + imgWidth + "| height = " + imgHeight);
        float widthScale = 640.0f / imgWidth;
        float heightScale = 400.0f / imgHeight;
        Log.d(getClass().getSimpleName(), "widthScale = " + widthScale + "| heightScale = " + heightScale);
        Matrix matrix = new Matrix();
        if (imgWidth < imgHeight) {//竖
            matrix.setScale(widthScale, widthScale);
        } else {
            matrix.setScale(heightScale, heightScale);
        }

        Bitmap matrixBitmap = Bitmap.createBitmap(bitmap, 0, 0, imgWidth, imgHeight, matrix, true);
        if (imgWidth < imgHeight) {
            return ImageUtils.rotateImage(matrixBitmap, 90);
        }
        return matrixBitmap;
    }

    public ArrayList<String> getJustSelectedPhotoPaths() {
        return mJustSelectedPhotoPaths;
    }

    public ArrayList<String> getBackSelectedPhotoPaths() {
        return mBackSelectedPhotoPaths;
    }
}
