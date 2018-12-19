package com.example.baweistore.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.Activity.MainActivity;
import com.example.baweistore.Bean.ShopCar;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ShopcarFragment extends Fragment implements View.OnClickListener {
    private RecyclerView shopping_recy;
    private TextView text;
    private CheckBox shopping_qx;
    private TextView shopping_price;
    private Button shopping_pay;
    private ArrayList<ShopCar.ResultBean> list;
    private ShopMadapter shopMadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_shopcar, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        shopping_recy = (RecyclerView) view.findViewById(R.id.shopping_recy);
        text = (TextView) view.findViewById(R.id.text);
        shopping_qx = (CheckBox) view.findViewById(R.id.shopping_qx);
        shopping_price = (TextView) view.findViewById(R.id.shopping_price);
        shopping_pay = (Button) view.findViewById(R.id.shopping_pay);

        shopping_pay.setOnClickListener(this);
        shopping_qx.setOnClickListener(this);
        getdata();

        //接口回调 调用adapter方法
        shopMadapter.setCartListener(new ShopMadapter.ShoppingListener() {

            @Override
            public void GoodsChange() {
                boolean isAllOk=shopMadapter.isAllGoodsIsSelected();
                shopping_qx.setChecked(isAllOk);
                //刷新适配器
                shopMadapter.notifyDataSetChanged();
                alterPrices();
            }

            @Override
            public void NumChange(int index, int num) {
                shopMadapter.changeGoodsNum(index,num);
                alterPrices();
            }

            @Override
            public void onDeleteClick(int position) {
                Toast.makeText(getActivity(),"删除",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.shopping_pay:

                break;
            case R.id.shopping_qx:

                boolean checked = shopping_qx.isChecked();
                shopMadapter.setAllGoodsIsSelected(checked);
                shopMadapter.notifyDataSetChanged();

                alterPrices();
                break;
        }
    }
    private void getdata() {
        list = new ArrayList<>();
        shopMadapter = new ShopMadapter(getActivity(), list);
        shopping_recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopping_recy.setAdapter(shopMadapter);

        String url = "http://172.17.8.100/small/order/verify/v1/findShoppingCart";
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                ShopCar shopCar = new Gson().fromJson(s, ShopCar.class);
                List<ShopCar.ResultBean> result = shopCar.getResult();
                if (result != null) {
                    list.clear();
                    list.addAll(result);
                    shopMadapter.notifyDataSetChanged();
                }

            }

            @Override
            public void failed(Exception e) {

            }
        });




    }

    private void alterPrices() {
        //价格
        float zongji=shopMadapter.setZongji();
        shopping_price.setText("合计:￥"+zongji);
    }
}