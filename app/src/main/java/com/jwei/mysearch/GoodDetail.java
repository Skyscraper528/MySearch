package com.jwei.mysearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.jwei.mysearch.item.GoodsCollect;
import com.jwei.mysearch.item.GoodsHistory;

import org.json.JSONArray;

import java.util.List;
import java.util.Objects;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by chen on 2016/12/19.
 */

public class GoodDetail extends AppCompatActivity {
    private TextView storename;
    private TextView goodsname;
    private TextView goodsprice;
    private TextView distance_;
    private ImageView goodsimage;
    private TextView storeaddr;
    private TextView goodscollect_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        Bmob.initialize(this, "82c5285224e3318150592e4b40e651ad");
        setContentView(R.layout.activity_gooddetails);

        ImageView button_nav =(ImageView)findViewById(R.id.store_navigation);
        storeaddr = (TextView) findViewById(R.id.store_address);
        final String addr = storeaddr.getText().toString();
        button_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(GoodDetail.this,RoutePlanDemo.class);
                intent2.putExtra("addr",addr);
                startActivity(intent2);
            }
        });



        storename = (TextView) findViewById(R.id.store_name3);
        goodsname = (TextView) findViewById(R.id.goods_name1);
        distance_ = (TextView) findViewById(R.id.goods_distance1);
        goodsprice = (TextView) findViewById(R.id.goods_price1);
        goodsimage = (ImageView) findViewById(R.id.goods_image1);

        final Intent intent = this.getIntent();
        int id = intent.getIntExtra("id",0);
        if (id==1) {
            //    得到跳转到该Activity的Intent对象
            String goods_name = intent.getStringExtra("goodsname");
            final String store_name = intent.getStringExtra("storename");
            String distance = intent.getStringExtra("distance");
            String goods_price = intent.getStringExtra("goodsprice");
            Bitmap goods_image=intent.getParcelableExtra("image");

            goodsimage.setImageBitmap(goods_image);
            goodsname.setText(goods_name);
            storename.setText(store_name);
            goodsprice.setText(goods_price);
            distance_.setText(distance);
        } else if(id ==2){
            String goods_name = intent.getStringExtra("namefootprint");
            final String store_name = intent.getStringExtra("storenamefootprint");
            String goods_price = intent.getStringExtra("pricefootprint");
            Bitmap goods_image=intent.getParcelableExtra("image1");

            goodsimage.setImageBitmap(goods_image);
            goodsname.setText(goods_name);
            storename.setText(store_name);
            goodsprice.setText(goods_price);
        }

        storename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GoodDetail.this,activity_shop_main.class);
                intent.putExtra("storename",storename.getText());
                startActivity(intent);
            }
        });

        SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
        String loginName = sp.getString("LoginName", "wrong key");

        GoodsHistory gs = new GoodsHistory();
        gs.setUsername(loginName);
        gs.setGoodsnamefootprint(goodsname.getText().toString());
        gs.setGoodspricefootprint(goodsprice.getText().toString());
        gs.setGoodsstorefootprint(storename.getText().toString());
        gs.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

            }
        });

        goodscollect_icon = (TextView) findViewById(R.id.goods_collect_icon);
        goodscollect_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsCollect gc = new GoodsCollect();
                //gc.setGoodscollectimage();
                gc.setGoodscollectname(goodsname.getText().toString());
                gc.setGoodscollectprice(goodsprice.getText().toString());
                gc.setGoodscollectstore(storename.getText().toString());
                SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
                String loginName = sp.getString("LoginName", "wrong key");
                gc.setUsername(loginName);
                gc.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(getApplicationContext(),"收藏成功",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"您已收藏",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        ImageView button_back =(ImageView) findViewById(R.id.goods_detail_back);


        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GoodDetail.this,GoodsListPage.class);
                startActivity(intent1);
            }
        });



    }




}
