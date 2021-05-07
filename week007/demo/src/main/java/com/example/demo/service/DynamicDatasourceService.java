package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DynamicDatasourceService {

    @Autowired
    @Qualifier("dataSource1")
    DataSource dataSource;

    @Autowired
    @Qualifier("dataSource2")
    DataSource dataSource2;


    public String test(int a){
        if (a<1){

        }
        return "qwer";
    }

}
