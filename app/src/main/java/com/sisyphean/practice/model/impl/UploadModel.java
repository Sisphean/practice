package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UploadBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadModel extends BaseModel {

    public Observable<ResponseBean<UploadBean>> uploadFile(MultipartBody.Part file) {
        return doRequest().uploadFile(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseBean<UploadBean>> uploadImages(Map<String, RequestBody> imgMap) {
        return doRequest().uploadImages(imgMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
