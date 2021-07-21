package com.example.school.configs;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

//    @Value("jdbc:postgresql://localhost:5432/postgres")
//    private String url;
//
//    @Value("postgres")
//    private String user;
//
//    @Value("1488228")
//    private String password;

    @Bean
    public PGPoolingDataSource getDataSource() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();

        YAMLConfig yamlConfig = new YAMLConfig();
        dataSource.setUrl(yamlConfig.getUrl());
        dataSource.setUser(yamlConfig.getUser());
        dataSource.setPassword(yamlConfig.getPassword());
        return dataSource;
    }
}
