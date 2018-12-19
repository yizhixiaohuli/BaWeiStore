package com.example.baweistore.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.example.baweistore.Adapter.MyAddressAdapter;
import com.example.baweistore.Bean.MyAddress;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private XRecyclerView my_address_xrv;
    private Button my_address_btadd;
    private ArrayList<MyAddress.ResultBean> list;
    private MyAddressAdapter myAddressadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        initView();
    }

    private void initView() {
        my_address_xrv = (XRecyclerView) findViewById(R.id.my_address_xrv);
        my_address_btadd = (Button) findViewById(R.id.my_address_btadd);

        my_address_btadd.setOnClickListener(this);
        getdata();
    }

    private void getdata() {
        list=new ArrayList<>();

        myAddressadapter=new MyAddressAdapter(this,list);
        my_address_xrv.setLayoutManager(new LinearLayoutManager(this));
        my_address_xrv.setAdapter(myAddressadapter);
        String url="http://172.17.8.100/small/user/verify/v1/receiveAddressList";
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                MyAddress myAddress = new Gson().fromJson(s, MyAddress.class);
                List<MyAddress.ResultBean> result = myAddress.getResult();
                if (result!=null){
                    list.clear();
                    list.addAll(result);
                    myAddressadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_address_btadd:
                startActivity(new Intent(MyAddressActivity.this,MyAddressAddActivity.class));

                break;
        }
    }
}
