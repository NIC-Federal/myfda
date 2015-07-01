package com.nicusa;

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
import java.util.HashMap;
import java.util.Map;

@Profile("hsqldb")
@Configuration
public class HsqlDBPersistenceConfiguration {

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager();
  }


  @Bean
  public TransactionTemplate transactionTemplate() {
    return new TransactionTemplate(transactionManager());
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
    return embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.HSQL).build();
  }

  @Bean
  public JpaVendorAdapter vendorAdapter() {
    EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.HSQL);
    vendorAdapter.setGenerateDdl(true);
    return vendorAdapter;
  }

  @Bean
  public Map<String, Object> jpaPropertyMap() {
    Map<String,Object> jpaPropertyMap = new HashMap<String, Object>();
    jpaPropertyMap.put("eclipselink.weaving", "false");
    jpaPropertyMap.put("eclipselink.logging.level", "FINE");
    jpaPropertyMap.put("eclipselink.validate-existence", "true");
    jpaPropertyMap.put("eclipselink.id-validation", "NULL");
    return jpaPropertyMap;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter());
    factory.setSharedCacheMode(SharedCacheMode.NONE);
    factory.setPackagesToScan("com.nicusa.domain");
    factory.setJpaPropertyMap(jpaPropertyMap());
    factory.setDataSource(dataSource());
    return factory;
  }
}
