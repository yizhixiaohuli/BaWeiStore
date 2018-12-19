package com.example.baweistore.MyActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.baweistore.Adapter.FootMyMadapter;
import com.example.baweistore.Bean.My_Foot;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyFootActivity extends AppCompatActivity {

    private XRecyclerView mt_foot_xrv;
    private ArrayList<My_Foot.ResultBean> list;
    private FootMyMadapter footmyadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_foot);
        initView();
    }

    private void initView() {
        mt_foot_xrv = (XRecyclerView) findViewById(R.id.mt_foot_xrv);
        getdata();
    }

    private void getdata() {
         list=new ArrayList<>();
        mt_foot_xrv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
          footmyadapter=new FootMyMadapter(this,list);
          mt_foot_xrv.setAdapter(footmyadapter);
        String url="http://172.17.8.100/small/commodity/verify/v1/browseList?page=1&count=5";
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                My_Foot my_foot = new Gson().fromJson(s, My_Foot.class);
                List<My_Foot.ResultBean> result = my_foot.getResult();
                if (result!=null){
                        list.clear();
                        list.addAll(result);
                        footmyadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }
}
