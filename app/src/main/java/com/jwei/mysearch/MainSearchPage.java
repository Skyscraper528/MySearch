package com.jwei.mysearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cn.bmob.v3.Bmob;

/**
 * Created by sh on 2017/5/3.
 */

public class MainSearchPage extends AppCompatActivity{
    private TextView tev1,tev2,tev3,tev4,tev5;
    private Button cancel1;
    private Button search1;
    private EditText search_to_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);
        Bmob.initialize(this, "82c5285224e3318150592e4b40e651ad");
        cancel1=(Button) findViewById(R.id.cancel1);
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainSearchPage.this,MainPages.class);
                startActivity(intent);
                finish();
            }
        });

        ListView lv=(ListView) findViewById(R.id.lv1);
        //生成动态数组，加入数据
        ArrayList<HashMap<String, Object>> listItem
                = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<100;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tex1","nicai");
            listItem.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源
                R.layout.searchitem,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"tex1"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.tex1}
        );

        //添加并且显示
        lv.setAdapter(listItemAdapter);

        search_to_list = (EditText) findViewById(R.id.search_to_list);
        search1=(Button) findViewById(R.id.search1);
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainSearchPage.this,GoodsListPage.class);
                intent.putExtra("goodsname",search_to_list.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}
