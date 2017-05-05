package com.jwei.mysearch.item;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2017/5/4.
 */

public class GoodsCollect extends BmobObject {
    public BmobFile goodscollectimage;
    public String goodscollectname;
    public String goodscollectprice;
    public String goodscollectstore;
    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoodscollectname() {
        return goodscollectname;
    }

    public void setGoodscollectname(String goodscollectname) {
        this.goodscollectname = goodscollectname;
    }

    public BmobFile getGoodscollectimage() {
        return goodscollectimage;
    }

    public void setGoodscollectimage(BmobFile goodscollectimage) {
        this.goodscollectimage = goodscollectimage;
    }

    public String getGoodscollectprice() {
        return goodscollectprice;
    }

    public void setGoodscollectprice(String goodscollectprice) {
        this.goodscollectprice = goodscollectprice;
    }

    public String getGoodscollectstore() {
        return goodscollectstore;
    }

    public void setGoodscollectstore(String goodscollectstore) {
        this.goodscollectstore = goodscollectstore;
    }

}
