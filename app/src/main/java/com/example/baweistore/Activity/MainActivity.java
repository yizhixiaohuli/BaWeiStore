package com.example.baweistore.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.baweistore.Fragment.CircleFragment;
import com.example.baweistore.Fragment.HomeFragment;
import com.example.baweistore.Fragment.List_Fragment;
import com.example.baweistore.Fragment.MyFragment;
import com.example.baweistore.Fragment.ShopcarFragment;
import com.example.baweistore.R;
import com.example.baweistore.ThemeManager;

import java.util.ArrayList;

import skin.support.SkinCompatManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager pager;
    private ImageView iv_home;
    private ImageView iv_circle;
    private ImageView iv_shopcar;
    private ImageView iv_list;
    private ImageView iv_my;
    private ArrayList<Fragment> list;
    private RadioGroup linear;
    private LinearLayout linearlayout;
    private DrawerLayout drawlayout;
    private Button bai;
    private Button hei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();
    }

    //初始化控件
    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_circle = (ImageView) findViewById(R.id.iv_circle);
        iv_shopcar = (ImageView) findViewById(R.id.iv_shopcar);
        iv_list = (ImageView) findViewById(R.id.iv_list);
        iv_my = (ImageView) findViewById(R.id.iv_my);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CircleFragment());
        list.add(new ShopcarFragment());
        list.add(new List_Fragment());
        list.add(new MyFragment());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        iv_circle.setImageResource(R.mipmap.common_tab_btn_circle_n_xxhdpi);
                        iv_list.setImageResource(R.mipmap.common_tab_btn_list_n_xxhdi);
                        iv_my.setImageResource(R.mipmap.common_tab_btn_my_n_xxhdpi);
                        iv_home.setImageResource(R.mipmap.common_tab_btn_home_s_xxhdpi);
                        break;
                    case 1:
                        iv_circle.setImageResource(R.mipmap.common_tab_btn_circle_s_xxhdpi);
                        iv_list.setImageResource(R.mipmap.common_tab_btn_list_n_xxhdi);
                        iv_my.setImageResource(R.mipmap.common_tab_btn_my_n_xxhdpi);
                        iv_home.setImageResource(R.mipmap.common_tab_btn_home_n_xxhdpi);
                        break;
                    case 2:
                        iv_circle.setImageResource(R.mipmap.common_tab_btn_circle_n_xxhdpi);
                        iv_list.setImageResource(R.mipmap.common_tab_btn_list_n_xxhdi);
                        iv_home.setImageResource(R.mipmap.common_tab_btn_home_n_xxhdpi);
                        iv_my.setImageResource(R.mipmap.common_tab_btn_my_n_xxhdpi);
                        break;
                    case 3:
                        iv_list.setImageResource(R.mipmap.common_tab_btn_list_s_xxhdpi);
                        iv_circle.setImageResource(R.mipmap.common_tab_btn_circle_n_xxhdpi);
                        iv_my.setImageResource(R.mipmap.common_tab_btn_my_n_xxhdpi);
                        iv_home.setImageResource(R.mipmap.common_tab_btn_home_n_xxhdpi);
                        break;
                    case 4:
                        iv_my.setImageResource(R.mipmap.common_tab_btn_my_s_xxhdpi);
                        iv_circle.setImageResource(R.mipmap.common_tab_btn_circle_n_xxhdpi);
                        iv_list.setImageResource(R.mipmap.common_tab_btn_list_n_xxhdi);
                        iv_home.setImageResource(R.mipmap.common_tab_btn_home_n_xxhdpi);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0);
            }
        });
        iv_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });
        iv_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2);
            }
        });
        iv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(3);
            }
        });
        iv_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(4);
            }
        });


        linearlayout = (LinearLayout) findViewById(R.id.linearlayout);

        drawlayout = (DrawerLayout) findViewById(R.id.drawlayout);


        bai = (Button) findViewById(R.id.bai);
        bai.setOnClickListener(this);
        hei = (Button) findViewById(R.id.hei);
        hei.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bai:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
                Toast.makeText(this, "我是白天模式", Toast.LENGTH_SHORT).show();
                break;
            case R.id.hei:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                recreate();
                Toast.makeText(this, "我是黑夜模式", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    //mRootView 就是要出现悬浮按钮的界面的根view。就是setContentView的View。

            /*FloatDragView.addFloatDragView(this, mRootView, new View.OnClickListener() {

        @Override

        public void onClick(View view) {

            // 点击事件

        }

    });*/

}
