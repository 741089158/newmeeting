package com.cc.service;

import com.cc.entity.HighLight;

public interface HighService {
    public HighLight findRecord(HighLight highLight);

    void insertRecord(HighLight highLight);
}
