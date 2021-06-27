package com.example.school.configs;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Bean
    public PGPoolingDataSource getDataSource() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("1488228");
        return dataSource;
    }
}
