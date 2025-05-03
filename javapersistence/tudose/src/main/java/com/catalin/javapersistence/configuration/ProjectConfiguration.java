package com.catalin.javapersistence.configuration;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import com.catalin.javapersistence.services.genid.IdGenerator;
import com.catalin.javapersistence.services.genid.SnowflakeIdGenerator;
import com.catalin.javapersistence.services.genid.SnowflakeIdGeneratorHibernate;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {

        @Value("${datacenter.id}")
        private short datacenterId;

        @Value("${worker.id}")
        private short workerId;

        @Bean
        public IdGenerator idGenerator() {
                return new SnowflakeIdGenerator(datacenterId, workerId);
        }

        @Bean
        public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
                return beanFactory -> {
                        IdGenerator idGenerator = beanFactory.getBean(IdGenerator.class);
                        AbstractEntityBase.setIdGenerator(idGenerator);
                        SnowflakeIdGeneratorHibernate.setIdGenerator(idGenerator);
                };
        }
}
