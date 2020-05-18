package com.stu.module.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ZhangSheng
 */
@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private ObjectMapper mapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        //登录成功响应 uthentication 对象的json字符串
        //response.setContentType("application/json;charset=utf-8")
        //response.getWriter().write(mapper.writeValueAsString(authentication))
        //跳转到具体的页面
        redirectStrategy.sendRedirect(request, response, "/index");
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//        System.out.println("savedRequest:"+savedRequest);
//        String redirectUrl = savedRequest.getRedirectUrl();
//        System.out.println("redirectUrl:"+redirectUrl);
//        redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
    }
}