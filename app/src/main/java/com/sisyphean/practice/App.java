package com.sisyphean.practice;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sisyphean.practice.net.RetrofitClient;
import com.sisyphean.practice.widget.MaterialHeader;

public class App extends Application {

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary);
                return new MaterialHeader(context);
            }
        });
    }

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

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        Log.d(getClass().getSimpleName(), "heap size = " + memoryClass);
    }


}
