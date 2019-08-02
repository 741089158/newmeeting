package com.cc.dao;

import com.cc.entity.Mail;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author TANGXIAN
 */
public interface MailDao {
    List<Mail> findAll(@Param("status") int status);
    void add(Mail mail);
    Mail findByid(@Param("id") int id);
    void update(Mail mail);

    List<Mail> findMailByMid(String pId);
}
