package com.qf.sxy.day31_volley.customrequest;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.qf.sxy.day31_volley.bean.QsTextBean;

import java.io.UnsupportedEncodingException;

/**
 * Created by sxy on 2016/10/31.
 * <p/>
 * parseNetworkResponse:   相当于doinBackground()
 * deliverResponse  : 相当于  onpostExcete()
 *
 * 自定义一个Volley请求
 * 1,创建一个类 继承 Request<T>
 * 2,实现方法
 *  parseNetworkResponse:   相当于doinBackground() 操作数据
 * deliverResponse  : 相当于  onpostExcete()  将数据传递回来
 *
 *  构造方法:自己添加请求成功后的回调
 */
public class QsTextRequest extends Request<QsTextBean> {

    //请求成功后的回调
    private Response.Listener<QsTextBean> listener;

    public QsTextRequest(String url, Response.Listener<QsTextBean> listener, Response.ErrorListener errorlistener) {
        super(url, errorlistener);
        this.listener = listener;
    }

    public QsTextRequest(int method, String url, Response.Listener<QsTextBean> listener, Response.ErrorListener errorlistener) {
        super(method, url, errorlistener);
        this.listener = listener;
    }

    @Override
    protected Response<QsTextBean> parseNetworkResponse(NetworkResponse response) {

        String str = null;
        try {
            str = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        /**
         * 数据是{}
         */
        QsTextBean qsTextBean = gson.fromJson(str, QsTextBean.class);
        /**
         * 请求数据是[]的
         */
        // List<QsTextBean> list =  gson.fromJson(str,new TypeToken<List<QsTextBean>>(){}.getType());
//
        //FastJson解析
        /**
         * 数据是{}
         */
//        byte[] data =response.data;
//        try {
//            String str = new String(data, HttpHeaderParser.parseCharset(response.headers));
//            QsTextBean qsTextBean1 = JSON.parseObject(str,QsTextBean.class);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        /**
         * 数据是[]
         */
       // List<QsTextBean>list =  JSON.parseArray(str,QsTextBean.class);


        //操做数据
        return Response.success(qsTextBean, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(QsTextBean response) {
        //将数据回传回去
        this.listener.onResponse(response);
    }
}
