package com.jwei.mysearch.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jwei.mysearch.GoodDetail;
import com.jwei.mysearch.GoodsListPage;
import com.jwei.mysearch.R;
import com.jwei.mysearch.item.Goods;
import com.jwei.mysearch.item.GoodsCollect;
import com.jwei.mysearch.item.MyUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class GoodsCollection extends Fragment {
    ListView listView;
    public Vector<GoodsCollect> goods = new Vector<>();
    //public MyAdapter myAdapter;
    public static final int loading=0x1;
    public MyUser my = BmobUser.getCurrentUser(MyUser.class);
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_goods_collection, container,false);
        listView = (ListView) view.findViewById(R.id.goods_collect_list);
        BmobQuery<GoodsCollect> gc = new BmobQuery<GoodsCollect>();
        gc.findObjects(new FindListener<GoodsCollect>() {
            @Override
            public void done(List<GoodsCollect> list, BmobException e) {
                if(e == null) {
                    ArrayList<HashMap<String, Object>> listgoods= new ArrayList<HashMap<String, Object>>();
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).getUsername().equals(my.getUsername())){
                            HashMap<String, Object> goodscollect = new HashMap<String, Object>();
//                            Bitmap bitmap=getPicture(list.get(i).getGoods_image().getFileUrl());
//                            Drawable bitmap1 = new BitmapDrawable(bitmap);
                            goodscollect.put("goods_name",list.get(i).getGoodscollectname());
                            goodscollect.put("store_name", list.get(i).getGoodscollectstore());
                            goodscollect.put("goods_price",list.get(i).getGoodscollectprice());
                            //goods.put("goods_image",bitmap1);
                            listgoods.add(goodscollect);
                        }
                    }

                    SimpleAdapter listItemAdapter = new SimpleAdapter(getActivity(),listgoods,//数据源
                            R.layout.collection_goods_item,//ListItem的XML实现
                            //动态数组与ImageItem对应的子项
                            new String[]{"goods_name", "goods_price", "store_name"},
                            new int[]{R.id.collect_goods_name, R.id.collection_goods_price, R.id.store_name1}
                    );
                    listView.setAdapter(listItemAdapter);
                    listItemAdapter.notifyDataSetChanged();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView goodsname = (TextView) view.findViewById(R.id.collect_goods_name);
                String goods_name = (String) goodsname.getText();
                TextView storename = (TextView) view.findViewById(R.id.store_name1);
                String store_name = (String) storename.getText();
                TextView goodsprice = (TextView) view.findViewById(R.id.collection_goods_price);
                String goods_price = (String) goodsprice.getText();
                ImageView pic = (ImageView) view.findViewById(R.id.collect_goods_imageview);
                Drawable image = pic.getDrawable();
                BitmapDrawable bd = (BitmapDrawable) image;
                Bitmap bitmap = bd.getBitmap();

                Intent intent=new Intent(getActivity(),GoodDetail.class);
                intent.putExtra("id",3);
                intent.putExtra("image", bitmap);
                intent.putExtra("goodsname",goods_name);
                intent.putExtra("storename",store_name);
                intent.putExtra("goodsprice",goods_price);
                startActivity(intent);
            }
        });

        return view;
    }

}
