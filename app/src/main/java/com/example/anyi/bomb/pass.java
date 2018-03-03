package com.example.anyi.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


public class pass extends AppCompatActivity {

    private Button bt;


    private EditText E_psw;
    private EditText E_Rpsw;
    private EditText E_Opsw;



    private TextInputLayout L_pwd;
    private TextInputLayout L_Rpwd;
    private TextInputLayout L_Opwd;



    public static String S_pwd;
    public static String S_Rpwd;
    public static String S_Opwd;

    private Toolbar toolbar;


/*

    private ProgressDialog pDialog;

    private static String url_create_user = "http://192.168.43.4/android_connect2/create_user.php";

    private static final String TAG_SUCCESS = "success";

    JSONParser jsonParser = new JSONParser();

*/



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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "c6f075c125f3c78b69088d3c81022306");
        setContentView(R.layout.pass);


        find();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        E_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        E_Rpsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        E_Opsw.setTransformationMethod(PasswordTransformationMethod.getInstance());


        E_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().equals("")) {

                }else{
                    L_pwd.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        E_Opsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().equals("")) {

                }else{
                    L_Opwd.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        E_Rpsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().equals(E_psw.getText().toString())){

                }else{
                    L_Rpwd.setErrorEnabled(false);
                }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean key = true;
                S_pwd = E_psw.getText().toString();
                S_Rpwd = E_Rpsw.getText().toString();
                S_Opwd = E_Opsw.getText().toString();

                if (S_pwd.equals("")){
                    key = false;
                    L_pwd.setError("不能为空");
                    E_psw.setText("");
                }

                if (!S_Rpwd.equals("")&&!S_pwd.equals(S_Rpwd)){
                    key = false;
                    L_Rpwd.setError("两次密码输入不一致，请重新输入");
                    E_Rpsw.setText("");
                }


                if (key) {
                    //creating new user in background thread
                    /* new CreateNewUser(stu_num, psw, tel).execute();*/
                    BmobUser user = BmobUser.getCurrentUser();

                    user.updateCurrentUserPassword(S_Opwd, S_pwd, new UpdateListener() {

                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                BmobUser.logOut();   //清除缓存用户对象
                                Toast.makeText(getApplicationContext(),"密码修改成功，可以用新密码进行登录啦",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(pass.this, Login.class));
                                finish();

                            }else{

                                    Toast.makeText(getApplicationContext(),"失败，请重新操作!"+e.getMessage(),Toast.LENGTH_SHORT).show();

                                System.out.println(e.getMessage());
                                clearE();

                            }
                        }

                    });










    /*
                   user.save(new SaveListener<String>() {
                       @Override
                       public void done(String s, BmobException e) {
                           if (e == null){
                               Toast.makeText(getApplicationContext(),"创建用户成功！",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(CreateUser.this, Login.class));
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"创建用户失败，请重新创建!"+e.getMessage(),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CreateUser.this, CreateUser.class));

                            }
                       }
                   });*/
                }
            }
        });


    }

    private void clearE() {
        E_psw.setText("");
        E_Rpsw.setText("");
        E_Opsw.setText("");
    }

    private void find() {


        toolbar = (Toolbar) findViewById(R.id.toolbar);


        L_pwd= (TextInputLayout) findViewById(R.id.log_psd);
        L_Rpwd = (TextInputLayout) findViewById(R.id.log_Rpsd);
        L_Opwd = (TextInputLayout) findViewById(R.id.log_Opsd);


        E_psw = L_pwd.getEditText();
        E_Rpsw = L_Rpwd.getEditText();
        E_Opsw = L_Opwd.getEditText();


        bt = (Button) findViewById(R.id.commit);
    }


}
