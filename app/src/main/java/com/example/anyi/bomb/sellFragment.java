package com.example.anyi.bomb;

/**
 * Created by Anyi on 2018/2/6.
 */

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by LW on 2017/4/21.
 */
public class sellFragment extends BaseFragment {
    @Override
    protected void loadData() {
        //Toast.makeText(mContent,"Fragment头条加载数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View initView() {
        final RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(getContext()).inflate(R.layout.sell, null);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        BmobQuery<text> query = new BmobQuery<text>();
        query.findObjects(new FindListener<text>() {
            @Override
            public void done(List<text> list, BmobException e) {
                if (e == null) {
                    System.out.println("查询成功！ " + list.get(0).getName());
                    recyclerView.setAdapter(new CardAdapter(list));
                }
            }
        });

        return recyclerView;
    }
}