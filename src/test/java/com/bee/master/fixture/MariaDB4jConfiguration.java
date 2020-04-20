package com.bee.master.fixture;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MariaDB4jConfiguration {
    @Value("${spring.flyway.locations}")
    private String migrationLocation;

    @Bean(destroyMethod = "stop")
    public MariaDB4jSpringService mariaDB4j() {
        MariaDB4jSpringService mariaDB4jSpringService = new MariaDB4jSpringService();
        mariaDB4jSpringService.getConfiguration().addArg("--user=root");
        mariaDB4jSpringService.getConfiguration().addArg("--character-set-server=utf8");
        return mariaDB4jSpringService;
    }

    @Bean
    public DataSource dataSource(MariaDB4jSpringService mariaDB4jSpringService,
                                 DataSourceProperties dataSourceProperties) throws ManagedProcessException {
        mariaDB4jSpringService.getDB().createDB(dataSourceProperties.getName());
        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(mariaDB4jSpringService.getConfiguration().getURL(dataSourceProperties.getName()))
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .build();
    }
}
