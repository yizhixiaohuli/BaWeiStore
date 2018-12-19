package com.example.baweistore.Fragment;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.Activity.HomeDetailsActivity;
import com.example.baweistore.Activity.SearchHomeActivity;
import com.example.baweistore.Adapter.HomeMadapter;
import com.example.baweistore.Adapter.HomeMlssMadapter;
import com.example.baweistore.Adapter.HomePinMadapter;
import com.example.baweistore.Adapter.ViewPaerAdapter;
import com.example.baweistore.Bean.Banner;
import com.example.baweistore.Bean.Home;
import com.example.baweistore.Okutils;
import com.example.baweistore.PagerLunbo;
import com.example.baweistore.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class HomeFragment extends Fragment {

    private ImageView ig_bt_menu;
    private SearchView searchview;
    private TextView tv_search_sou;
    private RecyclerView rv_home;
    private String url = "http://172.17.8.100/small/commodity/v1/commodityList";
    private String path = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private ArrayList<Home.ResultBean.RxxpBean.CommodityListBean> list;
    private ArrayList<Home.ResultBean.MlssBean.CommodityListBeanXX> mlsslist;
    private ArrayList<Home.ResultBean.PzshBean.CommodityListBeanX> pinlist;
    private HomeMadapter homeadapter;
    private HomeMlssMadapter mlsshomeadapter;
    private HomePinMadapter pinhomeadapter;
    private ViewPager viewpager_home;
    private RecyclerView rv_home_mo;
    private RecyclerView rv_home_pin;
    private EditText searchtext;
    private Handler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        initView(view);
       // initActionbar();
        return view;
    }



    private void initView(View view) {
        ig_bt_menu = (ImageView) view.findViewById(R.id.ig_bt_menu);

        tv_search_sou = (TextView) view.findViewById(R.id.tv_search_sou);
        rv_home = (RecyclerView) view.findViewById(R.id.rv_home);
        getData();

        getBanner();
        viewpager_home = (ViewPager) view.findViewById(R.id.viewpager_home);

        rv_home_mo = (RecyclerView) view.findViewById(R.id.rv_home_mo);

        rv_home_pin = (RecyclerView) view.findViewById(R.id.rv_home_pin);


        //搜索

        tv_search_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = searchtext.getText().toString().trim();
                if (search.equals("")) {
                    Toast.makeText(getActivity(), "搜索框不能为空", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(getActivity(), SearchHomeActivity.class);
                    intent.putExtra("name", search);
                    startActivity(intent);

                }
            }
        });
        //点击弹popuwindow
        ig_bt_menu.setOnClickListener(new View.OnClickListener() {
            /* private RadioGroup popupwindow_item_class;
             private RadioGroup popupwindow_item_title;*/
            @Override
            public void onClick(View v) {
                //获取布局文件中控件
                final View popup_item = View.inflate(getActivity(), R.layout.home_popuwindow_item, null);
                PopupWindow window = new PopupWindow(popup_item, 1000, 300, true);
                window.setBackgroundDrawable(new ColorDrawable());//设置背景
                window.setOutsideTouchable(true);//
                window.setTouchable(true);//
                window.showAsDropDown(ig_bt_menu);//弹出的位置在menu的下面
/*
                popupwindow_item_class = popup_item.findViewById(R.id.popupwindow_item_class);
                popupwindow_item_title = popup_item.findViewById(R.id.popupwindow_item_title);
                //当title点击的时候
                popupwindow_item_class.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton child = popup_item.findViewById(popupwindow_item_class.getCheckedRadioButtonId());
                        Toast.makeText(getActivity(), "" + child.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("name", child.getText());
                        startActivity(intent);
                    }
                });
                //点击跳转详细的商品信息界面
                popupwindow_item_title.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton child = popup_item.findViewById(popupwindow_item_title.getCheckedRadioButtonId());
                        Toast.makeText(getActivity(), "" + child.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("name", child.getText());
                        startActivity(intent);
                    }
                });*/
            }
        });

        searchtext = (EditText) view.findViewById(R.id.searchtext);

    }


    //轮播图
    private void getBanner() {
        Okutils.getInstance().doGet(path, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                Banner banner = new Gson().fromJson(s, Banner.class);
                List<Banner.ResultBean> result = banner.getResult();
                ViewPaerAdapter viewPaerAdapter = new ViewPaerAdapter(getActivity(), result);
                viewpager_home.setAdapter(viewPaerAdapter);
                viewpager_home.setPageMargin(20);
                viewpager_home.setOffscreenPageLimit(2);
                viewpager_home.setCurrentItem(500);
                viewpager_home.setPageTransformer(true, new PagerLunbo());//3D画廊模式         //左右都有图        mViewPager.setCurrentItem(1);


            }

            @Override
            public void failed(Exception e) {

            }
        });
    }


    //商品信息
    private void getData() {
        //热销商品
        list = new ArrayList<Home.ResultBean.RxxpBean.CommodityListBean>();
        homeadapter = new HomeMadapter(getActivity(), list);
        rv_home.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_home.setAdapter(homeadapter);
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                Home home = new Gson().fromJson(s, Home.class);
                List<Home.ResultBean.RxxpBean> rxxp = home.getResult().getRxxp();
                List<Home.ResultBean.RxxpBean.CommodityListBean> rxxpbean = rxxp.get(0).getCommodityList();
                if (rxxpbean != null) {
                    list.clear();
                    list.addAll(rxxpbean);
                    homeadapter.notifyDataSetChanged();

                }
                homeadapter.setOnclickListener(new HomeMadapter.OnclickListener() {

                    @Override
                    public void clickid(int position) {
                        Intent intent = new Intent(new Intent(getActivity(), HomeDetailsActivity.class));
                        intent.putExtra("id", list.get(position).getCommodityId() + "");
                        startActivity(intent);

                    }
                });
                //魅力时尚
                mlsslist = new ArrayList<Home.ResultBean.MlssBean.CommodityListBeanXX>();
                mlsshomeadapter = new HomeMlssMadapter(getActivity(), mlsslist);
                rv_home_mo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                rv_home_mo.setAdapter(mlsshomeadapter);
                List<Home.ResultBean.MlssBean.CommodityListBeanXX> mlss = home.getResult().getMlss().get(0).getCommodityList();
                if (mlss != null) {
                    mlsslist.clear();
                    mlsslist.addAll(mlss);
                    mlsshomeadapter.notifyDataSetChanged();

                }

                mlsshomeadapter.setMlssListener(new HomeMlssMadapter.OnMlssListener() {
                    @Override
                    public void clickid(int position) {
                        Intent intent = new Intent(new Intent(getActivity(), HomeDetailsActivity.class));
                        intent.putExtra("mid", mlsslist.get(position).getCommodityId() + "");
                        startActivity(intent);
                    }
                });
                //品质生活
                pinlist = new ArrayList<Home.ResultBean.PzshBean.CommodityListBeanX>();
                pinhomeadapter = new HomePinMadapter(getActivity(), pinlist);
                rv_home_pin.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                rv_home_pin.setAdapter(pinhomeadapter);
                List<Home.ResultBean.PzshBean.CommodityListBeanX> pin = home.getResult().getPzsh().get(0).getCommodityList();
                if (pin != null) {
                    pinlist.clear();
                    pinlist.addAll(pin);
                    pinhomeadapter.notifyDataSetChanged();

                }
                pinhomeadapter.setOnclickListener(new HomePinMadapter.OnclickListener() {
                    @Override
                    public void clickid(int position) {
                        Intent intent = new Intent(new Intent(getActivity(), HomeDetailsActivity.class));
                        intent.putExtra("pid", pinlist.get(position).getCommodityId() + "");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }

    private void submit() {
        // validate
        String searchtextString = searchtext.getText().toString().trim();
        if (TextUtils.isEmpty(searchtextString)) {
            Toast.makeText(getContext(), "  请输入您要搜索的商品", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }



}
