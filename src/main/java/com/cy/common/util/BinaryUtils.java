package com.cy.common.util;

import org.apache.commons.lang3.StringUtils;

public class BinaryUtils {
    private BinaryUtils() {
    }

    public static void main(String[] args){
        System.out.println(strToBinary("{\"nameName\":\"123456\"}"));
        System.out.println(binstrToStr("1111011 100010 1101110 1100001 1101101 1100101 1001110 1100001 1101101 1100101 100010 111010 100010 110001 110010 110011 110100 110101 110110 100010 1111101 "));
    }

    /**
     * 字符串转二进制
     *
     * @param str
     * @return
     */
    public static String strToBinary(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(Integer.toBinaryString(chars[i])).append(" ");
        }
        return sb.toString();
    }

    /**
     * 二进制转字符串
     *
     * @param binStr
     * @return
     */
    public static String binstrToStr(String binStr) {
        String[] tempStr = binStr.split(" ");
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    /**
     * 将二进制转换成字符
     *
     * @param binStr
     * @return
     */
    private static char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    /**
     * 将二进制字符串转换成int数组
     *
     * @param binStr
     * @return
     */
    private static int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }
}
