package com.dveeale.nettest.netpage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.dveeale.nettest.base.BasePresenter;
import com.dveeale.nettest.base.BaseView;

/**
 * Created by dveeale on 17/1/4.
 */

public interface NetPageContract {
  interface CView extends BaseView<NetPagePresonter> {
    void initViews(Activity mActivity);
    void ShowTips(String msg);
    void UpdateShowTips(String msg);
    void UpdateResultTips(String msg);

  }

  interface Presenter extends BasePresenter {
    void AskNetWork(String msg,int totalCount);

  }
}
