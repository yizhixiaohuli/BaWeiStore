package com.example.baweistore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baweistore.Bean.CIrcle_my;
import com.example.baweistore.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * date:2018/12/14
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class MyCircleAdapter extends RecyclerView.Adapter<MyCircleAdapter.ViewHolder>{
    Context context;
    ArrayList<CIrcle_my.ResultBean> list;
    public MyCircleAdapter(Context context, ArrayList<CIrcle_my.ResultBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public MyCircleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.my_circle_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCircleAdapter.ViewHolder holder, int position) {
        holder.fresco_my_circle_tua.setImageURI(list.get(position).getImage());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        holder.my_circle_date.setText(simpleDateFormat.format(list.get(position).getCreateTime()));
        holder.tv_my_circle_message.setText(list.get(position).getContent());
        holder.tv_my_circle_prise.setText(list.get(position).getGreatNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView fresco_my_circle_tua;
        TextView my_circle_date;
        TextView tv_my_circle_message;
        ImageView img_my_circle_prise;
        TextView tv_my_circle_prise;

        public ViewHolder(View itemView) {
            super(itemView);
            fresco_my_circle_tua=itemView.findViewById(R.id.fresco_my_circle_tua);
            my_circle_date=itemView.findViewById(R.id.my_circle_date);
            tv_my_circle_message=itemView.findViewById(R.id.tv_my_circle_message);
            img_my_circle_prise=itemView.findViewById(R.id.img_my_circle_prise);
            tv_my_circle_prise=itemView.findViewById(R.id.tv_my_circle_prise);
        }
    }
}
