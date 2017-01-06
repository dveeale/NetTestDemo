package com.dveeale.nettest.retrofit2.baidu;

import com.dveeale.nettest.retrofit2.news.NewItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dveeale on 17/1/6.
 */

public interface BaiduService {
  //http://apis.baidu.com/apistore/mobilenumber/mobilenumber?apikey=8e13586b86e4b7f3758ba3bd6c9c9135&phone=13521582866
  @GET("apistore/mobilenumber/mobilenumber")
  Call<BaiduItem> getKeyResult(@Query("apikey") String apikey,
                             @Query("phone") String phone);
}
