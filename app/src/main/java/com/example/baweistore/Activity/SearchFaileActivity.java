package com.example.baweistore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.R;

public class SearchFaileActivity extends AppCompatActivity {

    private ImageView ig_bt_menu;
    private EditText searchtext;
    private TextView tv_search_sou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_faile);
        initView();

    }

    private void initView() {
        ig_bt_menu = (ImageView) findViewById(R.id.ig_bt_menu);
        searchtext = (EditText) findViewById(R.id.searchtext);
        tv_search_sou = (TextView) findViewById(R.id.tv_search_sou);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        searchtext.setText(name);

    }

    private void submit() {
        // validate
        String searchtextString = searchtext.getText().toString().trim();
        if (TextUtils.isEmpty(searchtextString)) {
            Toast.makeText(this, "  请输入您要搜索的商品", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
