package com.dveeale.nettest.retrofit2;

import com.dveeale.nettest.retrofit2.baidu.BaiduService;
import com.dveeale.nettest.retrofit2.news.NewsService;
import com.dveeale.nettest.retrofit2.yuedan.YundanService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dveeale on 17/1/4.
 */

public class NetWorkApi {
  private NewsService mNewsService;
  private BaiduService mBaiduService;
  private YundanService mYundanService;

  public static NetWorkApi getApi(){
    return SingleHolder.newsApi;
  }

  private static class SingleHolder{
    public static NetWorkApi newsApi=new NetWorkApi();
  }

  public NewsService getNewsService(){
    return mNewsService;
  }

  public BaiduService getBaiduService(){
    return mBaiduService;
  }

  public YundanService getmYundanService(){
    return mYundanService;
  }

  private NetWorkApi(){
    initBaiduApi();
    initNewsApi();
    initYundanApi();
  }

  private void initNewsApi(){
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://newswifiapi.dfshurufa.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    mNewsService = retrofit.create(NewsService.class);
  }

  private void initBaiduApi(){
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://apis.baidu.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    mBaiduService = retrofit.create(BaiduService.class);
  }

  private void initYundanApi(){
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://android.api.iyuedan.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    mYundanService = retrofit.create(YundanService.class);

  }
}
