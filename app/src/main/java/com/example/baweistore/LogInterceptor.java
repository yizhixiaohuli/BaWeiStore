package com.example.baweistore;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * date:2018/12/14
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class LogInterceptor implements HttpLoggingInterceptor.Logger  {
    @Override
    public void log(String message) {
        Log.e("HttpLogInfo", message);
    }
}
