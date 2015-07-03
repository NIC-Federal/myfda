package com.nicusa.testing.tests.config

import com.nicusa.testing.tests.rules.ClearDatabaseBeforeAndAfterTest
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

import javax.sql.DataSource
import java.beans.beancontext.BeanContext

@Configuration
@PropertySources([
    @PropertySource(value = 'file:${sys:user.home}/.nic/unikitty.properties', ignoreResourceNotFound = true),
    @PropertySource(value = 'file:${user.home}/.nic/unikitty.properties', ignoreResourceNotFound = true) ])
class TestConfiguration {

    @Value('${mysql.username}') String username;

    @Value('${mysql.password}') String password;

    @Value('${mysql.jdbc.url}') String jdbcUrl;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(jdbcUrl);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTempalte() {
        return new JdbcTemplate(dataSource());;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(platformTransactionManager());
    }

    @Bean
    public ClearDatabaseBeforeAndAfterTest clearDatabaseBeforeAndAfterTest() {
        return new ClearDatabaseBeforeAndAfterTest(jdbcTempalte(), transactionTemplate());;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
