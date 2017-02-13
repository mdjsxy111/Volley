package com.qf.sxy.day31_volley.customrequest;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by sxy on 2016/10/31.
 */
public class GsonRequest<T> extends Request<T> {

    private Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClazz;

    public GsonRequest(String url,Class<T> mClazz,Response.Listener<T> listener, Response.ErrorListener errorlistener) {
        super(url, errorlistener);
        this.mListener = listener;
        mGson = new Gson();
        this.mClazz = mClazz;
    }

    public GsonRequest(int method, Class<T> mClazz,String url,Response.Listener<T> listener, Response.ErrorListener errorlistener) {
        super(method, url, errorlistener);
        this.mListener = listener;
        mGson = new Gson();
        this.mClazz = mClazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        //处理数据
        try {
            String  jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return Response.success(mGson.fromJson(jsonStr,mClazz),HttpHeaderParser.parseCacheHeaders(response)) ;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void deliverResponse(T response) {
        //将数据传递回去
         mListener.onResponse(response);
    }
}
