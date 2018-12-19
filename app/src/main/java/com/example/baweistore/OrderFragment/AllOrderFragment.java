package com.example.baweistore.OrderFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baweistore.Bean.OrderList;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllOrderFragment extends Fragment {


    private XRecyclerView orderall_xrv;
    private ArrayList<OrderList.OrderListBean> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_all_order, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        orderall_xrv = (XRecyclerView) view.findViewById(R.id.orderall_xrv);
        getData();
    }

    private void getData() {
         list=new ArrayList<>();
        final OrderMadapter orderMadapter=new OrderMadapter(getActivity(),list);
        orderall_xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderall_xrv.setAdapter(orderMadapter);

        String url="http://172.17.8.100/small/order/verify/v1/findOrderListByStatus?status=0&page=1&count=1";
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                OrderList orderList = new Gson().fromJson(s, OrderList.class);
                List<OrderList.OrderListBean> order= orderList.getOrderList();
                if (order!=null){
                    list.clear();
                    list.addAll(order);
                    orderMadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }
}
