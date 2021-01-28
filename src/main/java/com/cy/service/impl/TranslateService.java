package com.cy.service.impl;

import com.cy.mapper.TranslateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 翻译service
 * @Author: cy
 * @Date: 2021/1/27
 */
@Service
public class TranslateService {

    @Autowired
    private TranslateMapper mapper;

    public Object translate(Object base,Class oClass){
        return base;
    }

    public Map getTranslateField(String tabName, String code, String value, String key){
        return mapper.getTranslateDictValue(tabName, code, value, key);
    }
}
