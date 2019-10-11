package com.model.to;

import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@NoArgsConstructor
public class PageBean<E> {

    @Getter
    @Setter
    private int pageNum;
    @Getter
    @Setter
    private int pageSize;
    @Getter
    @Setter
    private long total;
    @Getter
    @Setter
    private int pages;
    @Getter
    @Setter
    private List<E> list;

    public PageBean(Page<E> page){
        this.pageNum=page.getPageNum();
        this.pageSize=page.getPageSize();
        this.total=page.getTotal();
        this.pages=page.getPages();
        list=page.getResult();
    }
 }
