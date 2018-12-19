package com.example.baweistore.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.baweistore.Adapter.CircleAdapter;
import com.example.baweistore.Bean.Circle;
import com.example.baweistore.Bean.Dzan;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class CircleFragment extends Fragment {
    private XRecyclerView xrv_circle;
    private String url="http://172.17.8.100/small/circle/v1/findCircleList?userId=1010&&sessionId=15320748258726&&page=1&&count=5";
    private List<Circle.ResultBean>  list;
    private CircleAdapter circleadapter;
    private int x=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_circle, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        xrv_circle = (XRecyclerView) view.findViewById(R.id.xrv_circle);
        getData();
        circleadapter.setOnclickZan(new CircleAdapter.OnclickZan() {
            @Override
            public void clickzan(final int postion) {
                x++;
                if (x%2==0){
                    Log.e("点击","点赞");
                    String path="http://172.17.8.100/small/circle/verify/v1/addCircleGreat";
                    HashMap<String, String> map = new HashMap<>();
                    map.put("circleId",postion+"");
                    Okutils.getInstance().doHeadPost(path, map, new Okutils.OkHttpCallBack() {
                        @Override
                        public void success(String s) {
                            Dzan dzan = new Gson().fromJson(s, Dzan.class);
                            String message = dzan.getMessage();
                            if (message.equals("点赞成功")){
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void failed(Exception e) {

                        }
                    });

                }
                 else{
                    Log.e("点击","取消点赞");
                    String path="http://172.17.8.100/small/circle/verify/v1/cancelCircleGreat";
                    HashMap<String, String> map = new HashMap<>();
                    map.put("circleId",postion+"");
                    Okutils.getInstance().doHeadDelete(path, map, new Okutils.OkHttpCallBack() {
                        @Override
                        public void success(String s) {
                            Dzan dzan = new Gson().fromJson(s, Dzan.class);
                            String message = dzan.getMessage();
                            if (message.equals("取消成功")){
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void failed(Exception e) {

                        }
                    });

                }

            }
        });
    }

    private void getData() {
        list=new ArrayList<>();
        xrv_circle.setLayoutManager(new LinearLayoutManager(getActivity()));
         circleadapter=new CircleAdapter(getActivity(),list);
        xrv_circle.setAdapter(circleadapter);
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                Circle circle = new Gson().fromJson(s, Circle.class);
                List<Circle.ResultBean> result = circle.getResult();
                if (result!=null){
                    list.clear();
                    list.addAll(result);
                    circleadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }
}
