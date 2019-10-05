package com.semisky.autotesttool.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.semisky.autotesttool.app.AutoTestApp;
import com.semisky.autotesttool.constant.Definition;

public class PreferenceUtil {

    private static final String SP_NAME = "CatchLogSP";
    private static final String KEY_LOG_PRIORITY = "LogPriority";
    private static final String KEY_LOG_TAG = "LogTag";

    /**
     * 获取断点过滤日志优先级
     *
     * @return
     */
    public static String getLastFilterLogPriority() {
        return getSP().getString(KEY_LOG_PRIORITY, Definition.LogcatPriority.V);
    }

    /**
     * 保存断点过滤日志优先级
     *
     * @param filterLogPriority
     */
    public static void saveLastFilterLogPriority(String filterLogPriority) {
        if (TextUtils.isEmpty(filterLogPriority)) {
            return;
        }
        getSP().edit().putString(KEY_LOG_PRIORITY, filterLogPriority).apply();
        sync();
    }

    /**
     * 获取断点过滤日志TAG
     *
     * @return
     */
    public static String getLastFilterLogTag() {
        return getSP().getString(KEY_LOG_TAG, Definition.TAG_DEF);
    }

    /**
     * 保存断点过滤日志TAG
     *
     * @param filterTag
     */
    public static void saveLastFilterLogTag(String filterTag) {
        getSP().edit().putString(KEY_LOG_TAG, filterTag).apply();
        sync();
    }

    static SharedPreferences getSP() {
        return AutoTestApp.getInstance().getCtx().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    static void sync() {
        try {
            Runtime.getRuntime().exec("sync");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
