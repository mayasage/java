package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(A1.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class A1_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String A2 = "a2";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	
	/**
	 * @see com.catalin.javapersistence.models.test.A1#a2
	 **/
	public static volatile SetAttribute<A1, A2> a2;
	
	/**
	 * @see com.catalin.javapersistence.models.test.A1#name
	 **/
	public static volatile SingularAttribute<A1, String> name;
	
	/**
	 * @see com.catalin.javapersistence.models.test.A1#description
	 **/
	public static volatile SingularAttribute<A1, String> description;
	
	/**
	 * @see com.catalin.javapersistence.models.test.A1
	 **/
	public static volatile EntityType<A1> class_;

}

