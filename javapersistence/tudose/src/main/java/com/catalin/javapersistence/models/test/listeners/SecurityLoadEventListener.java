package com.catalin.javapersistence.models.test.listeners;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.internal.DefaultPostLoadEventListener;
import org.hibernate.event.internal.DefaultPreLoadEventListener;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PreLoadEvent;

import java.io.Serializable;

public class SecurityLoadEventListener extends DefaultPostLoadEventListener {

        @Override
        public void onPostLoad(PostLoadEvent event) {
                System.out.println("SecurityLoadEventListener");

//                boolean authorized = MySecurity.isAuthorized(event.getEntity().getClass().getName(), (Long) event.getId());
//                if (!authorized) throw new MySecurityException("Access denied");

                super.onPostLoad(event);
        }

        private static class MySecurity {
                static boolean isAuthorized(String entityName, Serializable entityId) {
                        System.out.println("SecurityLoadEventListener.isAuthorized: " + " entityName: " + entityName +
                                           ", entityId:" +
                                           " " + entityId);
                        return true;
                }
        }

        private static class MySecurityException extends RuntimeException {
                public MySecurityException(String message) {
                        super(message);
                }
        }
}
