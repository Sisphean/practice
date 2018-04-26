package com.sisyphean.practice.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.sisyphean.practice.App;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("file create fail");
            }
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
        Log.d("xxx", file.getAbsolutePath());
        FileOutputStream out = null;
        ObjectOutputStream objout = null;
        try {
            out = new FileOutputStream(file);
            objout = new ObjectOutputStream(out);
            objout.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            quietClosed(out);
            quietClosed(objout);
        }
    }

    public static void get() {

    }

    public static void quietClosed(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
