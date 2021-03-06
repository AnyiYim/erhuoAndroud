package com.example.anyi.bomb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/*
*  大厅，实现了列表表单展示
*
* */


public class hall extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

/*    BmobUser bmobUser = BmobUser.getCurrentUser();*/
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);
        listView = (ListView)findViewById(R.id.lv);
        listView.setOnItemLongClickListener(this);


        loadDate();


    }

    private void loadDate() {
        BmobQuery<text> query = new BmobQuery<text>();
        query.findObjects(new FindListener<text>() {
            @Override
            public void done(List<text> list, BmobException e) {
                if (e == null) {
                    System.out.println("查询成功！ " + list.get(0).getName());
                    listView.setAdapter(new MyAdapter(hall.this, list));
                }
            }
        });
    }



    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
}
