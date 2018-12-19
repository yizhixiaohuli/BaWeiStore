package com.example.baweistore;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * date:2018/11/23
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class Okutils {
    private static volatile Okutils sOkutils;
    private final Handler mHandler;
    private final OkHttpClient mOkHttpClient;
    private String userId;
    private String sessionId;


    private Okutils() {
       /* //添加公共参数拦截器
        HashMap<String, String> map = new HashMap<>();
        //公共参数的值
        map.put("key","value");
        //添加成功
        PublicInterceptor publicInterceptor = new PublicInterceptor(map);*/
        mHandler = new Handler(Looper.getMainLooper());
        mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor(new   LogInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY))
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        //注册eventbus
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }

    public static Okutils getInstance() {
        if (sOkutils == null) {
            synchronized (Okutils.class) {
                if (sOkutils == null) {
                    sOkutils = new Okutils();
                }
            }
        }
        return sOkutils;
    }

    public void doGet(String Url, final OkHttpCallBack httpCallBack) {
        Request request = new Request.Builder()
                .addHeader("sessionId",userId)
                .addHeader("userId",sessionId)
                .get()
                .url(Url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (httpCallBack != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpCallBack.failed(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    final String s = response.body().string();
                    if (httpCallBack != null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                httpCallBack.success(s);
                            }
                        });
                    }
                }
            }
        });
    }

    public void doPost(String Url, Map<String, String> map, final OkHttpCallBack httpCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            for (String key : map.keySet()) {
                builder.add(key, map.get(key));
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .url(Url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (httpCallBack != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpCallBack.failed(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    final String s = response.body().string();
                    if (httpCallBack != null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                httpCallBack.success(s);
                            }
                        });
                    }
                }

            }
        });
    }

    public void doHeadPost(String Url, Map<String, String> map, final OkHttpCallBack httpCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            for (String key : map.keySet()) {
                builder.add(key, map.get(key));
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .addHeader("sessionId",userId)
                .addHeader("userId",sessionId)
                .post(body)
                .url(Url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (httpCallBack != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpCallBack.failed(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    final String s = response.body().string();
                    if (httpCallBack != null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                httpCallBack.success(s);
                            }
                        });
                    }
                }

            }
        });
    }
    public void doHeadDelete(String Url, Map<String, String> map, final OkHttpCallBack httpCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            for (String key : map.keySet()) {
                builder.add(key, map.get(key));
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .addHeader("sessionId",userId)
                .addHeader("userId",sessionId)
                .delete(body)
                .url(Url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (httpCallBack != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpCallBack.failed(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    final String s = response.body().string();
                    if (httpCallBack != null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                httpCallBack.success(s);
                            }
                        });
                    }
                }

            }
        });
    }

    public void doHeadPut(String Url, Map<String, String> map, final OkHttpCallBack httpCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            for (String key : map.keySet()) {
                builder.add(key, map.get(key));
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .addHeader("sessionId",userId)
                .addHeader("userId",sessionId)
                .put(body)
                .url(Url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (httpCallBack != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpCallBack.failed(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    final String s = response.body().string();
                    if (httpCallBack != null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                httpCallBack.success(s);
                            }
                        });
                    }
                }

            }
        });
    }

    //eventbus 把数据传过来
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void reception(EventBusMessage eventBusMessage) {
        userId = eventBusMessage.getUserId();
        sessionId = eventBusMessage.getSessionId();
        Log.e("好","userid:"+userId+"sesion:"+sessionId);

    }


    //接口
    public interface OkHttpCallBack {
        void success(String s);

        void failed(Exception e);
    }
}
