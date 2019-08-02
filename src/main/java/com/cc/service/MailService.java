package com.cc.service;

import com.cc.entity.Mail;

import java.util.List;


public interface MailService {
    List<Mail> findAll(int status);
    void add(Mail mail);
    void update(Mail mail);

    List<Mail> findMailByMid(String pId);
}
