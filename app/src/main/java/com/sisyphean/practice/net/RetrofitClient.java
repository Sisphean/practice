package com.sisyphean.practice.net;

import com.sisyphean.practice.common.URLContainer;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Api api;

    private RetrofitClient() {
        initRetrofit();
    }

    private static class RetrofitClientHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return RetrofitClientHolder.INSTANCE;
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLContainer.BASE_URL)
                .client(initOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    private OkHttpClient initOkHttpClient() {
        return new OkHttpClient.Builder()
//                .addInterceptor()
                .build();
    }

    public static Api getApi() {
        if (api == null) {
            throw new IllegalStateException("在application中初始化RetrofitClient");
        }

        return api;
    }
}
