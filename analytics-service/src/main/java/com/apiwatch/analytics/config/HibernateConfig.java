package com.apiwatch.analytics.config;


import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.hibernate.HibernateTransactionManager;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {

        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();

        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.apiwatch.analytics.entity");

        Properties properties = new Properties();
        properties.put("hibernate.dialect",
                "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "false");

        factory.setHibernateProperties(properties);

        return factory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(
            SessionFactory sessionFactory) {

        return new HibernateTransactionManager(sessionFactory);
    }
}

