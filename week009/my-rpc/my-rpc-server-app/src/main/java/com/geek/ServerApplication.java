package com.geek;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 服务端app启动类
 */
public class ServerApplication {

    public static void main(String[] args) {
        // 加载spring上下文，从而启动服务
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.geek");
    }
}
