package com.cy.common.util;

/**
 * 序列生成工具
 * @Author: Chen Yi
 * @create: 2020/5/11
 */
public class SequenceUtil {



    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数
        // 0 代表前面补充0
        // num 代表长度为4
        // d 代表参数为正数型
        result = String.format("%0" + num + "d", Integer.parseInt(code));
        return result;
    }
}
