package com.dveeale.nettest.retrofit.netpage;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dveeale on 17/1/4.
 */

public class NetPageApi {
  private NetPageService service;

  public static NetPageApi getApi(){
    return SingleHolder.newsApi;
  }

  private static class SingleHolder{
    public static NetPageApi newsApi=new NetPageApi();
  }

  public NetPageService getService(){
    return service;
  }

  private NetPageApi(){
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://newswifiapi.dfshurufa.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    service = retrofit.create(NetPageService.class);
  }
}
