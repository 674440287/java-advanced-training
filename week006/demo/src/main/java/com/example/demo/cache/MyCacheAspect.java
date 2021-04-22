package com.example.demo.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class MyCacheAspect {


    Map<String, Cache<Integer,Object>> cacheMap = new HashMap<>();


    @Pointcut("@annotation(com.example.demo.cache.MyCache)")
    private void pointcut() {}

//    @Before("pointcut() && @annotation(logger)")
//    public void advice(KthLog logger) {
//        System.out.println("--- Kth日志的内容为[" + logger.value() + "] ---");
//    }
//
//    @Around("pointcut() && @annotation(logger)")
//    public Object advice(ProceedingJoinPoint joinPoint, KthLog logger) {
//        System.out.println("["
//                + joinPoint.getSignature().getDeclaringType().getSimpleName()
//                + "][" + joinPoint.getSignature().getName()
//                + "]-日志内容-[" + logger.value() + "]");
//
//        Object result = null;
//
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        User user = new User();
//        user.setName("电子竞技");
//        return user;
//    }

    @Around("pointcut() && @annotation(cache)")
    public Object advice(ProceedingJoinPoint joinPoint, MyCache cache) {
        String functionName = joinPoint.getSignature().getDeclaringType() + joinPoint.getSignature().getName();
        if (!cacheMap.containsKey(functionName)){
            cacheMap.put(functionName, CacheBuilder.newBuilder()
                    .maximumSize(2)
                    .expireAfterWrite(cache.value(), TimeUnit.SECONDS)
                    .build());
        }

        Object result = null;
        Object[] args = joinPoint.getArgs();
        int userId = Integer.parseInt(args[0].toString());
        Object cacheObject = cacheMap.get(functionName).getIfPresent(userId);
        if (cacheObject==null){
            try {
                result = joinPoint.proceed(args);
                cacheMap.get(functionName).put(userId, result);
                System.out.println("function process");
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }else {
            System.out.println("use cache");
            result = cacheObject;
        }

        return result;
    }

}
