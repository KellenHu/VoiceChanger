package com.example.westd.ndkapplication.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

//import com.nineoldandroids.view.ViewHelper;

import java.lang.ref.WeakReference;

public class MyView extends ViewGroup implements GestureDetector.OnGestureListener{

  private static final String TAG = "MyView";

  private VelocityTracker velocityTracker;
  GestureDetector mGestureDetector ;

  Scroller mScroller ;
  Context mContext;
  private int mLastX;//记录上一次的位置
  private int mLastY;

  public MyView(Context context) {
    super(context);
    mContext = context;
    init();
  }

  public MyView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mContext = context;
    init();
  }

  public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mContext = context;
    init();
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    mContext = context;
    init();
  }

  private void init(){
    mGestureDetector = new GestureDetector(this);
    mGestureDetector.setIsLongpressEnabled(true);//长安无法拖动wenti

    mScroller = new Scroller(mContext);
  }

  //*缓慢滑动
  private void smoothScrollTo(int destX,int destY){
    int scrollX = getScrollX();
    int delta = destX - scrollX;
    mScroller.startScroll(scrollX,0,delta,0,1000);
    invalidate();
  }

  @Override
  public void computeScroll() {
    if (mScroller.computeScrollOffset()){
//      Log.e("ComputeScroll","开始滑了---> "  + "X = " + mScroller.getCurrX() + "Y = " + mScroller.getCurrY());
      scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
      postInvalidate();
    }
//    super.computeScroll();
  }


  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    Log.e(TAG,"事件分发之-->onInterceptTouchEvent()" + super.onInterceptTouchEvent(ev));
    return true;
  }

  public boolean dispatchTouchEvent(MotionEvent event) {
    Log.e(TAG,"事件分发之-->dispatchTouchEvent()" + super.dispatchTouchEvent(event));
    return super.dispatchTouchEvent(event);
  }


  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.e(TAG,"事件分发之-->onTouchEvent()"  );
    //速度追踪
    if (velocityTracker==null){
      velocityTracker = VelocityTracker.obtain();
    }
//    Log.e("MyView","速度追钟实例1---> "  + velocityTracker.hashCode() + "\n event实例_> " + event.hashCode());

    velocityTracker.addMovement(event);
    velocityTracker.computeCurrentVelocity(500);//没500ms计算速度
    int horiVelocity = (int) velocityTracker.getXVelocity();
    int vertiVelocity = (int) velocityTracker.getYVelocity();
//    Log.e("MyView","速度追钟水平速度---> "  + horiVelocity + "px/500ms");
//    Log.e("MyView","速度追钟水平速度---> "  + vertiVelocity + "px/500ms");

    //真实坐标
    int x = (int) event.getRawX();
    int y = (int) event.getRawY();

    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
        break;
      case MotionEvent.ACTION_UP:
        break;
      case MotionEvent.ACTION_MOVE:{
        int deltaX = x-mLastX;
        int deltaY = y-mLastY;

//        Log.e(TAG,"move , deltax = " + deltaX + " \t deltaY = " + deltaY);

//        int translationX = (int) (ViewHelper.getTranslationX(this)+deltaX);
//        int translationY = (int) (ViewHelper.getTranslationY(this)+deltaY);

//        ViewHelper.setTranslationX(this,translationX);
//        ViewHelper.setTranslationY(this,translationY);
      }break;

    }


    mLastX = x;
    mLastY = y;
    return true;
  }


  @Override
  public boolean onDown(MotionEvent e) {
    return false;
  }

  @Override
  public void onShowPress(MotionEvent e) {

  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    return false;
  }

  @Override
  public void onLongPress(MotionEvent e) {

  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    return false;
  }
}
