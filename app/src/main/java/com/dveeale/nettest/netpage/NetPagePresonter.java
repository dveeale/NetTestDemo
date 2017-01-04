package com.dveeale.nettest.netpage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.dveeale.nettest.listener.NetWorkCallBackListener;

/**
 * Created by dveeale on 17/1/4.
 */

public class NetPagePresonter implements NetPageContract.Presenter,NetWorkCallBackListener<Long> {

  private Activity mActivity;
  private NetPageView mNetPageView;
  private NetPageModelImpl mNetPageModelImpl;

  private int mTotalCount;

  public NetPagePresonter(Activity mActivity,NetPageView mNetPageView){
    this.mActivity=mActivity;
    this.mNetPageView=mNetPageView;
    this.mNetPageView.setPresenter(this);

    mNetPageModelImpl=new NetPageModelImpl(this);
  }

  @Override public void start() {
    mNetPageView.initViews(mActivity);
    mNetPageView.UpdateShowTips("初始化完毕");
  }

  @Override public void AskNetWork(String msg,int totalCount) {
    mNetPageView.UpdateShowTips("retrofit2 准备测试:"+msg);
    mTotalCount=totalCount;
    mNetPageModelImpl.AskNetWork(totalCount);
  }

  @Override
  public void onSuccess(Long result,int curCount) {
    mNetPageView.UpdateShowTips("请求成功，本次耗时"+result+"毫秒");
    mNetPageView.UpdateResultTips("正在测试第"+curCount+"次，请稍后...");
    //mNetPageView.ShowTips("成功耗时："+result);
  }

  @Override public void onFailure(Long msg,int curCount) {
    mNetPageView.UpdateShowTips("请求失败，本次耗时"+msg+"毫秒");
    //mNetPageView.ShowTips("失败耗时："+msg);
  }

  @Override public void onEnd(Long result) {
    mNetPageView.UpdateResultTips("总共请求"+mTotalCount+"次，平均每次耗时"+(result/mTotalCount)+"毫秒");
  }
}
