package com.qf.sxy.volleydemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.qf.sxy.volleydemo.MyApp;
import com.qf.sxy.volleydemo.R;
import com.qf.sxy.volleydemo.bean.QsTextBean;
import com.qf.sxy.volleydemo.uri.Uri;

import java.util.List;

/**
 * Created by sxy on 2016/10/31.
 */
public class MyAdapter extends MyBaseAdapter<QsTextBean.ItemsBean> {

    private ImageLoader imageLoader;
    /**
     * 构造方法
     * 获取数据,上下文对象
     *
     * @param datas
     * @param context
     */
    public MyAdapter(List<QsTextBean.ItemsBean> datas, Context context) {
        super(datas, context);
        imageLoader = new ImageLoader(MyApp.getQueue(), new ImageLoader.ImageCache() {
            private LruCache<String,Bitmap> lruCache = new LruCache<String, Bitmap>(10*1024*1024){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };
            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url,bitmap);

            }
        });
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            convertView = getLaytoutInflater().inflate(R.layout.item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //获取数据
        QsTextBean.ItemsBean itemsBean = (QsTextBean.ItemsBean) getItem(position);
        //设置用户名称
        if(itemsBean.getUser()!=null&&itemsBean.getUser().getLogin()!=null){
            viewHolder.tv.setText(itemsBean.getUser().getLogin());
        }

        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(viewHolder.iv,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        //下载图片
        if(itemsBean.getUser()!=null&&itemsBean.getUser().getIcon()!=null){

            //拼接图片地址
            String iconPath = String.format(Uri.URL_USER_ICON,
                    itemsBean.getUser().getId()/10000,
                    itemsBean.getUser().getId(),
                    itemsBean.getUser().getIcon()
                    );


            //下载图片
            imageLoader.get(iconPath,imageListener);


        }


        return convertView;
    }

    class ViewHolder{
        TextView tv;
        ImageView iv;
        public ViewHolder(View convertView){
            tv = (TextView) convertView.findViewById(R.id.item_tv);
            iv = (ImageView)convertView.findViewById(R.id.item_iv);

        }
    }


}
