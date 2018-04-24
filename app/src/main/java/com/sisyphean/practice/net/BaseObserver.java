package com.sisyphean.practice.net;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.sisyphean.practice.bean.ResponseBean;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseObserver<T> implements Observer<ResponseBean<T>> {

    private Context context;
    private ResponseHandleCallback<T> callback;

    public BaseObserver(Context context, ResponseHandleCallback<T> callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseBean<T> response) {
        responseHandle(response, callback);
    }

    @Override
    public void onError(Throwable e) {
        exceptionHandle(e);
    }

    @Override
    public void onComplete() {

    }


    private boolean responseHandle(ResponseBean<T> response, ResponseHandleCallback<T> callback) {
        if (response == null) {
            Toast.makeText(context, "response is null", Toast.LENGTH_SHORT).show();
            return false;
        }

        switch (response.getErrorCode()) {
            case 0:
                callback.onSuccess(response.getData());
                return true;
            default:
                callback.onFail(response.getErrorCode(), response.getErrorMsg());
                return false;
        }
    }

    private void exceptionHandle(Throwable e) {
        if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            Toast.makeText(context, "网络连接异常", Toast.LENGTH_SHORT).show();
        } else if (e instanceof InterruptedException) {
            Toast.makeText(context, "连接超时", Toast.LENGTH_SHORT).show();
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            Toast.makeText(context, "网络数据解析失败", Toast.LENGTH_SHORT).show();
        } else if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "请求超时", Toast.LENGTH_SHORT).show();
        } else if (e instanceof UnknownError) {
            Toast.makeText(context, "未知错误", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "未知错误", Toast.LENGTH_SHORT).show();
        }

    }

    public interface ResponseHandleCallback<T> {
        void onSuccess(T data);

        void onFail(int errorCode, String errorMsg);
    }

}
