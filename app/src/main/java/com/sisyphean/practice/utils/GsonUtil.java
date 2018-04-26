package com.sisyphean.practice.utils;

import com.google.gson.Gson;

public class GsonUtil {

    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T extends Object> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }
}
