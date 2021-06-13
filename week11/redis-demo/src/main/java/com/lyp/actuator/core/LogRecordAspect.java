package com.lyp.actuator.core;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面 打印请求、返回参数信息
 */
@Slf4j
//@Aspect // 定义一个切面
//@Configuration
public class LogRecordAspect {

//    // 定义切点Pointcut
//    @Pointcut("execution(* com.lyp.actuator.controller.*.*(..))")
//    public void exeService() {
//    }
//
//    @Around("exeService()")
//    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        assert sra != null;
//        HttpServletRequest request = sra.getRequest();
//
//        String method = request.getMethod();
//        String uri = request.getRequestURI();
//        String paraString = JSON.toJSONString(request.getParameterMap());
//        // result的值就是被拦截方法的返回值
//        Object result = pjp.proceed();
////        log.info("请求, URI: {}, method: {}, params: {}, return: {}", uri, method, paraString, JSON.toJSONString(result));
//        return result;
//    }
}