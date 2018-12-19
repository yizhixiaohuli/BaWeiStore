package com.example.baweistore.MyActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.example.baweistore.Adapter.MyCircleAdapter;
import com.example.baweistore.Bean.CIrcle_my;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyCircleActivity extends AppCompatActivity {


    private ImageView img_my_circle_delete;
    private XRecyclerView my_circle_xrv;
    private ArrayList<CIrcle_my.ResultBean> list;
    private MyCircleAdapter mycircleadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle);
        initView();
        getData();
    }

    private void initView() {


        img_my_circle_delete = (ImageView) findViewById(R.id.img_my_circle_delete);

        my_circle_xrv = (XRecyclerView) findViewById(R.id.my_circle_xrv);

        list=new ArrayList<>();

        mycircleadapter=new MyCircleAdapter(this,list);
        my_circle_xrv.setLayoutManager(new LinearLayoutManager(this));
        my_circle_xrv.setAdapter(mycircleadapter);


    }

    private void getData() {

        String url = "http://172.17.8.100/small/circle/verify/v1/findMyCircleById?page=1&count=5";
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                CIrcle_my cIrcle_my = new Gson().fromJson(s, CIrcle_my.class);
                List<CIrcle_my.ResultBean> result = cIrcle_my.getResult();

                if (result!=null){
                    list.clear();
                    list.addAll(result);
                    mycircleadapter.notifyDataSetChanged();


                }

            }

            @Override
            public void failed(Exception e) {

            }
        });
    }
}
