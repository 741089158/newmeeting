package com.cc.controller;


import com.alibaba.fastjson.JSON;
import com.cc.entity.HighLight;
import com.cc.entity.ResponseData;
import com.cc.service.HighService;
import com.cc.util.DateChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("high")
public class HighController {


    @Autowired
    private HighService highService;


    @RequestMapping(value = "/highLight")
    public String highLight(HighLight highLight){
        if (highLight.getUid()==null||highLight.getUid()==""){
            highLight.setUid("A901998");
        }

        HighLight light = highService.findRecord(highLight);//查询记录
        ResponseData responseData =null;
        if (light!=null){//存在记录
             responseData = new ResponseData(0, 404, "重复提交!", "");
        }else {
            String nowTime = DateChange.dateFormat(new Date(), "yyyy-MM-dd HH:mm");
            highLight.setCreateTime(nowTime);
            try {
                highService.insertRecord(highLight);
                responseData = new ResponseData(0, 200, "HighLight成功!", "");
            } catch (Exception e) {
                e.printStackTrace();
                responseData = new ResponseData(0, 404, "提交失败!", "");
            }
        }
        return JSON.toJSONString(responseData);
    }
}
