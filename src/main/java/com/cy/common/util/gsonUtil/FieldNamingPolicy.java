package com.cy.common.util.gsonUtil;

import com.google.gson.FieldNamingStrategy;
import com.cy.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 下划线转驼峰
 * @Author: Chen Yi
 * @create: 2019/12/9
 */
public enum FieldNamingPolicy implements FieldNamingStrategy {

    UNDERLINE_TO_CAMEL {
        @Override
        public String translateName(Field f) {
            return underline2Camel(f.getName());
        }
    };


    /**
     * 下划线转驼峰
     *
     * @param underlineStr
     * @return
     */
    public static String underline2Camel(String underlineStr) {

        if (StringUtils.isEmpty(underlineStr)) {

            return StringUtils.EMPTY;
        }

        int len = underlineStr.length();
        StringBuilder strb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {

            char c = underlineStr.charAt(i);
            if (c == Constants.UNDERLINE_CHAR && (++i) < len) {

                c = underlineStr.charAt(i);
                strb.append(Character.toUpperCase(c));
            } else {

                strb.append(c);
            }
        }
        return strb.toString();
    }

}
