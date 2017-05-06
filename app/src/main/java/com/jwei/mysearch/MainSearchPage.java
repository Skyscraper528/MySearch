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
import android.widget.Toast;

import com.jwei.mysearch.item.Goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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
        tev1=(TextView) findViewById(R.id.tv1);
        tev2=(TextView) findViewById(R.id.tv2);
        tev3=(TextView) findViewById(R.id.tv3);
        tev4=(TextView) findViewById(R.id.tv4);
        tev1.setText(null);
        tev2.setText(null);
        tev3.setText(null);
        tev4.setText(null);

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
        BmobQuery<Goods> bmobQuery=new BmobQuery<Goods>();
        bmobQuery.addWhereNotEqualTo("Goods_name","ssssssss");
        bmobQuery.order("-Hot");
        bmobQuery.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                if(e==null){
                    for(Goods goods:list){
                        if(tev1.getText().toString().equals("")){
                            tev1.setText(goods.getGoods_name());
                        }else if(tev2.getText().toString().equals("")){
                            tev2.setText(goods.getGoods_name());
                        }else if(tev3.getText().toString().equals("")){
                            tev3.setText(goods.getGoods_name());
                        }else{
                            tev4.setText(goods.getGoods_name());
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"查询失败"+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        tev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainSearchPage.this,GoodsListPage.class);
                intent.putExtra("goodsname",tev1.getText().toString().trim());
                startActivity(intent);
            }
        });
        tev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainSearchPage.this,GoodsListPage.class);
                intent.putExtra("goodsname",tev2.getText().toString().trim());
                startActivity(intent);
            }
        });
        tev3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainSearchPage.this,GoodsListPage.class);
                intent.putExtra("goodsname",tev3.getText().toString().trim());
                startActivity(intent);
            }
        });
        tev4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainSearchPage.this,GoodsListPage.class);
                intent.putExtra("goodsname",tev4.getText().toString().trim());
                startActivity(intent);
            }
        });

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
