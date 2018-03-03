package com.example.anyi.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


public class CreateUser extends AppCompatActivity {

    private Button bt;
    private Button bt2;

    private EditText E_stu_num;
    private EditText E_psw;
    private EditText E_Rpsw;
    private EditText E_tel;
    private EditText E_Mes;

    private TextInputLayout L_num;
    private TextInputLayout L_pwd;
    private TextInputLayout L_Rpwd;
    private TextInputLayout L_tel;
    private TextInputLayout L_Mes;


    public static String stu_num;
    public static String psw;
    public static String Rpsw;
    public static String tel;
    public static String Mes;

    private MyCountTimer timer;
/*

    private ProgressDialog pDialog;

    private static String url_create_user = "http://192.168.43.4/android_connect2/create_user.php";

    private static final String TAG_SUCCESS = "success";

    JSONParser jsonParser = new JSONParser();

*/



    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            bt2.setText((millisUntilFinished / 1000) +"秒后重发");
        }
        @Override
        public void onFinish() {
            bt2.setText("重新发送验证码");
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        find();




        E_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        E_Rpsw.setTransformationMethod(PasswordTransformationMethod.getInstance());

        E_stu_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length() > 8){
                    L_num.setError("请输入正确学号");
                }else{
                    L_num.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

        E_Mes.addTextChangedListener(new TextWatcher() {
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

        E_tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().equals("")) {

                }else{
                    L_tel.setErrorEnabled(false);
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
                stu_num = E_stu_num.getText().toString();
                psw = E_psw.getText().toString();
                Rpsw = E_Rpsw.getText().toString();
                tel = E_tel.getText().toString();
                Mes = E_Mes.getText().toString();


                if (Mes.equals("")){
                    key = false;
                    L_Mes.setError("验证码不能为空");
                    E_Mes.setText("");
                }
                if (psw.equals("")){
                    key = false;
                    L_pwd.setError("密码不能为空");
                    E_Rpsw.setText("");
                }
                if (stu_num.equals("")){
                    key = false;
                    L_num.setError("学号不能为空");
                }
                if (tel.equals("")){
                    key = false;
                    L_tel.setError("电话不能为空");
                }
                if (Rpsw.equals("")){
                    key = false;
                    L_Rpwd.setError("请再次输入密码");
                }
                if (!psw.equals("")&&!psw.equals(Rpsw)){
                    key = false;
                    L_Rpwd.setError("两次密码输入不一致，请重新输入");
                    E_Rpsw.setText("");
                }

                if (key) {



                   BmobSMS.verifySmsCode(tel, Mes,new UpdateListener() {
                        @Override
                        public void done(BmobException ex) {
                            if (ex==null){
                                BmobQuery<student> query = new BmobQuery<student>();
                                query.addWhereEqualTo("num", stu_num);
                                query.findObjects(new FindListener<student>() {
                                    @Override
                                    public void done(List<student> object, BmobException e) {
                                        if(e==null){
                                            for (student st_obj : object){
                                                Myuser nb = new Myuser();
                                                nb.setUsername(stu_num);
                                                nb.setPassword(psw);
                                                nb.setMobilePhoneNumber(tel);
                                                nb.setNick(st_obj.getName());
                                                nb.setSex(st_obj.isSex());
                                                nb.signUp(new SaveListener<Myuser>() {
                                                    @Override
                                                    public void done(Myuser s, BmobException e) {
                                                        if (e == null){
                                                            Toast.makeText(getApplicationContext(),"创建用户成功！",Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(CreateUser.this, Login.class));
                                                            finish();
                                                        }
                                                        else {
                                                            Toast.makeText(getApplicationContext(),"创建用户失败，请重新创建!",Toast.LENGTH_SHORT).show();
                                                            clearE();
                                                        }
                                                    }
                                                });
                                                break;
                                            }
                                        }
//                    toast("查询成功：共"+object.size()+"条数据。");
                                        else{
                                            // Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                                        }
                                    }
                                });

                            }
                            else {
                                Log.i("bmob", "验证失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                            }
                        }
                    });


                    //creating new user in background thread
                    /* new CreateNewUser(stu_num, psw, tel).execute();*/

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


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel = E_tel.getText().toString();
                timer = new MyCountTimer(60000, 1000);
                timer.start();
                BmobSMS.requestSMSCode(tel, "erhuo", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e==null){
                            Toast.makeText(getApplicationContext(),"验证码发送成功",Toast.LENGTH_SHORT).show();
                        }
                        else {

                        }
                    }
                });
            }
        });

    }

    private void clearE() {
        E_tel.setText("");
        E_psw.setText("");
        E_Rpsw.setText("");
        E_stu_num.setText("");
        E_Mes.setText("");
        stu_num = psw = Rpsw = tel =  Mes = "";
    }

    private void find() {

        L_num = (TextInputLayout) findViewById(R.id.log_num);
        L_pwd= (TextInputLayout) findViewById(R.id.log_psd);
        L_Rpwd = (TextInputLayout) findViewById(R.id.log_Rpsd);
        L_tel = (TextInputLayout) findViewById(R.id.log_tel);
        L_Mes = (TextInputLayout) findViewById(R.id.log_Mes);

        E_stu_num = L_num.getEditText();
        E_psw = L_pwd.getEditText();
        E_Rpsw = L_Rpwd.getEditText();
        E_tel = L_tel.getEditText();
        E_Mes = L_Mes.getEditText();





        bt = (Button) findViewById(R.id.Bt_1);
        bt2 = (Button) findViewById(R.id.Bt_2);
    }


   /* class CreateNewUser extends AsyncTask<String, String, String> {

        *//**
         * Before starting background thread Show Progress Dialog
         *
         * *//*
        String stunum, password, telnum;

        public CreateNewUser(String stu_num, String psw, String tel) {
            stunum = stu_num;
            password = psw;
            telnum = tel;

        }


        @Override




        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateUser.this);
            pDialog.setMessage("Creating Product..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        *//**
         * Creating user
         * *//*

        protected String doInBackground(String... strings) {


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(url_create_user);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        // 设置请求方式
                        httpURLConnection.setRequestMethod("POST");
                        // 设置是否向httpUrlConnection输出，因为这个是POST请求，参数要放在
                        // http正文内，因此需要设为true，默认情况下是false；
                        httpURLConnection.setDoOutput(true);
                        // 设置是否从httpUrlConnection读入，默认情况下是true；
                        httpURLConnection.setDoInput(true);
                        // 设置POST请求不使用缓存
                        httpURLConnection.setUseCaches(false);
                        // 设置请求的超时时间
                        httpURLConnection.setReadTimeout(5000);
                        httpURLConnection.setConnectTimeout(5000);
                        // 设定传达的内容类型是可序列化的Java对象
                        httpURLConnection.setRequestProperty("Content-type","application/json;charset=utf-8");
                        httpURLConnection.connect();

                        // POST请求

                        int responseCode = httpURLConnection.getResponseCode();
                        if (responseCode == 200) {
                            OutputStream out = httpURLConnection.getOutputStream();
                            JSONObject obj = new JSONObject();
                            obj.put("user_number",stunum);
                            obj.put("user_password",password);
                            obj.put("user_phone",telnum);
                            Log.v("TAG",obj.toString());
                            out.write(obj.toString().getBytes());
                            out.flush();
                            out.close();

                            //读取响应
                            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            String lines;
                            StringBuilder sb = new StringBuilder("");
                            while ((lines = reader.readLine()) != null) {
                                lines = new String(lines.getBytes(),"utf-8");
                                sb.append(lines);
                            }
                            Log.v("TAG",sb.toString());
                            reader.close();
                            //断开连接
                            httpURLConnection.disconnect();

                        }
                        else {
                            Log.v("TAG","访问失败：" + responseCode);
                        }





                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();





*//*


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("stu_num", stunum));
            params.add(new BasicNameValuePair("psw", password));
            params.add(new BasicNameValuePair("tel", telnum));

            Log.d("request!", "starting");

            // getting JSON Object
            // Note that create user url accept POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_user, "POST", params);


            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created user
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);

                    finish();


                }
                else {

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }*//*
            return null;


        }

        protected void onPostExecute(String file_url) {

        }

    }*/
}
