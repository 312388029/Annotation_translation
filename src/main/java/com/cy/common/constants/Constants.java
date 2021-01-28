package com.cy.common.constants;

public final class Constants {

    private Constants() {

    }

    public static final String DEFAULT_PASSWORD = "123456";

    // 接口返回码code常量定义
    public static final String SUCCEED = "0000";
    public static final String FAIL = "1000";

    //token超时
    public static final String TOKEN_OVERTIME = "6666";

    // profile
    public static final String PROFILE_DEV = "dev";
    public static final String PROFILE_TEST = "test";
    public static final String PROFILE_PROD = "prod";

    // 对应的token前缀，存入redis时使用
    public static final String MANAGE_TOKEN_PREFIX = "manage_";
    public static final String MOBILE_TOKEN_PREFIX = "mobile_";

    public static String getManageTokenPrefix(String tokenValue) {
        return MANAGE_TOKEN_PREFIX + tokenValue;
    }

    public static String getMobileTokenPrefix(String tokenValue) {
        return MOBILE_TOKEN_PREFIX + tokenValue;
    }

    // 字典缓存前缀，存入redis时使用
    public static final String DIC_TYPE_PREFIX = "DIC_TYPE=";
    // 商品类型缓存KEY，存入redis时使用
    public static final String GOODS_TYPE_KEY = "GOODS_TYPE_KEY";

    // Redis中Token超时时间30分钟
    //public static final long REDIS_EXPIRY_TIME_THIRTY_MINUTE = 30 * 60;
    public static final long REDIS_EXPIRY_TIME_THIRTY_MINUTE = 60 * 60 * 24 * 7;

    //下划线char
    public static final char UNDERLINE_CHAR = '_';
}
