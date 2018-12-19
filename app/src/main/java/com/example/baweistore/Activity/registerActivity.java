package com.example.baweistore.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baweistore.EventBusMessage;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class registerActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_rphone;
    private View view_rphone;
    private TextView tv_regiser_huo;
    private EditText ed_rpass;
    private View view_rpass;
    private EditText ed_yan;
    private View view_ryan;
    private TextView tv_regiser;
    private Button bt_regiser;
    private String rphone;
    private String rpass;
    private String ryan;
    private String url="http://172.17.8.100/small/user/v1/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        SMSSDK.setAskPermisionOnReadContact(true);

    }

    private void initView() {
        //手机号
        ed_rphone = (EditText) findViewById(R.id.ed_rphone);
        //获取验证码
        tv_regiser_huo = (TextView) findViewById(R.id.tv_regiser_huo);
       //验证码
        ed_rpass = (EditText) findViewById(R.id.ed_rpass);
        //登录密码
        ed_yan = (EditText) findViewById(R.id.ed_yan);

        tv_regiser = (TextView) findViewById(R.id.tv_regiser);
        //注册按钮
        bt_regiser = (Button) findViewById(R.id.bt_regiser);

        bt_regiser.setOnClickListener(this);

        tv_regiser_huo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                SMSSDK.getVerificationCode("86", rphone);
                

            }
        });
        
    }
    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                            Toast.makeText(registerActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            getData();
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_regiser:
                //手机号
                 rphone = ed_rphone.getText().toString().trim();
                //登录密码
                 rpass = ed_yan.getText().toString().trim();
                //验证码
                 ryan = ed_rpass.getText().toString().trim();

                getData();

                break;
        }
    }

    private void getData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",rphone);
        map.put("pwd",rpass);
        Okutils.getInstance().doPost(url, map, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String message = jsonObject.getString("message");
                    Toast.makeText(registerActivity.this,message, Toast.LENGTH_SHORT).show();
                    if (message.equals("注册成功")){
                        EventBus.getDefault().post(new EventBusMessage(rphone,rpass,"",""));
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {
                Toast.makeText(registerActivity.this,"注册失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 使用完EventHandler需注销，否则可能出现内存泄漏
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
