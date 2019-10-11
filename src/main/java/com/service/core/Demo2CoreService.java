package com.service.core;

import com.model.to.PageBean;

import java.util.List;

public interface Demo2CoreService {
    void insert();

    List<Integer> getList();

    PageBean<Integer> getPage(Integer pageNumber, Integer pageSize);

}
