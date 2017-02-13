package com.qf.sxy.day31_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.qf.sxy.day31_volley.bean.QsTextBean;
import com.qf.sxy.day31_volley.customrequest.GsonRequest;
import com.qf.sxy.day31_volley.customrequest.QsTextRequest;
import com.qf.sxy.day31_volley.ui.Uri;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void MyCustomClick(View view) {
        QsTextRequest request = new QsTextRequest(String.format(Uri.URL_TEXT,2), new Response.Listener<QsTextBean>() {
            @Override
            public void onResponse(QsTextBean response) {
                Log.e("AAA","==>"+response.getPage()+response.getItems().get(0).getContent());
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getQueue().cancelAll("cancle");
    }

    //是自定义的请求
    public void MyCustomClick2(View view) {

        GsonRequest<QsTextBean> request = new GsonRequest<QsTextBean>(
                String.format(Uri.URL_TEXT, 2),
                QsTextBean.class,
                new Response.Listener<QsTextBean>() {
                    @Override
                    public void onResponse(QsTextBean response) {
                        Log.e("AAA","=AAAA=>"+response.getPage()+response.getItems().get(2).getContent());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("AA","=AAA=>"+error.getMessage());
                    }
                }
        );

        request.setTag("cancle");
        MyApp.getQueue().add(request);
    }


}
