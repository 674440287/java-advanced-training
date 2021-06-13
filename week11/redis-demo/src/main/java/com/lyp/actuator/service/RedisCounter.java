package com.lyp.actuator.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisCounter {
    static JedisPool jedisPool = MyJedisPool.getJedisPool();

    public static void main(String[] args) {

    }

    public static long incr(int id){
        String key = "stock:"+id;
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr(key);
        }
    }

    public static long decr(int id){
        String key = "stock:"+id;
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.decr(key);
        }
    }
}
