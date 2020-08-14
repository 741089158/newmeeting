package com.cc.component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cc.service.NewUserService;
import com.cc.service.SysService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SysService sysService;

    @Autowired
    private NewUserService newUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //登陆信息   部署放开
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession httpSession = request.getSession();
        String username = (String) httpSession.getAttribute("username");
        if (username == null || username == "") {
            username = "E900916";
        }
        Map<String, String> map = newUserService.queryUserByAccount(username);
        //System.out.println(map);
        if (username != null && username.length() > 0) {
            // TODO 根据USERNAME获取NAME
            httpSession.setAttribute("name", map.get("name"));
            String department = map.get("department");
            //String substring = department.substring(department.lastIndexOf(" ")+1, department.length());
            httpSession.setAttribute("department", department);
            // 设置权限字段
            checkAuth(request, username);
        }
        // TODO 设置登录人昵称和头像
        request.setAttribute("loginUserName", map.get("name"));//名称
        request.setAttribute("loginUserNum", map.get("sAMAccountName"));//工号

        //图片
        // request.setAttribute("loginUserPhoto", "10.18.88.26:8080/image/top.jpg");

        //测试用
//        request.setAttribute("loginUserNum", "A901997");//工号
        request.setAttribute("auth", "sysAdmin");
        return true;
    }

    private void checkAuth(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        String auth = (String) session.getAttribute("auth");
        if (auth == null) {
            if (sysService.findOne(username) == null) {
                auth = "none";
            } else {
                auth = "sysAdmin";
            }
            session.setAttribute("auth", auth);
        }
        request.setAttribute("auth", auth);
    }
}
