package com.lyp.actuator.service;


import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
public class MyJedisPool {

    private static JedisPool jedisPool;
    private static final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    static String ip = "127.0.0.1";
    static int port = 6379;
    static int timeout = 2000;

    public static void init() {

        // 初始化jedis
        // 设置配置
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(100);
        jedisPoolConfig.setTestOnBorrow(false); //jedis 第一次启动时，会报错
        jedisPoolConfig.setTestOnReturn(true);

        // 初始化JedisPool
        jedisPool = new JedisPool(jedisPoolConfig, ip, port, timeout);
    }

    public static JedisPool getJedisPool(){
        if (jedisPool==null)
            init();
        return jedisPool;
    }


}
