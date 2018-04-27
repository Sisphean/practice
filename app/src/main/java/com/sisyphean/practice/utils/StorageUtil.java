package com.sisyphean.practice.utils;

import android.os.Environment;
import android.util.Log;

import com.sisyphean.practice.App;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class StorageUtil {

    /**
     * 判断外部存储是否可读写
     *
     * @return
     */
    public static boolean isExternalStorageEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取外部存储中的文件目录
     *
     * @return
     */
    public static File getExternalStorageDir(String fileName) {
        File file = new File(App.getContext().getExternalFilesDir(null), fileName);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new RuntimeException("file create fail");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 获取内部存储中的文件目录
     *
     * @param
     * @param fileName
     * @return
     */
    public static File getFilesDir(String fileName) {
        File file = new File(App.getContext().getFilesDir(), fileName);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new RuntimeException("file create fail");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void put(String fileName, Object object) {
        File file = null;
        if (isExternalStorageEnable()) {
            file = getExternalStorageDir(fileName);
        } else {
            file = getFilesDir(fileName);
        }
        BufferedSink bufferedSink = null;
        try {
            bufferedSink = Okio.buffer(Okio.sink(file));
            bufferedSink.writeUtf8(GsonUtil.toJson(object));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(bufferedSink);
        }
    }

    public static <T> T get(String fileName, Class<T> classOfT) {
        File file = null;
        if (isExternalStorageEnable()) {
            file = getExternalStorageDir(fileName);
        } else {
            file = getFilesDir(fileName);
        }

        BufferedSource bufferedSource = null;
        try {
            bufferedSource = Okio.buffer(Okio.source(file));
            String json = bufferedSource.readUtf8();
            return GsonUtil.fromJson(json, classOfT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeQuietly(bufferedSource);
        }

    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
