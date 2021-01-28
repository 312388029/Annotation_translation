package com.cy.service;

import com.cy.domain.Sequence;
import tk.mybatis.mapper.entity.Example;


public interface SequenceService extends BaseService<Sequence>{

    /**
     * 根据example获得一个结果
     * @param example
     * @return
     */
    Sequence getOneByExample(Example example);
}
