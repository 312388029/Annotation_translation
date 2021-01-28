package com.cy.common.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.parser.impl.AbstractParser;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.Map;

public class MysqlParser extends AbstractParser {
    @Override
    public String getPageSql(String sql) {
        return sql + " LIMIT ? OFFSET ?";
    }

    @Override
    public Map<String, Object> setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page<?> page) {
        Map<String, Object> paramMap = super.setPageParameter(ms, parameterObject, boundSql, page);
        paramMap.put(PAGEPARAMETER_FIRST, page.getPageSize());
        paramMap.put(PAGEPARAMETER_SECOND, page.getStartRow());
        return paramMap;
    }
}
