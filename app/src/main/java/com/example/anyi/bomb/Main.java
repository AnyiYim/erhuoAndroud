package com.example.anyi.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by Anyi on 2018/1/26.
 */

public class Main extends AppCompatActivity implements View.OnClickListener {

    private Button xbt;
    private Button ubt;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        find();
        Bmob.initialize(this, "c6f075c125f3c78b69088d3c81022306");
        xbt.setOnClickListener(this);
        ubt.setOnClickListener(this);

    }

    private void find() {
        xbt = (Button)findViewById(R.id.bt_xxlogin);
        ubt = (Button)findViewById(R.id.bt_userlogin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_xxlogin:
                BmobUser.logOut();   //清除缓存用户对象
                startActivity(new Intent(Main.this, toolbartext.class));
                break;
            case R.id.bt_userlogin:
                startActivity(new Intent(Main.this, Login.class));
                break;
            default:
                break;
        }
    }
}
