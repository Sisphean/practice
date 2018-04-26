package com.sisyphean.practice;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.sisyphean.practice.net.RetrofitClient;

public class App extends Application {

    private static App context;

    private static class AppHolder{
        private static App INSTANCE = context;
    }

    public static Context getContext(){
        return AppHolder.INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        RetrofitClient.getInstance();
    }


}
