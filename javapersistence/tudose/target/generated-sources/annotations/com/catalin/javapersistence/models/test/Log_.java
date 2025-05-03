package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Log.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Log_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String MESSAGE = "message";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Log#message
	 **/
	public static volatile SingularAttribute<Log, String> message;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Log
	 **/
	public static volatile EntityType<Log> class_;

}

