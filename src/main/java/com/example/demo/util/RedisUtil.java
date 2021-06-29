package com.example.demo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    public static Jedis  getConnet(){
        Jedis jedis = new Jedis("127.0.0.1", 1499, 10000);
        return jedis;
    }

    public static Jedis  getConnetPool() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(18);
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379, 20000);
        Jedis jedis = pool.getResource();
        return jedis;
    }
}
