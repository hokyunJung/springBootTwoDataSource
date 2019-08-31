package com.example.demo.second;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "secondEntityManagerFactory",
  transactionManagerRef = "secondTransactionManager",
  basePackages = {"com.example.demo.second.dao"})
public class DataBaseConfigSecond {
     
	@Bean
    @ConfigurationProperties(prefix = "app.datasource.second")
    public DataSourceProperties secondDataSourceProp() {
		DataSourceProperties a = new DataSourceProperties();
        return a;
    }
	
      @Bean(name = "secondDataSource")
      @ConfigurationProperties(prefix = "app.datasource.second")
      public DataSource secondDataSource() {
        //return DataSourceBuilder.create().build();
        return secondDataSourceProp().initializeDataSourceBuilder().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();

      }
 
      @Bean(name = "secondEntityManagerFactory")
      public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
              EntityManagerFactoryBuilder builder,
              @Qualifier("secondDataSource") DataSource secondDataSource,
              Environment env) {
        Map<String, Object> properties = new HashMap<String, Object>();    
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("second.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getRequiredProperty("second.hibernate.dialect"));
        properties.put("hibernate.implicit_naming_strategy", env.getRequiredProperty("spring.jpa.hibernate.naming.implicit-strategy"));
        properties.put("hibernate.physical_naming_strategy", env.getRequiredProperty("spring.jpa.hibernate.naming.physical-strategy"));
          
         
        return builder
          .dataSource(secondDataSource)
          .packages("com.example.demo.second.entity")
          .persistenceUnit("ShowB")
          .properties(properties)
          .build();
      }
 
      @Bean(name = "secondTransactionManager")
      public PlatformTransactionManager secondTransactionManager(
        @Qualifier("secondEntityManagerFactory") EntityManagerFactory secondEntityManagerFactory) {
        return new JpaTransactionManager(secondEntityManagerFactory);
      }
     
}