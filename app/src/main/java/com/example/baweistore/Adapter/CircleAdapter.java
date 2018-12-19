package com.example.baweistore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baweistore.Bean.Circle;
import com.example.baweistore.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhuang.likeviewlibrary.LikeView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * date:2018/12/11
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class CircleAdapter extends XRecyclerView.Adapter<CircleAdapter.ViewHolder>{
    Context context;
    List<Circle.ResultBean> list;
    int y=1;
    public CircleAdapter(Context context, List<Circle.ResultBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.circle_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CircleAdapter.ViewHolder holder, final int position) {
        holder.fresco_circle_tou.setImageURI(list.get(position).getHeadPic());
        holder.fresco_circle_tua.setImageURI(list.get(position).getImage());
        holder.tv_circle_name.setText(list.get(position).getNickName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        holder.tv_circle_date.setText(simpleDateFormat.format(list.get(position).getCreateTime()));
        holder.tv_circle_prise.setText(list.get(position).getGreatNum()+"");
        holder.tv_circle_message.setText(list.get(position).getContent());
        holder.img_circle_prise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y++;
                if (y%2==0){
                    Log.e("点击","aa");
                    holder.img_circle_prise.setImageResource(R.mipmap.common_btn_prise_s_xxhdpi);
                    list.get(position).setGreatNum(list.get(position).getGreatNum()+1);
                    holder.tv_circle_prise.setText((list.get(position).getGreatNum())+"");
                }
                else {
                    Log.e("取消","aa");
                    holder.img_circle_prise.setImageResource(R.mipmap.common_btn_prise_n_xxhdpi);
                    list.get(position).setGreatNum(list.get(position).getGreatNum()-1);
                    holder.tv_circle_prise.setText((list.get(position).getGreatNum())+"");
                }
                mOnclickZan.clickzan(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView fresco_circle_tou;
        SimpleDraweeView fresco_circle_tua;
        SimpleDraweeView fresco_circle_tub;
        TextView tv_circle_name;
        TextView tv_circle_date;
        TextView tv_circle_message;
        TextView tv_circle_prise;
        ImageView img_circle_prise;

        public ViewHolder(View itemView) {
            super(itemView);
            fresco_circle_tou=itemView.findViewById(R.id.fresco_circle_tou);
            fresco_circle_tua=itemView.findViewById(R.id.fresco_circle_tua);
            fresco_circle_tub=itemView.findViewById(R.id.fresco_circle_tub);
            tv_circle_name=itemView.findViewById(R.id.tv_circle_name);
            tv_circle_date=itemView.findViewById(R.id.tv_circle_date);
            tv_circle_message=itemView.findViewById(R.id.tv_circle_message);
            tv_circle_prise=itemView.findViewById(R.id.tv_circle_prise);
            img_circle_prise=itemView.findViewById(R.id.img_circle_prise);

        }
    }

    public interface OnclickZan{
        void clickzan(int postion);
    }

    public OnclickZan mOnclickZan;

    public void setOnclickZan(OnclickZan onclickZan) {
        mOnclickZan = onclickZan;
    }
}
