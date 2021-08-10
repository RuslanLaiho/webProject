package com.example.school.configs;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Url")
public class DataSourceConfig {

    @Value("${Url}")
    private String url;

    @Value("${User}")
    private String user;

    @Value("${Password}")
    private String password;

    @Bean
    public PGPoolingDataSource getDataSource() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();

        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        return dataSource;
    }
}
