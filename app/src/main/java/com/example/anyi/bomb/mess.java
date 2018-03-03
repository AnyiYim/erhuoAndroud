package com.example.anyi.bomb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;


public class mess extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView title, mess, link;
   // private FloatingActionButton fab;
    private String str="";


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
        setContentView(R.layout.mess);
        Intent intent = getIntent();
        String bID = intent.getStringExtra("extra_Mdata");

        find();

        BmobQuery<buymess> bmobQuery = new BmobQuery<buymess>();
        bmobQuery.getObject(bID, new QueryListener<buymess>() {
            @Override
            public void done(final buymess buymess, BmobException e) {
                if(e==null){


                    title.setText(buymess.getName());
                    mess.setText(buymess.getMess());
                    link.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BmobQuery<BmobUser> userBmobQuery = new BmobQuery<BmobUser>();
                            userBmobQuery.getObject(buymess.getUid(), new QueryListener<BmobUser>() {
                                @Override
                                public void done(BmobUser bmobUser, BmobException e) {
                                    if (e == null) {
                                        str=bmobUser.getMobilePhoneNumber();
                                        Uri smsToUri = Uri.parse("smsto:"+str);

                                        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);

                                        //intent.putExtra("sms_body", smsBody);

                                        startActivity(intent);
                                    }
                                    else {

                                    }
                                }
                            });
                        }
                    });

                }else{
                    //finish();
                }
            }


        });


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }



    private void find() {

        title = (TextView) findViewById(R.id.title);
        mess  = (TextView) findViewById(R.id.mess);
        link  = (TextView) findViewById(R.id.link);
       // fab = (FloatingActionButton) findViewById(R.id.fab);   // 悬浮按钮
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }


}
