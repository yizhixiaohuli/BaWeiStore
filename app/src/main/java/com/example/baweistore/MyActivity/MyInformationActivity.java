package com.example.baweistore.MyActivity;

import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.Activity.loginActivity;
import com.example.baweistore.Bean.My;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.HashMap;

public class MyInformationActivity extends AppCompatActivity {

    private SimpleDraweeView fresco_my_information_tou;
    private TextView tv_my_information_name;
    private TextView tv_my_information_pass;
    private SharedPreferences.Editor edit;
    private String ssid;
    private SharedPreferences sharedPreferences;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);

        getData();
        initView();
    }

    private void initView() {
        fresco_my_information_tou = (SimpleDraweeView) findViewById(R.id.fresco_my_information_tou);
        tv_my_information_name = (TextView) findViewById(R.id.tv_my_information_name);
        tv_my_information_pass = (TextView) findViewById(R.id.tv_my_information_pass);

    }
    private void getData() {
        //传数据
        sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
        //ssid = sharedPreferences.getString("ssid","");
        String number = sharedPreferences.getString("number", "");
         pass = sharedPreferences.getString("pass", "");
        Toast.makeText(MyInformationActivity.this, ssid, Toast.LENGTH_SHORT).show();

        String url = "http://172.17.8.100/small/user/verify/v1/getUserById";

        Okutils.getInstance().doGet(url,new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                My my = new Gson().fromJson(s, My.class);
                My.ResultBean result = my.getResult();
                fresco_my_information_tou .setImageURI(result.getHeadPic());
                tv_my_information_name.setText(result.getNickName()+"");
                tv_my_information_pass.setText(result.getPassword());

            }

            @Override
            public void failed(Exception e) {

            }
        });
    }
}
