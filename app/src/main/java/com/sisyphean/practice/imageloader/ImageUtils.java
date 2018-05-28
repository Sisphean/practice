package com.sisyphean.practice.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sisyphean.practice.utils.StorageUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {

    public static Bitmap decodeBitmapFromUrl(String photoPath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, options);
        options.inSampleSize = calculateSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(photoPath, options);
    }

    // 计算合适的采样率(当然这里还可以自己定义计算规则)，reqWidth为期望的图片大小，单位是px
    public static int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        Log.i("========", "calculateSampleSize reqWidth:" + reqWidth + ",reqHeight:" + reqHeight);
        int width = options.outWidth;
        int height = options.outHeight;
        Log.i("========", "calculateSampleSize width:" + width + ",height:" + height);
        int inSampleSize = 1;
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        while ((halfWidth / inSampleSize) >= reqWidth && (halfHeight / inSampleSize) >= reqHeight) {
            inSampleSize *= 2;
            Log.i("========", "calculateSampleSize inSampleSize:" + inSampleSize);
        }
        return inSampleSize;
    }

    // 图片旋转指定角度
    public static Bitmap rotateImage(Bitmap image, final int degree) {
        int width = image.getWidth();
        int height = image.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        if (image != null && !image.isRecycled()) {
            Bitmap resizedBitmap = Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
            return resizedBitmap;
        } else {
            return null;
        }
    }

    public static File saveBitmapFile(@Nullable Bitmap bitmap, String fileName){
        if (bitmap == null) return null;
        File file=StorageUtil.getExternalStorageDir("shopImg", fileName);//将要保存图片的路径
        Log.d("file", "file:" + file.getAbsolutePath());
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
