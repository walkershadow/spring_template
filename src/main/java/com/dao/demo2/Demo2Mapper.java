package com.dao.demo2;

import com.core.aop.mutidatasource.annotation.DataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DataSource("demo2")
public interface Demo2Mapper {
    void insert();

    List<Integer> getList();}
