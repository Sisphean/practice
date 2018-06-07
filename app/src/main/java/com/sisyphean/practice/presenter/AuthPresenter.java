package com.sisyphean.practice.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.huantansheng.easyphotos.EasyPhotos;
import com.sisyphean.practice.R;
import com.sisyphean.practice.bean.AuthBean;
import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UploadBean;
import com.sisyphean.practice.imageloader.ImageUtils;
import com.sisyphean.practice.model.impl.AuthModel;
import com.sisyphean.practice.model.impl.UploadModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.ui.activity.user.AuthActivity;
import com.sisyphean.practice.utils.StorageUtil;
import com.sisyphean.practice.view.user.IAuthView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AuthPresenter extends BasePresenter<IAuthView> {


    private AuthModel authModel;
    public ArrayList<String> mJustSelectedPhotoPaths = new ArrayList<>();
    public ArrayList<String> mBackSelectedPhotoPaths = new ArrayList<>();
    private MultipartBody.Part justImgPart;
    private MultipartBody.Part backImgPart;

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
                        getView().hideSubmit(AuthBean.AUTH_STATUS_AUTHING);
                        getView().setTrueName(data.getTrueName());
                        getView().setIDCard(data.getIdCardNum());
                        break;
                    case AuthBean.AUTH_STATUS_SUNCCESS:
                        getView().hideSubmit(AuthBean.AUTH_STATUS_SUNCCESS);
                        getView().setTrueName(data.getTrueName());
                        getView().setIDCard(data.getIdCardNum());
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

        if (justImgPart != null && backImgPart != null) {
            Observable<ResponseBean<UploadBean>> justObservable = authModel.uploadFile(UploadModel.CONTROLLER_IDENTITY, justImgPart);
            Observable<ResponseBean<UploadBean>> backObservable = authModel.uploadFile(UploadModel.CONTROLLER_IDENTITY, backImgPart);
            mCompositeDisposable.add(
                    Observable.zip(justObservable, backObservable, new BiFunction<ResponseBean<UploadBean>, ResponseBean<UploadBean>, List<String>>() {
                        @Override
                        public List<String> apply(ResponseBean<UploadBean> justUpload, ResponseBean<UploadBean> backUpload) throws Exception {
                            Log.d("auth", "justimg : " + justUpload.getInfo().getUrl() + " | backimg : " + backUpload.getInfo().getUrl());
                            List<String> imgUrls = new ArrayList<>();
                            imgUrls.add(justUpload.getInfo().getUrl());
                            imgUrls.add(backUpload.getInfo().getUrl());
                            return imgUrls;
                        }
                    })
                            .flatMap(new Function<List<String>, ObservableSource<ResponseBean<String>>>() {
                                @Override
                                public ObservableSource<ResponseBean<String>> apply(List<String> list) throws Exception {
                                    return authModel.userAuthenticate(getView().getTrueName(),
                                            getView().getIDCrad(),
                                            list.get(0),
                                            list.get(1));
                                }
                            })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new RxObserver<String>(getView().getContext()) {
                                @Override
                                protected void onStart() {
                                    super.onStart();
                                    getView().showLoading("身份认证提交中...");
                                }

                                @Override
                                protected void onSuccess(String data) {
                                    getView().hideLoading();
                                    getView().showToast(data);
                                    getView().hideSubmit(AuthBean.AUTH_STATUS_AUTHING);
                                }

                                @Override
                                protected void onFail(int errorCode, String errorMsg) {
                                    getView().hideLoading();
                                    getView().showToast(errorMsg);
                                }
                            })
            );
        }
    }

    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            final ArrayList<String> photosPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);

            if (photosPaths.size() > 0) {

                final String photoPath = photosPaths.get(0);
                mCompositeDisposable.add(
                        Observable.just(photoPath)
                                .map(new Function<String, Bitmap>() {
                                    @Override
                                    public Bitmap apply(String path) throws Exception {
                                        return createBitmap(path);
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnNext(new Consumer<Bitmap>() {
                                    @Override
                                    public void accept(Bitmap bitmap) throws Exception {
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

                                })
                                .observeOn(Schedulers.io())
                                .doOnNext(new Consumer<Bitmap>() {
                                    @Override
                                    public void accept(Bitmap bitmap) throws Exception {
                                        createImageBody(requestCode, bitmap, photoPath.substring(photoPath.lastIndexOf("/") + 1));
                                    }
                                })
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableObserver<Bitmap>() {
                                    @Override
                                    public void onNext(Bitmap bitmap) {

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

    private void createImageBody(int requestCode, Bitmap bitmap, String photoPath) {
        File file = ImageUtils.saveBitmapFile(bitmap, photoPath);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("img", file.getName(), imageBody);
        if (requestCode == AuthActivity.REQUESTCODE_JUST) {
            justImgPart = imageBodyPart;
        } else if (requestCode == AuthActivity.REQUESTCODE_BACK) {
            backImgPart = imageBodyPart;
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
