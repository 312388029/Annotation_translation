package com.cy.service.impl;

import com.cy.domain.Sequence;
import com.cy.mapper.SequenceMapper;
import com.cy.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceMapper sequenceMapper;

    @Override
    public int insert(Sequence record) {
        return sequenceMapper.insertSelective(record);
    }

    @Override
    public int update(Sequence record) {
        return sequenceMapper.updateByPrimaryKey(record);
    }

    @Override
    public Sequence getById(Object key) {
        return sequenceMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<Sequence> listByExample(Example example) {
        return sequenceMapper.selectByExample(example);
    }

    @Override
    public Sequence getOneByExample(Example example) {
        return sequenceMapper.selectOneByExample(example);
    }
}
