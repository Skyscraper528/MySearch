package com.jwei.mysearch.item;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2017/5/6.
 */

public class StoreCollect extends BmobObject {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String username;
    public String getCollect_store_name() {
        return collect_store_name;
    }

    public void setCollect_store_name(String collect_store_name) {
        this.collect_store_name = collect_store_name;
    }

    public String collect_store_name;

    public String getStore_distance() {
        return store_distance;
    }

    public void setStore_distance(String store_distance) {
        this.store_distance = store_distance;
    }

    public String store_distance;

    public BmobFile getStore_pic() {
        return store_pic;
    }

    public void setStore_pic(BmobFile store_pic) {
        this.store_pic = store_pic;
    }

    public BmobFile store_pic;

}
