package com.qf.sxy.day31_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    private String path="http://218.244.149.129:9010/api/companylist.php?";
    //Post请求参数是：industryid=99&count=10&type=100


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    //Post请求
    public void MyPostClick(View view) {

        /**
         * 参数1:请求模式
         * 参数2:请求地址
         * 参数3:请求成功后的回调
         * 参数4:请求失败的回调
         * getParams():添加请求参数的方法
         */
        StringRequest request = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("AAA","==>"+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AAA","==>"+error.getMessage());
            }
        }){
            //添加Post请求参数的方法
            //industryid=99&count=10&type=100
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("industryid","99");
                map.put("count","10");
                map.put("type","100");
                return map;
            }
        };

        request.setTag("cancle");
        MyApp.getQueue().add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getQueue().cancelAll("cancle");
    }
}
