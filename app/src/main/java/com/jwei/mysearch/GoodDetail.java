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
import com.jwei.mysearch.item.Goods;
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
    private TextView introduction;
    private TextView goodscollect_icon;
    private String goods_name;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        Bmob.initialize(this, "82c5285224e3318150592e4b40e651ad");
        setContentView(R.layout.activity_gooddetails);
        ImageView button_nav =(ImageView)findViewById(R.id.store_navigation);

        final Intent intent = this.getIntent();
        int id = intent.getIntExtra("id",0);
        if (id==1) {
            //    得到跳转到该Activity的Intent对象
            goods_name = intent.getStringExtra("goodsname");
        } else if(id ==2){
            goods_name = intent.getStringExtra("namefootprint");
        } else if (id ==3){
            goods_name = intent.getStringExtra("goodsname");
        } else if (id ==4){
            goods_name = intent.getStringExtra("goodsname");
        } else if (id ==5){
            goods_name = intent.getStringExtra("namefootprint");
        }

        storename = (TextView) findViewById(R.id.store_name3);
        goodsname = (TextView) findViewById(R.id.goods_name1);
        distance_ = (TextView) findViewById(R.id.goods_distance1);
        goodsprice = (TextView) findViewById(R.id.goods_price1);
        goodsimage = (ImageView) findViewById(R.id.goods_image1);
        storeaddr = (TextView) findViewById(R.id.store_address);
        introduction = (TextView) findViewById(R.id.goods_desp);
        initdata(goods_name);

        final String addr = storeaddr.getText().toString();
        button_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(GoodDetail.this,RoutePlanDemo.class);
                intent2.putExtra("id",1);
                intent2.putExtra("addr",addr);
                startActivity(intent2);
            }
        });

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

    public void initdata(final String name){

        BmobQuery<Goods> sc = new BmobQuery<Goods>();
        sc.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                if(e==null){
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).getGoods_name().equals(name)){
                            goodsname.setText(list.get(i).getGoods_name());
                            storename.setText(list.get(i).getStore_name());
                            distance_.setText(list.get(i).getDistance());
                            goodsprice.setText(list.get(i).getPrice());
                            storeaddr.setText(list.get(i).getAddr());
                            introduction.setText(list.get(i).getIntroduction());
                            break;
                        }
                    }
                }
            }
        });
    }


}
