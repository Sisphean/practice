package com.sisyphean.practice.net;

import android.content.Context;
import android.util.Log;
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
import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<ResponseBean<T>> {

    private Context context;

    public BaseObserver(Context context) {
        this.context = context;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(ResponseBean<T> response) {
        Log.d("xxx", "ThreadName: " + Thread.currentThread() + " | response: " + response.toString());
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
        switch (response.getErrorCode()) {
            case 0:
                onSuccess(response.getData());
                break;
            default:
                onFail(response.getErrorCode(), response.getErrorMsg());
                break;
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

    protected abstract void onSuccess(T data);

    protected abstract void onFail(int errorCode, String errorMsg);

}
