package com.example.anyi.bomb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.datatype.BmobFile;


/*
*   定义了适配器，改下T就好了。
*
* */


public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<text> list;

    public MyAdapter(Context context, List<text> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            String name;
            String num;
            BmobFile image;

            name = list.get(position).getName();
            num  = list.get(position).getObjectId();
//            image = list.get(position).getIcon();
//            System.out.println(image.getFileUrl());

            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_layout, null); // 实例化一个布局文件
            viewHolder = new ViewHolder();
            viewHolder.iv_img  = (ImageView) view.findViewById(R.id.iv);
            viewHolder.tv_name = (TextView)  view.findViewById(R.id.tv_name);
            viewHolder.tv_num  = (TextView)  view.findViewById(R.id.tv_id);
            view.setTag(viewHolder);

//            不能直接在主线程中进行从网络端获取信息，而需要单独开一个子线程完成从网络获取信息
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    根据表中的url地址来获取得到图片 （Bitmap类型）
//                    final Bitmap bitmap = getPicture(list.get(position).getIcon().getFileUrl());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*viewHolder.iv_img.post(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("*******************");
                            viewHolder.iv_img.setImageResource(bitmap); // 将图片放到视图中去
                        }
                    });*/

                }
            }).start();
            viewHolder.tv_name.setText(name);
            viewHolder.tv_num.setText(num);

        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }


    private class ViewHolder {
        ImageView iv_img;
        TextView tv_name;
        TextView tv_num;
    }
}
