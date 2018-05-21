package com.sisyphean.practice.net.interceptor;

import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class CommonParamsInterceptor implements Interceptor {

    private Map<String, String> headerParams = new HashMap<>();
    private Map<String, String> urlParams = new HashMap<>();
    private Map<String, String> postParams = new HashMap<>();

    private CommonParamsInterceptor() {

    }

    public static class Builder {
        CommonParamsInterceptor commonParamsInterceptor;

        public Builder() {
            commonParamsInterceptor = new CommonParamsInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            commonParamsInterceptor.headerParams.put(key, value);
            return this;
        }

        public Builder addHeaderParams(Map<String, String> paramsMap) {
            commonParamsInterceptor.headerParams.putAll(paramsMap);
            return this;
        }

        public Builder addUrlParams(String key, String value) {
            commonParamsInterceptor.urlParams.put(key, value);
            return this;
        }

        public Builder addUrlParams(Map<String, String> paramsMap) {
            commonParamsInterceptor.urlParams.putAll(paramsMap);
            return this;
        }

        public Builder addPostParams(String key, String value) {
            commonParamsInterceptor.postParams.put(key, value);
            return this;
        }

        public Builder addPostParams(Map<String, String> paramsMap) {
            commonParamsInterceptor.postParams.putAll(paramsMap);
            return this;
        }


        public CommonParamsInterceptor build() {
            return commonParamsInterceptor;
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        Headers.Builder headerBuilder = request.headers().newBuilder();

        if (headerParams.size() > 0) {
            for (String key : headerParams.keySet()) {
                String value = headerParams.get(key);
                headerBuilder.add(key, value);
            }
        }

        if (urlParams.size() > 0) {
            request = injectParamsToURL(request.url().newBuilder(), requestBuilder, urlParams);
        }

        if (postParams.size() > 0) {
            if (isPostForm(request)) {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                for (String key : postParams.keySet()) {
                    String value = postParams.get(key);
                    if (!TextUtils.isEmpty(value))
                        formBodyBuilder.add(key, value);
                }

                FormBody formBody = formBodyBuilder.build();
                String postBodyToString = bodyToString(request.body());
                postBodyToString += ((!TextUtils.isEmpty(postBodyToString) && postBodyToString.length() > 0) ? "&" : "") + bodyToString(formBody);
                requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyToString));

            }
        }
        request = requestBuilder.build();

        return chain.proceed(request);
    }

    private String bodyToString(RequestBody body) {

        try {
            Buffer buffer = new Buffer();
            if (body != null) {
                body.writeTo(buffer);
            } else {
                return "";
            }

            return buffer.readUtf8();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void injectParamsToBody(Request.Builder requestBuilder, Map<String, String> postParams) {

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

    private Request injectParamsToURL(HttpUrl.Builder httpUrlBuilder, Request.Builder requestBuilder, Map<String, String> urlParams) {
        for (String key : urlParams.keySet()) {
            String value = urlParams.get(key);
            httpUrlBuilder.addQueryParameter(key, value);
        }
        Request request = requestBuilder.url(httpUrlBuilder.build()).build();
        return request;
    }
}
