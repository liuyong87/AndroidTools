package com.semisky.autotesttool.manager;

public class LogcatCotrol {
    private static final String TAG = LogcatCotrol.class.getSimpleName();




    public static LogcatCotrol getInstance() {
        return Singleton._INSTANCE;
    }

    /**
     * 内部类懒汉单例
     */
    private static class Singleton {
        private static LogcatCotrol _INSTANCE = new LogcatCotrol();
    }
}
