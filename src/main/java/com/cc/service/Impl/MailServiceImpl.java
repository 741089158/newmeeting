package com.cc.service.Impl;

import com.cc.dao.MailDao;
import com.cc.entity.Mail;
import com.cc.service.MailService;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TANGXIAN
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
    @Autowired
    private MailDao mailDao;


    public List<Mail> findAll(int status) {
        List<Mail> all = mailDao.findAll(status);
        return all;
    }
    
    public List<Mail> findPage(int status,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        return mailDao.findAll(status);
    }

    public void add(Mail mail) {
    	mailDao.add(mail);
    }

    public Mail findByid(int id) {
        return mailDao.findByid(id);
    }
    
    public void update(Mail mail) {
    	mailDao.update(mail);
    }

    @Override
    public List<Mail> findMailByMid(String pId) {
        return mailDao.findMailByMid(pId);
    }

}
