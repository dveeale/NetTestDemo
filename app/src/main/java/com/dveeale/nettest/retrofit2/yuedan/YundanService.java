package com.dveeale.nettest.retrofit2.yuedan;

import com.dveeale.nettest.retrofit2.news.NewItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dveeale on 17/1/6.
 */

public interface YundanService {
  //http://android.api.iyuedan.com/api1.0/nearby/list?
  @GET("/api1.0/nearby/list") Call<YundanItem> getRefreshResult();
}
