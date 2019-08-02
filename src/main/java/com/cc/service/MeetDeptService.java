package com.cc.service;

import com.cc.entity.MeetDept;
import com.cc.entity.MeetRoom;
import com.cc.entity.SubOffice;

import java.util.List;
import java.util.Map;

/**
 * @author HOEP
 * @data 2019/4/23
 */
public interface MeetDeptService {
    List<Map<String,String>> fidnAll(Integer page, Integer size, String deptName);

    MeetDept findByid(String deptId);
    void update(MeetDept meetDept);
    void add(MeetDept meetDept);

    void delect(String id);

    List<SubOffice> findOffice();

    MeetDept findByDeptName(String deptname);
}
