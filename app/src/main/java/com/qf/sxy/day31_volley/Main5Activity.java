package com.qf.sxy.day31_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.qf.sxy.day31_volley.customrequest.XmlRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    //自定义的请求方式  请的数据是xml
    public void MyXmlClick(View view) {

        XmlRequest request = new XmlRequest("http://flash.weather.com.cn/wmaps/xml/china.xml", new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser response) {
                try {
                    //获取标签
                    int eventType =  response.getEventType();
                    while(eventType!=XmlPullParser.END_DOCUMENT){
                        switch (eventType){
                            case XmlPullParser.START_TAG:
                                String nodeName = response.getName();
                                if("city".equals(nodeName)){
                                    String value = response.getAttributeValue(0);
                                    String name = response.getAttributeName(0);
                                    Log.e("AAA","==name=>"+name+"=value==>"+value);
                                }
                                break;
                        }
                        //走下一个标签
                        eventType = response.next();

                    }

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AAA","===>"+error.getMessage());
            }
        });

        request.setTag("qx");

        MyApp.getQueue().add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getQueue().cancelAll("qx");
    }
}
