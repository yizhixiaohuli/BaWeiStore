package com.example.baweistore;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * date:2018/12/14
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class ShopAddView extends LinearLayout implements View.OnClickListener  {
    private TextView tv_sub;
    private TextView tv_text;
    private TextView tv_add;
    private int number = 1;

    public ShopAddView(Context context) {
        this(context, null);
    }

    public ShopAddView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShopAddView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.shopcarjjiajian, this);
        tv_add= view.findViewById(R.id.tv_add);
        tv_sub = view.findViewById(R.id.tv_sub);
        tv_text = view.findViewById(R.id.tv_text);

        tv_add.setOnClickListener(this);
        tv_sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //增加操作
            case R.id.tv_add:
                ++number;
                tv_text.setText(number + "");
                if (mOnNumberChangeListener != null) {
                    mOnNumberChangeListener.onNumberChange(number);
                }
                break;
            //减少操作
            case R.id.tv_sub:
                if (number > 1) {
                    --number;
                    tv_text.setText(number + "");
                    if (mOnNumberChangeListener != null) {
                        mOnNumberChangeListener.onNumberChange(number);
                    } else {
                        Toast.makeText(getContext(), "不能再少了", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        tv_text.setText(number + "");
    }

    //接口回调
    OnNumberChangeListener mOnNumberChangeListener;

    public interface OnNumberChangeListener {
        void onNumberChange(int num);
    }

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.mOnNumberChangeListener = onNumberChangeListener;
    }
}
