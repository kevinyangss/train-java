package com.kevin.redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisTest
 * @Description jedis连接 测试
 * @Author kevin.yang
 * @Date 2021-01-25 18:16
 */
public class JedisTest {

    private static Logger logger = LoggerFactory.getLogger(JedisTest.class);

    private static RedisUtil redisUtil;

    static {
        logger.info("init redis ---------------------------------------------------");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(300);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(200);
        jedisPoolConfig.setTestOnBorrow(true);

        String ip="127.0.0.1";
        int port=6379;
        int connection_timeout=2000;
        int so_timeout=5000;
        logger.info("history_redis_ip :" + ip);
        // redis设置了密码则赋值
        String password = "";
        redisUtil = new RedisUtil(jedisPoolConfig, ip, port, connection_timeout, so_timeout, password);
        logger.info("end init redis ---------------------------------------------------");
    }

    public static void main(String[] args) {

//        redisUtil.put("test", "abc");

        String val = redisUtil.get("test", String.class);
        System.out.println(val);

//        redisUtil.clean("test");
        System.exit(-1);

    }
}
