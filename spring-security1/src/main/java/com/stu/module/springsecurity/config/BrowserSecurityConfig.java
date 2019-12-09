package com.stu.module.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Program: stu-parent
 * @Description: ${}
 * @Author: Mr.Zhang
 * @Create: 2019-11-07 17:10
 **/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

//    UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
//    FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//基于表单认证
//          http.httpBasic()//HTTP Basic方式
                .and()
                .authorizeRequests()//授权认证
                .anyRequest()//所有的请求
                .authenticated();//都需认证
    }
}
