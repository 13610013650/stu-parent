package com.stu.activiti.config.bean;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ZhangSheng
 * @package com.stu.activiti.config.bean
 * @description 数据源配置
 * @date 2020/1/7
 * @EMAIL 2647665953@qq.com
 */
@Getter
@Setter
@Configuration
@PropertySource("classpath:application-datasource.yml")
@ConfigurationProperties(prefix = "spring.datasource" )
public class DataSourceProperties {

    private String username;
    private String password;
    private String jdbcUrl;
    private String driverClassName;
    private String type;
    private int initialSize;
    private int minIdle=2;
    private int maxActive=20;
    private int maxWait=60000;
    private int timeBetweenEvictionRunsMillis=60000;
    private int minEvictableIdleTimeMillis=300000;
    private String  validationQuery="SELECT 1 FROM DUAL";
    private boolean testWhileIdle=true;
    private boolean testOnBorrow= false;
    private boolean testOnReturn= false;
    private boolean poolPreparedStatements= true;
    private int maxPoolPreparedStatementPerConnectionSize= 20;
    private String filters= "stat,wall,log4j";
    //private String connectionProperties;
}
