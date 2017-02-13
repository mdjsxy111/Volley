package com.qf.sxy.volleydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by sxy on 2016/10/31.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private List<T> datas;
    private Context context;
    private LayoutInflater inflater;

    /**
     * 构造方法
     * 获取数据,上下文对象
     */
    public MyBaseAdapter(List<T> datas, Context context){
        this.datas = datas;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas!=null?datas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position,convertView,parent);
    }

    /**
     * 给getView()方法
     * 需要子类实现
     * @return
     */
    public abstract View getItemView(int position, View convertView, ViewGroup parent);

    /**
     * 提供一个方法给子类调用  实现LayoutInflater
     * @return
     */
    public LayoutInflater getLaytoutInflater(){
        return inflater;
    }

    /**
     * 添加数据的方法
     * @param list
     */
    public void addAllDatas(List<T> list){
        datas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 清除数据
     */
    public void clearDatas(){
        datas.clear();
        notifyDataSetChanged();
    }
}
