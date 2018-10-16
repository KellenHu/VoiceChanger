package com.example.westd.ndkapplication;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.westd.ndkapplication.changeVoice.ChangeVoiceActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

  // Used to load the 'native-lib' library on application startup.
  static {
//    System.loadLibrary("native-lib");
  }

  private static String TAG = "MainActivity111";

  private static String SD_CARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    TextView btChangeVoice = (TextView) findViewById(R.id.text_change_voice);


    tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,TestFlowLayoutActivity.class));
      }
    });

    btChangeVoice.setOnClickListener(v->{
      startActivity(new Intent(MainActivity.this,ChangeVoiceActivity.class));
    });


//    patch();
    Log.e(TAG,"ClassLoder->"+this.getClassLoader().toString());
    Log.e(TAG,"System library path->"+System.getProperty("java.library.path"));
  }

  public void diff(){
    Log.d(TAG,"diff begin java-eqwe das--ad-eqwe----dasd--ewqewq---eqweqw-");

    String path = SD_CARD_PATH + File.separator + "video.mp4";
    String pattern_path = SD_CARD_PATH + File.separator + "video_%d.mp4";

    FileUtils.diff(path,pattern_path,4);
  }

  public void patch(){
    Log.d(TAG,"merge begin java-------java patch-------");

    String path = SD_CARD_PATH + File.separator + "video_merge.mp4";
    String pattern_path = SD_CARD_PATH + File.separator + "video_%d.mp4";

    FileUtils.patch(path,pattern_path,4);
  }
}
