package com.cc.dao;

import com.cc.entity.MeetDept;
import com.cc.entity.SubOffice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author HOEP
 * @data 2019/4/23
 */
public interface MeetDeptDao {
    List<Map<String,String>> findAll(@Param("deptName") String deptName);

    MeetDept findByid(String deptId);

    void add(MeetDept meetDept);

    void update(MeetDept meetDept);

    void delete(String id);

    List<SubOffice> findOffice();

    List<Map<String ,String>> findDept();

    MeetDept findByDeptName(String deptname);
}
