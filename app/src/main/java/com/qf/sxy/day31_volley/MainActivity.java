package com.qf.sxy.day31_volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qf.sxy.day31_volley.ui.Uri;

/**
 * 优化过的VOlley请求
 * 1,导入Volley依赖
 * 2,创建一个请求队列
 * 3,创建一个请求
 * 4,给请求添加请求标记
 * 5,将请求放入到请求队列
 * 6,Activity销毁时  取消网络请求
 */
public class MainActivity extends AppCompatActivity {

    //private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //String请求
    public void MyStringRequestClick(View view) {
        //获取请求队列
       /// requestQueue = Volley.newRequestQueue(MainActivity.this);

        //获取请求对象
        /**
         * 参数1:请求地址
         * 参数2:请求成功后的回调
         * 参数3:请求失败的回调
         *
         * String.format(Uri.URL_TEXT,1)  将%d替换
         */
        StringRequest stringRequest = new StringRequest(String.format(Uri.URL_TEXT,1), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("AAA","=成功=>"+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AAA","=失败=>"+error.getMessage());

            }
        });

        //设置取消标记
        stringRequest.setTag("cancle");

        //将请求添加到请求队列
       // requestQueue.add(stringRequest);
        MyApp.getQueue().add(stringRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将网络请求取消(保持和Activity联动)
     //   requestQueue.cancelAll("cancle");
        MyApp.getQueue().cancelAll("cancle");
    }
}
