package com.example.anyi.bomb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anyi on 2018/1/24.
 */

public class MessAdapter extends RecyclerView.Adapter<MessAdapter.ViewHolder>{
    private Context mContext;
    private List<buymess> mText;

    static public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        //ImageView imageView;
        TextView textView;
        TextView mtextView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            //imageView = (ImageView) view.findViewById(R.id.PimageView);
            textView = (TextView) view.findViewById(R.id.MtextView);
            mtextView = (TextView) view.findViewById(R.id.MView);
        }
    }

    public MessAdapter(List<buymess> textList) {
        mText = textList;
    }

    @Override
    public MessAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.mess_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                buymess tpro = mText.get(position);
                String dataID = tpro.getObjectId();
                Intent intent = new Intent(mContext, mess.class);
                intent.putExtra("extra_Mdata", dataID);
                mContext.startActivity(intent);

            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(final MessAdapter.ViewHolder holder, final int position) {
        String name;
        String mess;
        //BmobFile image;
        final buymess mtext = mText.get(position);
        name = mtext.getName();
        mess = mtext.getMess();
        //image = mtext.getIcon();
        //System.out.println(image.getFileUrl());
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
        holder.mtextView.setText(mess);
        holder.textView.setText(name);
        //Glide.with(mContext).load(mtext.getIcon().getFileUrl()).into(holder.imageView);
    }

/*


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
*/

    @Override
    public int getItemCount() {
        return mText.size();
    }

}
