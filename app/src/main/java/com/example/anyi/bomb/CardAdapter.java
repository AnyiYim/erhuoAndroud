package com.example.anyi.bomb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Anyi on 2018/1/24.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    private Context mContext;
    private List<text> mText;

    static public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;
        TextView MtextView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView = (ImageView) view.findViewById(R.id.PimageView);
            textView = (TextView) view.findViewById(R.id.PtextView);
            MtextView = (TextView) view.findViewById(R.id.PMtextView);
        }
    }

    public CardAdapter(List<text> textList) {
        mText = textList;
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                text tpro = mText.get(position);
                String dataID = tpro.getObjectId();
                Intent intent = new Intent(mContext, pro.class);
                intent.putExtra("extra_data", dataID);
                intent.putExtra("bool",false);
                mContext.startActivity(intent);

            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(final CardAdapter.ViewHolder holder, final int position) {

        String name, price;
        BmobFile image;
        final text mtext = mText.get(position);
        name = mtext.getName();
        price = String.valueOf(mtext.getPrice());
        image = mtext.getIcon();
        System.out.println(image.getFileUrl());
/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        */

        holder.textView.setText(mtext.getName());
        holder.MtextView.setText(price);
        Glide.with(mContext).load(mtext.getIcon().getFileUrl()).into(holder.imageView);
    }

    private Bitmap getPicture(String fileUrl) {
        Bitmap bm=null;
        try{
            URL url=new URL(fileUrl);
            URLConnection connection=url.openConnection();
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            bm= BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bm;
    }

    @Override
    public int getItemCount() {
        return mText.size();
    }

}
