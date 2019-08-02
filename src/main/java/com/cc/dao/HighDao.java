package com.cc.dao;

import com.cc.entity.HighLight;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HighDao {

    HighLight findRecord(HighLight highLight);//查询记录

    void insertRecord(HighLight highLight);
}
