package com.dveeale.nettest.retrofit2;

import com.dveeale.nettest.listener.NetWorkCallBackListener;
import com.dveeale.nettest.retrofit2.NetWorkApi;
import com.dveeale.nettest.retrofit2.baidu.BaiduItem;
import com.dveeale.nettest.retrofit2.baidu.BaiduService;
import com.dveeale.nettest.retrofit2.news.NewItem;
import com.dveeale.nettest.retrofit2.news.NewsService;
import com.dveeale.nettest.retrofit2.yuedan.YundanItem;
import com.dveeale.nettest.retrofit2.yuedan.YundanService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dveeale on 17/1/4.
 */

public class NewsModelImpl {
  private NetWorkApi mNetWorkApi;
  private NewsService mNewsService;
  private BaiduService mBaiduService;
  private YundanService mYundanService;
  private NetWorkCallBackListener<Long> mListener;

  private Long mStartTime;//每次访问网络开始时间
  private int mTotalTime;
  private String mNetType;//请求网址类型，百度 东方头条 约单

  private int mCurCount;
  private int mTotalCount;


  public NewsModelImpl(NetWorkCallBackListener<Long>  mListener){
    this.mListener=mListener;
    mNetWorkApi= NetWorkApi.getApi();
    mNewsService=mNetWorkApi.getNewsService();
    mBaiduService=mNetWorkApi.getBaiduService();
    mYundanService=mNetWorkApi.getmYundanService();

  }

  public void AskBaiduData(){
    Call<BaiduItem> call=mBaiduService.getKeyResult("8e13586b86e4b7f3758ba3bd6c9c9135","13521582866");
    call.enqueue(new Callback<BaiduItem>() {
      @Override public void onResponse(Call<BaiduItem> call, Response<BaiduItem> response) {
        CallListener(true);
      }

      @Override public void onFailure(Call<BaiduItem> call, Throwable t) {
        CallListener(false);
      }
    });
  }

  public void AskNewsData(){
    Call<NewItem> call = mNewsService.getRefreshResult("toutiao","","baisi");
    call.enqueue(new Callback<NewItem>() {
      @Override public void onResponse(Call<NewItem> call, Response<NewItem> response) {
        CallListener(true);
      }

      @Override public void onFailure(Call<NewItem> call, Throwable t) {
        CallListener(false);
      }
    });
  }

  public void AskYueDan(){

    Call<YundanItem> call = mYundanService.getRefreshResult();
    call.enqueue(new Callback<YundanItem>() {
      @Override public void onResponse(Call<YundanItem> call, Response<YundanItem> response) {
        CallListener(true);
      }

      @Override public void onFailure(Call<YundanItem> call, Throwable t) {
        CallListener(false);
      }
    });
  }

  private void CallListener(boolean isSuccess){
    if(isSuccess){
      mListener.onSuccess((System.currentTimeMillis()-mStartTime),mCurCount);
    }else{
      mListener.onFailure((System.currentTimeMillis()-mStartTime),mCurCount);
    }
    mTotalTime+=(System.currentTimeMillis()-mStartTime);
    askAgain();
  }

  private void askAgain(){
    if(mCurCount<mTotalCount){
      mCurCount++;
      ChoseType();
    }else{
      mListener.onEnd((long) mTotalTime);
    }
  }

  public void AskNetWork(int totalCount,String type){
    mTotalCount=totalCount;
    mCurCount=1;
    mTotalTime=0;
    mNetType=type;
    ChoseType();
  }

  private void ChoseType(){
    mStartTime=System.currentTimeMillis();
    if(mNetType.equals("百度")){
      AskBaiduData();
    }else if(mNetType.equals("东方头条")){
      AskNewsData();
    }else if(mNetType.equals("约单")){
      AskYueDan();
    }
  }
}
