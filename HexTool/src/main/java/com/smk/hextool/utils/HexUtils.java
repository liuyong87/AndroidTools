package com.smk.hextool.utils;

import java.math.BigInteger;

public class HexUtils {

    /**
     * 16进制异或
     * @param str1
     * @param str2
     * @return
     */
    public static String hexXor(String str1, String str2) {
        BigInteger big1= new BigInteger(str1, 16);
        BigInteger big2= new BigInteger(str2, 16);
        return big1.xor(big2).toString(16);
    }

    /**
     * 16进制求和
     *
     * @param hexdata 04
     * @return 05
     */

    public static String makeChecksum(String hexdata) {
        if (hexdata == null || hexdata.equals("")) {
            return "00";
        }
        hexdata = hexdata.replaceAll(" ", "");
        int total = 0;
        int len = hexdata.length();

        if (len % 2 != 0) {
            return "00";
        }
        int num = 0;
        while (num < len) {
            String s = hexdata.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;

        }
        return hexInt(total);
    }


    private static String hexInt(int total) {
        int a = total / 256;
        int b = total % 256;
        if (a > 255) {
            return hexInt(a) + format(b);
        }
        return format(a) + format(b);
    }


    private static String format(int hex) {
        String hexa = Integer.toHexString(hex);
        int len = hexa.length();
        if (len < 2) {
            hexa = "0" + hexa;
        }
        return hexa;
    }
}
