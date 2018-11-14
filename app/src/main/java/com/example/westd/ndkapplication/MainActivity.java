package com.example.westd.ndkapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.widget.Toast;

import com.example.westd.ndkapplication.anfixdemo.AndfixMainActivity;
import com.example.westd.ndkapplication.changeVoice.ChangeVoiceActivity;
import com.example.westd.ndkapplication.myview.MyViewMainActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

  // Used to load the 'native-lib' library on application startup.
  static {
//    System.loadLibrary("native-lib");
  }

  private static String TAG = "MainActivity111";

  private static String SD_CARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
  private Class clazz;

  @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    TextView btChangeVoice = (TextView) findViewById(R.id.text_change_voice);
    TextView textAndfix = (TextView) findViewById(R.id.text_andfix);
    TextView text_my_view = (TextView) findViewById(R.id.text_my_view);


    int touchSlop =  ViewConfiguration.get(this).getScaledTouchSlop();//获取设备能识别的最小滑动距离

    tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,TestFlowLayoutActivity.class));
      }
    });

    btChangeVoice.setOnClickListener(v->{
      startActivity(new Intent(MainActivity.this,ChangeVoiceActivity.class));
//      Intent intent = new Intent(MainActivity.this,AndfixMainActivity.class);
//      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//      getApplicationContext().startActivity(intent);
    });

    textAndfix.setOnClickListener(v->{
      Intent intent = new Intent(MainActivity.this,AndfixMainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      getApplicationContext().startActivity(intent);
    });

    text_my_view.setOnClickListener(v->{
      Intent intent  =new Intent(MainActivity.this, MyViewMainActivity.class);
      intent.putExtra("ClassLoaded",clazz);
      startActivity(intent);
    });

//    patch();
    Log.e(TAG,"ClassLoder1->"+this.getClassLoader().toString());
    Log.e(TAG,"System library path 1->"+System.getProperty("java.library.path"));
    Log.e(TAG,"------------start123 -----加载class MyView");

    try {
      clazz = this.getClassLoader().loadClass("com.example.westd.ndkapplication.myview.MyView");
      Log.d(TAG, "onCreate: 2MyView" + clazz.toString());

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }


  }

  public void diff(){
    Log.d(TAG,"diff begin java-eqweqw-leadder 123456git add +ewqeqweqweqwee");

    String path = SD_CARD_PATH + File.separator + "video.mp4";
    String pattern_path = SD_CARD_PATH + File.separator + "video_%d.mp4";

    FileUtils.diff(path,pattern_path,4);
  }

  public void patch(){
    Log.d(TAG,"merge begin java-----大神--java patch-------");

    String path = SD_CARD_PATH + File.separator + "video_merge.mp4";
    String pattern_path = SD_CARD_PATH + File.separator + "video_%d.mp4";

    FileUtils.patch(path,pattern_path,4);
  }

  @Override
  protected void onDestroy() {
    Toast.makeText(getApplicationContext(),"MainActivity Ondesdroy",Toast.LENGTH_SHORT).show();
    super.onDestroy();
  }
}
