package com.qf.sxy.volleyimage;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class MainActivity extends AppCompatActivity {

    private ImageView ivShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivShow = ((ImageView) findViewById(R.id.iv_show));
    }

    //使用ImageRequest进行请求数据
    public void MyImageClick(View view) {

        /**
         * 参数1:请求的地址
         * 参数2:请求成功后的回调
         * 参数3,4:图片的宽和高    设置为0  表示不压缩  默认原本的宽和高
         * 参数5:缩放类型 (图片的展示形式)
         * 参数6:图片的解码形式  ARGB_8888[4] ARGB_4444[2] RGB565[2] ALPHA_8[1]
         * 参数7:图片形状的改变
         * 参数8:请求失败或者错误的回调
         */
        ImageRequest request = new ImageRequest(
                "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2507878052,3446525205&fm=58", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                //展示图片到Iv
                ivShow.setImageBitmap(response);

            }
        }, 600, 600, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888,
                new CirclBitmap(),
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        request.setTag("qx");
        MyApp.getQueue().add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getQueue().cancelAll("qx");
    }
}
