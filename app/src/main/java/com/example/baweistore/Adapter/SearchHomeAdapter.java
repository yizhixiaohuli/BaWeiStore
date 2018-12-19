package com.example.baweistore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baweistore.Bean.Home_search;
import com.example.baweistore.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:2018/12/13
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class SearchHomeAdapter extends XRecyclerView.Adapter<SearchHomeAdapter.Viewholder> {
    private final Context context;
    private final List<Home_search.ResultBean> mresult;





    public SearchHomeAdapter(Context context, List<Home_search.ResultBean> result) {
        this.context=context;
        mresult=result;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.home_search_item, null );
        Viewholder viewholder = new Viewholder( view );
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.fresco_home.setImageURI( mresult.get( position ).getMasterPic());
        String commodityName = mresult.get(position).getCommodityName();
        String substring = commodityName.substring(0,6);
        holder.tv_home_title.setText(substring+"...");
        holder.tv_home_price.setText("￥"+mresult.get(position).getPrice());
        holder.tv_home_num.setText("已售"+mresult.get(position).getSaleNum()+"件");

    }

    @Override
    public int getItemCount() {
        return mresult.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        SimpleDraweeView fresco_home;
        TextView tv_home_title;
        TextView tv_home_price;
        TextView tv_home_num;
        public Viewholder(View itemView) {
            super( itemView );
            fresco_home=itemView.findViewById(R.id.fresco_home);
            tv_home_title=itemView.findViewById(R.id.tv_home_title);
            tv_home_price=itemView.findViewById(R.id.tv_home_price);
            tv_home_num=itemView.findViewById(R.id.tv_home_num);
        }
    }
}
