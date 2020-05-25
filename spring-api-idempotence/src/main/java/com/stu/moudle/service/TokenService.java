package com.stu.moudle.service;


import javax.servlet.http.HttpServletRequest;

/**
 * token 操作接口
 */
public interface TokenService {


    String createToken();


    void checkToken(HttpServletRequest request);


}
