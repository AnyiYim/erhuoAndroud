package com.example.anyi.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class Login extends AppCompatActivity {

    private EditText et_num;
    private EditText et_pwd;
    private TextView tv_cru;
    private Button bt_login;
    private TextInputLayout usernameW;
    private TextInputLayout passwordW;

    public static String stu_num;
    public static String psw;
/*

    private ProgressDialog progressDialog;

    JSONParser jsonParser = new JSONParser();

    private static final String url_login_check = "http://192.168.43.4/android_connect2/get_user_password.php";
    // single user user


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_USER = "user";
    private static final String TAG_NUM = "stu_num";
    private static final String TAG_PSW = "psw";

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        find();
        et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

        et_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length() > 8){
                    usernameW.setError("请输入正确学号");
                }else{
                    usernameW.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_pwd.addTextChangedListener(new TextWatcher() {
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




        tv_cru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,CreateUser.class);
                startActivity(intent);
            }
        });


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stu_num = et_num.getText().toString();
                psw = et_pwd.getText().toString();

                if (!stu_num.equals("")&&!psw.equals("")) {
                    BmobUser.loginByAccount(stu_num, psw, new LogInListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if (bmobUser != null){
                                Toast.makeText(getApplicationContext(),"登录成功！",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this,toolbartext.class));
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"密码错误，请重新输入学号和密码！",Toast.LENGTH_SHORT).show();
                                et_num.setText("");
                                et_pwd.setText("");
                            }
                        }
                    });
                    /*new logincheck(stu_num, psw).execute();*/
                }
                else {
                    Toast.makeText(getApplicationContext(),"学号或密码不能为空！",Toast.LENGTH_SHORT).show();
                    et_num.setText("");
                    et_pwd.setText("");
                }

            }
        });




    }

    private void find() {
       /* et_num = (EditText) findViewById(R.id.Et_1);
        et_pwd = (EditText) findViewById(R.id.Et_2);*/
        tv_cru = (TextView) findViewById(R.id.CrU);
        bt_login = (Button) findViewById(R.id.Bt_1);
        usernameW = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordW = (TextInputLayout) findViewById(R.id.pwdWrapper);
        et_num = usernameW.getEditText();
        et_pwd = passwordW.getEditText();
    }



/*
    private class logincheck extends AsyncTask<String, String, String> {

        String stunum, password;

        public logincheck(String stu_num, String psw) {
            stunum = stu_num;
            password = psw;

        }

        @Override
        protected String doInBackground(String... strings) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {

                        // 用户登录存在用户名和密码的参数，如果是GET方式，需要将参数加载URL后面
                        String data = "user_number=" + stunum + "&user_password="+password;
                        URL url = new URL("url_login_check?"+data);
                        // 通过url.openConnection()获得HttpURLConnection
                        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                        // 设置HttpURLConnection的各项参数
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(10000); // 连接的超时时间
                        conn.setReadTimeout(5000); // 读数据的超时时间

                        // 设定传达的内容类型是可序列化的Java对象
                        conn.setRequestProperty("Content-type","application/json;charset=utf-8");
                        conn.connect();

                        // GET请求

                        int responseCode = conn.getResponseCode();
                        if (responseCode == 200) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            String lines;
                            StringBuilder sb = new StringBuilder("");
                            while ((lines = reader.readLine()) != null) {
                                lines = new String(lines.getBytes(),"utf-8");
                                sb.append(lines);
                            }
                            Log.v("TAG",sb.toString());
                            if (sb.toString().contains("\"success\":1")) {
                                Intent intent = new Intent(Login.this, hall.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplication(),sb.toString(),Toast.LENGTH_SHORT).show();
                            }
                            reader.close();
                            //断开连接
                            conn.disconnect();


                        }








                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }


                }
            });


            *//*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int success;
                    try {

                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("stu_num",stunum));
                        params.add(new BasicNameValuePair("psw",password));


                        JSONObject json = jsonParser.makeHttpRequest(url_login_check, "GET", params);

                        Log.d("Single Product Details", json.toString());

                        success = json.getInt(TAG_SUCCESS);

                        if(success == 1) {
                            Toast.makeText(getApplication(),"成功啦，我知道不会成功的。。",Toast.LENGTH_SHORT).show();
                            *//**//*
                            Intent intent = new Intent(Login.this, hall.class);
                            startActivity(intent);
                            finish();*//**//*
                        }
                        else {
                            String message = json.getString(TAG_MESSAGE);
                            Toast.makeText(getApplication(),message,Toast.LENGTH_SHORT).show();
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });*//*


            return null;

        }


        protected void onPostExecute(String file_url) {

        }
    }*/
}
