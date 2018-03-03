package com.example.anyi.bomb;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Anyi on 2018/1/14.
 */

public class text extends BmobObject {
    private String name;
    private BmobFile icon;
    private double price;
    private String uid;
    private String mess;

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public text() {
        this.setTableName("buymess");
    }

    public text(String name) {
        this.setTableName("buymess");
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BmobFile getIcon() {
        return icon;
    }

    public void setIcon(BmobFile icon) {
        this.icon = icon;
    }
}
