package com.jwei.mysearch.item;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2017/3/28.
 */

public class Store extends BmobObject{
    public String Store_name;
    public String Distance;
    public BmobFile Store_imageid;
    public String self_introduce;

    public String getSelf_introduce() {
        return self_introduce;
    }

    public void setSelf_introduce(String self_introduce) {
        this.self_introduce = self_introduce;
    }



    public String Store_name() {
        return Store_name;
    }
    public void setStore_name(String Store_name) {
        this.Store_name = Store_name;
    }

    public String Distance() {
        return Distance;
    }
    public void setDistance(String Distance) {
        this.Distance = Distance;
    }

    public BmobFile Store_imageid() {
        return Store_imageid;
    }

    public void setStore_imageid(BmobFile Store_imageid) {
        this.Store_imageid = Store_imageid;
    }
}
