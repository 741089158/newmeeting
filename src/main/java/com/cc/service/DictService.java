package com.cc.service;

import com.cc.entity.Dict;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface DictService {

    List<Dict> findAll();

    void add(Dict dict) ;

    Dict findOne(int id);


    void delete(int id);

    void update(Dict dict);

    Page<Map<String, Object>> list(Page page, String condition);


    List<Dict> findPage(int pageNum, int pageSize, String name);

    List<Dict> findByPid(Integer page, Integer size, Integer pid);
}
