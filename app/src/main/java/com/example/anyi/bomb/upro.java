package com.example.anyi.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Anyi on 2018/1/28.
 */

public class upro extends AppCompatActivity {
    private Toolbar ptoolbar;
    private DrawerLayout pdrawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageView;
    private TextView textView;
    private TextView ptextView;
    private TextView ctextView;
    private String str="";
    private text upro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upro_activity);
        Intent intent = getIntent();
        final String bID = intent.getStringExtra("extra_data");
        find();
        init();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        BmobQuery<text> bmobQuery = new BmobQuery<text>();
        bmobQuery.getObject(bID, new QueryListener<text>() {
            @Override
            public void done(text pro, BmobException e) {
                if(e==null){
                    upro = pro;

                    collapsingToolbarLayout.setTitle(pro.getName());
                    Glide.with(getApplicationContext()).load(pro.getIcon().getFileUrl()).into(imageView);
                    textView.setText(pro.getName());
                    ptextView.setText(pro.getMess());
                    ctextView.setText(pro.getPrice()+"");
                    BmobQuery<BmobUser> userBmobQuery = new BmobQuery<BmobUser>();
                    userBmobQuery.getObject(pro.getUid(), new QueryListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if (e == null) {
                                str=bmobUser.getMobilePhoneNumber();
                            }
                            else {

                            }
                        }
                    });
                }else{
                    //finish();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upro.delete(new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhh");
                            finish();
                            Intent intent = new Intent(upro.this, userPro.class);
                            startActivity(intent);
                        }
                        else {
                            System.out.println(e.toString());
                        }
                    }

                });
            }
        });




       /* ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, pdrawerLayout, ptoolbar, R.string.drawer_open, R.string.drawer_close
        ){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        ActionBar actionBar = getSupportActionBar();
        //设置显示返回箭头
        actionBar.setDisplayHomeAsUpEnabled(true);
        //设置显示三横杠
        pdrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        setSupportActionBar(ptoolbar);

    }

    private void find() {
        fab = (FloatingActionButton) findViewById(R.id.fab);   // 悬浮按钮
        ptoolbar = (Toolbar)findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.colltool);
        imageView = (ImageView) findViewById(R.id.piv);
        textView = (TextView) findViewById(R.id.ptv_t);
        ptextView = (TextView) findViewById(R.id.ptv_p);
        ctextView = (TextView) findViewById(R.id.ptv_c);

        //pdrawerLayout = (DrawerLayout) findViewById(R.id.drawerL);
        //navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //recyclerView = (RecyclerView) findViewById(R.id.recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
    }



}
