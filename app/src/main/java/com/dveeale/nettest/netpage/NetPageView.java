package com.dveeale.nettest.netpage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.dveeale.nettest.R;
import java.util.ArrayList;
import org.w3c.dom.Text;

/**
 * Created by dveeale on 17/1/4.
 */

public class NetPageView implements NetPageContract.CView {

          private Activity mActivity;
          private NetPagePresonter mPersonter;

          private Spinner mSpinner;
          private Button mStratButton;
          private EditText mNumEditText;
          private LinearLayout mShowLayout;
          private TextView mResultTextView;



          public NetPageView(){

          }

          @Override public void setPresenter(NetPagePresonter presenter) {
            mPersonter=presenter;
          }

          @Override
          public void initViews(Activity mActivity) {
            this.mActivity=mActivity;
            mSpinner=(Spinner)mActivity.findViewById(R.id.NetSpinner);
            mStratButton=(Button)mActivity.findViewById(R.id.srartButton);
            mNumEditText=(EditText)mActivity.findViewById(R.id.NumEditText);
            mShowLayout=(LinearLayout)mActivity.findViewById(R.id.showLayout);
            mResultTextView=(TextView)mActivity.findViewById(R.id.ResultTextView);

            initSpinner(mActivity);
            initStartButton(mActivity);
          }

          @Override public void ShowTips(String msg) {
            Toast.makeText(mActivity,msg,Toast.LENGTH_SHORT).show();
          }

          @Override public void UpdateShowTips(String msg) {
            TextView textView=new TextView(mActivity);
            textView.setText(msg);
            textView.setTextColor(Color.BLUE);
            mShowLayout.addView(textView);

          }

  @Override public void UpdateResultTips(String msg) {
    mResultTextView.setText(msg);
  }

  private void initStartButton(final Activity mActivity) {

          mStratButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mShowLayout.removeAllViews();
              mResultTextView.setText("开始测试，请稍后...");
              String url=mSpinner.getSelectedItem().toString();

              int count=10;

        try {
          count=Integer.parseInt(mNumEditText.getText().toString());
        } catch (NumberFormatException e) {
          e.printStackTrace();
        }

        mPersonter.AskNetWork(url,count);
      }
    });
  }

  private void initSpinner(Activity mActivity){
    ArrayList<String> list=new ArrayList<String>();
    list.add("约单");
    list.add("新闻头条");
    list.add("百思不得姐");
    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(mActivity,android.R.layout.simple_spinner_item,list);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mSpinner.setAdapter(arrayAdapter);

  }
}
