package com.qf.sxy.volleyimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.qf.sxy.volleyimage.cache.ImageCache;

/**
 * ImageLoader下载图片
 * 步骤:
 * 1,创建ImageLoader对象(需要请求队列,缓存对象)
 * 2,创建一个ImageListener监听(通过ImageLoader对象获取)
 * 3,使用ImageLoader对象下载图片(获取图片get(需要ImageListener))
 */
public class Main2Activity extends AppCompatActivity {

    private ImageView ivSHow;
    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ivSHow = ((ImageView) findViewById(R.id.iv_show));
        networkImageView = ((NetworkImageView) findViewById(R.id.networkImageView));

    }

    /**
     * 第三种方式获取View
     */
    public void getNetWorkImageView(){
        ImageLoader imageLoader = new ImageLoader(MyApp.getQueue(),new ImageCache());
        //默认展示的图片
        networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        //下载失败展示的图片
        networkImageView.setErrorImageResId(R.mipmap.ic_launcher);

        //开始下载图片
        networkImageView.setImageUrl("http://img5.imgtn.bdimg.com/it/u=1944571539,3989247234&fm=21&gp=0.jpg",imageLoader);

    }

    /**
     * 使用ImageLoader下载图片
     * @param view
     */
    public void MyImageLoaderClick(View view) {

        //data/data/{包名}/cache/volley
        /**
         * 第一步:获取对象
         * 参数1:请求队列
         * 参数2:缓存对象
         */
        ImageLoader imageLoader = new ImageLoader(MyApp.getQueue(),new ImageCache());
        /**
         * 2,
         * 参数1:图片展示到那个控件上
         * 参数2:默认展示的图片
         * 参数3:下载失败展示的图片
         */
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(ivSHow,R.mipmap.ic_launcher,R.mipmap.ic_launcher);

        /**
         * 3,使用ImageLoader下载图片
         * 参数1:地址
         * 参数2:ImageListener对象
         */
        imageLoader.get("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1477897375&di=5863aa6efd245753ff6c0bd57ef14233&src=http://file.ynet.com/2/1610/25/11893084.jpg"
                ,imageListener);

    }


    /**
     * 第三种请求图片的方式
     *
     *步骤:
     * 1,把NetWorkIamgeView以控件形式添加到布局文件中
     * 2,找出该控件.使用该控件获取图片(setImageUrl(地址,ImageLoader))
     * @param view
     */
    public void MyNetworkImageClick(View view) {
        getNetWorkImageView();
    }
}
