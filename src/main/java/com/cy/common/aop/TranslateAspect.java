package com.cy.common.aop;


import com.cy.common.annotation.Translate;
import com.cy.service.impl.TranslateService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Map;


/**
 * @Description: 拦截注解翻译AOP
 * @Author: cy
 * @Date: 2021/1/27
 */
@Aspect
@Component
public class TranslateAspect {

    Logger logger = LoggerFactory.getLogger(TranslateAspect.class);


    @Autowired
    private TranslateService translateService;

    /**
     * 定义切点Pointcut拦截所有对服务器的请求
     */
    @Pointcut("execution( * com.cy.service.impl.TranslateService.translate(..))")
    public void excudeService() {
    }

    /**
     * 在环绕通知中目标对象方法被调用后的结果进行再处理
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //这是定义开始事件
        long time1 = System.currentTimeMillis();
        //这是方法并获取返回结果
        Object[] agrs = joinPoint.getArgs();
        //这是获取到结束时间
        long time2 = System.currentTimeMillis();
        logger.info("获取JSON数据耗时：" + (time2 - time1) + "ms");
        //解析开始时间
        long start = System.currentTimeMillis();
        //开始解析
        Object result = this.parseCodeToValue(agrs[0], (Class) agrs[1]);
        //解析结束时间
        long end = System.currentTimeMillis();
        logger.info("解析注入JSON数据耗时：" + (end - start) + "ms");
        logger.info("经字典翻译后结果：" + result.toString());
        return result;
    }


    private Object parseCodeToValue(Object result, Class objClass) throws NoSuchFieldException, IllegalAccessException, ParseException {
        if (result != null) {
            //将Object类型强转
            try {
                objClass.cast(result);
            } catch (Exception e) {
                logger.info("传入翻译对象和其类型不匹配");
                e.printStackTrace();
            }
            //遍历该对象所有字段
            for (Field field : result.getClass().getDeclaredFields()) {
                //如果该属性上面有@Translate注解，则进行翻译
                if (field.getAnnotation(Translate.class) != null) {
                    //拿到注解的targetTab属性的值
                    String tabName = field.getAnnotation(Translate.class).targetTab();
                    //拿到注解的targetCode属性的值
                    String code = field.getAnnotation(Translate.class).targetCode();
                    //拿到注解的targetCode属性的值
                    String value = field.getAnnotation(Translate.class).targetValue();
                    //拿到注解的targetField属性的值
                    String targetFieldStr = field.getAnnotation(Translate.class).targetField();
                    Field targetField = null;
                    try {
                        targetField = result.getClass().getDeclaredField(targetFieldStr);
                        if (targetField == null) {
                            throw new Exception("目标字段为空");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("目标字段未找到", e.getCause());
                    }
                    //设置目标存储字段为可访问的
                    targetField.setAccessible(true);
                    //获取当前带翻译的值
                    //设置属性为可访问的
                    field.setAccessible(true);
                    String key = field.get(result).toString();
                    //翻译字典值对应的text值
                    Object resultValue = translateCode2Value(tabName, code, value, key);
                    logger.debug("翻译表名：" + tabName + " 目标code：" + code + " key值：" + key + " 目标字段： " + value + " 翻译结果：" + resultValue.toString());
                    targetField.set(result, resultValue);
                }
            }
        }
        return result;
    }

    /**
     * 翻译字典文本
     *
     * @param tabName 表名
     * @param code    翻译字段
     * @param value   目标字段
     * @param key     值
     * @return
     */
    private Object translateCode2Value(String tabName, String code, String value, String key) {
        //如果key为空直接返回就好了
        if (isEmpty(tabName) || isEmpty(code) || isEmpty(value) || isEmpty(key)) {
            return null;
        }
        //分割key值
        String[] keys = key.split(",");
        Object tmpValue = null;
        //循环keys中的所有值
        for (String k : keys) {
            logger.debug("字典key：" + k);
            if (k.trim().length() == 0) {
                continue;//跳过循环
            }
            Map result = translateService.getTranslateField(tabName, code, value, k);
            for (Object o : result.keySet()) {
                tmpValue = result.get(o);
            }
        }
        //返回翻译的值
        return tmpValue;
    }


    /**
     * 非空校验
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }


}
