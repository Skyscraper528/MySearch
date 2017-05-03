package com.jwei.mysearch.item;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by sh on 2017/5/2.
 */

public class MyUser extends BmobUser {
    private Boolean sex;
    private String nick;
    private Integer age;
    private String tel;
    private BmobFile image;

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

    private  String sEmail;
    private String selfintroduce;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmailaddr() {
        return Emailaddr;
    }

    public void setEmailaddr(String emailaddr) {
        Emailaddr = emailaddr;
    }

    public String getSlefintroduce() {
        return slefintroduce;
    }

    public void setSlefintroduce(String slefintroduce) {
        this.slefintroduce = slefintroduce;
    }

    private String Emailaddr;
    private String slefintroduce;
//    private String Country;

    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
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
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

//    public String getCountry() {
//        return Country;
//    }

//    public void setCountry(String country) {
//        Country = country;
//    }


}
