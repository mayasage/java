package com.catalin.javapersistence.configuration;

import com.catalin.javapersistence.models.test.listeners.SecurityLoadEventListener;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(
//        "com.catalin.javapersistence.repositories"
        basePackages = "com.catalin.javapersistence.repositories",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class SpringDataConfiguration {

        @Value("${custom.spring.datasource.url}")
        private String datasourceUrl;

        @Value("${custom.spring.datasource.username}")
        private String datasourceUsername;

        @Value("${custom.spring.datasource.password}")
        private String datasourcePassword;

        @Value("${custom.spring.jpa.show-sql}")
        private Boolean jpaShowSql;

        @Value("${custom.spring.jpa.hibernate.ddl-auto}")
        private String hibernateDDLAuto;

        @Bean
        public DataSource primaryDataSource() {
                HikariDataSource dataSource = new HikariDataSource();
                dataSource.setJdbcUrl(datasourceUrl);
                dataSource.setUsername(datasourceUsername);
                dataSource.setPassword(datasourcePassword);
                dataSource.setTransactionIsolation("TRANSACTION_READ_COMMITTED"); // For MySql
                dataSource.setConnectionInitSql("SET SESSION innodb_lock_wait_timeout = 2"); // For MySql
                return dataSource;
        }

        @Bean
        public JpaVendorAdapter jpaVendorAdapter() {
                HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
                jpaVendorAdapter.setDatabase(Database.MYSQL);
                jpaVendorAdapter.setShowSql(jpaShowSql);
                return jpaVendorAdapter;
        }

        @Bean
        @Primary
        public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
                LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
                emf.setDataSource(primaryDataSource());
                Properties jpaProperties = new Properties();
                jpaProperties.put("hibernate.hbm2ddl.auto", hibernateDDLAuto);
                jpaProperties.put("hibernate.use_identifier_rollback", true);
//                jpaProperties.put("javax.persistence.query.timeout", "1000");
//                jpaProperties.put("javax.persistence.lock.timeout", "1000");
//                jpaProperties.put("spring.jpa.properties.javax.persistence.query.timeout", "1000");
//                jpaProperties.put("hibernate.generate_statistics", true);
//                jpaProperties.put("hibernate.format_sql", true);
//                jpaProperties.put("hibernate.log_slow_query", 20L);
                jpaProperties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
//                jpaProperties.put("hibernate.ejb.event.load", SecurityLoadEventListener.class);
                emf.setJpaProperties(jpaProperties);
                emf.setJpaVendorAdapter(jpaVendorAdapter());
                emf.setPackagesToScan("com.catalin.javapersistence");
                emf.setMappingResources("META-INF/orm.xml");
                emf.setPersistenceUnitName("primaryPersistenceUnit");
                return emf;
        }

        @Bean
        @Primary
        public JpaTransactionManager primaryTransactionManager(EntityManagerFactory primaryEntityManagerFactory) {
                return new JpaTransactionManager(primaryEntityManagerFactory);
        }

        @Bean
        public DataSource getDataSourceA() {
//                DriverManagerDataSource dataSource = new DriverManagerDataSource();
//                dataSource.setUrl(datasourceUrl.replace("persistence", "a"));
//                dataSource.setUsername(datasourceUsername);
//                dataSource.setPassword(datasourcePassword);

                HikariDataSource dataSource = new HikariDataSource();
                dataSource.setJdbcUrl(datasourceUrl.replace("persistence", "a"));
                dataSource.setUsername(datasourceUsername);
                dataSource.setPassword(datasourcePassword);
                dataSource.setTransactionIsolation("TRANSACTION_READ_COMMITTED"); // For MySql
                dataSource.setConnectionInitSql("SET SESSION innodb_lock_wait_timeout = 2"); // For MySql

                return dataSource;
        }

        @Bean(name = "emfa")
        public LocalContainerEntityManagerFactoryBean entityManagerFactoryA() {
                LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
                emf.setDataSource(getDataSourceA());
                Properties jpaProperties = new Properties();
                jpaProperties.put("hibernate.hbm2ddl.auto", hibernateDDLAuto);
                jpaProperties.put("hibernate.use_identifier_rollback", true);
//                jpaProperties.put("hibernate.generate_statistics", true);
//                jpaProperties.put("hibernate.format_sql", true);
//                jpaProperties.put("hibernate.log_slow_query", 20L);
                jpaProperties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
//                jpaProperties.put("hibernate.ejb.event.load", SecurityLoadEventListener.class);
                emf.setJpaProperties(jpaProperties);
                emf.setJpaVendorAdapter(jpaVendorAdapter());
                emf.setPackagesToScan("com.catalin.javapersistence");
                emf.setMappingResources("META-INF/orm.xml");
//                emf.setPersistenceUnitName("a");
                return emf;
        }

        @Bean
        public JpaTransactionManager transactionManagerA(EntityManagerFactory entityManagerFactoryA) {
                return new JpaTransactionManager(entityManagerFactoryA);
        }

        @Bean
        public HikariDataSource getDataSourceB() {
//                DriverManagerDataSource dataSource = new DriverManagerDataSource();
//                dataSource.setUrl(datasourceUrl.replace("persistence", "b"));
//                dataSource.setUsername(datasourceUsername);
//                dataSource.setPassword(datasourcePassword);

                HikariDataSource dataSource = new HikariDataSource();
                dataSource.setJdbcUrl(datasourceUrl.replace("persistence", "b"));
                dataSource.setUsername(datasourceUsername);
                dataSource.setPassword(datasourcePassword);
                dataSource.setTransactionIsolation("TRANSACTION_READ_COMMITTED"); // For MySql
                dataSource.setConnectionInitSql("SET SESSION innodb_lock_wait_timeout = 2"); // For MySql

                return dataSource;
        }

        @Bean(name = "emfb")
        public LocalContainerEntityManagerFactoryBean entityManagerFactoryB() {
                LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
                emf.setDataSource(getDataSourceB());
                Properties jpaProperties = new Properties();
                jpaProperties.put("hibernate.hbm2ddl.auto", hibernateDDLAuto);
                jpaProperties.put("hibernate.use_identifier_rollback", true);
//                jpaProperties.put("hibernate.generate_statistics", true);
//                jpaProperties.put("hibernate.format_sql", true);
//                jpaProperties.put("hibernate.log_slow_query", 20L);
                jpaProperties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
//                jpaProperties.put("hibernate.ejb.event.load", SecurityLoadEventListener.class);
                emf.setJpaProperties(jpaProperties);
                emf.setJpaVendorAdapter(jpaVendorAdapter());
                emf.setPackagesToScan("com.catalin.javapersistence");
                emf.setMappingResources("META-INF/orm.xml");
//                emf.setPersistenceUnitName("b");
                return emf;
        }

        @Bean
        public JpaTransactionManager transactionManagerB(EntityManagerFactory entityManagerFactoryB) {
                return new JpaTransactionManager(entityManagerFactoryB);
        }

        @Bean
        public SessionFactory sessionFactory(EntityManagerFactory entityManagerFactory) {
                System.out.println("sessionFactory");
                return entityManagerFactory.unwrap(SessionFactory.class);
        }
}
