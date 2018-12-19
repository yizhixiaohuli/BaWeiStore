package com.example.baweistore.OrderFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.Bean.OrderList;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.example.baweistore.ShopAddView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * date:2018/12/15
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class OrderMadapter extends RecyclerView.Adapter<OrderMadapter.ViewHolder> {

    Context context;
    ArrayList<OrderList.OrderListBean> list;
    public OrderMadapter(Context context, ArrayList<OrderList.OrderListBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.allorser_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderMadapter.ViewHolder holder, final int position) {

        String Pictures =list.get(position).getDetailList().get(position).getCommodityPic();
        String[] split = Pictures.split(",");
        holder.mycart_simple.setImageURI(split[0]);
        holder.mycart_name.setText(list.get(position).getDetailList().get(position).getCommodityName());
        holder.tv_orderall_ding.setText("订单号:"+list.get(position).getOrderId());
      // holder.tv_orderall_data.setText(list.get(position).getDetailList().get(position).getOrderDetailId()+"");
        holder.mycart_price.setText("￥"+list.get(position).getPayAmount());
        holder.bt_qu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderId = list.get(position).getOrderId();
                String url="http://172.17.8.100/small/order/verify/v1/deleteOrder";
                HashMap<String, String> map = new HashMap<>();
                map.put("orderId",orderId);
                Okutils.getInstance().doHeadDelete(url, map, new Okutils.OkHttpCallBack() {
                    @Override
                    public void success(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String message = jsonObject.getString("message");

                            list.remove(position);
                            notifyItemRemoved(position);

                            notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Exception e) {

                    }
                });

            }
        });
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
        ShopAddView mycart_addsub;
        Button bt_qu;
        Button bt_zhi;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_orderall_data = itemView.findViewById(R.id.tv_orderall_data);
            tv_orderall_ding = itemView.findViewById(R.id.tv_orderall_ding);
            mycart_name = itemView.findViewById(R.id.mycart_name);
            mycart_simple = itemView.findViewById(R.id.mycart_simple);
            mycart_price = itemView.findViewById(R.id.mycart_price);
            mycart_addsub = itemView.findViewById(R.id.mycart_addsub);
            bt_qu = itemView.findViewById(R.id.bt_qu);
        }
    }
}
