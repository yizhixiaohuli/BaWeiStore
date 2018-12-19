package com.example.baweistore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.Adapter.SearchHomeAdapter;
import com.example.baweistore.Bean.Home_search;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class SearchHomeActivity extends AppCompatActivity {

    private ImageView ig_bt_menu;
    private EditText searchtext;
    private TextView tv_search_sou;
    private XRecyclerView shop_xrecyclerview;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_home);
        initView();

    }

    private void initView() {
        ig_bt_menu = (ImageView) findViewById(R.id.ig_bt_menu);
        searchtext = (EditText) findViewById(R.id.searchtext);
        tv_search_sou = (TextView) findViewById(R.id.tv_search_sou);
        shop_xrecyclerview = (XRecyclerView) findViewById(R.id.shop_xrecyclerview);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        searchtext.setText(name);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

            String url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&count=10&keyword="+name;

            Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
                @Override
                public void success(String s) {
                    Home_search home_search = new Gson().fromJson(s, Home_search.class);
                    List<Home_search.ResultBean> result = home_search.getResult();
                    if (result.size()!=0){

                        SearchHomeAdapter searchadapter=new SearchHomeAdapter(SearchHomeActivity.this,result);
                        shop_xrecyclerview.setAdapter(searchadapter);
                        shop_xrecyclerview.setLayoutManager(new GridLayoutManager(SearchHomeActivity.this,2));
                    }else{
                        finish();
                        Intent intent = new Intent( SearchHomeActivity.this, SearchFaileActivity.class );
                        intent.putExtra( "name", name );
                        startActivity( intent );
                    }
                }


                @Override
                public void failed(Exception e) {
                    Toast.makeText(SearchHomeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }

        }
        




