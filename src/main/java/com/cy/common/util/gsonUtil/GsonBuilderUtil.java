package com.cy.common.util.gsonUtil;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

/**
 * @Author: Chen Yi
 * @create: 2019/11/20
 * 自定义Gson生成方法
 */
public class GsonBuilderUtil {


    /**
     * 转下划线、时间转时间戳
     * @return
     */
    public static Gson toUnder() {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gb.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        //禁止HTML格式转码
        gb.disableHtmlEscaping();
        Gson gson = gb.create();
        return gson;
    }

    /**
     * 时间戳转为时间
     * @return
     */
    public static Gson getBack() {
        GsonBuilder gb = new GsonBuilder();
        //时间格式LONG2DATE
        gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer());
        Gson gson = gb.create();
        return gson;
    }
//    public static Gson toUpper() {
//        GsonBuilder gb = new GsonBuilder();
//        gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
//        gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
//        gb.setFieldNamingPolicy(com.cy.common.util.gsonUtil.FieldNamingPolicy.UNDERLINE_TO_CAMEL);
//        Gson gson = gb.create();
//        return gson;
//    }


}