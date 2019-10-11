package com.service.core.impl;

import com.core.aop.cache.annotation.RedisCache;
import com.dao.demo2.Demo2Mapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.model.to.PageBean;
import com.service.core.Demo2CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Demo2CoreServiceImpl implements Demo2CoreService {

    @Autowired
    private Demo2Mapper demo2Mapper;

    @Override
    public void insert() {
        demo2Mapper.insert();
    }

    @Override
    public List<Integer> getList() {
        return demo2Mapper.getList();
    }

    @RedisCache(namespace = "DEMO_2_", key = {"#0","#1"}, expire = 60)
    @Override
    public PageBean<Integer> getPage(Integer pageNumber, Integer pageSize) {
        Page<Integer> page = PageHelper.startPage(pageNumber, pageSize, true);
        demo2Mapper.getList();
        return new PageBean<>(page);
    }

}
