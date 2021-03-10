package com.kevin.redis.redistemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @ClassName RedisClusterTest
 * @Description TODO
 * @Author kevin.yang
 * @Date 2021-01-27 16:08
 */
public class RedisClusterTest {

    private static Logger logger = LoggerFactory.getLogger(RedisClusterTest.class);

    private static Integer maxActive = 8;
    private static Integer maxIdle = 8;
    private static Long maxWait = -1L;
    private static Integer minIdle = 0;
    private static Integer maxRedirects = 3;

    /**
     * redis哨兵模式配置
     */
    private static String sentinel = "";
    private static String master = "";

    /**
     * redis单机模式配置
     */
    private static String host = "127.0.0.1";
    private static Integer port = 6379;

    /**集群模式配置*/
    private static String nodes = "";

    private static String password = "";
    private static Integer database = 0;

    static RedisTemplate<String, Object> redisTemplate = null;

    static {
        logger.info("===========init redisTemplate=============");
        // 连接池配置
        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();

        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpcb.poolConfig(jedisPoolConfig);
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();

        // 连接
        JedisConnectionFactory jedisConnectionFactory = conn(jedisClientConfiguration);

        // redisTemplate
        redisTemplate = redisTemplate(jedisConnectionFactory);
        logger.info("===========finish redisTemplate=============");
    }

    public static void main(String[] args) {
        /**测试用例**/
        getKey();


    }

    private static void getKey(){
        String s = (String)redisTemplate.opsForValue().get("test");
        System.out.println(s);
    }

    private static void put(String value){
        redisTemplate.boundValueOps("key").set(value);
    }

    /**
     * jedis pool配置
     * @return
     */
    private static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        return jedisPoolConfig;
    }

    /**
     *
     * @param jedisClientConfiguration
     * @return
     */
    private static JedisConnectionFactory conn(JedisClientConfiguration jedisClientConfiguration){
        JedisConnectionFactory factory = null;

        if (StringUtils.isNotBlank(sentinel) && StringUtils.isNotBlank(master)) {
            // 哨兵模式
            logger.info("->->->Redis use SentinelConfiguration");
            RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
            String[] sentinelArray = sentinel.split(",");
            for (String s : sentinelArray) {
                try {
                    String[] split1 = s.split(":");
                    redisSentinelConfiguration.addSentinel(new RedisNode(split1[0], Integer.parseInt(split1[1])));
                } catch (Exception e) {
                    throw new RuntimeException(String.format("出现配置错误!请确认sentinelArray=[%s]是否正确", nodes));
                }
            }
            redisSentinelConfiguration.setMaster(master);
            redisSentinelConfiguration.setPassword(RedisPassword.of(password));
            redisSentinelConfiguration.setDatabase(database);
            factory = new JedisConnectionFactory(redisSentinelConfiguration, jedisClientConfiguration);

        } else if (StringUtils.isNotBlank(host)) {
            // 单机模式
            logger.info("->->->Redis use RedisStandaloneConfiguration");
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            if (!StringUtils.isEmpty(password)) {
                redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
            }
            redisStandaloneConfiguration.setPort(port);
            redisStandaloneConfiguration.setHostName(host);
            redisStandaloneConfiguration.setDatabase(database);
            factory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
        } else {
            // 集群模式
            logger.info("->->->Redis use RedisClusterConfiguration");
            String[] split = nodes.split(",");
            Set<HostAndPort> nodes = new LinkedHashSet<>();
            for (int i = 0; i < split.length; i++) {
                try {
                    String[] split1 = split[i].split(":");
                    nodes.add(new HostAndPort(split1[0], Integer.parseInt(split1[1])));
                } catch (Exception e) {
                    throw new RuntimeException(String.format("出现配置错误!请确认node=[%s]是否正确", nodes));
                }
            }
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            nodes.forEach(n -> {
                redisClusterConfiguration.addClusterNode(new RedisNode(n.getHost(), n.getPort()));
            });
            if (!StringUtils.isEmpty(password)) {
                redisClusterConfiguration.setPassword(RedisPassword.of(password));
            }
            redisClusterConfiguration.setMaxRedirects(maxRedirects);
            factory = new JedisConnectionFactory(redisClusterConfiguration, jedisClientConfiguration);
        }

        return factory;
    }

    private static RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jacksonSeial.setObjectMapper(om);
        StringRedisSerializer stringSerial = new StringRedisSerializer();
        template.setKeySerializer(stringSerial);
        template.setValueSerializer(stringSerial);
        template.setHashKeySerializer(stringSerial);
        template.setHashValueSerializer(stringSerial);

        // java.lang.IllegalArgumentException: template not initialized; call afterPropertiesSet() before using it
        template.afterPropertiesSet();
        return template;
    }
}
