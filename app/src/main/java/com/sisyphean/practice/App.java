package com.sisyphean.practice;

import android.app.Application;

import com.sisyphean.practice.net.RetrofitClient;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RetrofitClient.getInstance();
    }
}
