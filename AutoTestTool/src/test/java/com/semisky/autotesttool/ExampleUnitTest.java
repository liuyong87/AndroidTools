package com.semisky.autotesttool;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static final String PREFIX_LOG = "android_log_";
    private static final String SUFFIX_LOG = ".log";
    @Test
    public void testMethod(){
        String newLogFileName = PREFIX_LOG+"1997-07-01 9:00"+SUFFIX_LOG;
        System.out.println("====1111======");
        System.out.println(newLogFileName);
        newLogFileName = newLogFileName.substring(newLogFileName.lastIndexOf("_")+1,newLogFileName.lastIndexOf("."));
        System.out.println("====2222======");
        System.out.println(newLogFileName);
        System.out.println("==========");
    }
}