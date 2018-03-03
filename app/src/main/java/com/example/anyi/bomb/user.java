package com.example.anyi.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by Anyi on 2018/1/15.
 */

public class user extends AppCompatActivity {

    //private Button upload;

    private TextView num, tel, name, sname, sex;

    private Toolbar toolbar;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_mess);
        Bmob.initialize(this, "c6f075c125f3c78b69088d3c81022306");
        find();
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.tell) {
                    startActivity(new Intent(user.this, Tell.class));
                    finish();
                } else if (item.getItemId() == R.id.pass) {
                    startActivity(new Intent(user.this, pass.class));
                    finish();
                }
                return true;
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Myuser user = BmobUser.getCurrentUser(Myuser.class);
        num.setText(user.getUsername());
        name.setText(user.getNick());
        tel.setText(user.getMobilePhoneNumber());
        sex.setText(user.getSex()?"男":"女");
        sname.setText("中山大学新华学院");




    }

    private void find() {

        num = (TextView) findViewById(R.id.num);
        name = (TextView) findViewById(R.id.name);
        sname = (TextView) findViewById(R.id.sname);
        tel = (TextView) findViewById(R.id.tel);
        sex = (TextView) findViewById(R.id.sex);

        toolbar = (Toolbar) findViewById(R.id.toolbar);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.utoolbar, menu);
        return true;
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
