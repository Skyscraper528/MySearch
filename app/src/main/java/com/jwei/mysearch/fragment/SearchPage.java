package com.jwei.mysearch.fragment;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jwei.mysearch.GoodDetail;
import com.jwei.mysearch.MainSearchPage;
import com.jwei.mysearch.Mapview;
import com.jwei.mysearch.R;
import com.jwei.mysearch.item.Goods;

import java.util.Vector;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SearchPage extends Fragment implements AbsListView.OnScrollListener{

    ListView listView;
    public Vector<Goods> goods = new Vector<>();
    public MyAdapter myAdapter;
    public static final int loading=0x1;
    private int contentView;
    private Button to_mapview;
    private Button place;
    private EditText search1;




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_page, container,false);

        to_mapview = (Button) view.findViewById(R.id.searchtomap);
        to_mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Mapview.class);
                startActivity(intent);
            }
        });

        search1=(EditText) view.findViewById(R.id.mainpage_search);
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainSearchPage.class);
                startActivity(intent);
            }
        });

        listView = (ListView) view.findViewById(R.id.goods_recommend_list);
        listView.setOnScrollListener(this);
        initData();
        myAdapter = new MyAdapter(getActivity());
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView goodsname = (TextView) view.findViewById(R.id.recommend_goods_name);
                String goods_name = (String) goodsname.getText();

                Intent intent=new Intent(getActivity(),GoodDetail.class);
                intent.putExtra("id",4);
                intent.putExtra("goodsname",goods_name);
                startActivity(intent);
            }
        });

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return view;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()-1));
        ((ViewGroup.MarginLayoutParams)params).setMargins(10, 10, 10, 10);
        listView.setLayoutParams(params);
        return view;
    }

    public int index = 0;
    /*
    * 初始化数据
    * */

    public String[] Goods_name={
            "疯狂Android讲义 李刚疯狂的Android讲义教程从入门到精通",
            "康师傅绿茶"
    };

    public String[] Store_name={
            "瑞意图书专营店",
            "万嘉超市"
    };

    public String[] Price={
            "¥88.60",
            "¥3.00"
    };

    public int[] images={R.mipmap.goods1,
            R.mipmap.goods2,
    };

    public void initData(){
        for(int i=0;i < 10&&index<Goods_name.length;i++){
            Goods g = new Goods();
            g.Goods_name = Goods_name[index];
            g.Store_name = Store_name[index];
            g.Price = Price[index];
            String s=String.valueOf(images[index]);
            //g.sImage = s;
            ++index;
            goods.add(g);

        }
    }

    private int visibleLastIndex;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if(i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && myAdapter.getCount()==visibleLastIndex){
            new LoadThread().start();

        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        visibleLastIndex =i+i1-1;
    }

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case loading :
                    myAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    class LoadThread extends Thread{
        @Override
        public void run() {
            initData();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //通过handler给主线程发送一个标记
            handler.sendEmptyMessage(1);
        }
    }

    class MyAdapter extends BaseAdapter {
        private Context context;

        public MyAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return goods.size();
        }

        @Override
        public Object getItem(int i) {
            return goods.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder vh;


            if(view == null) {
                LayoutInflater inflater = LayoutInflater.from(this.context);
                //实例化一个布局
                view = View.inflate(context, R.layout.recommend_goods_item, null);
                vh = new ViewHolder();
                vh.t11 = (TextView) view.findViewById(R.id.recommend_goods_name);
                vh.t22 = (TextView) view.findViewById(R.id.store_name2);
                vh.t33 = (TextView) view.findViewById(R.id.recommend_goods_price);
                vh.iv1 = (ImageView) view.findViewById(R.id.recommend_goods_imageview);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            Goods g = goods.get(i);


            //System.out.println("view"+view);

            vh.t11.setText(g.Goods_name);
            vh.t22.setText(g.Store_name);
            vh.t33.setText(g.Price);
//            int j=Integer.parseInt(g.sImage);
//            vh.iv1.setImageResource(j);
            return view;
        }

        class ViewHolder{
            TextView t11;
            TextView t22;
            TextView t33;
            ImageView iv1;
        }
    }

    public void setContentView(int contentView) {
        this.contentView = contentView;
    }

}
