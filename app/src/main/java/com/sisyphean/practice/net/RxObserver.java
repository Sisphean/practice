package com.sisyphean.practice.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.sisyphean.practice.bean.ResponseBean;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class RxObserver<T> extends DisposableObserver<ResponseBean<T>> {

    private Context context;

    public RxObserver(Context context) {
        this.context = context;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(ResponseBean<T> response) {
        Log.d(getClass().getSimpleName(), "ThreadName: " + Thread.currentThread() + " | response: " + response.toString());
        if (response == null) {
            onError(new UnknownError());
        } else {
            responseHandle(response);
        }
    }

    @Override
    public void onError(Throwable e) {
        exceptionHandle(e);
    }

    @Override
    public void onComplete() {

    }


    private void responseHandle(ResponseBean<T> response) {
        switch (response.getStatus()) {
            case 1:
                onSuccess(response.getInfo());
                break;
            default:
                String errorMsg = response.getErrorMsg();
                onFail(response.getStatus(), errorMsg);
                break;
        }
    }

    private void exceptionHandle(Throwable e) {
        Log.d(getClass().getSimpleName(), "error message : " + e.toString());
        if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            onFail(-1, "网络连接异常");
        } else if (e instanceof InterruptedException) {
            onFail(-2, "连接超时");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof MalformedJsonException) {
            onFail(-3, "网络数据解析失败");
        } else if (e instanceof SocketTimeoutException) {
            onFail(-4, "请求超时");
        } else if (e instanceof UnknownError) {
            onFail(-5, "未知错误");
        } else {
            onFail(-5, "未知错误");
        }


    }

    protected abstract void onSuccess(T data);

    protected abstract void onFail(int errorCode, String errorMsg);

}
