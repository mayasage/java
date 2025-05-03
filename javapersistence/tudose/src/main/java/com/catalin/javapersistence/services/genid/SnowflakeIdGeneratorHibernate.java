package com.catalin.javapersistence.services.genid;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

public class SnowflakeIdGeneratorHibernate implements IdentifierGenerator, Configurable {

        private static IdGenerator idGenerator;

        public static void setIdGenerator(IdGenerator idGenerator) {
                System.out.println("ye chala kya bhai? " + idGenerator);
                SnowflakeIdGeneratorHibernate.idGenerator = idGenerator;
        }

        @Override
        public Long generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
                System.out.println("SnowflakeIdGeneratorHibernate.generate");
                if (idGenerator == null) throw new IllegalStateException("IdGenerator is null");
                return idGenerator.nextId();
        }

        @Override
        public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
                System.out.println("chal rha hai kya");
                IdentifierGenerator.super.configure(type, parameters, serviceRegistry);
        }
}

