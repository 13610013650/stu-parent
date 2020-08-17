package com.stu.rocketmq.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil implements InitializingBean {


    public static void main(String[] args) throws Exception {
//        String token = testCreateToken();
//        System.out.println(token);
    }


    public static String testCreateToken() {
        String username = "zhangsan";
        String password = "123";
        String tokenKey = "ZCEQIUBFKSJBFJH2020BQWE";
        long expires_date = 30 * 60 * 100000;
        Date expiresDate = new Date(System.currentTimeMillis() + expires_date);
        Algorithm algorithm = Algorithm.HMAC256(tokenKey);
        Map<String, Object> jwtheader = new HashMap<>();
        jwtheader.put("typ", "JWT");
        jwtheader.put("alg", "HS256");
        String token = JWT.create().withHeader(jwtheader)
                .withClaim("username", username)
                .withClaim("password", password)
                .withExpiresAt(expiresDate)
                .sign(algorithm);
        return token;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("===========afterPropertiesSet===========");
    }
}