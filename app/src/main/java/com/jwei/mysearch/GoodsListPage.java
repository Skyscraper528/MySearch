package com.jwei.mysearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.jwei.mysearch.item.Goods;

import java.util.Vector;

public class GoodsListPage extends AppCompatActivity implements AbsListView.OnScrollListener{
    ListView listView;
    Spinner condition;
    public Vector<Goods> goods = new Vector<>();
    public MyAdapter myAdapter;
    public static final int loading=0x1;
    private Button back1;
    private EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        listView = (ListView) findViewById(R.id.listview5);
        View footerView = getLayoutInflater().inflate(R.layout.loading,null);
        listView.addFooterView(footerView);
        listView.setOnScrollListener(this);
        initData();
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView goodsname = (TextView) view.findViewById(R.id.goods_name);
                String goods_name = (String) goodsname.getText();
                TextView storename = (TextView) view.findViewById(R.id.store_name);
                String store_name = (String) storename.getText();
                TextView distance = (TextView) view.findViewById(R.id.distance);
                String distance_ = (String) distance.getText();
                TextView goodsprice = (TextView) view.findViewById(R.id.goods_price);
                String goods_price = (String) goodsprice.getText();
                ImageView pic = (ImageView) view.findViewById(R.id.imageview2);
                Drawable image = pic.getDrawable();
                BitmapDrawable bd = (BitmapDrawable) image;
                Bitmap bitmap = bd.getBitmap();

                Intent intent=new Intent(GoodsListPage.this,GoodDetail.class);
                intent.putExtra("id",1);
                intent.putExtra("image", bitmap);
                intent.putExtra("goodsname",goods_name);
                intent.putExtra("storename",store_name);
                intent.putExtra("distance",distance_);
                intent.putExtra("goodsprice",goods_price);
                startActivity(intent);
            }
        });


        condition = (Spinner) findViewById(R.id.spinner_condition);
        ArrayAdapter< String> adapter = new ArrayAdapter< String>( this,R.layout.spinner_style);
        adapter.add("按距离排序");
        adapter.add("按价格排序");
        condition.setAdapter(adapter);
        //initGoods();
        //GoodsAdapter adapter=new GoodsAdapter(this,R.layout.goods_item,goodsList);
        //ListView listView=(ListView)findViewById(R.id.goods_listview);
        //listView.setAdapter(adapter);
    }

    public int index = 0;
    /*
    * 初始化数据
    * */

    public String[] Goods_name={
            "疯狂Android讲义 李刚疯狂的Android讲义教程从入门到精通",
            "林宥嘉 大小说家 CD+三张明信片+写真歌词本",
            "短袖T恤男 经典卡通动物印花圆领TEE",
            "马可彩铅笔72/48色油性彩铅专业绘画美术填图笔"
    };

    public String[] Store_name={
            "瑞意图书专营店",
            "天沐音像专营店",
            "lifeafterlife旗舰店",
            "标逸办公专营店"
    };

    public String[] Price={
            "¥88.60",
            "¥49.00",
            "¥59.00",
            "¥72.00"
    };

    public String[] Distance={
            "2.6km",
            "4.8km",
            "4.5km",
            "1.5km"
    };

    public int[] images={R.mipmap.goods1,
            R.mipmap.goods2,
            R.mipmap.goods3,
            R.mipmap.goods4
    };

    public void initData(){
        for(int i=0;i < 10&&index<Goods_name.length;i++){
            Goods g = new Goods();
            g.Goods_name = Goods_name[index];
            g.Store_name = Store_name[index];
            g.Distance = Distance[index];
            g.Price = Price[index];
            String s=String.valueOf(images[index]);
            g.sImage = s;
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
                view = View.inflate(context, R.layout.goods_item, null);
                vh = new ViewHolder();
                vh.t11 = (TextView) view.findViewById(R.id.goods_name);
                vh.t22 = (TextView) view.findViewById(R.id.store_name);
                vh.t33 = (TextView) view.findViewById(R.id.distance);
                vh.t44 = (TextView) view.findViewById(R.id.goods_price);
                vh.iv1 = (ImageView) view.findViewById(R.id.imageview2);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            Goods g = goods.get(i);


            //System.out.println("view"+view);

            vh.t11.setText(g.Goods_name);
            vh.t22.setText(g.Store_name);
            vh.t33.setText(g.Distance);
            vh.t44.setText(g.Price);
            int j=Integer.parseInt(g.sImage);
            vh.iv1.setImageResource(j);
            return view;
        }

        class ViewHolder{
            TextView t11;
            TextView t22;
            TextView t33;
            TextView t44;
            ImageView iv1;
        }
    }
}
