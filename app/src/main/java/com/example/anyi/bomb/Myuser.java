package com.example.anyi.bomb;

import cn.bmob.v3.BmobUser;

/**
 * Created by Anyi on 2018/2/5.
 */

public class Myuser extends BmobUser {
    private boolean sex;
    private String nick;

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




}
