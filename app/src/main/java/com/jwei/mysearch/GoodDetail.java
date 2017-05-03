package com.jwei.mysearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chen on 2016/12/19.
 */

public class GoodDetail extends Activity{
    private TextView storename;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_gooddetails);
        storename=(TextView) findViewById(R.id.store_name);
        storename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GoodDetail.this,activity_shop_main.class);
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
