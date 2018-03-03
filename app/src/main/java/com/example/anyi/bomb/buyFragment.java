package com.example.anyi.bomb;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Anyi on 2018/2/6.
 */

public class buyFragment extends BaseFragment {
    @Override
    protected void loadData() {
        Toast.makeText(mContent,"Fragment头条加载数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View initView() {
        final RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(getContext()).inflate(R.layout.sell, null);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        BmobQuery<buymess> query = new BmobQuery<buymess>();
        query.findObjects(new FindListener<buymess>() {
            @Override
            public void done(List<buymess> list, BmobException e) {
                if (e == null) {
                    System.out.println("查询成功！ " + list.get(0).getName());
                    recyclerView.setAdapter(new MessAdapter(list));
                }
            }

        });

        return recyclerView;
    }
}