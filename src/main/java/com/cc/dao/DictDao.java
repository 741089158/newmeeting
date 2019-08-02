package com.cc.dao;

import com.cc.entity.Dict;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictDao {

    List<Dict> findAll();
    List<Dict> findAll(@Param("name") String name);

    void add(Dict dict);

    Dict findOne(int id);

    void delete(int id);

    void update(Dict dict);

    /**
     * 根据编码获取词典列表
     */
    List<Dict> selectByDictId(@Param("dictId") int dictId);

    /**
     * 查询字典列表
     */
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String conditiion);


    List<Dict> selectByParentDictId(@Param("dictId") int dictId);

    /**
     * 根据父类pid获取词典列表
     */
    List<Dict> findByPid(Integer page, Integer size, Integer pid);
}
