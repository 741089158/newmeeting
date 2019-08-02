package com.cc.service.Impl;

import com.cc.dao.MeetDeptDao;
import com.cc.entity.MeetDept;
import com.cc.entity.MeetRoom;
import com.cc.entity.SubOffice;
import com.cc.service.MeetDeptService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author HOEP
 * @data 2019/4/23
 */
@Service("meetDeptService")
public class MeetDeptServiceImpl implements MeetDeptService {
    @Autowired
    private MeetDeptDao meetDeptDao;

    public List<Map<String,String>> fidnAll(Integer page, Integer size, String deptName) {
        PageHelper.startPage(page,size);
        return  meetDeptDao.findAll(deptName);
    }

    public MeetDept findByid(String deptId) {
        MeetDept byid = meetDeptDao.findByid(deptId);
        return byid;
    }

    public void update(MeetDept meetDept) {
        meetDeptDao.update(meetDept);
    }


    public void add(MeetDept meetDept) {
        String uuid = String.valueOf(UUID.randomUUID());
        String[] split = uuid.split("-");
        meetDept.setDeptid(split[1]);
        meetDeptDao.add(meetDept);
    }

    public void delect(String id) {
        meetDeptDao.delete(id);
    }

    @Override
    public List<SubOffice> findOffice() {
        return meetDeptDao.findOffice();
    }

    @Override
    public MeetDept findByDeptName(String deptname) {
        return meetDeptDao.findByDeptName(deptname);
    }
}
