package com.lyp.actuator.service;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

@Slf4j
public class RedisLock {

    private static final String lock_key = "redis_lock"; //锁键

    static long internalLockLeaseTime = 60 * 1000;//锁过期时间

    private  static final long timeout = 6 * 1000; //获取锁的超时时间

    //SET命令的参数
    static SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);

    static JedisPool jedisPool = MyJedisPool.getJedisPool();

    public static void main(String[] args) throws InterruptedException {
        String id = "random";
        System.out.println(lock(id));
    }

    public static boolean lock(String id) throws InterruptedException{

        try (Jedis jedis = jedisPool.getResource()) {
            long start = System.currentTimeMillis();
            for (; ; ) {
                //SET命令返回OK ，则证明获取锁成功
                String result = jedis.set(lock_key, id, params);
                if ("OK".equals(result)) {
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeout) {
                    return false;
                }
                Thread.sleep(10);
                // 直接return 失败
                return false;
            }
        }
    }

    /**
     * 先判断当前锁的字符串是否与传入的值相等，是的话就删除Key，解锁成功。
     */
    public boolean unlock(String id){
        try (Jedis jedis = jedisPool.getResource()) {
            String script =
                    "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                            "   return redis.call('del',KEYS[1]) " +
                            "else" +
                            "   return 0 " +
                            "end";
            Object result = jedis.eval(script,
                    Collections.singletonList(lock_key),
                    Collections.singletonList(id));
            return "1".equals(result.toString());
        }
    }
}