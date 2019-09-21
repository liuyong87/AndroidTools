package com.semisky.autotesttool.utils;

import android.util.Log;

public class Logutil {
    private static final String TAG_PREFIX = "AutoLog_";
    private static final boolean V = true;
    private static final boolean D = true;
    private static final boolean I = true;
    private static final boolean W = true;
    private static final boolean E = true;

    public static void e(String tag, String msg) {
        if (E) {
            Log.e(TAG_PREFIX + tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (W) {
            Log.w(TAG_PREFIX + tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (I) {
            Log.i(TAG_PREFIX + tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (D) {
            Log.d(TAG_PREFIX + tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (V) {
            Log.v(TAG_PREFIX + tag, msg);
        }
    }


}
