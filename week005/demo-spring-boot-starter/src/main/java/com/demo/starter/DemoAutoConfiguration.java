package com.demo.starter;

import com.demo.starter.pojo.Klass;
import com.demo.starter.pojo.School;
import com.demo.starter.pojo.Student;
import com.demo.starter.prop.SpringBootPropertiesConfiguration;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Configuration
@ComponentScan("com.demo.starter")
@EnableConfigurationProperties({SpringBootPropertiesConfiguration.class})
//@ConditionalOnProperty(prefix = "demo", name = "enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@RequiredArgsConstructor
public class DemoAutoConfiguration {

    private final SpringBootPropertiesConfiguration props;

    @Bean
    public Student shardingSphereDataSource() {
        System.out.println(props);
        return new Student();
    }
}
