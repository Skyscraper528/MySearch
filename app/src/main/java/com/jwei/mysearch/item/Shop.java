package com.jwei.mysearch.item;

import cn.bmob.v3.BmobObject;

/**
 * Created by sh on 2017/4/6.
 */

public class Shop extends BmobObject{
    private String sname;
    private String mString;
    private String mfile;

    public String getSname() {
        return sname;
    }

    public void setmSname(String mSname) {
        this.sname = mSname;
    }

    public String getString() {
        return mString;
    }

    public void setString(String mString) {
        this.mString = mString;
    }


    public String getmFile() {
        return mfile;
    }

    public void setmFile(String file) {
        mfile=file;
    }



}
