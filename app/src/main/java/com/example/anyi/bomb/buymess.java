package com.example.anyi.bomb;

import cn.bmob.v3.BmobObject;

/**
 * Created by Anyi on 2018/2/6.
 */

public class buymess extends BmobObject {
    private String name;
    private String uid;
    private String mess;

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public buymess() {
        this.setTableName("buymess");
    }

    public buymess(String name) {
        this.setTableName("buymess");
        this.name = name;
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




}
