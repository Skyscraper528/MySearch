package com.jwei.mysearch.item;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/12/19.
 */

public class Goods extends BmobObject{

    public String Goods_name;
    public String Store_name;
    public String Price;
    public String Distance;
    public String sImage;

    public int getHot() {

        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int hot;

    public Goods(){}

    public Goods(String Goods_name,String Store_name,String Price,String sImage){
        this.Goods_name = Goods_name;
        this.Store_name = Store_name;
        this.Price = Price;
        this.sImage=sImage;
    }

    public Goods(String Goods_name,String Store_name,String Distance,String Price,String Goods_imageid){
        this.Goods_name = Goods_name;
        this.Store_name = Store_name;
        this.Distance = Distance;
        this.Price = Price;
        this.sImage= Goods_imageid;
    }


    public String Goods_name() {
        return Goods_name;
    }
    public void setGoods_name(String Goods_name) {
        this.Goods_name = Goods_name;
    }

    public String Store_name() {
        return Store_name;
    }
    public void setStore_name(String Store_name) {
        this.Store_name = Store_name;
    }

    public String Price() {
        return Price;
    }
    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String Distance() {
        return Distance;
    }
    public void setDistance(String Distance) {
        this.Distance = Distance;
    }

    public String Goods_imageid() {
        return sImage;
    }
    public void setGoods_imageid(String Goods_imageid) {
        this.sImage = Goods_imageid;
    }
}
