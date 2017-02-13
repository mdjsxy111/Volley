package com.qf.sxy.volleyimage;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sxy on 2016/10/31.
 */
public class MyApp extends Application {

    public static RequestQueue queue;
    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getQueue(){
        return queue;
    }
}
