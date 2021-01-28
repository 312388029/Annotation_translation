package com.cy.service;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Yjie
 */
public interface BaseService<T> {
    int insert(T record);

    int update(T record);

    T getById(Object key);

    List<T> listByExample(Example example);
}
