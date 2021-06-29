package com.example.demo.service;

import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;


@Service
public class InitService {


    @Value("${count}")
    private Long count;

    @PostConstruct
    public void preTest(){
        Jedis jedis = RedisUtil.getConnetPool();


        long start = System.currentTimeMillis();


        for (long i = 0; i < count; i++) {

            String key = "key" + i;
            String value = "";
            jedis.hset("test",key,"债券承销交易币种 EV010102000001 描述证券化承销交易币种。债券承销交易币种 EV010102000001 描述证券化承销交易币种。 ");
        }

        long end = System.currentTimeMillis();
        System.out.println("---map------共花费-----" + (end-start)/1000.0 + "---秒------");

        start = System.currentTimeMillis();

        for (long i = 0; i < count; i++) {
            jedis.lpush("test1","债券承销交易币种 EV010102000001 描述证券化承销交易币种。债券承销交易币种 EV010102000001 描述证券化承销交易币种。 ");
        }

         end = System.currentTimeMillis();
        System.out.println("---list------共花费-----" + (end-start)/1000.0 + "---秒------");


        jedis.close();

    }

}
