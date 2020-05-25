package com.stu.moudle.config;

import org.springframework.context.annotation.Configuration;

/**
 * @package com.stu.moudle.config
 * @auther ZhangSheng
 * @description redis单机版配置类
 * @date 2020/5/20
 * @EMAIL 2647665953@qq.com
 */
@Configuration
public class SingleRedisConfigration {


    /**
     * 设置 redisTemplate 序列化方式
     *
     * @param factory
     * @return
     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//        redisTemplate.setConnectionFactory(factory);
//        // 设置值（value）的序列化采用FastJsonRedisSerializer。
//        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
//        // 设置键（key）的序列化采用StringRedisSerializer。
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }


}
