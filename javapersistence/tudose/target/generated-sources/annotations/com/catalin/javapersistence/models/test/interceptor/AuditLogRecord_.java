package com.catalin.javapersistence.models.test.interceptor;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuditLogRecord.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuditLogRecord_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String ENTITY_CLASS = "entityClass";
	public static final String ENTITY_ID = "entityId";
	public static final String MESSAGE = "message";
	public static final String USER_ID = "userId";

	
	/**
	 * @see com.catalin.javapersistence.models.test.interceptor.AuditLogRecord#entityClass
	 **/
	public static volatile SingularAttribute<AuditLogRecord, Class> entityClass;
	
	/**
	 * @see com.catalin.javapersistence.models.test.interceptor.AuditLogRecord#entityId
	 **/
	public static volatile SingularAttribute<AuditLogRecord, Long> entityId;
	
	/**
	 * @see com.catalin.javapersistence.models.test.interceptor.AuditLogRecord#message
	 **/
	public static volatile SingularAttribute<AuditLogRecord, String> message;
	
	/**
	 * @see com.catalin.javapersistence.models.test.interceptor.AuditLogRecord
	 **/
	public static volatile EntityType<AuditLogRecord> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.interceptor.AuditLogRecord#userId
	 **/
	public static volatile SingularAttribute<AuditLogRecord, Long> userId;

}

