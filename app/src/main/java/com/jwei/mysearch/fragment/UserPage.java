package com.jwei.mysearch.fragment;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jwei.mysearch.CollectionPage;
import com.jwei.mysearch.R;
import com.jwei.mysearch.activity_about_page;
import com.jwei.mysearch.activity_footprint_page;
import com.jwei.mysearch.activity_profile_page;
import com.jwei.mysearch.activity_setting_page;
import com.jwei.mysearch.activity_share_page;
import com.jwei.mysearch.item.MyUser;
import com.jwei.mysearch.item.Set;

import java.util.Vector;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/3/15.
 */

public class UserPage extends Fragment {
    ListView listView;
    public Vector<Set> s = new Vector<>();
    public MyAdapter myAdapter;
    public static final int loading=0x1;
    public Button collect_goods;
    public Button collect_store;

    private ImageView iv_personal_icon;
    private ImageView toSetting;
    private TextView Username;
    private TextView resume;
    public MyUser my = BmobUser.getCurrentUser(MyUser.class);

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_user_page, container,false);

        collect_goods = (Button) view.findViewById(R.id.collect_goods);
        collect_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CollectionPage.class);
                startActivity(intent);
            }
        });

        Username = (TextView) view.findViewById(R.id.username);
        resume = (TextView) view.findViewById(R.id.personal_resume);
        resume.setText(my.getSelfintroduce());

        iv_personal_icon = (ImageView) view.findViewById(R.id.iv_personal_icon);
        iv_personal_icon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_profile_page.class);
                startActivity(intent);
            }
        });

        toSetting = (ImageView) view.findViewById(R.id.to_settingpage);
        toSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),activity_setting_page.class);
                startActivity(intent);
            }
        });

        listView = (ListView) view.findViewById(R.id.user_page_choice);
        myAdapter = new MyAdapter(getActivity());
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView set = (TextView) view.findViewById(R.id.set_choice);
                String s = (String) set.getText();
                switch (s)
                {
                    case "我的发现":
                        Intent intent = new Intent(getActivity(),activity_share_page.class);
                        startActivity(intent);
                        break;
                    case "我的足迹":
                        intent = new Intent(getActivity(),activity_footprint_page.class);
                        startActivity(intent);
                        break;
                    case "关于我们":
                        intent = new Intent(getActivity(),activity_about_page.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        collect_store = (Button) view.findViewById(R.id.collect_store);
        collect_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CollectionPage.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });



        SharedPreferences sp = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String loginName = sp.getString("LoginName", "wrong key");
        Username.setText(loginName);
        return view;
    }


    public int index = 0;
    /*
    * 初始化数据
    * */

    public String[] set_choice={
            "我的发现",
            "我的足迹",
            "关于我们"
    };

    public int[] set_icons={R.mipmap.discover,
            R.mipmap.footprint2,
            R.mipmap.about2
    };

    class MyAdapter extends BaseAdapter {
        private Context context;

        public MyAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return set_choice.length;
        }

        @Override
        public Object getItem(int i) {
            return set_choice[i];
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
                view = View.inflate(context, R.layout.setting_choice, null);
                vh = new ViewHolder();
                vh.set_c = (TextView) view.findViewById(R.id.set_choice);
                vh.set_i = (ImageView) view.findViewById(R.id.set_icon);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            //Set set = s.get(i);


            //System.out.println("view"+view);

            vh.set_c.setText(set_choice[i]);
            vh.set_i.setImageResource(set_icons[i]);
            return view;
        }

        class ViewHolder{
            TextView set_c;
            ImageView set_i;
        }
    }

}
