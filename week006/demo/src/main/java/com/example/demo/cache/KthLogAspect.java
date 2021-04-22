package com.example.demo.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class KthLogAspect {

    @Pointcut("@annotation(com.example.demo.cache.KthLog)")
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

    @Around("pointcut() && @annotation(logger)")
    public Object advice(ProceedingJoinPoint joinPoint, KthLog logger) {
        System.out.println("["
                + joinPoint.getSignature().getDeclaringType().getSimpleName()
                + "][" + joinPoint.getSignature().getName()
                + "]-日志内容-[" + logger.value() + "]");

        Object result = null;

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if(args[i] instanceof Integer) {
                args[i] = (Integer)args[i] - 1;
                break;
            }
        }

        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if(result instanceof User) {
            User user = (User) result;
            user.setId(user.getId() + 1);
            return user;
        }
        return result;
    }


}
