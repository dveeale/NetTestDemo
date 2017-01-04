package com.dveeale.nettest.netpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.dveeale.nettest.R;

public class NetPageActivity extends AppCompatActivity {

  private NetPagePresonter mNetPagePresonter;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_net_page);

    mNetPagePresonter=new NetPagePresonter(this,new NetPageView());
    mNetPagePresonter.start();
  }
}
