package com.service.core;

import com.model.to.PageBean;

import java.util.*;

public interface Demo1CoreService {
    void insert();

    List<Integer> getList();

    PageBean<Integer> getPage(Integer pageNumber, Integer pageSize,String key);

}
