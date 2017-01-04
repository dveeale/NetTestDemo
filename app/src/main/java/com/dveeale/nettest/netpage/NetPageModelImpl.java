package com.dveeale.nettest.netpage;

import com.dveeale.nettest.listener.NetWorkCallBackListener;
import com.dveeale.nettest.retrofit.netpage.NetPageApi;
import com.dveeale.nettest.retrofit.netpage.NetPageService;
import com.dveeale.nettest.retrofit.netpage.NewItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dveeale on 17/1/4.
 */

public class NetPageModelImpl {
  private NetPageApi mNetPageApi;
  private NetPageService mNetPageService;
  private NetWorkCallBackListener<Long> mListener;

  private Long mStartTime;//每次访问网络开始时间
  private int mTotalTime;

  private int mCurCount;
  private int mTotalCount;


  public NetPageModelImpl(NetWorkCallBackListener<Long>  mListener){
    this.mListener=mListener;
    mNetPageApi=NetPageApi.getApi();
    mNetPageService=mNetPageApi.getService();

  }

  public void AskNewsData(){
    mStartTime=System.currentTimeMillis();
    Call<NewItem> call = mNetPageService.getRefreshResult("toutiao","","baisi");
    call.enqueue(new Callback<NewItem>() {
      @Override public void onResponse(Call<NewItem> call, Response<NewItem> response) {
        mListener.onSuccess((System.currentTimeMillis()-mStartTime),mCurCount);
        mTotalTime+=(System.currentTimeMillis()-mStartTime);
        askAgain();
      }

      @Override public void onFailure(Call<NewItem> call, Throwable t) {
        mListener.onFailure((System.currentTimeMillis()-mStartTime),mCurCount);
        mTotalTime+=(System.currentTimeMillis()-mStartTime);
        askAgain();
      }
    });
  }

  private void askAgain(){
    if(mCurCount<mTotalCount){
      mCurCount++;
      AskNewsData();
    }else{
      mListener.onEnd((long) mTotalTime);
    }
  }

  public void AskNetWork(int totalCount){
    mTotalCount=totalCount;
    mCurCount=1;
    mTotalTime=0;
    AskNewsData();


  }
}
