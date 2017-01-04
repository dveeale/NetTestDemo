package com.dveeale.nettest.listener;

/**
 * Created by dveeale on 16/12/28.
 */

public interface NetWorkCallBackListener<T> {

    void onSuccess(T result,int curCount);
    void onFailure(T result,int curCount);
    void onEnd(T result);
    //void onLoadNetData();
}
