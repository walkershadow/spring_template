package com.service.core.impl;

import com.core.aop.cache.annotation.RedisCache;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.dao.demo1.Demo1Mapper;
import com.dao.demo2.Demo2Mapper;
import com.model.enums.ErrorEnum;
import com.model.exceptions.XinNiuException;
import com.model.to.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.core.Demo1CoreService;

import java.util.*;

@Service
public class Demo1CoreServiceImpl implements Demo1CoreService {

    @Autowired
    private Demo1Mapper demo1Mapper;

    @Override
    public void insert() {
        demo1Mapper.insert();
    }

    @Override
    public List<Integer> getList() {
        return demo1Mapper.getList();
    }


    @RedisCache(namespace = "DEMO1", key = {"AAA", "#1", "#2.length()"}, expire = 60)
    @Override
    public PageBean<Integer> getPage(Integer pageNumber, Integer pageSize, String key) {
        Page<Integer> page = PageHelper.startPage(pageNumber, pageSize, true);
        demo1Mapper.getList();
        return new PageBean<>(page);
    }


}
