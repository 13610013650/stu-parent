package com.stu.activiti.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.stu.activiti.config.bean.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @package com.stu.activiti.config
 * @auther ZhangSheng
 * @description 数据源配置
 * @date 2020/1/7
 * @EMAIL 2647665953@qq.com
 */
@Configuration
public class DataSourceConfig {

    private DataSourceProperties properties;

    @Autowired
    public DataSourceConfig(DataSourceProperties properties){
        this.properties = properties;
    }


    @Bean
    public DataSource dataSource() throws IllegalAccessException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(properties.getJdbcUrl());
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setMinIdle(properties.getMinIdle());
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setDbType(properties.getType());
        return druidDataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() throws IllegalAccessException {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(this.dataSource());
        return manager;
    }

}
