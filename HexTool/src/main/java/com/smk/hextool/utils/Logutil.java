package com.smk.hextool.utils;

import android.util.Log;

public class Logutil {
    private static final String PREFIX_TAG = "jack_";

    public static void v(String tag, String msg) {
        Log.v(PREFIX_TAG + tag, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(PREFIX_TAG + tag, msg);
    }

    public static void i(String tag, String msg) {
        Log.i(PREFIX_TAG + tag, msg);
    }

    public static void w(String tag, String msg) {
        Log.w(PREFIX_TAG + tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(PREFIX_TAG + tag, msg);
    }
}
