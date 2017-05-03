package com.jwei.mysearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chen on 2016/12/19.
 */

public class GoodDetail extends AppCompatActivity {
    private TextView storename;
    private TextView goodsname;
    private TextView goodsprice;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooddetails);

        storename = (TextView) findViewById(R.id.store_name3);
        goodsname = (TextView) findViewById(R.id.goods_name1);
        // distance_ = (TextView) findViewById(R.id.distance);
        goodsprice = (TextView) findViewById(R.id.goods_price1);

        //    得到跳转到该Activity的Intent对象
        Intent intent = this.getIntent();
        String goods_name = intent.getStringExtra("goodsname");
        final String store_name = intent.getStringExtra("storename");
        //String distance = intent.getStringExtra("com.jwei.mysearch.GoodListPage.distance");
        String goods_price = intent.getStringExtra("goodsprice");

        goodsname.setText(goods_name);
        storename.setText(store_name);
        goodsprice.setText(goods_price);

        storename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GoodDetail.this,activity_shop_main.class);
                intent.putExtra("storename",store_name);
                startActivity(intent);
            }
        });

        ImageView button_back =(ImageView) findViewById(R.id.goods_detail_back);
        ImageView button_nav =(ImageView)findViewById(R.id.store_navigation);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GoodDetail.this,GoodsListPage.class);
                startActivity(intent1);
            }
        });

        button_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(GoodDetail.this,RoutePlanDemo.class);
                startActivity(intent2);
            }
        });

    }




}
