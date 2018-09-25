package com.example.westd.ndkapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFlowLayoutActivity extends AppCompatActivity {

  ListView listView;

  GroupDealPropertiesAdapter groupDealPropertiesAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_flow_layout);
    listView = findViewById(R.id.listView);

    groupDealPropertiesAdapter = new GroupDealPropertiesAdapter(this,formatPropertirsData());
    listView.setAdapter(groupDealPropertiesAdapter);

    TextView footer = new TextView(this);
    footer.setText("Footter");
    footer.setBackgroundColor(Color.parseColor("#00fF00"));


    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("Bug", "Bg");
      }
    });
  }

  /**
   * 格式化属性集合的数据
   */
  private List<Map<String,Object>> formatPropertirsData(){
    List<Map<String,Object>> list = new ArrayList<>();

    List<String> colorList = new ArrayList<>();
    colorList.add("red");
    colorList.add("green");
    colorList.add("blue");
    colorList.add("avtor");
    colorList.add("conmshsjdklk");
    colorList.add("conm");
    colorList.add("conj");
    colorList.add("conj287381239");

    Map<String,Object> listMap = new HashMap<>();
    listMap.put("property",colorList);
    listMap.put("type","颜色");
    list.add(listMap);

    List<String> modelList = new ArrayList<>();
    modelList.add("Sime 1");
    modelList.add("Hskdjk1");
    modelList.add("黑色模式");
    modelList.add("简单");
    modelList.add("飞机凯撒解放啦");
    modelList.add("co");
    modelList.add("co");
    modelList.add("所开发");

    Map<String,Object> listMap1 = new HashMap<>();
    listMap1.put("property",modelList);
    listMap1.put("type","模板");
    list.add(listMap1);

    List<String> modelList2 = new ArrayList<>();
    modelList2.add("Sime 1");
    modelList2.add("Hskdjk1");
    modelList2.add("黑色模式");
    modelList2.add("简单");
    modelList2.add("飞机凯撒解放啦");
    modelList2.add("co");
    modelList2.add("co");
    modelList2.add("所开发");

    Map<String,Object> listMap2 = new HashMap<>();
    listMap2.put("property",modelList2);
    listMap2.put("type","模板");
    list.add(listMap2);

    List<String> modelList3 = new ArrayList<>();
    modelList3.add("Sime 1");
    modelList3.add("Hskdjk1");
    modelList3.add("黑色模式");
    modelList3.add("简单");
    modelList3.add("飞机凯撒解放啦");
    modelList3.add("co");
    modelList3.add("co");
    modelList3.add("所开发");

    Map<String,Object> listMap3 = new HashMap<>();
    listMap3.put("property",modelList3);
    listMap3.put("type","模板");
    list.add(listMap3);

    List<String> modelList4 = new ArrayList<>();
    modelList4.add("Sime 1");
    modelList4.add("Hskdjk1");
    modelList4.add("黑色模式");
    modelList4.add("简单");
    modelList4.add("飞机凯撒解放啦");
    modelList4.add("co");
    modelList4.add("co");
    modelList4.add("所开发");

    Map<String,Object> listMap4 = new HashMap<>();
    listMap4.put("property",modelList4);
    listMap4.put("type","模板");
    list.add(listMap4);

    List<String> modelList5 = new ArrayList<>();
    modelList5.add("Sime 1");
    modelList5.add("Hskdjk1");
    modelList5.add("黑色模式");
    modelList5.add("简单");
    modelList5.add("飞机凯撒解放啦");
    modelList5.add("co");
    modelList5.add("co");
    modelList5.add("所开发");

    Map<String,Object> listMap5 = new HashMap<>();
    listMap5.put("property",modelList5);
    listMap5.put("type","模板");
    list.add(listMap5);

    List<String> modelList6 = new ArrayList<>();
    modelList6.add("Sime 1");
    modelList6.add("Hskdjk1");
    modelList6.add("黑色模式");
    modelList6.add("简单");
    modelList6.add("飞机凯撒解放啦");
    modelList6.add("co");
    modelList6.add("co");
    modelList6.add("所开发");

    Map<String,Object> listMap6 = new HashMap<>();
    listMap6.put("property",modelList6);
    listMap6.put("type","模板");
    list.add(listMap6);

    List<String> modelList7 = new ArrayList<>();
    modelList7.add("Sime 1");
    modelList7.add("Hskdjk1");
    modelList7.add("黑色模式");
    modelList7.add("简单");
    modelList7.add("飞机凯撒解放啦");
    modelList7.add("co");
    modelList7.add("co");
    modelList7.add("所开发");

    Map<String,Object> listMap7 = new HashMap<>();
    listMap7.put("property",modelList7);
    listMap7.put("type","模板");
    list.add(listMap7);

    List<String> modelList8 = new ArrayList<>();
    modelList8.add("Sime 1");
    modelList8.add("Hskdjk1");
    modelList8.add("黑色模式");
    modelList8.add("简单");
    modelList8.add("飞机凯撒解放啦");
    modelList8.add("co");
    modelList8.add("co");
    modelList8.add("所开发");

    Map<String,Object> listMap8 = new HashMap<>();
    listMap8.put("property",modelList8);
    listMap8.put("type","模板");
    list.add(listMap8);

    List<String> modelList9 = new ArrayList<>();
    modelList9.add("Sime 1");
    modelList9.add("Hskdjk1");
    modelList9.add("黑色模式");
    modelList9.add("简单");
    modelList9.add("飞机凯撒解放啦");
    modelList9.add("co");
    modelList9.add("co");
    modelList9.add("所开发");

    Map<String,Object> listMap9 = new HashMap<>();
    listMap9.put("property",modelList9);
    listMap9.put("type","模板");
    list.add(listMap9);

    List<String> modelList10 = new ArrayList<>();
    modelList10.add("Sime 1");
    modelList10.add("Hskdjk1");
    modelList10.add("黑色模式");
    modelList10.add("简单");
    modelList10.add("飞机凯撒解放啦");
    modelList10.add("co");
    modelList10.add("co");
    modelList10.add("所开发");

    Map<String,Object> listMap10 = new HashMap<>();
    listMap10.put("property",modelList10);
    listMap10.put("type","模板");
    list.add(listMap10);

    List<String> modelList11 = new ArrayList<>();
    modelList11.add("Sime 1");
    modelList11.add("Hskdjk1");
    modelList11.add("黑色模式");
    modelList11.add("简单");
    modelList11.add("飞机凯撒解放啦");
    modelList11.add("co");
    modelList11.add("co");
    modelList11.add("所开发");

    Map<String,Object> listMap11 = new HashMap<>();
    listMap11.put("property",modelList11);
    listMap11.put("type","模板");
    list.add(listMap11);

    return list;
  }

}
