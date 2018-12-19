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
public class HomeMlssMadapter extends RecyclerView.Adapter<HomeMlssMadapter.ViewHolder>{
    Context context;
    ArrayList<Home.ResultBean.MlssBean.CommodityListBeanXX> list;
    public HomeMlssMadapter(Context context, ArrayList<Home.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.home_mlss_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.fresco_home_mo.setImageURI(list.get(position).getMasterPic());
        holder.tv_home_mo_message.setText(list.get(position).getCommodityName()+"");
        holder.tv_home_mo_price.setText("￥"+list.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMlssListener.clickid(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView fresco_home_mo;
        TextView tv_home_mo_message;
        TextView tv_home_mo_price;


        public ViewHolder(View itemView) {
            super(itemView);
            fresco_home_mo=itemView.findViewById(R.id.fresco_home_mo);
            tv_home_mo_message=itemView.findViewById(R.id.tv_home_mo_message);
            tv_home_mo_price=itemView.findViewById(R.id.tv_home_mo_price);

        }
    }
    public interface OnMlssListener{
        void clickid(int position);

    }
    public OnMlssListener mMlssListener;

    public void setMlssListener(OnMlssListener mlssListener) {
        mMlssListener = mlssListener;
    }
}
