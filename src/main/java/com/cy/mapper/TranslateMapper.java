package com.cy.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Map;

public interface TranslateMapper {


    Map getTranslateDictValue(@Param("tabName") String tabName, @Param("code") String code, @Param("value") String value, @Param("key") String key);
}
