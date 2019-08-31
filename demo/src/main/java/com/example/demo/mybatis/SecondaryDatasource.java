package com.example.demo.mybatis;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
 
@Configuration
public class SecondaryDatasource {

    @Bean(name="secondaryDataSource")
    @ConfigurationProperties(prefix="app.datasource.second")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
    }

    @Bean(name="secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactoryBean(@Autowired @Qualifier("secondaryDataSource") DataSource secondaryDataSource, ApplicationContext applicationContext)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(secondaryDataSource);
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mappersSecond/**/*.xml"));
        return factoryBean.getObject();
    }
   
    @Bean(name="secondarySqlSession")
    public SqlSession secondarySqlSession(@Autowired @Qualifier("secondarySqlSessionFactory") SqlSessionFactory secondarySqlSessionFactory) {
        return new SqlSessionTemplate(secondarySqlSessionFactory);
    }
     
    @Bean(name="secondaryTransactionManager")
    public DataSourceTransactionManager secondaryTransactionManager(@Autowired @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        return new DataSourceTransactionManager(secondaryDataSource);
    }

}