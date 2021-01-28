package com.cy.common.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: cy
 * @Date: 2021/1/15
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Translate {
    /**
     * 目标表
     * @return
     */
    String targetTab() default "";

    /**
     * 翻译的目标表的CODE字段
     * @return
     */
    String targetCode() default "";

    /**
     * 要翻译的code对应值字段
     * @return String
     */
    String targetValue() default "";

    /**
     * 翻译后的目标实体类存储属性
     * @return
     */
    String targetField() default "";
}
