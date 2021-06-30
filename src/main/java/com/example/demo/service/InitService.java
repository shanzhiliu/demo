package com.example.demo.service;

import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class InitService {


    @Value("${count}")
    private Long count;

    @Value("${host}")
    private String host;

    @PostConstruct
    public void preTest(){
        Jedis jedis = RedisUtil.getConnetPool(host);

//        删除数据
        jedis.del("test");
        jedis.del("test1");

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
            String key = "key" + i;
            String value = "";
            jedis.lpush("test1", key);
        }

         end = System.currentTimeMillis();
        System.out.println("---list------共花费-----" + (end-start)/1000.0 + "---秒------");


        start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            String key = "key" + i;
            String value = "";
            List<String> test1 = jedis.lrange("test1", i, i + 1);
            if(test1 != null && test1.size() > 0 ){
                String key1 = test1.get(0);
                String test = jedis.hget("test", key1);

                if(test.equals("key1")){
                    System.out.println("11233123123123123123213123123123123123123");
                }


            }
        }

        end = System.currentTimeMillis();
        System.out.println("---遍历------共花费-----" + (end-start)/1000.0 + "---秒------");


        //        删除数据
        jedis.del("test");
        jedis.del("test1");

        jedis.close();

    }

}
