package com.service.biz.impl;

import com.core.aop.cache.annotation.RedisCache;
import com.model.to.PageBean;
import com.service.biz.DemoBizService;
import com.service.core.Demo1CoreService;
import com.service.core.Demo2CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemoBizServiceImpl implements DemoBizService {

    @Autowired
    private Demo1CoreService demo1CoreService;
    @Autowired
    private Demo2CoreService demo2CoreService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert() {
        demo1CoreService.insert();
        demo2CoreService.insert();
    }

    @Override
    public List<Integer> getList() {
        return demo1CoreService.getList();
    }

    @Override
    public PageBean<Integer> getPage(Integer pageNumber, Integer pageSize) {
        return demo1CoreService.getPage(pageNumber,pageSize,"");
    }



}
