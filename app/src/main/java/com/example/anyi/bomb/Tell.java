package com.example.anyi.bomb;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


public class Tell extends AppCompatActivity {

    private Button bt;

    private EditText E_tell;

    private TextInputLayout L_tell;

    private TextView textView;

    public static String str;
    public static String ntel;
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
        setContentView(R.layout.tell);


        find();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        textView.setText(textView.getText()+BmobUser.getCurrentUser().getMobilePhoneNumber());

        E_tell.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length() > 13){
                    L_tell.setError("请输入正确学号");
                }else{
                    L_tell.setErrorEnabled(false);
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
                ntel = E_tell.getText().toString();

                if (ntel.equals("")){
                    key = false;
                    L_tell.setError("不能为空");
                    E_tell.setText("");
                }


                if (key) {
                    //creating new user in background thread
                    /* new CreateNewUser(stu_num, psw, tel).execute();*/
                    BmobUser newUser = new BmobUser();
                    newUser.setMobilePhoneNumber(ntel);
                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    newUser.update(bmobUser.getObjectId(),new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(getApplicationContext(),"更改数据成功！",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Tell.this, user.class));
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"更改数据失败，请重新操作!",Toast.LENGTH_SHORT).show();
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
        E_tell.setText("");
        ntel = "";
    }

    private void find() {


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        L_tell = (TextInputLayout) findViewById(R.id.tell);

        E_tell = L_tell.getEditText();

        textView = (TextView) findViewById(R.id.str);

        bt = (Button) findViewById(R.id.commit);
    }


}
