package com.example.baweistore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baweistore.Bean.Home;
import com.example.baweistore.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * date:2018/12/10
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class HomeMadapter extends RecyclerView.Adapter<HomeMadapter.ViewHolder>{
    Context context;
    ArrayList<Home.ResultBean.RxxpBean.CommodityListBean> list;
    public HomeMadapter(Context context, ArrayList<Home.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.home_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.fresco_home.setImageURI(list.get(position).getMasterPic());
        String commodityName = list.get(position).getCommodityName();
        String substring = commodityName.substring(0,3);

        holder.tv_home_title.setText(substring+"...");
        holder.tv_home_price.setText("￥"+list.get(position).getPrice());
        holder.tv_home_num.setText("已售"+list.get(position).getSaleNum()+"件");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnclickListener.clickid(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView fresco_home;
        TextView tv_home_title;
        TextView tv_home_price;
        TextView tv_home_num;

        public ViewHolder(View itemView) {
            super(itemView);
            fresco_home=itemView.findViewById(R.id.fresco_home);
            tv_home_title=itemView.findViewById(R.id.tv_home_title);
            tv_home_price=itemView.findViewById(R.id.tv_home_price);
            tv_home_num=itemView.findViewById(R.id.tv_home_num);

        }
    }

    public interface OnclickListener{
        void clickid(int position);

    }
    public OnclickListener mOnclickListener;

    public void setOnclickListener(OnclickListener onclickListener) {
        mOnclickListener = onclickListener;
    }
}
