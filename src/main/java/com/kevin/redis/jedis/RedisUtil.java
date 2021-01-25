package com.kevin.redis.jedis;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.*;

import java.util.Set;

public class RedisUtil {

    Gson gson = new Gson();

    private JedisPool pool;

    public RedisUtil(JedisPoolConfig config, String host, int port, int connectionTimeout, int soTimeout) {
        this(config, host, port, connectionTimeout, soTimeout, null);
    }

    public RedisUtil(JedisPoolConfig config, String host, int port, int connectionTimeout, int soTimeout, String password) {
        if (StringUtils.isBlank(password)) {
            password = null;
        }
        pool = new JedisPool(config, host, port, connectionTimeout, password, Protocol.DEFAULT_DATABASE);
    }

    public void put(String k, Object v, long timeout) {
        Jedis jedis = pool.getResource();
        try {
            jedis.set(k, gson.toJson(v), "NX", "PX", timeout);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public Boolean putIfNotExist(String k, Object v, long timeout) {
        Jedis jedis = pool.getResource();
        try {
            String code = jedis.set(k, gson.toJson(v), "NX", "PX", timeout);
            if (StringUtils.isNotBlank(code)) {
                return true;
            }
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void put(String k, Object v) {
        Jedis jedis = pool.getResource();
        try {
            jedis.set(k, gson.toJson(v));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void clean(String k) {
        Jedis jedis = pool.getResource();
        try {
            jedis.del(k);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public <T> T get(String k, Class<T> clazz) {
        Jedis jedis = pool.getResource();
        T t = null;
        try {
            String value = jedis.get(k);
            if (value != null) {
                t = gson.fromJson(value, clazz);
            }
            return t;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void zincrby(String key, int count, String member) {
        Jedis jedis = pool.getResource();
        try {
            jedis.zincrby(key, Double.valueOf(count), member);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<Tuple> zrevrangeWithScores(String k, int start, int end) {
        Jedis jedis = pool.getResource();
        try {
            Set<Tuple> tuples = jedis.zrevrangeWithScores(k, start, end);
            return tuples;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
