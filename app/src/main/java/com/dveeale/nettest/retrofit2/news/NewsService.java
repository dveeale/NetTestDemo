package com.dveeale.nettest.retrofit2.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by dveeale on 17/1/4.
 */

public interface NewsService {
  @GET("") Call<String> getNetwork(@Url String url);

  @GET("/jsonnew/refresh")
  Call<NewItem> getRefreshResult(@Query("type") String type,
      @Query("endkey") String endkey,
      @Query("qid") String qid);

  //@GET("/jsonnew/next")
  //Call<NewsResult> getNextResult(@Query("type") String type,
  //    @Query("startkey") String endkey,
  //    @Query("qid") String qid);

  //@GET("/jsonnew/next")
  //Observable<NewsResult> getNextResultRx(@Query("type") String type,
  //    @Query("startkey") String endkey,
  //    @Query("qid") String qid);
}