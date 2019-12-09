package com.stu.module.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program: stu-parent
 * @description:
 * @author: Mr.Zhang
 * @create: 2019-11-08 18:03
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                // http.httpBasic() // HTTP Basic
                // 登录跳转 URL
                .loginPage("/authentication/require")
                // 处理表单登录 URL
                .loginProcessingUrl("/login")
                .successHandler(authenticationSucessHandler)
//              .defaultSuccessUrl("/index")
                .and()
                .authorizeRequests() // 授权配置
                // 登录跳转 URL 无需认证
                .antMatchers("/authentication/require" , "/login.html").permitAll()
                // 所有请求
                .anyRequest()
                // 都需要认证
                .authenticated()
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }
}
