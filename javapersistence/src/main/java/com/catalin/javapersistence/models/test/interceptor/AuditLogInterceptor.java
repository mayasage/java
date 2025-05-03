package com.catalin.javapersistence.models.test.interceptor;

import lombok.AccessLevel;
import lombok.Setter;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.type.Type;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Setter
public class AuditLogInterceptor implements Interceptor {

        private Session currentSession;
        private Long currentUserId;

        @Setter(value = AccessLevel.NONE)
        private final Set<Auditable> inserts = new HashSet<>();

        @Setter(value = AccessLevel.NONE)
        private final Set<Auditable> updates = new HashSet<>();

        @Override
        public boolean onPersist(Object entity, Object id, Object[] state, String[] propertyNames, Type[] propertyTypes) {
                System.out.println("AuditLogInterceptor.onInsert");

                if (entity instanceof Auditable auditable) {

                        System.out.println("AuditLogInterceptor.onInsert entity: " + auditable);

                        inserts.add(auditable);
                }

                return false;
        }

        @Override
        public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {

                System.out.println("AuditLogInterceptor.onFlushDirty");

                if (entity instanceof Auditable auditable) {

                        System.out.println("AuditLogInterceptor.onFlushDirty entity: " + auditable);

                        updates.add(auditable);
                }
                return false;
        }

        @Override
        public void postFlush(Iterator<Object> entities) throws CallbackException {
                try (Session newSession = currentSession.sessionWithOptions().connection().openSession()) {

                        System.out.println("Post Flush");

                        for (Auditable auditable : inserts) {

                                System.out.println("Insert " + auditable);

                                AuditLogRecord auditLogRecord = new AuditLogRecord();
                                auditLogRecord.setMessage("insert");
                                auditLogRecord.setEntityId(auditable.getId());
                                auditLogRecord.setUserId(currentUserId);
                                auditLogRecord.setEntityClass(auditable.getClass());

                                newSession.persist(auditLogRecord);
                        }

                        for (Auditable auditable : updates) {

                                System.out.println("Update " + auditable);

                                AuditLogRecord auditLogRecord = new AuditLogRecord();
                                auditLogRecord.setMessage("update");
                                auditLogRecord.setEntityId(auditable.getId());
                                auditLogRecord.setUserId(currentUserId);
                                auditLogRecord.setEntityClass(auditable.getClass());

                                newSession.persist(auditLogRecord);
                        }

                        newSession.flush();
                }

                inserts.clear();
                updates.clear();
        }
}
