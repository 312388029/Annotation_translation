package com.cy.common.constants;

/**
 * 正则表达式
 */
public interface RegexConstants {
    String EMPTY = "";

    // 手机号正则表达式
    String PHONE_REGEX = "\\d{11}";
    // 银行卡号正则表达式
    String BANK_CARD_REGEX = "[0-9]{13,19}";
    // 订单号正则表达式
    String ORDER_ID_REGEX = "[0-9a-zA-Z]{1,32}";
    // 短信验证码正则表达式
    String SMS_CODE_REGEX = "\\d{6}";
    // 18位身份证号正则表达式
    String ID_CORD_REGEX = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)(?!.*[^0-9X])$";

    String DATE_TIME_REGEX = "\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}";
}
