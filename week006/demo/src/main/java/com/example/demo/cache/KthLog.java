package com.example.demo.cache;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface KthLog {

    String value() default "";
}
