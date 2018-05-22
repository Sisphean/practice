package com.sisyphean.practice.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

import com.huantansheng.easyphotos.EasyPhotos;
import com.sisyphean.practice.bean.AuthBean;
import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UploadBean;
import com.sisyphean.practice.imageloader.ImageUtils;
import com.sisyphean.practice.model.impl.AuthModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.ui.activity.user.AuthActivity;
import com.sisyphean.practice.view.user.IAuthView;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
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
              /*saveBitmapFile(BitmapFactory.decodeResource(getResources(), R.drawable.ic_log_withdraw));
                File file  = StorageUtil.getExternalStorageDir("test.png");

                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("imgfile", file.getName(), imageBody);

                RetrofitClient.getApi().uploadFile(imageBodyPart);*/

        Map<String, RequestBody> imgMap = getImagesMap();

        mCompositeDisposable.add(
                authModel.uploadImages(imgMap)
                        .subscribeWith(new RxObserver<UploadBean>(getView().getContext()) {
                            @Override
                            protected void onStart() {
                                super.onStart();
                            }

                            @Override
                            protected void onSuccess(UploadBean data) {
                                getView().showToast(data.getUrl());
                            }

                            @Override
                            protected void onFail(int errorCode, String errorMsg) {
                                getView().showToast(errorMsg);
                            }
                        })
        );


    }

    private Map<String, RequestBody> getImagesMap() {
        return null;
    }

    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            final ArrayList<String> photosPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);

            if (photosPaths.size() > 0) {

                String photoPath = photosPaths.get(0);
                mCompositeDisposable.add(
                        Observable.just(photoPath)
                                .map(new Function<String, Bitmap>() {
                                    @Override
                                    public Bitmap apply(String s) throws Exception {
                                        return createBitmap(s);
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableObserver<Bitmap>() {
                                    @Override
                                    public void onNext(Bitmap bitmap) {
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

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                })
                );
            }

        }
    }

    private Bitmap createBitmap(String photoPath) {

        int groupWidth = getView().getGroupWidth();
        int groupHeight = getView().getGroupHeight();
        Bitmap bitmap = ImageUtils.decodeBitmapFromUrl(photoPath, groupWidth, groupHeight);
        int imgWidth = bitmap.getWidth();
        int imgHeight = bitmap.getHeight();
        Log.d(getClass().getSimpleName(), "width = " + imgWidth + "| height = " + imgHeight);
        Matrix matrix = new Matrix();
        float scale;
        if (imgWidth < imgHeight) {//竖
            scale = (float) groupHeight / imgWidth;

        } else {
            scale = (float) groupHeight / imgHeight;
        }
        matrix.setScale(scale, scale);
        Bitmap matrixBitmap = Bitmap.createBitmap(bitmap, 0, 0, imgWidth, imgHeight, matrix, true);
        if (imgWidth < imgHeight) {
            Bitmap rotateBitmap = ImageUtils.rotateImage(matrixBitmap, 90);
            ImageUtils.saveBitmapFile(rotateBitmap, photoPath);
            return rotateBitmap;
        }
        ImageUtils.saveBitmapFile(matrixBitmap, photoPath);
        return matrixBitmap;
    }

    public ArrayList<String> getJustSelectedPhotoPaths() {
        return mJustSelectedPhotoPaths;
    }

    public ArrayList<String> getBackSelectedPhotoPaths() {
        return mBackSelectedPhotoPaths;
    }
}
