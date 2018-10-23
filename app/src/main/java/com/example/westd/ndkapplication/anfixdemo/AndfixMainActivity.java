package com.example.westd.ndkapplication.anfixdemo;

import android.content.Intent;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.westd.ndkapplication.MainActivity;
import com.example.westd.ndkapplication.R;

public class AndfixMainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Toast.makeText(getApplicationContext(),"启动自己Oncreat",Toast.LENGTH_SHORT).show();
    setContentView(R.layout.activity_andfix_main);
    findViewById(R.id.start_self).setOnClickListener(v->{
      startActivity(new Intent(AndfixMainActivity.this,AndfixMainActivity.class));
//      Toast.makeText(getApplicationContext(),"启动自己成功",Toast.LENGTH_SHORT).show();
    });
    findViewById(R.id.start_mainactivity).setOnClickListener(v->{
      startActivity(new Intent(AndfixMainActivity.this,MainActivity.class));
//      Toast.makeText(getApplicationContext(),"启动Main成功",Toast.LENGTH_SHORT).show();
    });
  }


  @Override
  protected void onStart(){
//    Messenger messenger = new Messenger()
    super.onStart();
  }

  @Override
  protected void onStop(){
    super.onStop();
  }

  @Override
  protected void onNewIntent(Intent intent){
          Toast.makeText(getApplicationContext(),"And fix OnnewIntent",Toast.LENGTH_SHORT).show();
    super.onNewIntent(intent);
  }

  @Override
  protected void onDestroy() {
    Toast.makeText(getApplicationContext(),"And fix OnDestory",Toast.LENGTH_SHORT).show();
    super.onDestroy();
  }
}
