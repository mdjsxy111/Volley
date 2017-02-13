package com.qf.sxy.volleyimage.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by sxy on 2016/10/31.
 */
public class ImageCache implements ImageLoader.ImageCache {


    //声明缓存对象
    private LruCache<String,Bitmap> lruCache;
    public ImageCache(){
        int max = (int) (Runtime.getRuntime().maxMemory()/8);
        lruCache = new LruCache<String, Bitmap>(max){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

    }

    /**
     * 取图片的方法
     * @param url
     * @return
     */
    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    /**
     * 存图片的方法
     * @param url
     * @param bitmap
     */
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url,bitmap);
    }
}
