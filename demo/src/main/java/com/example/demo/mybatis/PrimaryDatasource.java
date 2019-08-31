package com.example.demo.mybatis;

import javax.sql.DataSource;

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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class PrimaryDatasource {
	//@Primary
    @Bean(name="primaryDataSource")
    @ConfigurationProperties(prefix="app.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
    }
 
    //@Primary
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Autowired @Qualifier("primaryDataSource") DataSource dataSource, ApplicationContext applicationContext)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config-primary.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mappersPrimary/**/*.xml"));
        return factoryBean.getObject();
    }
 
    //@Primary
    @Bean(name="sqlSession")
    public SqlSessionTemplate sqlSession(@Autowired @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
 
    //@Primary
    @Bean(name="primaryTransactionManager")
    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
