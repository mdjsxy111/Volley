package com.qf.sxy.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.qf.sxy.volleydemo.adapter.MyAdapter;
import com.qf.sxy.volleydemo.bean.QsTextBean;
import com.qf.sxy.volleydemo.uri.Uri;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = ((ListView) findViewById(R.id.lv));
        //获取Adapter对象
        adapter = new MyAdapter(new ArrayList<QsTextBean.ItemsBean>(),this);
        //设置adapter给lv
        lv.setAdapter(adapter);
        loadData();
    }

    /**
     * 下载数据
     */
    private void loadData() {
        StringRequest request = new StringRequest(String.format(Uri.URL_TEXT,1), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                QsTextBean qsTextBean = gson.fromJson(response, QsTextBean.class);
                //掉用父类方法  获取数据
                adapter.addAllDatas(qsTextBean.getItems());

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
