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


public class DmoneyFragment extends Fragment {


    private XRecyclerView dmoney_xrv;
    private ArrayList<OrderList.OrderListBean> list;
    private DmoneyMadapter demoneyMadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_dmoney, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        getData();
    }

    private void getData() {
       /* list=new ArrayList<>();
          demoneyMadapter=new DmoneyMadapter(getActivity(),list);
        dmoney_xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        dmoney_xrv.setAdapter(demoneyMadapter);

        String url="http://172.17.8.100/small/order/verify/v1/findOrderListByStatus?status=2&page=1&count=1";
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                OrderList orderList = new Gson().fromJson(s, OrderList.class);
                List<OrderList.OrderListBean> order= orderList.getOrderList();
                if (order!=null){
                    list.clear();
                    list.addAll(order);
                    demoneyMadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(Exception e) {

            }
        });*/
    }
}
