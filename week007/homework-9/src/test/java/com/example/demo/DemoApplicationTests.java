package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    @Qualifier("master")
    DataSource dataSource;

    @Autowired
    @Qualifier("slave")
    DataSource dataSource2;

    @Test
    void contextLoads(){
        System.out.println("--------------");
        System.out.println(dataSource);
        System.out.println("--------------");
        System.out.println(dataSource2);
    }

}
