package com.example.baweistore.MyActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.Bean.MyAddAddress;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.google.gson.Gson;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MyAddressAddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_address_name;
    private EditText ed_address_number;
    private EditText ed_address_area;
    private EditText ed_address_xarea;
    private EditText ed_address_code;
    private Button my_address_btadd;
    private CityPicker build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address_add);
        initView();
    }

    private void initView() {
        ed_address_name = (EditText) findViewById(R.id.ed_address_name);
        ed_address_number = (EditText) findViewById(R.id.ed_address_number);
        ed_address_area = (EditText) findViewById(R.id.ed_address_area);
        ed_address_xarea = (EditText) findViewById(R.id.ed_address_xarea);
        ed_address_code = (EditText) findViewById(R.id.ed_address_code);
        my_address_btadd = (Button) findViewById(R.id.my_address_btadd);

        my_address_btadd.setOnClickListener(this);
        initSan();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_address_btadd:
                String name = ed_address_name.getText().toString().trim();
                String number = ed_address_number.getText().toString().trim();
                String area = ed_address_area.getText().toString().trim();
                String xarea = ed_address_xarea.getText().toString().trim();
                String code = ed_address_code.getText().toString().trim();
                Log.e("la",name+"  "+number+"  "+area+"  "+xarea+"  "+code);
                String url="http://172.17.8.100/small/user/verify/v1/addReceiveAddress";
                HashMap<String, String> map = new HashMap<>();
                map.put("realName",name);
                map.put("phone",number);
                map.put("address",area+" "+xarea);
                map.put("zipCode",code);
                Okutils.getInstance().doHeadPost(url, map, new Okutils.OkHttpCallBack() {
                    @Override
                    public void success(String s) {
                        MyAddAddress myAddAddress = new Gson().fromJson(s, MyAddAddress.class);
                        String message = myAddAddress.getMessage();
                        Toast.makeText(MyAddressAddActivity.this, message, Toast.LENGTH_SHORT).show();
                        if (message.equals("添加成功")){
                            finish();
                            startActivity(new Intent(MyAddressAddActivity.this,MyAddressActivity.class));
                        }


                    }

                    @Override
                    public void failed(Exception e) {

                    }
                });

                break;
        }
    }
    //三级联动
    private void initSan() {
        ed_address_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPiker();
                build.show();

            }
        });
    }
    //三级联动
    public void initPiker() {

         build = new CityPicker.Builder(MyAddressAddActivity.this)
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)//省份滚轮是否循环显示
                .cityCyclic(false)//城市滚轮是否循环显示
                .districtCyclic(false)//地区（县）滚轮是否循环显示
                .visibleItemsCount(7)//滚轮显示的item个数
                .itemPadding(10)//滚轮item间距
                .onlyShowProvinceAndCity(false)
                .build();
        build.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... strings) {
                //省份
                String province = strings[0];
                String city = strings[1];
                String district = strings[2];
                String code = strings[3];
                ed_address_area.setText(province + city + district);
               // Log.e("aaaaaaaaaaaaaa", mine_address_add_diqu.getText().toString());
            }

            @Override
            public void onCancel() {

            }
        });

    }


    private void submit() {
        // validatemap
        String name = ed_address_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String number = ed_address_number.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(this, "number不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String area = ed_address_area.getText().toString().trim();
        if (TextUtils.isEmpty(area)) {
            Toast.makeText(this, "area不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String xarea = ed_address_xarea.getText().toString().trim();
        if (TextUtils.isEmpty(xarea)) {
            Toast.makeText(this, "xarea不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String code = ed_address_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "code不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
