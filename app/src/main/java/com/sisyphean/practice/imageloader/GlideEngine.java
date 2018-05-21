package com.sisyphean.practice.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huantansheng.easyphotos.engine.ImageEngine;

public class GlideEngine implements ImageEngine {

    private GlideEngine() {}

    private static class GlideEngineHolder{
        private static GlideEngine INSTANCE = new GlideEngine();
    }

    public static GlideEngine getInstance() {
        return GlideEngineHolder.INSTANCE;
    }

    @Override
    public void loadPhoto(Context context, String photoPath, ImageView imageView) {
        Glide.with(context).asBitmap().load(photoPath).into(imageView);
    }

    @Override
    public void loadGifAsBitmap(Context context, String gifPath, ImageView imageView) {
        Glide.with(context).asBitmap().load(gifPath).into(imageView);
    }

    @Override
    public void loadGif(Context context, String gifPath, ImageView imageView) {
        Glide.with(context).asGif().load(gifPath).into(imageView);
    }

    @Override
    public Bitmap getCacheBitmap(Context context, String path, int width, int height) throws Exception {
        return Glide.with(context).asBitmap().load(path).submit(width, height).get();
    }
}
