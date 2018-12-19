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

import java.util.List;

/**
 * date:2018/12/12
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class HomePinMadapter extends RecyclerView.Adapter<HomePinMadapter.ViewHolder> {
    Context context;
    List<Home.ResultBean.PzshBean.CommodityListBeanX> list;
    public HomePinMadapter(Context context, List<Home.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public HomePinMadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.home_pzsh_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePinMadapter.ViewHolder holder, final int position) {
        holder.fresco_home_pzsh.setImageURI(list.get(position).getMasterPic());
        holder.tv_home_pzsh_title.setText(list.get(position).getCommodityName()+"");
        holder.tv_home_pzsh_price.setText("￥"+list.get(position).getPrice());
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
        SimpleDraweeView fresco_home_pzsh;
        TextView tv_home_pzsh_title;
        TextView tv_home_pzsh_price;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_home_pzsh_price=itemView.findViewById(R.id.tv_home__pzsh_price);
            tv_home_pzsh_title=itemView.findViewById(R.id.tv_home_pzsh_title);
            fresco_home_pzsh=itemView.findViewById(R.id.fresco_home_pzsh);

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
