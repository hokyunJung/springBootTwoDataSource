package com.example.demo.primary;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = {"com.example.demo.primary.dao"})
@EnableConfigurationProperties
public class DataBaseConfigPrimary {
 
	@Primary
	@Bean
    @ConfigurationProperties(prefix = "app.datasource.primary")
    public DataSourceProperties dataSourceProp() {
		DataSourceProperties a = new DataSourceProperties();
        return a;
    }
	
      @Primary
      @Bean(name = "dataSource")
      @ConfigurationProperties(prefix = "app.datasource.primary.tomcat")
      public DataSource dataSource() {
        return dataSourceProp().initializeDataSourceBuilder().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
      }
 
      @Primary
      @Bean(name = "entityManagerFactory")   
      public LocalContainerEntityManagerFactoryBean entityManagerFactory(
              EntityManagerFactoryBuilder builder,
              @Qualifier("dataSource") DataSource dataSource,
              Environment env) {
        Map<String, Object> properties = new HashMap<String, Object>();    
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("primary.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getRequiredProperty("primary.hibernate.dialect"));
        properties.put("hibernate.implicit_naming_strategy", env.getRequiredProperty("spring.jpa.hibernate.naming.implicit-strategy"));
        properties.put("hibernate.physical_naming_strategy", env.getRequiredProperty("spring.jpa.hibernate.naming.physical-strategy"));
 
        return builder
          .dataSource(dataSource)
          .packages("com.example.demo.primary.entity")
          //.persistenceUnit("ShowA")
          .properties(properties)
          .build();
      }
 
      @Primary
      @Bean(name = "transactionManager")
      public PlatformTransactionManager transactionManager(
        @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
      }
 
}