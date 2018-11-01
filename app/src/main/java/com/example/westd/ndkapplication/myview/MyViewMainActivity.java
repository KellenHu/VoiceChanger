package com.example.westd.ndkapplication.myview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.westd.ndkapplication.FileUtils;
import com.example.westd.ndkapplication.MyUtils;
import com.example.westd.ndkapplication.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

import static android.widget.Toast.LENGTH_SHORT;

public class MyViewMainActivity extends AppCompatActivity {

  private final static String TAG = MyViewMainActivity.class.getSimpleName();
  private Object o;
  private Object o1;
  private Object o2;
  private WeakReference<Object> weakReference2;
  private HorizontalScrollViewEx mListContainer;

  Handler mhander;
  TextView text_view;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_view_main);

    o = new FileUtils();
//    Log.e("MyView","初始化出来的对象-->" + o.toString());
    o1 = new FileUtils();
    WeakReference<Object> weakReference = new WeakReference<Object>(o1);
//    Log.e("MyView","初始化出来的O1 对象-->" + weakReference.get().toString());
    text_view = findViewById(R.id.text_view);
    o2 = new FileUtils();
    weakReference2 = new WeakReference<Object>(o2);
//    Log.e("MyView","初始化出来的O2 对象-->" + weakReference2.get().toString());
    o2 = null;
    /**
     * Pre: 匿名创建 和 主动将对象强引用==null 效果一致
     * 结论 ：
     *
     * 当GC发生：
     *  弱引用中所保存的对象还有强引用时，对象不会被回收，该弱应用也不会被回收
     *  当对象被回收，弱引用也会被回收
     *  当对象未被回收，弱应用不一定会被回收
     *
     */


    /**
     *  View ViewrootImpl关系
     *  1. ViewRoot继承自ViewParent  是链接WIndowMananger和Decovie的纽带
     *  2. VIew中持有ViewParent 其实就是ViewRootImpl
     *  3. 调用View的Invalidat 其实是调用了mParent的.Invalidate.incalidateChild()--》 ViewRootImpl。invalidate -- >
     *  ViewRootImpl.scheduleTraversals()
     *  4. scheduleTraversals 开始View的重绘测量progress 其中有checkThread，如果不在主线程，就抛出异常
     *      > performTraversals
     *        > performMeasure() -> measure() -> (ViewGroup)onMeasure()
     *        > performLayout()  -> layout()  -> (ViewGroup)onLayout()
     *        > performDraw()  -> draw()   ->  (ViewGroup)onDraw()
     *  **以上则为View的绘制流程，其实是代理给ViewRootImpl了
     *
     */

    FrameLayout mRootView = (FrameLayout) getWindow().getDecorView();
    MyView myView = new MyView(this);

    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(120,120);
    myView.setBackgroundColor(Color.RED);
    myView.setLayoutParams(layoutParams);
    mRootView.addView(myView);
//    myView.setOnClickListener();
    initView();
//    Log.e("外边->","thread -> " + Thread.currentThread().getName() + "\n Looper -> " + getMainLooper().toString());

    new Thread(new Runnable() {
      @Override
      public void run() {
//        Looper.prepare();
//       mhander = new Handler(new Handler.Callback() {
//         @Override
//         public boolean handleMessage(Message msg) {
//           Log.e("收到消息1->","thread -> " + Thread.currentThread().getName()+"\n Looper -> " + getMainLooper().toString());
//           text_view.setText("故事的小黄花");
//           return false;
//         }
//       });
//        Log.e("xian->","thread -> " + Thread.currentThread().getName());
         try {
           Thread.sleep(1000);
//           Log.e("当前线程-->","thread -> " + Thread.currentThread().getName());
//           mhander.sendEmptyMessage(1);
         } catch (InterruptedException e){
           e.printStackTrace();
         }
//        Looper.loop();

      }
    }).start();

    new Thread(){
      @Override
      public void run(){
//        Looper.prepare();
//        Log.e("xian222->","thread -> " + Thread.currentThread().getName()+"\n Looper -> " + getMainLooper().toString());
        text_view.setText("故事的小黄花2332");
        text_view.invalidate();
        super.run();
//        Looper.loop();
      }
    }.start();


    Intent intent = getIntent();
    Class clazz = (Class) intent.getSerializableExtra("ClassLoaded");
    Log.d(TAG, "onCreate: 接受道的class" + clazz.toString());
    Log.d(TAG, "onCreate: 本地的ClassLoader - 》" + ((BaseDexClassLoader)this.getClassLoader()).toString());
    try {
      Class clazz1 = new DexClassLoader(null,null,null,null).loadClass("com.example.westd.ndkapplication.myview.MyView");
      Log.d(TAG, "onCreate: 本地的加载出来的class - >" + clazz1.toString() + "是否eqaul :" + clazz.equals(clazz1));
    } catch (ClassNotFoundException e){
      e.printStackTrace();
    }


  }

  private void initView() {
    LayoutInflater inflater = getLayoutInflater();
    mListContainer = (HorizontalScrollViewEx) findViewById(R.id.container);
    final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
    final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
    for (int i = 0; i < 18; i++) {
      ViewGroup layout = (ViewGroup) inflater.inflate(
          R.layout.content_layout, mListContainer, false);
      layout.getLayoutParams().width = screenWidth/3;
      TextView textView = (TextView) layout.findViewById(R.id.title);
      textView.setText("page " + (i + 1));
      textView.setTextColor(Color.rgb(255,255,255));
      layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
      createList(layout);
      mListContainer.addView(layout);
    }
  }

  private void createList(ViewGroup layout) {
    ListView listView = (ListView) layout.findViewById(R.id.list);
    ArrayList<String> datas = new ArrayList<String>();
    for (int i = 0; i < 50; i++) {
      datas.add("name " + i);
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        R.layout.content_list_item, R.id.name, datas);
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view,
                              int position, long id) {
        Toast.makeText(MyViewMainActivity.this, "click item",
            LENGTH_SHORT).show();

      }
    });
  }
}
