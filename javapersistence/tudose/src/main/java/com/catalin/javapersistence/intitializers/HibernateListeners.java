package com.catalin.javapersistence.intitializers;

import com.catalin.javapersistence.models.test.listeners.SecurityLoadEventListener;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.service.spi.EventListenerGroup;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HibernateListeners {

        private final EntityManagerFactory emf;

        @EventListener(ApplicationReadyEvent.class)
        public void init() {
                System.out.println("hibernateListeners");

                EventListenerRegistry registry =
                        emf
                                .unwrap(SessionFactoryImpl.class)
                                .getServiceRegistry()
                                .getService(EventListenerRegistry.class);

                assert registry != null;
                registry.setListeners(EventType.POST_LOAD, new SecurityLoadEventListener());
        }
}
