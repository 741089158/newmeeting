package com.cc.service.Impl;

import edu.yale.its.tp.cas.client.IContextInit;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyContextInit implements IContextInit {


    @Override
    public String getTranslatorUser(String userName) {
        //System.out.println(userName+"-----------------------------------------------------------");
        return userName;
    }

    @Override
    public void initContext(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain, String userName) {
       // System.out.println(userName+"-----------------------------------------------------------");

        ((HttpServletRequest)request).getSession().setAttribute("username", userName);


    }

}
