package com.sisyphean.practice.net.interceptor;

import android.text.TextUtils;
import android.util.Log;

import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.common.Constant;
import com.sisyphean.practice.utils.GsonUtil;
import com.sisyphean.practice.utils.SPUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        RequestBody body = request.body();

        if (isPostForm(request)) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            String token = getToken();
            if (!TextUtils.isEmpty(token)) {
                formBodyBuilder.add("token", token);
            }

            FormBody formBody = formBodyBuilder.build();
            String postBodyToString = bodyToString(body);
            postBodyToString += (postBodyToString != null && postBodyToString.length() > 0 ? "&" : "") + bodyToString(formBody);
            requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyToString));
        }

        return chain.proceed(requestBuilder.build());
    }

    private String bodyToString(RequestBody body) {
        try {
            Buffer buffer = new Buffer();
            if (body != null) {
                body.writeTo(buffer);
                return buffer.readUtf8();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isPostForm(Request request) {
        if (request == null) {
            return false;
        }

        String method = request.method();
        if (!TextUtils.equals(method, "POST")) {
            return false;
        }

        RequestBody body = request.body();
        if (body == null) {
            return false;
        }

        MediaType mediaType = body.contentType();
        if (mediaType == null) {
            return false;
        }

        String subtype = mediaType.subtype();
        if (!TextUtils.equals(subtype, "x-www-form-urlencoded")) {
            return false;
        }
        return true;
    }

    private String getToken() {
        Log.d(getClass().getSimpleName(), "getToken is called , curThread = " + Thread.currentThread());
        String loginJson = (String) SPUtil.getValue(Constant.KEY_LOGIN_INFO, "");
        Log.d(getClass().getSimpleName(), "loginJson = " + loginJson);
        if (!TextUtils.isEmpty(loginJson)) {
            UserBean userBean = GsonUtil.fromJson(loginJson, UserBean.class);
            return userBean.getToken();
        }
        return null;
    }
}
