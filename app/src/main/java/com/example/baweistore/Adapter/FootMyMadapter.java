package com.example.baweistore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baweistore.Bean.My_Foot;
import com.example.baweistore.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * date:2018/12/14
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class FootMyMadapter extends XRecyclerView.Adapter<FootMyMadapter.ViewHolder>{
    Context context;
    ArrayList<My_Foot.ResultBean> list;
    public FootMyMadapter(Context context, ArrayList<My_Foot.ResultBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.my_foot_item, null );
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FootMyMadapter.ViewHolder holder, int position) {

        Glide.with(context).load(list.get( position ).getMasterPic()).into(holder.fresco_home);
        /*String commodityName = list.get(position).getCommodityName();
        String substring = commodityName.substring(0,5);*/
        holder.tv_home_title.setText(list.get(position).getCommodityName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        holder.tv_my_foot_data.setText(simpleDateFormat.format(list.get(position).getBrowseTime()));
        holder.tv_home_price.setText("￥"+list.get(position).getPrice());
        holder.tv_home_num.setText("浏览"+list.get(position).getBrowseNum()+"次");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fresco_home;
        TextView tv_home_title;
        TextView tv_home_price;
        TextView tv_home_num;
        TextView tv_my_foot_data;
        public ViewHolder(View itemView) {
            super(itemView);
            fresco_home=itemView.findViewById(R.id.fresco_home);
            tv_home_title=itemView.findViewById(R.id.tv_home_title);
            tv_home_price=itemView.findViewById(R.id.tv_home_price);
            tv_home_num=itemView.findViewById(R.id.tv_home_num);
            tv_my_foot_data=itemView.findViewById(R.id.tv_my_foot_data);
        }
    }
}
