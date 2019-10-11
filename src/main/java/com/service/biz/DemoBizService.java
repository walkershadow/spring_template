package com.service.biz;

import com.model.to.PageBean;

import java.util.List;

public interface DemoBizService {

    void insert();

    List<Integer> getList();

    PageBean<Integer> getPage(Integer pageNumber, Integer pageSize);

}
