package com.example.baweistore.Fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.Bean.My;
import com.example.baweistore.EventBusMessage;
import com.example.baweistore.MyActivity.MyAddressActivity;
import com.example.baweistore.MyActivity.MyCircleActivity;
import com.example.baweistore.MyActivity.MyFootActivity;
import com.example.baweistore.MyActivity.MyInformationActivity;
import com.example.baweistore.MyActivity.MyWalletActivity;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 *
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private SimpleDraweeView fresco_my_tou;
    private TextView tv_my_name;
    private TextView tv_my_information;
    private TextView tv_my_circle;
    private TextView tv_my_foot;
    private TextView tv_my_wallet;
    private TextView tv_my_address;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_my, null);


        initView(view);
        return view;
    }

    private void initView(View view) {
        fresco_my_tou = (SimpleDraweeView) view.findViewById(R.id.fresco_my_tou);
        tv_my_name = (TextView) view.findViewById(R.id.tv_my_name);
        tv_my_information = (TextView) view.findViewById(R.id.tv_my_information);
        tv_my_circle = (TextView) view.findViewById(R.id.tv_my_circle);
        tv_my_foot = (TextView) view.findViewById(R.id.tv_my_foot);
        tv_my_wallet = (TextView) view.findViewById(R.id.tv_my_wallet);
        tv_my_address = (TextView) view.findViewById(R.id.tv_my_address);
        tv_my_wallet.setOnClickListener(this);
        tv_my_foot.setOnClickListener(this);
        tv_my_circle.setOnClickListener(this);
        tv_my_information.setOnClickListener(this);
        tv_my_address.setOnClickListener(this);
        sharedPreferences = getActivity().getSharedPreferences("key", MODE_PRIVATE);
        String headpic = sharedPreferences.getString("headpic", "");
        String nickname = sharedPreferences.getString("nickname", "");
        fresco_my_tou.setImageURI(headpic);
        tv_my_name.setText(nickname+"");



    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_my_information:
                startActivity(new Intent(getActivity(), MyInformationActivity.class));
                break;
            case R.id.tv_my_circle:
                startActivity(new Intent(getActivity(), MyCircleActivity.class));
                break;
            case R.id.tv_my_foot:
                startActivity(new Intent(getActivity(), MyFootActivity.class));
                break;
            case R.id.tv_my_wallet:
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
                break;
            case R.id.tv_my_address:
                startActivity(new Intent(getActivity(), MyAddressActivity.class));
                break;
        }
    }



}
