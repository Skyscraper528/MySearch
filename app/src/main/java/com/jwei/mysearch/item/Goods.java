package com.jwei.mysearch.item;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/12/19.
 */

public class Goods extends BmobObject{

    public String Goods_name;
    public String Store_name;
    public String Price;
    public String Distance;
    public BmobFile sImage;
    public int Hot=0;

    public int getHot() {

        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int hot;

    public Goods(){}

    public Goods(String Goods_name,String Store_name,String Price,BmobFile sImage){
        this.Goods_name = Goods_name;
        this.Store_name = Store_name;
        this.Price = Price;
        this.sImage=sImage;
    }

    public Goods(String Goods_name,String Store_name,String Distance,String Price,BmobFile Goods_imageid){
        this.Goods_name = Goods_name;
        this.Store_name = Store_name;
        this.Distance = Distance;
        this.Price = Price;
        this.sImage= Goods_imageid;
    }


    public String getGoods_name() {
        return Goods_name;
    }
    public void setGoods_name(String Goods_name) {
        this.Goods_name = Goods_name;
    }

    public String getStore_name() {
        return Store_name;
    }
    public void setStore_name(String Store_name) {
        this.Store_name = Store_name;
    }

    public String getPrice() {
        return Price;
    }
    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getDistance() {
        return Distance;
    }
    public void setDistance(String Distance) {
        this.Distance = Distance;
    }

    public BmobFile getGoods_image() {
        return sImage;
    }
    public void setGoods_image(BmobFile Goods_image) {
        this.sImage = Goods_image;
    }
}
