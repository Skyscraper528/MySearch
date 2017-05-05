package com.jwei.mysearch.item;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by sh on 2017/5/2.
 */

public class MyUser extends BmobUser {
    private String sex;
    private String nick;
    private Integer age;
    private String tel;
    private BmobFile image;
    private  String sEmail;
    private String selfintroduce;

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getSelfintroduce() {
        return selfintroduce;
    }

    public void setSelfintroduce(String selfintroduce) {
        this.selfintroduce = selfintroduce;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public BmobFile getImage() {
        return image;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
