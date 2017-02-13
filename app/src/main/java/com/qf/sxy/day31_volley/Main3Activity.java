package com.qf.sxy.day31_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main3Activity extends AppCompatActivity {
    private String path ="http://mobile.ximalaya.com/m/category_tag_menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    //JsonObject请求
    public void MyJsonObjectClick(View view) {

        /**
         * 参数1:请求地址   请求后的数据{}
         * 参数2:JsonObject对象{购物的商品信息:数量,名称,价格,总金额}  没有的话  传null
         * 参数3:请求成功的回调
         * 参数4:请求失败的回调
         */
        JsonObjectRequest request = new JsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("AAA","==>"+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AAA","==>"+error.getMessage());
            }
        });

        request.setTag("cancle");

        MyApp.getQueue().add(request);

    }

    //JsonArray请求
    public void MyJsonArrayClick(View view) {
        /**
         * note:请求参数 最外层是 []   中括号
         */
        JsonArrayRequest request = new JsonArrayRequest("", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.setTag("cancle");
        MyApp.getQueue().add(request);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getQueue().cancelAll("cancle");
    }
}
