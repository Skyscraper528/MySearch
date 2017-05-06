package com.jwei.mysearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.jwei.mysearch.item.Goods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class GoodsListPage extends AppCompatActivity {
    ListView listView;
    Spinner condition;


    //public MyAdapter myAdapter;
    public static final int loading=0x1;
    private Button back1;
    private EditText search;
    private String searchgoodsname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        Bmob.initialize(this, "82c5285224e3318150592e4b40e651ad");
        setContentView(R.layout.activity_goods_list_page);

        back1=(Button) findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GoodsListPage.this,MainSearchPage.class);
                startActivity(intent);
            }
        });

        search=(EditText) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GoodsListPage.this,MainSearchPage.class);
                startActivity(intent);
            }
        });


        Intent intent = this.getIntent();
        searchgoodsname = intent.getStringExtra("goodsname");
        search.setText(searchgoodsname);
        listView = (ListView) findViewById(R.id.listview5);
        // myAdapter = new MyAdapter(GoodsListPage.this);

        condition = (Spinner) findViewById(R.id.spinner_condition);
        ArrayAdapter< String> adapter = new ArrayAdapter< String>( this,R.layout.spinner_style);
        adapter.add("按距离排序");
        adapter.add("按价格排序");
        condition.setAdapter(adapter);

        final BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.order("Distance");

        //执行查询方法
        query.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                if(e == null) {
                    ArrayList<HashMap<String, Object>> listgoods= new ArrayList<HashMap<String, Object>>();
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).Goods_name.contains(search.getText().toString())){
                            HashMap<String, Object> goods = new HashMap<String, Object>();
//                            Bitmap bitmap=getPicture(list.get(i).getGoods_image().getFileUrl());
//                            Drawable bitmap1 = new BitmapDrawable(bitmap);
                            goods.put("goods_name",list.get(i).Goods_name);
                            goods.put("store_name", list.get(i).Store_name);
                            goods.put("goods_price",list.get(i).Price);
                            goods.put("distance",list.get(i).Distance);
                            //goods.put("goods_image",bitmap1);
                            listgoods.add(goods);
                        }
                    }

                    SimpleAdapter listItemAdapter = new SimpleAdapter(GoodsListPage.this,listgoods,//数据源
                            R.layout.goods_item,//ListItem的XML实现
                            //动态数组与ImageItem对应的子项
                            new String[]{"goods_name", "store_name", "distance", "goods_price"},
                            new int[]{R.id.goods_name, R.id.store_name, R.id.distance, R.id.goods_price}
                    );
                    listView.setAdapter(listItemAdapter);
                    listItemAdapter.notifyDataSetChanged();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView goodsname = (TextView) view.findViewById(R.id.goods_name);
                    String goods_name = (String) goodsname.getText();

                    Intent intent=new Intent(GoodsListPage.this,GoodDetail.class);
                    intent.putExtra("id",1);
                    intent.putExtra("goodsname",goods_name);
                    startActivity(intent);
                }
        });

    }

    public Bitmap getPicture(String path){
        Bitmap bm=null;
        try{
            URL url=new URL(path);
            URLConnection connection=url.openConnection();
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            bm= BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bm;
    }

}
