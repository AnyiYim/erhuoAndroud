package com.example.anyi.bomb;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Anyi on 2018/1/15.
 */

public class buytext extends AppCompatActivity {

    private Button upload;

    private EditText E_name;
    private EditText E_mess;


    private TextInputLayout L_name;
    private TextInputLayout L_mess;

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
        setContentView(R.layout.buytext);
        Bmob.initialize(this, "c6f075c125f3c78b69088d3c81022306");
        find();
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        E_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        E_mess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name,
                        mess;

                if ( E_name.getText().toString().equals("")||E_mess.getText().toString().equals("")){
                    //Toast.makeText(getApplicationContext(),"请提交完整信息！",Toast.LENGTH_SHORT).show();
                    //System.out.println(pimagePath+"\n"+eName.getText().toString());
                }
                else
                {
                    //Toast.makeText(getApplicationContext(),pimagePath,Toast.LENGTH_SHORT).show();
                    //System.out.println(pimagePath+"\n"+eName.getText().toString());
                    name = E_name.getText().toString();
                    mess = E_mess.getText().toString();
                    BmobUser userMe = BmobUser.getCurrentUser();
                    System.out.println("ss");
                    buymess goods = new buymess();
                    goods.setUid(userMe.getObjectId());
                    goods.setName(name);
                    //goods.setPrice(price);
                    goods.setMess(mess);
                    //goods.setIcon(bmobFile);
                    goods.save(new SaveListener<String>() {
                        @Override
                        public void onStart() {
                            Toast.makeText(buytext.this, "正在上传", Toast.LENGTH_SHORT).show();
                            super.onStart();
                        }

                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(buytext.this, "添加数据成功", Toast.LENGTH_SHORT).show();
                                System.out.println("添加数据成功");
                                finish();
                            }
                            else {
                                Toast.makeText(buytext.this, "添加数据失败", Toast.LENGTH_SHORT).show();
                                System.out.println("添加数据失败");
                            }
                        }
                    });
                }

            }
        });


    }

    private void find() {
        //        take_photo = (Button) findViewById(R.id.take_photo);
        //select_photo = (Button) findViewById(R.id.select_photo);
        //imageview = (ImageView) findViewById(R.id.imageview);
        upload = (Button) findViewById(R.id.upload);
        //eName = (EditText) findViewById(R.id.editName);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        L_name = (TextInputLayout) findViewById(R.id.editName);
        //L_price= (TextInputLayout) findViewById(R.id.editPrice);
        L_mess = (TextInputLayout) findViewById(R.id.editMess);
//        L_tel = (TextInputLayout) findViewById(R.id.log_tel);

        E_name = L_name.getEditText();
        //E_price = L_price.getEditText();
        E_mess = L_mess.getEditText();
        //   E_tel = L_tel.getEditText();


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
