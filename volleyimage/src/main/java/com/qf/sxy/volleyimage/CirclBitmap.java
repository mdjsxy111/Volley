package com.qf.sxy.volleyimage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.android.volley.toolbox.ImageRequest;

/**
 * Created by sxy on 2016/10/31.
 */
public class CirclBitmap implements ImageRequest.Transformation {
    //转变图片的
    @Override
    public Bitmap transform(Bitmap source, int maxWidth, int maxHeight) {
        int min = source.getWidth()>source.getHeight()?source.getHeight():source.getWidth();
        return getCircleImage(source,min);
    }

    //变圆的方法
    private Bitmap getCircleImage(Bitmap source,int min){

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap target= Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
        /**
         * 获取画布对象
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制一个圆
         */
        canvas.drawCircle(min/2,min/2,min/2,paint);
        /**
         * 获取俩个图层的交集
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //绘制图片
        canvas.drawBitmap(source,0,0,paint);

        return target;
    }

    /**
     * 设置标记
     * @return
     */
    @Override
    public String key() {
        return "circleImage";
    }
}
