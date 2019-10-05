package com.semisky.autotesttool.app;

import android.app.Application;
import android.content.Context;

import com.semisky.autotesttool.utils.Logutil;

public class AutoTestApp extends Application {
    private static final String TAG = AutoTestApp.class.getSimpleName();
    private static AutoTestApp INSTANCE;
    private static Context mContext;

    public Context getCtx(){
        return mContext;
    }

    public static AutoTestApp getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logutil.i(TAG,"onCreate() ...");
        INSTANCE = this;
        mContext = this.getApplicationContext();
    }

}
