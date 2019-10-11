package com.dao.demo1;

import com.core.aop.mutidatasource.annotation.DataSource;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
@DataSource("demo1")
public interface Demo1Mapper {
    void insert();

    List<Integer> getList();

}
