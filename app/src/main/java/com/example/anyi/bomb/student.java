package com.example.anyi.bomb;

import cn.bmob.v3.BmobObject;

/**
 * Created by Anyi on 2018/2/25.
 */
public class student extends BmobObject {
    private String name;
    private String num;
    private boolean sex;

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public boolean isSex() {
        return sex;
    }

    public student() {
        this.setTableName("student");
    }

    //其他方法，见上面的代码
}