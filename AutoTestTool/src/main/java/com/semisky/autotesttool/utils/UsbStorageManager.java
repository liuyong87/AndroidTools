package com.semisky.autotesttool.utils;

import android.annotation.SuppressLint;
import android.provider.Settings;

import com.semisky.autotesttool.app.AutoTestApp;

public class UsbStorageManager {

    /**
     * 打开ADB调试开关
     *
     * @param enable
     */
    @SuppressLint("NewApi")
    public void reqAdbEnable(boolean enable) {
        if (enable) {

        }
        Settings.Global.putInt(AutoTestApp.getInstance().getCtx().getContentResolver(), Settings.Global.ADB_ENABLED, (enable ? 1 : 0));
    }

    /**
     * 获取ADB调试开关是否打开<br>
     * 0 ：禁用
     * 1 ：启用
     *
     * @return
     */
    @SuppressLint("NewApi")
    public boolean isAdbEnable() {
        return (Settings.Global.getInt(AutoTestApp.getInstance().getCtx().getContentResolver(), Settings.Global.ADB_ENABLED, 0) > 0);
    }

    public static UsbStorageManager getInstance() {
        return Singleton.INSTANCE;
    }

    private UsbStorageManager() {

    }

    private static class Singleton {
        private static UsbStorageManager INSTANCE = new UsbStorageManager();
    }
}
