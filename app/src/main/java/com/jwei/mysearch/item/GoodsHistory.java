package com.jwei.mysearch.item;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/4.
 */

public class GoodsHistory extends BmobObject {
    public String goodsimagefootprint;
    public String goodsnamefootprint;
    public String goodspricefootprint;
    public String goodsstorefootprint;
    public String username;

    public GoodsHistory(String username,String Goods_name,String Store_name,String Price,String sImage){
        this.username = username;
        this.goodsnamefootprint = Goods_name;
        this.goodsstorefootprint = Store_name;
        this.goodspricefootprint = Price;
        this.goodsimagefootprint=sImage;
    }

    public String getGoodsimagefootprint() {
        return goodsimagefootprint;
    }

    public void setGoodsimagefootprint(String goodsimagefootprint) {
        this.goodsimagefootprint = goodsimagefootprint;
    }

    public String getGoodsnamefootprint() {
        return goodsnamefootprint;
    }

    public void setGoodsnamefootprint(String goodsnamefootprint) {
        this.goodsnamefootprint = goodsnamefootprint;
    }

    public String getGoodspricefootprint() {
        return goodspricefootprint;
    }

    public void setGoodspricefootprint(String goodspricefootprint) {
        this.goodspricefootprint = goodspricefootprint;
    }

    public String getGoodsstorefootprint() {
        return goodsstorefootprint;
    }

    public void setGoodsstorefootprint(String goodsstorefootprint) {
        this.goodsstorefootprint = goodsstorefootprint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
