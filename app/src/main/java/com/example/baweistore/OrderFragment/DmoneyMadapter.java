package com.example.baweistore.OrderFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.baweistore.Bean.Home_details;
import com.example.baweistore.Bean.OrderList;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.example.baweistore.ShopAddView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * date:2018/12/18
 * author:别的小朋友(别的小朋友)
 * function:
 */
class DmoneyMadapter extends RecyclerView.Adapter<DmoneyMadapter.ViewHolder>{
    Context context;
    ArrayList<OrderList.OrderListBean> list;
    public DmoneyMadapter(Context context, ArrayList<OrderList.OrderListBean> list) {
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.demoney_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String Pictures =list.get(position).getDetailList().get(position).getCommodityPic();
        String[] split = Pictures.split(",");
        holder.mycart_simple.setImageURI(split[0]);
        holder.mycart_name.setText(list.get(position).getDetailList().get(position).getCommodityName());
        holder.tv_orderall_ding.setText("订单号:"+list.get(position).getOrderId());
        // holder.tv_orderall_data.setText(list.get(position).getDetailList().get(position).getOrderDetailId()+"");
        holder.mycart_price.setText("￥"+list.get(position).getPayAmount());
        holder.demoney_pai.setText("派送公司："+list.get(position).getExpressCompName());
        holder.demoney_dan.setText("快递单号"+list.get(position).getExpressSn());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_orderall_data;
        TextView tv_orderall_ding;
        TextView mycart_name;
        SimpleDraweeView mycart_simple;
        TextView mycart_price;
        TextView demoney_pai;
        TextView demoney_dan;
        Button bt_zhi;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_orderall_data = itemView.findViewById(R.id.tv_orderall_data);
            tv_orderall_ding = itemView.findViewById(R.id.tv_orderall_ding);
            mycart_name = itemView.findViewById(R.id.mycart_name);
            mycart_simple = itemView.findViewById(R.id.mycart_simple);
            mycart_price = itemView.findViewById(R.id.mycart_price);
            demoney_pai = itemView.findViewById(R.id.demoney_pai);
            demoney_dan = itemView.findViewById(R.id.demoney_dan);
        }
    }
}
