package com.jwei.mysearch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jwei.mysearch.item.Shop;
import com.jwei.mysearch.item.Goods;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;

/**
 * Created by sh on 2017/5/3.
 */

public class activity_shop_main extends Activity {
    private TextView mString;
    private TextView shopname;
    private ImageView mImage;
    private Shop mShop;
    private String url;
    private TextView stepback;
    private List<Goods> mGoods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "c3691faf8b85561c7d207be91a25b9e4");
        setContentView(R.layout.activity_shop_main);

        shopname = (TextView) findViewById(R.id.shopname);
        Intent intent = this.getIntent();
        String shop_name = intent.getStringExtra("storename");
        shopname.setText(shop_name);

        ListView lv=(ListView) findViewById(R.id.goodsview01);
        //生成动态数组，加入数据
        ArrayList<HashMap<String, Object>> listItem
                = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<100;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("good_photo1",R.mipmap.dm1);
            map.put("textView1","nicai"+i);
            map.put("price1","11");
            listItem.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源
                R.layout.shopitem,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"good_photo1","textView1", "price1"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.good_photo1,R.id.textView1,R.id.price1}
        );

        //添加并且显示
        lv.setAdapter(listItemAdapter);

        stepback=(TextView) findViewById(R.id.stepback);
        stepback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_shop_main.this,GoodDetail.class);
                startActivity(intent);
            }
        });

        //setContentView(R.layout.activity_main);
//        mString = (TextView) findViewById(R.id.shop_string);
//        // mString.setText("你猜");
//        mShop = new Shop();
//        mShop.setmSname("nias");
//        mShop.setString("aaa");
//        mShop.setmFile(url);
//        mShop.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if (e == null) {
//                    Toast.makeText(getApplicationContext(), "上传成功", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "上传失败", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        BmobQuery<Shop> mshop = new BmobQuery<Shop>();
//        mshop.addWhereEqualTo("sname", "nias");
//        mshop.findObjects(new FindListener<Shop>() {
//            @Override
//            public void done(List<Shop> list, BmobException e) {
//                if (e == null) {
//                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
//                    for (Shop Shop : list) {
//                        mString.setText(Shop.getString());
//                        url=Shop.getmFile();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
//                }
//            }
//        });


    }

    private List<Map<String,Object>> getData() {
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for(int i=0;list.isEmpty()!=true;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("good_photo1",R.mipmap.dm1);
            map.put("textView1",list.get(i));
            map.put("price1","11");
            list.add(map);
        }
        return list;
    }
    public static Bitmap getBitmap(String path) throws IOException {
        URL url=new URL(path);
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode()==200){
            InputStream inputStream=conn.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;
    }

}
