package com.example.baweistore;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.demo.ggy.Dao.DaoMaster;
import com.demo.ggy.Dao.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * date:2018/12/10
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class MyApplication extends Application {
    private final static String DB_NAME="shopcar";
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        SQLiteDatabase database = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
        MobSDK.init(this);
        SkinCompatManager.withoutActivity(this)
                // 自定义加载策略，指定SDCard路径
                //.addStrategy(new CustomSDCardLoader())
                // material design
                .addInflater(new SkinMaterialViewInflater())
                // ConstraintLayout
                .addInflater(new SkinConstraintViewInflater())
                // CardView v7
                .addInflater(new SkinCardViewInflater())
                // 关闭状态栏换肤
                .setSkinStatusBarColorEnable(false)
                // 关闭windowBackground换肤
                .setSkinWindowBackgroundEnable(false)
                .loadSkin();

    }
    public static DaoSession getDaoSeesion(){
        return mDaoSession;
    }
}
