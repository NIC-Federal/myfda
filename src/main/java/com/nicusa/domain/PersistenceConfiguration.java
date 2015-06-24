package com.nicusa.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class PersistenceConfiguration {

  @Bean
  @Profile("local")
  public DataSource dataSource() throws SQLException {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder.setType(EmbeddedDatabaseType.HSQL).build();
  }

  @Bean
  public JpaVendorAdapter vendorAdapter() {
    EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.HSQL);
    vendorAdapter.setGenerateDdl(true);
    vendorAdapter.setShowSql(true);
    return vendorAdapter;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager();
  }


  @Bean
  public TransactionTemplate transactionTemplate() {
    return new TransactionTemplate(transactionManager());
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter());
    factory.setSharedCacheMode(SharedCacheMode.NONE);
    factory.setPackagesToScan("com.nicusa.domain");
    factory.getJpaPropertyMap().put("eclipselink.weaving", "false");
    factory.getJpaPropertyMap().put("eclipselink.logging.level", "FINE");
    factory.getJpaPropertyMap().put("eclipselink.validate-existence", "true");
    factory.getJpaPropertyMap().put("eclipselink.id-validation", "NULL");
    factory.setDataSource(dataSource());
    return factory;
  }


}
