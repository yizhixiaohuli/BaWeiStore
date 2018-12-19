package com.example.baweistore.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.baweistore.OrderFragment.AllOrderFragment;
import com.example.baweistore.OrderFragment.DcommentFragment;
import com.example.baweistore.OrderFragment.DmoneyFragment;
import com.example.baweistore.OrderFragment.DreceiverFragment;
import com.example.baweistore.OrderFragment.FinishedFragment;
import com.example.baweistore.R;

import java.util.ArrayList;

/**
 *
 */
public class List_Fragment extends Fragment {

    private ArrayList<Fragment> list;
    private RadioButton fragment_order_indent;
    private RadioButton fragment_order_expressage;
    private RadioButton fragment_order_delivery;
    private RadioButton fragment_order_evaluate;
    private RadioButton fragment_order_all;
    private RadioGroup fragment_order_fourrg;
    private ViewPager fragment_order_fourpager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_list, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new AllOrderFragment());
        list.add(new DcommentFragment());
        list.add(new DmoneyFragment());
        list.add(new DreceiverFragment());
        list.add(new FinishedFragment());
        fragment_order_fourpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragment_order_fourrg.check(fragment_order_fourrg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fragment_order_fourrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.fragment_order_indent:
                        fragment_order_fourpager.setCurrentItem(0);
                        break;
                    case R.id.fragment_order_expressage:
                        fragment_order_fourpager.setCurrentItem(1);
                        break;
                    case R.id.fragment_order_delivery:
                        fragment_order_fourpager.setCurrentItem(2);
                        break;
                    case R.id.fragment_order_evaluate:
                        fragment_order_fourpager.setCurrentItem(3);
                        break;
                    case R.id.fragment_order_all:
                        fragment_order_fourpager.setCurrentItem(4);
                        break;
                }
            }
        });
        fragment_order_fourpager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

    }

    private void initView(View view) {

        fragment_order_indent = (RadioButton) view.findViewById(R.id.fragment_order_indent);

        fragment_order_expressage = (RadioButton) view.findViewById(R.id.fragment_order_expressage);

        fragment_order_delivery = (RadioButton) view.findViewById(R.id.fragment_order_delivery);

        fragment_order_evaluate = (RadioButton) view.findViewById(R.id.fragment_order_evaluate);

        fragment_order_all = (RadioButton) view.findViewById(R.id.fragment_order_all);

        fragment_order_fourrg = (RadioGroup) view.findViewById(R.id.fragment_order_fourrg);

        fragment_order_fourpager = (ViewPager) view.findViewById(R.id.fragment_order_fourpager);

    }
}
