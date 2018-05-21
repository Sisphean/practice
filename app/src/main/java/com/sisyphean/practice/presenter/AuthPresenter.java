package com.sisyphean.practice.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.huantansheng.easyphotos.EasyPhotos;
import com.sisyphean.practice.bean.AuthBean;
import com.sisyphean.practice.imageloader.ImageUtils;
import com.sisyphean.practice.model.impl.AuthModel;
import com.sisyphean.practice.net.RxObserver;
import com.sisyphean.practice.ui.activity.user.AuthActivity;
import com.sisyphean.practice.view.user.IAuthView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;

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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            ArrayList<String> photosPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);

            if (photosPaths.size() > 0) {
                String photoPath = photosPaths.get(0);
                Bitmap bitmap = createBitmap(photosPaths);
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

    private Bitmap createBitmap(List<String> photosPaths) {
        mCompositeDisposable.add(
                Flowable.just(photosPaths)
                .subscribeOn(Schedulers.io())
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(List<String> list) throws Exception {
                        return Luban.with(getView().getContext())
                                .load(list)
                                .get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(List<File> files) throws Exception {
                        File file = files.get(0);
                        String filePath = file.getAbsolutePath();
                        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    }
                })
        );
        /*Bitmap bitmap = ImageUtils.decodeBitmapFromUrl(photoPath, 640, 260);
        int imgWidth = bitmap.getWidth();
        int imgHeight = bitmap.getHeight();
        if (imgWidth < imgHeight) {
           return ImageUtils.rotateImage(bitmap, 90);
        }
        return BitmapFactory.decodeFile(photoPath);*/
        return null;
    }

    public ArrayList<String> getJustSelectedPhotoPaths() {
        return mJustSelectedPhotoPaths;
    }

    public ArrayList<String> getBackSelectedPhotoPaths() {
        return mBackSelectedPhotoPaths;
    }
}
