package com.example.baweistore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.baweistore.Bean.Banner;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * date:2018/12/11
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class ViewPaerAdapter extends PagerAdapter {

    private int[] mData;
    private Context context;
    private List<Banner.ResultBean> result;
    public ViewPaerAdapter(Context context, List<Banner.ResultBean> result) {
        this.context = (Context) context;

        this.result = result;

    }

    @Override
    public int getCount() {
        return result.size()>0 ?Integer.MAX_VALUE :0;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.home_pager, null);
        SimpleDraweeView fresco_pager = view.findViewById(R.id.fresco_pager);
        fresco_pager.setImageURI(result.get(position%result.size()).getImageUrl());
        container.addView(view);//添加到父控件
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // 过滤和缓存的作用
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //从viewpager中移除掉
        container.removeView((View) object);
    }
}
