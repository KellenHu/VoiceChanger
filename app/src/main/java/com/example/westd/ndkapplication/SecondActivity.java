package com.example.westd.ndkapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

  private long timeStart;

  Handler handler = new Handler(new Handler.Callback() {
    @Override
    public boolean handleMessage(Message msg) {
      for (int i = 0; i < 1000000; i++) {
        Log.e("Handler:","线程:" + Thread.currentThread().toString());
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      return false;
    }
  });

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    timeStart = System.currentTimeMillis();
    setContentView(R.layout.activity_secend_simple);

    new Thread(new Runnable() {
      @Override
      public void run() {
//        try {
//          Thread.sleep(2000);

          handler.sendMessageDelayed(Message.obtain(handler,0),2000);
//          handler.postDelayed(, )
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
      }
    }).start();
    try {
      Thread.sleep(5000);
      for (int i = 0; i < 10; i++) {
        Log.e("onCreate:","线程:" + Thread.currentThread().toString());
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Log.e("初始化布局所花时间:","" + (System.currentTimeMillis()- timeStart) + " ms");
  }


  @Override
  protected void onStart() {
    super.onStart();

    Log.e("onStart:","" + (System.currentTimeMillis()-timeStart) + " ms");

  }

  @Override
  protected void onResume() {
    super.onResume();
    try {
      Thread.sleep(1);
      System.currentTimeMillis();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Log.e("onResume:","" + (System.currentTimeMillis()-timeStart) + " ms");

  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.e("onResume:","");
  }


}
