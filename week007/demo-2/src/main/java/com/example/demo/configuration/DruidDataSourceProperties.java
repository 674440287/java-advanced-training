package com.example.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperties {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private int initialSize;
    private int minIdle;
    private int maxActive = 100;
    private long maxWait;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;

    public DruidDataSourceProperties(Environment env) {
        this.driverClassName = env.getProperty("spring.datasource.driver-class-name");
        this.url = env.getProperty("spring.datasource.url");
        this.username = env.getProperty("spring.datasource.username");
        this.password=env.getProperty("spring.datasource.password");
    }

}