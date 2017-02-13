package com.qf.sxy.day31_volley.customrequest;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by sxy on 2016/10/31.
 */
public class XmlRequest extends Request<XmlPullParser> {

    //请求成功后的回调
    private Response.Listener<XmlPullParser> mListener;


    public XmlRequest(String url,Response.Listener<XmlPullParser> listener, Response.ErrorListener errorlistener) {
        super(url, errorlistener);
        this.mListener = listener;
    }

    public XmlRequest(int method, String url,Response.Listener<XmlPullParser> listener, Response.ErrorListener errorlistener) {
        super(method, url, errorlistener);
        this.mListener = listener;
    }

    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {
        try {
            String xmlStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            //获取解析工厂
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //通过解析工厂  得到解析器
            XmlPullParser xmlPullParser = factory.newPullParser();
            //将解析数据放入到解析器中
            xmlPullParser.setInput(new StringReader(xmlStr));

            return Response.success(xmlPullParser,HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void deliverResponse(XmlPullParser response) {
        mListener.onResponse(response);
    }
}
