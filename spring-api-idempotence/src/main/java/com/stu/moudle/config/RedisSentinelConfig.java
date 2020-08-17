package com.stu.moudle.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Redis哨兵模式的配置
 */
@Configuration
public class RedisSentinelConfig {

    @Value("${spring.redis.sentinel.nodes}")
    private String sentinelNodes;

    @Value("${spring.redis.sentinel.master}")
    private String master;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxTotal;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitMillis;

    @Value("${spring.redis.jedis.pool.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.redis.jedis.pool.num-tests-pereviction-run}")
    private int numTestsPerEvictionRun;

    @Value("${spring.redis.jedis.pool.time-between-eviction-runs}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.redis.jedis.pool.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${spring.redis.jedis.pool.test-while-idle}")
    private boolean testWhileIdle;

    /**
     * Redis连接池的配置
     *
     * @return JedisPoolConfig
     */
    @Bean
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }

    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        Set<RedisNode> nodes = new HashSet<>();
        String[] ips = sentinelNodes.split(",");
        for (int i = 0; i < ips.length; i++) {
            String[] ip_port = ips[i].split(":");
            nodes.add(new RedisNode(ip_port[0].trim(), Integer.valueOf(ip_port[1])));
        }
        redisSentinelConfiguration.setMaster(master);
        redisSentinelConfiguration.setSentinels(nodes);
        redisSentinelConfiguration.setPassword(RedisPassword.of(password));
        return redisSentinelConfiguration;
    }

   public RedisClusterConfiguration redisClusterConfiguration(){
       RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
       Set<RedisNode> nodes = new HashSet<>();
       String[] ips = sentinelNodes.split(",");
       for (int i = 0; i < ips.length; i++) {
           String[] ip_port = ips[i].split(":");
           nodes.add(new RedisNode(ip_port[0].trim(), Integer.valueOf(ip_port[1])));
       }
       redisClusterConfiguration.setClusterNodes(nodes);
       redisClusterConfiguration.setMaxRedirects(3);
       redisClusterConfiguration.setPassword(RedisPassword.of(password));
       return redisClusterConfiguration;
   }



    /**
     * redis连接工厂类
     *
     * @return JedisConnectionFactory
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        //单机模式
//        JedisConnectionFactory factory1 = new JedisConnectionFactory(getJedisPoolConfig());
        // 集群模式
//        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration(), getJedisPoolConfig());
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration(), getJedisPoolConfig());
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return RedisTemplate<String , Object>
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // Template初始化
        initDomainRedisTemplate(redisTemplate);
        return redisTemplate;
    }

    /**
     * 设置数据存入 redis 的序列化方式 使用默认的序列化会导致key乱码
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        // 开启redis数据库事务的支持
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        // 如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // fastJson序列化对象设置
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        // value序列化方式采用jackson
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        // hash的value序列化方式采用jackson
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
    }
}