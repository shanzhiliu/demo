package com.example.demo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

public class RedisUtil {

    public static Jedis  getConnet(){
        Jedis jedis = new Jedis("10.128.0.4", 6379, 10000);
        return jedis;
    }

    public static Jedis  getConnetPool(String host) {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(18);
        JedisPool pool = new JedisPool(config, host, 6379, 20000);
        Jedis jedis = pool.getResource();
        return jedis;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String s = list.get(0);

        System.out.println(s);
    }
}
