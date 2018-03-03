package com.example.anyi.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Anyi on 2018/1/20.
 */

public class userPro extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private DrawerLayout mDrawerLayout;
    //private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View headerLayout;
    private ImageView userlogo;
    private TextView username;
    private TabLayout tabLayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_p);
        Bmob.initialize(this, "c6f075c125f3c78b69088d3c81022306");
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.timeline_viewpager);
        ShortPagerAdapter adapter = new ShortPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);


        findView();
        init();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close
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
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });





    }


    private class ShortPagerAdapter extends FragmentPagerAdapter {
        public String[] mTilte;

        public ShortPagerAdapter(FragmentManager fm) {
            super(fm);
            mTilte = getResources().getStringArray(R.array.tab_short_Title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTilte[position];
        }

        @Override
        public BaseFragment getItem(int position) {
            BaseFragment fragment = FragmentFa.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private void init() {

        setSupportActionBar(toolbar);
       // navigationView.setNavigationItemSelectedListener(new MyNavigationListener());
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // 获取数据
//        BmobQuery<buymess> query = new BmobQuery<buymess>();
//        query.findObjects(new FindListener<buymess>() {
//            @Override
//            public void done(List<buymess> list, BmobException e) {
//                if (e == null) {
//                    System.out.println("查询成功！ " + list.get(0).getName());
//                   // recyclerView.setAdapter(new CardAdapter(list));
//                }
//            }
//        });

        // 下拉刷新
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshProList();
            }
        });





    }

    private void refreshProList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        BmobQuery<text> query = new BmobQuery<text>();
                        query.findObjects(new FindListener<text>() {
                            @Override
                            public void done(List<text> list, BmobException e) {
                                if (e == null) {
                                    System.out.println("查询成功！ " + list.get(0).getName());
                                    CardAdapter adapter = new CardAdapter(list);
                                    //recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        });

                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
            }
        }).start();

    }

    private void findView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
  //      drawerLayout = (DrawerLayout) findViewById(R.id.drawerL);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
      //  headerLayout = navigationView.inflateHeaderView(R.layout.drawer_header);
//        userlogo = (ImageView) headerLayout.findViewById(R.id.userlogo);
  //      username = (TextView) headerLayout.findViewById(R.id.username);
        //recyclerView = (RecyclerView) findViewById(R.id.recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Myuser bmobUser = BmobUser.getCurrentUser(Myuser.class);
        switch (item.getItemId()) {
            case R.id.back_up:
                startActivity(new Intent(userPro.this, Main.class));
                finish();
                break;
            case R.id.sell:
                if(bmobUser != null){
                    startActivity(new Intent(userPro.this, textalbumActivity.class));
                    //finish();
                    // 允许用户使用应用
                }else{
                    startActivity(new Intent(userPro.this, Login.class));
                    Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
                    finish();
                    //缓存用户对象为空时， 可打开用户注册界面…
                }
                break;

            case R.id.buy:
                if(bmobUser != null){
                    startActivity(new Intent(userPro.this, buytext.class));
                    //finish();
                    // 允许用户使用应用
                }else{
                    startActivity(new Intent(userPro.this, Login.class));
                    Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
                    finish();
                    //缓存用户对象为空时， 可打开用户注册界面…
                }
                break;

            default:
        }
        return true;
    }

/*    private class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            drawerLayout.closeDrawer(GravityCompat.START);
            BmobUser bmobUser = BmobUser.getCurrentUser();
            switch (item.getItemId()) {
                case R.id.nav_hall:
                    if(bmobUser != null){
                        startActivity(new Intent(toolbartext.this, userPro.class));
                        //允许用户使用应用
                    }else{
                        startActivity(new Intent(toolbartext.this, Login.class));
                        finish();
                    }
                    break;
                case R.id.nav_about:
                    if(bmobUser != null){
                        startActivity(new Intent(toolbartext.this, user.class));
                        // 允许用户使用应用
                    }else{
                        startActivity(new Intent(toolbartext.this, Login.class));
                        finish();
                    }
                    break;
                case R.id.nav_version:
                    break;
                case R.id.help:
                    break;
                case R.id.quite:

                    if(bmobUser != null){
                        BmobUser.logOut();
                        BmobUser currentUser = BmobUser.getCurrentUser();
                        startActivity(new Intent(toolbartext.this, Main.class));
                        finish();
                        // 允许用户使用应用
                    }else{
                        //缓存用户对象为空时， 可打开用户注册界面…
                    }

                    break;
            }
            return true;
        }
    }*/
}



/*
package com.example.anyi.bomb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

*/
/**
 * Created by Anyi on 2018/1/20.
 *//*


public class userPro extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private DrawerLayout mDrawerLayout;
    //private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View headerLayout;
    private ImageView userlogo;
    private TextView username;
    private TabLayout tabLayout;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_p);

        findView();
        init();

        Bmob.initialize(this, "c6f075c125f3c78b69088d3c81022306");
        ShortPagerAdapter adapter = new ShortPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    //    actionBar.setDisplayHomeAsUpEnabled(true);
        //设置显示三横杠
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });





    }


    private class ShortPagerAdapter extends FragmentPagerAdapter {
        public String[] mTilte;

        public ShortPagerAdapter(FragmentManager fm) {
            super(fm);
            mTilte = getResources().getStringArray(R.array.tab_short_Title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTilte[position];
        }

        @Override
        public BaseFragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private void init() {

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshProList();
            }
        });



    }

    private void refreshProList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        BmobQuery<text> query = new BmobQuery<text>();
                        query.findObjects(new FindListener<text>() {
                            @Override
                            public void done(List<text> list, BmobException e) {
                                if (e == null) {
                                    System.out.println("查询成功！ " + list.get(0).getName());
                                    CardAdapter adapter = new CardAdapter(list);
                                    //recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        });

                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
            }
        }).start();

    }

    private void findView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.timeline_viewpager);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Myuser bmobUser = BmobUser.getCurrentUser(Myuser.class);
        switch (item.getItemId()) {
            case R.id.back_up:
                finish();
                break;
            case R.id.sell:
                startActivity(new Intent(userPro.this, textalbumActivity.class));
                finish();
                break;

            case android.R.id.home:
                finish();
                break;

            case R.id.buy:
                startActivity(new Intent(userPro.this, buytext.class));
                finish();
                break;

            default:
        }
        return true;
    }

}
*/
