package com.demo.starter;

import com.demo.starter.pojo.Klass;
import com.demo.starter.pojo.School;
import com.demo.starter.pojo.Student;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Spring boot starter configuration.
 */
@Configuration
@ComponentScan("com.demo.starter")
@EnableConfigurationProperties({Klass.class,School.class,Student.class})
@ConditionalOnProperty(prefix = "demo", name = "enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@RequiredArgsConstructor
public class DemoAutoConfiguration {

    private final Klass klass;

    @Bean
    public Klass klass() {
        return new Klass();
    }

}
