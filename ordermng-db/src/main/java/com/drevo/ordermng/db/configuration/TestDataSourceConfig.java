package com.drevo.ordermng.db.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@ConfigurationProperties(prefix = "spring.datasource")
public class TestDataSourceConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
            .driverClassName(driverClassName)
            .url(url)
            .username(username)
            .password(password)
            .build();
    }
}
