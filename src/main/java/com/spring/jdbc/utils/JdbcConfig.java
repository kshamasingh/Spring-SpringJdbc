package com.spring.jdbc.utils;

import com.spring.jdbc.dao.StudentDao;
import com.spring.jdbc.dao.impl.StudentDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:service-config/application.properties"})
@ComponentScan(basePackages = {})
public class JdbcConfig {

    @Value("${jdbc.driver.class.name}")
    private String jdbcDriverClassName;

    @Value("${jdbc.mysql.url}")
    private String jdbcMysqlUrl;

    @Value("${jdbc.mysql.username}")
    private String jdbcMysqlUsername;

    @Value("${jdbc.mysql.password}")
    private String jdbcMysqlPassword;

    @Bean("studentDao")
    public StudentDao studentDao() {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        studentDao.setJdbcTemplate(jdbcTemplate());
        return studentDao;
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean("ds")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(jdbcDriverClassName);
        ds.setUrl(jdbcMysqlUrl);
        ds.setUsername(jdbcMysqlUsername);
        ds.setPassword(jdbcMysqlPassword);
        return ds;
    }

}
