package com.cc.service.Impl;

import com.cc.dao.HighDao;
import com.cc.entity.HighLight;
import com.cc.service.HighService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HighServiceImpl implements HighService {

    @Autowired
    private HighDao highDao;

    @Override
    public HighLight findRecord(HighLight highLight) {
        HighLight light = highDao.findRecord(highLight);
        return light;
    }

    @Override
    public void insertRecord(HighLight highLight) {
        highDao.insertRecord(highLight);
    }
}
