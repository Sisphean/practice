package com.sisyphean.practice.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.sisyphean.practice.App;

import java.util.Map;

public class SPUtil {

    private final static String SP_NAME = "Config";

    public static void putValue(String key, Object value) {
        SharedPreferences sp = App.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }

        editor.apply();
    }

    public static Object getValue(String key, Object defaultValue) {
        SharedPreferences sp = App.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        }

        return null;
    }

    public static void remove(String key) {
        SharedPreferences sp = App.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear() {
        SharedPreferences sp = App.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public static boolean contains(String key) {
        SharedPreferences sp = App.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static Map<String, ?> getAll() {
        SharedPreferences sp = App.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }
}
