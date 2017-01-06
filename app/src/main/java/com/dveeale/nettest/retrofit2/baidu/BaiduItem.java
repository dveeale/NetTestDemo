package com.dveeale.nettest.retrofit2.baidu;

/**
 * Created by dveeale on 17/1/6.
 */

public class BaiduItem {

  /**
   * errNum : 300202
   * errMsg : Missing apikey
   */

  private int errNum;
  private String errMsg;

  public int getErrNum() {
    return errNum;
  }

  public void setErrNum(int errNum) {
    this.errNum = errNum;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }
}
