package com.stu.moudle.service.impl;


import com.stu.moudle.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Service
public class TokenServiceImpl implements TokenService {


    private static final String TOKEN_KEY = "token";


    @Autowired private RedisTemplate<String,Object> redisTemplate;

    @Override
    public String createToken() {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token,token,15000, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_KEY);
        if (StringUtils.isEmpty(token)){
            token = request.getParameter(TOKEN_KEY);
            if (StringUtils.isEmpty(token)){
                throw new RuntimeException("token is must be have");
            }
        }

        if (!redisTemplate.hasKey(token)){
            throw new RuntimeException("token doesn't exist");
        }

        Boolean delete = redisTemplate.delete(token);
        if (!delete){
            throw new RuntimeException("token has been deleted");
        }

    }

}
