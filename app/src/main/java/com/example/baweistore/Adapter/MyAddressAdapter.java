package com.example.baweistore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.baweistore.Bean.MyAddress;
import com.example.baweistore.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * date:2018/12/14
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class MyAddressAdapter extends XRecyclerView.Adapter<MyAddressAdapter.ViewHolder>{
    Context context;
    ArrayList<MyAddress.ResultBean> list;
    public MyAddressAdapter(Context context, ArrayList<MyAddress.ResultBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.my_address_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressAdapter.ViewHolder holder, int position) {
        holder.tv_address_name.setText(list.get(position).getRealName());
        holder.tv_address_number.setText(list.get(position).getPhone());
        holder.tv_address_message.setText(list.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_address_name;
        TextView tv_address_number;
        TextView tv_address_message;
        RadioButton radio_my_address;
        Button bt_my_address_xiu;
        Button bt_my_address_shan;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_address_name=itemView.findViewById(R.id.tv_address_name);
            tv_address_number=itemView.findViewById(R.id.tv_address_number);
            tv_address_message=itemView.findViewById(R.id.tv_address_message);
            radio_my_address=itemView.findViewById(R.id.radio_my_address);
            bt_my_address_xiu=itemView.findViewById(R.id.bt_my_address_xiu);
            bt_my_address_shan=itemView.findViewById(R.id.bt_my_address_shan);
        }
    }
}
