package com.example.baweistore.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.EventBusMessage;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/*
*
*
* */
public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_phone;
    private View view_phone;
    private EditText ed_pass;
    private View view_pass;
    private CheckBox checkBox;
    private TextView tv_regiser;
    private Button bt_login;
    private String url="http://172.17.8.100/small/user/v1/login";
    private String loginphone;
    private String loginpass;
    private SharedPreferences.Editor editor;
    private String sessionId;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        //绑定
        EventBus.getDefault().register(this);
        //记住密码shareprefresh
         sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean ck_ji = sharedPreferences.getBoolean("ck_ji", false);
        if (ck_ji){
            String name = sharedPreferences.getString("name", null);
            String pwd = sharedPreferences.getString("pwd", null);
            ed_phone.setText(name);
            ed_pass.setText(pwd);
            checkBox.setChecked(true);
        }

    }

    private void initView() {
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        view_phone = (View) findViewById(R.id.view_phone);
        ed_pass = (EditText) findViewById(R.id.ed_pass);
        view_pass = (View) findViewById(R.id.view_pass);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        tv_regiser = (TextView) findViewById(R.id.tv_regiser);
        bt_login = (Button) findViewById(R.id.bt_login);

        bt_login.setOnClickListener(this);

        tv_regiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,registerActivity.class));

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                    //记住密码
                if (checkBox.isChecked()){
                    loginphone = ed_phone.getText().toString().trim();
                    loginpass = ed_pass.getText().toString().trim();
                    editor.putString("name",loginphone);
                    editor.putString("pwd",loginpass);
                    editor.putBoolean("ck_ji",true);
                    editor.commit();
                }
                getLogin();
                break;
        }
    }

    private void getLogin() {
             loginphone = ed_phone.getText().toString().trim();
             loginpass = ed_pass.getText().toString().trim();
            HashMap<String, String> map = new HashMap<>();
            map.put("phone",loginphone);
            map.put("pwd",loginpass);
             Okutils.getInstance().doPost(url, map, new Okutils.OkHttpCallBack() {
             @Override
             public void success(String s) {
                 try {
                     JSONObject jsonObject = new JSONObject(s);
                     JSONObject result = jsonObject.getJSONObject("result");
                      sessionId = result.getString("sessionId");
                     String userId = result.getString("userId");
                     String headPic = result.getString("headPic");
                     String nickName = result.getString("nickName");
                     EventBus.getDefault().postSticky(new EventBusMessage("","",sessionId+"",userId+""));

                     editor.putString("ssid",sessionId);
                     editor.putString("number",loginphone);
                     editor.putString("pass",loginpass);
                     editor.putString("headpic",headPic);
                     editor.putString("nickname",nickName);
                     editor.commit();
                     Toast.makeText(loginActivity.this, sessionId, Toast.LENGTH_SHORT).show();
                     String msg = jsonObject.getString("message");

                     //Toast.makeText(loginActivity.this, msg, Toast.LENGTH_SHORT).show();
                     if (msg.equals("登录成功")){
                         Intent intent = new Intent(loginActivity.this, MainActivity.class);

                         startActivity(intent);
                     }
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }

             @Override
             public void failed(Exception e) {
                 Toast.makeText(loginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
             }
         });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(EventBusMessage eventBusMessage) {
        ed_phone.setText(eventBusMessage.name);
        ed_pass.setText(eventBusMessage.pass);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        EventBus.getDefault().unregister(this);
    }

}
