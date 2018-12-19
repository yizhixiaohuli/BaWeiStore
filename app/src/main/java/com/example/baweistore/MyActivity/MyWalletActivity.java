package com.example.baweistore.MyActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.baweistore.Bean.Banner;
import com.example.baweistore.Bean.MyWallet;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MyWalletActivity extends AppCompatActivity {

    private TextView my_wallet_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        initView();
    }

    private void initView() {
        my_wallet_money = (TextView) findViewById(R.id.my_wallet_money);
        getData();
    }

    private void getData() {
        String url="http://172.17.8.100/small/user/verify/v1/findUserWallet?page=1&count=1";
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                MyWallet myWallet = new Gson().fromJson(s, MyWallet.class);
                MyWallet.ResultBean result = myWallet.getResult();
                my_wallet_money.setText(result.getBalance()+"");

            }

            @Override
            public void failed(Exception e) {

            }
        });
    }


}
